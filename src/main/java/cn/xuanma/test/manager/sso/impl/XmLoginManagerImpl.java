package cn.xuanma.test.manager.sso.impl;


import cn.xuanma.common.config.redis.service.RedisService;
import cn.xuanma.common.distributed.annotation.DistributedLock;
import cn.xuanma.common.enums.BizErrorEnum;
import cn.xuanma.common.exception.BizRuntimeException;
import cn.xuanma.common.utils.DateUtils;
import cn.xuanma.test.bean.dto.UserInfoDTO;
import cn.xuanma.test.bean.dto.XmLoginDto;
import cn.xuanma.test.bean.entity.AuthAdmin;
import cn.xuanma.test.bean.entity.AuthAdminPosition;
import cn.xuanma.test.bean.entity.AuthApp;
import cn.xuanma.test.bean.entity.AuthDepartment;
import cn.xuanma.test.constants.SecurityConstants;
import cn.xuanma.test.enums.AuthBizErrorEnum;
import cn.xuanma.test.enums.CacheKeyEnum;
import cn.xuanma.test.manager.sso.XmLoginManager;
import cn.xuanma.test.service.AuthAdminPositionService;
import cn.xuanma.test.service.AuthAdminService;
import cn.xuanma.test.service.AuthAppService;
import cn.xuanma.test.service.AuthDepartmentService;
import cn.xuanma.test.utils.JwtUtils;
import cn.xuanma.test.utils.PasswordGenerateUtils;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 登录操作实现
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
@Service("xmLoginManager")
@Slf4j
public class XmLoginManagerImpl implements XmLoginManager {

    private String env;

    @Value("${xm-auth.auth.orgId:1}")
    private Integer orgId;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AuthAdminService authAdminService;

    @Autowired
    private AuthAppService authAppService;

    @Autowired
    private AuthDepartmentService authDepartmentService;

    @Autowired
    private AuthAdminPositionService authAdminPositionService;


    @Override
    @DistributedLock(prefixKey = "xm-auth:phone:login:%s", isWait = true, waitTime = 13, timeout = 10, fullback = BizErrorEnum.DISTRBUTE_IN_HANDLE)
    public String login(XmLoginDto loginDto) {
        String passwordCountKey   = CacheKeyEnum.PASSWORD_COUNT_KEY.getKey(loginDto.getPhone());
        String passwordTimeOutKey = CacheKeyEnum.PASSWORD_TIMEOUT_KEY.getKey(loginDto.getPhone());
        Integer passwordCount = 0;
        String token = "";
        try {
            passwordCount = redisService.getCacheObject(passwordCountKey);
            if (null == passwordCount) {
                passwordCount = 0;
            }
            if (passwordCount >= SecurityConstants.PASSWORD_LOCK_TIMES) {
                Date passwordTimeout = redisService.getCacheObject(passwordTimeOutKey);
                Date currentDate = new Date();
                Double diffMin = DateUtils.differentMinutes(currentDate, passwordTimeout);
                if (diffMin > 0) {
                    String msg = String.format(AuthBizErrorEnum.LOGIN_LOCK_TIME.getMsg(), diffMin);
                     throw new BizRuntimeException(AuthBizErrorEnum.LOGIN_LOCK_TIME.getErrorCode(), AuthBizErrorEnum.LOGIN_LOCK_TIME.getMiniErrorCode(),msg);
                }
            }
            //后续会校验一些白名单规则，如果用户不属于白名单，需要手机验证码校验
            if (StringUtils.isNotBlank(loginDto.getCode())) {
                String oldVcode = redisService.getCacheObject(CacheKeyEnum.SMS_PHONE_CODE.getKey(loginDto.getPhone()));
                if (!loginDto.getCode().equals(oldVcode)) {
                    throw new BizRuntimeException(AuthBizErrorEnum.LOGIN_SMSCODE_ILLEGAL);
                }
            }

            token = setToken(loginDto, true);
            passwordCount = 0;
        } catch (BizRuntimeException e) {
            passwordCount++;
            redisService.setCacheObject(passwordTimeOutKey, DateUtils.add(new Date(), SecurityConstants.PASSWORD_UNLOCK_MINUTES + DateUtils.MINUTE));
            log.error("登录失败：" + e.getMessage(), JSONObject.toJSONString(loginDto));
            throw e;
        } finally {
            redisService.setCacheObject(passwordCountKey, passwordCount);
        }

        return token;
    }



    /**
     * 设置token
     *
     * @param loginDto
     * @param passwordCheck
     * @return
     */
    private String setToken(XmLoginDto loginDto, Boolean passwordCheck) {
        AuthApp authApp = getApp(loginDto);
        AuthAdmin authAdmin = getAdmin(loginDto);
        if (passwordCheck) {
            String password = PasswordGenerateUtils.generatePassword(loginDto.getPassword(), authAdmin.getSalt());
            if (!password.equals(authAdmin.getPassword())) {
                throw new BizRuntimeException(AuthBizErrorEnum.ADMIN_NO_EXIST);
            }
        }

        // Jwt存储信息
        Map<String, Object> claimsMap = new HashMap<String, Object>();
        claimsMap.put(SecurityConstants.USER_PHONE, authAdmin.getUserPhone());
        claimsMap.put(SecurityConstants.USER_ID, authAdmin.getId());
        Integer expire = CacheKeyEnum.TOKEN_KEY.getTimeout().intValue();
        if (env.equals("dev") && loginDto.getExpire() != null) {
            expire = loginDto.getExpire();
        }
        claimsMap.put(SecurityConstants.EXPIRE_KEY, DateUtils.add(new Date(), expire + DateUtils.SECOND));
        String token = JwtUtils.createToken(claimsMap, SecurityConstants.SECRET);
        String tokenKey = CacheKeyEnum.TOKEN_KEY.getKey(token);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setIcon(authAdmin.getIcon());
        userInfoDTO.setUserEmail(authAdmin.getUserEmail());
        userInfoDTO.setUserName(authAdmin.getUserName());
        userInfoDTO.setUserPhone(authAdmin.getUserPhone());
        userInfoDTO.setAppCode(authApp.getAppCode());
        userInfoDTO.setRoleIds(authAdmin.getRoleIds());
        userInfoDTO.setSecret(authApp.getSecret());
        userInfoDTO.setUserId(authAdmin.getId());
        if (null != authAdmin.getDepartmentId()) {
            AuthDepartment authDepartment = authDepartmentService.getById(authAdmin.getDepartmentId());
            if(authDepartment!=null) {
                userInfoDTO.setDepartName(authDepartment.getName());
            }
        }

        if (null != authAdmin.getPositionId()) {
            AuthAdminPosition authAdminPosition = authAdminPositionService.getById(authAdmin.getPositionId());
            if(authAdminPosition!=null) {
                userInfoDTO.setPositionName(authAdminPosition.getName());
            }
        }

        String userTokenKey = CacheKeyEnum.USER_TOKEN_KEY.getKey(authAdmin.getId());
        List<String> userTokenList = redisService.getCacheObject(userTokenKey);
        if (null != userTokenList) {
            Iterator iterator = userTokenList.iterator();
            while (iterator.hasNext()) {
                String ttoken = String.valueOf(iterator.next());
                String tkey = CacheKeyEnum.TOKEN_KEY.getKey(ttoken);
                if (null == redisService.getCacheObject(tkey)) {
                    log.info("token已过期自动清除：{}",tkey);
                    iterator.remove();
                }
            }
        } else {
            userTokenList = new ArrayList<>();
        }
        //操作10个则需要删除老token
        if (userTokenList.size() > SecurityConstants.LIMIT_LOGIN_USER) {
            String oldToken = userTokenList.get(0);
            userTokenList.remove(0);
            redisService.deleteObject(CacheKeyEnum.TOKEN_KEY.getKey(oldToken));
            log.info("该用户登录在线终端已经超过限制，踢出第1个登录终端：{}",oldToken);
        }

        userTokenList.add(token);
        redisService.setCacheObject(tokenKey, userInfoDTO, Long.valueOf(expire), TimeUnit.SECONDS);
        redisService.setCacheObject(userTokenKey, userTokenList, CacheKeyEnum.USER_TOKEN_KEY.getTimeout(), TimeUnit.SECONDS);

        return token;
    }

    /**
     * 验证app
     *
     * @param loginDto
     * @return
     */
    private AuthApp getApp(XmLoginDto loginDto) {
        AuthApp authApp = authAppService.getAppByCode(loginDto.getAppCode());
        if (null == authApp) {
            //log.error("登录失败："+BizErrorEnum.APP_NO_EXIST.getErrorCode(),JSONObject.toJSONString(loginDto));
            throw new BizRuntimeException(AuthBizErrorEnum.APP_NO_EXIST);
        }

        return authApp;
    }

    /**
     * 获取登录用户信息
     *
     * @param loginDto
     * @return
     */
    private AuthAdmin getAdmin(XmLoginDto loginDto) {
        AuthAdmin authAdmin = authAdminService.getValidAdminByPhone(loginDto.getPhone(), orgId);
        if (null == authAdmin) {
            throw new BizRuntimeException(AuthBizErrorEnum.ADMIN_NO_EXIST);
        }

        return authAdmin;
    }



}

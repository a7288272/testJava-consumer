package cn.xuanma.test.enums;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

/**
 * <p>
 *  缓存key枚举类
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-07
 */
@Getter
public enum CacheKeyEnum {
    //手机验证码分布式锁
    DISTRIBUTED_PHONE_LOGIN("xm-auth:phone:login:%s",30L),
    //生成手机验证码分布式锁
    DISTRIBUTED_PHONE_CODE("xm-auth:phone:code:%s",30L),
    //发送手机验证码分布式锁
    DISTRIBUTED_PHONE_SENDCODE("xm-auth:phone:sendcode:%s",30L),
    IMAGE_PHONE_CODE("xm-auth:image:phone:code:%s",60L),
    SMS_PHONE_CODE("xm-auth:sms:phone:code:%s",120L),

    TOKEN_KEY("xm-auth:jwt:token:%s",86400 * 7L),
    //存储某个用户登录的token
    USER_TOKEN_KEY("xm-auth:jwt:userid:%s",86400 * 7L),
    PASSWORD_COUNT_KEY("xm-auth:password:err:count:%s",7200L),
    PASSWORD_TIMEOUT_KEY("xm-auth:password:timeout:%s",900L),

    //某个应用的权限菜单列表
    PERMIISSION_MENU_KEY("xm-auth:permission:menu:info:%s",3600L),

    ;

    private String keyTemplate;
    private Long timeout;
    private int size;
    CacheKeyEnum(String keyTemplate, Long timeout){
        this.keyTemplate = keyTemplate;
        this.timeout     = timeout;
        this.size = StringUtils.countMatches(keyTemplate, "%s");
    }

    public String getKey(Object... params){
        if(params == null){
            throw new IllegalArgumentException("参数不能为null");
        }

        if(params.length != size){
            throw new IllegalArgumentException("参数数量不一致");
        }

        for(Object param : params){
            if(param == null){
                throw new IllegalArgumentException("参数不能为null，可以为空字符串");
            }
        }

        return String.format(keyTemplate, params);
    }
}

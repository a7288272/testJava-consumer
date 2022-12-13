package cn.xuanma.test.service.impl;

import cn.xuanma.common.constants.DbContants;
import cn.xuanma.test.enums.StatusEnum;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.xuanma.test.bean.entity.AuthApp;
import cn.xuanma.test.mapper.AuthAppMapper;
import cn.xuanma.test.service.AuthAppService;

import java.util.List;

/**
 * <p>
 * 应用表 服务实现类
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
@Service
public class AuthAppServiceImpl extends ServiceImpl<AuthAppMapper, AuthApp> implements AuthAppService {

    @Override
    public AuthApp getAppByCode(String code) {
        Wrapper<AuthApp> queryWrapper = Wrappers.<AuthApp>lambdaQuery()
                .eq(AuthApp::getAppCode, code)
                .eq(AuthApp::getIsDeleted, DbContants.N)
                .eq(AuthApp::getStatus, StatusEnum.ALLOW.getCode());

        return getOne(queryWrapper);
    }

    @Override
    public List<AuthApp> getValidAppByids(List<Integer> idList) {
        Wrapper<AuthApp> queryWrapper = Wrappers.<AuthApp>query()
                .eq("is_deleted", DbContants.N)
                .eq("status", StatusEnum.ALLOW.getCode())
                .in("id",idList)
                .orderByDesc("weight");

        return list(queryWrapper);
    }

    @Override
    public Boolean delete(List<Integer> ids) {
        for (Integer id : ids) {
            baseMapper.delete(id);
        }
        return true;
    }
}

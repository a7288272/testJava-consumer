package cn.xuanma.test.service.impl;

import cn.xuanma.common.constants.DbContants;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.xuanma.test.bean.entity.AuthAdmin;
import cn.xuanma.test.mapper.AuthAdminMapper;
import cn.xuanma.test.service.AuthAdminService;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
@Service
public class AuthAdminServiceImpl extends ServiceImpl<AuthAdminMapper, AuthAdmin> implements AuthAdminService {

    @Override
    public AuthAdmin getValidAdminByPhone(String phone, Integer orgId) {
        Wrapper<AuthAdmin> queryWrapper = Wrappers.<AuthAdmin>lambdaQuery()
                .eq(AuthAdmin::getIsDeleted, DbContants.N)
                .eq(AuthAdmin::getUserPhone, phone)
                .eq(AuthAdmin::getOrganizationId,orgId)
                .eq(AuthAdmin::getBlack, false);
        return getOne(queryWrapper);
    }
    @Override
    public Boolean updateRole(Integer id) {
        return null;
    }
}

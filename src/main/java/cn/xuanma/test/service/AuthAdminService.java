package cn.xuanma.test.service;


import cn.xuanma.test.bean.entity.AuthAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户信息 服务类
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
public interface AuthAdminService extends IService<AuthAdmin> {
    AuthAdmin getValidAdminByPhone(String phone, Integer orgId);
    Boolean updateRole(Integer id);
}

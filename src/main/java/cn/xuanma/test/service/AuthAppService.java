package cn.xuanma.test.service;


import com.baomidou.mybatisplus.extension.service.IService;
import cn.xuanma.test.bean.entity.AuthApp;

import java.util.List;

/**
 * <p>
 * 应用表 服务类
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
public interface AuthAppService extends IService<AuthApp> {
    AuthApp getAppByCode(String code);

    List<AuthApp> getValidAppByids(List<Integer> idList);

    Boolean delete(List<Integer> ids);
}

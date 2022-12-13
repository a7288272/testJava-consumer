package cn.xuanma.test.manager.sso;


import cn.xuanma.test.bean.dto.XmLoginDto;

/**
 * <p>
 * 登录操作接口
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
public interface XmLoginManager {
    String  login(XmLoginDto loginDto);
}

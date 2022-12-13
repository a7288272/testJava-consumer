package cn.xuanma.test.config;

import cn.xuanma.common.constants.SecurityConstants;
import cn.xuanma.common.utils.TokenUtils;
import cn.xuanma.common.vo.UserInfo;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"cn.xuanma.test.feign"})
public class FeignConfiguration implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        UserInfo userInfo = TokenUtils.getCurrentUser();
        if (userInfo != null) {
            setHeader(requestTemplate, SecurityConstants.USER_ID, userInfo.getUserId());
            setHeader(requestTemplate, SecurityConstants.USER_NAME, userInfo.getUserName());
            setHeader(requestTemplate, SecurityConstants.USER_PHONE, userInfo.getUserPhone());
            setHeader(requestTemplate, SecurityConstants.DEPART_NAME, userInfo.getDepartName());
            setHeader(requestTemplate, SecurityConstants.POSITION_NAME, userInfo.getPositionName());
            setHeader(requestTemplate, SecurityConstants.USER_EMAIL, userInfo.getUserEmail());
            setHeader(requestTemplate, SecurityConstants.APP_CODE_KEY, userInfo.getAppCode());
            setHeader(requestTemplate, SecurityConstants.TOKEN_KEY, userInfo.getToken());
        }
    }

    private void setHeader(RequestTemplate requestTemplate, String key, Object value) {
        if (value != null) {
            requestTemplate.header(key, value.toString());
        }
    }

}

package cn.xuanma.test.feign;

import cn.xuanma.common.vo.RetResult;
import cn.xuanma.common.vo.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "xm-auth", contextId = "authPermissionFeign")
public interface AuthPermissionFeign {
    @GetMapping(  "/permisson/getUserInfo")
    RetResult<UserInfo> getUserInfo();
}

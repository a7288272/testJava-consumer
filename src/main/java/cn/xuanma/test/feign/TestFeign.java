package cn.xuanma.test.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author:wangshu'an
 * @date:2022/11/4 14:41
 * @Description:
 */
@FeignClient("test")
public interface TestFeign {
    @RequestMapping(value = "/test")
    String helloWorld();
}

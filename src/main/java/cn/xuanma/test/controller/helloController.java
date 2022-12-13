package cn.xuanma.test.controller;

import cn.xuanma.test.feign.TestFeign;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @Author:wangshu'an
 * @date:2022/9/3 11:37
 * @Description:
 */

@RestController
@Slf4j
public class helloController {
    @Autowired
    TestFeign testFeign;

    @RequestMapping("/hello-consumer")
    public String hello(){
        String result=testFeign.helloWorld();
        return result;
    }


}

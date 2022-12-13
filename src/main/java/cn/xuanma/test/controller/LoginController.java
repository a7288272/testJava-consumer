package cn.xuanma.test.controller;

import cn.xuanma.test.bean.dto.XmLoginDto;
import cn.xuanma.test.manager.sso.XmLoginManager;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:wangshu'an
 * @date:2022/9/19 14:23
 * @Description:
 */
@RestController
@Slf4j
public class LoginController {

    @Autowired
    private XmLoginManager xmLoginManager;


    @ApiOperation("登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(@RequestBody @Validated XmLoginDto loginDto){
        return  xmLoginManager.login(loginDto);
    }
}

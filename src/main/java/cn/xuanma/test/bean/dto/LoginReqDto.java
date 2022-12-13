package cn.xuanma.test.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("登录dto")
public class LoginReqDto {
    @ApiModelProperty("手机号码")
    private String userphone;
    @ApiModelProperty("密码")
    private String password;
}

package cn.xuanma.test.bean.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("jwt输出模型")
public class JwtRespDto {
    @ApiModelProperty("token")
    private String token;
}

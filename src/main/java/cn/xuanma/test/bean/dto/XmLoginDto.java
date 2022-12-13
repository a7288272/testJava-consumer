package cn.xuanma.test.bean.dto;

import cn.xuanma.common.distributed.Distributed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * 轩妈内部账号登录对象
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
@ApiModel(value="轩妈内部账号登录对象", description="轩妈内部账号登录对象")
@Data
public class XmLoginDto implements Distributed {
    @ApiModelProperty("密码")
    @NotNull(message = "密码不能为空")
    private String password;

    @ApiModelProperty("手机号码")
    @NotNull(message = "手机号码不能为空")
    private String phone;

    @ApiModelProperty("appcode")
    @NotNull(message = "appcode不能为空")
    private String appCode;

    @ApiModelProperty("手机验证码")
    private String code;

    @ApiModelProperty("多少秒后过期，只测试环境有效")
    private Integer expire;

    @Override
    public Object[] distributeParams() {
        return new Object[]{phone};
    }
}

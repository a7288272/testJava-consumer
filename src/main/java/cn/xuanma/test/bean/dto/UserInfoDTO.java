package cn.xuanma.test.bean.dto;

import cn.xuanma.test.bean.vo.UserInfoVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * redis存储的用户信息对象
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
@ApiModel(value="redis存储的用户信息对象", description="redis存储的用户信息对象")
@Data
public class UserInfoDTO extends UserInfoVo {

    @ApiModelProperty(value = "角色id")
    private String roleIds;

    @ApiModelProperty(value = "密钥")
    private String secret;

    @ApiModelProperty("appcode")
    private String appCode;

}

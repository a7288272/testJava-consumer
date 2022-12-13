package cn.xuanma.test.bean.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 * 用户信息对象
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
@ApiModel(value="用户信息对象", description="用户信息对象")
@Data
public class UserInfoVo {
    @ApiModelProperty(value = "用户id")
    private Integer userId;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "部门名称")
    private String departName;

    @ApiModelProperty(value = "职位名称")
    private String positionName;
}

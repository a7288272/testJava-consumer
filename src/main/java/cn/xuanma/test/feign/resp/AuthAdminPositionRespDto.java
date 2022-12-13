package cn.xuanma.test.feign.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * <p>
 * 用户职位
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
@Data
@ApiModel(value="AuthAdminPosition对象", description="用户职位")
public class AuthAdminPositionRespDto {

    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "职位姓名")
    private String name;

    @ApiModelProperty(value = "职位备注")
    private String remark;

    @ApiModelProperty(value = "创建人")
    private String creator;

    @ApiModelProperty(value = "记录创建时间")
    private Date gmtCreated;

    @ApiModelProperty(value = "修改人")
    private String modifier;

    @ApiModelProperty(value = "修改时间")
    private Date gmtModified;

}

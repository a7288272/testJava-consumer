package cn.xuanma.test.feign.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
@ApiModel("部门详情Dto")
public class AuthDepartmentRespDto {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "部门代码")
    private String code;

    @ApiModelProperty(value = "部门名称")
    private String name;

    @ApiModelProperty(value = "父id")
    private Integer parentId;

    @ApiModelProperty(value = "父菜单路径：1-13-12")
    private String parentPath;

    @ApiModelProperty(value = "备注")
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

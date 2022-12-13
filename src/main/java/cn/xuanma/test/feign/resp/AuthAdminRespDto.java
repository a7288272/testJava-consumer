package cn.xuanma.test.feign.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@ApiModel("用户信息RespDto")
public class AuthAdminRespDto {
    private static final long serialVersionUID = 1L;

    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "关联的角色id")
    private String roleIds;

    @ApiModelProperty(value = "关联的角色名称")
    private String roleNames;

    @ApiModelProperty(value = "关联的角色id集合")
    private List<Integer> roleIdList;

    @ApiModelProperty(value = "关联部门id")
    private Integer departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

    @ApiModelProperty(value = "职位id")
    private Integer positionId;

    @ApiModelProperty(value = "职位")
    private String positionName;

    @ApiModelProperty(value = "黑名单 false-不是 true-是")
    private Boolean black;

    @ApiModelProperty(value = "钉钉用户id")
    private String dingdingUserId;

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

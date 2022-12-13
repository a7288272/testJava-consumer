package cn.xuanma.test.bean.entity;

import cn.xuanma.common.annotation.TableFieldType;
import cn.xuanma.common.enums.FileType;
import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * <p>
 * 用户信息
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_admin")
@ApiModel(value = "AuthAdmin对象", description = "用户信息")
public class AuthAdmin {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "姓名")
    private String userName;

    @ApiModelProperty(value = "手机号")
    private String userPhone;

    @ApiModelProperty(value = "邮箱")
    private String userEmail;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "头像")
    private String icon;

    @ApiModelProperty(value = "加密盐，用于密码加密")
    private String salt;

    @ApiModelProperty(value = "关联部门id")
    private Integer departmentId;

    @ApiModelProperty(value = "关联的角色id")
    private String roleIds;

    @ApiModelProperty(value = "职位id")
    private Integer positionId;

    @ApiModelProperty(value = "黑名单 false-不是 true-是")
    @TableField("is_black")
    private Boolean black;

    @ApiModelProperty(value = "钉钉用户id")
    private String dingdingUserId;

    @ApiModelProperty(value = "组织id")
    private Integer organizationId;

    @ApiModelProperty(value = "拓展信息")
    private String extJson;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "是否删除，Y是，N否")
    @TableLogic
    @TableField(fill = FieldFill.INSERT)
    @TableFieldType(FileType.Delete)
    private String isDeleted;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    @TableFieldType(FileType.Author)
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @TableFieldType(FileType.DateTime)
    private Date gmtCreated;

    /**
     * 修改人
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @TableFieldType(FileType.Author)
    private String modifier;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @TableFieldType(FileType.DateTime)
    private Date gmtModified;

}

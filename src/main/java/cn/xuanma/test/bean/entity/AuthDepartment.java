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
 * 部门信息表
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_department")
@ApiModel(value = "AuthDepartment对象", description = "部门信息表")
public class AuthDepartment {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    @ApiModelProperty(value = "钉钉id")
    private Long dingdingId;

    @ApiModelProperty(value = "组织id")
    private Integer organizationId;

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

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
 * 用户职位
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_admin_position")
@ApiModel(value = "AuthAdminPosition对象", description = "用户职位")
public class AuthAdminPosition {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "职位姓名")
    private String name;

    @ApiModelProperty(value = "组织id")
    private Integer organizationId;

    @ApiModelProperty(value = "职位备注")
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

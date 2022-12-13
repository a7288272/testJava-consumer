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
 * 应用表
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("auth_app")
@ApiModel(value = "AuthApp对象", description = "应用表")
public class AuthApp {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "简码")
    private String appCode;

    @ApiModelProperty(value = "名称")
    private String appName;

    @ApiModelProperty(value = "应用属性:1-内部;2-外部")
    private Integer type;

    @ApiModelProperty(value = "权重")
    private Integer weight;

    @ApiModelProperty(value = "位置:1左栏2右栏3上栏4左右栏")
    private Integer position;

    @ApiModelProperty(value = "所属类别id")
    private Integer categoryId;

    @ApiModelProperty(value = "组织id")
    private Integer organizationId;

    @ApiModelProperty(value = "密钥")
    private String secret;

    @ApiModelProperty(value = "状态1启用2关闭")
    private Integer status;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "路径")
    private String appUrl;

    @ApiModelProperty(value = "应用nacos服务")
    private String appNacosServer;

    @ApiModelProperty(value = "图标")
    private String icon;

    @ApiModelProperty(value = "是否删除，Y是，N否")
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

package cn.xuanma.test.enums;

import cn.xuanma.common.enums.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 启用状态枚举
 * @author 蒋剑和
 * 2022-08-07
 */
@Getter
@AllArgsConstructor
public enum StatusEnum implements BaseEnum<Integer> {
    /** 启用 */
    ALLOW(1, "启用", "启用"),
    /** 禁用 */
    FORBIDDEN(2, "禁用", "禁用"),
    ;

    /** 枚举值 */
    final Integer code;
    /** 名称 */
    final String name;
    /** 含义 */
    final String remark;

    public static StatusEnum getStatusEnumByCode(Integer code) {
        StatusEnum[] statusEnums = StatusEnum.values();
        for (StatusEnum el : statusEnums) {
            if (el.getCode().compareTo(code) == 0) {
                return el;
            }
        }
        return null;
    }

    public static String getNameByCode(Integer code) {
        StatusEnum statusEnum = getStatusEnumByCode(code);
        if (null != statusEnum) {
            return statusEnum.getRemark();
        } else {
            return "";
        }
    }
}

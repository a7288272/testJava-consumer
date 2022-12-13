package cn.xuanma.test.enums;

import cn.xuanma.common.enums.ErrorEnum;
import lombok.Getter;


/**
 * @description 业务错误枚举类
 * @author jiangjianhe
 * @date 2022-05-05
 */
@Getter
public enum AuthBizErrorEnum implements ErrorEnum {
    /**SUCCESS**/
    SUCCESS("200", "","成功"),

    //每个业务类型固定前缀方式命名 比如：登录 1001 开头  权限 1002 开头
    LOGIN_PASSWORD_ERROR("1001","001","用户名密码不匹配"),
    LOGIN_PASSWORD_EMPTY("1001","002","密码不能为空"),
    LOGIN_LOCK_TIME("1001","003","请等待%s分钟后，再次登录"),
    LOGIN_TOKEN_ILLEGAL("1001","004","无效token"),
    LOGIN_TOKEN_EXPIRE("1001","005","token已过期，请重新刷新token"),
    LOGIN_SMSCODE_ILLEGAL("1001","006","无效手机验证码"),
    LOGIN_SMSCODE_FREQUENT("1001","007","短信验证码已发送，120S内不能重新发送验证码"),
    PHONE_NO_MATCH("1001","008","重置密码手机号与登录用户手机号不匹配"),
    PWD_NO_MATCH("1001","009","密码不一致"),
    LOGIN_FREQUENTLY("1001","010","非正常登录"),

    APP_NO_EXIST("1002","001","app不存在或已经下架"),


    ADMIN_NO_EXIST("1003","001","用户名或者密码错误"),



    DISTRBUTE_IN_HANDLE("2001","001","正在处理中，请勿重复请求！"),
    NO_PERMISSION("401","","无权访问"),

    ORG_TOO_LONG_AGENT_ID("1004","001","钉钉agentId长度不能超过20"),
    ORG_EXISTED_CODE("1004","002","此组织编号已存在"),
    ORG_NOT_EXIST_DATA("1004","003","此组织信息不存在"),
    ORG_HAS_ADMIN("1004","004","此组织下存在用户"),

    APP_CATEGORY_INVALID_POSITION("1005","001","位置字段值不正确"),
    APP_CATEGORY_EXISTED_NAME("1005","002","此应用分类名称已存在"),
    APP_CATEGORY_NOT_EXIST_DATA("1005","003","此应用分类信息不存在"),
    APP_CATEGORY_HAS_APP("1005","004","此应用分类存在应用"),

    MENU_SHOW_CONTROL_NOT_EXIST_DATA("1006","001","此菜单权限控制信息不存在"),
    MENU_SHOW_CONTROL_INVALID_POSITION("1006","002","位置字段值不正确"),
    MENU_SHOW_CONTROL_EXISTED_DATA("1006","003","此菜单权限控制信息已存在"),

    ;

    private String errorCode;
    private String miniErrorCode;
    private String msg;


    AuthBizErrorEnum(String errorCode, String msg) {
        this.errorCode = errorCode;
        this.msg = msg;
        this.miniErrorCode = this.name();
    }

    AuthBizErrorEnum(String errorCode, String miniErrorCode, String msg) {
        this.errorCode = errorCode;
        this.msg       = msg;
        this.miniErrorCode = miniErrorCode;
    }

    public static AuthBizErrorEnum getByCode(String errorCode){
        for(AuthBizErrorEnum en : AuthBizErrorEnum.values()){
            String ecode = en.errorCode + en.miniErrorCode;
            if(ecode.equals(errorCode)){
                return en;
            }
        }

        return null;
    }
}


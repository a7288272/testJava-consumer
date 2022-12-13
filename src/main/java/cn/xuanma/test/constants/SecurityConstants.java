package cn.xuanma.test.constants;

/**
 * <p>
 * 鉴权安全相关常量
 * </p>
 *
 * @author jiangjianhe
 * @since 2022-05-05
 */
public class SecurityConstants {
    public static Integer PASSWORD_LOCK_TIMES      = 3;

    public static Integer PASSWORD_UNLOCK_MINUTES  = 15;

    //限制一个用户登录终端数
    public static Integer LIMIT_LOGIN_USER         = 20;

    public static String USER_NAME                 = "userName";

    public static String USER_PHONE                = "userPhone";

    public static String USER_ID                   = "userId";

    public static String EXPIRE_KEY                = "expire";

    public static String SECRET                    = "Q7IK4QaLGlcwOAVOzap3KnqrYsdGvDBd";
}

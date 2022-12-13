package cn.xuanma.test.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordGenerateUtils {
    public static String generatePassword(String password, String salt) {
        Md5Hash md5Hash = new Md5Hash(password, salt, 3);
        return md5Hash.toString();
    }
}

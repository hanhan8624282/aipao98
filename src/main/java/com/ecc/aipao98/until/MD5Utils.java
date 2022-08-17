package com.ecc.aipao98.until;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * 32位小写md5<br>
     * 27 md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
     */
    public static String MD5Encode(String strSrc) {
        return MD5Encode(strSrc, "");
    }

    /**
     * 27 md5加密产生，产生128位（bit）的mac 28 将128bit Mac转换成16进制代码 29
     * 
     * @param strSrc
     *            30
     * @param key
     *            31
     * @return 32
     */
    public static String MD5Encode(String strSrc, String key) {

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(strSrc.getBytes("UTF-8"));
            StringBuilder result = new StringBuilder(32);
            byte[] temp;
            temp = md5.digest(key.getBytes("UTF-8"));
            for (int i = 0; i < temp.length; i++) {
                result
                    .append(Integer.toHexString((0x000000ff & temp[i]) | 0xffffff00).substring(6));
            }
            return result.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void main(String[] args) throws Exception {

        System.out.println(MD5Encode("236166", "123456"));
        System.out.println(MD5Encode("root" + MD5Encode("236166")));
        System.out.println(MD5Utils.MD5Encode("SDK-SYG-010-00619" + "193847").toUpperCase());
    }

}

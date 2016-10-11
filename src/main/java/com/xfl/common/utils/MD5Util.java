package com.xfl.common.utils;


import java.security.MessageDigest;

/**
 * Created by XFL.
 * time on 2016/5/23 18:52
 * description:MD5加密工具
 */

public class MD5Util {
    /**
     * 私有构造函数.
     */
    private MD5Util() { }
    /**
     * MD5加密.
     */
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    /**
     * 将字节byte转化为字符串String.
     *
     * @param b 字节数组
     * @return 返回字符串
     */
    private  static String byteArrayToHexString(final byte[] b) {
        StringBuilder resultSb = new StringBuilder();
        for (byte aB : b) resultSb.append(byteToHexString(aB));
        return resultSb.toString();
    }

    /**
     * 字节转化为字符串.
     *
     * @param b 字节
     * @return 返回字符串
     */
    private static String byteToHexString(final byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * MD5加密.
     *
     * @param origin      需要加密的内容
     * @param charsetName 编码
     * @return 加密后的密文
     */
    public static synchronized String MD5Encode(final String origin, final String charsetName) {
        String resultString = null;
        try {
            resultString = origin;
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetName == null || "".equals(charsetName)) {
                resultString = byteArrayToHexString(
                        md.digest(resultString.getBytes()));
            } else {
                resultString = byteArrayToHexString(
                        md.digest(resultString.getBytes(charsetName)));
            }

        } catch (Exception exception) {
        }
        return resultString;
    }
}
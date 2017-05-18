package com.xfl.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by XFL.
 * time on 2017/5/17 23:29
 * description:用于文件MD5加密
 */
public class MD5FileUtil {
    public static final long SIZE_15M = 15 * 1024 * 1024;
    private static char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    private static MessageDigest messagedigest = null;

    static {
        try {
            messagedigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsaex) {
            nsaex.printStackTrace();
        }
    }

    /**
     * 取整个文件MD5值
     */
    public static String getFileMD5String(File file) {
        String value = null;
        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            value = getMD5Value(in, 0, file.length());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return value;
    }

    /**
     * 分段取文件MD5值(每段15MB).
     */
    public static String getFileMD5StringBy15M(File file) {

        String value = "";
        StringBuffer buffer = new StringBuffer();
        long beginByte = 0;
        long endByte = 0;
        long remainSize = 0;
        FileInputStream in = null;

        long fileSize = file.length();

        try {
            in = new FileInputStream(file);

            if (fileSize <= SIZE_15M) {

                value = getFileMD5String(file);

            } else {

                while (fileSize > endByte) {

                    endByte += SIZE_15M;

                    if (fileSize > endByte) {

                        value = getMD5Value(in, beginByte, SIZE_15M);

                    } else {

                        value = getMD5Value(in, beginByte, remainSize);

                    }

                    remainSize = fileSize - endByte;
                    beginByte = endByte;
                    buffer.append(value);
                    buffer.append(";");
                }
                value = buffer.toString();
                value = value.substring(0, value.length() - 1);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != in) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return value;
    }

    /**
     * 取MD5值.
     *
     * @param in        文件输入流
     * @param beginByte 开始字节数
     * @param size      大小
     */
    public static String getMD5Value(FileInputStream in, long beginByte, long size) {
        String value = null;
        FileChannel fileChannel = in.getChannel();
        MappedByteBuffer byteBuffer;
        try {
            byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, beginByte, size);
            messagedigest.update(byteBuffer);

            BigInteger bi = new BigInteger(1, messagedigest.digest());
            value = bi.toString(16);
            //超过32位左补0
            int length = value.length();
            if (length < 32) {
                for (int i = 0; i < 32 - length; i++) {
                    value = "0" + value;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return value;
    }

    public static String getMD5String(String s) {
        return getMD5String(s.getBytes());
    }

    public static String getMD5String(byte[] bytes) {
        messagedigest.update(bytes);
        return bufferToHex(messagedigest.digest());
    }

    private static String bufferToHex(byte bytes[]) {
        return bufferToHex(bytes, 0, bytes.length);
    }

    private static String bufferToHex(byte bytes[], int m, int n) {
        StringBuffer stringbuffer = new StringBuffer(2 * n);
        int k = m + n;
        for (int l = m; l < k; l++) {
            appendHexPair(bytes[l], stringbuffer);
        }
        return stringbuffer.toString();
    }

    private static void appendHexPair(byte bt, StringBuffer stringbuffer) {
        char c0 = hexDigits[(bt & 0xf0) >> 4];
        char c1 = hexDigits[bt & 0xf];
        stringbuffer.append(c0);
        stringbuffer.append(c1);
    }

}

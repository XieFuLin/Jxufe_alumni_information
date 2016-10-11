package com.xfl.common.redis;

import java.util.List;

/**
 * Created by XFL.
 * time on 2016/9/29 22:18
 * description:redis操作字符串接口方法
 */
public interface IRedisStrings {
    /**
     * 根据key获取记录.
     * @param key 键值
     * @return 返回值，不存在则返回null
     */
    String get(String key);

    /**
     *根据key获取记录.
     * @param key 键值
     * @return 返回值，不存在则返回null
     */
    byte[] get(byte[] key);

    /**
     * 添加有过期时间的记录.
     * @param key 键值
     * @param seconds 过期时间,以秒为单位
     * @param value 值
     * @return 操作状态,成功返回ok
     */
    String setEx(String key, int seconds, String value);

    /**
     * 添加有过期时间的记录.
     * @param key 键值
     * @param seconds 过期时间,以秒为单位
     * @param value 值
     * @return 操作状态,成功返回ok
     */
    String setEx(byte[] key, int seconds, byte[] value);

    /**
     *添加一条记录，仅当给定的key不存在时才插入.
     * @param key 键值
     * @param value 值
     * @return 状态码，1插入成功且key不存在，0未插入，key存在
     */
    long setnx(String key, String value);

    /**
     *添加记录,如果记录已存在将覆盖原有的value.
     * @param key 键值
     * @param value 值
     * @return 状态码
     */
    String set(String key, String value);

    /**
     * 添加记录,如果记录已存在将覆盖原有的value.
     * @param key 键值
     * @param value 值
     * @return 状态码
     */
    String set(String key, byte[] value);

    /**
     * 添加记录,如果记录已存在将覆盖原有的value.
     * @param key 键值
     * @param value 值
     * @return 状态码
     */
    String set(byte[] key, byte[] value);

    /**
     * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据.
     * 例:String str1="123456789";
     * 对str1操作后setRange(key,4,0000)，str1="123400009";
     * @param key 键值
     * @param offset 指定位置
     * @param value 值
     * @return 更改后value的长度
     */
    long setRange(String key, long offset, String value);

    /**
     * 在指定的key中追加value.
     * @param key 键值
     * @param value 值
     * @return 追加后value的长度
     */
    long append(String key, String value);

    /**
     * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用.
     * @param key 键值
     * @param number 要减去的值
     * @return 减指定值后的值
     */
    long decrBy(String key, long number);

    /**
     * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用.
     * @param key 键值
     * @param number 需要增加的值
     * @return 相加后的值
     */
    long incrBy(String key, long number);

    /**
     * 对指定key对应的value进行截取.
     * @param key 键值
     * @param startOffset 起始位置(包含)
     * @param endOffset 结束位置(包含)
     * @return 截取的值
     */
    String getrange(String key, long startOffset, long endOffset);

    /**
     * 获取并设置指定key对应的value.
     * 获取并设置指定key对应的value
     * @param key 键值
     * @param value 值
     * @return 原始value或null
     */
    String getSet(String key, String value);

    /**
     * 批量获取记录,如果指定的key不存在返回List的对应位置将是null.
     * @param keys 键值
     * @return 值得集合
     */
    List<String> mget(String... keys);

    /**
     * 批量存储记录.
     * @param keysvalues  例:keysvalues="key1","value1","key2","value2";
     * @return 状态码
     */
    String mset(String... keysvalues);

    /**
     * 获取key对应的值的长度.
     * @param key 键值
     * @return value值得长度
     */
    long strlen(String key);

}

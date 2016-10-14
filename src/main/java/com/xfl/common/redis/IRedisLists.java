package com.xfl.common.redis;

import redis.clients.jedis.BinaryClient;

/**
 * Created by XFL.
 * time on 2016/10/14 22:20
 * description:Redis操作List相关接口
 */
public interface IRedisLists {
    /**
     * 获取List的大小.
     * @param key 键值
     * @return List大小
     */
    long llen(String key);

    /**
     * 获取List的大小.
     * @param key 键值
     * @return List大小
     */
    long llen(byte[] key);

    /**
     * 向List中指定位置的添加值,会覆盖原来的值.
     * @param key 键值
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    String lset(byte[] key, int index, byte[] value);

    /**
     * 向List中指定位置的添加值,会覆盖原来的值.
     * @param key 键值
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    String lset(String key, int index, String value);

    /**
     * 在value的相对位置插入记录.
     * @param key 键值
     * @param where 前面插入或后面插入
     * @param pivot 插入的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    long linsert(String key, BinaryClient.LIST_POSITION where, String pivot,
                 String value);

    /**
     * 在value的相对位置插入记录.
     * @param key 键值
     * @param where 前面插入或后面插入
     * @param pivot 插入的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot,
                 byte[] value);
}

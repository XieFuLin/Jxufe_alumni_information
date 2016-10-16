package com.xfl.common.redis;

import redis.clients.jedis.BinaryClient;

import java.util.List;

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
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    long linsert(String key, BinaryClient.LIST_POSITION where, String pivot,
                 String value);

    /**
     * 在value的相对位置插入记录.
     * @param key 键值
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot,
                 byte[] value);

    /**
     * 获取List中指定位置的值.
     * @param key 键值
     * @param index 位置
     * @return 值
     */
    String lindex(String key, int index);

    /**
     * 获取List中指定位置的值.
     * @param key 键值
     * @param index 位置
     * @return 值
     */
    byte[] lindex(byte[] key, int index);

    /**
     * 将List中的第一条记录移出List.
     * @param key 键值
     * @return 移出的记录
     */
    String lpop(String key);

    /**
     * 将List中的第一条记录移出List.
     * @param key 键值
     * @return 移出的记录
     */
    byte[] lpop(byte[] key);

    /**
     * 将List中最后第一条记录移出List.
     * @param key 移出的记录
     * @return 移出的记录
     */
    String rpop(String key);

    /**
     * 向List头部追加记录.
     * @param key 键值
     * @param value 值
     * @return 记录总数
     */
    long lpush(String key, String value);

    /**
     * 向List尾部追加记录.
     * @param key 键值
     * @param value 值
     * @return 记录总数
     */
    long rpush(String key, String value);

    /**
     * 向List头部追加记录.
     * @param key 键值
     * @param value 值
     * @return 记录总数
     */
    long rpush(byte[] key, byte[] value);

    /**
     * 向List中追加记录.
     * @param key 键值
     * @param value 值
     * @return 记录总数
     */
    long lpush(byte[] key, byte[] value);

    /**
     * 获取指定范围的记录，可以做为分页使用.
     * @param key 键值
     * @param start 起始记录
     * @param end 结束记录
     * @return 记录集合
     */
    List<String> lrange(String key, long start, long end);

    /**
     * 获取指定范围的记录，可以做为分页使用.
     * @param key 键值
     * @param start 起始记录
     * @param end 结束记录
     * @return 记录集合
     */
    List<byte[]> lrange(byte[] key, int start, int end);

    /**
     * 删除List中c条记录，被删除的记录值为value.
     * @param key 键值
     * @param c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    long lrem(byte[] key, int c, byte[] value);

    /**
     * 删除List中c条记录，被删除的记录值为value.
     * @param key 键值
     * @param c 要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    long lrem(String key, int c, String value);

    /**
     *  删除其余记录，只保留start与end之间的记录.
     * @param key 键值
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end 结记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    String ltrim(byte[] key, int start, int end);

    /**
     * 删除其余记录，只保留start与end之间的记录.
     * @param key 键值
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end 记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    String ltrim(String key, int start, int end);
}

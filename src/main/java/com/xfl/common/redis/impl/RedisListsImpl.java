package com.xfl.common.redis.impl;

import com.xfl.common.redis.IRedisLists;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.BinaryClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.SafeEncoder;

import java.util.List;

/**
 * Created by XFL.
 * time on 2016/10/15 14:11
 * description:redis操作List相关接口实现
 */
@Repository("redisLists")
public class RedisListsImpl implements IRedisLists {
    /**
     * JedisSentinelPool.
     */
    private JedisSentinelPool jedisSentinelPool;

    /**
     * 注入jedisSentinelPool.
     * @param jedisSentinelPool 连接池
     */
    public RedisListsImpl(JedisSentinelPool jedisSentinelPool) {
        Assert.notNull(jedisSentinelPool, "redis连接池注入失败");
        this.jedisSentinelPool = jedisSentinelPool;
    }
    /**
     * 获取List的大小.
     *
     * @param key 键值
     * @return List大小
     */
    @Override
    public long llen(String key) {
        return llen(SafeEncoder.encode(key));
    }

    /**
     * 获取List的大小.
     *
     * @param key 键值
     * @return List大小
     */
    @Override
    public long llen(byte[] key) {
        Jedis jedis = getJedis();
        return jedis.llen(key);
    }

    /**
     * 向List中指定位置的添加值,会覆盖原来的值.
     *
     * @param key   键值
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    @Override
    public String lset(byte[] key, int index, byte[] value) {
        Jedis jedis = getJedis();
        return jedis.lset(key, index, value);
    }

    /**
     * 向List中指定位置的添加值,会覆盖原来的值.
     *
     * @param key   键值
     * @param index 位置
     * @param value 值
     * @return 状态码
     */
    @Override
    public String lset(String key, int index, String value) {
        return lset(SafeEncoder.encode(key), index,
                SafeEncoder.encode(value));
    }

    /**
     * 在value的相对位置插入记录.
     *
     * @param key   键值
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    @Override
    public long linsert(String key, BinaryClient.LIST_POSITION where, String pivot, String value) {
        return linsert(SafeEncoder.encode(key), where,
                SafeEncoder.encode(pivot), SafeEncoder.encode(value));
    }

    /**
     * 在value的相对位置插入记录.
     *
     * @param key   键值
     * @param where 前面插入或后面插入
     * @param pivot 相对位置的内容
     * @param value 插入的内容
     * @return 记录总数
     */
    @Override
    public long linsert(byte[] key, BinaryClient.LIST_POSITION where, byte[] pivot, byte[] value) {
        Jedis jedis = getJedis();
        return jedis.linsert(key, where, pivot, value);
    }

    /**
     * 获取List中指定位置的值.
     *
     * @param key   键值
     * @param index 位置
     * @return 值
     */
    @Override
    public String lindex(String key, int index) {
        return SafeEncoder.encode(lindex(SafeEncoder.encode(key), index));
    }

    /**
     * 获取List中指定位置的值.
     *
     * @param key   键值
     * @param index 位置
     * @return 值
     */
    @Override
    public byte[] lindex(byte[] key, int index) {
        Jedis jedis = getJedis();
        return jedis.lindex(key, index);
    }

    /**
     * 将List中的第一条记录移出List.
     *
     * @param key 键值
     * @return 移出的记录
     */
    @Override
    public String lpop(String key) {
        return SafeEncoder.encode(lpop(SafeEncoder.encode(key)));
    }

    /**
     * 将List中的第一条记录移出List.
     *
     * @param key 键值
     * @return 移出的记录
     */
    @Override
    public byte[] lpop(byte[] key) {
        Jedis jedis = getJedis();
        return jedis.lpop(key);
    }

    /**
     * 将List中最后第一条记录移出List.
     *
     * @param key 移出的记录
     * @return 移出的记录
     */
    @Override
    public String rpop(String key) {
        Jedis jedis = getJedis();
        return jedis.rpop(key);
    }

    /**
     * 向List头部追加记录.
     *
     * @param key   键值
     * @param value 值
     * @return 记录总数
     */
    @Override
    public long lpush(String key, String value) {
        return lpush(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 向List尾部追加记录.
     *
     * @param key   键值
     * @param value 值
     * @return 记录总数
     */
    @Override
    public long rpush(String key, String value) {
        Jedis jedis = getJedis();
        return jedis.rpush(key, value);
    }

    /**
     * 向List头部追加记录.
     *
     * @param key   键值
     * @param value 值
     * @return 记录总数
     */
    @Override
    public long rpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        return jedis.rpush(key, value);
    }

    /**
     * 向List中追加记录.
     *
     * @param key   键值
     * @param value 值
     * @return 记录总数
     */
    @Override
    public long lpush(byte[] key, byte[] value) {
        Jedis jedis = getJedis();
        return jedis.lpush(key, value);
    }

    /**
     * 获取指定范围的记录，可以做为分页使用.
     *
     * @param key   键值
     * @param start 起始记录
     * @param end   结束记录
     * @return 记录集合
     */
    @Override
    public List<String> lrange(String key, long start, long end) {
        Jedis jedis = getJedis();
        return jedis.lrange(key, start, end);
    }

    /**
     * 获取指定范围的记录，可以做为分页使用.
     *
     * @param key   键值
     * @param start 起始记录
     * @param end   结束记录
     * @return 记录集合
     */
    @Override
    public List<byte[]> lrange(byte[] key, int start, int end) {
        Jedis jedis = getJedis();
        return jedis.lrange(key, start, end);
    }

    /**
     * 删除List中c条记录，被删除的记录值为value.
     *
     * @param key   键值
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    @Override
    public long lrem(byte[] key, int c, byte[] value) {
        Jedis jedis = getJedis();
        return jedis.lrem(key, c, value);
    }

    /**
     * 删除List中c条记录，被删除的记录值为value.
     *
     * @param key   键值
     * @param c     要删除的数量，如果为负数则从List的尾部检查并删除符合的记录
     * @param value 要匹配的值
     * @return 删除后的List中的记录数
     */
    @Override
    public long lrem(String key, int c, String value) {
        return lrem(SafeEncoder.encode(key), c, SafeEncoder.encode(value));
    }

    /**
     * 删除其余记录，只保留start与end之间的记录.
     *
     * @param key   键值
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   结记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    @Override
    public String ltrim(byte[] key, int start, int end) {
        Jedis jedis = getJedis();
        return jedis.ltrim(key, start, end);
    }

    /**
     * 删除其余记录，只保留start与end之间的记录.
     *
     * @param key   键值
     * @param start 记录的开始位置(0表示第一条记录)
     * @param end   结记录的结束位置（如果为-1则表示最后一个，-2，-3以此类推）
     * @return 执行状态码
     */
    @Override
    public String ltrim(String key, int start, int end) {
        return ltrim(SafeEncoder.encode(key), start, end);
    }
    /**
     * 获取Jedis数据源.
     * @return 返回Jedis数据源
     */
    private Jedis getJedis() {
        return jedisSentinelPool.getResource();
    }
}

package com.xfl.common.redis.impl;

import com.xfl.common.redis.IRedisStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.util.SafeEncoder;

import java.util.List;


/**
 * Created by XFL.
 * time on 2016/9/26 22:30
 * description:Redis操作字符串实现类
 */
@Repository("redisStrings")
public class RedisStringsImpl implements IRedisStrings {
    /**
     * JedisSentinelPool.
     */
    private JedisSentinelPool jedisSentinelPool;

    /**
     * 自动注入redis连接池.
     * @param jedisSentinelPool 连接池
     */
    @Autowired
    private RedisStringsImpl(final JedisSentinelPool jedisSentinelPool) {
        Assert.notNull(jedisSentinelPool, "redis连接池注入失败");
        this.jedisSentinelPool = jedisSentinelPool;
    }
    /**
     * 根据key获取记录.
     * @param key 键值
     * @return 返回值，不存在则返回null
     */
    @Override
    public final String get(final String key) {
        Jedis jedis = getJedis();
        return jedis.get(key);
    }

    /**
     *根据key获取记录.
     * @param key 键值
     * @return 返回值，不存在则返回null
     */
    @Override
    public final byte[] get(final byte[] key) {
        Jedis jedis = getJedis();
        return jedis.get(key);
    }

    /**
     * 添加有过期时间的记录.
     * @param key 键值
     * @param seconds 过期时间,以秒为单位
     * @param value 值
     * @return 操作状态,成功返回ok
     */
    @Override
    public final String setEx(final String key, final int seconds, final String value) {
        Jedis jedis = getJedis();
        return jedis.setex(key, seconds, value);
    }

    /**
     * 添加有过期时间的记录.
     * @param key 键值
     * @param seconds 过期时间,以秒为单位
     * @param value 值
     * @return 操作状态,成功返回ok
     */
    @Override
    public final String setEx(final byte[] key, final int seconds, final byte[] value) {
        Jedis jedis = getJedis();
        return jedis.setex(key, seconds, value);
    }

    /**
     * 添加一条记录，仅当给定的key不存在时才插入.
     *
     * @param key   键值
     * @param value 值
     * @return 状态码，1插入成功且key不存在，0未插入，key存在
     */
    @Override
    public final long setnx(final String key, final String value) {
        Jedis jedis = getJedis();
        return jedis.setnx(key, value);
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value.
     *
     * @param key   键值
     * @param value 值
     * @return 状态码
     */
    @Override
    public final String set(final String key, final String value) {
        return set(SafeEncoder.encode(key), SafeEncoder.encode(value));
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value.
     *
     * @param key   键值
     * @param value 值
     * @return 状态码
     */
    @Override
    public final String set(final String key, final byte[] value) {
        return set(SafeEncoder.encode(key), value);
    }

    /**
     * 添加记录,如果记录已存在将覆盖原有的value.
     *
     * @param key   键值
     * @param value 值
     * @return 状态码
     */
    @Override
    public final String set(final byte[] key, final byte[] value) {
        Jedis jedis = getJedis();
        return jedis.set(key, value);
    }

    /**
     * 从指定位置开始插入数据，插入的数据会覆盖指定位置以后的数据.
     * 例:String str1="123456789";
     * 对str1操作后setRange(key,4,0000)，str1="123400009";
     *
     * @param key    键值
     * @param offset 指定位置
     * @param value  值
     * @return 更改后value的长度
     */
    @Override
    public final long setRange(final String key, final long offset, final String value) {
        Jedis jedis = getJedis();
        return jedis.setrange(key, offset, value);
    }

    /**
     * 在指定的key中追加value.
     *
     * @param key   键值
     * @param value 值
     * @return 追加后value的长度
     */
    @Override
    public final long append(final String key, final String value) {
        Jedis jedis = getJedis();
        return jedis.append(key, value);
    }

    /**
     * 将key对应的value减去指定的值，只有value可以转为数字时该方法才可用.
     *
     * @param key    键值
     * @param number 要减去的值
     * @return 减指定值后的值
     */
    @Override
    public final long decrBy(final String key, final long number) {
        Jedis jedis = getJedis();
        return jedis.decrBy(key, number);
    }

    /**
     * 将key对应的value加上指定的值，只有value可以转为数字时该方法才可用.
     *
     * @param key    键值
     * @param number 需要增加的值
     * @return 相加后的值
     */
    @Override
    public final long incrBy(final String key, final long number) {
        Jedis jedis = getJedis();
        return jedis.incrBy(key, number);
    }

    /**
     * 对指定key对应的value进行截取.
     *
     * @param key         键值
     * @param startOffset 起始位置(包含)
     * @param endOffset   结束位置(包含)
     * @return 截取的值
     */
    @Override
    public final String getrange(final String key, final long startOffset, final long endOffset) {
        Jedis jedis = getJedis();
        return jedis.getrange(key, startOffset, endOffset);
    }

    /**
     * 获取并设置指定key对应的value.
     * 获取并设置指定key对应的value
     *
     * @param key   键值
     * @param value 值
     * @return 原始value或null
     */
    @Override
    public final String getSet(final String key, final String value) {
        Jedis jedis = getJedis();
        return jedis.getSet(key, value);
    }

    /**
     * 批量获取记录,如果指定的key不存在返回List的对应位置将是null.
     *
     * @param keys 键值
     * @return 值得集合
     */
    @Override
    public final List<String> mget(final String... keys) {
        Jedis jedis = getJedis();
        return jedis.mget(keys);
    }

    /**
     * 批量存储记录.
     *
     * @param keysvalues 例:keysvalues="key1","value1","key2","value2";
     * @return 状态码
     */
    @Override
    public final String mset(final String... keysvalues) {
        Jedis jedis = getJedis();
        return jedis.mset(keysvalues);
    }

    /**
     * 获取key对应的值的长度.
     *
     * @param key 键值
     * @return value值得长度
     */
    @Override
    public final long strlen(final String key) {
        Jedis jedis = getJedis();
        return jedis.strlen(key);
    }

    /**
     * 获取Jedis数据源.
     * @return 返回Jedis数据源
     */
    private Jedis getJedis() {
        return jedisSentinelPool.getResource();
    }
}

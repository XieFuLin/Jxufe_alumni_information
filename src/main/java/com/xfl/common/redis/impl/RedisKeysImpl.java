package com.xfl.common.redis.impl;

import com.xfl.common.redis.IRedisKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.SortingParams;
import redis.clients.util.SafeEncoder;

import java.util.List;
import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/1 23:16
 * description:管理redis键值的实现类
 */
@Repository("redisKeys")
public class RedisKeysImpl implements IRedisKeys {
    /**
     * 自动注入JedisSentinelPool.
     */
    private final JedisSentinelPool jedisSentinelPool;

    /**
     * 自动注入jedis连接池.
     * @param jedisSentinelPool jedis连接池
     */
    @Autowired
    private RedisKeysImpl(final JedisSentinelPool jedisSentinelPool) {
        Assert.notNull(jedisSentinelPool, "redis连接池注入失败");
        this.jedisSentinelPool = jedisSentinelPool;
    }
    /**
     * 删除所有现有的数据库，而不仅仅是当前选择的一个的键.
     *(Redis默认会创建16个数据库,可以通过select index 命令切换,index为数据库的索引)
     * @return 返回OK
     */
    @Override
    public final String flushAll() {
        Jedis jedis = getJedis();
        return jedis.flushAll();
    }

    /**
     * 更改键的名称.(如果新的键存在,则新的键会被覆盖).
     *
     * @param oldkey 旧键值
     * @param newkey 新键值
     * @return 返回OK
     */
    @Override
    public final String rename(final String oldkey, final String newkey) {
        return rename(SafeEncoder.encode(oldkey),
                SafeEncoder.encode(newkey));
    }

    /**
     * 重命名键，如果新的键不存在,如果新的键存在,则重命名失败返回0.
     *
     * @param oldkey 旧键值
     * @param newkey 新键值
     * @return 返回1表示重命名成功, 0表示新键值存在
     */
    @Override
    public final long renamenx(final String oldkey, final String newkey) {
        Jedis jedis = getJedis();
        return jedis.renamenx(oldkey, newkey);
    }

    /**
     * 更改键的名称.
     *
     * @param oldkey 旧键值
     * @param newkey 新键值
     * @return 返回OK
     */
    @Override
    public final String rename(final byte[] oldkey, final byte[] newkey) {
        Jedis jedis = getJedis();
        return jedis.rename(oldkey, newkey);
    }

    /**
     * 设置过期时间以秒为单位.
     *
     * @param key     键值
     * @param seconds 时间单位为秒
     * @return 返回1表示设置成功, 0表示键不存在或者设置失败
     */
    @Override
    public final long expired(final String key, final int seconds) {
        Jedis jedis = getJedis();
        return jedis.expire(key, seconds);
    }

    /**
     * 指定的键过期时间。在这里，时间是在Unix时间戳格式.
     *
     * @param key       键值
     * @param timestamp 时间
     * @return 返回1表示设置成功, 0表示键不存在或者设置失败
     */
    @Override
    public final long expireAt(final String key, final long timestamp) {
        Jedis jedis = getJedis();
        return jedis.expireAt(key, timestamp);
    }

    /**
     * 获取键到期的剩余时间.
     *
     * @param key 键值
     * @return 返回到期时间以秒为单位,-1表示未设置过期时间
     */
    @Override
    public final long ttl(final String key) {
        Jedis jedis = getJedis();
        return jedis.ttl(key);
    }

    /**
     * 取消对key过期时间的设置.
     *
     * @param key 键值
     * @return 1表示设置成功0表示该键不存在或者该键未设置超时过期
     */
    @Override
    public final long persist(final String key) {
        Jedis jedis = getJedis();
        return jedis.persist(key);
    }

    /**
     * 删除键值.
     *
     * @param keys 键值可以是多个
     * @return 删除的记录个数
     */
    @Override
    public final long del(final String... keys) {
        Jedis jedis = getJedis();
        return jedis.del(keys);
    }

    /**
     * 删除键值.
     *
     * @param keys 键值可以是多个
     * @return 删除的记录个数
     */
    @Override
    public final long del(final byte[]... keys) {
        Jedis jedis = getJedis();
        return jedis.del(keys);
    }

    /**
     * 判断key是否存在.
     *
     * @param key 键值
     * @return true表示存在 false表示不存在
     */
    @Override
    public final boolean exists(final String key) {
        Jedis jedis = getJedis();
        return jedis.exists(key);
    }

    /**
     * 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法.
     *
     * @param key 键值
     * @return 排序后的元素
     */
    @Override
    public final List<String> sort(final String key) {
        Jedis jedis = getJedis();
        return jedis.sort(key);
    }

    /**
     * 对List,Set,SortSet进行排序或limit.
     *
     * @param key    键值
     * @param sortingParams 定义排序类型或limit的起止位置.
     * @return 全部或者部分记录
     */
    @Override
    public final List<String> sort(final String key,
                                   final SortingParams sortingParams) {
        Jedis jedis = getJedis();
        return jedis.sort(key, sortingParams);
    }

    /**
     * 返回指定key存储的类型.
     *
     * @param key 键值
     * @return 类型
     */
    @Override
    public final String type(final String key) {
        Jedis jedis = getJedis();
        return jedis.type(key);
    }

    /**
     * 查找所有匹配给定的模式的键.
     *
     * @param pattern 需要匹配的模式,正则表达式
     * @return 返回匹配成功的键值集合
     */
    @Override
    public final Set<String> keys(final String pattern) {
        Jedis jedis = getJedis();
        return jedis.keys(pattern);
    }
    /**
     * 获取Jedis数据源.
     * @return 返回Jedis数据源
     */
    private Jedis getJedis() {
        return jedisSentinelPool.getResource();
    }
}

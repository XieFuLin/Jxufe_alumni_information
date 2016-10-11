package com.xfl.common.redis.impl;

import com.xfl.common.redis.IRedisHashes;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/3 22:47
 * description:redis操作Hash
 */
@Repository("redisHashes")
public class RedisHashesImpl implements IRedisHashes {
    /**
     * JedisSentinelPool.
     */
    private JedisSentinelPool jedisSentinelPool;

    /**
     * 注入jedisSentinelPool.
     * @param jedisSentinelPool 连接池
     */
    public RedisHashesImpl(JedisSentinelPool jedisSentinelPool) {
        Assert.notNull(jedisSentinelPool, "redis连接池注入失败");
        this.jedisSentinelPool = jedisSentinelPool;
    }
    /**
     * 从hash中删除指定的存储.
     *
     * @param key    键值
     * @param fields 存储的名字，指Map中的key,可以是多个
     * @return 状态码，1成功，0失败
     */
    @Override
    public long hdel(String key, String... fields) {
        Jedis jedis = getJedis();
        return jedis.hdel(key, fields);
    }

    /**
     * 删除指定的值，把存取的整个map删除.
     *
     * @param key 键值
     * @return 状态码，1成功，0失败
     */
    @Override
    public long del(String key) {
        Jedis jedis = getJedis();
        return jedis.del(key);
    }

    /**
     * 测试hash中指定的存储是否存在.
     *
     * @param key   键值
     * @param field Map中存储的键值
     * @return true存在, false不存在
     */
    @Override
    public boolean hexists(String key, String field) {
        Jedis jedis = getJedis();
        return jedis.hexists(key, field);
    }

    /**
     * 返回hash中指定存储位置的值.
     *
     * @param key   键值
     * @param field Map中存储的键值
     * @return 存储对应的值
     */
    @Override
    public String hget(String key, String field) {
        Jedis jedis = getJedis();
        return jedis.hget(key, field);
    }

    /**
     * 返回hash中指定存储位置的值.
     *
     * @param key   键值
     * @param field Map中存储的键值
     * @return 存储对应的值
     */
    @Override
    public byte[] hget(byte[] key, byte[] field) {
        Jedis jedis = getJedis();
        return jedis.hget(key, field);
    }

    /**
     * 以Map的形式返回hash中的存储和值.
     *
     * @param key 键值
     * @return 返回Map中所有的键与值
     */
    @Override
    public Map<String, String> hgetAll(String key) {
        Jedis jedis = getJedis();
        return jedis.hgetAll(key);
    }

    /**
     * 添加一个对应关系,向Map中put一个新的值.
     *
     * @param key   键值
     * @param field Map中对应的key
     * @param value 值
     * @return 状态码 1成功，0失败，field已存在将更新，也返回0
     */
    @Override
    public long hset(String key, String field, String value) {
        Jedis jedis = getJedis();
        return jedis.hset(key, field, value);
    }

    /**
     * 添加一个对应关系,向Map中put一个新的值.
     *
     * @param key   键值
     * @param field Map中对应的key
     * @param value 值
     * @return 状态码 1成功，0失败，field已存在将更新，也返回0
     */
    @Override
    public long hset(String key, String field, byte[] value) {
        Jedis jedis = getJedis();
        return jedis.hset(key.getBytes(), field.getBytes(), value);
    }

    /**
     * 添加对应关系,向Map中put一个新的值，只有在field不存在时才执行.
     *
     * @param key   键值
     * @param field ap中对应的key
     * @param value 值
     * @return 状态码 1成功，0失败field已存
     */
    @Override
    public long hsetnx(String key, String field, String value) {
        Jedis jedis = getJedis();
        return jedis.hsetnx(key, field, value);
    }

    /**
     * 获取hash中value的集合.
     *
     * @param key 键值
     * @return 返回值集合
     */
    @Override
    public List<String> hvals(String key) {
        Jedis jedis = getJedis();
        return jedis.hvals(key);
    }

    /**
     * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型.
     *
     * @param key   键值
     * @param field 存储位置
     * @param value 要增加的值,可以是负数
     * @return 增加指定数字后，存储位置的值
     */
    @Override
    public long hincrby(String key, String field, long value) {
        Jedis jedis = getJedis();
        return jedis.hincrBy(key, field, value);
    }

    /**
     * 返回指定hash中的所有存储名字,类似Map中的keySet方法.
     *
     * @param key 键值
     * @return 返回存储名字的集合
     */
    @Override
    public Set<String> hkeys(String key) {
        Jedis jedis = getJedis();
        return jedis.hkeys(key);
    }

    /**
     * 获取hash中存储的个数，类似Map中size方法.
     *
     * @param key 键值
     * @return 返回存储的个数
     */
    @Override
    public long hlen(String key) {
        Jedis jedis = getJedis();
        return jedis.hlen(key);
    }

    /**
     * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null.
     *
     * @param key    键值
     * @param fields 存储位置
     * @return 值集合
     */
    @Override
    public List<String> hmget(String key, String... fields) {
        Jedis sjedis = getJedis();
        return sjedis.hmget(key, fields);
    }

    /**
     * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null.
     *
     * @param key    键值
     * @param fields 存储位置
     * @return 值集合
     */
    @Override
    public List<byte[]> hmget(byte[] key, byte[]... fields) {
        Jedis jedis = getJedis();
        return jedis.hmget(key, fields);
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖.
     *
     * @param key 键值
     * @param map 对应关系
     * @return 添加的结果ok表示成功, 其余失败
     */
    @Override
    public String hmset(String key, Map<String, String> map) {
        Jedis jedis = getJedis();
        return jedis.hmset(key, map);
    }

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖.
     *
     * @param key 键值
     * @param map 对应关系
     * @return 添加的结果ok表示成功, 其余失败
     */
    @Override
    public String hmset(byte[] key, Map<byte[], byte[]> map) {
        Jedis jedis = getJedis();
        return jedis.hmset(key, map);
    }
    /**
     * 获取Jedis数据源.
     * @return 返回Jedis数据源
     */
    private Jedis getJedis() {
        return jedisSentinelPool.getResource();
    }
}

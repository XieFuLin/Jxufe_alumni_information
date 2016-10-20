package com.xfl.common.redis.impl;

import com.xfl.common.redis.IRedisSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/17 22:38
 * description:redis操作set相关接口实现
 */
@Repository("redisSets")
public class RedisSetsImpl implements IRedisSets {
    /**
     * JedisSentinelPool.
     */
    private JedisSentinelPool jedisSentinelPool;

    /**
     * 注入jedisSentinelPool.
     *
     * @param jedisSentinelPool 连接池
     */
    @Autowired
    public RedisSetsImpl(JedisSentinelPool jedisSentinelPool) {
        Assert.notNull(jedisSentinelPool, "redis连接池注入失败");
        this.jedisSentinelPool = jedisSentinelPool;
    }

    /**
     * 向Set添加多条记录，如果members已存在返回0,否则返回1.
     *
     * @param key     键值
     * @param members 需要添加的记录
     * @return 操作码, 0或1
     */
    @Override
    public long sadd(String key, String... members) {
        Jedis jedis = getJedis();
        return jedis.sadd(key, members);
    }

    /**
     * 向Set添加多条记录，如果members已存在返回0,否则返回1.
     *
     * @param key     键值
     * @param members 需要添加的记录
     * @return 操作码, 0或1
     */
    @Override
    public long sadd(byte[] key, byte[]... members) {
        Jedis jedis = getJedis();
        return jedis.sadd(key, members);
    }

    /**
     * 获取给定key中元素个数.
     *
     * @param key 键值
     * @return 返回key中元素个数
     */
    @Override
    public long scard(String key) {
        Jedis jedis = getJedis();
        return jedis.scard(key);
    }

    /**
     * 返回从第一组和所有的给定集合之间的差异的成员.
     *
     * @param keys 需要比对的键值
     * @return 返回从第一组和所有的给定集合之间的差异的成员
     */
    @Override
    public Set<String> sdiff(String... keys) {
        Jedis jedis = getJedis();
        return jedis.sdiff(keys);
    }

    /**
     * 这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖.
     *
     * @param dstkey 新结果集的key
     * @param keys   比较的集合
     * @return 新集合中的记录数
     */
    @Override
    public long sdiffstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        return jedis.sdiffstore(dstkey, keys);
    }

    /**
     * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set.
     *
     * @param keys 需要比较的键值
     * @return 交集成员的集合
     */
    @Override
    public Set<String> sinter(String... keys) {
        Jedis jedis = getJedis();
        return jedis.sinter(keys);
    }

    /**
     * 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖.
     *
     * @param dstkey 新结果集的key
     * @param keys   比较的集合
     * @return 新集合中的记录数
     */
    @Override
    public long sinterstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        return jedis.sinterstore(dstkey, keys);
    }

    /**
     * 确定一个给定的值是否存在.
     *
     * @param key    键值
     * @param member 要判断的值
     * @return 存在返回1，不存在返回0
     */
    @Override
    public boolean sismember(String key, String member) {
        Jedis jedis = getJedis();
        return jedis.sismember(key, member);
    }

    /**
     * 返回集合中的所有成员.
     *
     * @param key 键值
     * @return 成员集合
     */
    @Override
    public Set<String> smembers(String key) {
        Jedis jedis = getJedis();
        return jedis.smembers(key);
    }

    /**
     * 返回集合中的所有成员.
     *
     * @param key 键值
     * @return 成员集合
     */
    @Override
    public Set<byte[]> smembers(byte[] key) {
        Jedis jedis = getJedis();
        return jedis.smembers(key);
    }

    /**
     * 将成员从原集合移出放入目标集合,如果原集合不存在或不包含指定成员，不进行任何操作，返回0.
     * 否则该成员从原集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在原集合进行删除
     *
     * @param srckey 原集合
     * @param dstkey 目标集合
     * @param member 原集合中的成员
     * @return 状态码，1成功，0失败
     */
    @Override
    public long smove(String srckey, String dstkey, String member) {
        Jedis jedis = getJedis();
        return jedis.smove(srckey, dstkey, member);
    }

    /**
     * 从集合中随机删除成员.
     *
     * @param key 键值
     * @return 被移除的随机元素。 当集合不存在或是空集时，返回 nil
     */
    @Override
    public String spop(String key) {
        Jedis jedis = getJedis();
        return jedis.spop(key);
    }

    /**
     * 从集合中删除指定成员.
     *
     * @param key    键值
     * @param member 要删除的成员
     * @return 状态码，成功返回1，成员不存在返回0
     */
    @Override
    public long srem(String key, String member) {
        Jedis jedis = getJedis();
        return jedis.srem(key, member);
    }

    /**
     * 合并多个集合并返回合并后的结果，合并后的结果集合并不保存.
     *
     * @param keys 需要合并的集合键值
     * @return 合并后的结果集合
     */
    @Override
    public Set<String> sunion(String... keys) {
        Jedis jedis = getJedis();
        return jedis.sunion(keys);
    }

    /**
     * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖.
     *
     * @param dstkey 新集合的key
     * @param keys   要合并的集合
     * @return 新集合中的元素个数
     */
    @Override
    public long sunionstore(String dstkey, String... keys) {
        Jedis jedis = getJedis();
        return jedis.sunionstore(dstkey, keys);
    }

    /**
     * 获取Jedis数据源.
     *
     * @return 返回Jedis数据源
     */
    private Jedis getJedis() {
        return jedisSentinelPool.getResource();
    }
}

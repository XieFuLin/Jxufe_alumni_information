package com.xfl.common.redis.impl;

import com.xfl.common.redis.IRedisSortedSets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.Map;
import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/21 20:17
 * description:redis操作SortedSets相关接口实现
 */
@Repository("redisSortedSets")
public class RedisSortedSetsImpl implements IRedisSortedSets {
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
    public RedisSortedSetsImpl(JedisSentinelPool jedisSentinelPool) {
        Assert.notNull(jedisSentinelPool, "redis连接池注入失败");
        this.jedisSentinelPool = jedisSentinelPool;
    }

    /**
     * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重.
     *
     * @param key    键值
     * @param score  权重
     * @param member 要加入的值
     * @return 状态码 1成功，0已存在member的值
     */
    @Override
    public long zadd(String key, double score, String member) {
        Jedis jedis = getJedis();
        return jedis.zadd(key, score, member);
    }

    /**
     * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重.
     *
     * @param key          键值
     * @param scoreMembers 权重与要加入的值
     * @return 状态码 1成功，0已存在member的值
     */
    @Override
    public long zadd(String key, Map<String, Double> scoreMembers) {
        Jedis jedis = getJedis();
        return jedis.zadd(key, scoreMembers);
    }

    /**
     * 获取集合中元素的数量.
     *
     * @param key 键值
     * @return 返回元素的数量
     */
    @Override
    public long zcard(String key) {
        Jedis jedis = getJedis();
        return jedis.zcard(key);
    }

    /**
     * 获取指定权重区间内集合的数量.
     *
     * @param key 键值
     * @param min 最小排序位置
     * @param max 最大排序位置
     * @return 返回符合条件的集合数量
     */
    @Override
    public long zcount(String key, double min, double max) {
        Jedis jedis = getJedis();
        return jedis.zcount(key, min, max);
    }

    /**
     * 获得set的长度.
     *
     * @param key 键值
     * @return 集合中的数量即长度
     */
    @Override
    public long zlength(String key) {
        long len;
        Set<String> set = zrange(key, 0, -1);
        len = set.size();
        return len;
    }

    /**
     * 权重增加给定值，如果给定的member已存在.
     *
     * @param key    键值
     * @param score  权重
     * @param member 要加入的值
     * @return 增加后的权重
     */
    @Override
    public double zincrby(String key, double score, String member) {
        Jedis jedis = getJedis();
        return jedis.zincrby(key, score, member);
    }

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素.
     *
     * @param key   键值
     * @param start 开始位置(包含)
     * @param end   结束位置(包含)
     * @return 返回符合条件的集合
     */
    @Override
    public Set<String> zrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        return jedis.zrange(key, start, end);
    }

    /**
     * 返回指定权重区间的元素集合.
     *
     * @param key 键值
     * @param min 上限权重
     * @param max 下限权重
     * @return 返回符合条件的集合
     */
    @Override
    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = getJedis();
        return jedis.zrangeByScore(key, min, max);
    }

    /**
     * 获取指定值在集合中的位置，集合排序从低到高.
     *
     * @param key    键值
     * @param member 指定的值
     * @return 在集合中的位置
     */
    @Override
    public long zrank(String key, String member) {
        Jedis jedis = getJedis();
        return jedis.zrank(key, member);
    }

    /**
     * 获取指定值在集合中的位置，集合排序从高到低.
     *
     * @param key    键值
     * @param member 指定的值
     * @return 在集合中的位置
     */
    @Override
    public long zrevrank(String key, String member) {
        Jedis jedis = getJedis();
        return jedis.zrevrank(key, member);
    }

    /**
     * 从集合中删除成员.
     *
     * @param key    键值
     * @param member 要删除的成员
     * @return 返回1成功
     */
    @Override
    public long zrem(String key, String member) {
        Jedis jedis = getJedis();
        return jedis.zrem(key, member);
    }

    /**
     * 删除某个集合.
     *
     * @param key 需要删除的集合键值
     * @return 状态码1删除成功 0失败
     */
    @Override
    public long del(String key) {
        Jedis jedis = getJedis();
        return jedis.del(key);
    }

    /**
     * 删除给定位置区间的元素.
     *
     * @param key   键值
     * @param start 开始区间，从0开始(包含)
     * @param end   结束区间,-1为最后一个元素(包含)
     * @return 删除的数量
     */
    @Override
    public long zremrangeByRank(String key, int start, int end) {
        Jedis jedis = getJedis();
        return jedis.zremrangeByRank(key, start, end);
    }

    /**
     * 删除给定权重区间的元素.
     *
     * @param key 键值
     * @param min 下限权重(包含)
     * @param max 上限权重(包含)
     * @return 删除的数量
     */
    @Override
    public long zremrangeByScore(String key, double min, double max) {
        Jedis jedis = getJedis();
        return jedis.zremrangeByScore(key, min, max);
    }

    /**
     * 获取给定区间的元素，原始按照权重由高到低排序.
     *
     * @param key   键值
     * @param start 起始位置
     * @param end   结束位置
     * @return 符合条件的集合
     */
    @Override
    public Set<String> zrevrange(String key, int start, int end) {
        Jedis jedis = getJedis();
        return jedis.zrevrange(key, start, end);
    }

    /**
     * 获取给定值在集合中的权重.
     *
     * @param key    键值
     * @param member 需要获取权重的值
     * @return 返回该值的权重
     */
    @Override
    public double zscore(String key, String member) {
        Jedis jedis = getJedis();
        Double score = jedis.zscore(key, member);
        if (score != null) {
            return score;
        } else {
            return 0;
        }
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

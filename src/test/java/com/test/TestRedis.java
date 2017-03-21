package com.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

public class TestRedis {
    private static JedisSentinelPool jedisSentinelPool = null;

    /**
     *  初始化Redis连接池.
     */
    static {
        JedisPoolConfig config = new JedisPoolConfig();
        //设置最大连接总数
        config.setMaxTotal(300);
        //设置最大空闲数
        config.setMaxIdle(50);
        //设置最小空闲数
        config.setMinIdle(8);
        config.setMaxWaitMillis(10000);
        //在获取连接的时候检查有效性, 默认false
        config.setTestOnBorrow(true);
        //在空闲时检查有效性, 默认false
        config.setTestOnReturn(true);
        //是否启用pool的jmx管理功能, 默认true
        config.setJmxEnabled(true);
        //Idle时进行连接扫描
        config.setTestWhileIdle(true);
        //是否启用后进先出, 默认true
        config.setLifo(true);
        //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
        config.setTimeBetweenEvictionRunsMillis(30000);
        //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
        config.setNumTestsPerEvictionRun(10);
        //表示一个对象至少停留在idle状态的最短时间，然后才能被idle object evitor扫描并驱逐；这一项只有在timeBetweenEvictionRunsMillis大于0时才有意义
        config.setMinEvictableIdleTimeMillis(60000);
        //连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
        config.setBlockWhenExhausted(true);
        //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)
        config.setSoftMinEvictableIdleTimeMillis(1800000);
        Set<String> set = new HashSet<>();
        //连接地址以及端口号,有多个就一次增加
        set.add("192.168.1.101:26379");
        jedisSentinelPool = new JedisSentinelPool("mymaster", set, config);
    }

    /**
     * 获取Jedis实例
     *
     * @return 返回Jedis实例
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisSentinelPool != null) {
                return jedisSentinelPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 释放资源.
     *
     * @param jedis jedis
     */
    public static void releaseResource(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
            jedisSentinelPool.destroy();
        }
    }

    public static void main(String[] args) {
        Jedis jedis = getJedis();
        jedis.hgetAll("hash");
        releaseResource(jedis);
    }
}

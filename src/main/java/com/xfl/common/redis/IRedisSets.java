package com.xfl.common.redis;

import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/17 14:15
 * description:redis操作Set相关接口
 */
public interface IRedisSets {
    /**
     * 向Set添加多条记录，如果members已存在返回0,否则返回1.
     *
     * @param key     键值
     * @param members 需要添加的记录
     * @return 操作码, 0或1
     */
    long sadd(String key, String... members);

    /**
     * 向Set添加多条记录，如果members已存在返回0,否则返回1.
     *
     * @param key     键值
     * @param members 需要添加的记录
     * @return 操作码, 0或1
     */
    long sadd(byte[] key, byte[]... members);

    /**
     * 获取给定key中元素个数.
     *
     * @param key 键值
     * @return 返回key中元素个数
     */
    long scard(String key);

    /**
     * 返回从第一组和所有的给定集合之间的差异的成员.
     *
     * @param keys 需要比对的键值
     * @return 返回从第一组和所有的给定集合之间的差异的成员
     */
    Set<String> sdiff(String... keys);

    /**
     * 这个命令等于sdiff,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖.
     *
     * @param dstkey 新结果集的key
     * @param keys   比较的集合
     * @return 新集合中的记录数
     */
    long sdiffstore(String dstkey, String... keys);

    /**
     * 返回给定集合交集的成员,如果其中一个集合为不存在或为空，则返回空Set.
     *
     * @param keys 需要比较的键值
     * @return 交集成员的集合
     */
    Set<String> sinter(String... keys);

    /**
     * 这个命令等于sinter,但返回的不是结果集,而是将结果集存储在新的集合中，如果目标已存在，则覆盖.
     *
     * @param dstkey 新结果集的key
     * @param keys   比较的集合
     * @return 新集合中的记录数
     */
    long sinterstore(String dstkey, String... keys);

    /**
     * 确定一个给定的值是否存在.
     *
     * @param key    键值
     * @param member 要判断的值
     * @return 存在返回1，不存在返回0
     */
    boolean sismember(String key, String member);

    /**
     * 返回集合中的所有成员.
     *
     * @param key 键值
     * @return 成员集合
     */
    Set<String> smembers(String key);

    /**
     * 返回集合中的所有成员.
     *
     * @param key 键值
     * @return 成员集合
     */
    Set<byte[]> smembers(byte[] key);

    /**
     * 将成员从原集合移出放入目标集合,如果原集合不存在或不包含指定成员，不进行任何操作，返回0.
     * 否则该成员从原集合上删除，并添加到目标集合，如果目标集合中成员已存在，则只在原集合进行删除
     *
     * @param srckey 原集合
     * @param dstkey 目标集合
     * @param member 原集合中的成员
     * @return 状态码，1成功，0失败
     */
    long smove(String srckey, String dstkey, String member);

    /**
     * 从集合中随机删除成员.
     *
     * @param key 键值
     * @return 被移除的随机元素。 当集合不存在或是空集时，返回 nil
     */
    String spop(String key);

    /**
     * 从集合中删除指定成员.
     *
     * @param key    键值
     * @param member 要删除的成员
     * @return 状态码，成功返回1，成员不存在返回0
     */
    long srem(String key, String member);

    /**
     * 合并多个集合并返回合并后的结果，合并后的结果集合并不保存.
     *
     * @param keys 需要合并的集合键值
     * @return 合并后的结果集合
     */
    Set<String> sunion(String... keys);

    /**
     * 合并多个集合并将合并后的结果集保存在指定的新集合中，如果新集合已经存在则覆盖.
     *
     * @param dstkey 新集合的key
     * @param keys   要合并的集合
     * @return 新集合中的元素个数
     */
    long sunionstore(String dstkey, String... keys);
}

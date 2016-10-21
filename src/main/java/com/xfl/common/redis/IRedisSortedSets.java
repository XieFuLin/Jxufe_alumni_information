package com.xfl.common.redis;

import java.util.Map;
import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/21 18:04
 * description:redis操作sortedSets.
 * <p>
 * Sorted Sets中的每个成员都分配了一个分数值(score)，
 * 它用于在Sorted Sets中进行成员排序，从最小值到最大值。
 * Sorted Sets中所有的成员都是唯一的，
 * 其分数(score)是可以重复的，即是说一个分数可能会对应多个值。
 */
public interface IRedisSortedSets {
    /**
     * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重.
     *
     * @param key    键值
     * @param score  权重
     * @param member 要加入的值
     * @return 状态码 1成功，0已存在member的值
     */
    long zadd(String key, double score, String member);

    /**
     * 向集合中增加一条记录,如果这个值已存在，这个值对应的权重将被置为新的权重.
     *
     * @param key          键值
     * @param scoreMembers 权重与要加入的值
     * @return 状态码 1成功，0已存在member的值
     */
    long zadd(String key, Map<String, Double> scoreMembers);

    /**
     * 获取集合中元素的数量.
     *
     * @param key 键值
     * @return 返回元素的数量
     */
    long zcard(String key);

    /**
     * 获取指定权重区间内集合的数量.
     *
     * @param key 键值
     * @param min 最小排序位置
     * @param max 最大排序位置
     * @return 返回符合条件的集合数量
     */
    long zcount(String key, double min, double max);

    /**
     * 获得set的长度.
     *
     * @param key 键值
     * @return 集合中的数量即长度
     */
    long zlength(String key);

    /**
     * 权重增加给定值，如果给定的member已存在.
     *
     * @param key    键值
     * @param score  权重
     * @param member 要加入的值
     * @return 增加后的权重
     */
    double zincrby(String key, double score, String member);

    /**
     * 返回指定位置的集合元素,0为第一个元素，-1为最后一个元素.
     *
     * @param key   键值
     * @param start 开始位置(包含)
     * @param end   结束位置(包含)
     * @return 返回符合条件的集合
     */
    Set<String> zrange(String key, int start, int end);

    /**
     * 返回指定权重区间的元素集合.
     *
     * @param key 键值
     * @param min 上限权重
     * @param max 下限权重
     * @return 返回符合条件的集合
     */
    Set<String> zrangeByScore(String key, double min, double max);

    /**
     * 获取指定值在集合中的位置，集合排序从低到高.
     *
     * @param key    键值
     * @param member 指定的值
     * @return 在集合中的位置
     */
    long zrank(String key, String member);

    /**
     * 获取指定值在集合中的位置，集合排序从高到低.
     *
     * @param key    键值
     * @param member 指定的值
     * @return 在集合中的位置
     */
    long zrevrank(String key, String member);

    /**
     * 从集合中删除成员.
     *
     * @param key    键值
     * @param member 要删除的成员
     * @return 返回1成功
     */
    long zrem(String key, String member);

    /**
     * 删除某个集合.
     *
     * @param key 需要删除的集合键值
     * @return 状态码1删除成功 0失败
     */
    long del(String key);

    /**
     * 删除给定位置区间的元素.
     *
     * @param key   键值
     * @param start 开始区间，从0开始(包含)
     * @param end   结束区间,-1为最后一个元素(包含)
     * @return 删除的数量
     */
    long zremrangeByRank(String key, int start, int end);

    /**
     * 删除给定权重区间的元素.
     *
     * @param key 键值
     * @param min 下限权重(包含)
     * @param max 上限权重(包含)
     * @return 删除的数量
     */
    long zremrangeByScore(String key, double min, double max);

    /**
     * 获取给定区间的元素，原始按照权重由高到低排序.
     *
     * @param key   键值
     * @param start 起始位置
     * @param end   结束位置
     * @return 符合条件的集合
     */
    Set<String> zrevrange(String key, int start, int end);

    /**
     * 获取给定值在集合中的权重.
     *
     * @param key    键值
     * @param member 需要获取权重的值
     * @return 返回该值的权重
     */
    double zscore(String key, String member);
}

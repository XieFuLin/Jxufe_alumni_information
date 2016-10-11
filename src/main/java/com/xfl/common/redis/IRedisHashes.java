package com.xfl.common.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by XFL.
 * time on 2016/10/3 22:46
 * description:Redis操作Hash相关接口方法
 */
public interface IRedisHashes {
    /**
     * 从hash中删除指定的存储.
     * @param key 键值
     * @param fields 存储的名字，指Map中的key,可以是多个
     * @return 状态码，1成功，0失败
     */
    long hdel(String key, String... fields);

    /**
     * 删除指定的值，把存取的整个map删除.
     * @param key 键值
     * @return 状态码，1成功，0失败
     */
    long del(String key);

    /**
     * 测试hash中指定的存储是否存在.
     * @param key 键值
     * @param field Map中存储的键值
     * @return true存在,false不存在
     */
    boolean hexists(String key, String field);

    /**
     * 返回hash中指定存储位置的值.
     * @param key 键值
     * @param field Map中存储的键值
     * @return 存储对应的值
     */
    String hget(String key, String field);

    /**
     * 返回hash中指定存储位置的值.
     * @param key 键值
     * @param field Map中存储的键值
     * @return 存储对应的值
     */
    byte[] hget(byte[] key, byte[] field);

    /**
     * 以Map的形式返回hash中的存储和值.
     * @param key 键值
     * @return 返回Map中所有的键与值
     */
    Map<String, String> hgetAll(String key);

    /**
     * 添加一个对应关系,向Map中put一个新的值.
     * @param key 键值
     * @param field Map中对应的key
     * @param value 值
     * @return 状态码 1成功，0失败，field已存在将更新，也返回0
     */
    long hset(String key, String field, String value);
    /**
     * 添加一个对应关系,向Map中put一个新的值.
     * @param key 键值
     * @param field Map中对应的key
     * @param value 值
     * @return 状态码 1成功，0失败，field已存在将更新，也返回0
     */
    long hset(String key, String field, byte[] value);

    /**
     * 添加对应关系,向Map中put一个新的值，只有在field不存在时才执行.
     * @param key 键值
     * @param field ap中对应的key
     * @param value 值
     * @return 状态码 1成功，0失败field已存
     */
    long hsetnx(String key, String field, String value);

    /**
     * 获取hash中value的集合.
     * @param key 键值
     * @return 返回值集合
     */
    List<String> hvals(String key);

    /**
     * 在指定的存储位置加上指定的数字，存储位置的值必须可转为数字类型.
     * @param key 键值
     * @param field 存储位置
     * @param value 要增加的值,可以是负数
     * @return 增加指定数字后，存储位置的值
     */
    long hincrby(String key, String field, long value);

    /**
     * 返回指定hash中的所有存储名字,类似Map中的keySet方法.
     * @param key 键值
     * @return 返回存储名字的集合
     */
    Set<String> hkeys(String key);

    /**
     * 获取hash中存储的个数，类似Map中size方法.
     * @param key 键值
     * @return 返回存储的个数
     */
    long hlen(String key);

    /**
     * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null.
     * @param key 键值
     * @param fields 存储位置
     * @return 值集合
     */
    List<String> hmget(String key, String... fields);

    /**
     * 根据多个key，获取对应的value，返回List,如果指定的key不存在,List对应位置为null.
     * @param key 键值
     * @param fields 存储位置
     * @return 值集合
     */
    List<byte[]> hmget(byte[] key, byte[]... fields);

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖.
     * @param key 键值
     * @param map 对应关系
     * @return 添加的结果ok表示成功,其余失败
     */
    String hmset(String key, Map<String, String> map);

    /**
     * 添加对应关系，如果对应关系已存在，则覆盖.
     * @param key 键值
     * @param map 对应关系
     * @return 添加的结果ok表示成功,其余失败
     */
    String hmset(byte[] key, Map<byte[], byte[]> map);
}

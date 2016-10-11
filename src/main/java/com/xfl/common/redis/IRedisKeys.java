package com.xfl.common.redis;

import redis.clients.jedis.SortingParams;

import java.util.List;
import java.util.Set;

/**
 * Created by XFL
 * time on 2016/10/1 23:07
 * description:管理Redis键值的接口方法
 */
public interface IRedisKeys {
    /**
     * 删除所有现有的数据库，而不仅仅是当前选择的一个的键.
     * @return 返回OK
     */
    String flushAll();

    /**
     * 更改键的名称.
     * @param oldkey 旧键值
     * @param newkey 新键值
     * @return 返回OK
     */
    String rename(String oldkey, String newkey);

    /**
     * 重命名键，如果新的键不存在.
     * @param oldkey 旧键值
     * @param newkey 新键值
     * @return 返回1表示重命名成功,0表示新键值存在
     */
    long renamenx(String oldkey, String newkey);

    /**
     * 更改键的名称.
     * @param oldkey 旧键值
     * @param newkey 新键值
     * @return 返回OK
     */
    String rename(byte[] oldkey, byte[] newkey);

    /**
     *  设置过期时间以秒为单位.
     * @param key 键值
     * @param seconds 时间单位为秒
     * @return 返回1表示设置成功,0表示键不存在或者设置失败
     */
    long expired(String key, int seconds);

    /**
     * 指定的键过期时间。在这里，时间是在Unix时间戳格式.
     * @param key 键值
     * @param timestamp 时间
     * @return 返回1表示设置成功,0表示键不存在或者设置失败
     */
    long expireAt(String key, long timestamp);

    /**
     * 获取键到期的剩余时间.
     * @param key 键值
     * @return 返回到期时间以秒为单位,-1表示未设置过期时间
     */
    long ttl(String key);

    /**
     * 取消对key过期时间的设置.
     * @param key 键值
     * @return 1表示设置成功0表示该键不存在或者该键未设置超时过期
     */
    long persist(String key);

    /**
     * 删除键值.
     * @param keys 键值可以是多个
     * @return 删除的记录个数
     */
    long del(String... keys);

    /**
     * 删除键值.
     * @param keys 键值可以是多个
     * @return 删除的记录个数
     */
    long del(byte[]... keys);

    /**
     * 判断key是否存在.
     * @param key 键值
     * @return true表示存在 false表示不存在
     */
    boolean exists(String key);

    /**
     * 对List,Set,SortSet进行排序,如果集合数据较大应避免使用这个方法.
     * @param key 键值
     * @return 排序后的元素
     */
    List<String> sort(String key);

    /**
     * 对List,Set,SortSet进行排序或limit.
     * @param key 键值
     * @param sortingParams 定义排序类型或limit的起止位置.
     * @return 全部或者部分记录
     */
    List<String> sort(String key, SortingParams sortingParams);

    /**
     * 返回指定key存储的类型.
     * @param key 键值
     * @return 类型
     */
    String type(String key);

    /**
     * 查找所有匹配给定的模式的键.
     * @param pattern 需要匹配的模式,正则表达式
     * @return 返回匹配成功的键值集合
     */
    Set<String> keys(String pattern);
}

package com.xfl.common.repository;

import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Map;

/**
 * Created by XFL.
 * time on 2016/5/20 18:21
 * description:数据库操作接口方法
 */
public interface ICommonRepository {
    /**
     * 查询单个对象.
     *
     * @param statement 查询语句
     * @param <T>       对象泛型
     * @return 单个对象查询结果
     */
    <T> T selectOne(String statement);

    /**
     * 根据查询条件查询单个对象.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param <T>       对象泛型
     * @return 单个对象查询结果
     */
    <T> T selectOne(String statement, Object parameter);

    /**
     * 查询集合.
     *
     * @param statement 查询语句
     * @param <E>       集合泛型
     * @return 符合条件的集合
     */
    <E> List<E> selectList(String statement);

    /**
     * 查询集合.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param <E>       集合泛型
     * @return 符合条件的集合
     */
    <E> List<E> selectList(String statement, Object parameter);

    /**
     * 查询集合(利用内存分页).
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param rowBounds 分页显示条件
     * @param <E> 集合泛型
     * @return 返回符合条件的集合
     */
    <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds);
    /**
     * 查询，将结果作为Map返回.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param <K>       Map的key值泛型
     * @param <V>       Map的Value值泛型
     * @return Map对象
     */
    <K, V> Map<K, V> selectMap(String statement, String parameter);

    /**
     * 查询，将结果作为Map返回，且key为指定的mapKey，value为对象.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param mapKey    查询结果列之一，作为key
     * @param <K>       Map的key值泛型
     * @param <V>       Map的Value值泛型
     * @return Map对象
     */
    <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey);

    /**
     * 新增数据.
     *
     * @param statement 新增语句
     * @return 新增结果
     */
    int insert(String statement);

    /**
     * 新增数据.
     *
     * @param statement 新增语句
     * @param parameter 新增数据对象
     * @return 新增结果
     */
    int insert(String statement, Object parameter);

    /**
     * 批量新增数据.
     *
     * @param statement 新增语句
     * @param parameter 新增数据对象
     * @return 新增成功数量
     */
    int batchInsert(String statement, List parameter);

    /**
     * 更新数据.
     *
     * @param statement 更新语句
     * @return 更新结果
     */
    int update(String statement);

    /**
     * 更新数据.
     *
     * @param statement 更新语句
     * @param parameter 更新数据对象
     * @return 更新结果
     */
    int update(String statement, Object parameter);

    /**
     * 批量更新数据.
     *
     * @param statement 更新语句
     * @param parameter 更新数据对象
     * @return 更新成功数量
     */
    int batchUpdate(String statement, List parameter);

    /**
     * 删除数据.
     *
     * @param statement 删除语句
     * @return 删除结果
     */
    int delete(String statement);

    /**
     * 删除数据.
     *
     * @param statement 删除语句
     * @param parameter 删除数据对象
     * @return 删除结果
     */
    int delete(String statement, Object parameter);

    /**
     * 批量删除数据.
     *
     * @param statement 删除语句
     * @param parameter 删除数据对象
     * @return 删除结果
     */
    int batchDelete(String statement, List parameter);
}

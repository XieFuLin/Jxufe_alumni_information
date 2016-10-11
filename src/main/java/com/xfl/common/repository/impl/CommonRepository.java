package com.xfl.common.repository.impl;

import com.xfl.common.repository.ICommonRepository;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by XFL.
 * time on 2016/5/20 18:23
 * description:数据库操作方法实现类
 */
@Repository("commonRepository")
public class CommonRepository implements ICommonRepository {
    /**
     * sqlSessionTemplate.
     */
    private final SqlSessionTemplate sqlSessionTemplate;

    /**
     * 自动注入sqlSessionTemplate.
     *
     * @param sqlSessionTemplate 从bean中获取的sqlSessionTemplate
     */
    @Autowired
    private CommonRepository(final SqlSessionTemplate sqlSessionTemplate) {
        Assert.notNull(sqlSessionTemplate, "mysql连接池注入失败");
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * 查询单个对象.
     *
     * @param statement 查询语句
     * @return 单个对象
     */
    @Override
    public <T> T selectOne(final String statement) {
        return sqlSessionTemplate.selectOne(statement);
    }

    /**
     * 根据查询条件查询单个对象.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @return 单个对象查询结果
     */
    @Override
    public <T> T selectOne(final String statement, final Object parameter) {
        return sqlSessionTemplate.selectOne(statement, parameter);
    }

    /**
     * 查询集合.
     *
     * @param statement 查询语句
     * @return 符合条件的集合
     */
    @Override
    public <E> List<E> selectList(final String statement) {
        return sqlSessionTemplate.selectList(statement);
    }

    /**
     * 查询集合.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @return 符合条件的集合
     */
    @Override
    public <E> List<E> selectList(final String statement, final Object parameter) {
        return sqlSessionTemplate.selectList(statement, parameter);
    }

    /**
     * 查询集合(利用内存分页).
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param rowBounds 分页显示条件
     * @param <E>       集合泛型
     * @return 返回集合
     */
    @Override
    public <E> List<E> selectList(final String statement, final Object parameter, final RowBounds rowBounds) {
        return sqlSessionTemplate.selectList(statement, parameter, rowBounds);
    }

    /**
     * 查询，将结果作为Map返回.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @return Map对象
     */
    @Override
    public <K, V> Map<K, V> selectMap(final String statement, final String parameter) {
        return sqlSessionTemplate.selectMap(statement, parameter);
    }

    /**
     * 查询，将结果作为Map返回，且key为指定的mapKey，value为对象.
     *
     * @param statement 查询语句
     * @param parameter 查询条件
     * @param mapKey    查询结果列之一，作为key
     * @return Map对象
     */
    @Override
    public <K, V> Map<K, V> selectMap(final String statement, final Object parameter, final String mapKey) {
        return sqlSessionTemplate.selectMap(statement, parameter, mapKey);
    }

    /**
     * 新增数据.
     *
     * @param statement 新增语句
     * @return 新增结果
     */
    @Override
    public int insert(final String statement) {
        return sqlSessionTemplate.insert(statement);
    }

    /**
     * 新增数据.
     *
     * @param statement 新增语句
     * @param parameter 新增数据对象
     * @return 新增结果
     */
    @Override
    public int insert(final String statement, final Object parameter) {
        return sqlSessionTemplate.insert(statement, parameter);
    }

    /**
     * 批量新增数据.
     *
     * @param statement 新增语句
     * @param parameter 新增数据对象
     * @return 新增成功数量
     */
    @Override
    public int batchInsert(final String statement, final List parameter) {
        SqlSessionFactory sqlSessionFactory =
                sqlSessionTemplate.getSqlSessionFactory();
        int count = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            if (!parameter.isEmpty()) {
                for (Object aParameter : parameter) {
                    count += sqlSession.insert(statement, aParameter);
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.clearCache();
                sqlSession.close();
            }
        }
        return count;
    }

    /**
     * 更新数据.
     *
     * @param statement 更新语句
     * @return 更新结果
     */
    @Override
    public int update(final String statement) {
        return sqlSessionTemplate.update(statement);
    }

    /**
     * 更新数据
     *
     * @param statement 更新语句
     * @param parameter 更新数据对象
     * @return 更新结果
     */
    @Override
    public int update(final String statement, final Object parameter) {
        return sqlSessionTemplate.update(statement, parameter);
    }

    /**
     * 批量更新数据.
     *
     * @param statement 更新语句
     * @param parameter 更新数据对象
     * @return 更新成功数量
     */
    @Override
    public int batchUpdate(final String statement, final List parameter) {
        SqlSessionFactory sqlSessionFactory =
                sqlSessionTemplate.getSqlSessionFactory();
        int count = 0;
        SqlSession sqlSession = null;
        try {
            sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
            if (!parameter.isEmpty()) {
                for (Object aParameter : parameter) {
                    count += sqlSession.update(statement, aParameter);
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.clearCache();
                sqlSession.close();
            }
        }
        return count;
    }

    /**
     * 删除数据.
     *
     * @param statement 删除语句
     * @return 删除结果
     */
    @Override
    public int delete(final String statement) {
        return sqlSessionTemplate.delete(statement);
    }

    /**
     * 删除数据.
     *
     * @param statement 删除语句
     * @param parameter 删除数据对象
     * @return 删除结果
     */
    @Override
    public int delete(final String statement, final Object parameter) {
        return sqlSessionTemplate.delete(statement, parameter);
    }

    /**
     * 批量删除数据.
     *
     * @param statement 删除语句
     * @param parameter 删除数据对象
     * @return 删除结果
     */
    @Override
    public int batchDelete(final String statement, final List parameter) {
        return sqlSessionTemplate.delete(statement, parameter);
    }
}

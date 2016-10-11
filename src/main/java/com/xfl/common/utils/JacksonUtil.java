package com.xfl.common.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by XFL.
 * time on 2016/10/7 19:58
 * description: Json工具类,使用jackson.
 */
public class JacksonUtil {
    /**
     * 自动注入objectMapper,已经配置了该bean.
     */
    @Resource
    private ObjectMapper objectMapper;

    /**
     * 将JavaBean转化为Json对象.
     * @param entity JavaBean.
     * @return 返回转化的结果,如果失败就为null.
     */
    public final String writeEntityToJson(final Object entity) {
        String json = null;
         try {
             json = objectMapper.writeValueAsString(entity);
         } catch (IOException e) {
         }
         return json;
    }

    /**
     * 将json转化为Entity.
     * @param json 需要转化的json
     * @param cls 需要转化的目标类型
     * @param <T> 需要返回的Entity类型
     * @return 转化后的结果,entity
     */
    public <T> T  readJsonToEntity(final String json, Class<T> cls) {
        T entity = null;
        try {
            entity = objectMapper.readValue(json, cls);
        } catch (IOException e) {
        }
        return entity;
    }
    /**
     * 将Map转化为Json.
     * @param map 需要转化为json的对象.
     * @return 返回转化的结果.
     */
    public final String writeMapToJson(final Map<?, ?> map) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(map);
        } catch (IOException e) {
        }
        return json;
    }

    /**
     * 将Json转化为map.
     * @param mapJson 需要转化的Json
     * @param typeReference 类型 例如: new TypeReference<Map<String, Integer>>(){}
     * @param <K> 键值
     * @param <V> 值
     * @return 返回转换后的结果
     */
    public <K, V> Map<K, V> readJsonToMap(String mapJson, TypeReference typeReference) {
          Map<K, V> map = null;
        try {
            map = objectMapper.readValue(mapJson, typeReference);
        } catch (IOException e) {
        }
        return map;
    }

    /**
     * List转化为Json.
     * @param list 需要转化的List
     * @return  转化后的结果
     */
    public String writeListToJson(List<?> list) {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(list);
        } catch (IOException e) {
        }
        return json;
    }

    /**
     * 将json转化为List.
     * @param listJson 需要转化的list
     * @param typeReference  类型new TypeReference<List<Student>>() {}
     * @param <E> 存放的元素
     * @return 返回转化后的结合
     */
    public <E> List<E> readJsonToList(String listJson, TypeReference typeReference) {
        List<E> list = null;
        try {
            list = objectMapper.readValue(listJson, typeReference);
        } catch (IOException e) {
        }
        return list;
    }

    /**
     * 将json转化为JavaBean.
     * @param json 需要转化的json
     * @param typeReference 类型new TypeReference<List<Student>>() {}
     * @param <T> 泛型,表示Type类型
     * @return 返回转化后的结果
     */
    public <T> T readJsonToType(String json, TypeReference typeReference) {
        T entity = null;
        try {
            entity = objectMapper.readValue(json, typeReference);
        } catch (IOException e) {
        }
        return entity;
    }
}

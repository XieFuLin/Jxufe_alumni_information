package com.xfl.test.service;

import java.util.List;
import java.util.Map;

/**
 * Created by XFL.
 * time on 2016/5/20 18:46
 * description: 测试接口
 */
public interface ITestService {
    /**
     * 测试.
     * @return 返回响应结果
     */
    List<Map<String, Object>> test();

    /**
     * 测试数据插入.
     * @return 返回受影响的行数
     */
     Integer insertTest();
}

package com.xfl.test.service.impl;

import com.xfl.common.repository.ICommonRepository;
import com.xfl.test.service.ITestService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by XFL.
 * time on 2016/5/20 18:47
 * description:测试 Service实现类
 */
@Service("testService")
public class TestServiceImpl implements ITestService {
    /**
     * 注入commonRepository.
     */
    @Resource
    private ICommonRepository commonRepository;

    /**
     * 测试.
     * @return 返回测试响应结果
     */
    @Override
    public Map<String, Object> test() {
        return commonRepository.selectOne("TestMapper.test");
    }

    /**
     * 测试数据插入.
     *
     * @return 返回受影响的行数
     */
    @Override
    public Integer insertTest() {
        Map<String,String> parameterMap = new HashMap<>();
        parameterMap.put("testName", "TestName");
        Integer count = commonRepository.insert("TestMapper.saveTest", parameterMap);
      //  int tmp = 1 /0;
        return count;
    }
}

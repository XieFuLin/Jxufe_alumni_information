package com.xfl.test.controller;

import com.xfl.common.controller.BaseController;
import com.xfl.common.entity.Response;
import com.xfl.common.enumeration.ResponseStatusEnum;
import com.xfl.test.service.ITestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by XFL.
 * time on 2016/10/10 22:40
 * description:测试类
 */
@RestController
@RequestMapping("/Test")
public class TestController extends BaseController{
    /**
     * 注入Service.
     */
    @Resource
    private ITestService testService;

    /**
     * 用于测试.
     * @return 返回测试结果
     */
    @RequestMapping(method = RequestMethod.GET)
    public Response test() {
        int count = testService.insertTest();
        return new Response(ResponseStatusEnum.SUCCESS.getCode(), testService.test());
    }
}

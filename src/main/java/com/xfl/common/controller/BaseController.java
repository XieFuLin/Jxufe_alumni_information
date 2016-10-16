package com.xfl.common.controller;

import com.xfl.common.entity.Response;
import com.xfl.common.enumeration.ResponseStatusEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by XFL.
 * time on 2016/5/20 19:37
 * description:基础Controller
 */
@RestController
public class BaseController {
    /**
     * 日志对象.
     */
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 用于获取请求对象的信息.
     */
    @Resource
    private HttpServletRequest servletRequest;

    /**
     * Controller统一异常处理.
     *
     * @param e 异常对象
     * @return 异常返回信息
     */
    @ExceptionHandler
    public final Response exceptionHandler(final Exception e) {
        logger.info("发生异常", e);
        logger.info("异常信息: ", e.getMessage());
        return new Response(
                ResponseStatusEnum.FAILURE.getCode(), "发生异常，请查看日志！");
    }
    /**
     * 获取请求参数.
     *
     * @return 请求参数Map集合
     */
    protected final Map<String, Object> getParameterMap() {
        Map<String, Object> parameterMap = new HashMap<>();
        Map<String, String[]> requestParameterMap = servletRequest.getParameterMap();
        requestParameterMap.forEach((key, value) -> {
            String paramValue = "";
            if (value != null) {
                for (String aValue : value) {
                    paramValue = aValue + ",";
                }
                //用于删除paramValue多余的最后一个逗号
                paramValue = paramValue.substring(0, paramValue.length() - 1);
            }
            parameterMap.put(key, paramValue);
        });
        return parameterMap;
    }

    /**
     * 获取request对象.
     * @return 返回request对象
     */
    protected final HttpServletRequest getServletRequest() {
        return servletRequest;
    }

    /**
     * 获取session 如果没有的话就不创建一个新的session.
     * @return 返回session对象
     */
    protected final HttpSession getSession() {
        return servletRequest.getSession(Boolean.FALSE);
    }
}

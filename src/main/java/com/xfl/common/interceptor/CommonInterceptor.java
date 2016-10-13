package com.xfl.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by XFL.
 * time on 2016/9/9 23:12
 * description:拦截器
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    /**
     * Log4j2 日志对象.
     */
    private static final Logger logger = LoggerFactory.getLogger(CommonInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入拦截器:" + "CommonInterceptor->" + "preHandle");
        String path = request.getServletPath();
        logger.info("请求路径:" + path);
        return super.preHandle(request, response, handler);
    }
}

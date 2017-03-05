package com.xfl.common.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
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

    /**
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("进入拦截器:" + "CommonInterceptor->" + "preHandle");
        String path = request.getServletPath();
        logger.info("请求路径:" + path);
        return super.preHandle(request, response, handler);
    }

    /**
     * 在当前请求进行处理之后，也就是Controller 方法调用之后执行.
     * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用，
     * 所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    /**
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}

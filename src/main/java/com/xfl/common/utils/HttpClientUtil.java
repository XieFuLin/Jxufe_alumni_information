package com.xfl.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by XFL.
 * time on 2017/3/19 22:23
 * description:HttpClientUtil
 */
public class HttpClientUtil {
    /**
     * 日志打印.
     */
    private final Logger log = LoggerFactory.getLogger(HttpClientUtil.class);
    private HttpClient httpClient = null;

    /**
     * 发送httpGet请求.
     *
     * @param url 请求地址
     * @param cls 返回结果数据类型
     * @param <T> 返回结果类型
     * @return 返回结果
     */
    public <T> T httpGet(String url, Class<T> cls) {
        try {
            url = URLDecoder.decode(url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("url编码转化出错:" + e);
        }
        T resultObject = null;
        //创建连接
        httpClient = HttpClients.createDefault();
        // 创建httpGet
        HttpGet httpget = new HttpGet(url);
        //执行连接
        try {
            HttpResponse httpResponse = httpClient.execute(httpget);
            HttpEntity httpEntity = httpResponse.getEntity();
            String resultData = EntityUtils.toString(httpEntity);
            JacksonUtil jacksonUtil = new JacksonUtil();
            resultObject = jacksonUtil.readJsonToEntity(resultData, cls);
        } catch (IOException e) {
            log.error("发送get请求异常:" + e);
        }
        return resultObject;
    }
}

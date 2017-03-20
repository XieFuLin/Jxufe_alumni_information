package com.xfl.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
            resultObject = httpExecute(cls, httpget);
        } catch (IOException e) {
            log.error("发送get请求异常:" + e);
        }
        return resultObject;
    }

    /**
     * 发送post请求.
     *
     * @param url    请求地址.
     * @param params 参数
     * @param cls    返回结果类型
     * @param <T>    返回结果泛型
     * @return 返回结果
     */
    public <T> T httpPost(String url, Map<String, String> params, Class<T> cls) {
        T resultObject = null;
        httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> formParams = new ArrayList<>();
        Set<String> keySet = params.keySet();
        for (String key : keySet) {
            formParams.add(new BasicNameValuePair(key, params.get(key)));
        }
        UrlEncodedFormEntity uefEntity;
        try {
            uefEntity = new UrlEncodedFormEntity(formParams, "UTF-8");
            httpPost.setEntity(uefEntity);
            try {
                resultObject = httpExecute(cls, httpPost);
            } catch (IOException e) {
                log.error("发送post请求失败:" + e);
            }
        } catch (UnsupportedEncodingException e) {
            log.error("发送post请求编码转化失败:" + e);
        }

        return resultObject;
    }

    /**
     * 执行Execute.
     *
     * @param cls        返回结果类型
     * @param httpMethod 请求方式 HttpPost或者HttpGet
     * @param <T>        返回结果泛型
     * @return 返回请求结果
     * @throws IOException
     */
    private <T> T httpExecute(Class<T> cls, HttpUriRequest httpMethod) throws IOException {
        T resultObject;
        HttpResponse httpResponse = httpClient.execute(httpMethod);
        HttpEntity entity = httpResponse.getEntity();
        String resultData = EntityUtils.toString(entity);
        JacksonUtil jacksonUtil = new JacksonUtil();
        resultObject = jacksonUtil.readJsonToEntity(resultData, cls);
        return resultObject;
    }
}

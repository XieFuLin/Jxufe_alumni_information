package com.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * Created by XFL.
 * time on 2017/3/8 23:37
 * description:测试工具类
 */
public class RestTester {
    private final String url;

    private final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();

    public void set(String key, String value) {
        params.add(key, value);
    }

    /**
     * 构造方法,请求url.
     *
     * @param url 请求地址
     */
    public RestTester(String url) {
        super();
        this.url = url;
    }

    /**
     * 发送get请求.
     *
     * @return 返回请求结果
     */
    public ResponseEntity<String> get() {
        String fullUrl = UriComponentsBuilder.fromHttpUrl(url).queryParams(params).build().toUriString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForEntity(fullUrl, String.class);
    }

    /**
     * 发送post请求.
     *
     * @return 返回请求结果
     */
    public ResponseEntity<String> post() {
        String fullUrl = UriComponentsBuilder.fromHttpUrl(url).build().toUriString();
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.postForEntity(fullUrl, params, String.class);
    }

    /**
     * 发送/获取 服务端数据(主要用于解决发送put,delete方法无返回值问题).
     *
     * @param url      绝对地址
     * @param method   请求方式
     * @param params   请求参数
     * @param bodyType 返回类型
     * @param <T>      返回类型
     * @return 返回结果(响应体)
     */
    public <T> T exchange(String url, HttpMethod method, Map<String, Object> params, Class<T> bodyType) {
        // 请求头
        HttpHeaders headers = new HttpHeaders();
        MimeType mimeType = MimeTypeUtils.parseMimeType("application/json");
        MediaType mediaType = new MediaType(mimeType.getType(), mimeType.getSubtype(), Charset.forName("UTF-8"));
        // 请求体
        headers.setContentType(mediaType);
        //提供json转化功能
        ObjectMapper mapper = new ObjectMapper();
        String str = null;
        try {
            if (params != null) {
                str = mapper.writeValueAsString(params);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // 发送请求
        HttpEntity<String> entity = new HttpEntity<>(str, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<T> obj = restTemplate.exchange(url, method, entity, bodyType);
        if (obj != null) {
            return obj.getBody();
        }
        return null;
    }
}

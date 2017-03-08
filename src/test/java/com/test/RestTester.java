package com.test;

import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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
}

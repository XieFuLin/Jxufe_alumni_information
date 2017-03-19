package com.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xfl.common.entity.Response;
import com.xfl.common.utils.RestTemplateUtil;

/**
 * Created by XFL
 * time on 2017/3/8 23:41
 * description:测试Get方法
 */
public class TestGet {
    public static void main(String[] args) {
        RestTemplateUtil restTester = new RestTemplateUtil("http://localhost:8080/Test");
        String result = restTester.get(String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            Response response = objectMapper.readValue(result, Response.class);
            System.out.println(response.getData());
        } catch (Exception e) {
            System.out.println("Error");
        }

    }

}

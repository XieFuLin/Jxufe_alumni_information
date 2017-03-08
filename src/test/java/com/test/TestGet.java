package com.test;

import org.springframework.http.ResponseEntity;

/**
 * Created by XFL
 * time on 2017/3/8 23:41
 * description:测试Get方法
 */
public class TestGet {
    public static void main(String[] args) {
        RestTester restTester = new RestTester("http://localhost:8080/Test");
        ResponseEntity<String> responseEntity = restTester.get();
        System.out.println(responseEntity.getBody());
    }

}

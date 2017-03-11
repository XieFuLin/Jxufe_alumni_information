package com.test;


/**
 * Created by XFL
 * time on 2017/3/8 23:41
 * description:测试Get方法
 */
public class TestGet {
    public static void main(String[] args) {
        RestTester restTester = new RestTester("http://localhost:8080/Test");
        String result = restTester.get(String.class);
        System.out.println(result);
    }

}

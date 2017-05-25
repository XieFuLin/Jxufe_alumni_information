package com.test;


import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**
 * Created by XFL
 * time on 2017/3/12 23:41
 * description:
 */
public class TestHttpClient {
    public static void main(String[] args) {
        CloseableHttpClient httpClient = getHttpClient();

    }

    private static CloseableHttpClient getHttpClient() {
        return HttpClients.createDefault();
    }
}

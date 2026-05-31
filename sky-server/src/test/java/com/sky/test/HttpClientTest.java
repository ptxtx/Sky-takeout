package com.sky.test;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
public class HttpClientTest {

    /*
    测试通过HttpClient发送get方式的请求
     */
    @Test
    public void testGet() throws IOException {
        //s1. 创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //s2.创建请求对象
        HttpGet httpGet = new HttpGet("http://localhost:8080/user/shop/status");

        //s3.发送请求,获取响应结果
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //获取服务端返回的状态码
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态码为："+statusCode);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity);//body:响应体
        System.out.println("服务端返回的响应结果为："+body);

        //s4.关闭流并释放连接
        response.close();
        httpClient.close();
    }

    /**
     * 测试通过HttpClient发送post方式的请求
     */
    @Test
    public void testPost() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //s2.创建请求对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/admin/employee/login");

        //设置请求参数
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", "admin");
        jsonObject.put("password", "123456");
        StringEntity entity=new StringEntity(jsonObject.toString());

        entity.setContentEncoding("utf-8");
        entity.setContentType("application/json");

        httpPost.setEntity(entity);

        //s3.发送请求，获取响应结果
        CloseableHttpResponse response = httpClient.execute(httpPost);

        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println("服务端返回的状态码为："+statusCode);
        HttpEntity entity1 = response.getEntity();
        String body = EntityUtils.toString(entity1);
        System.out.println("服务端返回的响应结果为："+body);
        response.close();
        httpClient.close();

    }

}

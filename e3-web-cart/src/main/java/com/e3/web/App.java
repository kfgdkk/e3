package com.e3.web;

import com.e3.service.goods.pojo.TbItem;
import com.e3.util.common.HttpClientUtil;
import com.e3.util.common.JsonUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class App {
    @Test
    public  void doGet() throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity,"utf-8");
        System.out.println(s);
        response.close();
        httpClient.close();
    }

    public static void main(String[] args) {
        try {
            doGetWithParam();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void doGetWithParam()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        URIBuilder uriBuilder = new URIBuilder("http://www.sogou.com");
        uriBuilder.addParameter("query","老九门");
        HttpGet get = new HttpGet(uriBuilder.build());
        CloseableHttpResponse response = httpClient.execute(get);
        int statusCode = response.getStatusLine().getStatusCode();
        System.out.println(statusCode);
        HttpEntity entity = response.getEntity();
        String s = EntityUtils.toString(entity,"utf-8");
        System.out.println(s);
        response.close();
        httpClient.close();
    }
    @Test
    public void doPost()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
        CloseableHttpResponse execute = httpClient.execute(post);
        HttpEntity entity = execute.getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        execute.close();
        httpClient.close();
    }
    @Test
    public void doPostWithParam()throws Exception{
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
        List<NameValuePair> kvList = new ArrayList<>();
        kvList.add(new BasicNameValuePair("username","zhangsan"));
        kvList.add(new BasicNameValuePair("password","123"));
        StringEntity entity= new UrlEncodedFormEntity(kvList,"utf-8");
        post.setEntity(entity);
        CloseableHttpResponse execute = httpClient.execute(post);
        String s = EntityUtils.toString(entity);
        System.out.println(s);
        execute.close();
        httpClient.close();
    }
    @Test
    public void  testHttpClientUTils(){
        String jsondata =  HttpClientUtil.doGet("http://localhost:8080/e3-web-manager/item/155497555462466");
        TbItem tbItem = JsonUtils.jsonToPojo(jsondata,TbItem.class);
        System.out.println(tbItem.getTitle());
    }
}

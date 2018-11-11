package com.xxin.demo.chat.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

public class HttpUtil {

    public static String httpGet(String url, HashMap map){
        CloseableHttpClient client = HttpClients.createDefault();
        int i=0;
        if (map!=null){
            url +="?";
            for (Object key:map.keySet()){
                if (i+1==map.size()){
                    url += key+"="+map.get(key);
                }else{
                    url += key+"="+map.get(key)+"&";
                }
            }
        }
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        String body = null;
        try {
            response = client.execute(get);
            HttpEntity entity = response.getEntity();
            body  =  EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }
}

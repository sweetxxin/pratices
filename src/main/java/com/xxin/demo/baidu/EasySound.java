package com.xxin.demo.baidu;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.boot.json.JacksonJsonParser;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EasySound {
    static CloseableHttpClient client = HttpClients.createDefault();

    public static String getToken() throws IOException {
        String url = "https://openapi.baidu.com/oauth/2.0/token";
        String apiKey = "e7Q5yXmh7CLUqsO2MbI1gmG3";
        String appSecret = "k0X7hrpcFqCGZsPemEB87ELj6XlXQ1Y2";
        HttpPost post = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("grant_type", "client_credentials"));
        nvps.add(new BasicNameValuePair("client_id", apiKey));
        nvps.add(new BasicNameValuePair("client_secret",appSecret));
        post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
        post.setHeader("Content-type", "application/x-www-form-urlencoded");
        post.setHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        CloseableHttpResponse response = client.execute(post);
        HttpEntity entity = response.getEntity();
        String body = EntityUtils.toString(entity, "UTF-8");
        JacksonJsonParser parser = new JacksonJsonParser();
        Map map = parser.parseMap(body);
        return  map.get("access_token").toString();
    }
    public static byte[] getAudio(String path) throws IOException {
        byte[]bytes = toByteArray(" ");
        return null;

    }
    public static byte[] toByteArray(String filename) throws IOException {
        FileChannel fc = null;
        try {
            fc = new RandomAccessFile(filename, "r").getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0,
                    fc.size()).load();
            System.out.println(byteBuffer.isLoaded());
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {

    }
}

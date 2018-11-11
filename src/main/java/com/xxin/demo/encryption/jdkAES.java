package com.xxin.demo.encryption;



import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SecureRandom;


public class jdkAES {
    private static String src = "sweet xxin";

    public static void main(String[] args) {
        jdkDES();
    }
    public static void jdkDES() {
        try {
            //1.生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            //2.key转换
            Key key = new SecretKeySpec(byteKey,"AES");
            //3.加密
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk aes encrypt:" + Base64.encodeBase64String(result));
            //4.解密
            cipher.init(Cipher.DECRYPT_MODE, key);
            result = cipher.doFinal(result);
            System.out.println("jdk aes encrypt:"+new String(result));
        }catch (Exception e){

        }
    }
}

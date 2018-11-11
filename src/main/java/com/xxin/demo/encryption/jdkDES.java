package com.xxin.demo.encryption;

import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.Key;

public class jdkDES {
    private static String src = "sweet xxin";

    public static void main(String[] args) {
        jdkDES();
    }
    public static void jdkDES() {
        try {
            //1.生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
            keyGenerator.init(56);
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            //2.key转换
            DESKeySpec desKeySpec = new DESKeySpec(byteKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
            Key convertSecretKey = factory.generateSecret(desKeySpec);
            //3.加密
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk des encrypt:" + HexUtils.toHexString(result));
            //4.解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk des encrypt:"+new String(result));
        }catch (Exception e){

        }
    }
}

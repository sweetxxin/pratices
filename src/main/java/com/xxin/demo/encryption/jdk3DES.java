package com.xxin.demo.encryption;

import org.apache.tomcat.util.buf.HexUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.DESedeKeySpec;
import java.security.Key;
import java.security.SecureRandom;

public class jdk3DES {
    private static String src = "sweet xxin";

    public static void main(String[] args) {
        jdk3DES();
    }
    public static void jdk3DES() {
        try {
            //1.生成key
            KeyGenerator keyGenerator = KeyGenerator.getInstance("DESede");
            keyGenerator.init(new SecureRandom());
            SecretKey secretKey = keyGenerator.generateKey();
            byte[] byteKey = secretKey.getEncoded();
            //2.key转换
            DESedeKeySpec desKeySpec = new DESedeKeySpec(byteKey);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("DESede");
            Key convertSecretKey = factory.generateSecret(desKeySpec);
            //3.加密
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, convertSecretKey);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk 3des encrypt:" + HexUtils.toHexString(result));
            //4.解密
            cipher.init(Cipher.DECRYPT_MODE, convertSecretKey);
            result = cipher.doFinal(result);
            System.out.println("jdk 3des encrypt:"+new String(result));
        }catch (Exception e){

        }
    }
}

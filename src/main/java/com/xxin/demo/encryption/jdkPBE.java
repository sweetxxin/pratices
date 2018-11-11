package com.xxin.demo.encryption;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class jdkPBE {
    private static String src = "sweet xxin";
    public static void main(String[] args) {
        jdkPEB();
    }
    public static void jdkPEB(){
        try {
            //1.初始化盐
            SecureRandom random = new SecureRandom();
            byte[] salt = random.generateSeed(8);
            //2.口令与秘钥
            String password = "sweetxxin";
            PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray());
            SecretKeyFactory factory =  SecretKeyFactory.getInstance("PBEWITHMD5andDES");
            Key key = factory.generateSecret(pbeKeySpec);
            //3.加密
            PBEParameterSpec pbeParameterSpec = new PBEParameterSpec(salt,100 );
            Cipher cipher = Cipher.getInstance("PBEWITHMD5andDES");
            cipher.init(Cipher.ENCRYPT_MODE,key,pbeParameterSpec);
            byte[] result = cipher.doFinal(src.getBytes());
            System.out.println("jdk pbe encrypt:"+ Base64.encodeBase64String(result));
            //4.解密
            cipher.init(Cipher.DECRYPT_MODE,key,pbeParameterSpec);
            result = cipher.doFinal(result);
            System.out.println("jdk pbe decrypt:"+new String(result));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

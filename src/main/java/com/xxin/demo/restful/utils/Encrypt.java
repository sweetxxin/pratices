package com.xxin.demo.restful.utils;

import org.apache.tomcat.util.buf.HexUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrypt {
    public static String sha1(String src){
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("SHA1");
            messageDigest.update(src.getBytes());
            return HexUtils.toHexString(messageDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}

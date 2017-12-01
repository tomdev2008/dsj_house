package com.dsj.common.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 
 * @作用：常规Base64加密解密实用工具类
 * @功能：
 * @作者: zgz
 * @日期：2015-12-10 下午11:13:05 
 * @版本：V1.0
 */
public class Base64SecurityUtil {

    public static String getEncryptString(String originalString) throws UnsupportedEncodingException{
        byte[] arr = Base64.encodeBase64(originalString.getBytes(), true);
        return new String(arr,"UTF-8");
    }
    

    public static String getDecryptString(String encryptString) throws UnsupportedEncodingException{
        byte[] arr = Base64.decodeBase64(encryptString.getBytes("UTF-8"));
        return new String(arr);
    }
    
    public static byte[] getDecryptArr(String encryptString) throws UnsupportedEncodingException{
        byte[] arr = Base64.decodeBase64(encryptString.getBytes("UTF-8"));
        return arr;
    }

    public static void main(String[] args) throws UnsupportedEncodingException{
        String str="Hello world";
        
        String str1= Base64SecurityUtil.getEncryptString(str);
        System.out.println("经Base64加密后的密文为"+str1);
        
        String str2= Base64SecurityUtil.getDecryptString(str1);
        System.out.println("经Base64解密后的原文为"+str2);
    }
}
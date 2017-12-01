package com.dsj.common.utils.encrypt;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
	StringBuffer resultSb = new StringBuffer();
	for (int i = 0; i < b.length; i++)
	    resultSb.append(byteToHexString(b[i]));

	return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
	int n = b;
	if (n < 0)
	    n += 256;
	int d1 = n / 16;
	int d2 = n % 16;
	return hexDigits[d1] + hexDigits[d2];
    }

    public static String MD5Encode(String origin, String charsetname) {
	String resultString = null;
	try {
	    resultString = new String(origin);
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    if (charsetname == null || "".equals(charsetname))
		resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
	    else
		resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
	} catch (Exception exception) {
	}
	return resultString;
    }

    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
	    "e", "f" };

    // 产生随机的三位数
    public static String getNine() {
	Random rad = new Random();
	return rad.nextInt(999999999) + "" + new Date();
    }

    public static void main(String[] args) {
	// System.out.println(MD5Util.MD5Encode(MD5Util.getNine(),
	// "").toUpperCase());
	String hex = MD5Util.MD5Encode("ABCD123456", "UTF-8");
	System.out.println(hex);
    }

    /**
     * 1.对文本进行32位小写MD5加密
     * 
     * @param plainText
     *            要进行加密的文本
     * @return 加密后的内容
     */
    public static String textToMD5L32(String plainText) {
	String result = null;
	// 首先判断是否为空
	if (StringUtils.isBlank(plainText)) {
	    return null;
	}
	try {
	    // 首先进行实例化和初始化
	    MessageDigest md = MessageDigest.getInstance("MD5");
	    // 得到一个操作系统默认的字节编码格式的字节数组
	    byte[] btInput = plainText.getBytes();
	    // 对得到的字节数组进行处理
	    md.update(btInput);
	    // 进行哈希计算并返回结果
	    byte[] btResult = md.digest();
	    // 进行哈希计算后得到的数据的长度
	    StringBuffer sb = new StringBuffer();
	    for (byte b : btResult) {
		int bt = b & 0xff;
		if (bt < 16) {
		    sb.append(0);
		}
		sb.append(Integer.toHexString(bt));
	    }
	    result = sb.toString();
	} catch (NoSuchAlgorithmException e) {
	    e.printStackTrace();
	}
	return result;
    }
}

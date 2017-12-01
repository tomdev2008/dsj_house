package com.dsj.common.utils;

import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import com.dsj.common.constants.CommConst;

public class ShiroSaltAndMd5Utils {

	public static Integer getSalt() {
		Integer salt = new Random().nextInt(20) + 1;
		return salt;
	}
	// 对照shiro的md5方式加密
	public static String getMD5SaltPwd(Integer salt) {
		SimpleHash hashpwd = new SimpleHash(Md5Hash.ALGORITHM_NAME, CommConst.INIT_PWD,
				SimpleHash.toBytes(salt.toString()), 1024);
		return hashpwd.toString();
	}
	public static String getMD5SaltPwd(String password, Integer salt) {
		SimpleHash hashpwd = new SimpleHash(Md5Hash.ALGORITHM_NAME,password,
				SimpleHash.toBytes(salt.toString()), 1024);
		return hashpwd.toString();
	}
	
	public static String getMD5(String password) {
		SimpleHash hashpwd = new SimpleHash(Md5Hash.ALGORITHM_NAME,password,
				null, 1);
		return hashpwd.toString();
	}
}
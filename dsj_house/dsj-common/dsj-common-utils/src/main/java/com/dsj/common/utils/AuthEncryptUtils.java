package com.dsj.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

public class AuthEncryptUtils {
	
	private static final Logger logger=Logger.getLogger(AuthEncryptUtils.class);
	
	/**
	 * 返回加密认证串

	 */
	public static String encrypt(String param, String key,String ts) {
			//String jsonParamCode1=JsonTools.mapToJson(mapParam);
		String sign=DigestUtils.md5Hex( key+ts+ param);
		System.out.println(sign);
		return DigestUtils.md5Hex( key+ts+ param);
		
		
	}
	/*public static void main(String[] args) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("username", "111");
		map.put("password", "111");
		String jsonParamCode1=JsonTools.mapToJson(map);
		//9e9b682b480d7a95721e6fd08ec8c897
		System.out.println(encrypt("1482139346","n3m662ypvrcocu2m",jsonParamCode1));
	}*/
	/**
	 * key按照字典排序并返回字符串
	 * params中包括随机串
	 * @param params
	 * @param nonceStr
	 * @return
	 */
	public static String getFieldAndValue(Map<String, String> params) {
		Set<String> keySet = params.keySet();
		List<String> keyList = new ArrayList<String>(keySet);
		//字典序排序
		Collections.sort(keyList);
		String mapKey = keyList.get(0);
		String mapValue = params.get(mapKey);
		//拼接
		String fieldAndValue = mapKey + "=" + mapValue;
		for (int i = 1; i < keyList.size(); i++) {
			mapKey = keyList.get(i);
			mapValue = params.get(mapKey);
			fieldAndValue += "&" + mapKey + "=" + mapValue;
		}
		return fieldAndValue;
	}
	
	/**
	 * 返回随机字符串，长度10
	 * @return
	 */
	public static String nonceStr() {
		String str = RandomStringUtils.randomAlphanumeric(10);
		return str;
	}
	
	
}

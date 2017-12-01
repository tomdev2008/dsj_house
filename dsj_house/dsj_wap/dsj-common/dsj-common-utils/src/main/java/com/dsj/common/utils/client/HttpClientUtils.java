package com.dsj.common.utils.client;

import java.net.URLDecoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;

import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.client.http.HttpUtils;
import com.dsj.common.utils.client.http.ResponseWrap;
import com.dsj.common.utils.http.Http;


public class HttpClientUtils {
	
	/**
	 * 封装请求
	 */
	protected static String doRequest(HttpUtils httpUtils){
		String result="";
		httpUtils.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:39.0) Gecko/20100101 Firefox/39.0");
		ResponseWrap response = httpUtils.execute(); //执行请求
		result=response.getString();
		httpUtils.shutdown();
		return result;
	}
	
	/**
	 * Get方式提交,URL中包含查询参数, 格式：http://www.g.cn?search=p&name=s.....
	 * @param url
	 * 提交地址
	 * @return 响应消息
	 */
	public static String get(String url) {
		return doRequest(HttpUtils.get(url));
	}
	
	/**
	 * Get方式提交,URL中不包含查询参数, 格式：http://www.g.cn
	 * @param url
	 * 提交地址
	 * @param params
	 * 查询参数集, 键/值对
	 * @return 响应消息
	 */
	public static String get(String url, Map<String,String> params) {
		HttpUtils httpUtil=HttpUtils.get(url);
		httpUtil.addParameters(params);
		return doRequest(httpUtil);
	}
	
	/**
	 * Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	 *
	 * @param url
	 * 提交地址
	 * @param params
	 * 提交参数集, 键/值对
	 * @return 响应消息
	 */
	public static String post(String url, Map<String,String>  params) {
		HttpUtils httpUtil=HttpUtils.post(url);
		httpUtil.addParameters(params);
		return doRequest(httpUtil);
	}
	
	/**
	 * Post方式提交,URL中不包含提交参数, 格式：http://www.g.cn
	 *
	 * @param url
	 * 提交地址
	 * @param params
	 * 提交参数, String
	 * @return 响应消息
	 */
	public static String post(String url, String strParams) {
		HttpUtils httpUtil=HttpUtils.post(url);
		httpUtil.setParameter(strParams);
		return doRequest(httpUtil);
	}
	public static void main(String[] args) {
		String url ="http://apixt.cs.12366.com/api/dept/findDeptList.do";
		String ts1 = String.valueOf((new Date()).getTime());
		String key1 = "n3m662ypvrcocu2m";
		Map<String,Object> mapParam=new HashMap<String,Object>();
		
		mapParam.put("version", "1");

	
		mapParam.put("deptType", 1);//  0：直营店，1：BD店
		mapParam.put("deptAreaId", "110128");
		mapParam.put("currentPage", 1);
		mapParam.put("pageSize", 10);

		
		String jsonParamCode1=JsonTools.mapToJson(mapParam);
		String sign1 = DigestUtils.md5Hex(key1 + ts1+ String.valueOf(jsonParamCode1));
		
		//url+="?sign="+sign1+"&ts="+ts1+"&system_code="+111+"&param="+jsonParamCode1;
		
		Map<String,String> mapParam1=new HashMap<String,String>(); 
		mapParam1.put("sign", sign1);
		mapParam1.put("ts", ts1);
		mapParam1.put("param", jsonParamCode1);
		System.out.println(HttpClientUtils.get(url,mapParam1));
	}
}

package com.dsj.data.web.utils.payUtil;

import java.util.HashMap;
import java.util.Map;

public class payTest {

	public static void main(String[] args) {
		
		/*
		//查询订单状态接口
		//获取请求参数
		String requestid			= "F20170914105146572";	
		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);
		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.QUERYORDERAPI_REQUEST_HMAC_ORDER);
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.QUERYORDERAPI_NAME);
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		System.out.println(requestUrl + "?data=" + data + "&cusotmernumber=" + ZGTUtils.getCustomernumber());
		//第三步 判断请求是否成功，
		if(responseMap.containsKey("code")) {
			System.out.println(responseMap);
		}
		//第四步 解密同步响应密文data，获取明文参数
		String responseData	= responseMap.get("data");
		Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
		System.out.println("易宝的同步响应：" + responseMap);
		System.out.println("data解密后明文：" + responseDataMap);
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			System.out.println("code = " + responseDataMap.get("code") + "<br>");
			System.out.println("msg  = " + responseDataMap.get("msg"));
		}
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.QUERYORDERAPI_RESPONSE_HMAC_ORDER)) {
			System.out.println("<br>hmac check error!<br>");
		}
		//第七步 进行业务处理
		System.out.println(responseDataMap.toString());
		*/

		//确认担保
		//获取请求参数
		/*String orderrequestid	= "F20170914105146572";	
		Map<String, String> params	= new HashMap<String, String>();
		params.put("orderrequestid", orderrequestid);
		//第一步 生成密文data
		String data			= ZGTUtils.buildData(params, ZGTUtils.SETTLECONFIRMAPI_REQUEST_HMAC_ORDER);
		//第二步 发起请求
		String requestUrl	= ZGTUtils.getRequestUrl(ZGTUtils.SETTLECONFIRMAPI_NAME);
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		//第三步 判断请求是否成功，
		if(responseMap.containsKey("code")) {
			System.out.println(responseMap);
		}
		//第四步 解密同步响应密文data，获取明文参数
		String responseData	= responseMap.get("data");
		Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
		System.out.println("易宝的同步响应：" + responseMap);
		System.out.println("data解密后明文：" + responseDataMap);
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			System.out.println("code = " + responseDataMap.get("code") + "<br>");
			System.out.println("msg  = " + responseDataMap.get("msg"));
		}
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.SETTLECONFIRMAPI_RESPONSE_HMAC_ORDER)) {
			System.out.println("<br>hmac check error!<br>");
		}*/

	}
}

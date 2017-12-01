package com.dsj.common.utils.sms;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import org.apache.http.client.utils.HttpClientUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsj.common.utils.http.Http;

public class SMSUtil {
	private static final Logger logger = LoggerFactory.getLogger(SMSUtil.class);

/*	public static String sendSMS(String content, String phone)
			throws UnsupportedEncodingException {
		String returnStr = "";
		// String url = "http://103.193.161.210:8087/Service.asmx/sendsms";
		String url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend?";
		HttpClient httpClient = new HttpClient();
		httpClient.getParams().setBooleanParameter(
				"http.protocol.expect-continue", false);
		PostMethod getMethod = new PostMethod(url);
		String Content = java.net.URLEncoder.encode(content, "UTF-8");
		NameValuePair[] data = { new NameValuePair("UserID", "968680"),
				new NameValuePair("Account", "admin"),
				new NameValuePair("Password", "ZC835N"),
				new NameValuePair("Phones", phone),
				new NameValuePair("SendType", "1"),
				new NameValuePair("SendTime", ""),
				new NameValuePair("PostFixNumber", ""),
				new NameValuePair("Content", Content) };
		getMethod.setRequestBody(data);
		getMethod.addRequestHeader("Connection", "close");
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String str = new String(responseBody, "GBK");
			logger.info(str);
			if (str.contains("GBK")) {
				str = str.replaceAll("GBK", "utf-8");
			}
			int beginPoint = str.indexOf("<RetCode>");
			int endPoint = str.indexOf("</RetCode>");
			String result = "RetCode=";
			logger.debug(result + str.substring(beginPoint + 9, endPoint));
			logger.debug(str);
			returnStr = str.substring(beginPoint + 9, endPoint);
			// return getMethod.getResponseBodyAsString();
		}  catch (Exception e) {
			logger.error(e.getMessage(),e);
		} finally {
			getMethod.releaseConnection();
		}
		return returnStr;
	}*/
	

	// 下发
	public static String send(String url, String param) {
		String ret = "";
		url = url + "?" + param;
		System.out.println("【SDKHttpClient】发送MT到SDK->" + url);
		String responseString =Http.get(url, "", "utf-8");
		//String responseString = HttpClientUtil.sendGet(url);
		responseString = responseString.trim();
		if (null != responseString && !"".equals(responseString)) {
			ret = xmlMt(responseString);
		}
		return ret;
	}
	// 解析下发response
	public static String xmlMt(String response) {
		String result = "0";
		Document document = null;
		try {
			document = DocumentHelper.parseText(response);
		} catch (DocumentException e) {
			e.printStackTrace();
			result = "-250";
		}
		Element root = document.getRootElement();
		result = root.elementText("error");
		if (null == result || "".equals(result)) {
			result = "-250";
		}
		return result;
	}
	
	public static String sendSMS(String content, String phone){
		try {
			content = URLEncoder.encode(content,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String param = "cdkey=9SDK-EMY-0999-SBXUR&password=374707&phone="+phone+"&message="+content+"&addserial=&seqid=&smspriority=1";
		String url = "http://bj999.eucp.b2m.cn:8080/sdkproxy/sendsms.action";
		String result = SMSUtil.send(url, param);
		return result;
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		System.out.println(SMSUtil.sendSMS("【大搜家】有新的经纪人入住大搜家平台，姓名：张震江，电话：18610859041请进行审核！", "13071157020"));
	//Sucess
		//Faild
	}
	
}

package com.dsj.common.utils.http;
import java.net.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsj.common.utils.AuthEncryptUtils;
import com.dsj.common.utils.JsonTools;

import java.io.*;
/**
 * 
 * @author wyt
 *
 */
public class Http {
	static  Logger logger=LoggerFactory.getLogger(Http.class);
	public static String get( String url, String data, String encoding) {
		logger.info("开始网络请求{}",url);
		HttpURLConnection conn = null;
		InputStream in = null;
		InputStreamReader isr = null;
		OutputStream out = null;
		StringBuffer result = null;
		try {

			URL u = new URL(url);
			
			conn = (HttpURLConnection) u.openConnection();
			conn.setRequestProperty("Accept-Encoding", "gzip");
			conn.setRequestProperty("Content-Type", "utf-8");

			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setConnectTimeout(60000);
			conn.setReadTimeout(60000);
			int status = conn.getResponseCode();
			if (status == 200) {
				String enc = conn.getContentEncoding();
				result = new StringBuffer();
				in = conn.getInputStream();
				enc = conn.getContentEncoding();

				if (enc != null && enc.equals("gzip")) {
					// System.out.println("\n\t\t\t\t\t\t------using gzip------\n");
					java.util.zip.GZIPInputStream gzin = new java.util.zip.GZIPInputStream(in);
					isr = new InputStreamReader(gzin, "UTF-8");

				} else {
					isr = new InputStreamReader(in, "UTF-8");
					// System.out.println("\n\t\t\t\t\t\t------NO gzip------\n");
				}

				char[] c = new char[1024];
				int a = isr.read(c);
				while (a != -1) {
					result.append(new String(c, 0, a));
					a = isr.read(c);
				}
			} else {
//				System.out.println("http code = " + status);
				logger.warn("网络IO请求{}发生错误,内容：{},状态码：{}",url,data,status);

			}

		} catch (IOException e) {
			logger.warn("网络IO请求{}发生错误,内容：{},原因：{}",url,data,e.getMessage(),e);
		} finally {
			if (conn != null) {
				conn.disconnect();
			}
			try {
				if (in != null) {
					in.close();
				}
				if (isr != null) {
					isr.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		logger.info("开始网络请求{}，请求返回内容：{}",url,result);
		return result == null ? null : result + "";
	}
	
	public static Boolean existHttpPath(String httpPath){
		URL httpurl = null;
		try {
		httpurl = new URL(httpPath);
		URLConnection rulConnection = httpurl.openConnection();
		rulConnection.getInputStream();
		return true;
		} catch (Exception e) {
		return false;
		}
	}
	
	public static void main(String[] args) {
		String url ="http://apixt.cs.12366.com/api/product/delete.do";
		String ts1 = String.valueOf((new Date()).getTime());
		String key1 = "n3m662ypvrcocu2m";
		Map<String,Object> mapParam=new HashMap<String,Object>();
		
		mapParam.put("rId", "2");

	

		
		String jsonParamCode1=JsonTools.mapToJson(mapParam);
		String sign1 = DigestUtils.md5Hex(key1 + ts1+ String.valueOf(jsonParamCode1));
		
		url+="?sign="+sign1+"&ts="+ts1+"&system_code="+111+"&param="+jsonParamCode1;
		
		System.out.println(Http.get( url, "", "utf-8"));
	}
}

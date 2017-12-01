package com.dsj.data.web.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import net.sf.json.JSONObject;

public class LngLatUtil {
	
	public static String[] latLng(String name){
		String urlStr="http://api.map.baidu.com/geocoder/v2/?output=json"
				+ "&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka&address="+name;
		/** 网络的url地址 */
        URL url = null;
        /** 输入流 */
        BufferedReader in = null;
        StringBuffer sb = new StringBuffer();
        try {
        	 url = new URL(urlStr);
             in = new BufferedReader(new InputStreamReader(url.openStream(), "GBK"));
             String str = null;
             while ((str = in.readLine()) != null) {
                 sb.append(str);
             }
		} catch (Exception e) {
			
		}finally{
			try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
            }
		}
        String result1 = sb.toString();
        JSONObject jsStr = JSONObject.fromObject(result1);
        String status=jsStr.getString("status");
        Integer statu=Integer.parseInt(status);
        if(statu==0){
        	String result=jsStr.getString("result");
			JSONObject point = JSONObject.fromObject(result);
			String location=point.getString("location");
			JSONObject ccc = JSONObject.fromObject(location);
			String lng=ccc.getString("lng");
			String lat=ccc.getString("lat");
			String[] A =new String[2];
			A[0]=lng;
			A[1]=lat;
			return A;
        }else{
        	String[] A =new String[2];
        	return A;
        }
        
	}
}

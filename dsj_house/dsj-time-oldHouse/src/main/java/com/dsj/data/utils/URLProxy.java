package com.dsj.data.utils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;

public class URLProxy {

	private String proxyIp;
	private String url;
	private Integer port;

	public URLProxy(String url, String proxyIp, Integer port) {
		super();
		this.proxyIp = proxyIp;
		this.url = url;
		this.port = port;
	}

	InputStream getWebStream() {
		try {
			/* 构造Proxy对象，以适用于代理上网的方式 */
			InetSocketAddress socketAddress = new InetSocketAddress(InetAddress.getByName(proxyIp), port);

			Proxy proxy = new Proxy(Proxy.Type.HTTP, socketAddress);
			/* 构造url对象 */
			/* 测试是否能够打开连接，获得输入流，连接方式是直连方式 */
			 //InputStream inputStream=url.openConnection().getInputStream();
			/* 下面用代理的方式进行连接，需要构造Proxy对象 */
			
			URL myurl = new URL(url);   
			URLConnection myurlcon = myurl.openConnection(proxy); 
			myurlcon.setConnectTimeout(5*1000);   
			//myurlcon.setReadTimeout(10*1000);   
			InputStream input = myurlcon.getInputStream();
			
			if (input != null) {
				System.out.println("Connectioned");
			}
			return input;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}

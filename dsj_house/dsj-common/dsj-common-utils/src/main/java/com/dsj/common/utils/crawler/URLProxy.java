package com.dsj.common.utils.crawler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class URLProxy {
	private static final Logger logger = LoggerFactory.getLogger(URLProxy.class);
	private String proxyIp;
	private String url;
	private Integer port;

	public URLProxy(String url, String proxyIp, Integer port) {
		super();
		this.proxyIp = proxyIp;
		this.url = url;
		this.port = port;
	}
	
	
	public String webParseHtml() {
		String html = "";
		BrowserVersion[] versions = {BrowserVersion.INTERNET_EXPLORER_11, BrowserVersion.CHROME, BrowserVersion.FIREFOX_38, BrowserVersion.INTERNET_EXPLORER_8};
		WebClient client = new WebClient(versions[(int)(versions.length * Math.random())]);
		try {
			client.getOptions().setThrowExceptionOnFailingStatusCode(false);
			client.getOptions().setJavaScriptEnabled(false);
			client.getOptions().setCssEnabled(false);
			client.getOptions().setThrowExceptionOnScriptError(false);
			client.getOptions().setTimeout(3000);
			client.getOptions().setAppletEnabled(true);
			client.getOptions().setGeolocationEnabled(true);
			client.getOptions().setRedirectEnabled(true);
			
			//String ipport = getAProxy();
			
			ProxyConfig proxyConfig = new ProxyConfig(proxyIp, port);
			client.getOptions().setProxyConfig(proxyConfig);
		
		//	logger.info("开始解析："+url);
			HtmlPage page = client.getPage(this.url);
			html = page.asXml();
			
			//System.out.println(getName() + " 使用代理 " + ipport + "请求目标网址返回HTML：" + html);
			
		} catch (Exception e) {
			logger.error("超时：",e.getMessage(),e);
			
		} finally {
			client.close();
		}
		return html;
	}
	

}

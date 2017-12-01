package com.dsj.common.utils.crawler;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

public class DOMJsonpConfig {
	private static final Logger logger = LoggerFactory.getLogger(DOMJsonpConfig.class);
	 public static Document getDocByUrl(String url) throws IOException, SAXException{  
		 	logger.info("解析url:" + url+"开始");
			Document doc = Jsoup.parse(new URL(url).openStream(), "utf-8", url);// 编码根据爬取的页面的编码一样
	        return doc;
	 }
	 
	 public static Document getDocByPropUrl(String url,String ip,Integer prop) throws Exception{  
		 	logger.info("解析url:" + url+"开始");
		 	URLProxy proxy=new URLProxy(url,ip,prop);// 编码根据爬取的页面的编码一样
	        return Jsoup.parse(proxy.webParseHtml());
	 }
	 
	 
	 public static Document getDocByPropUrl(String url, CrawlerConfig config){
		
		String html=  config.new Crawler(14*1000, url,false, 4000).webParseHtml(url);
		return Jsoup.parse(html);
		
	 }
}

package com.dsj.common.utils.crawler;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrawlerPropxyUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(CrawlerPropxyUtils.class);
	
    public static String authHeader(String orderno, String secret, int timestamp){
        //拼装签名字符串
        String planText = String.format("orderno=%s,secret=%s,timestamp=%d", orderno, secret, timestamp);

        //计算签名
        String sign = org.apache.commons.codec.digest.DigestUtils.md5Hex(planText).toUpperCase();

        //拼装请求头Proxy-Authorization的值
        String authHeader = String.format("sign=%s&orderno=%s&timestamp=%d", sign, orderno, timestamp);
        return authHeader;
    }
    
    public static Document connect(String url){
    	final String ip = "forward.xdaili.cn";//这里以正式服务器ip地址为准
        final int port = 80;//这里以正式服务器端口地址为准

        int timestamp = (int) (new Date().getTime()/1000);
        //以下订单号，secret参数 须自行改动
        final String authHeader = authHeader("ZF201711286127culrD4", "3dcdc599cd964b42afac3d3558588387", timestamp);
        ExecutorService thread = Executors.newFixedThreadPool(1);
        Document doc = null ;
        try {
        	logger.info("解析url:{}",url);
			doc = Jsoup.connect(url)
			        .proxy(ip, port)
			        .validateTLSCertificates(false) //忽略证书认证,每种语言客户端都有类似的API
			        .header("Proxy-Authorization", authHeader)
			        .get();
		}  catch (IOException e) {
			logger.error("爬虫 connet error:",e);
			doc=connect(url);
        }
        
        return doc;
    }
}

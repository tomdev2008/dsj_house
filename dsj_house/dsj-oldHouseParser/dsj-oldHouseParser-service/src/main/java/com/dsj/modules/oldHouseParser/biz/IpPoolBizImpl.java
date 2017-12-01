package com.dsj.modules.oldHouseParser.biz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.modules.oldHouseParser.po.IpPoolPo;
import com.dsj.modules.oldHouseParser.service.IpPoolService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class IpPoolBizImpl  implements IpPoolBiz {
	private static final Logger logger = LoggerFactory.getLogger(IpPoolBizImpl.class);
	@Autowired
	IpPoolService ipPoolService;
	
	//处理ip
	@Override
	@Transactional(readOnly = true)
	public void dealIpPool(){

		 CrawlerConfig config=new CrawlerConfig();
		 new Thread(config.new GetIP(14*1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620")).start();
		 
		 while(true)  {
			 System.out.println(true);
			 try{
				 if(config.ipList.size()>0){
					 for(String ips:config.ipList){
						 String[] ipPort=ips.split(":");
						 createIPAddress(ipPort[0],Integer.parseInt(ipPort[1]));
						 
						 Map<String,Object> paramMap=new HashMap<String,Object>();
						 paramMap.put("ip", ipPort[0]);
						 paramMap.put("port", ipPort[1]);
						 IpPoolPo hasIp=ipPoolService.getBy(paramMap);
						 if(hasIp==null){
							 IpPoolPo ip=new IpPoolPo();
							 ip.setCreateTime(new Date());
							 ip.setIp(ipPort[0]);
							 ip.setPort(ipPort[1]);
							 ip.setTime(3000l);
							 ipPoolService.saveDynamic(ip);
						 }
					 }
				 }
			 }catch(Exception e){
				 e.printStackTrace();
				 logger.error("获取ip错误：",e);
			 }
		 }
		 
	}
	
	public static void createIPAddress(String ip, int port) {
		URL url = null;
		try {
			url = new URL("https://bj.lianjia.com");
		} catch (MalformedURLException e) {
			System.out.println("url invalidate");
		}
		InetSocketAddress addr = null;
		addr = new InetSocketAddress(ip, port);
		Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http proxy
		InputStream in = null;
		try {
			URLConnection conn = url.openConnection(proxy);
			conn.setConnectTimeout(3000);
			conn.setReadTimeout(3000);
			in = conn.getInputStream();
		} catch (Exception e) {
			logger.error("ip " + ip + " is not aviable");// 异常IP
		}
		String s = convertStreamToString(in);
		System.out.println(s);
		// System.out.println(s);
		if (s.indexOf("lianjia") > 0) {// 有效IP
			logger.info(ip + ":" + port + " is ok");
		}
	}

	public static String convertStreamToString(InputStream is) {
		if (is == null)
			return "";
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "/n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();

	}

}
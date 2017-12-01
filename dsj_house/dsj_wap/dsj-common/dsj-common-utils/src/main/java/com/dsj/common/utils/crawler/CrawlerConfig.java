package com.dsj.common.utils.crawler;

	
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.ProxyConfig;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class CrawlerConfig {
	private static final Logger logger = LoggerFactory.getLogger(CrawlerConfig.class);
	public static List<String> ipList = new ArrayList<>();
	public static boolean gameOver = false;
	
    
	// 抓取IP138，检测IP
	public class Crawler {
		
		long sleepMs = 200;
		boolean useJs = false;
		String targetUrl = "";
		int timeOut = 5000;
		
		public Crawler(long sleepMs, String targetUrl, boolean useJs, int timeOut) {
			this.sleepMs = sleepMs;
			this.targetUrl = targetUrl;
			this.useJs = useJs;
			this.timeOut = timeOut;
		}
		public String webParseHtml(String url) {
			String html = "";
			BrowserVersion[] versions = {BrowserVersion.INTERNET_EXPLORER_11, BrowserVersion.CHROME, BrowserVersion.FIREFOX_38, BrowserVersion.INTERNET_EXPLORER_8};
			WebClient client = new WebClient(versions[(int)(versions.length * Math.random())]);
			try {
				client.getOptions().setThrowExceptionOnFailingStatusCode(false);
				client.getOptions().setJavaScriptEnabled(useJs);
				client.getOptions().setCssEnabled(false);
				client.getOptions().setThrowExceptionOnScriptError(false);
				client.getOptions().setTimeout(timeOut);
				client.getOptions().setAppletEnabled(true);
				client.getOptions().setGeolocationEnabled(true);
				client.getOptions().setRedirectEnabled(true);
				
				String ipport = getAProxy();
				if (ipport != null) {
					ProxyConfig proxyConfig = new ProxyConfig(ipport.split(":")[0], Integer.parseInt(ipport.split(":")[1]));
					client.getOptions().setProxyConfig(proxyConfig);
				}else {
					System.out.print(".");
					return "";
				}
				logger.info("开始解析："+url);
				HtmlPage page = client.getPage(url);
				html = page.asXml();
				
				//System.out.println(getName() + " 使用代理 " + ipport + "请求目标网址返回HTML：" + html);
				
			} catch (Exception e) {
				logger.error("超时：",e.getMessage(),e);
				return webParseHtml(url);
			
			} finally {
				client.close();
			}
			return html;
		}
		
	    private String getAProxy() {
	    	if (ipList.size() > 0) {
	    		String ip = ipList.get((int)(Math.random() * ipList.size()));
	    		return ip ;
			}
			return null;
		}
	}
	
	// 定时获取动态IP
	public class GetIP implements Runnable{
		long sleepMs = 1000;
		int maxTime = 3;
		String order = "";
		
		public GetIP(long sleepMs, int maxTime, String order) {
			this.sleepMs = sleepMs;
			this.maxTime = maxTime;
			this.order = order;
		}
		
		@Override
		public void run() {
			long getIpTime = 0;
			int time = 1;
			while(!gameOver){
				/*if(time >= maxTime){
					gameOver = true;
					break;
				}*/
				try {
					java.net.URL url = new java.net.URL("http://api.ip.data5u.com/dynamic/get.html?order=" + order + "&ttl");
			    	HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			    	connection.setConnectTimeout(3000);
			    	connection = (HttpURLConnection)url.openConnection();
			    	
			        InputStream raw = connection.getInputStream();  
			        InputStream in = new BufferedInputStream(raw);  
			        byte[] data = new byte[in.available()];
			        int bytesRead = 0;  
			        int offset = 0;  
			        while(offset < data.length) {  
			            bytesRead = in.read(data, offset, data.length - offset);  
			            if(bytesRead == -1) {  
			                break;  
			            }  
			            offset += bytesRead;  
			        }  
			        in.close();  
			        raw.close();
					String[] res = new String(data, "UTF-8").split("\n");
					List<String> ipList = new ArrayList<>();
					for (String ip : res) {
						try {
							String[] parts = ip.split(",");
							if (Integer.parseInt(parts[1]) > 0) {
								System.out.println(parts[0]);
								ipList.add(parts[0]);
							}
						} catch (Exception e) {
						}
					}
					if (ipList.size() > 0) {
						CrawlerConfig.ipList = ipList;
						System.out.println("第" + ++getIpTime + "次获取动态IP " + ipList.size() + " 个");
						time += 1;
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.err.println(">>>>>>>>>>>>>>获取IP出错");
				}
				try {
					Thread.sleep(sleepMs);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}


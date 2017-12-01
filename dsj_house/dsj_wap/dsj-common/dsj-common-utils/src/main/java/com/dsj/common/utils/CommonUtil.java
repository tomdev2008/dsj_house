package com.dsj.common.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.util.Random;

/**
 * 通用工具类
 * @author
 */
public class CommonUtil {
	
	private static int socketTimeout = 10000;

	private static int connectTimeout = 30000;

	private static RequestConfig requestConfig;

	private static CloseableHttpClient httpClient;
	
	public final static int RANDOM_LENGTH = 17;
    /**
     * 短信发送计时cookie的名字
     */
    public static final String DX_TIME="28A46108DFF6E0619A74AD0B67ADB3BF";
    /**
     * 短信cookie的名字
     */
    public static final String DX_NAME="4D8C2BCE33C853B9885C6F9A21884E37";

	/**
	 * 通过Https往API post xml数据
	 * 
	 * @param url
	 *            API地址
	 * @param xmlObj
	 *            要提交的XML数据对象
	 * @return API回包的实际数据
	 * @throws IOException
	 * @throws KeyStoreException
	 * @throws UnrecoverableKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String httpsRequest(String requestUrl, String xmlObj)
			throws KeyStoreException, IOException, KeyManagementException,
			UnrecoverableKeyException, NoSuchAlgorithmException {
		httpClient = HttpClients.custom().build();
		// 根据默认超时限制初始化requestConfig
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
		String result = null;
		HttpPost httpPost = new HttpPost(requestUrl);
		// 得指明使用UTF-8编码，否则到API服务器XML的中文不能被成功识别
		
		StringEntity postEntity = new StringEntity(xmlObj, "UTF-8");
		httpPost.addHeader("Content-Type", "text/xml");
		httpPost.setEntity(postEntity);
		// 设置请求器的配置
		httpPost.setConfig(requestConfig);
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			result = EntityUtils.toString(entity, "UTF-8");
		} catch (ConnectionPoolTimeoutException e) {
			e.printStackTrace();
		} catch (ConnectTimeoutException e) {
			e.printStackTrace();
		} catch (SocketTimeoutException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpPost.abort();
		}
		return result;
	}

	public static String urlEncodeUTF8(String source) {
		String result = source;
		try {
			result = java.net.URLEncoder.encode(source, "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}

	@SuppressWarnings("unused")
	private void resetRequestConfig() {
		requestConfig = RequestConfig.custom().setSocketTimeout(socketTimeout).setConnectTimeout(connectTimeout).build();
	}
	/**
	 * 
	 * @功能：生成一个4位随机数
	 * @作者: zgz
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2015-12-10 上午10:27:01
	 */
	public static int create4num() {
		int max = 9999;
		int min = 1000;
		Random random = new Random();

		int s = random.nextInt(max) % (max - min + 1) + min;
		return s;
	}
	/**
	 * 
	 * @功能：生成一个随机字符串
	 * @作者: zgz
	 * @参数： @param length 生成的长度
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2015-12-16 上午11:44:25
	 */
	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @功能：加密文
	 * @作者: zgz
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2015-12-16 下午12:59:04
	 */
	public static String getEnCiphertext(String text) throws UnsupportedEncodingException {
		return getRandomString(RANDOM_LENGTH)
				+ Base64SecurityUtil.getEncryptString(text).replaceAll("\r\n","");
	}
	/**
	 * 
	 * @throws UnsupportedEncodingException 
	 * @功能：解密文
	 * @作者: zgz
	 * @参数： @return
	 * @返回值：String
	 * @日期: 2015-12-16 下午12:59:08
	 */
	public static String getDnCiphertext(String text) throws UnsupportedEncodingException {

		return Base64SecurityUtil.getDecryptString(text.substring(CommonUtil.RANDOM_LENGTH)).replaceAll("\r\n","");
	}
}
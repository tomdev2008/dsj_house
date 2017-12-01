package com.dsj.data.web.utils.payUtil;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;

/**
 * 基本的工具操作类
 * @author chengchao.zhou
 *
 */

public class Util {

	/**
	 * 签名算法
	 * @param data
	 * @param key
	 * @return
	 */
	public static String sign(Map<String, String> data,String key){
		System.out.println(data.toString());
		String resp = "";
		String unsignString = "";
		List<String> nameList = new ArrayList<String>(data.keySet());
		//首先按字段名的字典序排列
		Collections.sort(nameList);
		for (String name : nameList) {
			String value = data.get(name);
			if (value != null && !"".equals(value)) {
				unsignString +=name+"="+value+"&";
			}
		}
		//后面加上key然后md5签名
		unsignString += "key="+key;
        System.out.println("unsignString===="+unsignString);
		try {
			resp = md5(unsignString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		resp = resp.toUpperCase();
		return resp;
	}
	
	/**
	 * 验证返回的字符串的签名并解析为Map对象
	 * @param resp
	 * @param key
	 * @return
	 */
	public static Map<String, String> checkSignAndParseResult(String resp,String key){
		
		try{
            List<String> nameList=new ArrayList<String>();
            Document document = new SAXReader().read(new StringReader(resp));
            Element root= document.getRootElement();
            String sign="";
            String text="";
            Map<String, String> result = new HashMap<String, String>();
            for(Object o : root.elements()){
                Element element=(Element)o;
                String name = element.getName();
                if("sign".equals(name)){
                    sign = element.getText();
                }else {
                    nameList.add(name);
                }
                result.put(name, element.getText());
                //System.out.println(name +" " + element.getText());
            }
            Collections.sort(nameList);
            for(String name:nameList){
                String value=root.element(name).getText();
                if(value!=null && !"".equals(value))
                text+=name+"="+value+"&";
            }
            text+="key="+key;
            System.out.println("text===="+text);
            String sign2=md5(text);
            sign2=sign2.toUpperCase();
            if(sign.equals(sign2)){
            	return result;
            }else{
            	result = null;
            	return null;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
		return null;
	}
	
	/**
	 * md5 签名
	 * @param value
	 * @return
	 * @throws Exception
	 */
	public static String md5(String value) throws Exception{
		MessageDigest mdInst = MessageDigest.getInstance("MD5");
        mdInst.update(value.getBytes("UTF-8"));
        byte[] arr=mdInst.digest();
        StringBuffer sb = new StringBuffer();  
        for (int i = 0; i < arr.length; ++i) {
            sb.append(Integer.toHexString((arr[i] & 0xFF) | 0x100).substring(1,3));  
        }  
        return sb.toString();
	}
	
	/**
	 * 以Http的方式请求服务器
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            请求参数
	 * @return
	 * @throws Exception
	 */
	public static String sendToServer(String url, Map<String, Object> params) throws Exception {
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> formParams = new ArrayList<NameValuePair>();
		// 设置参数
		for (String key : params.keySet()) {
			BasicNameValuePair param = new BasicNameValuePair(key,
					(String) params.get(key));
			formParams.add(param);
		}
		UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams,
				"UTF-8");
		post.setEntity(entity);
		
		HttpResponse response;
		int statusCode;
		BufferedReader in = null;
		InputStream instream = null;
		try {
			response = client.execute(post);
			statusCode = response.getStatusLine().getStatusCode();
			
			if (statusCode == 200) {
				instream = response.getEntity().getContent();
				in = new BufferedReader(new InputStreamReader(
						instream, "utf-8"));
				StringBuffer buffer = new StringBuffer();
				String line = "";
				while ((line = in.readLine()) != null) {
					buffer.append(line);
				}
				return buffer.toString();
			} else {
				throw new Exception(url + "不能访问.返回HTTP-" + statusCode + "错误请求");
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			throw new Exception(e);
		}finally {
			if(in != null){
				in.close();
			}
			if(instream != null){
				instream.close();
			}
			post.releaseConnection();
			//System.out.println("关闭流，释放链接");
		}
	}
	
	/**
	 * 将十六进制字符串转为字节数组
	 * @param s
	 * @return
	 */
	public static byte[] hex2byte(String s){
		byte[] src = s.toLowerCase().getBytes();
        byte[] ret = new byte[src.length / 2];
        for (int i = 0; i < src.length; i += 2) {
            byte hi = src[i];
            byte low = src[i + 1];
            hi = (byte) ((hi >= 'a' && hi <= 'f') ? 0x0a + (hi - 'a')
                    : hi - '0');
            low = (byte) ((low >= 'a' && low <= 'f') ? 0x0a + (low - 'a')
                    : low - '0');
            ret[i / 2] = (byte) (hi << 4 | low);
        }
        return ret;
	}
		
	/**
	 * 将十六进制字符串保存为图片
	 * @param content
	 * @param fp
	 * @throws IOException
	 */
	public static void decodeHexImage(String content,String fp) throws IOException{
		byte[] bs = hex2byte(content);
		ByteArrayInputStream in = new ByteArrayInputStream(bs);
        BufferedImage image = ImageIO.read(in);
        //输出
        ImageIO.write(image,"png",new File(fp));
        in.close();
	}
	
	/**
	 * 根据图片路径获取其base64形式字符串 （去掉回车、换行字符）
	 * @param fp
	 * @return
	 * @throws IOException
	 */
	public static String getImageBase64Str(String fp) throws IOException{
		File file = new File(fp);
		BufferedImage bi = ImageIO.read(file);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ImageIO.write(bi, "png", bos);
		byte[] bytes = bos.toByteArray();
		bos.close();
		String strs = new BASE64Encoder().encodeBuffer(bytes);
		StringBuilder sb = new StringBuilder(strs.length());
		for(int i=0;i< strs.length();i++){
			if(strs.charAt(i) != '\r' && strs.charAt(i) != '\n'){
				sb.append(strs.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 图片的十六进制字符串转为base64形式的字符串（去掉回车、换行字符）
	 * @param src
	 * @return
	 */
	public static String hexStr2Base64Str(String src){
		byte[] bytes = hex2byte(src);
		String strs = new BASE64Encoder().encodeBuffer(bytes);
		StringBuilder sb = new StringBuilder(strs.length());
		for(int i=0;i< strs.length();i++){
			if(strs.charAt(i) != '\r' && strs.charAt(i) != '\n'){
				sb.append(strs.charAt(i));
			}
		}
		return sb.toString();
	}
	
	/**
	 * 将base64形式的图片字符串保存为图片
	 * @param str
	 * @param fp
	 * @throws IOException
	 */
	public static void base64Str2Image(String str,String fp) throws IOException{
		byte[] bytes = new BASE64Decoder().decodeBuffer(str);
		BufferedImage bi1 =ImageIO.read(new ByteArrayInputStream(bytes));
		File file = new File(fp);
		ImageIO.write(bi1, "png", file);
	}
	
	/**
	 * 获取当前系统的时间戳序列
	 * @return
	 */
	public static String getTimeStamp(){
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}

	/**
	 * 将xml字符串解析为对应的Map
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Map<String, String> parseXML2Map(String xml) throws DocumentException{
		Map<String, String> map = new HashMap<String,String>();
		Document document = new SAXReader().read(new StringReader(xml));
        Element root= document.getRootElement();
        for(Object o : root.elements()){
            Element element=(Element)o;
            String name = element.getName();
            map.put(name, element.getText());
        }
        return map;
	}
	
}

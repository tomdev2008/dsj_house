package com.dsj.data.web.weixin;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.system.enums.UserStatus;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.UserService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * QQ登录
 * @author dsj
 *
 */
@Controller
@RequestMapping(value = "QQLogin")
public class QQLogin {
	@Autowired
	private UserService userService;
	private final Logger LOGGER = LoggerFactory.getLogger(WeixinLogin.class);
	public static final String APP_ID = "101432477"; //AppID(应用ID)
	private static final String APP_SECRET = "245b88d9dae8905ec5708850d64635f5"; //AppSecret(应用密钥)
	private static final String REDIRECT_URL="http%3a%2f%2fwangxl.tunnel.echomod.cn%2fdsj-front%2fQQLogin%2fauth";
   
	/**
     * 生成用于获取access_token的Code的Url
     *
     * @param redirectUrl
     * @return
	 * @throws IOException 
     */
	@RequestMapping("getRequestCodeUrl")
    public void getRequestCodeUrl(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.sendRedirect("https://graph.qq.com/oauth2.0/authorize?response_type=code&client_id="
				   + APP_ID
				   + "&redirect_uri="
				   + REDIRECT_URL 
				   + "&scope=get_user_info&state=test");
	}
	
	/**
     * 路由控制
     * 
     * @param request
     * @param code
     * @return 
     */
	@RequestMapping("auth")
	public String auth(String code,HttpServletRequest request){
		 Map<String, String> data = new HashMap();
	        Map<String, String> result = getUserInfoAccessToken(code);//通过这个code获取access_token
	        String openId = result.get("openid");
        	LOGGER.info("try getting user info. [openid={}]", openId);
        	Map<String, String> userInfo =getUserInfo(result.get("access_token"), openId);//使用access_token获取用户信息
        	UserPo userPo=userService.selectUser(openId,UserStatus.QQ.getValue());
        	Long userId=null;
        	if(userPo==null){
        		userPo=new UserPo();
        		userPo.setCreateTime(new Date());
        		userPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
        		userPo.setOpenId(openId);
        		userPo.setType(UserStatus.QQ.getValue());
        		userPo.setUpdateTime(new Date());
        		userPo.setUsername(userInfo.get("nickname"));
        		userPo.setUserType(UserType.MEMBER.getValue());
        	    userId=userService.saveDynamic(userPo);
        	}
        		 HttpSession session = request.getSession();
        		 if(userId!=null){
        			 userPo.setImPassword(userService.getById(userId).getImPassword());
        	     }
        	     session.setAttribute(BusinessConst.PC_USER_SIESSION,userPo);
        	     String url="";
        	       // 取出session中登陆跳转地址
        	       Object obj = session.getAttribute(CommConst.COOKIE_PC_LOGIN_URL);
        	       String path = request.getContextPath();
        	  
        	       if(obj == null){
        	    	   url=path+"/";
        	       } else {
        	    	   url = (String)obj;
        	    	   session.setAttribute(CommConst.COOKIE_PC_LOGIN_URL, null);
        	       }
	        return "redirect:"+url;
	}
	
	/**
     * 获取请求用户信息的access_token
     *
     * @param code
     * @return
     */
	@RequestMapping("getUserInfoAccessToken")
	@ResponseBody
	public Map<String, String> getUserInfoAccessToken(String code){
		    JsonObject object = null;
	        Map<String, String> data = new HashMap();
	        try {
	            String url="https://graph.qq.com/oauth2.0/token?grant_type=authorization_code&client_id="
	 				   + APP_ID
					   + "&client_secret="
					   + APP_SECRET 
					   + "&code="
					   + code
					   +"&redirect_uri="
					   +REDIRECT_URL;
	            LOGGER.info("request accessToken from url: {}", url);
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(url);
	            System.err.println(httpGet);
	            HttpResponse httpResponse = httpClient.execute(httpGet);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            String tokens = EntityUtils.toString(httpEntity, "utf-8");
	            String[] token=tokens.split("&");
	            String[] access=token[0].split("=");
	            String accessToken=access[1];
	            String urls="https://graph.qq.com/oauth2.0/me?callback=callback&access_token="+accessToken;
	            DefaultHttpClient httpClients = new DefaultHttpClient();
	            HttpGet httpGets = new HttpGet(urls);
	            System.err.println(httpGets);
	            HttpResponse httpResponses = httpClient.execute(httpGets);
	            HttpEntity httpEntitys = httpResponses.getEntity();
	            String toke = EntityUtils.toString(httpEntitys, "utf-8");
	            String st2 = toke.substring(toke.indexOf("(")+1,toke.indexOf(")")); 
	            JSONObject  myJson = new JSONObject();
	            JSONObject of=myJson.parseObject(st2);
	            String openId=of.getString("openid");
	            data.put("openid", openId);
	            data.put("access_token", accessToken);
	        } catch (Exception ex) {
	        	LOGGER.error("fail to request wechat access token. [error={}]", ex);
	        }
	        return data;
	}
	
	/**
     * 获取用户信息
     *
     * @param accessToken
     * @param openId
     * @return
     */
    public Map<String, String> getUserInfo(String accessToken, String openId) {
        Map<String, String> data = new HashMap();
        String url = "https://graph.qq.com/user/get_user_info?access_token="+accessToken+"&oauth_consumer_key="+APP_ID+"&openid="+openId;
        LOGGER.info("request user info from url: {}", url);
        JsonObject userInfo = null;
        try {
            DefaultHttpClient httpClient = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            HttpResponse httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            String response = EntityUtils.toString(httpEntity, "utf-8");
            Gson token_gson = new Gson();
            userInfo = token_gson.fromJson(response, JsonObject.class);
            LOGGER.info("get userinfo success. [result={}]", userInfo);
            data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
        	LOGGER.error("fail to request wechat user info. [error={}]", ex);
        }
        return data;
    }
}

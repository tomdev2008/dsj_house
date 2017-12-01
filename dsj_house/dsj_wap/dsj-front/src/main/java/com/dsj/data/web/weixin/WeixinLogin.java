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
 * 微信登录
 * @author dsj
 *
 */
@Controller
@RequestMapping(value = "weixin")
public class WeixinLogin {
	@Autowired
	private UserService userService;
	private final Logger LOGGER = LoggerFactory.getLogger(WeixinLogin.class);
	public static final String APP_ID = "wx0f95f76a22f1ce3c"; //AppID(应用ID)
	private static final String APP_SECRET = "a7384e106013526c71fc1efc4d2bc7bd"; //AppSecret(应用密钥)
	private static final String REDIRECT_URL="http%3A%2F%2Fwangxl.tunnel.echomod.cn%2Fdsj-front%2Fweixin%2Fauth";
	/**
     * 生成用于获取access_token的Code的Url
     *
     * @param redirectUrl
     * @return
	 * @throws IOException 
     */
	@RequestMapping("getRequestCodeUrl")
    public void getRequestCodeUrl(HttpServletResponse response,HttpServletRequest request) throws IOException {
		response.sendRedirect("https://open.weixin.qq.com/connect/qrconnect?appid="
				   + APP_ID
				   + "&redirect_uri="
				   + REDIRECT_URL 
				   + "&response_type=code&scope=snsapi_login&state=66666#wechat_redirect");
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
        	UserPo userPo=userService.selectUser(openId,UserStatus.WEIXIN.getValue());
        	Long userId=null;
        	if(userPo==null){
        		userPo=new UserPo();
        		userPo.setCreateTime(new Date());
        		userPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
        		userPo.setOpenId(openId);
        		userPo.setType(UserStatus.WEIXIN.getValue());
        		userPo.setUpdateTime(new Date());
        		userPo.setAvatar(userInfo.get("headimgurl"));
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
	            String url="https://api.weixin.qq.com/sns/oauth2/access_token?appid="
	 				   + APP_ID
					   + "&secret="
					   + APP_SECRET 
					   + "&code="
					   + code
					   +"&grant_type=authorization_code";
	            LOGGER.info("request accessToken from url: {}", url);
	            DefaultHttpClient httpClient = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(url);
	            System.err.println(httpGet);
	            HttpResponse httpResponse = httpClient.execute(httpGet);
	            HttpEntity httpEntity = httpResponse.getEntity();
	            String tokens = EntityUtils.toString(httpEntity, "utf-8");
	            Gson token_gson = new Gson();
	            object = token_gson.fromJson(tokens, JsonObject.class);
	            LOGGER.info("request accessToken success. [result={}]", object);
	            data.put("openid", object.get("openid").toString().replaceAll("\"", ""));
	            data.put("access_token", object.get("access_token").toString().replaceAll("\"", ""));
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
        String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" + accessToken + "&openid=" + openId + "&lang=zh_CN";
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
            data.put("openid", userInfo.get("openid").toString().replaceAll("\"", ""));
            data.put("nickname", userInfo.get("nickname").toString().replaceAll("\"", ""));
            data.put("city", userInfo.get("city").toString().replaceAll("\"", ""));
            data.put("province", userInfo.get("province").toString().replaceAll("\"", ""));
            data.put("country", userInfo.get("country").toString().replaceAll("\"", ""));
            data.put("headimgurl", userInfo.get("headimgurl").toString().replaceAll("\"", ""));
        } catch (Exception ex) {
        	LOGGER.error("fail to request wechat user info. [error={}]", ex);
        }
        return data;
    }
     
}

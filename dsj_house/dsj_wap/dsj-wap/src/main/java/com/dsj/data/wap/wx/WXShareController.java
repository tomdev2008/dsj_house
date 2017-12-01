package com.dsj.data.wap.wx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import com.dsj.common.utils.json.JsonMapper;
import com.dsj.data.web.utils.Sign;

@Controller
@RequestMapping("weixinIndex")
public class WXShareController {

	private static final Logger LOGGER = LoggerFactory.getLogger(WXShareController.class);
	
	private RestTemplate httpClientRestTemplate = new RestTemplate();
	
	/**
     * 
     * @throws IOException 
     * @功能：获取用来分享的顺利办微信信息
     * @作者：liumin
     * @参数： 
     * @返回值：String
     * @日期: 2016-7-4 下午8:05:40
     */
	@RequestMapping("getSLBWXInfoForShare")
	@ResponseBody
	public Map<String, String> getSLBWXInfoForShare(String url) {
		url.replace("?from=singlemessage&isappinstalled=0", "");
    	Map<String, String> sign = sign(url);
    	return sign;
    }
    
    
    @SuppressWarnings("unchecked")
	public Map<String, String> sign(String url) {
		LOGGER.info("share sign start:{}", url );
		Map<String, String> sign = new HashMap<String, String>();
		 String nonceStr =  "44e98c4fa5cb5c85dfff1a178673ec97";
         String appId = "wxf750030c2d7f3ee2";
			// 微信分享签名
			String refreshToken = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appId +"&secret=" + nonceStr;
			String forObject = httpClientRestTemplate.getForObject(refreshToken, String.class, new HashMap<String, Object>());
			LOGGER.info("wei xin rollback data: {}", forObject);
			JsonMapper jsonMapper = JsonMapper.nonDefaultMapper();
			Map<String, Object> fromJson = jsonMapper.fromJson(forObject, Map.class);
			Object object = fromJson.get("access_token");
			if (object != null) {
				String accessToken = (String)object;
				LOGGER.info("weixin share get accessToken:", accessToken);
				String getTicket = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="+ accessToken +"&type=jsapi";
				forObject = httpClientRestTemplate.getForObject(getTicket, String.class, new HashMap<String, Object>());
				fromJson = jsonMapper.fromJson(forObject, Map.class);
				String errmsg = (String)fromJson.get("errmsg");
				if (errmsg.equals("ok")) {
					String ticket = (String)fromJson.get("ticket");
					LOGGER.info("weixin share get ticket:{}", ticket);
					sign = Sign.sign(ticket, url);
					sign.put("appId", appId);
					LOGGER.info("weixin share sign end:{}", sign);
				}
			}
		return sign;
	}
    
	
}

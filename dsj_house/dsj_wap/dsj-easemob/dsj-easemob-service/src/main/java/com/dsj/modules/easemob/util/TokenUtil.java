package com.dsj.modules.easemob.util;

import com.dsj.common.utils.spring.ConfigUtils;
import com.google.gson.Gson;

import io.swagger.client.ApiException;
import io.swagger.client.api.AuthenticationApi;
import io.swagger.client.model.Token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
*
* @描述: 获取环信服务token.
* @作者: wangjl.
* @创建时间: 2017-07-27 14:17:27.
* @版本: 1.0 .
*/
@Component
public class TokenUtil implements InitializingBean {

	@Value("${GRANT_TYPE}")
	private String GRANT_TYPE;
	
	@Value("${CLIENT_ID}")
    private String CLIENT_ID;
	
	@Value("${CLIENT_SECRET}")
    private String CLIENT_SECRET;
	
    private Token BODY;
    private String ACCESS_TOKEN;
    private Double EXPIREDAT = -1D;
    
    private static final Logger logger 
    	= LoggerFactory.getLogger(TokenUtil.class);
    
    @Autowired
	public static TokenUtil instance = new TokenUtil();
    
    private static AuthenticationApi API = new AuthenticationApi();

    public void initTokenByProp() {
        String resp = null;
        try {
        	BODY = new Token().clientId(CLIENT_ID).grantType(GRANT_TYPE)
        			.clientSecret(CLIENT_SECRET);
            resp = API.orgNameAppNameTokenPost(OrgInfo.instance.getORG_NAME(), 
            		OrgInfo.instance.getAPP_NAME(), BODY);
        } catch (ApiException e) {
            logger.error(e.getMessage());
        }
        Gson gson = new Gson();
        Map map = gson.fromJson(resp, Map.class);
        ACCESS_TOKEN = " Bearer " + map.get("access_token");
        EXPIREDAT = System.currentTimeMillis() + (Double) map.get("expires_in");
    }

    public String getAccessToken() {
        if (ACCESS_TOKEN == null || isExpired()) {
            initTokenByProp();
        }
        return ACCESS_TOKEN;
    }

    private Boolean isExpired() {
        return System.currentTimeMillis() > EXPIREDAT;
    }

	public String getGRANT_TYPE() {
		return GRANT_TYPE;
	}

	public void setGRANT_TYPE(String gRANT_TYPE) {
		GRANT_TYPE = gRANT_TYPE;
	}

	public String getCLIENT_ID() {
		return CLIENT_ID;
	}

	public void setCLIENT_ID(String cLIENT_ID) {
		CLIENT_ID = cLIENT_ID;
	}

	public String getCLIENT_SECRET() {
		return CLIENT_SECRET;
	}

	public void setCLIENT_SECRET(String cLIENT_SECRET) {
		CLIENT_SECRET = cLIENT_SECRET;
	}

	public Token getBODY() {
		return BODY;
	}

	public void setBODY(Token bODY) {
		BODY = bODY;
	}

	public String getACCESS_TOKEN() {
		return ACCESS_TOKEN;
	}

	public void setACCESS_TOKEN(String aCCESS_TOKEN) {
		ACCESS_TOKEN = aCCESS_TOKEN;
	}

	public Double getEXPIREDAT() {
		return EXPIREDAT;
	}

	public void setEXPIREDAT(Double eXPIREDAT) {
		EXPIREDAT = eXPIREDAT;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		TokenUtil.instance = this;
	}
    
}


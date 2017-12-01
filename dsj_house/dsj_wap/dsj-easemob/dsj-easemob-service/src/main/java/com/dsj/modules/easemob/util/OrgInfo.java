package com.dsj.modules.easemob.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class OrgInfo implements InitializingBean {
	
	@Value("${ORG_NAME}")
    private String ORG_NAME;
	
	@Value("${APP_NAME}")
	private String APP_NAME;
	
	@Autowired
	public static OrgInfo instance = new OrgInfo();

	public String getORG_NAME() {
		return ORG_NAME;
	}

	public void setORG_NAME(String oRG_NAME) {
		ORG_NAME = oRG_NAME;
	}

	public String getAPP_NAME() {
		return APP_NAME;
	}

	public void setAPP_NAME(String aPP_NAME) {
		APP_NAME = aPP_NAME;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		OrgInfo.instance = this;
	}
    
}

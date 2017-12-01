package com.dsj.modules.easemob.util;

import io.swagger.client.ApiException;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class ResponseHandler {
	
    private static final Logger logger 
    	= LoggerFactory.getLogger(ResponseHandler.class);

    public Object handle(EasemobAPI easemobAPI) {
        Object result = null;
        try {
            result = easemobAPI.invokeEasemobAPI();
        } catch (ApiException e) {
            if (e.getCode() == 401) {
            	logger.info("The current token is invalid, "
            			+ "re-generating token for you and calling it again");
                TokenUtil.instance.initTokenByProp();
                try {
                    result = easemobAPI.invokeEasemobAPI();
                } catch (ApiException e1) {
                	logger.error(e1.getMessage());
                	result = "{\"errorMessage\":\"" + e1.getMessage() + "\"}";
                }
            } else if (e.getCode() == 429) {
            	logger.warn("The api call is too frequent");
            	result = "{\"errorMessage\":\"The api call is too frequent\"}";
            } else if (e.getCode() >= 500) {
            	logger.info("The server connection failed "
            			+ "and is being reconnected");
                result = retry(easemobAPI);
                if (result == null) {
                	result = "{\"errorMessage\":\"The server connection failed "
                			+ "and is being reconnected\"}";
                }
                System.out.println(e);
                logger.error(e + "\nThe server may be faulty."
                		+ "Please try again later");
            } else {
	            Gson gson = new Gson();
	            Map<String, String> map = gson.fromJson(e.getResponseBody(), 
	            		Map.class);
	            logger.error("error_code:{} error_msg:{} error_desc:{}", 
	            		e.getCode(), e.getMessage(), 
	            		map.get("error_description"));
	            result = "{\"errorMessage\":\"" + map.get("error_description")
	            		+ "\"}";
            }
        }
        return result;
    }

    public Object retry(EasemobAPI easemobAPI) {
        Object result = null;
        long time = 5;
        for (int i = 0; i < 3; i++) {
            try {
                TimeUnit.SECONDS.sleep(time);
                logger.info("Reconnection is in progress..." + i);
                result = easemobAPI.invokeEasemobAPI();
                if (result != null) {
                    return result;
                }
            } catch (ApiException e1) {
                time *= 3;
            } catch (InterruptedException e1) {
            	logger.error(e1.getMessage());
            }
        }
        return result;
    }
    
}

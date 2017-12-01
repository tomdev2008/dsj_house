/*
 * Copyright 2012 Stormpath, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dsj.common.web.exhandler;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.dsj.common.enums.ApiStatusCode;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.exceptions.BusinessException;
import com.dsj.common.mapper.JsonMapper;
import com.dsj.common.utils.RegexpUtils;
import com.dsj.common.vo.CommResultVo;

public class RestExceptionHandler extends AbstractHandlerExceptionResolver implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(RestExceptionHandler.class);

    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        CommResultVo commResultVo=new CommResultVo();
        if(ex instanceof BusinessException){
        	BusinessException bex=(BusinessException)ex;
        	commResultVo.setReturnCode(bex.getStatusCode().getCode());
        	commResultVo.setReturnMsg(bex.getStatusCode().getMsg());
        }else{
        	ApiStatusCode statusCode=null;
        	try{
	        	String msg=RegexpUtils.findFrist(ex.getMessage(), RegexpUtils.CHINESE_REGEXP);
	        	if(!StringUtils.isEmpty(msg)){
	        		 statusCode=ApiStatusCode.getByMsg(msg);
	        	}
        	}catch(Exception e){
        	}
        	if(statusCode!=null){
	        	commResultVo.setReturnCode(statusCode.getCode());
	        	commResultVo.setReturnMsg(statusCode.getMsg());
        	}else{
	        	commResultVo.setReturnCode(StatusCode.SERVER_ERROR.getCode());
	        	commResultVo.setReturnMsg(ex.getMessage());
	        	log.error("异常：",ex);
        	}
        }
        
        ModelAndView mav = new ModelAndView();  
        /*  使用response返回    */  
        response.setStatus(HttpStatus.OK.value()); //设置状态码  
        response.setContentType(MediaType.APPLICATION_JSON_VALUE); //设置ContentType  
        response.setCharacterEncoding("UTF-8"); //避免乱码  
        response.setHeader("Cache-Control", "no-cache, must-revalidate");  
        try {  
            response.getWriter().write(JsonMapper.toJsonString(commResultVo));  
            response.getWriter().flush();
        } catch (IOException e) {  
           log.error("与客户端通讯异常:"+ e.getMessage(), e);  
        }  
        return mav;
    }
    
}

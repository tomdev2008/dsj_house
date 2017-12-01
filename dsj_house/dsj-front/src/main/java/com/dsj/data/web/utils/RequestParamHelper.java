package com.dsj.data.web.utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;

/**
 * 提取request 请求参数封装成map
 * @author wyt
 * @date 2017-3-12 14:23:33
 */
public class RequestParamHelper {

    public static Map<String,Object> paramsToMaps(ServletRequest request)
    {
        Map<String,Object> params=new HashMap<String,Object>();
        Enumeration<String> keys=request.getParameterNames();
        while(keys.hasMoreElements()){
            String key=keys.nextElement();
            params.put(key, request.getParameter(key));
        }
        return params;
    }
}

/**
 * 
 */
package com.dsj.common.utils;


import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类, 继承org.apache.commons.lang.time.DateUtils类
 * @author ThinkGem
 * @version 2014-4-15
 */
public class BeanToMap {

	private final Logger LOGGER = LoggerFactory.getLogger(BeanToMap.class);
	
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
		public  Map<String, Object> transBean2Map(Object obj) {  
		    if (obj == null) {  
		        return null;  
		    }  
		    Map<String, Object> map = new HashMap<String, Object>();  
		    try {  
		        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
		        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
		        for (PropertyDescriptor property : propertyDescriptors) {  
		            String key = property.getName();  
		            // 过滤class属性  
		            if (!key.equals("class")) {  
		                // 得到property对应的getter方法  
		                Method getter = property.getReadMethod();  
		                Object value = getter.invoke(obj);  
		  
		                map.put(key, value);  
		            }  
		  
		        }  
		    } catch (Exception e) {  
		    	LOGGER.error("transBean2Map Error {}" ,e);  
		    }  
		    return map;  
		  
		} 
}

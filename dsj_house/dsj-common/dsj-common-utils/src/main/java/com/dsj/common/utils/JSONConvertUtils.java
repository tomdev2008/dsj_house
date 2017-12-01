package com.dsj.common.utils;

import java.util.Map;

import com.dsj.common.utils.Reflections;

import net.sf.json.JSONObject;


/**
 * @Summary JSON工具类
 * @author asykjiang
 * @Detail
 * @Nonuse
 */
public class JSONConvertUtils {

	/**
	 * 将JSON字符串转换成POJO对象
	 * @param jsonStr  JSON数据
	 * @param clazz   POJO对象Class类
	 * @return 转换后的POJO对象
	 */
	public static Object jsonToObject(String jsonStr,Class clazz){
		Map<String, Class> classMap = Reflections.getSpecialFieldClass(clazz);  
		JSONObject jsonObj=JSONObject.fromObject(jsonStr);
	    return JSONObject.toBean(jsonObj,clazz,classMap); 
	}
}

package com.dsj.common.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

/**
 * 
 * 处理json的工具类. <br>
 * 本类为处理json的工具类
 * 
 * @author slj
 */
public class JsonTools {
	private static Logger log = Logger.getLogger(JsonTools.class);
	/**
	 * 
	 * json转换list. <br>
	 * 详细说明
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @return
	 * @return List<Map<String,Object>> list
	 * @throws @author
	 *             slj
	 * @date 2013年12月24日 下午1:08:03
	 */
	public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
		JSONArray jsonArr = JSONArray.fromObject(jsonStr);
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<JSONObject> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = it.next();
			list.add(parseJSON2Map(json2.toString()));
		}
		return list;
	}

	/**
	 * 
	 * json转换map. <br>
	 * 详细说明
	 * 
	 * @param jsonStr
	 *            json字符串
	 * @return
	 * @return Map<String,Object> 集合
	 * @throws @author
	 *             slj
	 */
	public static Map<String, Object> parseJSON2Map(String jsonStr) {
		ListOrderedMap map = new ListOrderedMap();
		// 最外层解析
		JSONObject json = JSONObject.fromObject(jsonStr);
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<JSONObject> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = it.next();
					list.add(parseJSON2Map(json2.toString()));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}

	/**
	 * 
	 * 通过HTTP获取JSON数据. <br>
	 * 通过HTTP获取JSON数据返回list
	 * 
	 * @param url
	 *            链接
	 * @return
	 * @return List<Map<String,Object>> list
	 * @throws @author
	 *             slj
	 */
	public static List<Map<String, Object>> getListByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2List(sb.toString());
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return null;
	}

	/**
	 * 
	 * 通过HTTP获取JSON数据. <br>
	 * 通过HTTP获取JSON数据返回map
	 * 
	 * @param url
	 *            链接
	 * @return
	 * @return Map<String,Object> 集合
	 * @throws @author
	 *             slj
	 */
	public static Map<String, Object> getMapByUrl(String url) {
		try {
			// 通过HTTP获取JSON数据
			InputStream in = new URL(url).openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
			return parseJSON2Map(sb.toString());
		} catch (Exception e) {
			log.error("Exception", e);
		}
		return null;
	}

	/**
	 * 
	 * map转换json. <br>
	 * 详细说明
	 * 
	 * @param map
	 *            集合
	 * @return
	 * @return String json字符串
	 * @throws @author
	 *             slj
	 */
	public static String mapToJson(Map<String, Object> map) {
		Set<String> keys = map.keySet();
		String key="";
		StringBuffer jsonBuffer = new StringBuffer();
		jsonBuffer.append("{");
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			key = (String) it.next();
			String value = ObjectUtils.toString(map.get(key), "");
			jsonBuffer.append(key + ":" + "\"" + value + "\"");
			if (it.hasNext()) {
				jsonBuffer.append(",");
			}
		}
		jsonBuffer.append("}");
		return jsonBuffer.toString();
	}
	
	

	/**
	 * 
	 * map转换json. <br>
	 * 详细说明
	 * 
	 * @param map
	 *            集合
	 * @return
	 * @return String json字符串
	 * @throws @author
	 *             slj
	 */
	public static String mapToKvJson(Map<String, Object> map) {
		Set<String> keys = map.keySet();
		   JSONArray jsonArray=new    JSONArray();
			String key = "";
		for (Iterator<String> it = keys.iterator(); it.hasNext();) {
			JSONObject object = new JSONObject();  
			key = (String) it.next();
			String value = ObjectUtils.toString(map.get(key), "");
			  object.put("key", key);  
			  object.put("value", value);  
			  jsonArray.add(object);
		}
		return jsonArray.toString();
	}
	
	
	 /**
     * key 	value转map
		[{"name":"sEcho","value":1},{"name":"iColumns","value":3}]转map
     * @param jsonStr
     * @return
     */
    public static Map<String, Object> parsePageMap(String jsonStr){  
        Map<String, Object> map = new HashMap<String, Object>();  
        //最外层解析  
        if(StringUtils.isNotBlank(jsonStr)){
        	jsonStr=jsonStr.replace("&quot;", "\"");
        }
        JSONArray jsonArray = JSONArray.fromObject(jsonStr); 
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject json = (JSONObject) jsonArray.get(i);
            map.put(json.getString("name"), json.getString("value"));

        }
       
        return map;  
    } 

	// test
	public static void main(String[] args) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("2", "1");
		map.put("3", "1");
		System.out.println(mapToKvJson(map));
	}
}
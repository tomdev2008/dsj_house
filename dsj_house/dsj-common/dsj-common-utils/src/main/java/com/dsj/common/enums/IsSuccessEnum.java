package com.dsj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum IsSuccessEnum {
	YES("成功", 1),
	NO("失败", 2);
	

	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private IsSuccessEnum(String desc, Integer value) {
		this.desc = desc;
		this.value = value;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static IsSuccessEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		IsSuccessEnum resultEnum = null;
		IsSuccessEnum[] enumAry = IsSuccessEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		IsSuccessEnum[] ary = IsSuccessEnum.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", ary[num].getValue());
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		IsSuccessEnum[] ary = IsSuccessEnum.values();
		List list = new ArrayList();
		for(int i=0;i<ary.length;i++){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("value",ary[i].getValue());
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}
}
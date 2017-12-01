package com.dsj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 前台面积枚举
 * @author wyt
 *
 */
public enum FrontRefrencePriceEnum {

	
	ONE("2万以下", 1),
	TWO("2-3万", 2),
	THREE("3-4万", 3),
	FOUR("4-6万", 4),
	FIVE("6-8万", 5),
	SIX("8-10万", 6),
	SEVEN("10万以上", 7),
	;


	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private FrontRefrencePriceEnum(String desc, Integer value) {
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
	
	public static FrontRefrencePriceEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		FrontRefrencePriceEnum resultEnum = null;
		FrontRefrencePriceEnum[] enumAry = FrontRefrencePriceEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		FrontRefrencePriceEnum[] ary = FrontRefrencePriceEnum.values();
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

	public static Map<String, Object> toMap() {
		FrontRefrencePriceEnum[] ary = FrontRefrencePriceEnum.values();
		Map<String, Object> enumMap = new HashMap<String, Object>();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		FrontRefrencePriceEnum[] ary = FrontRefrencePriceEnum.values();
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

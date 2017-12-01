package com.dsj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 前台租房面积枚举
 * @author wyt
 *
 */
public enum FrontRentAreaEnum {


	ONE("20m²以下", 1),
	TWO("20m²-30m²", 2),
	THREE("30m²-40m²", 3),
	FOUR("40m²-50m²", 4),
	FIVE("50²-60m²", 5),
	SIX("60m²-70m²", 6),
	SEVEN("70m²以上", 7),
	;

	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private FrontRentAreaEnum(String desc, Integer value) {
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
	
	public static FrontRentAreaEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		FrontRentAreaEnum resultEnum = null;
		FrontRentAreaEnum[] enumAry = FrontRentAreaEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		FrontRentAreaEnum[] ary = FrontRentAreaEnum.values();
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
		FrontRentAreaEnum[] ary = FrontRentAreaEnum.values();
		Map<String, Object> enumMap = new HashMap<String, Object>();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		FrontRentAreaEnum[] ary = FrontRentAreaEnum.values();
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

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
public enum FrontRentTypeEnum {

	
	ONE("整租", 1),
	TWO("合租", 2),
	;


	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private FrontRentTypeEnum(String desc, Integer value) {
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
	
	public static FrontRentTypeEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		FrontRentTypeEnum resultEnum = null;
		FrontRentTypeEnum[] enumAry = FrontRentTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		FrontRentTypeEnum[] ary = FrontRentTypeEnum.values();
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
		FrontRentTypeEnum[] ary = FrontRentTypeEnum.values();
		Map<String, Object> enumMap = new HashMap<String, Object>();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		FrontRentTypeEnum[] ary = FrontRentTypeEnum.values();
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
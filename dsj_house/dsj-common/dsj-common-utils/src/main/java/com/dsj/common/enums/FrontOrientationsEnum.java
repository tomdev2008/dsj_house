package com.dsj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
/**
 * 前台面积枚举
 * @author wyt
 *
 */
public enum FrontOrientationsEnum {

	
	D("东", 122),
	X("西", 123),
	N("南", 121),
	B("北", 124),
	NB("南北", 119),
	DN("东南", 127),
	XN("西南", 128),
	DB("东北", 126),
	XB("西北", 125),
	DX("东西", 120),
	;
	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private FrontOrientationsEnum(String desc, Integer value) {
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
	
	public static FrontOrientationsEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		FrontOrientationsEnum resultEnum = null;
		FrontOrientationsEnum[] enumAry = FrontOrientationsEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		FrontOrientationsEnum[] ary = FrontOrientationsEnum.values();
		Map<String, Map<String, Object>> enumMap = Maps.newLinkedHashMap();
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
		FrontOrientationsEnum[] ary = FrontOrientationsEnum.values();
		Map<String, Object> enumMap  = Maps.newLinkedHashMap();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		FrontOrientationsEnum[] ary = FrontOrientationsEnum.values();
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

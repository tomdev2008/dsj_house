package com.dsj.common.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
/**
 * 前台价格枚举
 * @author wyt
 *
 */
public enum FrontCompanyTypeEnum {


	DSJ("大搜家精选", 1),
	LIANJIA("链家", 2),
	WAWJ("我爱我家", 5),
	MAITIAN("麦田房产", 3),
	ZHUANGYUAN("中原地产", 4),
	OTHER("其他", 99);

	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private FrontCompanyTypeEnum(String desc, Integer value) {
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
	
	public static FrontCompanyTypeEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		FrontCompanyTypeEnum resultEnum = null;
		FrontCompanyTypeEnum[] enumAry = FrontCompanyTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		FrontCompanyTypeEnum[] ary = FrontCompanyTypeEnum.values();
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
		FrontCompanyTypeEnum[] ary = FrontCompanyTypeEnum.values();
		Map<String, Object> enumMap = Maps.newLinkedHashMap();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		FrontCompanyTypeEnum[] ary = FrontCompanyTypeEnum.values();
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

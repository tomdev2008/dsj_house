package com.dsj.modules.other.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *	二手房委托 和 二手房求购状态
 */
public enum HouseEntrustAndRequireStatus {
	YES("已处理", 1),
	NO("未处理", 2);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private int value;

	private HouseEntrustAndRequireStatus(String desc, int value) {
		this.desc = desc;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public static HouseEntrustAndRequireStatus getEnum(int value){
		HouseEntrustAndRequireStatus resultEnum = null;
		HouseEntrustAndRequireStatus[] enumAry = HouseEntrustAndRequireStatus.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue() == value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		HouseEntrustAndRequireStatus[] ary = HouseEntrustAndRequireStatus.values();
		Map<String, Map<String, Object>> enumMap = new HashMap<String, Map<String, Object>>();
		for (int num = 0; num < ary.length; num++) {
			Map<String, Object> map = new HashMap<String, Object>();
			String key = String.valueOf(getEnum(ary[num].getValue()));
			map.put("value", String.valueOf(ary[num].getValue()));
			map.put("desc", ary[num].getDesc());
			enumMap.put(key, map);
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		HouseEntrustAndRequireStatus[] ary = HouseEntrustAndRequireStatus.values();
		List list = new ArrayList();
		for(int i=0;i<ary.length;i++){
			Map<String,String> map = new HashMap<String,String>();
			map.put("value",String.valueOf(ary[i].getValue()));
			map.put("desc", ary[i].getDesc());
			list.add(map);
		}
		return list;
	}
}

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
public enum NewHouseTsEnum {

	
	ONE("热销楼盘", 96),
	TWO("即将开盘", 285),
	THREE("刚需楼盘", 97),
	FOUR("低总价", 244),
	FIVE("大户型", 337),
	SIX("小三室", 287),
	SEVEN("大客厅", 277),
	EIGHT("大主卧", 238),
	NINE("南北通透", 323),
	TEN("车位充足", 290);


	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private NewHouseTsEnum(String desc, Integer value) {
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
	
	public static NewHouseTsEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		NewHouseTsEnum resultEnum = null;
		NewHouseTsEnum[] enumAry = NewHouseTsEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		NewHouseTsEnum[] ary = NewHouseTsEnum.values();
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
		NewHouseTsEnum[] ary = NewHouseTsEnum.values();
		Map<String, Object> enumMap = new HashMap<String, Object>();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		NewHouseTsEnum[] ary = NewHouseTsEnum.values();
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

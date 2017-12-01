package com.dsj.modules.other.enums;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @描述: 用户状态 . <br/>
 * @作者: WuShuicheng .
 * @创建时间: 2013-9-12,上午11:16:23 .
 * @版本: 1.0 .
 */
public enum SubwayObjectTypeEnum {
	/** 楼盘字典   1 */
	HOUSE_DIRECTORY("楼盘字典", 1),
	/** 楼盘字典   2 */
	NEW_HOUSE_DIRECTORY("新房", 2);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private int value;

	private SubwayObjectTypeEnum(String desc, int value) {
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
	
	public static SubwayObjectTypeEnum getEnum(int value){
		SubwayObjectTypeEnum resultEnum = null;
		SubwayObjectTypeEnum[] enumAry = SubwayObjectTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue() == value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMapMap() {
		SubwayObjectTypeEnum[] ary = SubwayObjectTypeEnum.values();
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
	
	public static Map<String, Object> toMap() {
		SubwayObjectTypeEnum[] ary = SubwayObjectTypeEnum.values();
		Map<String, Object> enumMap = new HashMap<String, Object>();
		for (int i=0;i<ary.length;i++) {
			enumMap.put(String.valueOf(ary[i].getValue()),ary[i].getDesc());
		}
		return enumMap;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		SubwayObjectTypeEnum[] ary = SubwayObjectTypeEnum.values();
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

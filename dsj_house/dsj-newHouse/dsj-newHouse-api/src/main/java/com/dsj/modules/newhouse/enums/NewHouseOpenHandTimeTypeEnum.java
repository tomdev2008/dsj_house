package com.dsj.modules.newhouse.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NewHouseOpenHandTimeTypeEnum {
	OPEN("开盘", 1),
	HAND("交房", 2);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private NewHouseOpenHandTimeTypeEnum(String desc, Integer value) {
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
	
	public static NewHouseOpenHandTimeTypeEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		NewHouseOpenHandTimeTypeEnum resultEnum = null;
		NewHouseOpenHandTimeTypeEnum[] enumAry = NewHouseOpenHandTimeTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		NewHouseOpenHandTimeTypeEnum[] ary = NewHouseOpenHandTimeTypeEnum.values();
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
		NewHouseOpenHandTimeTypeEnum[] ary = NewHouseOpenHandTimeTypeEnum.values();
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

package com.dsj.modules.other.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 区域码枚举
 */
public enum AreaEnum {
	
	/** "是自定义", 2 */
	IS_CUSTOM_YES("是自定义", 2),
	
	/** "非自定义", 1 */
	IS_CUSTOM_NO("非自定义", 1),
	
	/** "省分级别", 1 */
	PROVINCE_LV("省分级别", 1),
	
	/** "城市级别", 2 */
	CITY_LV("城市级别", 2),
	
	/** "区县级别", 3 */
	COUNTY_LV("区县级别", 3),
	
	/** "商圈级别", 4 */
	TRADE_LV("商圈级别", 4),
	
	/** "非自定义（标准区域）", 1 */
	NOT_CUSTOM("非自定义（标准区域）", 1),
	
	/** "是自定义", 2 */
	IS_CUSTOM("是自定义", 2)
	;
	
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private AreaEnum(String desc, Integer value) {
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
	
	public static AreaEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		AreaEnum resultEnum = null;
		AreaEnum[] enumAry = AreaEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		AreaEnum[] ary = AreaEnum.values();
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
		AreaEnum[] ary = AreaEnum.values();
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

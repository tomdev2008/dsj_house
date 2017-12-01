package com.dsj.modules.solr.enums;


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
public enum OrderingRuleEnum {
	UPDATE_DESC("时间倒叙", 1),
	UPDATE_ASC("时间正序", 2),
	PRICE_DESC("价格倒叙", 3),
	PRICE_ASC("价格正序", 4),
	BUILD_YEAR_DESC("建筑时间倒叙", 5),
	BUILD_YEAR_ASC("建筑时间正序", 6),
	AREA_DESC("面积倒叙", 7),
	AREA_ASC("面积正序", 8),
	;
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private int value;

	private OrderingRuleEnum(String desc, int value) {
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
	
	public static OrderingRuleEnum getEnum(int value){
		OrderingRuleEnum resultEnum = null;
		OrderingRuleEnum[] enumAry = OrderingRuleEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue() == value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		OrderingRuleEnum[] ary = OrderingRuleEnum.values();
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
		OrderingRuleEnum[] ary = OrderingRuleEnum.values();
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

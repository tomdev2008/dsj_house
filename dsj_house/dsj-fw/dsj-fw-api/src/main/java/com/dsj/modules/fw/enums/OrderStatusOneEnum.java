package com.dsj.modules.fw.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatusOneEnum {
	//1待办订单 2在办订单 3退款订单 4待结算订单 5已结订单 6无效订单
	ONE("待办订单", 1),
	TWO("在办订单", 2),
	THREE("退款订单", 3),
	FOUR("待结算订单", 4),
	FIVE("已结订单", 5),
	FIX("无效订单", 6);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;
	private OrderStatusOneEnum(String desc, Integer value) {
		this.desc = desc;
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	
	
	public static OrderStatusOneEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		OrderStatusOneEnum resultEnum = null;
		OrderStatusOneEnum[] enumAry = OrderStatusOneEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		OrderStatusOneEnum[] ary = OrderStatusOneEnum.values();
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
		OrderStatusOneEnum[] ary = OrderStatusOneEnum.values();
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

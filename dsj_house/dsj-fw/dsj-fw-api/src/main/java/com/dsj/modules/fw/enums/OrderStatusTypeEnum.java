package com.dsj.modules.fw.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum OrderStatusTypeEnum {
	WAIT_pay("待付款", 1),
	CANCEL("已取消", 2),
	TIME_OUT("已过期", 3),
	SUCCESS_PAY("已付款", 4),
	RETURNING("退款受理中", 5),
	RETURNCOMPLITE("退款完成", 6),
	WAIT_PRESON_COMFIRE("待买家确认", 7),
	WAIT_COMMENTED("待评价", 9),
	PRESON_COMMENTED("买价已评价", 10),
	OPEND("已开启服务", 12),
	WAIT_OVER("待结束服务", 13);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;
	private OrderStatusTypeEnum(String desc, Integer value) {
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
	
	
	public static OrderStatusTypeEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		OrderStatusTypeEnum resultEnum = null;
		OrderStatusTypeEnum[] enumAry = OrderStatusTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		OrderStatusTypeEnum[] ary = OrderStatusTypeEnum.values();
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
		OrderStatusTypeEnum[] ary = OrderStatusTypeEnum.values();
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

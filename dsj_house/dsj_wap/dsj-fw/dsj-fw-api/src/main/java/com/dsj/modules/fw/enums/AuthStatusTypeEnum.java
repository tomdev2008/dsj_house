package com.dsj.modules.fw.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum AuthStatusTypeEnum {
	AUTH_WAIT("待审核", 1),
	AUTH_SUCCESS("已通过", 2),
	AUTH_FAIL("已驳回", 3),
	WAIT_COMMIT("待提交", 4),
	WAIT_END("待结束", 5);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;
	private AuthStatusTypeEnum(String desc, Integer value) {
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
	
	
	public static AuthStatusTypeEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		AuthStatusTypeEnum resultEnum = null;
		AuthStatusTypeEnum[] enumAry = AuthStatusTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		AuthStatusTypeEnum[] ary = AuthStatusTypeEnum.values();
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
		AuthStatusTypeEnum[] ary = AuthStatusTypeEnum.values();
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

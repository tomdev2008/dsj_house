package com.dsj.modules.newhouse.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NewHouseEditEnum {
	YES("展示",1),
	NO("不展示",2);
	
	/** 描述 */
    private String desc;
    /** 枚举值 */
    private Integer value;
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

	private NewHouseEditEnum(String desc, Integer value) {
	        this.desc = desc;
	        this.value = value;
	  }
	 
	 public static NewHouseEditEnum getEnum(Integer value){
		  NewHouseEditEnum resultEnum = null;
		  NewHouseEditEnum[] enumAry = NewHouseEditEnum.values();
	        for(int i = 0;i<enumAry.length;i++){
	            if(enumAry[i].getValue().equals(value)){
	                resultEnum = enumAry[i];
	                break;
	            }
	        }
	        return resultEnum;
	    }
	 public static Map<String, Map<String, Object>> toMap() {
		 NewHouseEditEnum[] ary = NewHouseEditEnum.values();
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
			NewHouseEditEnum[] ary = NewHouseEditEnum.values();
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

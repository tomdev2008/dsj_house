package com.dsj.modules.newhouse.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum NewHouseIsTrueEnum {
	NOUP("未上架",1),
	UP("已上架",2),
	DOWN("已下架",3),
	DELETE("已删除",4);
	
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

	private NewHouseIsTrueEnum(String desc, Integer value) {
	        this.desc = desc;
	        this.value = value;
	  }
	 
	 public static NewHouseIsTrueEnum getEnum(Integer value){
		  NewHouseIsTrueEnum resultEnum = null;
		  NewHouseIsTrueEnum[] enumAry = NewHouseIsTrueEnum.values();
	        for(int i = 0;i<enumAry.length;i++){
	            if(enumAry[i].getValue().equals(value)){
	                resultEnum = enumAry[i];
	                break;
	            }
	        }
	        return resultEnum;
	    }
	 public static Map<String, Map<String, Object>> toMap() {
		 NewHouseIsTrueEnum[] ary = NewHouseIsTrueEnum.values();
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
			NewHouseIsTrueEnum[] ary = NewHouseIsTrueEnum.values();
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

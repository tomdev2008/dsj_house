package com.dsj.modules.oldhouse.enums;


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
public enum CompanyTypeEnum {

	DSJ("大搜家", 1),
	LIANJIA("链家", 2),
	MAITIAN("麦田", 3),
	ZHONGYUAN("中原地产", 4),
	WAWJ("我爱我家", 5);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private int value;

	private CompanyTypeEnum(String desc, int value) {
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
	
	public static CompanyTypeEnum getEnum(int value){
		CompanyTypeEnum resultEnum = null;
		CompanyTypeEnum[] enumAry = CompanyTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue() == value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		CompanyTypeEnum[] ary = CompanyTypeEnum.values();
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
	
	public static String getCompanyTypeStr(CompanyTypeEnum companyType){
		String resultStr= "";
		switch (companyType){
			case LIANJIA:
			resultStr="lianjia";
			break;
			case MAITIAN:
			resultStr="maitian";
			break;
			case ZHONGYUAN:
			resultStr="centanet";
			break;
			case WAWJ:
			resultStr="wiwj";
			break;
				
		}
		return resultStr;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List toList(){
		CompanyTypeEnum[] ary = CompanyTypeEnum.values();
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

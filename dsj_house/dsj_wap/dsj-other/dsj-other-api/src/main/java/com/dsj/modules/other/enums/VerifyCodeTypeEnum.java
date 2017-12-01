package com.dsj.modules.other.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum VerifyCodeTypeEnum {
	BACK_FIND_PASSWORD("后台找回密码", 1),
	DEVELOPER_REGISTER("开发商注册", 2),
	DEVELOPER_FIND_REGISTER("开发商找回密码", 3),
	DEVELOPER_UPDATE_PHONE("开发商修改手机号", 4),
	WARRANT_FIND_REGISTER("权证管理找回密码", 5),
	PC_LOGIN("pc用户登录", 6),
	WAP_LOGIN("wap用户登录", 7),
	WAP_FIND_PASSWORD("wap找回密码", 8),
	WAP_REGISTER("wap注册账号", 9),
	;
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;

	private VerifyCodeTypeEnum(String desc, Integer value) {
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
	
	public static VerifyCodeTypeEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		VerifyCodeTypeEnum resultEnum = null;
		VerifyCodeTypeEnum[] enumAry = VerifyCodeTypeEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		VerifyCodeTypeEnum[] ary = VerifyCodeTypeEnum.values();
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
		VerifyCodeTypeEnum[] ary = VerifyCodeTypeEnum.values();
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

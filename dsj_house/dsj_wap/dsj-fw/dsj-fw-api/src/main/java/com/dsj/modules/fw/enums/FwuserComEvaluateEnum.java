package com.dsj.modules.fw.enums;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FwuserComEvaluateEnum {
	FU_WU_ZHUAN_YE("服务专业", 1),
	JING_YAN_CHONG_ZU("经验充足", 2),
	LIU_CHENG_QING_XI("流程清晰", 3),
	TA_SHI_WEN_ZHONG("踏实稳重", 4),
	REN_ZHENG_YAN_JIN("认真严谨", 5),
	LI_MAO_DAI_KE("礼貌待客", 6),
	CHENG_XIN_ZHENG_ZHI("诚信正直", 7),
	BAN_SHI_GAO_XIAO("办事高效", 8);
	/** 描述 */
	private String desc;
	/** 枚举值 */
	private Integer value;
	
	private FwuserComEvaluateEnum(String desc, Integer value) {
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

	public static FwuserComEvaluateEnum getEnum(Integer value){
		if(value==null){
			return null;
		}
		FwuserComEvaluateEnum resultEnum = null;
		FwuserComEvaluateEnum[] enumAry = FwuserComEvaluateEnum.values();
		for(int i = 0;i<enumAry.length;i++){
			if(enumAry[i].getValue().intValue()==value){
				resultEnum = enumAry[i];
				break;
			}
		}
		return resultEnum;
	}
	
	public static Map<String, Map<String, Object>> toMap() {
		FwuserComEvaluateEnum[] ary = FwuserComEvaluateEnum.values();
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
		FwuserComEvaluateEnum[] ary = FwuserComEvaluateEnum.values();
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

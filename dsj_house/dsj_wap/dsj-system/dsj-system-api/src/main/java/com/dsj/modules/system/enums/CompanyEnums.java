package com.dsj.modules.system.enums;

public enum CompanyEnums {
	AGENTCOMPANY("经纪人公司",1),
	BUSINESS("商家",2);
	
	
	private Integer value;
	private String desc;
	private CompanyEnums(String desc,Integer value) {
		this.desc = desc;
        this.value = value;        
    }

	public String getDesc() {
		return desc;
	}


	public Integer getValue() {
		return value;
	}
}

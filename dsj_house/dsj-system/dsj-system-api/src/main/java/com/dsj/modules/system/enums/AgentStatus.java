package com.dsj.modules.system.enums;

public enum AgentStatus {
	//注册未认证
	//是否外部注册
	IS_EXTERNAL_REGIST("是",1),
	NOT_EXTERNAL_REGIST("否",0),
	UN_APPLY("注册未认证", 4),
	AUDIT_SUCCESS("已通过", 1),
	UN_AUDIT("待审核", 2),
	AUDIT_REFUSE("已驳回", 3),
	ALL_STATUS("所有状态",5),
	//是否卖新房
	IS_SELL_NEW_HOUSE("是",1),
	NOT_SELL_NEW_HOUSE("否",0),
	
	SORT_DEFAULT("未排序",100);
	
	
	private String msg;
	private Integer code;
	
	private AgentStatus(String msg,Integer code) {
		this.msg = msg;
        this.code = code;        
    }

	public String getMsg() {
		return msg;
	}


	public Integer getCode() {
		return code;
	}

}

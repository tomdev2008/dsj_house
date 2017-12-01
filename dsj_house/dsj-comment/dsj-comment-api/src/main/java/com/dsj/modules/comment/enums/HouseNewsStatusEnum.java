package com.dsj.modules.comment.enums;

public enum HouseNewsStatusEnum {
	UN_AUDIT("待审核",1),
	AUDIT_SUCCESS("已通过",2),
	AUDIT_REFUSE("已驳回",3),
	WAIT_SUBMIT("待提交",4),
	
	
	
	NONE("未上线",3),
	UP("已上线",1),
	DOWN("已下线",2);
	
	private String msg;
	private Integer value;
	
	private HouseNewsStatusEnum(String msg,Integer value) {
		this.msg = msg;
        this.value = value;        
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}

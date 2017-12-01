package com.dsj.common.enums;

public enum RoleChecked {
	CHECKED("选中",1),
	UNCHECKED("未选中",0);
	private String msg;
	private Integer code;
	
	private RoleChecked(String msg,Integer code) {
		this.msg = msg;
        this.code = code;        
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}

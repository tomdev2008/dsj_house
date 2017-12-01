package com.dsj.modules.comment.enums;

public enum FwContentStatusEnum {
	
	XIA_XIAN("已下线",0),
	WEI_SHANG_XIAN("未上线",1),
	SHANG_XIAN("已上线",2);
	
	private String msg;
	private Integer value;
	
	private FwContentStatusEnum(String msg,Integer value) {
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

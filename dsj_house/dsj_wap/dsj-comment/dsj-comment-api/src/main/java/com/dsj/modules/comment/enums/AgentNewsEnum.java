package com.dsj.modules.comment.enums;

public enum AgentNewsEnum {

	
	COMMENT_INTI_NUM("评论初始值",0),

	LIKE_INIT_NUM("初始点赞",0),
	NAGETIVA_INIT_NUM("初始差评",0),
	STICK("置顶",1),

	NOT_STICK("未置顶",0);


	
	private String msg;
	private Integer code;
	
	private AgentNewsEnum(String msg,Integer code) {
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

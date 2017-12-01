package com.dsj.common.enums;

/**
 * 状态码枚举
 * @date 2016-08-29
 */
public enum StatusCode {
	//状态码规则：前三位业务说明，后三位流水号，共六位 
	//异常 999 开头
	SERVER_ERROR(999001, "系统异常"), 
	PERMISSION_DENIED(999002, "没有权限"), 
	UNKNOWN_ERROR(999003, "未知错误，请联系客服"), 
	UNKNOWN_BUSINESS(999004, "未知业务类型"), 
	SAVE_DATE_ERROR(999005, "数据入库失败"), 
	REPEAT_DATE_ERROR(999006, "业务数据已存在"), 
	PARAMS_ERROR(999007, "参数错误"),
	NO_RESULT(999008, "没有查询到数据"),
	RECORD_NOT_EXIST(999009, "该记录不存在"),
	DEL_DATE_ERROR(999010, "数据删除失败"),
	EDIT_DATE_ERROR(999011, "数据操作失败"),
	SUCCESS(200,"成功"),
	SERVER_ERRORS(999013, "请先在前台首页下线后，再下架楼盘"),
	
	//签名认证 998 开头
	PARAM_ERROR(998001,"参数错误"),
	SIGN_INVALID_APPLICANT(998002,"无效请求用户"),
	SIGN_INVALID_IP(998003,"禁止访问"),
	
	//用户：101 开头
	USER_NOT_EXIST(101001, "用户不存在"),
	USER_PASSWORD_ERROR(101002, "原始密码错误"),
	USERNAME_OR_PASSWORD_ERROR(101003, "用户名或密码错误"),
	USER_VCODE_ERROR(101004, "验证码错误"),
	USER_VCODE_OVERTIME(101005, "验证码超时"),
	PASSWORD_NOT_EQ(101006, "二次密码不一致"),
	SESSION_PHONE_ERROR(101007, "修改密码无法通过验证，请重试"),
	ROLE_HAD(101008, "该角色编码已存在,请更换"),
	USER_EXIST(101009, "用户名已注册"),
	PHONE_EXIST(101010, "手机号已经注册，请直接登录"),
	PHONE_NOT_EXIST(101010, "手机号不存在"),
	PHONE_NOT_NULL(101011, "手机号不能为空"),
	USERNAME_NOT_NULL(101012, "用户名不能为空"),
	VCODE_NOT_NULL(101013, "验证码不能为空"),
	PASSWORD_NOT_NULL(101014, "密码不能为空"),
	PHONE_EXIST_2(101015, "手机号已经注册"),
	USER_KJ_LOGIN_ERROR(101016, "已为其他类型账号，无法登录"),
	
	AGENT_RECOMENT_COUNT(201000, "最多推荐10个房源"),
	;
	
	
	private Integer code;
	private String msg;

	private StatusCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public static String getResponseMsg(String code) {
		if(code==null){
			return null;
		}
		for (StatusCode statusCode : StatusCode.values()) {
			if (code.equals(statusCode.getCode())) {
				return statusCode.getMsg();
			}
		}
		return SERVER_ERROR.getMsg();
	}
	
	public static StatusCode getByMsg(String msg) {
		if(msg==null||msg==""){
			return null;
		}
		for (StatusCode statusCode : StatusCode.values()) {
			if (msg.equals(statusCode.getMsg())) {
				return statusCode;
			}
		}
		return SERVER_ERROR;
	}
}
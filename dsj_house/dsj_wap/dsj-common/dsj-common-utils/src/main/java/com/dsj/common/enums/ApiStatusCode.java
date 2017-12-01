package com.dsj.common.enums;

/**
 * 状态码枚举
 * @date 2016-08-29
 */
public enum ApiStatusCode {	
	SERVER_ERROR(999001, "系统异常"), 
	PARAMS_ERROR(999002, "参数为空"),
	CALL_INTERFACE_ERROR(999003, "调用接口失败"),
	

	//签名认证 998 开头
	SIGN_PARAM_ERROR(998001,"签名错误"),
	SIGN_INVALID_APPLICANT(998002,"无效请求用户"),
	SIGN_INVALID_IP(998003,"禁止访问"),
	SIGN_PARAM_NULL(998004,"签名为空"),
	TS_NULL(998005,"时间戳为空"),
	
	//提供给APP 登录
	USER_NOT_EXIST(101001, "用户不存在"),
	USER_PASSWORD_ERROR(101002, "密码错误"),
	USER_SMSCODE_ERROR(101003, "短信发送失败，请重试"),
	USER_CODENUM_ERROR(101004, "验证码错误"),
	USER_CODENUMINVALID_ERROR(101005, "验证码失效"),
	USER_EXIST(101006, "该用户已经存在"),
	SUCCESS(200, "成功"),
	
	;

	private Integer code;
	private String msg;

	private ApiStatusCode(Integer code, String msg) {
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
		for (ApiStatusCode statusCode : ApiStatusCode.values()) {
			if (code.equals(statusCode.getCode())) {
				return statusCode.getMsg();
			}
		}
		return SERVER_ERROR.getMsg();
	}
	
	public static ApiStatusCode getByMsg(String msg) {
		if(msg==null||msg==""){
			return null;
		}
		for (ApiStatusCode statusCode : ApiStatusCode.values()) {
			if (msg.equals(statusCode.getMsg())) {
				return statusCode;
			}
		}
		return SERVER_ERROR;
	}
}
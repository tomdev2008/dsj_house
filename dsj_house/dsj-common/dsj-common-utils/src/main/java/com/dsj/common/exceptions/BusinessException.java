package com.dsj.common.exceptions;

import com.dsj.common.enums.ApiStatusCode;

/**
 * @描述: 业务异常
 * @创建时间: 2016-08-29
 */
public class BusinessException extends RuntimeException {

	private static final long serialVersionUID = -5875371379845226068L;
	
	/**
	 * 异常信息
	 */
	protected ApiStatusCode statusCode;
	
	public ApiStatusCode getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(ApiStatusCode statusCode) {
		this.statusCode = statusCode;
	}

	public BusinessException(ApiStatusCode statusCode,  Object... args) {
		super(String.format(statusCode.getMsg(), args));
		this.statusCode=statusCode;
	}

	public BusinessException() {
		super();
	}

	public BusinessException(ApiStatusCode statusCode, Throwable cause) {
		super(statusCode.getMsg(), cause);
	}

	public BusinessException(Throwable cause) {
		super(cause);
	}

	public BusinessException(ApiStatusCode statusCode) {
		super(statusCode.getMsg());
		this.statusCode=statusCode;
	}
}

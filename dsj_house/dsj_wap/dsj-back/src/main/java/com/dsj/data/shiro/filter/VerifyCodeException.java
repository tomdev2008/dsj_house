package com.dsj.data.shiro.filter;

import org.apache.shiro.authc.AuthenticationException;

public class VerifyCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public VerifyCodeException() {
		super();
	}

	public VerifyCodeException(String message, Throwable cause) {
		super(message, cause);
	}

	public VerifyCodeException(String message) {
		super(message);
	}

	public VerifyCodeException(Throwable cause) {
		super(cause);
	}

}

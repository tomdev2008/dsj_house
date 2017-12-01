package com.dsj.data.auth;

import javax.servlet.ServletException;

/**
 * Exception indication Authentication failure
 *
 * @author: wyt
 */
public class AuthenticationFailedException extends ServletException {
    private static final long serialVersionUID = -8799659324455306881L;

    public AuthenticationFailedException(String message) {
        super(message);
    }
}

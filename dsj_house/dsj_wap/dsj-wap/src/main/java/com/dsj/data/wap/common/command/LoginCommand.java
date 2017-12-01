package com.dsj.data.wap.common.command;


import java.io.Serializable;

/**
 * Created by liu on 2016/12/9.
 */
public class LoginCommand implements Serializable{

    private String loginName ;

    private String password ;

    private String verifyCode;
    
    private Integer count;
    
    private String phone;
    private Integer type;

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
    
    
}

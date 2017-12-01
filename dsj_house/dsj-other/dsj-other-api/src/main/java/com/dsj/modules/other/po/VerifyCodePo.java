package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-16 11:55:36.
 * @版本: 1.0 .
 */
public class VerifyCodePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String verifyCode;		// 验证码
	private String phone;		// 手机号
	private Integer type;		//   4个人中心修改手机号
	
	public VerifyCodePo() {
		super();
	}

	public VerifyCodePo(Long id){
		this();
	}
	

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
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
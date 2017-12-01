package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-16 10:10:37.
 * @版本: 1.0 .
 */
public class SmsLogsPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String phone;		// 手机号
	private String content;		// 发短信内容
	private Integer isSussess;		// is_sussess
	
	public SmsLogsPo() {
		super();
	}

	public SmsLogsPo(Long id){
		this();
	}
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getIsSussess() {
		return isSussess;
	}

	public void setIsSussess(Integer isSussess) {
		this.isSussess = isSussess;
	}
	
}
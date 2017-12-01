package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import com.dsj.modules.system.enums.MemberEnum;

import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 19:51:03.
 * @版本: 1.0 .
 */
public class MemberPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer memberCode;		// 会员id
	private Integer signupOrigin;		// 创建来源1:pc,2:wap
	private String nikename;		// 昵称
	private String tellPhone;		// tell_phone
	private Integer isBlack;		// 是否在黑名单1是，0否
	private Integer blackNum;		// 黑名单次数
	private Date blackTime;		// 拉黑时间
	private Date updateTime;		// update_time
	private Integer operatePerson;		// 操作人
	private Date operateTime;		// 操作时间
	
	private Long userId;		// dsj_user表id
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	


	public MemberPo() {
		super();
	}

	public MemberPo(Long id){
		this();
	}
	

	public Integer getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(Integer memberCode) {
		this.memberCode = memberCode;
	}
	
	public Integer getSignupOrigin() {
		return signupOrigin;
	}

	public void setSignupOrigin(Integer signupOrigin) {
		this.signupOrigin = signupOrigin;
	}
	
	public String getNikename() {
		return nikename;
	}

	public void setNikename(String nikename) {
		this.nikename = nikename;
	}
	
	public String getTellPhone() {
		return tellPhone;
	}

	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	
	public Integer getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(Integer isBlack) {
		this.isBlack = isBlack;
	}
	
	public Integer getBlackNum() {
		return blackNum;
	}

	public void setBlackNum(Integer blackNum) {
		this.blackNum = blackNum;
	}
	
	public Date getBlackTime() {
		return blackTime;
	}

	public void setBlackTime(Date blackTime) {
		this.blackTime = blackTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getOperatePerson() {
		return operatePerson;
	}

	public void setOperatePerson(Integer operatePerson) {
		this.operatePerson = operatePerson;
	}
	
	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}
	
}
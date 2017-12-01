package com.dsj.modules.oldhouse.vo;

import java.io.Serializable;
import java.util.Date;

import com.dsj.modules.oldhouse.po.OldHouseMasterPo;

public class OldHouseAgentVo  implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	
	private Long userId;		// dsj_user表id
	private String companyName;		// 公司
	private String tellPhone;		// 电话号码
	private String agentPhone;
	private Long oldMasterId;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTellPhone() {
		return tellPhone;
	}
	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	public Long getOldMasterId() {
		return oldMasterId;
	}
	public void setOldMasterId(Long oldMasterId) {
		this.oldMasterId = oldMasterId;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}

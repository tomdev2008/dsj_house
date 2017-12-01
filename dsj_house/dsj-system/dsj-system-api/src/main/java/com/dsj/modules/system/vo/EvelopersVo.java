package com.dsj.modules.system.vo;
import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.po.FunctionPo;

public class EvelopersVo extends EvelopersPo implements Serializable {

	//添加字段
	private String username;
	private String password;
	private Integer createPerson;
	private Integer updatePerson;
	private Long evelopersId;
	private String updateWhat;//是否可修改user表
	
	//查询字段
	private String fullName;
	private Integer status;
	
	//地区名称
	private String areaName1;
	private String areaName2;
	private String areaName3;
	
	private String loupanNames;
	private String loupanIds;
	
	private String phone;
	
	private Boolean hasCommitAudit;
	
	private List<NewHouseDirectoryAuthPo> idAndNameList;
	
	public List<NewHouseDirectoryAuthPo> getIdAndNameList() {
		return idAndNameList;
	}
	public void setIdAndNameList(List<NewHouseDirectoryAuthPo> idAndNameList) {
		this.idAndNameList = idAndNameList;
	}
	public Long getEvelopersId() {
		return evelopersId;
	}
	public void setEvelopersId(Long evelopersId) {
		this.evelopersId = evelopersId;
	}
	public Integer getUpdatePerson() {
		return updatePerson;
	}
	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	public Integer getCreatePerson() {
		return createPerson;
	}
	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUpdateWhat() {
		return updateWhat;
	}
	public void setUpdateWhat(String updateWhat) {
		this.updateWhat = updateWhat;
	}
	public String getAreaName1() {
		return areaName1;
	}
	public void setAreaName1(String areaName1) {
		this.areaName1 = areaName1;
	}
	public String getAreaName2() {
		return areaName2;
	}
	public void setAreaName2(String areaName2) {
		this.areaName2 = areaName2;
	}
	public String getAreaName3() {
		return areaName3;
	}
	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}
	public String getLoupanNames() {
		return loupanNames;
	}
	public void setLoupanNames(String loupanNames) {
		this.loupanNames = loupanNames;
	}
	public String getLoupanIds() {
		return loupanIds;
	}
	public void setLoupanIds(String loupanIds) {
		this.loupanIds = loupanIds;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Boolean getHasCommitAudit() {
		if(StringUtils.isNotBlank(getName())&&StringUtils.isNotBlank(getOperationCard())){
			return true;
		}else{
			return false;
		}
	}
	public void setHasCommitAudit(Boolean hasCommitAudit) {
		this.hasCommitAudit = hasCommitAudit;
	}
	
}

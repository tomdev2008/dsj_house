package com.dsj.modules.system.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: fengqh.
 * @创建时间: 2017-06-16 15:44:20.
 * @版本: 1.0 .
 */
public class EmployeePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;		// user表id
	private Integer empNum;		// 员工编码
	private String realName;		// 真实姓名
	private String tellPhone;		// 电话号码
	private Integer department;		// 所属部门
	private Integer defaultRole;		// 默认角色
	private Integer extendRole;		// 扩展角色
	private String birthday;		// 出生日期
	private String education;		// 学历
	private String major;		// 专业背景
	private String skilled;		// 擅长
	private Date updateTime;		// update_time
	private Integer deleteFlag;		// delete_flag
	private String roleName;    //角色名称
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public EmployeePo() {
		super();
	}

	public EmployeePo(Long id){
		this();
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Integer getEmpNum() {
		return empNum;
	}

	public void setEmpNum(Integer empNum) {
		this.empNum = empNum;
	}
	
	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getTellPhone() {
		return tellPhone;
	}

	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	
	public Integer getDepartment() {
		return department;
	}

	public void setDepartment(Integer department) {
		this.department = department;
	}
	
	public Integer getDefaultRole() {
		return defaultRole;
	}

	public void setDefaultRole(Integer defaultRole) {
		this.defaultRole = defaultRole;
	}
	
	public Integer getExtendRole() {
		return extendRole;
	}

	public void setExtendRole(Integer extendRole) {
		this.extendRole = extendRole;
	}
	
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	
	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}
	
	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}
	
	public String getSkilled() {
		return skilled;
	}

	public void setSkilled(String skilled) {
		this.skilled = skilled;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public String toString() {
		return "EmployeePo [userId=" + userId + ", empNum=" + empNum + ", realName=" + realName + ", tellPhone="
				+ tellPhone + ", department=" + department + ", defaultRole=" + defaultRole + ", extendRole="
				+ extendRole + ", birthday=" + birthday + ", education=" + education + ", major=" + major + ", skilled="
				+ skilled + ", updateTime=" + updateTime + ", deleteFlag=" + deleteFlag + "]";
	}
	
}
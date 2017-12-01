package com.dsj.modules.system.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.dsj.modules.system.po.AgentPo;

public class AgentVo extends AgentPo {

	private String areaName;
	private String auditStatusName;
	private String isSellNewHouseName;
	private String businessName;
	private String cityName;
	private String provinceName;
	private String auditName;
	private String avatar;	//头像
	private String username;  //user表中的username
	private String realname;  //user表中的realname
	private String phone;	//uer表中的phone
	private String companyName;
	private Integer objectId;
	private Date lookTime;
	private String lookTimeString;
	private String mobile;
	private String linkAddress; //经纪人地址
	private String agName; //经纪人等级
	private Integer totalScore; //经纪人评分
	private String gradeName;  //经纪人等级
	private String smallIcon;  //等级小图片
	private String bigIcon;  //等级大图片
	private Integer atiTotalCount; //服务态度总评数
	private Integer atiHighCount;  //服务态度好评数
	private Integer atiMidCount;  //服务态度中评数
	private Integer atiBadCount;  //服务态度差评数
	private Integer proTotalCount; //专业水平总评数
	private Integer proHighCount; //专业水平好评数
	private Integer proMidCount; //专业水平中评数
	private Integer proBadCount;  //专业水平中评数
	private String responName;//责任楼盘
	
	public String getMobile() {
		if(mobile==null){
			mobile="";
		}
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getLookTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         if(this.lookTime==null){
        	 lookTimeString=null;
         }else{
        	 lookTimeString = sdf.format(this.lookTime);
         }
		return lookTimeString;
		
	}

	public void setLookTimeString(String lookTimeString) {
		this.lookTimeString = lookTimeString;
	}
	public Date getLookTime() {
		return lookTime;
	}
	public void setLookTime(Date lookTime) {
		this.lookTime = lookTime;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getAuditName() {
		return auditName;
	}
	public void setAuditName(String auditName) {
		this.auditName = auditName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAuditStatusName() {
		return auditStatusName;
	}
	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}
	public String getIsSellNewHouseName() {
		return isSellNewHouseName;
	}
	public void setIsSellNewHouseName(String isSellNewHouseName) {
		this.isSellNewHouseName = isSellNewHouseName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLinkAddress() {
		return linkAddress;
	}

	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}

	public String getAgName() {
		return agName;
	}

	public void setAgName(String agName) {
		this.agName = agName;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public String getSmallIcon() {
		return smallIcon;
	}

	public void setSmallIcon(String smallIcon) {
		this.smallIcon = smallIcon;
	}

	public String getBigIcon() {
		return bigIcon;
	}

	public void setBigIcon(String bigIcon) {
		this.bigIcon = bigIcon;
	}

	public Integer getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}

	public Integer getAtiTotalCount() {
		return atiTotalCount;
	}

	public void setAtiTotalCount(Integer atiTotalCount) {
		this.atiTotalCount = atiTotalCount;
	}

	public Integer getAtiHighCount() {
		return atiHighCount;
	}

	public void setAtiHighCount(Integer atiHighCount) {
		this.atiHighCount = atiHighCount;
	}

	public Integer getAtiMidCount() {
		return atiMidCount;
	}

	public void setAtiMidCount(Integer atiMidCount) {
		this.atiMidCount = atiMidCount;
	}

	public Integer getAtiBadCount() {
		return atiBadCount;
	}

	public void setAtiBadCount(Integer atiBadCount) {
		this.atiBadCount = atiBadCount;
	}

	public Integer getProTotalCount() {
		return proTotalCount;
	}

	public void setProTotalCount(Integer proTotalCount) {
		this.proTotalCount = proTotalCount;
	}

	public Integer getProHighCount() {
		return proHighCount;
	}

	public void setProHighCount(Integer proHighCount) {
		this.proHighCount = proHighCount;
	}

	public Integer getProMidCount() {
		return proMidCount;
	}

	public void setProMidCount(Integer proMidCount) {
		this.proMidCount = proMidCount;
	}

	public Integer getProBadCount() {
		return proBadCount;
	}

	public void setProBadCount(Integer proBadCount) {
		this.proBadCount = proBadCount;
	}

	public String getResponName() {
		return responName;
	}

	public void setResponName(String responName) {
		this.responName = responName;
	}
	
	
}

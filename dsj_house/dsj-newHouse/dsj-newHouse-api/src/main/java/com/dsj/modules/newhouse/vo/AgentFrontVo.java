package com.dsj.modules.newhouse.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AgentFrontVo implements Serializable{
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
	
	private Integer isDuty;
	public String getMobile() {
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
	
	private Long userId;		// dsj_user表id
	private String avatarUrl;		// 头像链接
	private String avatarReUrl;     //头像 长方形


	private Integer agentCode;		// 经纪人id
	private Date applyTime;		// 申请时间
	private Integer company;		// 公司
	private String name;		// 姓名
	private String tellPhone;		// 电话号码
	private String province;		// 省
	private String city;		// 所在城市
	private String area;		// 行政区域
	private String business;		// 商圈
	private String mainCommunity;		// 主营小区
	private String isSellNewHouse;		// 是否卖新房1：是，0：否
	private String sellBuilding;		// 销售新房楼盘
	private String idNumber;		// 身份证号
	private Integer isExternalRegist;		// 是否外部注册 是 1，否0
	private Integer auditStatus;		// 审核状态 0:外部申请为未认证，1：外部申请已认证成功，2：外部申请认证待审核，3：申请驳回
	private String dutyBuilding;		// 责任楼盘
	private String idCardUrl;		// 身份证照片链接 正面
	private String idCardUrlBack;		// 身份证照片链接 背面
	private String cardUrl;		// 工牌图片链接
	private Integer updateUser;		// 更新人
	private Integer createUser;		// 创建人
	private Integer auditUser;		// 审核人
	private Date auditTime;		// 审核时间
	private Date updateTime;		// update_time	

	private String timeString;
	
	private Integer sort;
	private String sex;		// 性别
	private String birthday;		// 生日
	private String star;		// 星座
	private String blood;		// 血型
	private String email;		// email
	private String education;		// 学历
	private String major;		// 专业
	private String signature;		// 签名
	private Integer workyears;		// 工作年数
	private String skill;		// skill
	private String feature;		// 个人特色 例 1，2，3，4
	private String background;		// 背景图链接
	
	private String[] featureArray; //个人特色
	private String[] sellBuildingList;
	private String[] mainCommunityList;
	
	private String year;
	private String month;
	private String day;
	private Long pcAgentId;
	
	private float score;
	private Integer takeLook;
	private Integer deal;
	
	public String getIdCardUrlBack() {
		return idCardUrlBack;
	}
	public void setIdCardUrlBack(String idCardUrlBack) {
		this.idCardUrlBack = idCardUrlBack;
	}

	public String getAvatarReUrl() {
		return avatarReUrl;
	}

	public void setAvatarReUrl(String avatarReUrl) {
		this.avatarReUrl = avatarReUrl;
	}
	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public Integer getTakeLook() {
		return takeLook;
	}

	public void setTakeLook(Integer takeLook) {
		this.takeLook = takeLook;
	}

	public Integer getDeal() {
		return deal;
	}

	public void setDeal(Integer deal) {
		this.deal = deal;
	}

	public String[] getMainCommunityList() {
		if(this.mainCommunity!=null){
			mainCommunityList = this.mainCommunity.split(",");
		}
		return mainCommunityList;
	}

	public void setMainCommunityList(String[] mainCommunityList) {
		this.mainCommunityList = mainCommunityList;
	}

	
	
	public String[] getSellBuildingList() {
		if(this.sellBuilding!=null){
			sellBuildingList = this.sellBuilding.split(",");
		}
		return sellBuildingList;
	}

	public void setSellBuildingList(String[] sellBuildingList) {
		this.sellBuildingList = sellBuildingList;
	}

	public String getYear() {
		if(this.birthday!=null){
			year = this.birthday.split("-")[0];
		}
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		if(this.birthday!=null){
			month = this.birthday.split("-")[1];
		}
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDay() {
		if(this.birthday!=null){
			day = this.birthday.split("-")[0];
		}
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}



	public String[] getFeatureArray() {
		if(this.feature!=null){
			featureArray = this.feature.split(",");
		}
		return featureArray;
	}

	public void setFeatureArray(String[] featureArray) {
		this.featureArray = featureArray;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getStar() {
		return star;
	}

	public void setStar(String star) {
		this.star = star;
	}

	public String getBlood() {
		return blood;
	}

	public void setBlood(String blood) {
		this.blood = blood;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public Integer getWorkyears() {
		return workyears;
	}

	public void setWorkyears(Integer workyears) {
		this.workyears = workyears;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getFeature() {
		return feature;
	}

	public void setFeature(String feature) {
		this.feature = feature;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getTimeString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
         if(this.applyTime==null){
        	 timeString=null;
         }else{
        	 timeString = sdf.format(this.applyTime);
         }
		return timeString;
		
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public Integer getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(Integer agentCode) {
		this.agentCode = agentCode;
	}
	
	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	
	public Integer getCompany() {
		return company;
	}

	public void setCompany(Integer company) {
		this.company = company;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTellPhone() {
		return tellPhone;
	}

	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}
	
	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}
	
	public String getMainCommunity() {
		return mainCommunity;
	}

	public void setMainCommunity(String mainCommunity) {
		this.mainCommunity = mainCommunity;
	}
	
	public String getIsSellNewHouse() {
		return isSellNewHouse;
	}

	public void setIsSellNewHouse(String isSellNewHouse) {
		this.isSellNewHouse = isSellNewHouse;
	}
	
	public String getSellBuilding() {
		return sellBuilding;
	}

	public void setSellBuilding(String sellBuilding) {
		this.sellBuilding = sellBuilding;
	}
	
	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}
	
	public Integer getIsExternalRegist() {
		return isExternalRegist;
	}

	public void setIsExternalRegist(Integer isExternalRegist) {
		this.isExternalRegist = isExternalRegist;
	}
	
	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	public String getDutyBuilding() {
		return dutyBuilding;
	}

	public void setDutyBuilding(String dutyBuilding) {
		this.dutyBuilding = dutyBuilding;
	}
	
	public String getIdCardUrl() {
		return idCardUrl;
	}

	public void setIdCardUrl(String idCardUrl) {
		this.idCardUrl = idCardUrl;
	}
	
	public String getCardUrl() {
		return cardUrl;
	}

	public void setCardUrl(String cardUrl) {
		this.cardUrl = cardUrl;
	}
	
	public Integer getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Integer updateUser) {
		this.updateUser = updateUser;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	public Integer getAuditUser() {
		return auditUser;
	}

	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	
	public Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getPcAgentId() {
		return pcAgentId;
	}

	public void setPcAgentId(Long pcAgentId) {
		this.pcAgentId = pcAgentId;
	}

	public Integer getIsDuty() {
		return isDuty;
	}

	public void setIsDuty(Integer isDuty) {
		this.isDuty = isDuty;
	}
	
}

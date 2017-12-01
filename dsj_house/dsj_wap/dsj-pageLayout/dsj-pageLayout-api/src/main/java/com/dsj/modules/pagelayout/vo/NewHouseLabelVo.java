package com.dsj.modules.pagelayout.vo;

import com.dsj.common.entity.BaseEntity;

public class NewHouseLabelVo extends BaseEntity{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	 private Long labelId; //标签ID
     private String name; //楼盘名称
     private String houseType; //主推户型
     private String referencePriceMin; //周边最低单价
     private String referencePriceMan; //周边最低单价
     private Integer indexPrice;//首页展示价格
 	private String priceName;//价格名称
 	private String pricedw;//价格单位
     private String phone; //400电话
     private Integer room;		// 室
 	 private Integer hall;		// 厅
 	 private Integer toilet;		// 卫
 	 private String wyTypeName; //物业类型
 	 private long houseTypeId; //户型id
 	 private String picture;   //图片
 	 private Long agentId;     //经纪人ID
 	 private String agentName; //经纪人名字
 	 private String comment;  //经纪人评论
 	 private String avatarUrl; //经纪人头像
 	 private String linkAddress; //链接地址
 	 private String recommend;  //推荐语
 	 private Long pcNewHouseId; //pc新房ID
 	 private Long newHouseId; //新房楼盘ID
 	 private String oldHouseId; //二手房ID
 	 private Long pcOldHouseId;//pc二手房ID
 	 private String companyTypes; //委托中介
 	 private String occupyArea; //占地面积
 	 private String typeGroupName; //朝向
 	 private String agentAddress;//经纪人路径
 	 private String totalScore; //经纪人评分
 	 private String agName;//经纪人等级名称
 	 private String smallIcon;  //等级小图片
	 private String bigIcon;  //等级大图片
	 private String address; //连接
	 private String agentPhone; // 经纪人400电话
	 private String userName;//经纪人账号
	 private String userId;//经纪人id
	 private Integer price;
	 
	 
	
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getIndexPrice() {
		return indexPrice;
	}
	public void setIndexPrice(Integer indexPrice) {
		this.indexPrice = indexPrice;
	}
	public String getPriceName() {
		return priceName;
	}
	public void setPriceName(String priceName) {
		this.priceName = priceName;
	}
	public String getPricedw() {
		return pricedw;
	}
	public void setPricedw(String pricedw) {
		this.pricedw = pricedw;
	}
	public String getReferencePriceMin() {
		return referencePriceMin;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getReferencePriceMan() {
		return referencePriceMan;
	}
	public void setReferencePriceMan(String referencePriceMan) {
		this.referencePriceMan = referencePriceMan;
	}
	public void setReferencePriceMin(String referencePriceMin) {
		this.referencePriceMin = referencePriceMin;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getRoom() {
		return room;
	}
	public void setRoom(Integer room) {
		this.room = room;
	}
	public Integer getHall() {
		return hall;
	}
	public void setHall(Integer hall) {
		this.hall = hall;
	}
	public Integer getToilet() {
		return toilet;
	}
	public void setToilet(Integer toilet) {
		this.toilet = toilet;
	}
	public String getWyTypeName() {
		return wyTypeName;
	}
	public void setWyTypeName(String wyTypeName) {
		this.wyTypeName = wyTypeName;
	}
	public long getHouseTypeId() {
		return houseTypeId;
	}
	public void setHouseTypeId(long houseTypeId) {
		this.houseTypeId = houseTypeId;
	}
	public Long getLabelId() {
		return labelId;
	}
	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Long getAgentId() {
		return agentId;
	}
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}
	public String getAgentName() {
		return agentName;
	}
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getRecommend() {
		return recommend;
	}
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	public Long getPcNewHouseId() {
		return pcNewHouseId;
	}
	public void setPcNewHouseId(Long pcNewHouseId) {
		this.pcNewHouseId = pcNewHouseId;
	}
	public Long getNewHouseId() {
		return newHouseId;
	}
	public void setNewHouseId(Long newHouseId) {
		this.newHouseId = newHouseId;
	}
	
	public String getOldHouseId() {
		return oldHouseId;
	}
	public void setOldHouseId(String oldHouseId) {
		this.oldHouseId = oldHouseId;
	}
	public Long getPcOldHouseId() {
		return pcOldHouseId;
	}
	public void setPcOldHouseId(Long pcOldHouseId) {
		this.pcOldHouseId = pcOldHouseId;
	}
	public String getCompanyTypes() {
		return companyTypes;
	}
	public void setCompanyTypes(String companyTypes) {
		this.companyTypes = companyTypes;
	}
	public String getOccupyArea() {
		return occupyArea;
	}
	public void setOccupyArea(String occupyArea) {
		this.occupyArea = occupyArea;
	}
	public String getTypeGroupName() {
		return typeGroupName;
	}
	public void setTypeGroupName(String typeGroupName) {
		this.typeGroupName = typeGroupName;
	}
	public String getAgentAddress() {
		return agentAddress;
	}
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}
	public String getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(String totalScore) {
		this.totalScore = totalScore;
	}
	public String getAgName() {
		return agName;
	}
	public void setAgName(String agName) {
		this.agName = agName;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAgentPhone() {
		return agentPhone;
	}
	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
	
     
}

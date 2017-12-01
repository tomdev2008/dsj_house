package com.dsj.modules.newhouse.vo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

public class NewHouseDirectoryAuthVo extends NewHouseDirectoryAuthPo {

	//添加字段
	private String referencePrice;// 参考单价
	private String propertyRightName;// 产权年限
	private String openTime;// 开盘时间
	private String handTime;// 交房时间
	private String realname;//提交人
	private String houseName;//房型
	private String pictureUrl;//图片字符串
	private String picture; // 展示图片
	//修改会显字段
	private ArrayList<List<NewHouseOpenHandTimeAuthPo>> openTimeList;
	private ArrayList<List<NewHouseOpenHandTimeAuthPo>> handTimeList;
	private List<NewHouseWyMsgAuthPo> wyMsgList;
	private String[] phoneList;
	private String[] discountList;
	private String[] prepList;
	private String housetypeNames;//修改户型回显
	private List<NewHouseSubwayAuthPo> subWayList;
	//查询
	private String maintainName; //经纪人名字
    private String shelves; //上下架
    private String eveloperName;//开发商名字
	
    private String recommend;  //推荐
    private Integer flag;
    
    private Integer isDuty;//是否是楼盘维护人，1是，0否
    private Long agentId; //经纪人id;
    private String isDutyString; 
    private String mobile;//五位短号
    private Integer objectId;//关注 的 id
    private String newsTitle;//动态名称，列表展示一条
    private String lookTime;
    private String areaName3;
    
    private String[]  dicTraitList; //楼盘特点
	private Integer indexPrice;//首页展示价格
	private String priceName;//价格名称
	private String pricedw;//价格单位
    
	public String[] getDicTraitList() {
		return dicTraitList;
	}

	public void setDicTraitList(String[] dicTraitList) {
		this.dicTraitList = dicTraitList;
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
	public String getAreaName3() {
		return areaName3;
	}

	public void setAreaName3(String areaName3) {
		this.areaName3 = areaName3;
	}

	public String getLookTime() {
		return lookTime;
	}

	public void setLookTime(String lookTime) {
		this.lookTime = lookTime;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public Integer getObjectId() {
		return objectId;
	}

	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<NewHouseSubwayAuthPo> getSubWayList() {
		return subWayList;
	}

	public void setSubWayList(List<NewHouseSubwayAuthPo> subWayList) {
		this.subWayList = subWayList;
	}

	public String getHousetypeNames() {
		return housetypeNames;
	}

	public void setHousetypeNames(String housetypeNames) {
		this.housetypeNames = housetypeNames;
	}

	public String getMaintainName() {
		return maintainName;
	}

	public void setMaintainName(String maintainName) {
		this.maintainName = maintainName;
	}

	public String getShelves() {
		return shelves;
	}

	public void setShelves(String shelves) {
		this.shelves = shelves;
	}

	public String getEveloperName() {
		return eveloperName;
	}

	public void setEveloperName(String eveloperName) {
		this.eveloperName = eveloperName;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public ArrayList<List<NewHouseOpenHandTimeAuthPo>> getOpenTimeList() {
		return openTimeList;
	}

	public void setOpenTimeList(ArrayList<List<NewHouseOpenHandTimeAuthPo>> openTimeList) {
		this.openTimeList = openTimeList;
	}

	public ArrayList<List<NewHouseOpenHandTimeAuthPo>> getHandTimeList() {
		return handTimeList;
	}

	public void setHandTimeList(ArrayList<List<NewHouseOpenHandTimeAuthPo>> handTimeList) {
		this.handTimeList = handTimeList;
	}

	public String[] getPrepList() {
		return prepList;
	}

	public void setPrepList(String[] prepList) {
		this.prepList = prepList;
	}

	public String[] getPhoneList() {
		return phoneList;
	}

	public void setPhoneList(String[] phoneList) {
		this.phoneList = phoneList;
	}

	public String[] getDiscountList() {
		return discountList;
	}

	public void setDiscountList(String[] discountList) {
		this.discountList = discountList;
	}

	public List<NewHouseWyMsgAuthPo> getWyMsgList() {
		return wyMsgList;
	}

	public void setWyMsgList(List<NewHouseWyMsgAuthPo> wyMsgList) {
		this.wyMsgList = wyMsgList;
	}

	public String getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(String referencePrice) {
		this.referencePrice = referencePrice;
	}

	public String getPropertyRightName() {
		return propertyRightName;
	}

	public void setPropertyRightName(String propertyRightName) {
		this.propertyRightName = propertyRightName;
	}

	public String getOpenTime() {
		return openTime;
	}

	public void setOpenTime(String openTime) {
		this.openTime = openTime;
	}

	public String getHandTime() {
		return handTime;
	}

	public void setHandTime(String handTime) {
		this.handTime = handTime;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}


	public String getRecommend() {
		return recommend;
	}

	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Integer getIsDuty() {
		return isDuty;
	}

	public void setIsDuty(Integer isDuty) {
		this.isDuty = isDuty;
	}

	public Long getAgentId() {
		return agentId;
	}

	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	public String getIsDutyString() {
		return isDutyString;
	}

	public void setIsDutyString(String isDutyString) {
		this.isDutyString = isDutyString;
	}
	
	


	
  
   
	

}

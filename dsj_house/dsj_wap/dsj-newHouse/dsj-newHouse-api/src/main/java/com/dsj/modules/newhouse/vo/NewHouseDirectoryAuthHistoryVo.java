package com.dsj.modules.newhouse.vo;

import java.util.List;

import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

public class NewHouseDirectoryAuthHistoryVo extends NewHouseDirectoryAuthHistoryPo {

	//添加字段
	private String referencePrice;// 参考单价
	private String propertyRightName;// 产权年限
	private String openTime;// 开盘时间
	private String handTime;// 交房时间
	private String realname;//提交人
	
	//修改会显字段
	private List<NewHouseOpenHandTimeAuthPo> openTimeList;
	private List<NewHouseOpenHandTimeAuthPo> handTimeList;
	private List<NewHouseWyMsgAuthPo> wyMsgList;
	private String[] phoneList;
	private String[] discountList;
	private String[] prepList;
	
	//查询
	private String maintainName; //经纪人名字
    private String shelves; //上下架
    private String eveloperName;//开发商名字
    private String authRealName;//审核人名称
	
    
	public String getAuthRealName() {
		return authRealName;
	}

	public void setAuthRealName(String authRealName) {
		this.authRealName = authRealName;
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

	public List<NewHouseOpenHandTimeAuthPo> getOpenTimeList() {
		return openTimeList;
	}

	public void setOpenTimeList(List<NewHouseOpenHandTimeAuthPo> openTimeList) {
		this.openTimeList = openTimeList;
	}

	public List<NewHouseOpenHandTimeAuthPo> getHandTimeList() {
		return handTimeList;
	}

	public void setHandTimeList(List<NewHouseOpenHandTimeAuthPo> handTimeList) {
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

}

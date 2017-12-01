package com.dsj.modules.newhouse.vo;

import java.io.Serializable;
import java.util.List;

import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.vo.HouseNewsVo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

public class NewHouseFrontVo extends NewHouseDirectoryAuthPo implements Serializable {

	private String dicTraitName;//楼盘特点字符串
	private String[]  dicTraitList; //楼盘特点
	private String aroundPriceMin; //均价 小
	private String aroundPriceMax; //均价 大
	private Integer indexPrice;//首页展示价格
	private String priceName;//价格名称
	private String pricedw;//价格单位
	private String houseTypeMsg;//楼盘户型
	private String houseTypeStrPc;
	private String newOpenTime;//最新开盘时间
	private String areaDetail;//区域商圈
	private String pcMobile;//pc分机号
	private String wapMobile;
	private String appMobile;
	
	private HouseNewsVo newsPo;//楼盘动态
	
	private List<NewHouseTypeCountVo> houseTypeList;//户型总数集合
	private Integer houseTypeTotalCount; //户型总数
	
	private List<AgentFrontVo> agentVoList;//经纪人集合
	
	private AgentFrontVo agentVo;//责任楼盘维护人 
	
	private List<NewHouseWyMsgAuthPo> wyMsgList;//物业相关结合
	private List<NewHouseOpenHandTimeAuthPo> openTimeList;//开盘时间
	private List<NewHouseOpenHandTimeAuthPo> handTimeList;//交房时间
	
	private List<NewHouseLikeVo> likeList;//猜你喜欢
	
	private List<NewHouseTypeAuthPo> newHouseTypeList;
	
	private String  imageUrl;//相册封面
	private Integer imageSize;
	
	private Long houseNewsCount;//楼盘动态数量
	
	private Boolean checked;//wap是否关注，
	
	private Boolean loginStatus;// wap 是否登录
	
	private Integer tabType = 3;//详情页tab选项  默认3
	
	public String getHouseTypeStrPc() {
		return houseTypeStrPc;
	}
	public void setHouseTypeStrPc(String houseTypeStrPc) {
		this.houseTypeStrPc = houseTypeStrPc;
	}
	public List<NewHouseOpenHandTimeAuthPo> getHandTimeList() {
		return handTimeList;
	}
	public void setHandTimeList(List<NewHouseOpenHandTimeAuthPo> handTimeList) {
		this.handTimeList = handTimeList;
	}
	public String getWapMobile() {
		return wapMobile;
	}
	public void setWapMobile(String wapMobile) {
		this.wapMobile = wapMobile;
	}
	public String getAppMobile() {
		return appMobile;
	}
	public void setAppMobile(String appMobile) {
		this.appMobile = appMobile;
	}
	public String getPcMobile() {
		return pcMobile;
	}
	public void setPcMobile(String pcMobile) {
		this.pcMobile = pcMobile;
	}
	public List<NewHouseLikeVo> getLikeList() {
		return likeList;
	}
	public void setLikeList(List<NewHouseLikeVo> likeList) {
		this.likeList = likeList;
	}
	public List<NewHouseOpenHandTimeAuthPo> getOpenTimeList() {
		return openTimeList;
	}
	public void setOpenTimeList(List<NewHouseOpenHandTimeAuthPo> openTimeList) {
		this.openTimeList = openTimeList;
	}
	public String getDicTraitName() {
		return dicTraitName;
	}
	public void setDicTraitName(String dicTraitName) {
		this.dicTraitName = dicTraitName;
	}
	public String[] getDicTraitList() {
		return dicTraitList;
	}
	public void setDicTraitList(String[] dicTraitList) {
		this.dicTraitList = dicTraitList;
	}
	public String getAroundPriceMin() {
		return aroundPriceMin;
	}
	public void setAroundPriceMin(String aroundPriceMin) {
		this.aroundPriceMin = aroundPriceMin;
	}
	public String getAroundPriceMax() {
		return aroundPriceMax;
	}
	public void setAroundPriceMax(String aroundPriceMax) {
		this.aroundPriceMax = aroundPriceMax;
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
	public String getHouseTypeMsg() {
		return houseTypeMsg;
	}
	public void setHouseTypeMsg(String houseTypeMsg) {
		this.houseTypeMsg = houseTypeMsg;
	}
	public String getNewOpenTime() {
		return newOpenTime;
	}
	public void setNewOpenTime(String newOpenTime) {
		this.newOpenTime = newOpenTime;
	}
	public String getAreaDetail() {
		return areaDetail;
	}
	public void setAreaDetail(String areaDetail) {
		this.areaDetail = areaDetail;
	}
	public List<NewHouseTypeCountVo> getHouseTypeList() {
		return houseTypeList;
	}
	public void setHouseTypeList(List<NewHouseTypeCountVo> houseTypeList) {
		this.houseTypeList = houseTypeList;
	}
	public Integer getHouseTypeTotalCount() {
		return houseTypeTotalCount;
	}
	public void setHouseTypeTotalCount(Integer houseTypeTotalCount) {
		this.houseTypeTotalCount = houseTypeTotalCount;
	}
	public List<AgentFrontVo> getAgentVoList() {
		return agentVoList;
	}
	public void setAgentVoList(List<AgentFrontVo> agentVoList) {
		this.agentVoList = agentVoList;
	}
	public List<NewHouseWyMsgAuthPo> getWyMsgList() {
		return wyMsgList;
	}
	public void setWyMsgList(List<NewHouseWyMsgAuthPo> wyMsgList) {
		this.wyMsgList = wyMsgList;
	}
	public List<NewHouseTypeAuthPo> getNewHouseTypeList() {
		return newHouseTypeList;
	}
	public void setNewHouseTypeList(List<NewHouseTypeAuthPo> newHouseTypeList) {
		this.newHouseTypeList = newHouseTypeList;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public AgentFrontVo getAgentVo() {
		return agentVo;
	}
	public void setAgentVo(AgentFrontVo agentVo) {
		this.agentVo = agentVo;
	}

	public Integer getImageSize() {
		return imageSize;
	}
	public void setImageSize(Integer imageSize) {
		this.imageSize = imageSize;
	}

	public Long getHouseNewsCount() {
		return houseNewsCount;
	}
	public void setHouseNewsCount(Long houseNewsCount) {
		this.houseNewsCount = houseNewsCount;
	}
	public Boolean getChecked() {
		return checked;
	}
	public void setChecked(Boolean checked) {
		this.checked = checked;
	}
	public Boolean getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(Boolean loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Integer getTabType() {
		return tabType;
	}
	public void setTabType(Integer tabType) {
		this.tabType = tabType;
	}
	public HouseNewsVo getNewsPo() {
		return newsPo;
	}
	public void setNewsPo(HouseNewsVo newsPo) {
		this.newsPo = newsPo;
	}
	
	
	
}

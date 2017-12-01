package com.dsj.modules.rent.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-10 11:15:27.
 * @版本: 1.0 .
 */
public class RentHouseOriginPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long dicId;		// 楼盘字典
	private String roomName;		// 房间名称
	private Integer rentType;		// 出租类型  1整租  2合租
	private Double rentPrice;		// 租金
	private Integer payType;		// 付款方式
	private Integer door;		// 室
	private Integer hall;		// 厅
	private Integer toilet;		// 卫
	private Integer kitchen;		// 厨
	private Integer houseFloor;		// 所在楼层
	private Integer totalFloors;		// 总楼层
	private String wyType;		// 房屋类型
	private String zxType;		// 装修类型
	private Integer orientation;		// 朝向
	private Integer genderType;		// 性别限制
	private Double area;		// 面积
	private String detailPoint;		// 房屋配置
	private String houseTitle;		// 房源标题
	private String houseDesc;		// 房源描述
	private Date issueDate;		// 交房时间
	private Integer status;		// 状态
	private Integer deleteFlag;		// 删除标记
	private Integer createPerson;		// 创建人
	private Integer updatePerson;		// 更新人
	private Date updateTime;		// 更新时间
	private String pictureUrl ;		//封面图片
	
	
	public RentHouseOriginPo() {
		super();
	}

	public RentHouseOriginPo(Long id){
		this();
	}
	

	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}
	
	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	
	public Integer getRentType() {
		return rentType;
	}

	public void setRentType(Integer rentType) {
		this.rentType = rentType;
	}
	
	public Double getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(Double rentPrice) {
		this.rentPrice = rentPrice;
	}
	
	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	
	public Integer getDoor() {
		return door;
	}

	public void setDoor(Integer door) {
		this.door = door;
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
	
	public Integer getHouseFloor() {
		return houseFloor;
	}

	public void setHouseFloor(Integer houseFloor) {
		this.houseFloor = houseFloor;
	}
	
	public Integer getTotalFloors() {
		return totalFloors;
	}

	public void setTotalFloors(Integer totalFloors) {
		this.totalFloors = totalFloors;
	}
	
	public String getWyType() {
		return wyType;
	}

	public void setWyType(String wyType) {
		this.wyType = wyType;
	}
	
	public String getZxType() {
		return zxType;
	}

	public void setZxType(String zxType) {
		this.zxType = zxType;
	}
	
	public Integer getOrientation() {
		return orientation;
	}

	public void setOrientation(Integer orientation) {
		this.orientation = orientation;
	}
	
	public Integer getGenderType() {
		return genderType;
	}

	public void setGenderType(Integer genderType) {
		this.genderType = genderType;
	}
	
	public Double getArea() {
		return area;
	}

	public void setArea(Double area) {
		this.area = area;
	}
	
	public String getDetailPoint() {
		return detailPoint;
	}

	public void setDetailPoint(String detailPoint) {
		this.detailPoint = detailPoint;
	}
	
	public String getHouseTitle() {
		return houseTitle;
	}

	public void setHouseTitle(String houseTitle) {
		this.houseTitle = houseTitle;
	}
	
	public String getHouseDesc() {
		return houseDesc;
	}

	public void setHouseDesc(String houseDesc) {
		this.houseDesc = houseDesc;
	}
	
	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Integer getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Integer createPerson) {
		this.createPerson = createPerson;
	}
	
	public Integer getUpdatePerson() {
		return updatePerson;
	}

	public void setUpdatePerson(Integer updatePerson) {
		this.updatePerson = updatePerson;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getKitchen() {
		return kitchen;
	}

	public void setKitchen(Integer kitchen) {
		this.kitchen = kitchen;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
}
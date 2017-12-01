package com.dsj.modules.newhouse.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public class NewHouseTypeAuthHistoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String houseName;		// 户型名称
	private Long houseStatus;		// 户型状态
	private String houseStatusName;		// 户型状态名称
	private Long wyType;		// 户型类型
	private String wyTypeName;		// 物业name
	private Integer room;		// 室
	private Integer hall;		// 厅
	private Integer toilet;		// 卫
	private Integer kitchen;		// 厨
	private String referencePrice;		// 参考单价
	private String builtUp;		// 建筑面积
	private Integer orientations;		// 朝向
	private String orientationsName;		// 朝向名称
	private Double floor;		// 层高
	private String describes;		// 户型描述
	private String distribution;		// 户型分布
	private Long dicId;		// 楼盘
	private Long createPreson;		// 创建人
	private Long updatePreson;		// 修改人
	private Date createDate;		// 创建时间
	private Date updateDate;		// 修改时间
	private Integer deleteFlag;		// 删除标记
	private String imgUrl;		// 户型图地址
	private String balcony;		// 阳
	private String originDicId;		// 原楼盘ID
	private String buildId;		// 栋座ID(不清楚啊)
	private Integer num;		// 总套数
	private String useArea;		// 使用面积
	private String houseType;		// 户型类型
	
	public NewHouseTypeAuthHistoryPo() {
		super();
	}

	public NewHouseTypeAuthHistoryPo(Long id){
		this();
	}
	

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public Long getHouseStatus() {
		return houseStatus;
	}

	public void setHouseStatus(Long houseStatus) {
		this.houseStatus = houseStatus;
	}
	
	public String getHouseStatusName() {
		return houseStatusName;
	}

	public void setHouseStatusName(String houseStatusName) {
		this.houseStatusName = houseStatusName;
	}
	
	public Long getWyType() {
		return wyType;
	}

	public void setWyType(Long wyType) {
		this.wyType = wyType;
	}
	
	public String getWyTypeName() {
		return wyTypeName;
	}

	public void setWyTypeName(String wyTypeName) {
		this.wyTypeName = wyTypeName;
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
	
	public Integer getKitchen() {
		return kitchen;
	}

	public void setKitchen(Integer kitchen) {
		this.kitchen = kitchen;
	}
	
	public String getReferencePrice() {
		return referencePrice;
	}

	public void setReferencePrice(String referencePrice) {
		this.referencePrice = referencePrice;
	}
	
	public String getBuiltUp() {
		return builtUp;
	}

	public void setBuiltUp(String builtUp) {
		this.builtUp = builtUp;
	}
	
	public Integer getOrientations() {
		return orientations;
	}

	public void setOrientations(Integer orientations) {
		this.orientations = orientations;
	}
	
	public String getOrientationsName() {
		return orientationsName;
	}

	public void setOrientationsName(String orientationsName) {
		this.orientationsName = orientationsName;
	}
	
	public Double getFloor() {
		return floor;
	}

	public void setFloor(Double floor) {
		this.floor = floor;
	}
	
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	public String getDistribution() {
		return distribution;
	}

	public void setDistribution(String distribution) {
		this.distribution = distribution;
	}
	
	public Long getDicId() {
		return dicId;
	}

	public void setDicId(Long dicId) {
		this.dicId = dicId;
	}
	
	public Long getCreatePreson() {
		return createPreson;
	}

	public void setCreatePreson(Long createPreson) {
		this.createPreson = createPreson;
	}

	public Long getUpdatePreson() {
		return updatePreson;
	}

	public void setUpdatePreson(Long updatePreson) {
		this.updatePreson = updatePreson;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
	public String getBalcony() {
		return balcony;
	}

	public void setBalcony(String balcony) {
		this.balcony = balcony;
	}
	
	public String getOriginDicId() {
		return originDicId;
	}

	public void setOriginDicId(String originDicId) {
		this.originDicId = originDicId;
	}
	
	public String getBuildId() {
		return buildId;
	}

	public void setBuildId(String buildId) {
		this.buildId = buildId;
	}
	
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}
	
	public String getUseArea() {
		return useArea;
	}

	public void setUseArea(String useArea) {
		this.useArea = useArea;
	}
	
	public String getHouseType() {
		return houseType;
	}

	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	
}
package com.dsj.modules.mobile400.po;

import java.util.Date;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 13:41:36.
 * @版本: 1.0 .
 */
public class MobilePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String mobile;		// 400电话后五位
	private String mobilePro;		// 权证400电话
	private Integer type;		// 1楼盘 2经纪人  3权证代办人
	private Long houseId;		// 楼盘id
	private String houseName;		// house_name
	private String houseCode;     //楼盘编码
	private Long agentId;		// 经纪人id
	private String agentName;		// 经纪人名称
	private String agentCode;
	private Integer propertyId;		//权证代办人id
	private String propertyName;	//权证代办人名称
	private Integer channel;		// 1pc 2wap 3app
	private String phone;		// 目标号码(多个时候逗号拼接)
	private String msgPhone;		// 短信号码
	private Date lastBindingTime;		// 最后一次绑定时间
	private Integer way;		// 轮询模式
	private Long timeOut;		// 超时时长
	private Integer status;		// 1未绑定 2已绑定
	private Date updateTime;		// 修改时间(绑定时间)
	private Long createPreson;		// create_preson
	private Long updatePreson;		// update_preson


	public String getMobilePro() {
		return mobilePro;
	}

	public void setMobilePro(String mobilePro) {
		this.mobilePro = mobilePro;
	}

	public Integer getPropertyId() {
		return propertyId;
	}

	public void setPropertyId(Integer propertyId) {
		this.propertyId = propertyId;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}

	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}

	public MobilePo() {
		super();
	}

	public MobilePo(Long id){
		this();
	}
	

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	
	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
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
	
	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getMsgPhone() {
		return msgPhone;
	}

	public void setMsgPhone(String msgPhone) {
		this.msgPhone = msgPhone;
	}
	
	public Date getLastBindingTime() {
		return lastBindingTime;
	}

	public void setLastBindingTime(Date lastBindingTime) {
		this.lastBindingTime = lastBindingTime;
	}
	
	public Integer getWay() {
		return way;
	}

	public void setWay(Integer way) {
		this.way = way;
	}
	
	public Long getTimeOut() {
		return timeOut;
	}

	public void setTimeOut(Long timeOut) {
		this.timeOut = timeOut;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	
}
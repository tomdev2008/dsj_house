package com.dsj.modules.mobile400.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-20 13:41:50.
 * @版本: 1.0 .
 */
public class MobileDetailPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String extcode;		// 分机号
	private String ani;		// 主叫
	private String dni;		// 被叫
	private String callertime;		// 通话时长
	private String callresult;		// 通话结果
	private String cityname;		// 归属地
	private Date startdate;		// 开始时间
	private String agentCode;		// agent_code
	private String agentName;		// agent_name
	private String houseCode;		// house_code
	private String houseName;		// house_name
	private Integer propertyId;
	private String propertyName;
	private String recorderwav;		// 录音
	private Integer type;		// 1楼盘 2经纪人  3权证代办人
	private Integer channel;
	private Integer lastid;
	
	private String channelName;
	private String callresultName;
	
	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getCallresultName() {
		return callresultName;
	}

	public void setCallresultName(String callresultName) {
		this.callresultName = callresultName;
	}
	
	public Integer getLastid() {
		return lastid;
	}

	public void setLastid(Integer lastid) {
		this.lastid = lastid;
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

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		this.channel = channel;
	}

	public MobileDetailPo() {
		super();
	}

	public MobileDetailPo(Long id){
		this();
	}
	

	public String getExtcode() {
		return extcode;
	}

	public void setExtcode(String extcode) {
		this.extcode = extcode;
	}
	
	public String getAni() {
		return ani;
	}

	public void setAni(String ani) {
		this.ani = ani;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	public String getCallertime() {
		return callertime;
	}

	public void setCallertime(String callertime) {
		this.callertime = callertime;
	}
	
	public String getCallresult() {
		return callresult;
	}

	public void setCallresult(String callresult) {
		this.callresult = callresult;
	}
	
	public String getCityname() {
		return cityname;
	}

	public void setCityname(String cityname) {
		this.cityname = cityname;
	}
	
	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	
	public String getAgentCode() {
		return agentCode;
	}

	public void setAgentCode(String agentCode) {
		this.agentCode = agentCode;
	}
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getHouseCode() {
		return houseCode;
	}

	public void setHouseCode(String houseCode) {
		this.houseCode = houseCode;
	}
	
	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	
	public String getRecorderwav() {
		return recorderwav;
	}

	public void setRecorderwav(String recorderwav) {
		this.recorderwav = recorderwav;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
}
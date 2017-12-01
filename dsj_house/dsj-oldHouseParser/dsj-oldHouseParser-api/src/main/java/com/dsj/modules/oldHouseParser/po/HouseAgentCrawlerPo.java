package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:21.
 * @版本: 1.0 .
 */
public class HouseAgentCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String originHouseId;		// 源房源id
	private Long oldMasterCrawlerId;		// 房源ID
	private String agentName;		// 经纪人姓名
	private String agentPhone;		// 电话
	private Integer companyType;		// 1 大搜家 2 链接 3 麦田 4 中原地产 5 我爱我家
	private String originAgentPicUrl;		// 源经纪人头像连接
	private String agentPicUrl;		// 经纪人头像连接
	private Long createPerson;		// 创建人
	private Long updatePreson;		// 修改人
	private Date updateTime;		// 修改时间
	private Integer deleteFlag;		// 删除标记
	
	private String agentUrl;
	
	public HouseAgentCrawlerPo() {
		super();
	}

	public HouseAgentCrawlerPo(Long id){
		this();
	}
	

	public String getOriginHouseId() {
		return originHouseId;
	}

	public void setOriginHouseId(String originHouseId) {
		this.originHouseId = originHouseId;
	}
	
	public Long getOldMasterCrawlerId() {
		return oldMasterCrawlerId;
	}

	public void setOldMasterCrawlerId(Long oldMasterCrawlerId) {
		this.oldMasterCrawlerId = oldMasterCrawlerId;
	}
	
	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}
	
	public String getAgentPhone() {
		return agentPhone;
	}

	public void setAgentPhone(String agentPhone) {
		this.agentPhone = agentPhone;
	}
	
	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}
	
	public String getOriginAgentPicUrl() {
		return originAgentPicUrl;
	}

	public void setOriginAgentPicUrl(String originAgentPicUrl) {
		this.originAgentPicUrl = originAgentPicUrl;
	}
	
	public String getAgentPicUrl() {
		return agentPicUrl;
	}

	public void setAgentPicUrl(String agentPicUrl) {
		this.agentPicUrl = agentPicUrl;
	}
	
	public Long getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}
	
	public Long getUpdatePreson() {
		return updatePreson;
	}

	public void setUpdatePreson(Long updatePreson) {
		this.updatePreson = updatePreson;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	

	public String getAgentUrl() {
		return agentUrl;
	}

	public void setAgentUrl(String agentUrl) {
		this.agentUrl = agentUrl;
	}

	@Override
	public String toString() {
		return "HouseAgentCrawlerPo [originHouseId=" + originHouseId + ", oldMasterCrawlerId=" + oldMasterCrawlerId
				+ ", agentName=" + agentName + ", agentPhone=" + agentPhone + ", companyType=" + companyType
				+ ", originAgentPicUrl=" + originAgentPicUrl + ", agentPicUrl=" + agentPicUrl + ", createPerson="
				+ createPerson + ", updatePreson=" + updatePreson + ", updateTime=" + updateTime + ", deleteFlag="
				+ deleteFlag + "]";
	}
	
}
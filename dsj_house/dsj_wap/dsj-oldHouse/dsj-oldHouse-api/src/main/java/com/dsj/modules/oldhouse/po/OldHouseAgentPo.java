package com.dsj.modules.oldhouse.po;

import com.dsj.common.entity.BaseEntity;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 18:24:08.
 * @版本: 1.0 .
 */
public class OldHouseAgentPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long oldMasterId;		// 房源ID
	private String agentName;		// 经纪人姓名
	private String agentPhone;		// 电话
	private Integer companyType;		// 公司名
	private String companyTypeName;
	private String agentPicUrl;		// 经纪人连接
	private Long createPerson;		// 创建人
	private Long updatePreson;		// 修改人
	private Date updateTime;		// 修改时间
	private Integer deleteFlag;		// 删除标记
	private Long userId;		// 经纪人用户id
	private String ccode;//公司编码
	
	private String ico;
	
	
	private String dsjAgentName;
	private String dsjAgentPhone;
	private String dsjAgentCompanyName;
	private String dsjShortName;
	private String dsjAvatarReUrl;
	private String dsjAvatarUrl;
	
	private String gradeName;
	private String smallIcon;
	private String bigIcon;
	private String atiTotalCount;
	
	private String username;
	
	private BigDecimal price;//房源价格
	
	private BigDecimal agentFree;//中介费
	
	private String mobile;
	public OldHouseAgentPo() {
		super();
	}

	public OldHouseAgentPo(Long id){
		this();
	}
	

	public Long getOldMasterId() {
		return oldMasterId;
	}

	public void setOldMasterId(Long oldMasterId) {
		this.oldMasterId = oldMasterId;
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
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getDsjAgentName() {
		return dsjAgentName;
	}

	public void setDsjAgentName(String dsjAgentName) {
		this.dsjAgentName = dsjAgentName;
	}

	public String getDsjAgentPhone() {
		return dsjAgentPhone;
	}

	public void setDsjAgentPhone(String dsjAgentPhone) {
		this.dsjAgentPhone = dsjAgentPhone;
	}

	public String getDsjAgentCompanyName() {
		return dsjAgentCompanyName;
	}

	public void setDsjAgentCompanyName(String dsjAgentCompanyName) {
		this.dsjAgentCompanyName = dsjAgentCompanyName;
	}

	public String getDsjShortName() {
		return dsjShortName;
	}

	public void setDsjShortName(String dsjShortName) {
		this.dsjShortName = dsjShortName;
	}

	public String getDsjAvatarUrl() {
		return dsjAvatarUrl;
	}

	public void setDsjAvatarUrl(String dsjAvatarUrl) {
		this.dsjAvatarUrl = dsjAvatarUrl;
	}

	public String getDsjAvatarReUrl() {
		return dsjAvatarReUrl;
	}

	public void setDsjAvatarReUrl(String dsjAvatarReUrl) {
		this.dsjAvatarReUrl = dsjAvatarReUrl;
	}

	public String getCompanyTypeName() {
		if(companyType!=null && CompanyTypeEnum.getEnum(companyType)!=null){
			return CompanyTypeEnum.getEnum(companyType).getDesc();
		}
		return companyTypeName;
	}

	public void setCompanyTypeName(String companyTypeName) {
		this.companyTypeName = companyTypeName;
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

	public String getAtiTotalCount() {
		return atiTotalCount;
	}

	public void setAtiTotalCount(String atiTotalCount) {
		this.atiTotalCount = atiTotalCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getAgentFree() {
		if(companyType!=null && CompanyTypeEnum.getEnum(companyType)!=null&&price!=null){
			if(companyType==CompanyTypeEnum.LIANJIA.getValue()){
				agentFree=price.multiply(new BigDecimal(0.022));
			}else if(companyType==CompanyTypeEnum.MAITIAN.getValue()||
					companyType==CompanyTypeEnum.ZHONGYUAN.getValue()||
					companyType==CompanyTypeEnum.WAWJ.getValue()){
				agentFree=price.multiply(new BigDecimal(0.02));
			}
		}
		return agentFree;
	}

	public void setAgentFree(BigDecimal agentFree) {
		this.agentFree = agentFree;
	}

	public String getIco() {
		return ico;
	}

	public void setIco(String ico) {
		this.ico = ico;
	}

	public String getCcode() {
		return ccode;
	}

	public void setCcode(String ccode) {
		this.ccode = ccode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
	
}
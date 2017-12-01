package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public class FwSkuPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long spuId;		// 服务描述
	private Long typeId;		// 服务类型id
	private String areaCodeOne;		// area_code_one
	private String areaCodeTwo;		// area_code_two
	private String areaCodeThree;		// 三级地区code
	private Double price;		// 服务名称
	private String describes;		// 服务描述
	private String guarantee;		// 服务保障
	private String describeswap;		// 服务描述
	private String guaranteewap;		// 服务保障
	private Integer deleteFlag;		// delete_flag
	private Date updateTime;		// update_time
	
	public FwSkuPo() {
		super();
	}

	public FwSkuPo(Long id){
		this();
	}
	
	public String getDescribeswap() {
		return describeswap;
	}

	public void setDescribeswap(String describeswap) {
		this.describeswap = describeswap;
	}

	public String getGuaranteewap() {
		return guaranteewap;
	}

	public void setGuaranteewap(String guaranteewap) {
		this.guaranteewap = guaranteewap;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	
	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
	
	public String getAreaCodeOne() {
		return areaCodeOne;
	}

	public void setAreaCodeOne(String areaCodeOne) {
		this.areaCodeOne = areaCodeOne;
	}
	
	public String getAreaCodeTwo() {
		return areaCodeTwo;
	}

	public void setAreaCodeTwo(String areaCodeTwo) {
		this.areaCodeTwo = areaCodeTwo;
	}
	
	public String getAreaCodeThree() {
		return areaCodeThree;
	}

	public void setAreaCodeThree(String areaCodeThree) {
		this.areaCodeThree = areaCodeThree;
	}
	
	public String getDescribes() {
		return describes;
	}

	public void setDescribes(String describes) {
		this.describes = describes;
	}
	
	public String getGuarantee() {
		return guarantee;
	}

	public void setGuarantee(String guarantee) {
		this.guarantee = guarantee;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
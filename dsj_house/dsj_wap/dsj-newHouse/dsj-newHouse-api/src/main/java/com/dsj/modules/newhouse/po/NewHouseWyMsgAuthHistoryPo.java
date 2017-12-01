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
public class NewHouseWyMsgAuthHistoryPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long houseId;		// 楼盘ID
	private Long wyType;		// 物业类型ID
	private String wyTypeName;		// wy_type_name
	private Double referencePriceMin;		// 参考单价-最低
	private Double referencePriceMax;		// 参考单价-最高
	private Double totalPriceMin;		// 总价-最低
	private Double totalPriceMax;		// 总价-最高
	private Double payments;		// 最低首付
	private Double monthPay;		// 月供
	private Long propertyRight;		// 产权年限
	private String propertyRightName;		// 产权名称
	private String plotRatio;		// 容积率
	private String propertyFee;		// 物业费
	
	public NewHouseWyMsgAuthHistoryPo() {
		super();
	}

	public NewHouseWyMsgAuthHistoryPo(Long id){
		this();
	}
	

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
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
	
	
	public Double getReferencePriceMin() {
		return referencePriceMin;
	}

	public void setReferencePriceMin(Double referencePriceMin) {
		this.referencePriceMin = referencePriceMin;
	}

	public Double getReferencePriceMax() {
		return referencePriceMax;
	}

	public void setReferencePriceMax(Double referencePriceMax) {
		this.referencePriceMax = referencePriceMax;
	}

	public Double getTotalPriceMin() {
		return totalPriceMin;
	}

	public void setTotalPriceMin(Double totalPriceMin) {
		this.totalPriceMin = totalPriceMin;
	}

	public Double getTotalPriceMax() {
		return totalPriceMax;
	}

	public void setTotalPriceMax(Double totalPriceMax) {
		this.totalPriceMax = totalPriceMax;
	}

	public Double getPayments() {
		return payments;
	}

	public void setPayments(Double payments) {
		this.payments = payments;
	}

	public Double getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(Double monthPay) {
		this.monthPay = monthPay;
	}

	public Long getPropertyRight() {
		return propertyRight;
	}

	public void setPropertyRight(Long propertyRight) {
		this.propertyRight = propertyRight;
	}
	
	public String getPropertyRightName() {
		return propertyRightName;
	}

	public void setPropertyRightName(String propertyRightName) {
		this.propertyRightName = propertyRightName;
	}
	
	public String getPlotRatio() {
		return plotRatio;
	}

	public void setPlotRatio(String plotRatio) {
		this.plotRatio = plotRatio;
	}
	
	public String getPropertyFee() {
		return propertyFee;
	}

	public void setPropertyFee(String propertyFee) {
		this.propertyFee = propertyFee;
	}
	
}
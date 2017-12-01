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
public class FwuserCommentPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long userId;		// 用户id
	private Long fwuserId;		// 服务者id
	private Long spuId;			//服务id
	private Long orderId;		//订单id
	private Integer attitude;		// 服务态度
	private Integer major;		// 专业水平
	private Integer speed;		// 相应速度
	private String Label;
	private String labelNew;
	private String content;		// 评论内容
	private Integer type;		//1差  2中 3好评
	
	public String getLabel() {
		return Label;
	}

	public String getLabelNew() {
		return labelNew;
	}

	public void setLabelNew(String labelNew) {
		this.labelNew = labelNew;
	}

	public void setLabel(String label) {
		Label = label;
	}

	public Long getSpuId() {
		return spuId;
	}

	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getFwuserId() {
		return fwuserId;
	}

	public void setFwuserId(Long fwuserId) {
		this.fwuserId = fwuserId;
	}

	public FwuserCommentPo() {
		super();
	}

	public FwuserCommentPo(Long id){
		this();
	}
	

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Integer getAttitude() {
		return attitude;
	}

	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}
	
	public Integer getMajor() {
		return major;
	}

	public void setMajor(Integer major) {
		this.major = major;
	}
	
	public Integer getSpeed() {
		return speed;
	}

	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
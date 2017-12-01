package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;

import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 13:36:52.
 * @版本: 1.0 .
 */
public class HouseNewsPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer type;     //发布人类型。1：员工 2: 经纪人3：开发商
	private Long houseId;		// 楼盘ID
	private String title;		// 动态名称
	private String content;		// 内容
	private String contentst;		// 内容缩写
	private String pictureUrl;		// 图片
	private Integer auditStatus;		// 审核状态
	private Integer createUser;		// create_user
	private Integer auditUser;		// 审核人
	private Date auditTime;		// 审核时间
	private String auditReason;		// 驳回理由
	private Date updateTime;		// 更新时间
	private Integer delFlag;		// del_flag
	private Integer stick;		//是否置顶1是，0否
	private Integer pccount;		//pc端楼盘动态详情页点击次数统计
	private Integer upDownLine;		// 上下线
	
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Integer getCreateUser() {
		return createUser;
	}
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	public Integer getAuditUser() {
		return auditUser;
	}
	public void setAuditUser(Integer auditUser) {
		this.auditUser = auditUser;
	}
	public Date getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditReason() {
		return auditReason;
	}
	public void setAuditReason(String auditReason) {
		this.auditReason = auditReason;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public Integer getDelFlag() {
		return delFlag;
	}
	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	public Integer getUpDownLine() {
		return upDownLine;
	}
	public void setUpDownLine(Integer upDownLine) {
		this.upDownLine = upDownLine;
	}
	public Integer getStick() {
		return stick;
	}
	public void setStick(Integer stick) {
		this.stick = stick;
	}
	public String getContentst() {
		return contentst;
	}
	public void setContentst(String contentst) {
		this.contentst = contentst;
	}
	public Integer getPccount() {
		return pccount;
	}
	public void setPccount(Integer pccount) {
		this.pccount = pccount;
	}
	
	
	
	
}
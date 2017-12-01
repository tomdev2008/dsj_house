package com.dsj.modules.oldhouse.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-18 18:23:40.
 * @版本: 1.0 .
 */
public class OldMasterAgentRecommendPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer deleteFlag;		// delete_flag
	private Long userId;		// 经纪人id
	private Long oldMasterId;		// 房源id
	
	public OldMasterAgentRecommendPo() {
		super();
	}

	public OldMasterAgentRecommendPo(Long id){
		this();
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
	
	public Long getOldMasterId() {
		return oldMasterId;
	}

	public void setOldMasterId(Long oldMasterId) {
		this.oldMasterId = oldMasterId;
	}
	
}
package com.dsj.modules.pagelayout.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public class PcLabelNewHousePo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long labelId;		// 标签ID
	private Long newHouseId;		// 新房ID
	
	public PcLabelNewHousePo() {
		super();
	}

	public PcLabelNewHousePo(Long id){
		this();
	}
	

	public Long getLabelId() {
		return labelId;
	}

	public void setLabelId(Long labelId) {
		this.labelId = labelId;
	}
	
	public Long getNewHouseId() {
		return newHouseId;
	}

	public void setNewHouseId(Long newHouseId) {
		this.newHouseId = newHouseId;
	}
	
}
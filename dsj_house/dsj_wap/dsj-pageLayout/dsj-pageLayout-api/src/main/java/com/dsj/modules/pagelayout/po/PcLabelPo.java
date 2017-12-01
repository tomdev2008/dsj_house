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
public class PcLabelPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String label;		// 标签
	private Long type;		// 类型（1新房，2二手房）
	
	public PcLabelPo() {
		super();
	}

	public PcLabelPo(Long id){
		this();
	}
	

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
	
	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
}
package com.dsj.modules.pagelayout.vo;

import java.util.List;

import com.dsj.modules.pagelayout.po.WapLabelPo;

public class WapLabelVo extends WapLabelPo{
	private List<WeightAndTypeVo> typeList;

	public List<WeightAndTypeVo> getTypeList() {
		return typeList;
	}

	public void setTypeList(List<WeightAndTypeVo> typeList) {
		this.typeList = typeList;
	}
}

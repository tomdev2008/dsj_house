package com.dsj.modules.fw.vo;

import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.po.FwSpuPo;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public class FwSkuVo extends FwSkuPo {
	
	private String typeName;		// 服务属性名称
	
	private String name; //spu商品名称
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
}
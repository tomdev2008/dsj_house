package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public class DicDaCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Date daTime;		// 回答时间
	private String content;		// 回答内容
	private String dicWenId;		// 问题id
	private String originDicId;		// 源小区id
	private String originDaId;		// origin_da_id
	private Long wenId;		// 业务联系表问id
	
	public DicDaCrawlerPo() {
		super();
	}

	public DicDaCrawlerPo(Long id){
		this();
	}
	

	public Date getDaTime() {
		return daTime;
	}

	public void setDaTime(Date daTime) {
		this.daTime = daTime;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	public String getDicWenId() {
		return dicWenId;
	}

	public void setDicWenId(String dicWenId) {
		this.dicWenId = dicWenId;
	}

	public String getOriginDicId() {
		return originDicId;
	}

	public void setOriginDicId(String originDicId) {
		this.originDicId = originDicId;
	}
	
	public String getOriginDaId() {
		return originDaId;
	}

	public void setOriginDaId(String originDaId) {
		this.originDaId = originDaId;
	}
	
	public Long getWenId() {
		return wenId;
	}

	public void setWenId(Long wenId) {
		this.wenId = wenId;
	}
	
}
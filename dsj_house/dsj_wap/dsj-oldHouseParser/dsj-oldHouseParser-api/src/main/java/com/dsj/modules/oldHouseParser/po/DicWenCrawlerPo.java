package com.dsj.modules.oldHouseParser.po;

import java.util.Date;
import java.util.List;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public class DicWenCrawlerPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Date wenTime;		// 提问时间
	private String originDicId;		// 链家小区id
	private String content;		// 内容
	private String title;		// 标题
	private String classifyName;		// 分类
	private String originWenId;		// 源提问id
	
	List<DicDaCrawlerPo> dicDaCrawlers;
	
	public DicWenCrawlerPo() {
		super();
	}

	public DicWenCrawlerPo(Long id){
		this();
	}
	

	public Date getWenTime() {
		return wenTime;
	}

	public void setWenTime(Date wenTime) {
		this.wenTime = wenTime;
	}
	
	public String getOriginDicId() {
		return originDicId;
	}

	public void setOriginDicId(String originDicId) {
		this.originDicId = originDicId;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}
	
	public String getOriginWenId() {
		return originWenId;
	}

	public void setOriginWenId(String originWenId) {
		this.originWenId = originWenId;
	}

	public List<DicDaCrawlerPo> getDicDaCrawlers() {
		return dicDaCrawlers;
	}

	public void setDicDaCrawlers(List<DicDaCrawlerPo> dicDaCrawlers) {
		this.dicDaCrawlers = dicDaCrawlers;
	}
	
}
package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:24:01.
 * @版本: 1.0 .
 */
public class TradeAreaPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String fullName;		// 商圈全名
	private String name;		// 商圈名称
	private String treePath;		// 路径
	private String areaCode;		// 商圈编码
	private Long parentId;		// 父级节点
	private Integer orders;		// 排序
	private String englishName;		// 拼音全拼
	private String englishHead;		// 拼音全拼简写
	private String level;		// 区域级别
	private String likePinyin;		// 首字母大写
	private Long createPerson;		// 创建人
	private String dimension;			//纬度
	private String accuracy;			//经度
	private Integer deleteFlag;		// delete_flag
	
	public TradeAreaPo() {
		super();
	}

	public TradeAreaPo(Long id){
		this();
	}
	

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTreePath() {
		return treePath;
	}

	public void setTreePath(String treePath) {
		this.treePath = treePath;
	}
	
	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public Integer getOrders() {
		return orders;
	}

	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	
	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	public String getLikePinyin() {
		return likePinyin;
	}

	public void setLikePinyin(String likePinyin) {
		this.likePinyin = likePinyin;
	}
	
	public Long getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(Long createPerson) {
		this.createPerson = createPerson;
	}
	
	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(String accuracy) {
		this.accuracy = accuracy;
	}

	public String getEnglishHead() {
		return englishHead;
	}

	public void setEnglishHead(String englishHead) {
		this.englishHead = englishHead;
	}
	
}
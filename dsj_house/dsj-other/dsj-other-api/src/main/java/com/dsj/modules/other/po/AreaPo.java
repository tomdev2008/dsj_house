package com.dsj.modules.other.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 15:25:36.
 * @版本: 1.0 .
 */
public class AreaPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String fullName;		// 全名
	private String name;		// 名称
	private String treePath;		// 路径
	private String areaCode;		// 地区编码
	private Long parentId;		// 父节点
	private Integer orders;		// 排序
	private Integer isLeaf;		// 是否为叶节点
	private Date createDate;		// 创建时间
	private Date modifyDate;		// 修改时间
	private String englishName;		// 拼音全拼
	private String englishHead;		// 拼音全拼简写
	private String likePinyin;		// 首字母大写
	private String level;		// 城市级别
	private Integer provinceSort;		// 省份排序号
	private Integer deleteFlag;			//删除标记
	private String dimension;			//纬度
	private String accuracy;			//经度
	private Integer isCustom;		 //是否为自定义数据  1 否  2 是
	
	public AreaPo() {
		super();
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public Integer getIsCustom() {
		return isCustom;
	}

	public void setIsCustom(Integer isCustom) {
		this.isCustom = isCustom;
	}

	public AreaPo(Long id){
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
	
	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
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
	
	public Integer getProvinceSort() {
		return provinceSort;
	}

	public void setProvinceSort(Integer provinceSort) {
		this.provinceSort = provinceSort;
	}

	public String getLikePinyin() {
		return likePinyin;
	}

	public void setLikePinyin(String likePinyin) {
		this.likePinyin = likePinyin;
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
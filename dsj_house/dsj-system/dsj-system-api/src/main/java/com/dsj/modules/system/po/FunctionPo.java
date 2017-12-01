package com.dsj.modules.system.po;

import java.util.Date;
import java.util.List;

import com.dsj.common.entity.BaseEntity;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public class FunctionPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String name;		// 名称
	private String uri;		// 地址
	private String pattern;		// pattern
	private String nameCode;		// 标签
	private Date updateTime;		// 修改时间
	private Integer pid;		// 父id
	private String iconUri;		// 图标路径
	private Integer sort;		// 排序
	private Integer type;		// 1 菜单 2按钮
	private Integer level;      //菜单级别
	private boolean checked;  
	private List<FunctionPo> children;
	
	public FunctionPo() {
		super();
	}

	public FunctionPo(Long id){
		this();
	}
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getNameCode() {
		return nameCode;
	}

	public void setNameCode(String nameCode) {
		this.nameCode = nameCode;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIconUri() {
		return iconUri;
	}

	public void setIconUri(String iconUri) {
		this.iconUri = iconUri;
	}
	
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}


	public List<FunctionPo> getChildren() {
		return children;
	}

	public void setChildren(List<FunctionPo> children) {
		this.children = children;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	
	
}
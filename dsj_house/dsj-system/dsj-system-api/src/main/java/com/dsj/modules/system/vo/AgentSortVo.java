package com.dsj.modules.system.vo;

public class AgentSortVo {
	private long id;
	private Integer oldSort;
	private Integer newSort;
	private Integer start;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Integer getOldSort() {
		return oldSort;
	}
	public void setOldSort(Integer oldSort) {
		this.oldSort = oldSort;
	}
	public Integer getNewSort() {
		return newSort;
	}
	public void setNewSort(Integer newSort) {
		this.newSort = newSort;
	}
	public Integer getStart() {
		return start;
	}
	public void setStart(Integer start) {
		this.start = start;
	}
}

package com.dsj.modules.system.vo;

public class FindVo {
    private Integer status;
    private String name;
    private Integer page;
    private Integer isTure;
    private Integer authStatus;
    
    //排序方式
    private Integer orderNum;
    //三级地区
    private String areaCodeThree;
    //排序方式
    private Long spuId;
    
    
	public Long getSpuId() {
		return spuId;
	}
	public void setSpuId(Long spuId) {
		this.spuId = spuId;
	}
	public Integer getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
	public String getAreaCodeThree() {
		return areaCodeThree;
	}
	public void setAreaCodeThree(String areaCodeThree) {
		this.areaCodeThree = areaCodeThree;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getIsTure() {
		return isTure;
	}
	public void setIsTure(Integer isTure) {
		this.isTure = isTure;
	}
	public Integer getAuthStatus() {
		return authStatus;
	}
	public void setAuthStatus(Integer authStatus) {
		this.authStatus = authStatus;
	}
	
    
}

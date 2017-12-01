package com.dsj.common.page;

import org.apache.commons.beanutils.BeanMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @描述: 分页参数传递工具类 .
 * @作者: WuShuicheng .
 * @创建时间: 2013-9-4,下午2:23:47 .
 * @版本: 1.0 .
 */
public class PageParam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6297178964005032338L;
	private int pageNum; // 当前页数
	private int numPerPage; // 每页记录数

	private boolean isNeedCount=true;

	public void setNeedCount(boolean needCount) {
		isNeedCount = needCount;
	}

	public boolean isNeedCount() {
		return isNeedCount;
	}

	public PageParam(){

	}
	public PageParam(int pageNum, int numPerPage) {
		super();
		this.pageNum = pageNum;
		this.numPerPage = numPerPage;
	}

	/** 当前页数 */
	public int getPageNum() {
		return pageNum;
	}

	/** 当前页数 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/** 每页记录数 */
	public int getNumPerPage() {
		return numPerPage;
	}

	/** 每页记录数 */
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public long pageFirst() {
		return (pageNum-1)*numPerPage;
	}
	public long pageSize() {
		return numPerPage;
	}

	public void setiDisplayLength(Integer iDisplayLength) {
		setNumPerPage(iDisplayLength);
	}

	public void setiDisplayStart(Integer iDisplayStart) {
		setPageNum((iDisplayStart/numPerPage)+1);
	}

	public Integer getiDisplayLength() {
		return pageNum;
	}

	public Integer getiDisplayStart() {
		return (pageNum-1)*numPerPage;
	}


	public PageParam asPageParam() {
		return new PageParam(getPageNum(),getNumPerPage());
	}


	public Map<String, Object> asMap(){
		Map<String,Object> ret=new HashMap();
		ret.putAll(new BeanMap(this));
		ret.remove("class");
		return ret;
	}
}

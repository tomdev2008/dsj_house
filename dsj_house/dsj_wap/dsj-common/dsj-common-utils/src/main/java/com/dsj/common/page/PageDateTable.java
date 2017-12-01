package com.dsj.common.page;

import java.util.List;

public class   PageDateTable<T>{
	private List<?> data;//表格数据
	private int iTotalRecords;//实际列
	private int iTotalDisplayRecords;
	private int totalPage;
	private String sEcho;
	
	public PageDateTable(){}
	
	public PageDateTable(PageBean page){
		data=page.getRecordList();//本页的数据列数赋值
		iTotalRecords=page.getTotalCount();//总记录数赋值
		iTotalDisplayRecords=page.getTotalCount();
		totalPage=page.getPageCount();//总页数赋值
	}

	public List<?> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	public int getiTotalRecords() {
		return iTotalRecords;
	}

	public void setiTotalRecords(int iTotalRecords) {
		this.iTotalRecords = iTotalRecords;
	}

	public int getiTotalDisplayRecords() {
		return iTotalDisplayRecords;
	}

	public void setiTotalDisplayRecords(int iTotalDisplayRecords) {
		this.iTotalDisplayRecords = iTotalDisplayRecords;
	}

	public String getsEcho() {
		return sEcho;
	}

	public void setsEcho(String sEcho) {
		this.sEcho = sEcho;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	
}

package com.dsj.common.page;

import java.io.Serializable;
import java.util.List;

import com.dsj.common.enums.ApiStatusCode;

/**
 * api 返回结果通用结果集
 * @author majian
 */
public class LayUiDataTableVo<T> implements Serializable {
	
	private static final long serialVersionUID = 3517344801693747496L ;

	private Integer code;
	
	private String msg;
	
	private List<?> list;

	private Integer count;

	public LayUiDataTableVo(){

	}


	public LayUiDataTableVo(Integer code, String msg, List list, Integer count) {
		this.code = code;
		this.msg = msg;
		this.list = list;
		this.count = count;
	}
	public LayUiDataTableVo(PageBean page){
		this.list=page.getRecordList();
		this.count=page.getTotalCount();
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
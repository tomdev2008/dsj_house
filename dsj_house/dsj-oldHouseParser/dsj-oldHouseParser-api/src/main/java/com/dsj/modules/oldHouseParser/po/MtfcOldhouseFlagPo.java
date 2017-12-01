package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 15:27:00.
 * @版本: 1.0 .
 */
public class MtfcOldhouseFlagPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer pageflag;		// 页数
	private Integer pagenum;		// 页数条目
	private String url;		// 单个楼盘url
	private String pageurl;		// 页数的url
	private String successflag; //是否成功 Y成功  N失败
	
	public MtfcOldhouseFlagPo() {
		super();
	}

	public MtfcOldhouseFlagPo(Long id){
		this();
	}
	

	public Integer getPageflag() {
		return pageflag;
	}

	public void setPageflag(Integer pageflag) {
		this.pageflag = pageflag;
	}
	
	public Integer getPagenum() {
		return pagenum;
	}

	public void setPagenum(Integer pagenum) {
		this.pagenum = pagenum;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getPageurl() {
		return pageurl;
	}

	public void setPageurl(String pageurl) {
		this.pageurl = pageurl;
	}

	public String getSuccessflag() {
		return successflag;
	}

	public void setSuccessflag(String successflag) {
		this.successflag = successflag;
	}

}
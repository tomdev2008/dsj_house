package com.dsj.modules.newhouse.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

public class NewHousePictureVo implements Serializable {

	private String  describe;
	private String url;
	
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
}

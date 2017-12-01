package com.dsj.modules.newhouse.vo;

import java.io.Serializable;

public class NewHousePictureCountVo implements Serializable {

	private Long id;
	private String typegroupname;
	private String pictureUrl;
	private int count;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTypegroupname() {
		return typegroupname;
	}
	public void setTypegroupname(String typegroupname) {
		this.typegroupname = typegroupname;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
}

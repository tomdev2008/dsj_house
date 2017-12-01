package com.dsj.modules.oldhouse.vo;

import java.io.Serializable;

public class OldMasterImageVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String insideImages;
	private String houseTypeImages;
	private String outInsideImages;
	public String getInsideImages() {
		return insideImages;
	}
	public void setInsideImages(String insideImages) {
		this.insideImages = insideImages;
	}
	public String getHouseTypeImages() {
		return houseTypeImages;
	}
	public void setHouseTypeImages(String houseTypeImages) {
		this.houseTypeImages = houseTypeImages;
	}
	public String getOutInsideImages() {
		return outInsideImages;
	}
	public void setOutInsideImages(String outInsideImages) {
		this.outInsideImages = outInsideImages;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	
}

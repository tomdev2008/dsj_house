package com.dsj.modules.newhouse.vo;

import java.io.Serializable;
import java.util.List;

import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;

public class NewHousePictureFrontVo implements Serializable {

	private String name;//相册名称
	private String[] imgList;//相册组集合
	private String imgStr;
	private List<NewHousePictureAuthPo> pictureList;
	
	public List<NewHousePictureAuthPo> getPictureList() {
		return pictureList;
	}
	public void setPictureList(List<NewHousePictureAuthPo> pictureList) {
		this.pictureList = pictureList;
	}
	public String getImgStr() {
		return imgStr;
	}
	public void setImgStr(String imgStr) {
		this.imgStr = imgStr;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String[] getImgList() {
		return imgList;
	}
	public void setImgList(String[] imgList) {
		this.imgList = imgList;
	}
	
}

package com.dsj.modules.pagelayout.vo;

import java.io.Serializable;
import java.util.Date;

public class WapIndexPageListVo implements Serializable{
	private String price;
	private String content1;
	private String content2;
	private String content3;
	private String content4;
	private String content5;
	private String content6;
	private String rate;
	private String picture;
	private Date time;
	private String createUser;
	private Integer type;
	private Integer groupId;
	private Integer objectId;
	
	
	public String getContent6() {
		return content6;
	}
	public void setContent6(String content6) {
		this.content6 = content6;
	}
	public String getRate() {
		return rate;
	}
	public void setRate(String rate) {
		this.rate = rate;
	}
	public String getContent5() {
		return content5;
	}
	public void setContent5(String content5) {
		this.content5 = content5;
	}
	public String getContent4() {
		return content4;
	}
	public void setContent4(String content4) {
		this.content4 = content4;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public Integer getObjectId() {
		return objectId;
	}
	public void setObjectId(Integer objectId) {
		this.objectId = objectId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getGroupId() {
		return groupId;
	}
	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}
	public String getContent1() {
		return content1;
	}
	public void setContent1(String content1) {
		this.content1 = content1;
	}
	public String getContent2() {
		return content2;
	}
	public void setContent2(String content2) {
		this.content2 = content2;
	}
	public String getContent3() {
		return content3;
	}
	public void setContent3(String content3) {
		this.content3 = content3;
	}
	public String getPicture() {
		if(picture!=null&&!picture.equals(" ")){
			if(picture.split(",").length>3){
				picture = picture.split(",")[0]+","+picture.split(",")[1]+","+picture.split(",")[2];
			}
		}
		System.out.println(picture);
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	
}

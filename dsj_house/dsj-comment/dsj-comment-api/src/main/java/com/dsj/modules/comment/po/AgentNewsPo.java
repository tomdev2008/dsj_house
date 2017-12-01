package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;
import java.util.List;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 12:08:06.
 * @版本: 1.0 .
 */
public class AgentNewsPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String content;		// 内容
	private String pictures;		// 图片数，多张，逗号隔开
	private Integer commentNum;		// 评论数
	private Integer likeNum;		// 赞数量
	private Integer createUser;		// create_user
	private Integer delFlag;		// del_flag
	private Integer stick;		// 是否置顶1是，0否
	private Integer negativeNum; //差评数量 和 点赞对应那个
	
	private String[] picturesArr;		// 图片数，多张，逗号隔开
	
	private String timeString;//
	
	



	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}
	
	public Integer getNegativeNum() {
		return negativeNum;
	}

	public void setNegativeNum(Integer negativeNum) {
		this.negativeNum = negativeNum;
	}

	public AgentNewsPo() {
		super();
	}

	public AgentNewsPo(Long id){
		this();
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPictures() {
		return pictures;
	}

	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}
	
	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public Integer getStick() {
		return stick;
	}

	public void setStick(Integer stick) {
		this.stick = stick;
	}

	public String[] getPicturesArr() {
		if(this.pictures.length()>0){
			picturesArr = this.pictures.split(",");
		}
		return picturesArr;
	}
	

	public void setPicturesArr(String[] picturesArr) {
		this.picturesArr = picturesArr;
	}
	
}
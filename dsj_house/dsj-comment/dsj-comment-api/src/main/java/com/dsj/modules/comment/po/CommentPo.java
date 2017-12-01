package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 13:39:32.
 * @版本: 1.0 .
 */
public class CommentPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long objectId;		// 评论的动态id
	private Integer objectType;		// 评论的动态类型  1：经纪人动态评论2：楼盘动态评论，3：经纪人对楼盘点评 4：普通用户点评',
	private Integer commentUser;		// 评论人
	private String content;		// 评论内容
	private Integer replyUser;		// 被回复的人
	private Integer likeNum;		// 点赞数
	private Integer negativeNum;		// 点赞数
	private Integer delFlag;		// del_flag
	private Integer commentNum;		// 评论数量，只针对经纪人楼盘点评	
	private Integer houseId; //评论的新房id		
	private String picture;  //图片
	private String[] pictureArray;
	private String timeString;
	
	public String[] getPictureArray() {
		if(this.picture!=null){
			pictureArray = this.picture.split(",");
		}
		return pictureArray;
	}

	public void setPictureArray(String[] pictureArray) {
		this.pictureArray = pictureArray;
	}

	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	public Integer getHouseId() {
		return houseId;
	}

	public void setHouseId(Integer houseId) {
		this.houseId = houseId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public CommentPo() {
		super();
	}

	public CommentPo(Long id){
		this();
	}
	

	public Long getObjectId() {
		return objectId;
	}

	public void setObjectId(Long objectId) {
		this.objectId = objectId;
	}
	
	public Integer getObjectType() {
		return objectType;
	}

	public void setObjectType(Integer objectType) {
		this.objectType = objectType;
	}
	
	public Integer getCommentUser() {
		return commentUser;
	}

	public void setCommentUser(Integer commentUser) {
		this.commentUser = commentUser;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getReplyUser() {
		return replyUser;
	}

	public void setReplyUser(Integer replyUser) {
		this.replyUser = replyUser;
	}
	
	public Integer getLikeNum() {
		return likeNum;
	}

	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	
	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
	
	public Integer getCommentNum() {
		return commentNum;
	}

	public void setCommentNum(Integer commentNum) {
		this.commentNum = commentNum;
	}

	public Integer getNegativeNum() {
		return negativeNum;
	}

	public void setNegativeNum(Integer negativeNum) {
		this.negativeNum = negativeNum;
	}
	
}
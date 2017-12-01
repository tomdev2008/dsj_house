package com.dsj.modules.comment.vo;

import com.dsj.modules.comment.po.CommentPo;

public class CommentVo extends CommentPo{
	
	
	private String tellPhone;
	private String houseName;//评论新房的名称
	private String commentUserName;//评论人realname
	private String commentUserName2;//评论人username
	private Integer commentUserType; //评论人类型
	private String commentNikename;//评论人昵称
	private String commentUserAvatar;//评论头像
	private String commentPhone;//评论人手机号
	private String replyUserName;//被回复的人realname
	private String replyUserName2;//被回复的人username
	private Integer replyUserType; //被回复的人类型
	private String replyNikename;//被回复的人昵称
	
	private String mobile;//经纪人的400电话
	
	private Integer clicktype;//是否点赞  null没点赞  1 顶  2踩
	
	public String getCommentNikename() {
		return commentNikename;
	}

	public void setCommentNikename(String commentNikename) {
		this.commentNikename = commentNikename;
	}

	public String getReplyNikename() {
		return replyNikename;
	}

	public void setReplyNikename(String replyNikename) {
		this.replyNikename = replyNikename;
	}

	public Integer getCommentUserType() {
		return commentUserType;
	}

	public void setCommentUserType(Integer commentUserType) {
		this.commentUserType = commentUserType;
	}

	public Integer getReplyUserType() {
		return replyUserType;
	}

	public void setReplyUserType(Integer replyUserType) {
		this.replyUserType = replyUserType;
	}

	public String getCommentUserName2() {
		return commentUserName2;
	}

	public void setCommentUserName2(String commentUserName2) {
		this.commentUserName2 = commentUserName2;
	}

	public String getReplyUserName2() {
		return replyUserName2;
	}

	public void setReplyUserName2(String replyUserName2) {
		this.replyUserName2 = replyUserName2;
	}

	public String getCommentUserName() {
		return commentUserName;
	}

	public void setCommentUserName(String commentUserName) {
		this.commentUserName = commentUserName;
	}

	public String getReplyUserName() {
		return replyUserName;
	}

	public void setReplyUserName(String replyUserName) {
		this.replyUserName = replyUserName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getTellPhone() {
		return tellPhone;
	}

	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}

	public String getCommentUserAvatar() {
		return commentUserAvatar;
	}

	public void setCommentUserAvatar(String commentUserAvatar) {
		this.commentUserAvatar = commentUserAvatar;
	}

	public String getCommentPhone() {
		return commentPhone;
	}

	public void setCommentPhone(String commentPhone) {
		this.commentPhone = commentPhone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getClicktype() {
		return clicktype;
	}

	public void setClicktype(Integer clicktype) {
		this.clicktype = clicktype;
	}


}

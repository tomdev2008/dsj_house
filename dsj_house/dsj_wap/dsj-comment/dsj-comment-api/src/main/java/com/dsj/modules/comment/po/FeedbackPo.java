package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 14:12:01.
 * @版本: 1.0 .
 */
public class FeedbackPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String content;		// 反馈意见
	private Integer createUser;		// create_user
	
	public FeedbackPo() {
		super();
	}

	public FeedbackPo(Long id){
		this();
	}
	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}
	
}
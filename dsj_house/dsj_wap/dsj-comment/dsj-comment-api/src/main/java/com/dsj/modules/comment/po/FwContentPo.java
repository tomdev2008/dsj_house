package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-09-04 13:55:25.
 * @版本: 1.0 .
 */
public class FwContentPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String title;		// 标题
	private String content;		// 内容
	private String photo;		// 封面图片
	private Integer status;		// 状态    0：已下线     1：未上线     2：已上线
	private Long createUser;		// create_user
	private Long updateUser;		// update_user
	private Date updateTime;		// update_time
	private Integer dianZan;	//点赞数
	private Integer read1;	//阅读数
	
	public Integer getDianZan() {
		return dianZan;
	}

	public void setDianZan(Integer dianZan) {
		this.dianZan = dianZan;
	}


	public Integer getRead1() {
		return read1;
	}

	public void setRead1(Integer read1) {
		this.read1 = read1;
	}

	public FwContentPo() {
		super();
	}

	public FwContentPo(Long id){
		this();
	}
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public Long getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}
	
	public Long getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(Long updateUser) {
		this.updateUser = updateUser;
	}
	
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
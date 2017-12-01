package com.dsj.modules.comment.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *	点赞统计表
 * @描述: PO.
 * @作者: kk.
 * @创建时间: 2017-07-19 15:34:20.
 * @版本: 1.0 .
 */
public class ClickCountPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Integer objType;		// 类别  动态点赞1  评论点赞2
	private Long objId;		// 关联id
	private Integer type;		// 点赞类型
	private Integer createPreson;		// 创建人
	
	public ClickCountPo() {
		super();
	}

	public ClickCountPo(Long id){
		this();
	}
	
	public Integer getObjType() {
		return objType;
	}

	public void setObjType(Integer objType) {
		this.objType = objType;
	}
	
	public Long getObjId() {
		return objId;
	}

	public void setObjId(Long objId) {
		this.objId = objId;
	}
	
	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	public Integer getCreatePreson() {
		return createPreson;
	}

	public void setCreatePreson(Integer createPreson) {
		this.createPreson = createPreson;
	}
	
}
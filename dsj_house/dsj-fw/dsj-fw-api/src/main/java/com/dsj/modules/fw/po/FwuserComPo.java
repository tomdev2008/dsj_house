package com.dsj.modules.fw.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-09-13 09:57:29.
 * @版本: 1.0 .
 */
public class FwuserComPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private Long commentId;		// 评价表id
	private Long evaluateNum;		// 1服务专业  2经验富足  3流程清晰  4踏实稳重   5认真严谨   6礼貌待客   7诚信正直   8办事高效
	
	public FwuserComPo() {
		super();
	}

	public FwuserComPo(Long id){
		this();
	}
	

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}
	
	public Long getEvaluateNum() {
		return evaluateNum;
	}

	public void setEvaluateNum(Long evaluateNum) {
		this.evaluateNum = evaluateNum;
	}
	
}
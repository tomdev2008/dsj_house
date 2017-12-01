package com.dsj.modules.fw.service;

import java.util.HashMap;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwuserCommentPo;
import com.dsj.modules.fw.vo.FwuserCommentVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public interface FwuserCommentService extends BaseService<FwuserCommentPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	PageBean evaluatePage(PageParam pageParam, Map<String, Object> requestMap);

	FwuserCommentVo getCommentByDetailId(HashMap<String, Object> hashMap);

	void saveFwuserComment(FwuserCommentPo fwuserComment);

	FwuserCommentPo getByOrderId(Long orderId);

	PageBean listNewCommentPage(PageParam pageParam, Map<String, Object> requestMap);

	FwuserCommentVo getPingLv(Long id);

	FwuserCommentVo getPingLvBySpu(Long spuId);



	
}
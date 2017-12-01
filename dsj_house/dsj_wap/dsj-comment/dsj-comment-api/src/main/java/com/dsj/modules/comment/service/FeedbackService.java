package com.dsj.modules.comment.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.comment.po.FeedbackPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-29 14:12:01.
 * @版本: 1.0 .
 */
public interface FeedbackService extends BaseService<FeedbackPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);


	
}
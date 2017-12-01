package com.dsj.modules.comment.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.comment.service.FeedbackService;
import com.dsj.modules.comment.dao.FeedbackDao;
import com.dsj.modules.comment.po.FeedbackPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-29 14:12:01.
 * @版本: 1.0 .
 */
@Service
public class FeedbackServiceImpl  extends BaseServiceImpl<FeedbackDao,FeedbackPo> implements FeedbackService {

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPageList");
	}
	
}
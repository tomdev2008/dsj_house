package com.dsj.modules.comment.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.comment.po.FwContentPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-09-04 13:55:25.
 * @版本: 1.0 .
 */
public interface FwContentService extends BaseService<FwContentPo>{

	void updateMany(String ids);


	
}
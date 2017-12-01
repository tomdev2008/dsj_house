package com.dsj.modules.system.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.MemberPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 19:51:03.
 * @版本: 1.0 .
 */
public interface MemberService extends BaseService<MemberPo>{
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	void updateBlackMany(String ids);
	void updateRemoveBlack(String id);
	
}
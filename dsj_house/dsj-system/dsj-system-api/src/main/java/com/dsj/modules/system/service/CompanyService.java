package com.dsj.modules.system.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.vo.RoleVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface CompanyService extends BaseService<CompanyPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	void insertCompany(CompanyPo company);
	
	List<RoleVo> serviceTypeList();
	
	List<RoleVo> editPageServiceTypeList(String[] serviceTypes);
}
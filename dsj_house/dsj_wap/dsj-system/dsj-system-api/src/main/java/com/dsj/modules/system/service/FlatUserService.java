package com.dsj.modules.system.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.FlatUserPo;
import com.dsj.modules.system.vo.FlatUserVo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-06-15 19:11:19.
 * @版本: 1.0.
 */
public interface FlatUserService extends BaseService<FlatUserPo>{

	void saveFlatUserAdd(FlatUserVo vo) throws Exception;
	
	/**
	 * 
	 * @param vo
	 * @throws Exception
	 * TODO 保存user和flat_user
	 * 2017年6月22日
	 */
	void saveFlatUserUpdate(FlatUserVo vo) throws Exception;
	
	void deleteFlatUsers(String ids);
	
	FlatUserVo getVoById(Long id);

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	
}
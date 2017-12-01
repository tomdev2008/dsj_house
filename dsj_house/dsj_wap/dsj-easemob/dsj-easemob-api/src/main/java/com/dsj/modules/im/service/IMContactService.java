package com.dsj.modules.im.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.im.po.IMContactPo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-02 16:35:02.
 * @版本: 1.0 .
 */
public interface IMContactService extends BaseService<IMContactPo>{

	/**
	 * 根据条件查询[最多查rowCount条] listLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<IMContactPo> listLimitBy(Map<String, Object> paramMap);
	
	/**
	 * 根据条件删除  deleteLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public void deleteLimitBy(Map<String, Object> paramMap);
	
}
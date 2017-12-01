package com.dsj.modules.evaluate.dao;

import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.evaluate.po.AgentInfoPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:52:42.
 * @版本: 1.0 .
 */
public interface AgentInfoDao extends BaseDao<AgentInfoPo> {
	
	/**
	 * 分页条件查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	public PageBean listPageByParams(PageParam pageParam, 
			Map<String, Object> paramMap);
	
}
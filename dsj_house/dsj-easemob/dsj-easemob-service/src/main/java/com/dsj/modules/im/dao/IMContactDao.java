package com.dsj.modules.im.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.im.po.IMContactPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-02 16:35:02.
 * @版本: 1.0 .
 */
public interface IMContactDao extends BaseDao<IMContactPo> {
	
	/**
	 * 根据条件查询[最多查rowCount条] listLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	public List<IMContactPo> listLimitBy(Map<String, Object> paramMap);
	
	/**
	 * 根据条件删除  deleteMoreLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	public void deleteMoreLimitBy(Map<String, Object> paramMap);
	
}
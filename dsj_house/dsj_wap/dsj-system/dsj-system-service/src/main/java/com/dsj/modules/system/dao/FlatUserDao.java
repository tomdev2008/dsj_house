package com.dsj.modules.system.dao;

import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.FlatUserPo;
import com.dsj.modules.system.vo.FlatUserVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 19:11:19.
 * @版本: 1.0 .
 */
public interface FlatUserDao extends BaseDao<FlatUserPo> {
	
	int updateFlatsAudit(Map<String, Object> paramMap);
	
	int deleteByIds(String ids);
	
	public FlatUserVo getVoById(Long id);
	
}
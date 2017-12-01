package com.dsj.modules.comment.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.vo.AgentNewsVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 12:08:06.
 * @版本: 1.0 .
 */
public interface AgentNewsDao extends BaseDao<AgentNewsPo> {
	List<AgentNewsPo> getNewsByUserId(Map<String,Object> map);
	
	void updateAddStick(Map<String,Object> map);
	
	void updateRemoveStick(Map<String,Object> map);
	
	void deleteByIds(Map<String,Object> map);
	
	AgentNewsVo getVoById(long id);
}
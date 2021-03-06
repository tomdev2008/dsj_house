package com.dsj.modules.system.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface EvelopersDao extends BaseDao<EvelopersPo> {

	EvelopersVo getVoById(Long id);

	List<EvelopersVo> listByIds(String ids);

	EvelopersVo getVoBy(Map<String, Object> evelopersMap);

	void updateEveloper(Map<String, Object> paramMap);

	EvelopersPo getEveloper(Map<String, Object> paramMap);
	
}
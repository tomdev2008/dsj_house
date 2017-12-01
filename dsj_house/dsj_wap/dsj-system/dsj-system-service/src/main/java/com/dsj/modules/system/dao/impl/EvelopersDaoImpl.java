package com.dsj.modules.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.EvelopersDao;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Repository
public class EvelopersDaoImpl extends BaseDaoImpl<EvelopersPo> implements EvelopersDao{

	@Override
	public EvelopersVo getVoById(Long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}

	@Override
	public List<EvelopersVo> listByIds(String ids) {
		return sessionTemplate.selectList(getStatement("listByUserIds"), ids);
	}

	@Override
	public EvelopersVo getVoBy(Map<String, Object> evelopersMap) {
		return sessionTemplate.selectOne(getStatement("getVoBy"), evelopersMap);
	}

	@Override
	public void updateEveloper(Map<String, Object> paramMap) {
		sessionTemplate.update("updateEveloper", paramMap);
	}

	@Override
	public EvelopersPo getEveloper(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne("getEveloper", paramMap);
	}
	
}
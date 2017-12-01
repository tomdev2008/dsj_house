package com.dsj.modules.system.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.system.dao.FlatUserDao;
import com.dsj.modules.system.po.FlatUserPo;
import com.dsj.modules.system.vo.FlatUserVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 19:11:19.
 * @版本: 1.0 .
 */
@Repository
public class FlatUserDaoImpl extends BaseDaoImpl<FlatUserPo> implements FlatUserDao{
	
	public int updateFlatsAudit(Map<String, Object> paramMap) {
		int result = sessionTemplate.update(getStatement("updateFlatsAudit"), paramMap);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0
				.newInstance("数据库操作, updateFlatsAudit返回0.{%s}", 
						getStatement("updateFlatsAudit"));
		}
		return result;
	}
	
	public int deleteByIds(String ids) {
		return (int) sessionTemplate.update(getStatement("deleteByIds"), ids);
	}
	
	@Override
	public FlatUserVo getVoById(Long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}
}
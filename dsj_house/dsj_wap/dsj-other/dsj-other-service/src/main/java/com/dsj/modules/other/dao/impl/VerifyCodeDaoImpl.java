package com.dsj.modules.other.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.other.dao.VerifyCodeDao;
import com.dsj.modules.other.po.VerifyCodePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-16 11:58:07.
 * @版本: 1.0 .
 */
@Repository
public class VerifyCodeDaoImpl extends BaseDaoImpl<VerifyCodePo> implements VerifyCodeDao{

	@Override
	public VerifyCodePo getByLast(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("getByLast"), paramMap);
	}
	
}
package com.dsj.modules.other.dao;

import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.VerifyCodePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-16 11:57:31.
 * @版本: 1.0 .
 */
public interface VerifyCodeDao extends BaseDao<VerifyCodePo> {

	VerifyCodePo getByLast(Map<String, Object> paramMap);
	
}
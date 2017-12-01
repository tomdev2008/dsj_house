package com.dsj.modules.comment.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.comment.dao.FwContentDao;
import com.dsj.modules.comment.po.FwContentPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-09-04 13:55:25.
 * @版本: 1.0 .
 */
@Repository
public class FwContentDaoImpl extends BaseDaoImpl<FwContentPo> implements FwContentDao{

	@Override
	public void downByIds(Map<String, Object> map) {
		sessionTemplate.update(getStatement("downByIds"), map);
	}
	
}
package com.dsj.modules.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.MemberDao;
import com.dsj.modules.system.po.MemberPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 19:51:03.
 * @版本: 1.0 .
 */
@Repository
public class MemberDaoImpl extends BaseDaoImpl<MemberPo> implements MemberDao{

	@Override
	public void updateRemoveBlack(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateRemoveBlack"),map);
		
	}

	@Override
	public void updateBlackMany(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateBlackMany"),map);
		
	}


	
}
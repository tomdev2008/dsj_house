package com.dsj.modules.fw.dao.impl;

import java.util.HashMap;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.FwuserCommentDao;
import com.dsj.modules.fw.po.FwuserCommentPo;
import com.dsj.modules.fw.vo.FwuserCommentVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
@Repository
public class FwuserCommentDaoImpl extends BaseDaoImpl<FwuserCommentPo> implements FwuserCommentDao{

	@Override
	public FwuserCommentVo getCommentByDetailId(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectOne("getCommentByDetailId", hashMap);
	}

	@Override
	public FwuserCommentPo getByOrderId(Long orderId) {
		return sessionTemplate.selectOne("getByOrderId", orderId);
	}

	@Override
	public FwuserCommentVo getPingLv(Long id) {
		return sessionTemplate.selectOne("getPingLv", id);
	}

	@Override
	public FwuserCommentVo getPingLvBySpu(Long spuId) {
		return sessionTemplate.selectOne("getPingLvBySpu", spuId);
	}

	
}
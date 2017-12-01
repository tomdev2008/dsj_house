package com.dsj.modules.fw.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.FwSpuDao;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.vo.FwSpuVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
@Repository
public class FwSpuDaoImpl extends BaseDaoImpl<FwSpuPo> implements FwSpuDao{

	@Override
	public List<FwSpuVo> getFwSpuVoList(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getFwSpuVoList"), map);
	}

	@Override
	public List<FwSpuVo> getSanFwSpuVoList(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getSanFwSpuVoList"), map);
	}

	@Override
	public List<FwSpuPo> getThree() {
		return sessionTemplate.selectList(getStatement("getThree"));
	}

}
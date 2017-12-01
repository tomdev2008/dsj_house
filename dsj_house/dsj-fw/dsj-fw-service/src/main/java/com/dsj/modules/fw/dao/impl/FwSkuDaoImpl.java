package com.dsj.modules.fw.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.FwSkuDao;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.vo.FwSkuVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
@Repository
public class FwSkuDaoImpl extends BaseDaoImpl<FwSkuPo> implements FwSkuDao{

	@Override
	public List<FwSkuVo> getFwSkuVoList(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getFwSkuVoList"), map);
	}

	@Override
	public List<FwSkuVo> getSkuNameAndTypeId(Map<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("getSkuNameAndTypeId"), hashMap);
	}

	@Override
	public FwSkuVo getSkuVoBySpuId(Long spuId) {
		return sessionTemplate.selectOne(getStatement("getSkuVoBySpuId"), spuId);
	}

	@Override
	public FwSkuVo getFwSkuVoById(Long skuId) {
		return sessionTemplate.selectOne(getStatement("getFwSkuVoById"), skuId);
	}


}
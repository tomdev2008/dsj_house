package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHouseWyMsgAuthDao;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseWyMsgAuthDaoImpl extends BaseDaoImpl<NewHouseWyMsgAuthPo> implements NewHouseWyMsgAuthDao{

	@Override
	public void deleteByNewHouseId(Long id) {
		sessionTemplate.delete(getStatement("deleteByNewHouseId"), id);
	}

	@Override
	public void updateNewHouseId(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateNewHouseId"), map);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		sessionTemplate.insert(getStatement("saveList"), map);
	}

	@Override
	public Double getMinRefrencePrice(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getMinRefrencePrice"), map);
	}

	@Override
	public Double getMinTotalPrice(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getMinTotalPrice"), map);
	}

	@Override
	public List<NewHouseRecommendVo> listRecommendNewHouseBysq(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listRecommendNewHouseBysq"), map);
	}

	@Override
	public List<NewHouseRecommendVo> listRecommendNewHouseBysq2(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listRecommendNewHouseBysq2"), map);
	}

	@Override
	public List<NewHouseRecommendVo> listRecommendNewHouseBysq3(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listRecommendNewHouseBysq3"), map);
	}

	
}
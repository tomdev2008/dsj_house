package com.dsj.modules.oldHouseParser.dao.impl;

import java.util.List;
import java.util.Map;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.HouseMasterCrawlerDao;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Repository
public class HouseMasterCrawlerDaoImpl extends BaseDaoImpl<HouseMasterCrawlerPo> implements HouseMasterCrawlerDao{

	@Override
	public List<HouseMasterCrawlerPo> getSelectHouseMasterCrawler(Map<String, Object> map) {
		return sessionTemplate.selectList("getSelectHouseMasterCrawler", map);
	}

	@Override
	public List<HouseMasterCrawlerPo> listNewPage(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("listNewPage"), hashMap);
	}

	@Override
	public Long getAllCount() {
		return sessionTemplate.selectOne(getStatement("getAllCount"));
	}

	@Override
	public void updateIsDeal(Integer isDeal, Long id) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("isDeal", isDeal);
		map.put("id", id);
		sessionTemplate.update(getStatement("updateIsDeal"),map);
	}
	
}
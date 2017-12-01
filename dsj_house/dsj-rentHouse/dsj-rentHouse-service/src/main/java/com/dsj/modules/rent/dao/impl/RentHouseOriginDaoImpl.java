package com.dsj.modules.rent.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.rent.dao.RentHouseOriginDao;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.vo.RentCountMapInfoVo;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.rent.vo.WarrantOriginVo;
import com.dsj.modules.solr.vo.RentHouseIndexFiled;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-10 11:15:27.
 * @版本: 1.0 .
 */
@Repository
public class RentHouseOriginDaoImpl extends BaseDaoImpl<RentHouseOriginPo> implements RentHouseOriginDao{

	@Override
	public void updateOriginStatus(String[] ids, Integer status) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("status",status);
		int result = sessionTemplate.update(getStatement("updateOriginStatus"),map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
	}

	@Override
	public void updateDeleteFlag(String[] ids, Integer deleteFlag, Long updatePerson) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("deleteFlag",deleteFlag);
		map.put("updatePerson",updatePerson);
		sessionTemplate.update(getStatement("updateDeleteFlag"),map);
	}


	@Override
	public List<RentHouseOriginPo> getRentHouseList(HashMap<String, Object> map) {
		return sessionTemplate.selectList("getRentHouseList", map);
	}

	@Override
	public List<RentHouseOriginVo> getRentHouse() {
		return sessionTemplate.selectList("getRentHouse");
	}

	@Override
	public void saveAgentOrigin(Map<String, Object> map) {
		sessionTemplate.update(getStatement("saveAgentOrigin"),map);
	}

	@Override
	public void deleteAgentOrigin(Map<String, Object> map) {
		sessionTemplate.update(getStatement("deleteAgentOrigin"),map);
	}

	@Override
	public RentHouseOriginVo getVoById(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getVoById"),map);
	}

	@Override
	public List<String> getSameVillageList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getSameVillageList"),map);
	}

	@Override
	public List<String> getSameTradeList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getSameTradeList"),map);
	}

	@Override
	public List<String> getSimilarList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getSimilarList"),map);
	}
	@Override
	public List<RentHouseOriginVo> findFollow(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findFollow"),map);
	}


	@Override
	public long findFollowCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findFollowCount"), map);
	}


	@Override
	public List<RentHouseOriginVo> lookHistory(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("lookHistory"),map);
	}


	@Override
	public long lookHistoryCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("lookHistoryCount"), map);
	}
	@Override
	public List<String> getLateList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getLateList"),map);
	}

	@Override
	public List<RentHouseOriginVo> getRecommendList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getRecommendList"),map);
	}

	@Override
	public void deleteRentRecommend(Long id) {
		sessionTemplate.update(getStatement("deleteRentRecommend"),id);
	}

	@Override
	public void updateRentRecommend(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateRentRecommend"),map);
	}

	@Override
	public Integer listCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("listCount"),map);
	}

	@Override
	public List<RentHouseOriginPo> selectByLimit(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("selectByLimit"),map);
	}

	@Override
	public List<RentCountMapInfoVo> getRentByCity(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getRentByCity"),map);
	}

	@Override
	public List<RentCountMapInfoVo> getRentByCounty(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getRentByCounty"),map);
	}

	@Override
	public List<RentCountMapInfoVo> getRentByTrade(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getRentByTrade"),map);
	}

	@Override
	public List<RentHouseOriginVo> getRentHouseListPage() {
		return sessionTemplate.selectList("getRentHouseListPage");
	}

	@Override
	public List<RentHouseOriginVo> findAgentRentHouse(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findAgentRentHouse"),map);
	}

	@Override
	public int findAgentRentHouseCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findAgentRentHouseCount"),map);
	}

	@Override
	public List<RentHouseIndexFiled> getRentHouseSolrByIds(String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		return sessionTemplate.selectList(getStatement("getRentHouseSolrByIds"),map);
	}

	@Override
	public void updateOriginRecommend(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateOriginRecommend"),map);
	}

	@Override
	public Integer getCountRecommend(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getCountRecommend"),map);
	}

	@Override
	public Integer getCountShow(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getCountShow"),map);
	}

	@Override
	public void saveAgentShow(Map<String, Object> map) {
		sessionTemplate.update(getStatement("saveAgentShow"),map);
	}

	@Override
	public void deleteAgentShow(Map<String, Object> map) {
		sessionTemplate.update(getStatement("deleteAgentShow"),map);
	}

	@Override
	public List<WarrantOriginVo> getWarrantList() {
		return sessionTemplate.selectList("getWarrantList");
	}

	@Override
	public WarrantOriginVo getWarrant(Map<String, Object> map) {
		return sessionTemplate.selectOne("getWarrant", map);
	}

	@Override
	public void updateWarrant(WarrantOriginVo warrantOriginVo) {
       sessionTemplate.update("updateWarrant", warrantOriginVo);		
	}

	@Override
	public WarrantOriginVo getWarrantVo(Map<String, Object> map) {
		return sessionTemplate.selectOne("getWarrantVo", map);
	}

	@Override
	public void updateWarrantPage(WarrantOriginVo warrantOriginVo) {
        sessionTemplate.update("updateWarrantPage", warrantOriginVo);		
	}



}
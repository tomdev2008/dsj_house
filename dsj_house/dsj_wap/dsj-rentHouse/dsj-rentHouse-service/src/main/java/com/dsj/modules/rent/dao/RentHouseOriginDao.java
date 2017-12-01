package com.dsj.modules.rent.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.vo.RentCountMapInfoVo;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.rent.vo.WarrantOriginVo;
import com.dsj.modules.solr.vo.RentHouseIndexFiled;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-10 11:15:27.
 * @版本: 1.0 .
 */
public interface RentHouseOriginDao extends BaseDao<RentHouseOriginPo> {

	void updateOriginStatus(String[] ids, Integer status);

	void updateDeleteFlag(String[] ids, Integer value, Long userId);

	List<RentHouseOriginPo> getRentHouseList(HashMap<String, Object> map);

	List<RentHouseOriginVo> getRentHouse();

	void saveAgentOrigin(Map<String, Object> map);

	void deleteAgentOrigin(Map<String, Object> map);

	RentHouseOriginVo getVoById(Map<String, Object> map);

	List<String> getSameVillageList(Map<String, Object> map);

	List<String> getSameTradeList(Map<String, Object> map);

	List<String> getSimilarList(Map<String, Object> map);
	
	List<RentHouseOriginVo> findFollow(Map<String, Object> map);
	
	long findFollowCount(Map<String, Object> map);
	
	List<RentHouseOriginVo> lookHistory(Map<String, Object> map);
	
	long lookHistoryCount(Map<String, Object> map);
	
	List<String> getLateList(Map<String, Object> map);

	List<RentHouseOriginVo> getRecommendList(Map<String, Object> map);

	void deleteRentRecommend(Long id);

	void updateRentRecommend(Map<String, Object> map);

	Integer listCount(Map<String, Object> map);

	List<RentHouseOriginPo> selectByLimit(Map<String, Object> map);

	List<RentCountMapInfoVo> getRentByCity(Map<String, Object> paramMap);

	List<RentCountMapInfoVo> getRentByCounty(Map<String, Object> paramMap);

	List<RentCountMapInfoVo> getRentByTrade(Map<String, Object> paramMap);
	
	
	List<RentHouseOriginVo> findAgentRentHouse(Map<String, Object> map);
	
	int findAgentRentHouseCount(Map<String, Object> map);

	List<RentHouseIndexFiled> getRentHouseSolrByIds(String[] ids);

	List<RentHouseOriginVo> getRentHouseListPage();

	void updateOriginRecommend(Map<String, Object> map);

	Integer getCountRecommend(Map<String, Object> map);

	Integer getCountShow(Map<String, Object> map);

	void saveAgentShow(Map<String, Object> map);

	void deleteAgentShow(Map<String, Object> map);

	List<WarrantOriginVo> getWarrantList();

	WarrantOriginVo getWarrant(Map<String, Object> map);

	void updateWarrant(WarrantOriginVo warrantOriginVo);

	WarrantOriginVo getWarrantVo(Map<String, Object> map);

	void updateWarrantPage(WarrantOriginVo warrantOriginVo);


}
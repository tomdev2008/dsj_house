package com.dsj.modules.rent.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.vo.RentCountMapInfoVo;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.rent.vo.WarrantOriginVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-10 11:15:27.
 * @版本: 1.0 .
 */
public interface RentHouseOriginService extends BaseService<RentHouseOriginPo>{

	PageBean listRentOriginPage(PageParam pageParam, Map<String, Object> requestMap);

	void updateOriginStatus(String[] ids, Integer status) throws Exception;

	void updateDeleteFlag(String[] ids, Integer value, Long userId);

	List<RentHouseOriginPo> getRentHouse(HashMap<String, Object> map);

	void updatePictureUrl(Long id, int size, String string);

	List<RentHouseOriginVo> getRentHouseList();

	PageBean listAgentRentOriginPage(PageParam pageParam, Map<String, Object> requestMap);

	void saveAgentOrigin(int houseType, Long houseId, Long userId, Long creatUser);

	void deleteAgentOrigin(int houseType, Long houseId);

	RentHouseOriginVo getVoById(Long id);

	PageBean listDetailOriginPage(PageParam pageParam, Map<String, Object> requestMap);

	List<String> getSameVillageList(Map<String, Object> map);

	List<String> getSameTradeList(Map<String, Object> map);

	List<String> getSimilarList(Map<String, Object> map);
	
	List<String> getLateList(Map<String, Object> map);
	
	List<RentHouseOriginVo> findFollow(Map<String, Object> map);
	
	long findFollowCount(Map<String, Object> map);
	
	List<RentHouseOriginVo> lookHistory(Map<String, Object> map);
	
	long lookHistoryCount(Map<String, Object> map);	

	List<RentHouseOriginVo> getRecommendList(Long id);
	
	void deleteRentRecommend(Long id);

	void updateRentRecommend(Long id, List<String> ids);

	Integer listCount(Map<String, Object> paramMap);

	List<RentHouseOriginPo> selectByLimit(Map<String, Object> map);

	List<RentCountMapInfoVo> getRentByCity(Map<String, Object> paramMap);

	List<RentCountMapInfoVo> getRentByCounty(Map<String, Object> paramMap);

	List<RentCountMapInfoVo> getRentByTrade(Map<String, Object> paramMap);
	
	/**
	 *经纪人的租房
	 */
	List<RentHouseOriginVo> findAgentRentHouse(Map<String, Object> map);
	
	int findAgentRentHouseCount(Map<String, Object> map);

	List<RentHouseOriginVo> getRentHouseListPage();

	void updateOriginRecommend(String[] ids, Integer status, Long id);

	Integer getCountRecommend(Long id, Integer value);

	Integer getCountShow(Long id);

	void saveAgentShow(String[] ids, Long id);

	void deleteAgentShow(String[] ids, Long id);

	List<WarrantOriginVo> getWarrantList();

	WarrantOriginVo getWarrant(Long id);

	void updateWarrant(WarrantOriginVo warrantOriginVo);

	WarrantOriginVo getWarrantVo(Long id);

	void updateWarrantPage(WarrantOriginVo warrantOriginVo);

}
package com.dsj.modules.oldhouse.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;

import com.dsj.modules.oldhouse.vo.OldHouseRecommendVo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-28 14:44:14.
 * @版本: 1.0 .
 */
public interface OldHouseMasterDao extends BaseDao<OldHouseMasterPo> {

	void updateDeleteFlag(String[] ids, Integer deleteFlag);

	int updateImageUrlById(Long id, String imageUrl);

	int updateMasterStatus(String[] ids, Integer status);

	 List<ErshoufangIndexFiled>  getErshoufangSolrByIds(String[] ids);
	 

	List<String> getSameVillageList(Map<String, Object> map);

	List<String> getSameTradeList(Map<String, Object> map);

	List<String> getSimilarList(Map<String, Object> map);

	List<String> getLateList(Map<String, Object> map);

	void deleteOldRecommend(Long id);

	void updateOldRecommend(Map<String, Object> map);

	Integer listCount(Map<String, Object> map);

	List<OldHouseMasterPo> selectByLimit(Map<String, Object> map);

	List<OldHouseRecommendVo> getOldHouseRecommendById(Map<String, Object> map);
	
	List<OldHouseMasterVo> findAgentOldHouse(Map<String, Object> map);
	
	int findAgentOldHouseCount(Map<String, Object> map);
	
	List<OldHouseMasterVo> findFollow(Map<String, Object> map);
	
	int findFollowCount(Map<String, Object> map);
	
	List<OldHouseMasterVo> lookHistory(Map<String, Object> map);
	
	int lookHistoryCount(Map<String, Object> map);

	List<OldHouseMasterVo> getPcByNamePreMatchding(Map<String, Object> map);
	
	List<OldHouseMasterVo> findPcPageOldHouse(Map<String, Object> map);
	
	int findPcPageOldHouseCount(Map<String, Object> map);

	Long getCount(Map<String, Object> map);

	List<OldHouseMasterPo> listNewPage(HashMap<String, Object> hashMap);

	List<OldHouseMasterPo> listJoinDicBy(HashMap<String, Object> map);

	int updateCompanyTypes(Map<String, Object> map);

	List<OldHouseMasterPo> listLeftAgentPage(HashMap<String, Object> listLeftAgentPage);
}
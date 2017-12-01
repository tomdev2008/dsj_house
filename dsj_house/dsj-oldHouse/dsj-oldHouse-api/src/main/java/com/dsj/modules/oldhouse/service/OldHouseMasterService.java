package com.dsj.modules.oldhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.vo.OldHouseRecommendVo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterDetailVo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-28 14:44:14.
 * @版本: 1.0 .
 */
public interface OldHouseMasterService extends BaseService<OldHouseMasterPo>{
	/**
	 * 后台二手房查询
	 * @param pageParam
	 * @param requestMap
	 * @return
	 */
	PageBean listOldMasterPage(PageParam pageParam, Map<String, Object> requestMap);

	void updateDeleteFlag(String[] ids, Integer value);

	int updateMasterStatus(String[] ids, Integer status);
	
	/**
	 * 根据id修改图片
	 * @param objId
	 * @param pictureUrl
	 */
	int updateImageUrlById(Long id, String imageUrl);
	
	/**
	 * 经纪人二手房
	 * @param pageParam
	 * @param requestMap
	 * @return
	 */
	PageBean listAgentOldMasterPage(PageParam pageParam, Map<String, Object> requestMap);
	
	
	/**
	 * 后台保存二手房
	 * @param po
	 * @param agentId
	 * @param id
	 * @return
	 */
	Long saveOldMaster(OldHouseMasterPo po,  Long id);

	void updateOldMaster(OldHouseMasterPo po,Long userId);

	void saveErshoufangSolr(String[] ids);

	void deleteOldRecommend(Long id);

	void updateOldRecommend(Long id, List<String> ids);

	List<String> getSameVillageList(Map<String, Object> map);

	List<String> getSameTradeList(Map<String, Object> map);

	List<String> getSimilarList(Map<String, Object> map);

	List<String> getLateList(Map<String, Object> map);

	Integer listCount(Map<String, Object> paramMap);

	List<OldHouseMasterPo> selectByLimit(Map<String, Object> map);

	List<OldHouseRecommendVo> getOldHouseRecommendById(Long id);
	
	List<OldHouseMasterVo> findAgentOldHouse(Map<String, Object> map);
	
	int findAgentOldHouseCount(Map<String, Object> map);
	
	List<OldHouseMasterVo> findFollow(Map<String, Object> map);
	
	int findFollowCount(Map<String, Object> map);
	
	List<OldHouseMasterVo> lookHistory(Map<String, Object> map);
	
	int lookHistoryCount(Map<String, Object> map);
	
	/**
	 * solr下架
	 * @param ids
	 */
	void deleteErshoufangSolr(String[] ids);

	void updateOldMasterBack(OldHouseMasterPo po, Long userId);	
	
	List<OldHouseMasterVo> getPcByNamePreMatchding(String name);

	OldHouseMasterDetailVo getByWapOldMasterId(Long id);
	
	
	List<OldHouseMasterVo> findPcPageOldHouse(Map<String, Object> map);
	
	int findPcPageOldHouseCount(Map<String, Object> map);

	List<OldHouseMasterPo> listNewPage(HashMap<String, Object> hashMap);
	
	/***
	 * 连接小区查询二手房爬数据对比用
	 * @param map
	 * @return
	 */
	List<OldHouseMasterPo> listJoinDicBy(HashMap<String, Object> map);
	
	
	void updateCompanyTypes(String string, Long id);

	Long getUpAllCount();

	List<OldHouseMasterPo> listLeftAgentPage(HashMap<String, Object> hashMap);
}
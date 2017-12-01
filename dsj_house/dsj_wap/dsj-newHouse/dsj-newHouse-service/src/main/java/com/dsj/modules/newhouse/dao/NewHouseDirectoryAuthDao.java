package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseAuthMsgVo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseLikeVo;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;
import com.dsj.modules.solr.vo.newHouse.NewHouseIndexFiled;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 18:38:48.
 * @版本: 1.0 .
 */
public interface NewHouseDirectoryAuthDao extends BaseDao<NewHouseDirectoryAuthPo> {

	NewHouseDirectoryAuthVo getVoById(Long id);

	void updateNewHouseExamine(Map<String, Object> paramMap);

	void updateIsTrueNewHouses(HashMap<String, Object> hashMap);

	void updateDeleteFlagNewHouses(HashMap<String, Object> hashMap);

	NewHouseDirectoryAuthPo selectNewHouseDirectory(Map<String, Object> paramMap);
	
	int update(Map<String, Object> paramMap);

	int updateEvelopers(Map<String, Object> paramMap);

	NewHouseDirectoryAuthVo getVoBy(HashMap<String, Object> hashMap);


	String getNamesByIds(String ids);


	List<NewHouseDirectoryAuthVo> getPictureList(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthVo> getPicture(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthVo> getPictureLists(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthPo> getAuthHousesByAgentId(Map<String, Object> paraMap);

	long insertNewDynamic(NewHouseDirectoryAuthPo po);

	List<NewHouseDirectoryAuthPo> getIdAndName(String loupanIds);


	List<NewHouseDirectoryAuthPo> getByIds(String ids);

	NewHouseFrontVo getNewHouseFrontVo(Long id);

	Integer listCount(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthPo> selectByLimit(Map<String, Object> map);

	List<NewHouseRecommendVo> selectEightNewUpNewHouse();

	void saveRecommendList(List<NewHouseRecommendVo> list);

	List<NewHouseLikeVo> getLikeNewHouse(Long id);

	void deleteNewHouseById(Long id);

	List<NewHouseIndexFiled> selectNewHouseIndexFiled(HashMap<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> findAgentNewHouse(Map<String, Object> map);
	
	int findAgentNewHouseCount(Map<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> getIndexNewHouse(Map<String, Object> map);
	
	int getIndexNewHouseCount(Map<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> findFollow(Map<String, Object> map);
	
	int findFollowCount(Map<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> lookHistory(Map<String, Object> map);
	
	int lookHistoryCount(Map<String, Object> map);

	String getUpIds(HashMap<String, Object> hashMap);

	void updateTwo(NewHouseDirectoryAuthPo authPo);
	//经纪人 货架新房
	NewHouseDirectoryAuthVo getRecommendNewHouse(long id);

	NewHouseAuthMsgVo getNowStatus(String code);

	void updateEvelopersPerson(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthPo> getElev(HashMap<String, Object> map);

	void updateFengMianImage(Long id);
}
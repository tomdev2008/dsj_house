package com.dsj.modules.newhouse.service;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseAgentFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseLikeVo;


/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 18:38:48.
 * @版本: 1.0 .
 */
public interface NewHouseDirectoryAuthService extends BaseService<NewHouseDirectoryAuthPo>{

	HashMap<String, String> getPrice(List<NewHouseWyMsgAuthPo> wyMsgList, String min, String max);
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	long saveOrUpdateNewHouse(HashMap<String, Object> newHouseMap, Long createPerson) throws ParseException;

	NewHouseDirectoryAuthVo getVoById(Long id);

	void updateNewHouseExamine(Long id, Long long1) throws Exception;

	void updateDeleteFlagNewHouses(List<Integer> ids, Long id);

	void updateIsTrueNewHouses(List<Integer> ids, Long id);

	NewHouseDirectoryAuthPo getWyType(Long id);

	PageBean listDirectoryPage(PageParam pageParam, Map<String, Object> requestMap);

	int updateNewHouse(Long agentId, Long id);

	int updateNewHouseUn(Long id);

	PageBean listEveloperCountPage(PageParam pageParam, Map<String, Object> requestMap);

	int updateEvelopers(Long evelopersId, Long id);

	int updateEveloper(Long id);

	PageBean listNewHousePage(PageParam pageParam, Map<String, Object> requestMap);

	void saveNewHousesAuth(List<NewHouseDirectoryAuthPo> list, long longValue) throws Exception;

	NewHouseDirectoryAuthVo getVoBy(HashMap<String, Object> map);

	void deleteEditNewHouse(NewHouseDirectoryAuthPo authPo);

	/**
	 * 根据楼盘id查询楼盘名称
	 * @param loupanName
	 * @return
	 */
	String getNamesByIds(String ids);
	
	/**
	 * 根据楼盘id查询楼盘名称
	 * @param loupanName
	 * @return
	 */
	List<NewHouseDirectoryAuthPo> getByIds(String ids);


	PageBean listAgentNewHousePage(PageParam pageParam, Map<String, Object> requestMap);

	List<NewHouseDirectoryAuthVo> findPictureList();

	List<NewHouseDirectoryAuthVo> findPicture(Long id);

	List<NewHouseDirectoryAuthVo> getPictureList(Long id);

	PageBean listAgentNewHouseResponPage(PageParam pageParam, Map<String, Object> requestMap);

	List<NewHouseDirectoryAuthPo> getAuthHousesByAgentId(Map<String, Object> paraMap);

	List<NewHouseDirectoryAuthPo> getIdAndName(String loupanIds);

	NewHouseFrontVo getIndexNewHouseFrontVo(Long id);

	NewHouseFrontVo getNewHouseFrontVo(Long id);

	Integer listCount(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthPo> selectByLimit(Map<String, Object> map);

	void setRecommendList(NewHouseDirectoryAuthPo po);

	void saveNewHouseToSolr(HashMap<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> findAgentNewHouse(Map<String, Object> map);
	
	int findAgentNewHouseCount(Map<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> getIndexNewHouse(Map<String, Object> map);
	
	int getIndexNewHouseCount(Map<String, Object> map);
	
	
	List<NewHouseDirectoryAuthVo> findFollow(Map<String, Object> map);
	
	int findFollowCount(Map<String, Object> map);
	
	List<NewHouseDirectoryAuthVo> lookHistory(Map<String, Object> map);
	
	int lookHistoryCount(Map<String, Object> map);

	String getUpIds(int start, int count);

	void saveNewHouseToSolrByAgentId(HashMap<String, Object> map);	
	//经纪人 货架新房
	NewHouseDirectoryAuthVo getRecommendNewHouse(long id);

	void updateEvelopersPerson(Map<String, Object> paramMap);

	List<NewHouseDirectoryAuthPo> getElev(HashMap<String, Object> map);

	void updateFengMianImage(Long id);
	
	NewHouseFrontVo getNewHousePrice(long id);

	PageBean getListDataType(PageParam pageParam, Map<String, Object> requestMaps);
	
	public List<NewHouseLikeVo> getLikeList(Long id);
}
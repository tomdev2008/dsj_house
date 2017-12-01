package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.newhouse.dao.NewHouseDirectoryAuthDao;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseAuthMsgVo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseLikeVo;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;
import com.dsj.modules.solr.vo.newHouse.NewHouseIndexFiled;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 18:38:48.
 * @版本: 1.0 .
 */
@Repository
public class NewHouseDirectoryAuthDaoImpl extends BaseDaoImpl<NewHouseDirectoryAuthPo> implements NewHouseDirectoryAuthDao{

	@Override
	public NewHouseDirectoryAuthVo getVoById(Long id) {
		
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}

	@Override
	public void updateNewHouseExamine(Map<String, Object> paramMap) {
		sessionTemplate.update("updateNewHouseExamine", paramMap);
	}

	@Override
	public void updateIsTrueNewHouses(HashMap<String, Object> hashMap) {
		sessionTemplate.update(getStatement("updateIsTrueNewHouses"), hashMap);
	}

	@Override
	public void updateDeleteFlagNewHouses(HashMap<String, Object> hashMap) {
		sessionTemplate.update(getStatement("updateDeleteFlagNewHouses"), hashMap);
	}

	@Override
	public NewHouseDirectoryAuthPo selectNewHouseDirectory(Map<String, Object> paramMap) {
		
		return sessionTemplate.selectOne("selectNewHouseDirectory", paramMap);
	}
	
	@Override
	public int update(Map<String, Object> paramMap) {
		int result = sessionTemplate.update(getStatement("updateNewHouse"), paramMap);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0
				.newInstance("数据库操作, updateNewHouse返回0.{%s}", 
						getStatement("updateNewHouse"));
		}
		return result;
	}

	@Override
	public int updateEvelopers(Map<String, Object> paramMap) {
		int result = sessionTemplate.update(getStatement("updateEveloper"), paramMap);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0
				.newInstance("数据库操作, updateEveloper返回0.{%s}", 
						getStatement("updateEveloper"));
		}
		return result;
	}

	@Override
	public NewHouseDirectoryAuthVo getVoBy(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectOne(getStatement("getVoByMap"), hashMap);
	}

	@Override
	public String getNamesByIds(String ids) {
		return sessionTemplate.selectOne(getStatement("getNamesByIds"), ids);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> getPictureList(Map<String, Object> paramMap) {
		return sessionTemplate.selectList("getPictureListNewHouse", paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> getPicture(Map<String, Object> paramMap) {
		return sessionTemplate.selectList("getPicture", paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> getPictureLists(Map<String, Object> paramMap) {
		return sessionTemplate.selectList("getPictureLists", paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getAuthHousesByAgentId(Map<String, Object> paraMap) {
		return sessionTemplate.selectList(getStatement("getAuthHousesByAgentId"), paraMap);
	}

	@Override
	public long insertNewDynamic(NewHouseDirectoryAuthPo po) {
		return sessionTemplate.insert(getStatement("insertNewDynamic"), po);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getIdAndName(String loupanIds) {
		return sessionTemplate.selectList(getStatement("getIdAndName"), loupanIds);
		
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getByIds(String ids) {
		return sessionTemplate.selectList(getStatement("getByIds"), ids);
	}


	@Override
	public NewHouseFrontVo getNewHouseFrontVo(Long id) {
		return sessionTemplate.selectOne(getStatement("getNewHouseFrontVo"), id);
	}

	@Override
	public Integer listCount(Map<String, Object> paramMap) {
		return sessionTemplate.selectOne(getStatement("listCount"),paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> selectByLimit(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("selectByLimit"), map);
	}

	@Override
	public List<NewHouseRecommendVo> selectEightNewUpNewHouse() {
		return sessionTemplate.selectList(getStatement("selectEightNewUpNewHouse"));
	}

	@Override
	public void saveRecommendList(List<NewHouseRecommendVo> list) {
		sessionTemplate.insert(getStatement("saveRecommendList"),list);
	}

	@Override
	public List<NewHouseLikeVo> getLikeNewHouse(Long id) {
		return 	sessionTemplate.selectList(getStatement("getLikeNewHouse"),id);
	}

	@Override
	public void deleteNewHouseById(Long id) {
		sessionTemplate.delete(getStatement("deleteNewHouseById"), id);
	}

	@Override
	public List<NewHouseIndexFiled> selectNewHouseIndexFiled(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("selectNewHouseIndexFiled"),map);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> findAgentNewHouse(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findAgentNewHouse"),map);
	}

	@Override
	public int findAgentNewHouseCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findAgentNewHouseCount"),map);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> findFollow(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findFollow"),map);
	}

	@Override
	public int findFollowCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findFollowCount"),map);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> lookHistory(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("lookHistory"),map);
	}

	@Override
	public int lookHistoryCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("lookHistoryCount"),map);
	}

	@Override
	public String getUpIds(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectOne(getStatement("getUpIds"),hashMap);
	}

	@Override
	public void updateTwo(NewHouseDirectoryAuthPo authPo) {
		sessionTemplate.update(getStatement("updateTwo"), authPo);
	}

	@Override
	public NewHouseDirectoryAuthVo getRecommendNewHouse(long id) {
		return sessionTemplate.selectOne(getStatement("getRecommendNewHouse"), id);
	}

	@Override
	public NewHouseAuthMsgVo getNowStatus(String code) {
		return sessionTemplate.selectOne(getStatement("getNowStatus"), code);
	}

	@Override
	public void updateEvelopersPerson(Map<String, Object> paramMap) {
		sessionTemplate.update("updateEvelopersPerson", paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getElev(HashMap<String, Object> map) {
		return sessionTemplate.selectList("getElev", map);
	}

	@Override
	public void updateFengMianImage(Long id) {
		sessionTemplate.update(getStatement("updateFengMianImage"), id);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> getIndexNewHouse(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getIndexNewHouse"),map);
	}

	@Override
	public int getIndexNewHouseCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getIndexNewHouseCount"),map);
	}

	
}
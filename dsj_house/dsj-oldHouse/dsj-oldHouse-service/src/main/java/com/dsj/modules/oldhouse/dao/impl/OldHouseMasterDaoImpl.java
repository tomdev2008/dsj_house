package com.dsj.modules.oldhouse.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.oldhouse.dao.OldHouseMasterDao;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.vo.OldHouseRecommendVo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-28 14:44:14.
 * @版本: 1.0 .
 */
@Repository
public class OldHouseMasterDaoImpl extends BaseDaoImpl<OldHouseMasterPo> implements OldHouseMasterDao{

	@Override
	public void updateDeleteFlag(String[] ids, Integer deleteFlag) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("deleteFlag",deleteFlag);
		sessionTemplate.update(getStatement("updateDeleteFlag"),map);
	}

	@Override
	public int updateMasterStatus(String[] ids, Integer status) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("status",status);
		int result = sessionTemplate.update(getStatement("updateMasterStatus"),map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		return result;
	}

	@Override
	public int updateImageUrlById(Long id, String imageUrl) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("id", id);
		map.put("imageUrl",imageUrl);
		int result = sessionTemplate.update(getStatement("updateImageUrlById"),map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		return result;
	}


	@Override
	public List<ErshoufangIndexFiled> getErshoufangSolrByIds(String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		return sessionTemplate.selectList(getStatement("getErshoufangSolrByIds"), map);
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
	public List<String> getLateList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getLateList"),map);
	}

	@Override
	public void deleteOldRecommend(Long id) {
		sessionTemplate.update(getStatement("deleteOldRecommend"),id);
	}

	@Override
	public void updateOldRecommend(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateOldRecommend"),map);
	}

	@Override
	public Integer listCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("listCount"), map);
	}

	@Override
	public List<OldHouseMasterPo> selectByLimit(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("selectByLimit"), map);
	}

	@Override
	public List<OldHouseRecommendVo> getOldHouseRecommendById(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getOldHouseRecommendById"), map);
	}
	@Override
	public List<OldHouseMasterVo> findAgentOldHouse(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findAgentOldHouse"), map);
	}

	@Override
	public int findAgentOldHouseCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findAgentOldHouseCount"), map);
	}

	@Override
	public List<OldHouseMasterVo> findFollow(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findFollow"), map);
	}

	@Override
	public int findFollowCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findFollowCount"), map);
	}

	@Override
	public List<OldHouseMasterVo> lookHistory(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("lookHistory"), map);
	}

	@Override
	public int lookHistoryCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("lookHistoryCount"), map);
	}

	@Override
	public List<OldHouseMasterVo> getPcByNamePreMatchding(Map<String, Object> map) {
		return sessionTemplate.selectList("getPcByNamePreMatchding", map);
	}

	@Override
	public List<OldHouseMasterVo> findPcPageOldHouse(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("findPcPageOldHouse"),map);
	}

	@Override
	public int findPcPageOldHouseCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("findPcPageOldHouseCount"),map);
	}

	@Override
	public Long getCount(Map<String, Object> map) {
		return sessionTemplate.selectOne(getStatement("getCount"),map);
	}

	@Override
	public List<OldHouseMasterPo> listNewPage(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("listNewPage"), hashMap);
	}

	@Override
	public List<OldHouseMasterPo> listJoinDicBy(HashMap<String, Object> map) {
	
		return sessionTemplate.selectList("listJoinDicBy",map);
	}

	@Override
	public int updateCompanyTypes(Map<String, Object> map) {
		int result = sessionTemplate.update(getStatement("updateCompanyTypes"),map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0.newInstance("数据库操作,update返回0.{%s}", getStatement(SQL_UPDATE));
		}
		return result;
	}

	@Override
	public List<OldHouseMasterPo> listLeftAgentPage(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listLeftAgentPage"),map);
	}
	
}
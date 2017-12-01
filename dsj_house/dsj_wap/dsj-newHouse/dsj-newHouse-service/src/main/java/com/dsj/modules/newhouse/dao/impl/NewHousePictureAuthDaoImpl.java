package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHousePictureAuthDao;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Repository
public class NewHousePictureAuthDaoImpl extends BaseDaoImpl<NewHousePictureAuthPo> implements NewHousePictureAuthDao{

	@Override
	public void saveList(HashMap<String, Object> hashMap) {
		sessionTemplate.insert(getStatement("saveList"), hashMap);
	}

	@Override
	public List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("getListCount"), hashMap);
	}

	@Override
	public List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("listVoBy"), hashMap);
	}

	@Override
	public void updateAllFirstByNewHouseId(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateAllFirstByNewHouseId"), map);
	}

	@Override
	public void updateDeleteFlagNewHousesPicture(HashMap<String, Object> hashMap) {
		sessionTemplate.update(getStatement("updateDeleteFlagNewHousesPicture"), hashMap);
	}

	@Override
	public void deleteByNewHouseId(Long id) {
		sessionTemplate.delete(getStatement("deleteByNewHouseId"), id);
	}

	@Override
	public void updateNewHouseId(HashMap<String, Object> map) {
		sessionTemplate.update(getStatement("updateNewHouseId"), map);
	}

	@Override
	public List<NewHousePictureFrontVo> getPictureList(Long id) {
		return sessionTemplate.selectList(getStatement("getPictureList"), id);
	}
	
}
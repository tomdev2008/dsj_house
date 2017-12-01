package com.dsj.modules.other.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.other.dao.HousePictureDao;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.vo.HousePictureVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-16 08:34:39.
 * @版本: 1.0 .
 */
@Repository
public class HousePictureDaoImpl extends BaseDaoImpl<HousePicturePo> implements HousePictureDao{
	
	@Override
	public void deleteOldHousePictureByObjIdAndType(Long objId, int objType) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("objId", objId);
		map.put("objType", objType);
		sessionTemplate.update(getStatement("deleteOldHousePictureByObjIdAndType"),map);
	}

	@Override
	public void updateDeleteFlagByIds(String[] ids) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("deleteFlag", DeleteStatusEnum.DEL.getValue());
		sessionTemplate.update(getStatement("updateDeleteFlagByIds"),map);
	}

	@Override
	public void updateCoverByObjIdAndType(Long objId, int objType,Integer isCover) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("objId", objId);
		map.put("objType", objType);
		map.put("isCover", isCover);
		sessionTemplate.update(getStatement("updateCoverByObjIdAndType"),map);
	}

	@Override
	public List<HousePictureVo> listVoBy(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("listVoBy"),paramMap);
	}
	
	@Override
	public List<HousePicturePo> save(List<HousePicturePo> list) {

		if (list == null || list.size() <= 0) {
			return list;
		}

		int result = sessionTemplate.insert(getStatement(SQL_BATCH_INSERT), list);

		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", getStatement(SQL_BATCH_INSERT));
		}

		return list;
	}

	@Override
	public HousePicturePo getIsCoverByObjId(Map<String, Object> paramMap) {
	
		return sessionTemplate.selectOne(getStatement("getIsCoverByObjId"),paramMap);
	}
	
}
package com.dsj.modules.rent.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.exceptions.BizException;
import com.dsj.modules.rent.dao.RentHousePictureDao;
import com.dsj.modules.rent.po.RentHousePicturePo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-10 11:40:37.
 * @版本: 1.0 .
 */
@Repository
public class RentHousePictureDaoImpl extends BaseDaoImpl<RentHousePicturePo> implements RentHousePictureDao{

	@SuppressWarnings("unchecked")
	@Override
	public List<RentHousePicturePo> saveList(Map<String, Object> map) {
		List<RentHousePicturePo> list = (List<RentHousePicturePo>) map.get("picList");
		if (list == null || list.size() <= 0) {
			return list;
		}
		int result = sessionTemplate.insert(getStatement("saveList"), list);
		if (result <= 0) {
			throw BizException.DB_INSERT_RESULT_0.newInstance("数据库操作,insert返回0.{%s}", "saveList");
		}
		return list;
	}

	@Override
	public void updateCoverByObjIdAndType(Long objId, int objType, Integer isCover) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("objId", objId);
		map.put("objType", objType);
		map.put("isCover", isCover);
		sessionTemplate.update(getStatement("updateCoverByObjIdAndType"),map);
	}

	@Override
	public void updateDeleteFlag(Map<String, Object> map) {
		int result = sessionTemplate.update(getStatement("updateDeleteFlag"), map);
		if (result <= 0) {
			throw BizException.DB_UPDATE_RESULT_0
				.newInstance("数据库操作, updateDeleteFlag返回0.{%s}", 
						getStatement("updateDeleteFlag"));
		}
	}

	@Override
	public List<RentHousePicturePo> listCoverBy(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("listCoverBy"), paramMap);
	}
	
}
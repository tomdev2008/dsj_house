package com.dsj.modules.rent.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.rent.po.RentHousePicturePo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-10 11:40:37.
 * @版本: 1.0 .
 */
public interface RentHousePictureDao extends BaseDao<RentHousePicturePo> {

	List<RentHousePicturePo> saveList(Map<String, Object> map);

	void updateCoverByObjIdAndType(Long objId, int objType, Integer value);

	void updateDeleteFlag(Map<String, Object> map);

	List<RentHousePicturePo> listCoverBy(Map<String, Object> paramMap);
	
}
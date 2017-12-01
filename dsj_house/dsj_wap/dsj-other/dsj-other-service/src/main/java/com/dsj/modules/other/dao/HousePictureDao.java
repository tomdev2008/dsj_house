package com.dsj.modules.other.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.vo.HousePictureVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-16 08:34:39.
 * @版本: 1.0 .
 */
public interface HousePictureDao extends BaseDao<HousePicturePo> {
	
	void deleteOldHousePictureByObjIdAndType(Long objId, int objType);

	void updateDeleteFlagByIds(String[] ids);

	void updateCoverByObjIdAndType(Long objId, int objType,Integer isCover);

	List<HousePictureVo> listVoBy(Map<String, Object> paramMap);

	List<HousePicturePo> save(List<HousePicturePo> list);

	HousePicturePo getIsCoverByObjId(Map<String, Object> paramMap);
	
}
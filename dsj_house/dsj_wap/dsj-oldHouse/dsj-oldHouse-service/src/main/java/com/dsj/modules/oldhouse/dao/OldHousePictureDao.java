package com.dsj.modules.oldhouse.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.vo.OldHousePictureVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-28 14:44:15.
 * @版本: 1.0 .
 */
public interface OldHousePictureDao extends BaseDao<OldHousePicturePo> {

	void deleteOldHousePictureByObjIdAndType(Long objId, int objType);

	void updateDeleteFlagByIds(String[] ids);

	void updateCoverByObjIdAndType(Long objId, int objType,Integer isCover);

	List<OldHousePictureVo> listVoBy(Map<String, Object> paramMap);

	List<OldHousePicturePo> save(List<OldHousePicturePo> images);

	OldHousePicturePo getLastBy(Map<String, Object> paramImageMap);
	
}
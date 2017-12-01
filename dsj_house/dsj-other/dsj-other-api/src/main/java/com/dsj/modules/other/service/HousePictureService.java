package com.dsj.modules.other.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.vo.HousePictureVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-16 08:34:39.
 * @版本: 1.0 .
 */
public interface HousePictureService extends BaseService<HousePicturePo>{

	/**
	 * 删除图片
	 * @param objId
	 * @param objType
	 */
	void deleteOldHousePictureByObjIdAndType(Long objId, int objType);

	void deleteMasterImage(String[] ids);

	void updateCover(int objType , Long id);

	void updateDirectoryImage(String[] imageUrls, Integer pictureType, Long id , Long userId);

	List<HousePictureVo> listVoBy(Map<String, Object> paramMap);

	void deleteDirectoryImage(String[] ids);

	HousePicturePo getIsCoverByObjId(Long id);
	
}
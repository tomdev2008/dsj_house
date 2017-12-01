package com.dsj.modules.oldhouse.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.vo.OldHousePictureVo;
import com.dsj.modules.oldhouse.vo.OldMasterImageVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-28 14:44:15.
 * @版本: 1.0 .
 */
public interface OldHousePictureService extends BaseService<OldHousePicturePo>{
	
	/**
	 * 报存修改二手房图片
	 * @param imageVo
	 */
	void updateMasterImage(OldMasterImageVo imageVo);
	
	/**
	 * 删除图片
	 * @param objId
	 * @param objType
	 */
	void deleteOldHousePictureByObjIdAndType(Long objId, int objType);

	List<OldHousePicturePo>  updateMasterImage(String[] imageUrls, Integer pictureType,Long id);

	void deleteMasterImage(String[] ids);

	void updateCover(int objType , Long id);

	void updateDirectoryImage(String[] imageUrls, Integer pictureType, Long id , Long userId);

	List<OldHousePictureVo> listVoBy(Map<String, Object> paramMap);

	void deleteDirectoryImage(String[] ids);

	List<OldHousePicturePo> getByMasterId(Long id);

	OldHousePicturePo getLastBy(Map<String, Object> paramImageMap);

}
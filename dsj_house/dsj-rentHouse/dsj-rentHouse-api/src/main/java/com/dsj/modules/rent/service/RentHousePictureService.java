package com.dsj.modules.rent.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.rent.po.RentHousePicturePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-10 11:40:37.
 * @版本: 1.0 .
 */
public interface RentHousePictureService extends BaseService<RentHousePicturePo>{

	List<RentHousePicturePo> updateRentImage(String[] imageUrls, Integer pictureType, Long id, Integer objType , Integer userId);

	void updateCover(int value, Long id);

	void updateDeleteFlag(String[] ids, Integer value);

	List<RentHousePicturePo> listCoverBy(Map<String, Object> paramMap);

}
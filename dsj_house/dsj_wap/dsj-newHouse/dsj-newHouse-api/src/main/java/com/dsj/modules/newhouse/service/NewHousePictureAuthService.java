package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHousePictureAuthService extends BaseService<NewHousePictureAuthPo>{

	void saveList(List<NewHousePictureAuthPo> newHousePictureAuthPoList, long longValue);

	List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap);

	List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap);

	void saveNewHousePictureFirst(Long id, Long newHouseId);

	void updateDeleteFlagNewHousesPicture(List<Integer> ids, Long id);

	void deleteByEditYesByNewHouseId(Long id, Long id2);

	List<NewHousePictureFrontVo> getPictureList(Long id);

	HashMap<String, Object> getNewHousePictureListAndCountById(Long id);

	void deleteByNewHouseId(Long id);

}
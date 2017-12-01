package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHousePictureAuthDao extends BaseDao<NewHousePictureAuthPo> {

	void saveList(HashMap<String, Object> hashMap);

	List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap);

	List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap);

	void updateAllFirstByNewHouseId(HashMap<String, Object> map);

	void updateDeleteFlagNewHousesPicture(HashMap<String, Object> hashMap);

	void deleteByNewHouseId(Long yesId);

	void updateNewHouseId(HashMap<String, Object> map);

	List<NewHousePictureFrontVo> getPictureList(Long id);
	
}
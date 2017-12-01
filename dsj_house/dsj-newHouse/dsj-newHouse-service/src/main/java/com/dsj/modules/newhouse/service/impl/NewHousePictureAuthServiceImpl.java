package com.dsj.modules.newhouse.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthService;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;
import com.dsj.modules.newhouse.dao.NewHousePictureAuthDao;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.enums.NewHousePictureIsFirstEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Service
public class NewHousePictureAuthServiceImpl  extends BaseServiceImpl<NewHousePictureAuthDao,NewHousePictureAuthPo> implements NewHousePictureAuthService {

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	/**
	 *
	 * @return 
	 * @描述: 批量保存图片
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void saveList(List<NewHousePictureAuthPo> newHousePictureAuthPoList, long createPreson) {
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("newHousePictureAuthPoList", newHousePictureAuthPoList);
		hashMap.put("createPreson", createPreson);
		dao.saveList(hashMap);
		//楼盘封面图
		newHouseDirectoryAuthService.updateFengMianImage(newHousePictureAuthPoList.get(0).getObjectId());
	}

	/**
	 *
	 * @return: list<NewHousePictureCountVo> 
	 * @描述: 图片数量
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap) {
		return dao.getListCount(hashMap);
	}
	/**
	 *
	 * @return: list<NewHousePictureAuthVo> 
	 * @描述: 图片集合
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap) {
		return dao.listVoBy(hashMap);
	}

	/**
	 *
	 * @return
	 * @描述: 设为相册封面
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void saveNewHousePictureFirst(Long id, Long newHouseId) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("pictureFrist", NewHousePictureIsFirstEnum.NO.getValue());
		map.put("objectId", newHouseId);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		dao.updateAllFirstByNewHouseId(map);
		NewHousePictureAuthPo pictureAuthPo = new NewHousePictureAuthPo();
		pictureAuthPo.setId(id);
		pictureAuthPo.setPictureFrist(NewHousePictureIsFirstEnum.YES.getValue());
		dao.updateDynamic(pictureAuthPo);
		//楼盘封面图
		newHouseDirectoryAuthService.updateFengMianImage(newHouseId);
	}

	/**
	 *
	 * @return
	 * @描述: 删除图片
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void updateDeleteFlagNewHousesPicture(List<Integer> ids, Long id) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", ids);
		hashMap.put("updatePreson", id);
		hashMap.put("deleteFlag", DeleteStatusEnum.DEL.getValue());
		dao.updateDeleteFlagNewHousesPicture(hashMap);		
		//楼盘封面图
		Long newHouseId = dao.getById(ids.get(0)).getObjectId();
		newHouseDirectoryAuthService.updateFengMianImage(newHouseId);
	}
	
	/**
	 *
	 * @return
	 * @描述: 未展示图片,审核通过后覆盖原相册展示图片
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void deleteByEditYesByNewHouseId(Long yesId, Long noId) {
		dao.deleteByNewHouseId(yesId);
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("yesId", yesId);
		map.put("noId", noId);
		dao.updateNewHouseId(map);
	}

	@Override
	public List<NewHousePictureFrontVo> getPictureList(Long id) {
		 List<NewHousePictureFrontVo> list = dao.getPictureList(id);
		 for (NewHousePictureFrontVo newHousePictureFrontVo : list) {
			 newHousePictureFrontVo.setImgList(newHousePictureFrontVo.getImgStr().split(","));
		}
		return list;
	}

	@Override
	public HashMap<String, Object> getNewHousePictureListAndCountById(Long id) {
		 List<NewHousePictureFrontVo> list = dao.getPictureList(id);
		 HashMap<String, Object> hashMap = new HashMap<String,Object>();
		 hashMap.put("objectId", id);
		 hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		 int pictureTotalCount = 0;
		 for (NewHousePictureFrontVo newHousePictureFrontVo : list) {
			 hashMap.put("pictureStatusName", newHousePictureFrontVo.getName());
			 List<NewHousePictureAuthPo> pictureList = dao.listBy(hashMap);
			 newHousePictureFrontVo.setPictureList(pictureList);
			 pictureTotalCount = pictureTotalCount + pictureList.size();
		}
		 
		 hashMap.clear();
		 hashMap.put("totalCount", pictureTotalCount);
		 hashMap.put("pictureList", list);
		return hashMap;
	}

	@Override
	public void deleteByNewHouseId(Long id) {
		dao.deleteByNewHouseId(id);
	}

}
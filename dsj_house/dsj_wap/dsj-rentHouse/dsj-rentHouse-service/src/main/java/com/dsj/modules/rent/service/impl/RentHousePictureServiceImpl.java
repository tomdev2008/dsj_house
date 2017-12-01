package com.dsj.modules.rent.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.rent.dao.RentHousePictureDao;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.po.RentHousePicturePo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.service.RentHousePictureService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-10 11:40:37.
 * @版本: 1.0 .
 */
@Service
public class RentHousePictureServiceImpl  extends BaseServiceImpl<RentHousePictureDao,RentHousePicturePo> implements RentHousePictureService {

	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	
	@Override
	public List<RentHousePicturePo> updateRentImage(String[] imageUrls, Integer pictureType, Long id , Integer objType , Integer userId) {
		//po类中的首页图片处理  
//		Map<String, Object> paramMap = new HashMap<>();
//		paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
//		paramMap.put("objId", id);
//		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
//		List<RentHousePicturePo> poList = listBy(paramMap);
//		rentHouseOriginService.updatePictureUrl(id, poList.size(), imageUrls[0]);
		
		List<RentHousePicturePo> picList = new ArrayList<RentHousePicturePo>();
		for (String str : imageUrls) {
			RentHousePicturePo po = toPoByType(str, pictureType , id , objType ,userId );
			picList.add(po);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("picList", picList);
		List<RentHousePicturePo> res = dao.saveList(map);
		
		RentHouseOriginPo originPo = rentHouseOriginService.getById(id);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
		paramMap.put("objId", id);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("isCover", YesNoEnum.YES.getValue());
		//listCoverBy排序规则不同， 防止设置的不是第一张
		List<RentHousePicturePo> poList = listCoverBy(paramMap);
		if (poList.isEmpty()) {
			paramMap.clear();
			paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
			paramMap.put("objId", id);
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<RentHousePicturePo> poList2 = listCoverBy(paramMap);
			originPo.setPictureUrl(poList2.get(0).getPictureUrl());
		}
		rentHouseOriginService.updateDynamic(originPo);
		return res;
	}
	
	private RentHousePicturePo toPoByType(String pictureUrl, Integer pictureType,Long id , Integer objType , Integer userId) {
		RentHousePicturePo po = new RentHousePicturePo();
		po.setPictureUrl(pictureUrl);
		po.setPictureType(pictureType);
		po.setObjId(id);
		po.setObjType(objType);
		po.setIsCover(YesNoEnum.NO.getValue());
		po.setCreatePerson(userId);
		po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		return po;
	}

	@Override
	public void updateCover(int objType, Long id) {
		//设置originPo封面
		RentHousePicturePo po = getById(id);
		RentHouseOriginPo originPo = rentHouseOriginService.getById(po.getObjId());
		originPo.setPictureUrl(po.getPictureUrl());
		rentHouseOriginService.updateDynamic(originPo);
		
		dao.updateCoverByObjIdAndType(po.getObjId(),objType,YesNoEnum.NO.getValue());
		po.setIsCover(YesNoEnum.YES.getValue());
		po.setUpdateTime(new Date());
		updateDynamic(po);
	}

	@Override
	public void updateDeleteFlag(String[] ids, Integer deleteFlag) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("deleteFlag", deleteFlag);
		dao.updateDeleteFlag(map);
		
		//删除后设置源类默认图片  如果有图片 设置第一张为首页 如果没有 设为空
		RentHouseOriginPo originPo = rentHouseOriginService.getById
				(getById(Long.parseLong(ids[0])).getObjId());
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
		paramMap.put("objId", originPo.getId());
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("isCover", YesNoEnum.YES.getValue());
		//listCoverBy排序规则不同， 防止设置的不是第一张
		List<RentHousePicturePo> poList = listCoverBy(paramMap);
		if (poList.isEmpty()) {
			paramMap.put("isCover", null);
			List<RentHousePicturePo> poList2 = listCoverBy(paramMap);
			if (poList2.isEmpty()) {
				originPo.setPictureUrl("");
			}else {
				originPo.setPictureUrl(poList2.get(0).getPictureUrl());
			}
		}else {
			originPo.setPictureUrl(poList.get(0).getPictureUrl());
		}
		rentHouseOriginService.updateDynamic(originPo);
	}

	@Override
	public List<RentHousePicturePo> listCoverBy(Map<String, Object> paramMap) {
		return dao.listCoverBy(paramMap);
	}

}
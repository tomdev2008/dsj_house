package com.dsj.modules.oldhouse.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.modules.oldhouse.dao.OldHousePictureDao;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.service.OldHousePictureService;
import com.dsj.modules.oldhouse.vo.OldHousePictureVo;
import com.dsj.modules.oldhouse.vo.OldMasterImageVo;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-28 14:44:15.
 * @版本: 1.0 .
 */
@Service
public class OldHousePictureServiceImpl extends BaseServiceImpl<OldHousePictureDao, OldHousePicturePo>
		implements OldHousePictureService {
	
	@Autowired
	OldHouseMasterService oldHouseMasterService;
	
	public void updateMasterImage(OldMasterImageVo imageVo) {
		
		deleteOldHousePictureByObjIdAndType(imageVo.getId(),PictureObjectEnum.OLD_MASTER.getValue());
		
		List<OldHousePicturePo> images = new ArrayList<OldHousePicturePo>();
		// 把图片url字符串，转换成po
		addOldImage(images, imageVo.getInsideImages(), PictureTypeEnum.INSIDE.getValue(),imageVo.getId());
		addOldImage(images, imageVo.getHouseTypeImages(), PictureTypeEnum.HOUSETYPE.getValue(),imageVo.getId());
		addOldImage(images, imageVo.getOutInsideImages(), PictureTypeEnum.OUTSIDE.getValue(),imageVo.getId());
		save(images);
	}
	
	@Override
	public void deleteOldHousePictureByObjIdAndType(Long objId, int objType) {
		Map<String,Object> map=new HashMap<String,Object>();
		dao.deleteOldHousePictureByObjIdAndType(objId,objType);
	}

	/**
	 * 字符串转po
	 * 
	 * @param pictureUrl
	 * @param pictureType
	 * @return
	 */
	private OldHousePicturePo toPoByType(String pictureUrl, Integer pictureType,Long id,Integer isCover) {
		OldHousePicturePo po = new OldHousePicturePo();
		po.setCreateTime(new Date());
		po.setPictureUrl(pictureUrl);
		po.setUpdateTime(new Date());
		po.setPictureType(pictureType);
		po.setObjId(id);
		po.setIsCover(isCover);
		po.setObjType(PictureObjectEnum.OLD_MASTER.getValue());
		po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		return po;
	}

	private List<OldHousePicturePo> addOldImage(List<OldHousePicturePo> images, String strs, Integer pictureType,Long id) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pictureType", pictureType);
		paramMap.put("objType", PictureObjectEnum.DIC.getValue());
		paramMap.put("objId", id);
		List<OldHousePicturePo> lists=listBy(paramMap);
		
		if(StringUtils.isNotBlank(strs)){
			String[] imageStrs = strs.split(",");
			for (int i=0;i< imageStrs.length;i++) {
				String str=imageStrs[i];
				OldHousePicturePo po  =new OldHousePicturePo();
				if(lists==null){
					po = toPoByType(str, pictureType,id,YesNoEnum.YES.getValue());
				}else{
					po = toPoByType(str, pictureType,id,YesNoEnum.NO.getValue());
				}
				images.add(po);
			}
		}
		return images;
	}

	@Override
	public List<OldHousePicturePo> updateMasterImage(String[] imageUrls, Integer pictureType,Long id) {
		List<OldHousePicturePo> images = new ArrayList<OldHousePicturePo>();
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pictureType", pictureType);
		paramMap.put("objType", PictureObjectEnum.OLD_MASTER.getValue());
		paramMap.put("objId", id);
		List<OldHousePicturePo> lists=listBy(paramMap);
		for (int i=0;i< imageUrls.length;i++) {
			String str=imageUrls[i];
			OldHousePicturePo po  =new OldHousePicturePo();
			if(lists==null){
				po = toPoByType(str, pictureType,id,YesNoEnum.YES.getValue());
			}else{
				po = toPoByType(str, pictureType,id,YesNoEnum.NO.getValue());
			}
			
			images.add(po);
		}
		if(lists!=null){
			Map<String,Object> paramImageMap=new HashMap<String,Object>();
			paramImageMap.put("isCover", YesNoEnum.YES.getValue());
			paramImageMap.put("objId", id);
			paramMap.put("objType", PictureObjectEnum.OLD_MASTER.getValue());
			paramImageMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			OldHousePicturePo oldHousePicturePo=getBy(paramImageMap);
			if(oldHousePicturePo==null){
				oldHouseMasterService.updateImageUrlById(id, imageUrls[imageUrls.length-1]);
			}
		}
		return dao.save(images);
	}
	
	@Override
	public void updateDirectoryImage(String[] imageUrls, Integer pictureType,Long id , Long userId) {
		List<OldHousePicturePo> images = new ArrayList<OldHousePicturePo>();
		for (String str : imageUrls) {
			OldHousePicturePo po = toPoByType(str, pictureType,id,YesNoEnum.NO.getValue());
			po.setObjType(PictureObjectEnum.DIC.getValue());
			po.setCreatePerson(userId.intValue());
			images.add(po);
		}
		save(images);
	}

	@Override
	public void deleteMasterImage(String[] ids) {	
		dao.updateDeleteFlagByIds(ids);
		if(ids.length>0){
			OldHousePicturePo po=getById(Long.parseLong(ids[0]));
			if(po!=null){
				Map<String,Object> paramImageMap=new HashMap<String,Object>();
				paramImageMap.put("objId", po.getObjId());
				paramImageMap.put("objType", PictureObjectEnum.OLD_MASTER.getValue());
				paramImageMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				OldHousePicturePo 	oldHousePicture=getLastBy(paramImageMap);
				oldHouseMasterService.updateImageUrlById(po.getObjId(),oldHousePicture.getPictureUrl());
			}
		}
	}
	
	@Override
	public OldHousePicturePo getLastBy(Map<String, Object> paramImageMap) {
		return dao.getLastBy(paramImageMap);
	}

	@Override
	public void deleteDirectoryImage(String[] ids) {	
		dao.updateDeleteFlagByIds(ids);
	}

	@Override
	public void updateCover(int objType , Long id) {
		OldHousePicturePo po=getById(id);
		dao.updateCoverByObjIdAndType(po.getObjId(),objType,YesNoEnum.NO.getValue());
		po.setIsCover(YesNoEnum.YES.getValue());
		po.setUpdateTime(new Date());
		oldHouseMasterService.updateImageUrlById(po.getObjId(),po.getPictureUrl());
		updateDynamic(po);
	}

	@Override
	public List<OldHousePictureVo> listVoBy(Map<String, Object> paramMap) {
		return dao.listVoBy(paramMap);
	}

	@Override
	public List<OldHousePicturePo> getByMasterId(Long objId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("objId", objId);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.listBy(paramMap);
	}

}
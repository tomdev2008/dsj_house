package com.dsj.modules.other.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.modules.other.service.HousePictureService;
import com.dsj.modules.other.vo.HousePictureVo;
import com.dsj.modules.other.dao.HousePictureDao;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.po.HousePicturePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-16 08:34:39.
 * @版本: 1.0 .
 */
@Service
public class HousePictureServiceImpl  extends BaseServiceImpl<HousePictureDao,HousePicturePo> implements HousePictureService {
	
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
	private HousePicturePo toPoByType(String pictureUrl, Integer pictureType,Long id) {
		HousePicturePo po = new HousePicturePo();
		po.setCreateTime(new Date());
		po.setPictureUrl(pictureUrl);
		po.setUpdateTime(new Date());
		po.setPictureType(pictureType);
		po.setObjId(id);
		po.setIsCover(YesNoEnum.NO.getValue());
		po.setObjType(PictureObjectEnum.OLD_MASTER.getValue());
		po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		return po;
	}

	private List<HousePicturePo> addOldImage(List<HousePicturePo> images, String strs, Integer pictureType,Long id) {
		if(StringUtils.isNotBlank(strs)){
			String[] imageStrs = strs.split(",");
			for (String str : imageStrs) {
				HousePicturePo po = toPoByType(str, pictureType,id);
				images.add(po);
			}
		}
		return images;
	}

	@Override
	public void updateDirectoryImage(String[] imageUrls, Integer pictureType,Long id , Long userId) {
		List<HousePicturePo> images = new ArrayList<HousePicturePo>();
		for (String str : imageUrls) {
			HousePicturePo po = toPoByType(str, pictureType,id);
			po.setObjType(PictureObjectEnum.DIC.getValue());
			po.setCreatePerson(userId.intValue());
			images.add(po);
		}
		save(images);
	}

	@Override
	public void deleteMasterImage(String[] ids) {	
		dao.updateDeleteFlagByIds(ids);
	}
	
	@Override
	public void deleteDirectoryImage(String[] ids) {	
		dao.updateDeleteFlagByIds(ids);
	}

	@Override
	public void updateCover(int objType , Long id) {
		HousePicturePo po=getById(id);
		dao.updateCoverByObjIdAndType(po.getObjId(),objType,YesNoEnum.NO.getValue());
		po.setIsCover(YesNoEnum.YES.getValue());
		po.setUpdateTime(new Date());
		updateDynamic(po);
	}

	@Override
	public List<HousePictureVo> listVoBy(Map<String, Object> paramMap) {
		return dao.listVoBy(paramMap);
	}

	@Override
	public HousePicturePo getIsCoverByObjId(Long objId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("objId", objId);
		return dao.getIsCoverByObjId(paramMap);
	}
	
}
package com.dsj.modules.oldHouseParser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.service.HousePictureCrawlerService;
import com.dsj.modules.oldHouseParser.dao.HousePictureCrawlerDao;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:38.
 * @版本: 1.0 .
 */
@Service
public class HousePictureCrawlerServiceImpl  extends BaseServiceImpl<HousePictureCrawlerDao,HousePictureCrawlerPo> implements HousePictureCrawlerService {

	@Override
	public List<HousePictureCrawlerPo> selectPictureId(String originCommunityId) {
		Map<String, Object> map=new HashMap<>();
		map.put("originObjId", originCommunityId);
		return dao.selectPictureId(map);
	}
	
}
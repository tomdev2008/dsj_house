package com.dsj.modules.oldHouseParser.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:15.
 * @版本: 1.0 .
 */
public interface HousePictureCrawlerService extends BaseService<HousePictureCrawlerPo>{

	List<HousePictureCrawlerPo> selectPictureId(String originCommunityId);



	
}
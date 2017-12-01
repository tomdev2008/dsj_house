package com.dsj.modules.oldHouseParser.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:32:38.
 * @版本: 1.0 .
 */
public interface HousePictureCrawlerDao extends BaseDao<HousePictureCrawlerPo> {

	List<HousePictureCrawlerPo> selectPictureId(Map<String, Object> map);
	
}
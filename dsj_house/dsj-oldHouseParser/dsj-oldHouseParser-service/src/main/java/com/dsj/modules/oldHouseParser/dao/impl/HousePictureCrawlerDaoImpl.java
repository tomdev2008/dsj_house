package com.dsj.modules.oldHouseParser.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.oldHouseParser.dao.HousePictureCrawlerDao;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:38.
 * @版本: 1.0 .
 */
@Repository
public class HousePictureCrawlerDaoImpl extends BaseDaoImpl<HousePictureCrawlerPo> implements HousePictureCrawlerDao{

	@Override
	public List<HousePictureCrawlerPo> selectPictureId(Map<String, Object> map) {
		return sessionTemplate.selectList("selectPictureId", map);
	}
	
}
package com.dsj.modules.oldHouseParser.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.DicHouseTypeCrawlerPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public interface DicHouseTypeCrawlerService extends BaseService<DicHouseTypeCrawlerPo>{

	void saveHuxing(List<DicHouseTypeCrawlerPo> huxingList);


	
}
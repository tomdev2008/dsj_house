package com.dsj.modules.oldHouseParser.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.DicWenCrawlerPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
public interface DicWenCrawlerService extends BaseService<DicWenCrawlerPo>{

	void saveDicWenCrawler(DicWenCrawlerPo wenPo);


	
}
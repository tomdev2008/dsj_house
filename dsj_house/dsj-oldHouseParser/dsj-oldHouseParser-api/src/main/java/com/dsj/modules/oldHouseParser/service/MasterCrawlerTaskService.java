package com.dsj.modules.oldHouseParser.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.MasterCrawlerTaskPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-11-27 14:58:21.
 * @版本: 1.0 .
 */
public interface MasterCrawlerTaskService extends BaseService<MasterCrawlerTaskPo>{

	 //任务码  0000  (链家，我爱我家，麦田，中原)
	void updateDynamicTaskByIndex(int index);


	
}
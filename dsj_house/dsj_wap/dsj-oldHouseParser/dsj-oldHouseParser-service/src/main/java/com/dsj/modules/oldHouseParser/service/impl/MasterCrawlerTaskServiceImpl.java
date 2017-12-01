package com.dsj.modules.oldHouseParser.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.service.MasterCrawlerTaskService;
import com.dsj.modules.oldHouseParser.dao.MasterCrawlerTaskDao;
import com.dsj.modules.oldHouseParser.po.MasterCrawlerTaskPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-27 14:58:21.
 * @版本: 1.0 .
 */
@Service
public class MasterCrawlerTaskServiceImpl  extends BaseServiceImpl<MasterCrawlerTaskDao,MasterCrawlerTaskPo> implements MasterCrawlerTaskService {

	@Override
	public void updateDynamicTaskByIndex(int index) {
		MasterCrawlerTaskPo masterCrawlerTask=getById(1L);
		masterCrawlerTask.setTaskCode(com.dsj.common.utils.StringUtils.
				replaceIndex(index,masterCrawlerTask.getTaskCode(),"1"));
		updateDynamic(masterCrawlerTask);
		
	}
	
}
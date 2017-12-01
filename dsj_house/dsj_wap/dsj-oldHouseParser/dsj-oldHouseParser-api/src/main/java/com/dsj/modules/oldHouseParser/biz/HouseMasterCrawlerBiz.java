package com.dsj.modules.oldHouseParser.biz;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.HouseAgentCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:22.
 * @版本: 1.0 .
 */
public interface HouseMasterCrawlerBiz{

	void saveHeBingOldHouse(Integer pageNum, Integer pageSize) throws Exception;



	
}
package com.dsj.modules.oldHouseParser.service;

import java.util.HashMap;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.HouseAgentCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:22.
 * @版本: 1.0 .
 */
public interface HouseMasterCrawlerService extends BaseService<HouseMasterCrawlerPo>{
	
	/**
	 * 
	 * @param houseMasterCrawler 二手房
	 * @param dic 小区
	 * @param dicPicList 小区图片
	 * @param oldPicList 二手房图片
	 * @param agent 经纪人
	 * @param companyType 公司类型枚举 CompanyTypeEnum
	 * 
	 */
	void saveHouseMasterCrawler(HouseMasterCrawlerPo houseMasterCrawler, HouseAlikeCommunityPo dic,
			List<HousePictureCrawlerPo> dicPicList	, List<OldHousePictureCrawlerPo> oldPicList,
			HouseAgentCrawlerPo agent,CompanyTypeEnum companyType);


	List<HouseMasterCrawlerPo> selectHouseMasterCrawler(long id);

	void saveDownOldHouse(Integer pageNum, Integer pageSize) throws Exception;

	List<HouseMasterCrawlerPo> listNewPage(HashMap<String, Object> hashMap);

	void saveDynamicCrawler(List<OldHouseMasterPo> fathferList,HouseMasterCrawlerPo houseMasterCrawlerPo);

	/**
	 * 查询个数
	 * @return
	 */
	Long getAllCount();

	/**
	 * 
	 * @param value
	 * @param id
	 */
	void updateIsDeal(Integer isDetal, Long id);


	
}
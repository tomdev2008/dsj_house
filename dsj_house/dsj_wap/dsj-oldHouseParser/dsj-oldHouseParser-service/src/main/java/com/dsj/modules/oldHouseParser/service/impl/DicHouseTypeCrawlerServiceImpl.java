package com.dsj.modules.oldHouseParser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.utils.DateUtils;
import com.dsj.modules.oldHouseParser.dao.DicHouseTypeCrawlerDao;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;
import com.dsj.modules.oldHouseParser.po.DicHouseTypeCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseTypeDealCrawlerPo;
import com.dsj.modules.oldHouseParser.service.CommonUploadService;
import com.dsj.modules.oldHouseParser.service.DicDealLogsCrawlerService;
import com.dsj.modules.oldHouseParser.service.DicHouseTypeCrawlerService;
import com.dsj.modules.oldHouseParser.service.HouseTypeDealCrawlerService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
@Service
public class DicHouseTypeCrawlerServiceImpl  extends BaseServiceImpl<DicHouseTypeCrawlerDao,DicHouseTypeCrawlerPo> implements DicHouseTypeCrawlerService {
	
	@Autowired
	HouseTypeDealCrawlerService houseTypeDealCrawlerService;
	@Autowired
	DicDealLogsCrawlerService dicDealLogsCrawlerService;
	
	@Autowired
	CommonUploadService commonUploadService;
	
	@Override
	public void saveHuxing(List<DicHouseTypeCrawlerPo> huxingList) {
		for(DicHouseTypeCrawlerPo huxing:huxingList){
			
			Map<String,Object> huxingMap=new HashMap<String,Object>();
			huxingMap.put("originHouseTypeId", huxing.getOriginHouseTypeId());
			huxingMap.put("originDicId", huxing.getOriginDicId());
			DicHouseTypeCrawlerPo huxingPo=getBy(huxingMap);
			
			 List<DicDealLogsCrawlerPo> dealList=huxing.getDealList();
			if(huxingPo==null){
				if(StringUtils.isNotBlank(huxing.getOriginImageUrl())){
					String imageUrl=commonUploadService.downloadOrUpImg(huxing.getOriginImageUrl());
					huxing.setImageUrl(imageUrl);
				}
				long huxingId=saveDynamic(huxing);
				for(DicDealLogsCrawlerPo dealPo:dealList){
					HouseTypeDealCrawlerPo huxingDeal=saveOrdealDataPo( huxing, dealPo);
					huxingDeal.setHouseTypeId(huxingId);
					houseTypeDealCrawlerService.saveDynamic(huxingDeal);
				}
			}else{
				for(DicDealLogsCrawlerPo dealPo:dealList){
					Map<String,Object> paramMap=new HashMap<String,Object>();
					paramMap.put("originHouseId", dealPo.getOriginHouseId());
					paramMap.put("originHouseTypeId", huxingPo.getOriginHouseTypeId());
					paramMap.put("originDicId", huxingPo.getOriginDicId());
					paramMap.put("contractDate", DateUtils.date2String(dealPo.getContractDate()));
					HouseTypeDealCrawlerPo houseTypeDealCrawler=houseTypeDealCrawlerService.getBy(paramMap);
					if(houseTypeDealCrawler!=null){//交易记录存在
						break;
					}else{
						HouseTypeDealCrawlerPo huxingDeal=saveOrdealDataPo( huxing, dealPo);
						huxingDeal.setHouseTypeId(huxingDeal.getId());
						houseTypeDealCrawlerService.saveDynamic(huxingDeal);
					}
						
				}
			}
		}
	}
	
	private HouseTypeDealCrawlerPo saveOrdealDataPo(DicHouseTypeCrawlerPo huxing,DicDealLogsCrawlerPo dealPo){
		HouseTypeDealCrawlerPo huxingDeal=new HouseTypeDealCrawlerPo();
		huxingDeal.setOriginDicId(huxing.getOriginDicId());
		huxingDeal.setOriginHouseTypeId(huxing.getOriginHouseTypeId());
		huxingDeal.setOriginHouseId(dealPo.getOriginHouseId());
		huxingDeal.setCreateTime(new Date());
		huxingDeal.setContractDate(dealPo.getContractDate());
		//查询成交记录是否存在
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("originHouseId", dealPo.getOriginHouseId());
		paramMap.put("originDicId", dealPo.getOriginDicId());
		paramMap.put("contractDate", DateUtils.date2String(dealPo.getContractDate()));
		DicDealLogsCrawlerPo hasDealPo=dicDealLogsCrawlerService.getBy(paramMap);
		if(hasDealPo!=null){
			huxingDeal.setDealId(hasDealPo.getId());
		}else{
			Long dealId=dicDealLogsCrawlerService.saveDynamic(dealPo);
			huxingDeal.setDealId(dealId);
		}
		
		return huxingDeal;
	}
	
}
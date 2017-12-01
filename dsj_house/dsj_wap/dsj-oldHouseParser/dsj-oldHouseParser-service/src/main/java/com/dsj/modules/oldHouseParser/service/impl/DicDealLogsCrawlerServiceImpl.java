package com.dsj.modules.oldHouseParser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.utils.DateUtils;
import com.dsj.modules.oldHouseParser.dao.DicDealLogsCrawlerDao;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;
import com.dsj.modules.oldHouseParser.service.DicDealLogsCrawlerService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
@Service
public class DicDealLogsCrawlerServiceImpl  extends BaseServiceImpl<DicDealLogsCrawlerDao,DicDealLogsCrawlerPo> implements DicDealLogsCrawlerService {

	@Override
	public Boolean saveDicDealLogs(List<DicDealLogsCrawlerPo> dics) {
		for(DicDealLogsCrawlerPo po:dics){
			
			Map<String,Object> paramMap=new HashMap<String,Object>();
			
			paramMap.put("originDicId", po.getOriginDicId());
			paramMap.put("originHouseId", po.getOriginHouseId());
			paramMap.put("price", po.getPrice());
			paramMap.put("contractDate", DateUtils.date2String(po.getContractDate()));
			List<DicDealLogsCrawlerPo> hasDicsList=listBy(paramMap);
			if(hasDicsList.size()>0){
				return true;
			}
			saveDynamic(po);
		}
		return false;
	}
	
}
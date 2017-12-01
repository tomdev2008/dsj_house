package com.dsj.modules.oldHouseParser.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.utils.DateUtils;
import com.dsj.modules.oldHouseParser.dao.DicWenCrawlerDao;
import com.dsj.modules.oldHouseParser.po.DicDaCrawlerPo;
import com.dsj.modules.oldHouseParser.po.DicWenCrawlerPo;
import com.dsj.modules.oldHouseParser.service.DicDaCrawlerService;
import com.dsj.modules.oldHouseParser.service.DicWenCrawlerService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-21 17:17:45.
 * @版本: 1.0 .
 */
@Service
public class DicWenCrawlerServiceImpl  extends BaseServiceImpl<DicWenCrawlerDao,DicWenCrawlerPo> implements DicWenCrawlerService {
	
	@Autowired
	DicDaCrawlerService dicDaCrawlerService;
	@Override
	public void saveDicWenCrawler(DicWenCrawlerPo wenPo) {
		
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("originWenId", wenPo.getOriginWenId());
		
		DicWenCrawlerPo dicWenCrawlerPo=getBy(paramMap);
		Long wenId=0l;
		if(dicWenCrawlerPo!=null){
			wenId=dicWenCrawlerPo.getId();
		}else{
			wenPo.setCreateTime(new Date());
		    wenId=saveDynamic(wenPo);
		}
		
		List<DicDaCrawlerPo> dicDas=wenPo.getDicDaCrawlers();
		for(DicDaCrawlerPo daPo:dicDas){
			daPo.setCreateTime(new Date());
			daPo.setWenId(wenId);
			paramMap.put("wenId", wenPo.getId());
			paramMap.put("daTime", DateUtils.date2String(daPo.getDaTime(),"yyyy-MM-dd HH:mm:ss"));
			DicDaCrawlerPo dicDaCrawlerPo=dicDaCrawlerService.getBy(paramMap);
			if(dicDaCrawlerPo!=null){
				break;
			}else{
				dicDaCrawlerService.saveDynamic(daPo);
			}
		}
	}
	
	
}
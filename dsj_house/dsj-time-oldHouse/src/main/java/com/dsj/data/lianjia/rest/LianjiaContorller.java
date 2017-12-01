package com.dsj.data.lianjia.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.data.lianjia.biz.LianjiaDicHuxingBiz;
import com.dsj.data.lianjia.biz.LianjiaDicWendaBiz;
import com.dsj.data.lianjia.biz.LianjiaErshoufangBiz;
import com.dsj.data.lianjia.service.LianjiaErshoufangService;
import com.dsj.modules.oldHouseParser.biz.HouseMasterCrawlerBiz;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
@Controller
@RequestMapping("lianjia")
public class LianjiaContorller {
	private final static Logger LOGGER = LoggerFactory.getLogger(LianjiaContorller.class);
	
	@Autowired
	LianjiaErshoufangBiz lianjiaErshoufangBiz;
	
	@Autowired
	LianjiaErshoufangService lianjiaErshoufangService;
	
	@Autowired
	HouseMasterCrawlerBiz  houseMasterCrawlerBiz;
	
	@Autowired
	HouseMasterCrawlerService houseMasterCrawlerService;
	
	@Autowired
	OldHouseMasterService oldHouseMasterService;
	
	@Autowired
	LianjiaDicHuxingBiz lianjiaDicHuxingBiz;
	
	@Autowired
	LianjiaDicWendaBiz lianjiaDicWendaBiz;
	
	//二手房根据地区爬数据
	@RequestMapping("ershoufang")
	@ResponseBody
	public String dealErshoufang(){
		lianjiaErshoufangBiz.dealErshoufangByArea(2);
		return "ok";
	}
	
	//根据id二手房根据地区爬数据
	@RequestMapping("ershoufang/name")
	@ResponseBody
	public String dealErshoufangById(String name){
		lianjiaErshoufangBiz.dealErshoufangByArea(name);
		return "ok";
	}
	
	@RequestMapping("area")
	@ResponseBody
	public String dealErshoufangByArea(){
		try {
			lianjiaErshoufangService.saveLianjiaArea("https://bj.lianjia.com/ershoufang/dongcheng/");
		} catch (Exception e) {
			LOGGER.error("二手房爬地区商圈个数错误",e);
			return "error";
		}
		return "ok";
	}
	

	@RequestMapping("hebing")
	@ResponseBody
	public String hebing(){
		try {
			Integer pageSize=30;
			Long count=houseMasterCrawlerService.getAllCount();
			for(int i=0;i<count/pageSize+1;i++){
				houseMasterCrawlerBiz.saveHeBingOldHouse(i*pageSize, pageSize);
			}
		} catch (Exception e) {
			LOGGER.error("二手房合并上架错误",e);
			return "error";
		}
		return "ok";
	}
	
	@RequestMapping("down")
	@ResponseBody
	public String down(){
		try {
			Integer pageSize=100;
			Long count=oldHouseMasterService.getUpAllCount();
			for(int i=0;i<count/pageSize+1;i++){
				houseMasterCrawlerService.saveDownOldHouse(i*pageSize, pageSize);
			}
			//houseMasterCrawlerService.saveDownOldHouse(s, pageSize);
		} catch (Exception e) {
			LOGGER.error("二手房合并上架错误",e);
			return "error";
		}
		return "ok";
	}
	
	
	//二手房户型
	@RequestMapping("huxing")
	@ResponseBody
	public String dealErshoufangHuxing(){
		lianjiaDicHuxingBiz.dealHuxingByDic();
		return "ok";
	}
	
	//二手房问答
	@RequestMapping("wenda")
	@ResponseBody
	public String dealErshoufangWenda(){
		lianjiaDicWendaBiz.dealWendaByDic();
		return "ok";
	}
}

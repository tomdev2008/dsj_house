package com.dsj.data.lianjia.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.CrawlerConfig.GetIP;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.data.lianjia.service.LianjiaErshoufangService;
import com.dsj.modules.oldHouseParser.po.IpPoolPo;
import com.dsj.modules.oldHouseParser.po.OldMasterLianjiaAreaPo;
import com.dsj.modules.oldHouseParser.service.IpPoolService;
import com.dsj.modules.oldHouseParser.service.OldMasterLianjiaAreaService;

@Service
public class LianjiaErshoufangServiceImpl implements LianjiaErshoufangService{
	
	@Autowired
	IpPoolService ipPoolService;
	
	@Autowired
	OldMasterLianjiaAreaService oldMasterLianjiaAreaService;
	
	
	//地区
	@Override
	public  void saveLianjiaArea(String url) throws NumberFormatException, IOException, SAXException {
		
		 CrawlerConfig config=new CrawlerConfig();
		 Thread thread=	 new Thread(config.new GetIP(14*1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		 thread.start();
		 
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			Document doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		Elements areaEles= doc.getElementsByClass("position").get(0).getElementsByAttributeValue("data-role", "ershoufang");
	
		Elements eles1=areaEles.get(0).getElementsByTag("div").get(1).getElementsByTag("a");
		for(Element ele:eles1 ){
			OldMasterLianjiaAreaPo lianArea=new OldMasterLianjiaAreaPo();
			//System.out.println(ele.attr("href")+":"+ele.ownText());
			 lianArea.setAreaAme(ele.ownText());
			 lianArea.setAreaListUrl(ele.attr("href"));
			 lianArea.setCreateTime(new Date());
			 lianArea.setStatus(0);
			 lianArea.setFatherId(0l);
			Long id= oldMasterLianjiaAreaService.saveDynamic(lianArea);
			 
			 List<OldMasterLianjiaAreaPo>  areas=saveLianjiaArea1(ele.attr("href"),config);
			 for(OldMasterLianjiaAreaPo areaPo:areas){
				 areaPo.setFatherId(id);
				 oldMasterLianjiaAreaService.saveDynamic(areaPo);
			 }
			//LianjiaSecondAreaModel model1=lianjiaSecondAreaSevice.getLianjiaAreaByName(ele.ownText());
		
		}
		//try {
           // Thread.sleep(2000);
           thread.interrupt();
       
	}
	
	
	//商圈
	public  List<OldMasterLianjiaAreaPo> saveLianjiaArea1(String url,CrawlerConfig config) throws NumberFormatException, IOException, SAXException{
		
		String lianjiaAreaUrl="";
		
		if(!url.contains("https:")){
			 lianjiaAreaUrl="https://bj.lianjia.com";
		}
		
		Document doc= DOMJsonpConfig.getDocByPropUrl(lianjiaAreaUrl+url,config);
		List<OldMasterLianjiaAreaPo> areas=new ArrayList<OldMasterLianjiaAreaPo>();
		
		if(doc.getElementsByClass("position").size()>0){
		Elements areaEles= doc.getElementsByClass("position").get(0).getElementsByAttributeValue("data-role", "ershoufang");
		Elements eles2=areaEles.get(0).getElementsByTag("div").get(2).getElementsByTag("a");
	
		for(Element ele:eles2 ){
			OldMasterLianjiaAreaPo area=new OldMasterLianjiaAreaPo();
			System.out.println(ele.attr("href")+":"+ele.ownText());
			area.setTradeName(ele.ownText());
			area.setTradeListUrl(ele.attr("href"));
			if(url.contains("xianghe")||url.contains("yanjiao")){
				lianjiaAreaUrl="https://lf.lianjia.com";
			}
			Document doc1= DOMJsonpConfig.getDocByPropUrl(lianjiaAreaUrl+ele.attr("href"),config);
				if(doc1.getElementsByClass("total fl")!=null && doc1.getElementsByClass("total fl").size()>0&&doc1.getElementsByClass("total fl").get(0).getElementsByTag("span").size()>0){
					area.setHouseCount(Integer.parseInt(doc1.getElementsByClass("total fl").get(0).getElementsByTag("span").get(0).ownText()));
					area.setStatus(0);
					areas.add(area);
				}
			}
		}
		return areas;
	}
	
	public Document  getDocByPropUrl(String url){
		IpPoolPo ipPoool=ipPoolService.getByLast();
		try{
			Document doc = DOMJsonpConfig.getDocByPropUrl(url, ipPoool.getIp(), Integer.parseInt(ipPoool.getPort()));
			return doc;
		}catch(Exception e){
			return getDocByPropUrl(url);
		}
		
	}
	
	//二手房数据处理
	
}

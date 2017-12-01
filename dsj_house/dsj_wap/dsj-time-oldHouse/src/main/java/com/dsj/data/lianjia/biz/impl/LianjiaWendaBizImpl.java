package com.dsj.data.lianjia.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.StringUtils;
import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.data.lianjia.biz.LianjiaDicHuxingBiz;
import com.dsj.data.lianjia.biz.LianjiaDicWendaBiz;
import com.dsj.modules.oldHouseParser.po.DicDaCrawlerPo;
import com.dsj.modules.oldHouseParser.po.DicWenCrawlerPo;
import com.dsj.modules.oldHouseParser.service.DicWenCrawlerService;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.HouseDirectoryService;
@Service
public class LianjiaWendaBizImpl implements LianjiaDicWendaBiz {

	private final static Logger LOGGER = LoggerFactory.getLogger(LianjiaDicHuxingBiz.class);
	static String xiaoquUrl="https://bj.lianjia.com/wenda/liebiao/";
	
	static String lianjiaUrl="https://bj.lianjia.com/";
	@Autowired
	DicWenCrawlerService dicWenCrawlerService; 
	
	@Autowired
	HouseDirectoryService houseDirectoryService;
	public   void dealWendaList(String originDicId){
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		configThread.start();
		Document doc= DOMJsonpConfig.getDocByPropUrl(xiaoquUrl+"c"+originDicId,config);
	    String countStr=StringUtils.getNumberByText(doc.getElementsByClass("big-title").get(0)
	    		.getElementsByClass("answers-num").get(0).text());
      
        if(StringUtils.isNotBlank(countStr)){
    	  Integer count=Integer.parseInt(countStr);
    	  Integer pageLast=count/10+1;
    	  
		  for(int i=1;i<=pageLast;i++){
			  dealDicWendaByPage(originDicId,i,config);
		  }
    	  
       }
	}
	private  void dealDicWendaByPage(String originDicId, int pg, CrawlerConfig config) {
		 Document doc= DOMJsonpConfig.getDocByPropUrl(xiaoquUrl+"c"+originDicId+"/pg"+pg,config);
		  getWendaPoBy(originDicId,doc,config);
	}
	private  void getWendaPoBy(String originDicId, Document doc, CrawlerConfig config) {
		
		if(doc.getElementsByClass("all-answers shadow").size()>0&&
				doc.getElementsByClass("all-answers shadow").
				 get(0).getElementsByClass("ans-ul").size()>0){
			 Elements elesContent= doc.getElementsByClass("all-answers shadow").
					 get(0).getElementsByClass("ans-ul").
					 get(0).getElementsByClass("ans-item");
			 for(Element ele:elesContent){
				 try{
					 String DetailUrl=ele.getElementsByClass("subtitle").get(0).attr("href");
					 System.out.println(lianjiaUrl+DetailUrl);
					 DicWenCrawlerPo wenPo=dealWendaDetail(lianjiaUrl+DetailUrl+"?sort=1",config,originDicId);
					 if(wenPo!=null){
						 dicWenCrawlerService.saveDicWenCrawler(wenPo);
					 }
				 }catch(Exception e){
					 LOGGER.error("问答错误:",e);
				 }
			 }
		}
	}
	
	
	private  DicWenCrawlerPo dealWendaDetail(String detailUrl,CrawlerConfig config,String originDicId) {
		Document doc= DOMJsonpConfig.getDocByPropUrl(detailUrl,config);
		 DicWenCrawlerPo wenPo=new DicWenCrawlerPo();
		wenPo.setOriginDicId(originDicId);
		if(doc.getElementsByClass("q-desc").size()>0&&doc.getElementsByClass("q-desc").get(0).getElementsByTag("h1").size()>0){
			String title=doc.getElementsByClass("q-desc").get(0).getElementsByTag("h1").get(0).ownText();
			wenPo.setTitle(title);
			
			String wenTime=doc.getElementsByClass("quer").get(0).
					getElementsByTag("span").get(1).ownText();
			wenPo.setWenTime(DateUtils.string2Date(wenTime.replace("时间：", ""),"yyyy-MM-dd HH:mm:ss"));
			
			Elements classifyName=doc.getElementsByClass("quer").get(0).
					getElementsByTag("span").get(2).getElementsByTag("a");
			wenPo.setClassifyName(classifyName.get(0).ownText()+"-"+classifyName.get(1).ownText());
			
			if(doc.getElementsByClass("q-info").size()>0){
			String content=doc.getElementsByClass("q-info").get(0).ownText();
				wenPo.setContent(content);
			}
			wenPo.setOriginWenId(detailUrl.split("/")[6].replace(".html", "").replace("?sort=1", ""));
			
			List<DicDaCrawlerPo> das=new ArrayList<DicDaCrawlerPo>();
			
			 Elements elesContent= doc.getElementsByClass("solve-list").
					 get(0).getElementsByClass("solve-listli");
			 for(Element ele:elesContent){
				 Element liEle=ele.getElementsByClass("li-r").get(0);
				 
				 DicDaCrawlerPo dapo=new DicDaCrawlerPo();
				 dapo.setOriginDicId(wenPo.getOriginDicId());
				 String datime=liEle.getElementsByClass("time").get(0).ownText();
				 dapo.setDaTime(DateUtils.string2Date(datime,"yyyy-MM-dd HH:mm:ss"));
				 
				 String dacontent=liEle.getElementsByClass("content lj_editor_view").get(0).html();
				 dapo.setContent(dacontent);
				 dapo.setDicWenId(wenPo.getOriginWenId());
				 das.add(dapo);
			 }
			 wenPo.setDicDaCrawlers(das);
		}else{
			return null;
		}
		return wenPo;
	}
	
	@Override
	public void dealDicWendaList(String originDicId, CrawlerConfig config) {
		Document doc= DOMJsonpConfig.getDocByPropUrl(xiaoquUrl+"c"+originDicId,config);
		if(doc.getElementsByClass("big-title").size()>0){
		    String countStr=StringUtils.getNumberByText(doc.getElementsByClass("big-title").get(0)
		    		.getElementsByClass("answers-num").get(0).text());
	      
	        if(StringUtils.isNotBlank(countStr)){
	    	  Integer count=Integer.parseInt(countStr);
	    	  Integer pageLast=count/10+1;
	    	  
			  for(int i=1;i<=pageLast;i++){
				  dealDicWendaByPage(originDicId,i,config);
			  }
	    	  
	       }
		}
	}
	@Override
	public void dealWendaByDic() {
		
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
			configThread.start();
		for(int i=0;i<100;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageFirst", i*100);
			map.put("pageSize", 100);
			List<HouseDirectoryPo> dicList=houseDirectoryService.listByLimit(map);
			for(HouseDirectoryPo po:dicList){
				if(StringUtils.isNotBlank(po.getOriginCommunityId())){
					dealDicWendaList(po.getOriginCommunityId(),config);
				}
			}
		}
		
		configThread.interrupt();
	}
	


}













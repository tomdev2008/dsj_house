package com.dsj.data.lianjia.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
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

import com.dsj.common.utils.StringUtils;
import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.data.lianjia.biz.LianjiaDicChengJiaoBiz;
import com.dsj.data.lianjia.biz.LianjiaDicHuxingBiz;
import com.dsj.data.lianjia.rest.LianjiaContorller;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;
import com.dsj.modules.oldHouseParser.po.DicHouseTypeCrawlerPo;
import com.dsj.modules.oldHouseParser.service.DicHouseTypeCrawlerService;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.HouseDirectoryService;
@Service
public class LianjiaDicHuxingBizImpl implements LianjiaDicHuxingBiz {

	
	static String xiaoquUrl="https://bj.lianjia.com/xiaoqu/";
	static String chengJiaoUrl="https://bj.lianjia.com/chengjiao/";
	
	private final static Logger LOGGER = LoggerFactory.getLogger(LianjiaDicHuxingBiz.class);
	@Autowired
	LianjiaDicChengJiaoBiz lianjiaDicChengJiaoBiz;
	@Autowired
	DicHouseTypeCrawlerService dicHouseTypeCrawlerService;
	@Autowired
	HouseDirectoryService houseDirectoryService;
	public   void dealDicHuxingList(String originDicId){
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		configThread.start();
		Document doc= DOMJsonpConfig.getDocByPropUrl(xiaoquUrl+originDicId+"/huxing",config);
	    String countStr=StringUtils.getNumberByText(doc.getElementsByClass("frameListRes clear").get(0).text());
      
        if(StringUtils.isNotBlank(countStr)){
    	  Integer count=Integer.parseInt(countStr);
    	  Integer pageLast=count/10+1;
    	  if(count<10000){
    		  for(int i=1;i<=pageLast;i++){
    			dealDicHuxingByPage(originDicId,i,config);
    		  }
    	  }
       }
	}
	
	@Override
	public  void dealDicHuxingList(String originDicId,CrawlerConfig config){
		Document doc= DOMJsonpConfig.getDocByPropUrl(xiaoquUrl+originDicId+"/huxing",config);
	    String countStr=StringUtils.getNumberByText(doc.getElementsByClass("frameListRes clear").get(0).text());
      
        if(StringUtils.isNotBlank(countStr)){
    	  Integer count=Integer.parseInt(countStr);
    	  Integer pageLast=count/10+1;
	
		  for(int i=1;i<=pageLast;i++){
			  List<DicHouseTypeCrawlerPo> huxingList=dealDicHuxingByPage(originDicId,i,config);
			  dicHouseTypeCrawlerService.saveHuxing(huxingList);
		  }
    	  
       }
	}
	
	
	private  List<DicHouseTypeCrawlerPo> dealDicHuxingByPage(String originDicId, int pg, CrawlerConfig config) {
		 Document doc= DOMJsonpConfig.getDocByPropUrl(xiaoquUrl+originDicId+"/huxing/pg"+pg,config);
		 return getHuxingPoBy(originDicId,doc,config);
	}
	private  List<DicHouseTypeCrawlerPo> getHuxingPoBy(String originDicId, Document doc,CrawlerConfig config) {
		 Elements elesContent= doc.getElementsByClass("frameListContent").
				 get(0).getElementsByClass("frameListItem clear");
		 List<DicHouseTypeCrawlerPo>  huxingList=new ArrayList<DicHouseTypeCrawlerPo>();
		 for(Element ele:elesContent){
			// System.out.println(ele);
			 DicHouseTypeCrawlerPo po=new DicHouseTypeCrawlerPo();
			 po.setOriginDicId(originDicId);
			 po.setCreateTime(new Date());
			 String title=ele.getElementsByClass("fr").get(0).getElementsByTag("a").get(0).ownText();
			 String originImageUrl= ele.getElementsByClass("fl").get(0).getElementsByTag("a")
					 .get(0).getElementsByTag("img").attr("data-original");
			 po.setOriginImageUrl(originImageUrl);
			 if(StringUtils.isNotBlank(title)){
				 String room=title.split("室")[0];
				if(StringUtils.isNotBlank(room)&&!room.contains("-")){
					po.setRoom(Integer.parseInt(room));
				}
				if(title.split("室").length>=2){
					if(title.split("室")[1].split("厅").length>0){
						String hall=title.split("室")[1].split("厅")[0];
						if(StringUtils.isNotBlank(hall)&&!hall.contains("-")){
							po.setHall(Integer.parseInt(hall));
						}
					}
					if(title.contains("厅")&&title.contains("卫")){
						String toilet=title.split("厅")[1];
						po.setToilet(Integer.parseInt(toilet.split("卫")[0]));
					}
					
				}
				
				String area=title.split(" ")[1];
				po.setBuildArea(new BigDecimal(area.replace("㎡", "")));
			 }
			 
				
				
			 String frameItemInfo=ele.getElementsByClass("frameItemInfo").get(0).ownText();	
			 String[] frameItemInfoArr=frameItemInfo.replace("朝向：", "").replace("使用率：", "").split(" ");
			 if(frameItemInfoArr.length>0){
				 String orientationsName="";
				 for(int i=0;i<frameItemInfoArr.length-1;i++){
					 orientationsName=frameItemInfoArr[i]+" ";
				 }
				 po.setOrientationsName(orientationsName);
			 }
			 if(frameItemInfoArr.length>1 && frameItemInfoArr[frameItemInfoArr.length-1].contains("%")){
				 po.setUseRate(frameItemInfoArr[frameItemInfoArr.length-1]);
			 }
			 
			 String frameItemTotalPrice=ele.getElementsByClass("frameItemPrice").get(0)
					 .getElementsByClass("frameItemTotalPrice").get(0).ownText();
			 
			 po.setPrice(frameItemTotalPrice);
			 
			 Elements frameItemSell=ele.getElementsByClass("fl").get(0)
					.getElementsByTag("a");
			 //https://bj.lianjia.com/xiaoqu/1111027374654/huxing/1115732065995994.html
			
			 po.setOriginHouseTypeId(frameItemSell.get(0).attr("href").split("/")[6].replace(".html", ""));
			 
			 //处理户型下的成交记录
			 List<DicDealLogsCrawlerPo> dealList=detailHuxingChengjiao(originDicId,po.getOriginHouseTypeId(),config);
			 po.setDealList(dealList);
			 huxingList.add(po);
		 }
		return huxingList;
	}
	
	
	private   List<DicDealLogsCrawlerPo>  detailHuxingChengjiao(String originDicId, String originHouseTypeId, CrawlerConfig config) {
		String url=chengJiaoUrl+"c"+originDicId+"/"+originHouseTypeId;
		 List<DicDealLogsCrawlerPo> dealList=new ArrayList<DicDealLogsCrawlerPo>();
		Document doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		if(doc.getElementsByClass("frameListHeader").size()>0){
		    String countStr=StringUtils.getNumberByText(doc.getElementsByClass("frameListHeader").
		    		get(0).getElementsByTag("span").text());
		    
		    if(StringUtils.isNotBlank(countStr)){
		    	  Integer count=Integer.parseInt(countStr);
		    	  Integer pageLast=count/30+1;
		    	  if(count<10000){
		    		  for(int i=1;i<=pageLast;i++){
		    			  dealList.addAll(dealDicChengJiaoListByPage(originDicId,i,config,url));
		    		  }
		    	  }
		       }
		}
	    return dealList;
	}
	
	public   List<DicDealLogsCrawlerPo> dealDicChengJiaoListByPage(String originDicId,int pg,CrawlerConfig config,String url){
		 Document doc= DOMJsonpConfig.getDocByPropUrl(url+"/pg"+pg,config);
		 return lianjiaDicChengJiaoBiz.getDealPoBy(originDicId,doc);
	}
	
	public static void main(String[] args) {
		
		LianjiaDicHuxingBizImpl a=new LianjiaDicHuxingBizImpl();
		a.dealDicHuxingList("1111027379626");
	}

	@Override
	public void dealHuxingByDic() {
	/*	try {
			Integer pageSize=100;
			Long count=oldHouseMasterService.getUpAllCount();
			for(int i=0;i<count/pageSize+1;i++){
				houseMasterCrawlerService.saveDownOldHouse(i*pageSize, pageSize);
			}
			//houseMasterCrawlerService.saveDownOldHouse(s, pageSize);
		} catch (Exception e) {
			LOGGER.error("二手房合并上架错误",e);
			return "error";
		}*/
		
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		configThread.start();
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 
		for(int i=0;i<100;i++){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageFirst", i*100);
			map.put("pageSize", 100);
			List<HouseDirectoryPo> dicList=houseDirectoryService.listByLimit(map);
			for(HouseDirectoryPo po:dicList){
				if(StringUtils.isNotBlank(po.getOriginCommunityId())){
					try{
						dealDicHuxingList(po.getOriginCommunityId(),config);
					}catch(Exception e){
						LOGGER.error("户型错误：",e);
					}
				}
			}
		}
		
		configThread.interrupt();
	}
	
}













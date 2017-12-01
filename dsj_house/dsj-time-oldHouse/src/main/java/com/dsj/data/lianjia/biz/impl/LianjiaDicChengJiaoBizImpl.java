package com.dsj.data.lianjia.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.StringUtils;
import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.data.lianjia.biz.LianjiaDicChengJiaoBiz;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;
import com.dsj.modules.oldHouseParser.service.DicDealLogsCrawlerService;
import com.dsj.modules.oldhouse.enums.CertificateTypeEnum;
@Service
public class LianjiaDicChengJiaoBizImpl implements LianjiaDicChengJiaoBiz {
	
	@Autowired
	DicDealLogsCrawlerService dicDealLogsCrawlerService;
	
	static String chengJiaoUrl="https://bj.lianjia.com/chengjiao/";
	public  void dealDicChengJiaoList(String originDicId){
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		configThread.start();
		Document doc= DOMJsonpConfig.getDocByPropUrl(chengJiaoUrl+"c"+originDicId,config);
	    String countStr=StringUtils.getNumberByText(doc.getElementsByClass("total fl").get(0).text());
      
        if(StringUtils.isNotBlank(countStr)){
    	  Integer count=Integer.parseInt(countStr);
    	  Integer pageLast=count/30+1;
    	  if(count<10000){
    		  for(int i=1;i<=pageLast;i++){
    			  dealDicChengJiaoListByPage(originDicId,i,config);
    			  
    		  }
    	  }
       }
	}
	
	public  List<DicDealLogsCrawlerPo> dealDicChengJiaoListByPage(String originDicId,int pg,CrawlerConfig config){
		 Document doc= DOMJsonpConfig.getDocByPropUrl(chengJiaoUrl+"pg"+pg+"c"+originDicId,config);
		 return getDealPoBy(originDicId,doc);
	}
	
	@Override
	public  void dealDicChengjiaoList(String originDicId,CrawlerConfig config){
		 Document doc= DOMJsonpConfig.getDocByPropUrl(chengJiaoUrl+"c"+originDicId,config);
	      String countStr=StringUtils.getNumberByText(doc.getElementsByClass("total fl").get(0).text());
	   
	      if(StringUtils.isNotBlank(countStr)){
	    	  Integer count=Integer.parseInt(countStr);
	    	  Integer pageLast=count/30+1;
	    	  if(count<10000){
	    		  for(int i=1;i<=pageLast;i++){
	    			  List<DicDealLogsCrawlerPo> dics=new ArrayList<DicDealLogsCrawlerPo>();
	    			  dics=dealDicChengJiaoListByPage(originDicId,i,config);
	    			  	
	    			  Boolean b=dicDealLogsCrawlerService.saveDicDealLogs(dics);
	    			  if(b){
	    				  break;
	    			  }
	    		  }
	    	  }
	      }
	}
	
	@Override
	public List<DicDealLogsCrawlerPo> getDealPoBy(String originDicId,Document doc){
		 
		 List<DicDealLogsCrawlerPo> dics=new ArrayList<DicDealLogsCrawlerPo>();
		 if(doc.getElementsByClass("listContent").size()>0){
			 Elements elesContent= doc.getElementsByClass("listContent").get(0).getElementsByTag("li");
			 for(Element ele:elesContent){
				 DicDealLogsCrawlerPo dic=new DicDealLogsCrawlerPo();
				
				 dic.setOriginImageUrl(	ele.getElementsByTag("img").attr("data-original"));
				 dic.setOriginDicId(originDicId);
				 String originHouseIdHref=ele.getElementsByTag("a").attr("href");
				 dic.setOriginHouseId(StringUtils.getNumberByText(originHouseIdHref));
				 
				 //列表内容
				 Elements infoContent=ele.getElementsByClass("info");
				 if(infoContent.size()>0){
					 String title=infoContent.get(0).getElementsByClass("title").get(0).text();
					dic.setTitle(title);
					String[] titleArr=title.split(" ");
					if(titleArr.length>1){
						String room=titleArr[1].split("室")[0];
						if(StringUtils.isNotBlank(room)&&!room.contains("-")){
							dic.setRoom(Integer.parseInt(room));
						}
						
						String hall=titleArr[1].split("室")[1].replace("厅","");
						if(StringUtils.isNotBlank(hall)&&!hall.contains("-")){
							dic.setHall(Integer.parseInt(hall));
						}
					}
					
					if(titleArr.length>2){
						String area=titleArr[2];
						dic.setBuildArea(Double.parseDouble(area.replace("平米", "")));
					}
					
					
					Element houseInfo=infoContent.get(0).getElementsByClass("address").get(0).getElementsByClass("houseInfo").get(0);
					String houseInfoStr=houseInfo.ownText();
					
					String[] houseInfoStrArr=houseInfoStr.trim().replace("|", ",").split(",");
					dic.setOrientationsName(houseInfoStrArr[0]);
					dic.setRenovationName(houseInfoStrArr[1]);
					if(houseInfoStrArr.length>2){
						if(houseInfoStrArr[2].contains("有")){
							dic.setIsElevator(YesNoEnum.YES.getValue());
						}else{
							dic.setIsElevator(YesNoEnum.NO.getValue());
						}
					}
					Element dealDate=infoContent.get(0).getElementsByClass("address").get(0).getElementsByClass("dealDate").get(0);
					if(!dealDate.ownText().contains("近")){
						dic.setContractDate(DateUtils.string2Date(dealDate.ownText().replace(".", "-")));
					}
					
					if(infoContent.get(0).getElementsByClass("address").get(0)
							.getElementsByClass("totalPrice").get(0)
							.getElementsByClass("number").size()>0){
						Element totalPrice=infoContent.get(0).getElementsByClass("address").get(0)
								.getElementsByClass("totalPrice").get(0)
								.getElementsByClass("number").get(0);
						
						String price=totalPrice.ownText();
						if(StringUtils.isNotBlank(price)&&
								org.apache.commons.lang.StringUtils.isNumeric(price)){
							dic.setPrice(new BigDecimal(price));
						}
					}
					
					if(infoContent.get(0).getElementsByClass("flood").get(0)
							.getElementsByClass("unitPrice").get(0)
							.getElementsByClass("number").size()>0){
						Element totalPrice=infoContent.get(0).getElementsByClass("flood").get(0)
								.getElementsByClass("unitPrice").get(0)
								.getElementsByClass("number").get(0);
						
						String unitPrice=totalPrice.ownText();
						if(StringUtils.isNotBlank(unitPrice)&&
								org.apache.commons.lang.StringUtils.isNumeric(unitPrice)){
							dic.setUnitPrice(new BigDecimal(unitPrice));
						}
					}
					
					Element positionIcon=infoContent.get(0).getElementsByClass("flood").get(0)
							.getElementsByClass("positionInfo").get(0);
						
					String positionIconStr=positionIcon.ownText();//高楼层(共28层) 2009年建板塔结合
					String[] positionIconArr=positionIconStr.split(" ");
					dic.setFloorNum(Integer.parseInt(StringUtils.getNumberByText(
							positionIconArr[0].replace("(", "").replace(")", ""))));
					
					dic.setBuildTypeName(positionIconStr);
					FloorTypeEnum[] ary = FloorTypeEnum.values();
					for (int i=0;i<ary.length;i++) {
						if(ary[i].getDesc().equals(positionIconArr[0].split("楼层")[0])){
							dic.setFloorType(ary[i].getValue());
						}
					}
					if("底层".equals(positionIconArr[0])){
						dic.setFloorType(FloorTypeEnum.BOTTOM.getValue());
					}
					if("顶层".equals(positionIconArr[0])){
						dic.setFloorType(FloorTypeEnum.HEIGHT.getValue());
					}
					
					if(positionIconArr.length>=2){
						dic.setBuildYear(StringUtils.getNumberByText(positionIconArr[1]));
						if(positionIconArr[1].split("年建").length>1){
							dic.setBuildTypeName(positionIconArr[1].split("年建")[1]);
						}
					}
					
					
					Element source=infoContent.get(0).getElementsByClass("flood").get(0)
							.getElementsByClass("source").get(0);
					dic.setSource(source.ownText());
					
					if(infoContent.get(0).getElementsByClass("dealHouseInfo").size()>0
							&&infoContent.get(0).getElementsByClass("dealHouseInfo").get(0)
							.getElementsByClass("dealHouseTxt").size()>0){
						Elements dealHouseTxts=infoContent.get(0).getElementsByClass("dealHouseInfo").get(0)
						.getElementsByClass("dealHouseTxt").get(0).getElementsByTag("span");
					//System.out.println(dealHouseTxts.get(0));
					
						if(dealHouseTxts.size()>=2){
							if(dealHouseTxts.get(1).ownText().contains("满五年")){
								dic.setCertificateType(CertificateTypeEnum.FIVE.getValue());
							}else if(dealHouseTxts.get(1).ownText().contains("满两年")){
								dic.setCertificateType(CertificateTypeEnum.TWO.getValue());
							}
						}
					}
					
					if(infoContent.get(0).getElementsByClass("dealCycleeInfo").size()>0){
						Elements dealCycleTxt=infoContent.get(0).getElementsByClass("dealCycleeInfo").get(0)
						.getElementsByClass("dealCycleTxt").get(0).getElementsByTag("span");
						if(dealCycleTxt.size()>=2){
							dic.setListedPrice(new BigDecimal(StringUtils.getNumberByText(dealCycleTxt.get(1).ownText())));
						}
						if(dealCycleTxt.size()>=3){
							dic.setDealInfo(dealCycleTxt.get(2).ownText());
						}
					}
					
				 }
				 
				 if(dic.getPrice()!=null && dic.getContractDate()!=null&&dic.getUnitPrice()!=null){
					 dics.add(dic);
				 }
			 }
		 }
		 return dics;
	}
	
/*	public static void main(String[] args) {
		dealDicChengJiaoList("1111027375945");
	}*/
}

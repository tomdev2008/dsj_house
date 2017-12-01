package com.dsj.data.lianjia.biz.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.CrawlerPropxyUtils;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.data.lianjia.biz.LianjiaErshoufangBiz;
import com.dsj.modules.oldHouseParser.po.HouseAgentCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseTradeInfoCrawlerPo;
import com.dsj.modules.oldHouseParser.po.IpPoolPo;
import com.dsj.modules.oldHouseParser.po.MatserHouseTypesPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.OldMasterLianjiaAreaPo;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldHouseParser.service.IpPoolService;
import com.dsj.modules.oldHouseParser.service.MasterCrawlerTaskService;
import com.dsj.modules.oldHouseParser.service.OldMasterLianjiaAreaService;
import com.dsj.modules.oldhouse.enums.CertificateTypeEnum;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.other.enums.PictureTypeEnum;

@Service
public class LianjiaErshoufangBizImpl implements LianjiaErshoufangBiz{
	private static final Logger logger = LoggerFactory.getLogger(LianjiaErshoufangBizImpl.class);
	@Autowired
	IpPoolService ipPoolService;
	
	@Autowired
	OldMasterLianjiaAreaService oldMasterLianjiaAreaService;

	@Autowired
	HouseMasterCrawlerService houseMasterCrawlerService;
	
	@Autowired
	MasterCrawlerTaskService masterCrawlerTaskService;
	

	class ErshufangThread extends Thread{
		private List<OldMasterLianjiaAreaPo> areaThreads;
		CrawlerConfig config;
		private Integer proxyType;//1 动态id 2转发
		ErshufangThread(List<OldMasterLianjiaAreaPo> areaThreads,CrawlerConfig config,Integer proxyType) {  
			   this.areaThreads = areaThreads;  
			   this.config=config ;
			   this.proxyType=proxyType;
		}  
		@Override
		public void run() {
			logger.info("areaThreads size：{}",areaThreads.size());
			dealEshoufangList(areaThreads,config,proxyType);
		}  
	}
	
	// 二手房数据处理
	@Override
	public void dealErshoufangByArea(Integer proxyType) {
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=null;
		if(proxyType!=2){
			 configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
			
			 //停顿2秒防止取不到ip
			 try {
				Thread.currentThread().sleep(2000);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
			
			 configThread.start();
		}
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("fatherId", 0);
		List<OldMasterLianjiaAreaPo> lianjiaAreas  = oldMasterLianjiaAreaService.listBy(paramMap);
		
		List<OldMasterLianjiaAreaPo> areaThreads1=new ArrayList<OldMasterLianjiaAreaPo>();
		List<OldMasterLianjiaAreaPo> areaThreads2=new ArrayList<OldMasterLianjiaAreaPo>();
		List<OldMasterLianjiaAreaPo> areaThreads3=new ArrayList<OldMasterLianjiaAreaPo>();
		List<OldMasterLianjiaAreaPo> areaThreads4=new ArrayList<OldMasterLianjiaAreaPo>();
		
		for(int i=0;i<lianjiaAreas.size();i++){
			if(i<2){
				areaThreads1.add(lianjiaAreas.get(i));
			}
			else if(i==2){
				areaThreads2.add(lianjiaAreas.get(i));
			}
			else if(i>2 && i<=6){
				areaThreads3.add(lianjiaAreas.get(i));
			}
			else{
				areaThreads4.add(lianjiaAreas.get(i));
			}
		}
		ErshufangThread eshoufangthread1=new ErshufangThread(areaThreads1,config,proxyType);
		
		Thread thread1 = new Thread(eshoufangthread1);  
		thread1.start();  
		
		ErshufangThread eshoufangthread2=new ErshufangThread(areaThreads2,config,proxyType);
		Thread thread2 = new Thread(eshoufangthread2);  
		thread2.start();
		
		ErshufangThread eshoufangthread3=new ErshufangThread(areaThreads3,config,proxyType);
		Thread thread3 = new Thread(eshoufangthread3);  
		thread3.start();
		
		ErshufangThread eshoufangthread4=new ErshufangThread(areaThreads4,config,proxyType);
		Thread thread4 = new Thread(eshoufangthread4);  
		thread4.start();
		
		while(true){
			if(!thread1.isAlive() && !thread2.isAlive() && !thread3.isAlive()&& !thread4.isAlive()){
				if(proxyType!=2){
					configThread.interrupt();
				}
				masterCrawlerTaskService.updateDynamicTaskByIndex(0);
				break;
			}
			try {
				Thread.currentThread().sleep(2*60*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	// 二手房数据处理
	@Override
	public void dealErshoufangByArea(String name) {
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("areaAme", name);
	
		List<OldMasterLianjiaAreaPo> lianjiaAreas  = oldMasterLianjiaAreaService.listBy(paramMap);

		configThread.start();
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		
		dealEshoufangList(lianjiaAreas,config,2);
		
		configThread.interrupt();
	}

	
	private void dealEshoufangList(List<OldMasterLianjiaAreaPo> lianjiaAreas,CrawlerConfig config,Integer proxyType){
		for (OldMasterLianjiaAreaPo areaPo : lianjiaAreas) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("fatherId", areaPo.getId());
			List<OldMasterLianjiaAreaPo> lianjiaTradeAreas = oldMasterLianjiaAreaService.listBy(paramMap);
			for (OldMasterLianjiaAreaPo po : lianjiaTradeAreas) {
			
				Integer page = 1;
				Integer pageLimit = po.getHouseCount() / 30;

				String url = "";
				;
				if (url.contains("https://bj.lianjia.com")) {
					url += po.getAreaListUrl();
				} else {
					if (po.getTradeListUrl().contains("xianghe") || po.getTradeListUrl().contains("yanjiao")) {
						url += "https://lf.lianjia.com" + po.getTradeListUrl();
					} else {
						url += "https://bj.lianjia.com" + po.getTradeListUrl();
					}
				}
				while (page <= pageLimit) {
					url += "pg" + page;
					try {

						secondHandHousingList(url, config, page, areaPo.getAreaAme(), po.getTradeName(), proxyType);
					} catch (Exception e) {
						logger.error("列表错误：", e, e.getMessage());
						secondHandHousingList(url, config, page, areaPo.getAreaAme(), po.getTradeName(), proxyType);

					}
					
					po.setStatus(1);
					oldMasterLianjiaAreaService.updateDynamic(po);
					
					url = url.replace("pg" + page, "");
					page++;
				}
			}
			
			areaPo.setStatus(1);
			oldMasterLianjiaAreaService.updateDynamic(areaPo);
		}
	}


	
	private  void secondHandHousingList(String url, CrawlerConfig config, Integer page, String areaName,
			String tradeName,Integer proxyType) {
		Document doc=null;
		if(proxyType==2){
			doc=CrawlerPropxyUtils.connect(url);
		}else{
			doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		
		}
		
		Elements elesContent=null;
		try{
			elesContent=doc.getElementsByClass("sellListContent").get(0).getElementsByTag("li");
		}catch(Exception e){
			secondHandHousingList( url,  config,  page,  areaName,
					 tradeName,proxyType);
		}
		logger.info("列表地址"+url);
		for(Element ele:elesContent){
			String detailUrl=ele.getElementsByClass("img").attr("href");
			logger.info("详情地址："+detailUrl);

			try{
				secondHandHousingDetail(detailUrl, config, areaName, tradeName,proxyType);
			}catch(Exception e){
				
				e.printStackTrace();
				logger.error("详情错误+"+detailUrl+"：",e,e.getMessage());
				//secondHandHousing.setIsError(1);
			}
		}
	}

	
	private Document getDocByPropUrl(String url){
		IpPoolPo ipPool=ipPoolService.getByLast();
		try {
			return DOMJsonpConfig.getDocByPropUrl(url, ipPool.getIp(), Integer.parseInt(ipPool.getPort()));
		} catch (NumberFormatException e) {
			logger.error("getDocByPropUrl error:",e,e.getMessage());
			getDocByPropUrl(url);
		} catch (Exception e) {
			logger.error("getDocByPropUrl error:",e,e.getMessage());
			getDocByPropUrl(url);
		}
		return null;
	}

	@Override
	public  void secondHandHousingDetail(String url, CrawlerConfig config, String areaName, String tradeName,Integer proxyType) {
		HouseMasterCrawlerPo houseMasterCrawler=new HouseMasterCrawlerPo();
		Document doc=null;
		if(proxyType==2){
			doc=CrawlerPropxyUtils.connect(url);
		}else{
			doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		
		}
		//Document doc= DOMJsonpConfig.getDocByUrl(url);
		Elements elesContent =doc.getElementsByClass("m-content");
		//基本信息
		Elements elesBasic =elesContent.get(0).getElementsByClass("newwrap baseinform").get(0).getElementsByClass("base").get(0).getElementsByClass("content").get(0).getElementsByTag("li");
	//	logger.info(elesBasic.html());
		getBasciInfo(elesBasic,houseMasterCrawler);
		//secondHandHousing.setBasicInfo(basicInfo);
		
		
		
		houseMasterCrawler.setOriginHouseId(url.split("/")[4].replace(".html", ""));
		
		//基础信息
		Elements elseInfo=doc.getElementsByClass("overview").get(0).getElementsByClass("content");
		getHousingInfo(elseInfo,houseMasterCrawler);
		
		//交易信息
		Elements elesPay =elesContent.get(0).getElementsByClass("newwrap baseinform").get(0).getElementsByClass("transaction").get(0).getElementsByClass("content").get(0).getElementsByTag("li");
	//	logger.info(elesPay.html());
		getPayInfo(elesPay,houseMasterCrawler);
		
		
		
		//房源特色
		Elements elesSpecial =elesContent.get(0).getElementsByClass("introContent showbasemore").get(0).getElementsByTag("div");
		getSpecialInfos(elesSpecial,houseMasterCrawler);
		
		Element elesType =elesContent.get(0).getElementById("layout");
		if(doc.getElementsByClass("layout-wrapper")!=null&&doc.getElementsByClass("layout-wrapper").size()>0){
			Elements elesLayoutpic=doc.getElementsByClass("layout-wrapper").get(0).getElementsByClass("content");
			getHouseingTypeInfo(elesType,elesLayoutpic,houseMasterCrawler);
	
		}
		
		//二手房图片
		//System.out.println(doc.getElementById("thumbnail2").html());
		Elements elesImage =doc.getElementsByTag("script");
		getErshoufangImage(elesImage,houseMasterCrawler);
		
		Elements elseAgent=doc.getElementsByClass("brokerInfo clear");
		getAgent(elseAgent,houseMasterCrawler);
		
		//小区
		Elements eleVill =doc.getElementsByClass("intro clear").get(0).getElementsByClass("container").get(0).getElementsByClass("fl l-txt");
		getHouseingVillageInfo( eleVill,config,houseMasterCrawler,  areaName,  tradeName,proxyType);
		houseMasterCrawler.setOriginDicId(houseMasterCrawler.getHouseAlikeCommunity().getOriginCommunityId());
		
		Elements elseTitle=doc.getElementsByClass("sellDetailHeader").get(0).getElementsByClass("content").get(0).getElementsByClass("title").get(0).getElementsByClass("main");
		houseMasterCrawler.setTitle(elseTitle.get(0).ownText());
		houseMasterCrawler.setCompanyType(CompanyTypeEnum.LIANJIA.getValue());
		houseMasterCrawler.setCreateTime(new Date());
		
		Element  aroundInfo=doc.getElementsByClass("overview").get(0).getElementsByClass("aroundInfo").get(0);
		
		getAroundInfo(aroundInfo,houseMasterCrawler);
	
		houseMasterCrawlerService.saveHouseMasterCrawler(houseMasterCrawler,houseMasterCrawler.getHouseAlikeCommunity(),
				houseMasterCrawler.getPictureCrawler(),houseMasterCrawler.getOldHousePicture(),
				houseMasterCrawler.getAgent(), CompanyTypeEnum.getEnum(CompanyTypeEnum.LIANJIA.getValue()));
		
/*		void saveHouseMasterCrawler(HouseMasterCrawlerPo houseMasterCrawler, HouseAlikeCommunityPo dic,
				List<HousePictureCrawlerPo> dicPicList	, List<OldHousePictureCrawlerPo> oldPicList,
				HouseAgentCrawlerPo agent,CompanyTypeEnum companyType);*/
	}
	

	private void getAroundInfo(Element aroundInfo, HouseMasterCrawlerPo houseMasterCrawler) {
		
		
		if(aroundInfo.getElementsByClass("areaName").size()>0){
			Element areaName=aroundInfo.getElementsByClass("areaName").get(0);
			String ring=areaName.getElementsByClass("info").get(0).ownText();
			houseMasterCrawler.setRing(ring.trim().replace(" ", ""));
			if(areaName
					.getElementsByTag("a").size()>=3){
				
				houseMasterCrawler.setSubwayUrl(areaName
					.getElementsByTag("a").get(2).attr("href"));
				houseMasterCrawler.setSubwayDetail(areaName
					.getElementsByTag("a").get(2).ownText());
			}
		}
		
		
		
	}

	private void getHouseingTypeInfo(Element eleType, Elements elesLayoutpic,
			HouseMasterCrawlerPo houseMasterCrawler) {
		
		Elements row=eleType.getElementsByClass("row");
		//po.setOriginImageUrl(elesLayoutpic.get(0).getElementsByTag("div").get(2).attr("data-img")+".720x540.jpg");
		List<MatserHouseTypesPo> matserHouseTypeList=new ArrayList<MatserHouseTypesPo>();
		for(Element ele:row){
			MatserHouseTypesPo po=new MatserHouseTypesPo();
			po.setName(ele.getElementsByClass("col").get(0).text());
			po.setArea(ele.getElementsByClass("col").get(1).text());
			po.setOrientationsName(ele.getElementsByClass("col").get(2).text());
			po.setWindTypeName(ele.getElementsByClass("col").get(3).text());
			matserHouseTypeList.add(po);
		}
		houseMasterCrawler.setHouseTypeLjPicUrl(elesLayoutpic.get(0).getElementsByTag("div").get(2).attr("data-img")+".720x540.jpg");
		houseMasterCrawler.setMatserHouseTypeList(matserHouseTypeList);
	}

	private void getPayInfo(Elements elesPay, HouseMasterCrawlerPo houseMasterCrawler) {
		// TODO Auto-generated method stub
		houseMasterCrawler.setHouseTypeName(elesPay.get(3).ownText());
		
		String certificateTypeName=elesPay.get(4).ownText();
		
		if(StringUtils.isNotBlank(certificateTypeName)){
			if("满五年".equals(certificateTypeName)){
				houseMasterCrawler.setCertificateType(CertificateTypeEnum.FIVE.getValue());
			}else if("满两年".equals(certificateTypeName)){
				houseMasterCrawler.setCertificateType(CertificateTypeEnum.TWO.getValue());
			}
		}
		
		HouseTradeInfoCrawlerPo payInfo=new HouseTradeInfoCrawlerPo();
		String tradeDate=elesPay.get(0).ownText();
		String listingDate=elesPay.get(2).ownText();
		payInfo.setListingDate(DateUtils.string2Date(listingDate));
		payInfo.setTradeOwnership(elesPay.get(1).ownText());
		payInfo.setLastPayDate(DateUtils.string2Date(tradeDate));
		payInfo.setHousingUse(elesPay.get(3).ownText());
		payInfo.setCertificateTypeName(certificateTypeName);
		payInfo.setPropertyRightOwnership(elesPay.get(5).ownText());
		payInfo.setMortgageInfo(elesPay.get(6).ownText());
		payInfo.setSpareParts(elesPay.get(7).ownText());
		
		houseMasterCrawler.setTradeInfo(payInfo);
	}



	private void getAgent(Elements elseAgent, HouseMasterCrawlerPo houseMasterCrawler) {

		HouseAgentCrawlerPo agent=new HouseAgentCrawlerPo();
		Elements elseAgentImage=elseAgent.get(0).getElementsByClass("fl LOGVIEW LOGCLICK");
		agent.setAgentUrl(elseAgentImage.get(0).attr("href"));
		agent.setAgentPicUrl(elseAgentImage.get(0).getElementsByTag("img").get(0).attr("src"));
		Elements elseDiv=elseAgent.get(0).getElementsByClass("brokerInfoText fr");
		agent.setAgentName(elseDiv.get(0).getElementsByClass("brokerName").get(0).getElementsByClass("name LOGCLICK").get(0).ownText());
		//System.out.println(elseDiv.get(0).getElementsByClass("brokerName").get(0).getElementsByClass("tag first").get(0).ownText());
	/*	agent.setIntro(elseDiv.get(0).getElementsByClass("brokerName").get(0).getElementsByClass("tag first").get(0).ownText());	*/
		//System.out.println(elseDiv.get(0).getElementsByClass("phone").get(0).ownText());
		agent.setAgentPhone(elseDiv.get(0).getElementsByClass("phone").get(0).ownText().replace(" ", "-"));
		agent.setCompanyType(CompanyTypeEnum.LIANJIA.getValue());
		agent.setOldMasterCrawlerId(houseMasterCrawler.getId());
		agent.setOriginHouseId(houseMasterCrawler.getOriginHouseId());
		agent.setCreateTime(new Date());
		agent.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		houseMasterCrawler.setAgent(agent);
		
	}



	private void getErshoufangImage(Elements elesImage, HouseMasterCrawlerPo houseMasterCrawler) {
		List<OldHousePictureCrawlerPo> oldHousePictureList=new ArrayList<OldHousePictureCrawlerPo>();
		for (Element ele : elesImage) {
			String script = ele.toString();
			if (script.indexOf("ershoufang/sellDetail/detailV3") > -1) {
				script = ele.childNode(0).toString();
				// 使用ScriptEngine來parse
				//System.out.println("图片处理："+ele.html());
				ScriptEngine engine = new ScriptEngineManager().getEngineByName("javascript");
				String imagesStr=replaceBlank(script.split("images:")[1]);
				imagesStr=imagesStr.split("}]")[0]+"}]";
				//System.out.println(engine.toString());
				List<Map<String,Object>> listMap=JsonTools.parseJSON2List(imagesStr);
				for(Map<String,Object> map:listMap){
					OldHousePictureCrawlerPo pic=new OldHousePictureCrawlerPo();
					pic.setOriginPictureUrl(map.get("uri").toString()+".1420x800.jpg");
					pic.setOriginObjId(houseMasterCrawler.getOriginHouseId());
					pic.setPictureType(PictureTypeEnum.INSIDE.getValue());
				    dealErshoufangPic( pic) ;
				    oldHousePictureList.add(pic);
				}
			}
		}
		houseMasterCrawler.setOldHousePicture(oldHousePictureList);
	}
	

	private void dealErshoufangPic(OldHousePictureCrawlerPo pic) {
		pic.setCompayType(CompanyTypeEnum.LIANJIA.getValue());
		pic.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		pic.setIsDownload(YesNoEnum.NO.getValue());
		pic.setIsUpload(YesNoEnum.NO.getValue());
		pic.setCreateTime(new Date());
		pic.setUpdateTime(new Date());
	}



	public static String replaceBlank(String str) {
		        String dest = "";
		        if (str!=null) {
		            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		            Matcher m = p.matcher(str);
		            dest = m.replaceAll("");
		        }
		        return dest;
	}


	private void getHousingInfo(Elements elseInfo, HouseMasterCrawlerPo houseMasterCrawler) {
		//System.out.println(elseInfo.html());
		Elements elsePrice= elseInfo.get(0).getElementsByClass("price");
		houseMasterCrawler.setPrice(Double.parseDouble(elsePrice.get(0).getElementsByClass("total").get(0).ownText()));
	
		String unitPrice=elsePrice.get(0).getElementsByClass("text").get(0)
				.getElementsByClass("unitPrice").get(0)
				.getElementsByClass("unitPriceValue").get(0).ownText();
		if(StringUtils.isNotBlank(unitPrice)){
			houseMasterCrawler.setUnitPrice(Double.parseDouble(unitPrice));
		}
		
		if(elsePrice.get(0).getElementsByClass("tax").get(0)
				.getElementsByClass("taxtext").size()>0){
			String payments=elsePrice.get(0).getElementsByClass("tax").get(0)
					.getElementsByClass("taxtext").get(0)
					.getElementsByTag("span").get(1).ownText();
			System.out.println(payments);
			if(StringUtils.isNotBlank(payments)&&payments.contains("首付")){
				houseMasterCrawler.setPayments(Double.parseDouble(payments.replace("首付","").replace("万", "")));
				
			}
		}
		
		
		//建筑年代
		Elements elsehouseInfo= elseInfo.get(0).getElementsByClass("houseInfo").get(0).getElementsByClass("area").get(0)
				.getElementsByClass("subInfo");
		String buildYear=elsehouseInfo.get(0).ownText();
		if(StringUtils.isNotBlank(buildYear)){
			houseMasterCrawler.setBuildYear(buildYear.split("/")[0].replace("年建", ""));
		}
		
		
		//装修情况
	/*	Elements renovationInfo= elseInfo.get(0).getElementsByClass("houseInfo").get(0).getElementsByClass("type").get(0)
				.getElementsByClass("subInfo");
		String renovationName=renovationInfo.get(0).ownText();
		if(StringUtils.isNotBlank(renovationName)&&renovationName.split("/").length>=2){
			houseMasterCrawler.setRenovationName(renovationName.split("/")[1]);
		}*/
	}



	private  void getHouseingVillageInfo(Elements eleVill, CrawlerConfig config,
			HouseMasterCrawlerPo houseMasterCrawler, String areaName, String tradeName,Integer proxyType) {
		HouseAlikeCommunityPo villInfo=new HouseAlikeCommunityPo();
		
		List<HousePictureCrawlerPo> pictureCrawlers=new ArrayList<HousePictureCrawlerPo>();
		
		String xiaoquDetail=eleVill.get(0).getElementsByTag("a").get(4).attr("href");
		if(StringUtils.isNotBlank(xiaoquDetail)){
		//	logger.info("小区信息："+"https://bj.lianjia.com/xiaoqu"+xiaoquDetail.replace("c", "").split("/")[2]);
			
			Document doc=null;
			if(proxyType==2){
				doc=CrawlerPropxyUtils.connect("https://bj.lianjia.com/xiaoqu/"+xiaoquDetail.replace("c", "").split("/")[2]);
			}else{
				doc= DOMJsonpConfig.getDocByPropUrl("https://bj.lianjia.com/xiaoqu/"+xiaoquDetail.replace("c", "").split("/")[2],config);
			
			}
			
			//System.out.println(doc.html());
			if(doc.getElementsByClass("xiaoquInfo").size()>0){
			Elements eles =doc.getElementsByClass("xiaoquInfo").get(0).getElementsByClass("xiaoquInfoItem");
		
			villInfo.setOriginCommunityId(xiaoquDetail.replace("c", "").split("/")[2]);
			villInfo.setName(doc.getElementsByClass("detailTitle").get(0).ownText());
			villInfo.setAveragePrice(doc.getElementsByClass("xiaoquUnitPrice").get(0).ownText());
			
			
			villInfo.setStore(eles.get(7).getElementsByClass("xiaoquInfoContent").get(0).ownText());
			String propertyFee=eles.get(2).getElementsByClass("xiaoquInfoContent").get(0).ownText();
			if(!"暂无信息".equals(propertyFee)){
				villInfo.setPropertyFee(propertyFee);
			}
			villInfo.setPropertyCompany(eles.get(3).getElementsByClass("xiaoquInfoContent").get(0).ownText());
			villInfo.setDeveloper(eles.get(4).getElementsByClass("xiaoquInfoContent").get(0).ownText());
			villInfo.setAreaName1("北京市");
			villInfo.setAreaCode1("110000");
			villInfo.setAreaName2("北京市");
			villInfo.setAreaCode2("110100");
			if(StringUtils.isNotBlank(areaName) && !areaName.contains("区") &&  !areaName.contains("香河") &&!areaName.contains("燕郊")){
				villInfo.setAreaName3(areaName+"区");
			}else{
				villInfo.setAreaName3(areaName);
			}
			villInfo.setTradingAreaName(tradeName);
			villInfo.setCreateTime(new Date());
			String buildYear=eles.get(0).getElementsByClass("xiaoquInfoContent").get(0).ownText();
			if(StringUtils.isNotBlank(buildYear)){
				villInfo.setBuiltYear(buildYear.replace("年建成", "").replace("年建", ""));
			}
			//villInfo.setListingHouses(eles.get(1).ownText());
			}
			
			List<String> bigs=new ArrayList<String>();
			List<String> smalls=new ArrayList<String>();
			//if(doc.getElementById("overviewThumbnail")!=null){
			//if(){
				Elements elesImage =doc.getElementById("overviewThumbnail").getElementsByTag("li");
				for(Element ele:elesImage ){
					bigs.add(ele.attr("data-src"));
					smalls.add(ele.getElementsByTag("img").get(0).attr("src"));
					
					HousePictureCrawlerPo pic =new HousePictureCrawlerPo();
					pic.setOriginObjId(xiaoquDetail.replace("c", "").split("/")[2]);
					pic.setOriginPictureUrl(ele.attr("data-src"));
					pic.setObjType(PictureTypeEnum.BUILDING.getValue());
					dealHousePic(pic);
					pictureCrawlers.add(pic);
				}
			//}
		
			Elements elesScript =doc.getElementsByTag("script");
			for(Element ele:elesScript){
				if(ele.html().contains("ershoufang/xiaoquDetail/index")){
				//	System.out.println(ele);
					String html=ele.html().split("resblockPosition")[1].split("resblockName")[0].replace(":", "").replace("'", "").replace("\n", "").trim();
					html=html.substring(0, html.length()-1);
					villInfo.setResblockPosition(html);
					if(StringUtils.isNotBlank(html)&&html.split(",").length>0){
						villInfo.setAccuracy(html.split(",")[0]);
						villInfo.setDimension(html.split(",")[1]);
					}
				}
			}
		}
		
		houseMasterCrawler.setHouseAlikeCommunity(villInfo);
		houseMasterCrawler.setPictureCrawler(pictureCrawlers);
	}

	
	//小区图片处理
	private  void dealHousePic(HousePictureCrawlerPo pic) {
		pic.setCompayType(CompanyTypeEnum.LIANJIA.getValue());
		pic.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		pic.setIsDownload(YesNoEnum.NO.getValue());
		pic.setIsUpload(YesNoEnum.NO.getValue());
		pic.setPictureType(PictureTypeEnum.BUILDING.getValue());
		pic.setCreateTime(new Date());
	}


	private  void getSpecialInfos(Elements eles,HouseMasterCrawlerPo houseMasterCrawler) {
	
		Elements ele1  =eles.get(0).getElementsByClass("baseattribute clear");
		Elements eleTags  =eles.get(0).getElementsByClass("tags clear");
		String featuresName="";
		for(Element ele:eleTags){//特色
			featuresName=ele.getElementsByClass("content").text()+",";
		}
		if(StringUtils.isNotBlank(featuresName)){
			houseMasterCrawler.setFeaturesName(featuresName.substring(0,featuresName.length()-1).replace(" ", ","));
		}
	
		
		if(ele1.size()>0){
			if("核心卖点".equals(ele1.get(0).getElementsByClass("name").text())){
				houseMasterCrawler.setSellingPoint(ele1.get(0).getElementsByClass("content").text());
			}
		}
		
		
	}



	public  HouseMasterCrawlerPo getBasciInfo(Elements elesBasic,HouseMasterCrawlerPo houseMasterCrawler){
		String layoutName=elesBasic.get(0).ownText();
		if(StringUtils.isNotBlank(layoutName)){
			if(layoutName.contains("室")){
				if(layoutName.split("室").length>0){
					houseMasterCrawler.setRoom(Integer.parseInt(layoutName.split("室")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("室")[0]+"室","");
			}
			
			if(layoutName.contains("房间")){
				if(layoutName.split("房间").length>0){
					houseMasterCrawler.setRoom(Integer.parseInt(layoutName.split("房间")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("房间")[0]+"房间","");
			}
			if(layoutName.contains("厅")){
				if(layoutName.split("厅").length>0){
					houseMasterCrawler.setHall(Integer.parseInt(layoutName.split("厅")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("厅")[0]+"厅","");
			}
			if(layoutName.contains("厨")){
				if(layoutName.split("厨").length>0){
					houseMasterCrawler.setKitchen(Integer.parseInt(layoutName.split("厨")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("厨")[0]+"厨","");
			}
			if(layoutName.contains("卫")){
				if(layoutName.split("卫").length>0){
					houseMasterCrawler.setToilet(Integer.parseInt(layoutName.split("卫")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("卫")[0]+"卫","");
			}
		}
		String floorname=elesBasic.get(1).ownText();
		
		if(StringUtils.isNotBlank(floorname)){//高楼层 (共21层)
			String[] strs=floorname.split(" ");
			if(strs.length>0){
				houseMasterCrawler.setFloorTypeName(strs[0]);
				
				FloorTypeEnum[] ary = FloorTypeEnum.values();
				for (int i=0;i<ary.length;i++) {
					if(ary[i].getDesc().equals(strs[0].replace("楼层", ""))){
						houseMasterCrawler.setFloorType(ary[i].getValue());
					}
				}
				
				if("底层".equals(strs[0])){
					houseMasterCrawler.setFloorType(FloorTypeEnum.BOTTOM.getValue());
				}
				
				
				if("顶层".equals(strs[0])){
					houseMasterCrawler.setFloorType(FloorTypeEnum.HEIGHT.getValue());
				}
			
			
			}
			if(strs.length>1){
				houseMasterCrawler.setFloorNum(Integer.parseInt(strs[1].replace("(共", "").replace("层)", "")));
			}
		}
		
		String buildArea=elesBasic.get(2).ownText();
		if(StringUtils.isNotBlank(buildArea)){
			houseMasterCrawler.setBuildArea(Double.parseDouble(buildArea.replace("㎡", "")));//建筑面积
		}
		/*if(!"暂无数据".equals(elesBasic.get(3).ownText())){
			houseMasterCrawler.setHouseTypeName(elesBasic.get(3).ownText());
		}*/
		houseMasterCrawler.setOrientationsName(elesBasic.get(6).ownText());
	//	String buildYear=elesBasic.get(7).ownText();
		
		if(elesBasic.size()>=13){
			houseMasterCrawler.setCertificateName(elesBasic.get(12).ownText());
		}
	
		//houseMasterCrawler.setRenovationName(elesBasic.get(8).ownText());
		if(elesBasic.size()>=11){
			houseMasterCrawler.setHeatingMode(elesBasic.get(10).ownText());
		}
		//houseMasterCrawler.setHouseTypeName(houseTypeName);
		
		if(elesBasic.size()==13){
			houseMasterCrawler.setBuildingStruture(elesBasic.get(5).ownText());//建筑结构
			houseMasterCrawler.setStairScale(elesBasic.get(9).ownText());
			
			if(elesBasic.get(11).ownText().contains("有")){
				houseMasterCrawler.setIsElevator(YesNoEnum.YES.getValue());
			}else{
				houseMasterCrawler.setIsElevator(YesNoEnum.NO.getValue());
			}
			if(!elesBasic.get(7).ownText().contains("暂无数据")){
				houseMasterCrawler.setLayoutStructure(elesBasic.get(7).ownText());
			}
			houseMasterCrawler.setRenovationName(elesBasic.get(8).ownText());
		}
		if(!elesBasic.get(4).ownText().contains("暂无数据")){
			houseMasterCrawler.setInsideArea(new BigDecimal(elesBasic.get(4).ownText().replace("㎡", "")));
		}
		
		return houseMasterCrawler;
	}
	
	public static void main(String[] args) {
	/*	 CrawlerConfig config=new CrawlerConfig();
		 new Thread(config.new GetIP(14*1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620")).start();
		 secondHandHousingDetail("https://bj.lianjia.com/ershoufang/101102224344.html",config,"","");*/
	/*	try {
			Document doc	=DOMJsonpConfig.getDocByPropUrl("https://bj.lianjia.com/", "119.136.114.248", 9999);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		String layoutName="4室2厅1厨4卫";
		if(StringUtils.isNotBlank(layoutName) && !layoutName.contains("房间")){
			if(layoutName.contains("室")){
				if(layoutName.split("室").length>0){
					//houseMasterCrawler.setRoom(Integer.parseInt(layoutName.split("室")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("室")[0]+"室","");
			}
			if(layoutName.contains("厅")){
				if(layoutName.split("厅").length>0){
					//houseMasterCrawler.setHall(Integer.parseInt(layoutName.split("厅")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("厅")[0]+"厅","");
			}
			if(layoutName.contains("厨")){
				if(layoutName.split("厨").length>0){
					//houseMasterCrawler.setKitchen(Integer.parseInt(layoutName.split("厨")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("厨")[0]+"厨","");
			}
			if(layoutName.contains("卫")){
				if(layoutName.split("卫").length>0){
					System.out.println(Integer.parseInt(layoutName.split("卫")[0]));
				}
				layoutName=layoutName.replace(layoutName.split("卫")[0]+"卫","");
			}
		}
		
		if(StringUtils.isNotBlank(layoutName) && !layoutName.contains("房间")){
			
		}
	}
}

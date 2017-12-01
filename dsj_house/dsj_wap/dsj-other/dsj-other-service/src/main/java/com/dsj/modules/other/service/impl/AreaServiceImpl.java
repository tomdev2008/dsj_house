package com.dsj.modules.other.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.utils.PinyinUtil;
import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.DOMJsonpConfig;
import com.dsj.common.utils.lngLat.LngLatUtil;
import com.dsj.modules.other.dao.AreaDao;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 15:25:36.
 * @版本: 1.0 .
 */
@Service
public class AreaServiceImpl  extends BaseServiceImpl<AreaDao,AreaPo> implements AreaService {
	@Autowired
	private AreaDao areaDao;
	@Autowired
	TradeAreaService tradeAreaService;
	@Autowired
	SubwayService subwayService;
	@Override
	public String findNameByAreaCode(String areaCode) {
		String name  = areaDao.findNameByAreaCode(areaCode);
		return name;
	}
	@Override
	public AreaPo getMaxIDArea(HashMap<String, Object> map) {
		return areaDao.getMaxIDArea(map);
	}
	@Override
	public List<AreaPo> listParent(HashMap<String, Object> map) {
		return areaDao.listParent(map);
	}
	@Override
	public List<AreaPo> getAreaList() {
		return dao.getAreaList();
	}
	
	@Override
	public void saveCrawlerArea(){
		saveLianjiaAreaByConfig("https://bj.lianjia.com/ershoufang/");
	}
	

	public   void saveLianjiaAreaByConfig(String url){
		
		 CrawlerConfig config=new CrawlerConfig();
		 new Thread(config.new GetIP(14*1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620")).start();
		try {
				saveLianjiaArea(url,config);
		} catch (Exception e) {
			saveLianjiaArea(url,config);
			logger.error("链家地区错误：",e,e.getMessage());
		}
	}
	//地区
	public  void saveLianjiaArea(String url,CrawlerConfig config){
		Document doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		Elements areaEles= doc.getElementsByClass("position").get(0).getElementsByAttributeValue("data-role", "ershoufang");
		
		List<TradeAreaPo> areas=new ArrayList<TradeAreaPo>();
	
		Elements eles1=areaEles.get(0).getElementsByTag("div").get(1).getElementsByTag("a");
		for(Element ele:eles1 ){
		/*	TradeAreaPo model=new TradeAreaPo();
			//System.out.println(ele.attr("href")+":"+ele.ownText());
			model.setName(ele.ownText());
			model.setNameListUrl(ele.attr("href"));
			model.setStatus(0);
			model.setUpdateDate(new Date());*/
		//	LianjiaSecondAreaModel model1=lianjiaSecondAreaSevice.getLianjiaAreaByName(ele.ownText());
			String urlList="https://bj.lianjia.com";
			if(!ele.attr("href").contains("https://bj.lianjia.com")){
				urlList+=ele.attr("href");
			}else{
				urlList=ele.attr("href");
			}
		/*	if(model1!=null){
				model.setId(model1.getId());
			}*/
			String areaName=ele.ownText();
			if(urlList.contains("https://lf.lianjia.com")){
				urlList=urlList.replace("https://bj.lianjia.com", "");
			}
			try {
				areas=saveLianjiaArea1(urlList,config,areaName,areas);
			//	model.setAreas(areas);
				//lianjiaSecondAreaSevice.addSecondArea(model);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("链家商圈错误：",e,e.getMessage());
			}
		
		}
		for(TradeAreaPo tradeArea:areas){
			if(tradeArea!=null && StringUtils.isNotBlank(tradeArea.getName())){
				tradeAreaService.saveDynamic(tradeArea);
			}
		}
		//tradeAreaService.save(areas);
		
	}
//字地区
	public  List<TradeAreaPo> saveLianjiaArea1(String url,CrawlerConfig config,String areaName,List<TradeAreaPo> areas){
		Document doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		System.out.println(doc.getElementsByClass("position").get(0));
		Elements areaEles= doc.getElementsByClass("position").get(0).getElementsByAttributeValue("data-role", "ershoufang");
		Elements eles2=areaEles.get(0).getElementsByTag("div").get(2).getElementsByTag("a");
		
		String lianjiaAreaUrl="https://bj.lianjia.com";
		if(url.contains("xianghe")||url.contains("yanjiao")){
			lianjiaAreaUrl="https://lf.lianjia.com";
		}
		
		Map<String, Object> paramMapArea=new HashMap<String,Object>();
		paramMapArea.put("name",areaName );
		paramMapArea.put("parentId","110100" );
		AreaPo area=getBy(paramMapArea);
		for(Element ele:eles2 ){
			TradeAreaPo tradeAreaPo=new TradeAreaPo();
			
			tradeAreaPo.setName(ele.ownText());
			tradeAreaPo.setParentId(area.getId());
			buildTradeAreaPo(tradeAreaPo);
			tradeAreaPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			tradeAreaPo.setCreateTime(new Date());
			if(area!=null){
				
				Map<String, Object> paramMap=new HashMap<String,Object>();
				paramMap.put("name",tradeAreaPo.getName() );
				TradeAreaPo tradeArea=tradeAreaService.getBy(paramMap);
				if(tradeArea==null){
					//tradeAreaService.saveDynamic(tradeAreaPo);
					areas.add(tradeAreaPo);
				}
			}
			
		}
		
		return areas;
	}
	
	private void buildTradeAreaPo(TradeAreaPo tradeAreaPo) {
		tradeAreaPo.setLikePinyin(PinyinUtil.getFirstWord(tradeAreaPo.getName()));
		tradeAreaPo.setEnglishHead(PinyinUtil.getFirstWords(tradeAreaPo.getName()));
		tradeAreaPo.setEnglishName(PinyinUtil.getPinyin(tradeAreaPo.getName()));
		if (tradeAreaPo.getParentId()!=null) {
			AreaPo fatherPo = getById(tradeAreaPo.getParentId());
			AreaPo grandfatherPo = getById(fatherPo.getParentId());
			tradeAreaPo.setDimension(LngLatUtil.latLng(grandfatherPo.getName()+fatherPo.getName()+tradeAreaPo.getName())[0]);
			tradeAreaPo.setAccuracy(LngLatUtil.latLng(grandfatherPo.getFullName()+fatherPo.getName()+tradeAreaPo.getName())[1]);
		}
	}
	
	//地铁数据处理
	@Override
	public void saveCrawlerSubway() {
		saveLianjiaSubwayByConfig("https://bj.lianjia.com/ditiefang/li647/");
	}
	
	public   void saveLianjiaSubwayByConfig(String url){
		
		 CrawlerConfig config=new CrawlerConfig();
		 new Thread(config.new GetIP(14*1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620")).start();
		try {
				saveLianjiaSubway(url,config);
		} catch (Exception e) {
			saveLianjiaArea(url,config);
			logger.error("链家地区错误：",e,e.getMessage());
		}
	}
	
	//地铁
	public  void saveLianjiaSubway(String url,CrawlerConfig config){
		Document doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		Elements areaEles= doc.getElementsByClass("position").get(0).getElementsByAttributeValue("data-role", "ditiefang");
		
	
		Elements eles1=areaEles.get(0).getElementsByTag("div").get(1).getElementsByTag("a");
		int sort=0;
		for(Element ele:eles1 ){
			SubwayPo subway=new SubwayPo();
			subway.setName(ele.ownText());
			subway.setCreateTime(new Date());
			subway.setUpdateTime(new Date());
			subway.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			subway.setCityId(110100l);
			subway.setSort(sort);
			subway.setPid(0);
			sort++;
			String urlList= "https://bj.lianjia.com";
			if(!ele.attr("href").contains("https://bj.lianjia.com")){
				urlList+=ele.attr("href");
			}else{
				urlList=ele.attr("href");
			}
			Long subwayId=subwayService.saveDynamic(subway);
			try {
				saveLianjiaSubway(urlList,config,subwayId);
			//	model.setAreas(areas);
				//lianjiaSecondAreaSevice.addSecondArea(model);
			}catch(Exception e){
				e.printStackTrace();
				logger.error("地铁：",e,e.getMessage());
			}
		}
	}
	private void saveLianjiaSubway(String url, CrawlerConfig config, Long subwayId) {
		Document doc= DOMJsonpConfig.getDocByPropUrl(url,config);
		System.out.println(doc.getElementsByClass("position").get(0));
		Elements areaEles= doc.getElementsByClass("position").get(0).getElementsByAttributeValue("data-role", "ditiefang");
		Elements eles2=areaEles.get(0).getElementsByTag("div").get(2).getElementsByTag("a");
	
		int sort=0;
		for(Element ele:eles2 ){
			SubwayPo subway=new SubwayPo();
			subway.setName(ele.ownText());
			subway.setCreateTime(new Date());
			subway.setUpdateTime(new Date());
			subway.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			subway.setCityId(110100l);
			subway.setSort(sort);
			subway.setPid(subwayId.intValue());
			subway.setAccuracy(LngLatUtil.latLng("北京市北京"+ele.ownText())[0]);
			subway.setDimension(LngLatUtil.latLng("北京市北京"+ele.ownText())[1]);
			sort++;
			subwayService.saveDynamic(subway);
		}
		
		
	}
	@Override
	public List<AreaPo> getRrareaList() {
		return dao.getRrareaList();
	}
	@Override
	public void updateTrea(Map<String, Object> map) {
		dao.updateTrea(map);
	}
	
	@Override
	public List<AreaPo> getAreaList(Map<String, Object> map) {
		return dao.getAreaList(map);
	}

}
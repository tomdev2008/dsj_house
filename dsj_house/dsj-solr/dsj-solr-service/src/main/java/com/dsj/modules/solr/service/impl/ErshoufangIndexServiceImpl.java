package com.dsj.modules.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.Group;
import org.apache.solr.client.solrj.response.GroupCommand;
import org.apache.solr.client.solrj.response.GroupResponse;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.FrontAreaEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.enums.FrontErshoufangWyTypeEnum;
import com.dsj.common.enums.FrontPriceEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.enums.OrderingRuleEnum;
import com.dsj.modules.solr.service.ErshoufangIndexService;
import com.dsj.modules.solr.utils.SolrContent;
import com.dsj.modules.solr.utils.SolrStatementUtils;
import com.dsj.modules.solr.utils.SolrSynaxConstant;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;
import com.dsj.modules.solr.vo.ErshoufangSolrVo;
@Service
public class ErshoufangIndexServiceImpl implements ErshoufangIndexService{
	


	private final Logger LOGGER = LoggerFactory.getLogger(ErshoufangIndexServiceImpl.class);
	
	
	@Autowired
	SolrContent solrContent;
	SolrClient solrClinet;
	public   SolrClient getInterface() {
		if (solrClinet == null) {
			solrClinet =solrContent.getSolrClient();
		}
		return solrClinet;
	}
	
	public void addItemIndex(ErshoufangIndexFiled fields) throws IOException, SolrServerException{
		//LOGGER.info("solr save:");
			getInterface().addBean(fields);
			getInterface().commit();
	}

	public void addItemIndexs(List<ErshoufangIndexFiled> fieldsList) throws SolrServerException, IOException{
		
		
			getInterface().addBeans(fieldsList);
			getInterface().commit();
		
	}
	
	public void deleteItemIndex(String id) throws SolrServerException, IOException{
		
		getInterface().deleteById(id);
		getInterface().commit();
		
	}
	
	public void updateItemIndex(ErshoufangIndexFiled fields) throws SolrServerException, IOException{
		
		getInterface().deleteById(fields.getId());
		getInterface().commit();
		
	}
	
	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException{
		getInterface().deleteByQuery(query);
		getInterface().commit();
	
	}

	@Override
	public PageBean getErshoufangSolr(int pageSize,ErShoufangQueryVo queryVo) {
		
		PageBean page=null;
		String params=dealSolrQuery(queryVo);
		
		LOGGER.info("二手房查询参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		Integer pageNum=CommConst.PAGE_NUM;
		if(queryVo.getPageNum()!=null){
			pageNum=queryVo.getPageNum();
		}
		List<ErshoufangSolrVo> recordList=new ArrayList<ErshoufangSolrVo>();
		query.setStart((pageNum-1)*pageSize).setRows(pageSize);
		if(queryVo.getOrdering()!=null){
			if(OrderingRuleEnum.PRICE_DESC.getValue()==queryVo.getOrdering()){
				query.addSort("price", ORDER.desc);
			}else if(OrderingRuleEnum.PRICE_ASC.getValue()==queryVo.getOrdering()){
				query.addSort("price", ORDER.asc);
			}else if(OrderingRuleEnum.AREA_DESC.getValue()==queryVo.getOrdering()){
				query.addSort("buildArea", ORDER.desc);
			}else if(OrderingRuleEnum.AREA_ASC.getValue()==queryVo.getOrdering()){
				query.addSort("buildArea", ORDER.asc);
			}else if(OrderingRuleEnum.BUILD_YEAR_DESC.getValue()==queryVo.getOrdering()){
				query.addSort("buildYear", ORDER.desc);
			}else if(OrderingRuleEnum.BUILD_YEAR_ASC.getValue()==queryVo.getOrdering()){
				query.addSort("buildYear", ORDER.asc);
			}else if(OrderingRuleEnum.UPDATE_DESC.getValue()==queryVo.getOrdering()){
				query.addSort("updateTime", ORDER.desc);
			}else if(OrderingRuleEnum.UPDATE_ASC.getValue()==queryVo.getOrdering()){
				query.addSort("updateTime", ORDER.asc);
			}
		}else{
			query.addSort("updateTime", ORDER.desc);
		}
		try {
			QueryResponse resp = getInterface().query(query);
			recordList=resp.getBeans(ErshoufangSolrVo.class);
			page=new PageBean(pageNum, pageSize, new Long(resp.getResults().getNumFound()).intValue(), recordList);
		} catch (SolrServerException | IOException e) {
			LOGGER.error("solr服务器错误：",e);
		} 
		return page;
	}
	
	
	@Override
	public 	PageBean getGroupAreaCode3(int pageSize,ErShoufangQueryVo queryVo) throws SolrServerException, IOException {
		PageBean page=getErshoufangSolr(pageSize,queryVo);
		Map<String,Object> countResultMap=new HashMap<String,Object>();
		countResultMap.put("mapCount", getGroupAreaCode3(queryVo));
		page.setCountResultMap(countResultMap);
		return page;
	}
	
	@Override
	public 	List<AreaLatLngGroupVo> getGroupAreaCode3(ErShoufangQueryVo queryVo) throws SolrServerException, IOException {
		String params=dealSolrQuery(queryVo);
		LOGGER.info("二手房查询参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		query.setFacetLimit(-1);
		query.setParam("group", true);
		query.setParam("group.field", "areaCode3");
		query.setParam("group.ngroups", true);
		query.setStart(0).setRows(30);
		QueryResponse resp = solrClinet.query(query);
		GroupResponse gresp = resp.getGroupResponse();
		List<GroupCommand> commands = gresp.getValues();
		List<AreaLatLngGroupVo> lists=new ArrayList<AreaLatLngGroupVo>();
		if(commands!=null){
			for (GroupCommand com : commands) {
				LOGGER.info("总的分组个数：" + com.getNGroups().longValue());
				for (Group group : com.getValues()) {
					System.out.println(group.getGroupValue() + ","
							+ (int) group.getResult().getNumFound());

					AreaLatLngGroupVo vo = new AreaLatLngGroupVo();
				
					System.out.println(group.getResult());
					vo.setCount((int) group.getResult().getNumFound());
					SolrDocumentList sds = group.getResult();
					
					if(group.getGroupValue()!=null){
						for (SolrDocument doc : sds) {
							vo.setId(doc.getFieldValue("areaCode3").toString());
							vo.setName(doc.getFieldValue("areaName3").toString());
							if(doc.getFieldValue("areaAccuracy")!=null){
								vo.setLat(doc.getFieldValue("areaAccuracy").toString());
							}
							if(doc.getFieldValue("areaDimension")!=null){
								vo.setLng(doc.getFieldValue("areaDimension").toString());
							}
							lists.add(vo);
						}
					}
				}
			}
			
		}
		return lists;
	}
	
	
	//参数处理
	private String dealSolrQuery(ErShoufangQueryVo queryVo){
		String params="";
		Map<String,String> caseMap=new HashMap<String,String>();
		if(StringUtils.isNotBlank(queryVo.getAreaCode2())){
			caseMap.put("areaCode2", queryVo.getAreaCode2());
		}
		if(StringUtils.isNotBlank(queryVo.getAreaCode3())){
			caseMap.put("areaCode3", queryVo.getAreaCode3());
		}
		
		if(queryVo.getTradingAreaId()!=null){
			caseMap.put("tradingAreaId",String.valueOf(queryVo.getTradingAreaId()));
		}
		
		if(queryVo.getOrientations()!=null){
			caseMap.put("orientations", queryVo.getOrientations().toString());
		}
		
		if(queryVo.getRenovation()!=null){
			caseMap.put("renovation", queryVo.getRenovation().toString());
		}
		
		
		if(queryVo.getFloorType()!=null){
			caseMap.put("floorType", queryVo.getFloorType().toString());
		}
		
		
		if(caseMap.size()>0){
			params=SolrStatementUtils.generateAndMatchStatement(caseMap);
		}
		
		if(queryVo.getPrice()!=null){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+=SolrStatementUtils.generateLongRangeMatchStatementEnum("price",FrontPriceEnum.getEnum(queryVo.getPrice()).getDesc(),queryVo.getPrice());
		}
		
		
		if(queryVo.getPmn()!=null&&queryVo.getPmx()!=null){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+=SolrStatementUtils.generateRangeMatchStatement("price",queryVo.getPmn().doubleValue(),queryVo.getPmx().doubleValue());
		}
		
		
		if(queryVo.getAmn()!=null&&queryVo.getAmx()!=null){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+=SolrStatementUtils.generateRangeMatchStatement("buildArea",queryVo.getAmn().doubleValue(),queryVo.getAmx().doubleValue());
		}
		
		
		if(queryVo.getBuildArea()!=null){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+=SolrStatementUtils.generateLongRangeMatchStatementEnum("buildArea",FrontAreaEnum.getEnum(queryVo.getBuildArea()).getDesc().replace(BusinessConst.AREA_UNIT, ""),queryVo.getBuildArea());
			
		}
		
		if(queryVo.getRoom()!=null){
			String room=queryVo.getRoom().toString();
			if(queryVo.getRoom()<FrontRoomEnum.FIVE.getValue()){
				caseMap.put("room", room);
				if(!StringUtils.isNotBlank(params)){
					params+=SolrStatementUtils.generateAndMatchStatement(caseMap);
				}else{
					params+=" && "+SolrStatementUtils.generateAndMatchStatement(caseMap);
				}
			}else{
				if(StringUtils.isNotBlank(params)){
					params+=" && ";
				}
				params+=SolrStatementUtils.generateBaseMatchStatement("room",SolrSynaxConstant.BRACKETS_START+queryVo.getRoom()+SolrSynaxConstant.TO+SolrSynaxConstant.ANY+SolrSynaxConstant.BRACKETS_END);
			}
		}
		
		if(StringUtils.isNotBlank(queryVo.getAreaCode3s())){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			String strs[]=queryVo.getAreaCode3s().split(",");
			for(int i=0;i<strs.length;i++){
				if(i==0){
					params+="(areaCode3:"+strs[i];
				
				}else{
						params+=SolrSynaxConstant.OR+"areaCode3:"+strs[i];
				}
			}
			params+=")";
			
		}
		
		if(StringUtils.isNotBlank(queryVo.getTradingAreaIds())){
			
			
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			String strs[]=queryVo.getTradingAreaIds().split(",");
			for(int i=0;i<strs.length;i++){
				if(i==0){
					params+="(tradingAreaId:"+strs[i];
				
				}else{
						params+=SolrSynaxConstant.OR+"tradingAreaId:"+strs[i];
				}
			}
			params+=")";
		}
			
		if(StringUtils.isNotBlank(queryVo.getDicIds())){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			String strs[]=queryVo.getDicIds().split(",");
			for(int i=0;i<strs.length;i++){
				if(i==0){
					params+="(dicId:"+strs[i];
				
				}else{
						params+=SolrSynaxConstant.OR+"dicId:"+strs[i];
				}
			}
			params+=")";
		}
		
		if(null !=queryVo.getCompanyType()){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			if(FrontCompanyTypeEnum.OTHER.getValue()==queryVo.getCompanyType()){
				params+=SolrStatementUtils.generateBlurMatchStatement("companyTypes","3,4");
			}else{
				params+=SolrStatementUtils.generateBlurMatchStatement("companyTypes",queryVo.getCompanyType());
			}
		}
		
		if(StringUtils.isNotBlank(queryVo.getSubway())){
			
			
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			String strs[]=queryVo.getSubway().split("&");
			for(int i=0;i<strs.length;i++){
				if(i==0){
					params+="(subway:"+SolrSynaxConstant.ANY +strs[i]+SolrSynaxConstant.ANY ;
				
				}else{
						params+=SolrSynaxConstant.OR+"subway:"+SolrSynaxConstant.ANY +strs[i]+SolrSynaxConstant.ANY;

				}
			}
			params+=")";
		}
		
		if(StringUtils.isNotBlank(queryVo.getSubwayline())){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+="subwayline:"+SolrSynaxConstant.ANY +","+queryVo.getSubwayline()+","+SolrSynaxConstant.ANY;
		}
		
		
		if(queryVo.getWyType()!=null){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			FrontErshoufangWyTypeEnum enumType=FrontErshoufangWyTypeEnum.getEnum(queryVo.getWyType());
			switch(enumType){
				case ZZ:
					params+="(wyType:590 "+SolrSynaxConstant.OR +"wyType:611 "+SolrSynaxConstant.OR +"wyType:384)";
				break;
				
				case GY:
					//params+="wyType:(385,532,533,590,611,736,966)";
					
					params+="(wyType:385 "+SolrSynaxConstant.OR +"wyType:532 "+SolrSynaxConstant.OR +"wyType:533 " 
					+SolrSynaxConstant.OR +"wyType:590 "+SolrSynaxConstant.OR +"wyType:611 "
					+SolrSynaxConstant.OR +"wyType:736 "+SolrSynaxConstant.OR +"wyType:736)";
				break;
					
				case BS:
					params+="wyType:386";
				break;
					
				case PF:
					params+="wyType:387";
				break;
				
				case SHY:
					params+="wyType:388";
				break;
				
				case QT:
					params+="(wyType:389 "+SolrSynaxConstant.OR +"wyType:512)";
				break;
			}
			//params+="subwayline:"+SolrSynaxConstant.ANY +","+queryVo.getSubwayline()+","+SolrSynaxConstant.ANY;
		}

	
		
		if(StringUtils.isNotBlank(queryVo.getKeywords())){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+="search_item:"+queryVo.getKeywords().trim();
		}
		
		if(StringUtils.isNotBlank(queryVo.getKw())){
			if(StringUtils.isNotBlank(params)){
				params+=" && ";
			}
			params+="search_item:\""+queryVo.getKw().trim()+"\"";
		}
			
		if(StringUtils.isBlank(params)){
			params=SolrStatementUtils.generateMatchAnyStatement();
		}
		return params;
	}

	@Override
	public PageBean getGroupTrade(int pageSize, ErShoufangQueryVo queryVo) throws SolrServerException, IOException {
		PageBean page=getErshoufangSolr(pageSize,queryVo);
		Map<String,Object> countResultMap=new HashMap<String,Object>();
		countResultMap.put("mapCount", getGroupTrade(queryVo));
		page.setCountResultMap(countResultMap);
		return page;
	}
	
	@Override
	public 	List<AreaLatLngGroupVo> getGroupTrade(ErShoufangQueryVo queryVo) throws SolrServerException, IOException {
		String params=dealSolrQuery(queryVo);
		LOGGER.info("二手房地图商圈查询参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		query.setFacetLimit(-1);
		query.setParam("group", true);
		query.setParam("group.field", "tradingAreaId");
		query.setParam("group.ngroups", true);
		query.setStart(0).setRows(1000);
		QueryResponse resp = getInterface().query(query);
		GroupResponse gresp = resp.getGroupResponse();
		List<GroupCommand> commands = gresp.getValues();
		List<AreaLatLngGroupVo> lists=new ArrayList<AreaLatLngGroupVo>();
		if(commands!=null){
			for (GroupCommand com : commands) {
				LOGGER.info("总的分组个数：" + com.getNGroups().longValue());
				for (Group group : com.getValues()) {
					System.out.println(group.getGroupValue() + ","
							+ (int) group.getResult().getNumFound());

					AreaLatLngGroupVo vo = new AreaLatLngGroupVo();
				
					System.out.println(group.getResult());
					vo.setCount((int) group.getResult().getNumFound());
					SolrDocumentList sds = group.getResult();
					
					if(group.getGroupValue()!=null){
						for (SolrDocument doc : sds) {
							vo.setId(doc.getFieldValue("tradingAreaId").toString());
							vo.setName(doc.getFieldValue("tradingAreaName").toString());
							if(doc.getFieldValue("tradeAccuracy")!=null){
								vo.setLat(doc.getFieldValue("tradeAccuracy").toString());
							}
							if(doc.getFieldValue("tradeDimension")!=null){
								vo.setLng(doc.getFieldValue("tradeDimension").toString());
							}
							lists.add(vo);
						}
					}
				}
			}
			
		}
		return lists;
	}

	@Override
	public PageBean getGroupDic(int pageSize, ErShoufangQueryVo queryVo) throws SolrServerException, IOException {
		PageBean page=getErshoufangSolr(pageSize,queryVo);
		Map<String,Object> countResultMap=new HashMap<String,Object>();
		countResultMap.put("mapCount", getGroupDic(queryVo));
		page.setCountResultMap(countResultMap);
		return page;
	}
	
	@Override
	public 	List<AreaLatLngGroupVo> getGroupDic(ErShoufangQueryVo queryVo) throws SolrServerException, IOException {
		String params=dealSolrQuery(queryVo);
		
		LOGGER.info("二手房地区小区查询参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		query.setFacetLimit(-1);
		query.setParam("group", true);
		query.setParam("group.field", "dicId");
		query.setParam("group.ngroups", true);
		query.setStart(0).setRows(10000);
		
		QueryResponse resp = getInterface().query(query);
		GroupResponse gresp = resp.getGroupResponse();
		List<GroupCommand> commands = gresp.getValues();
		List<AreaLatLngGroupVo> lists=new ArrayList<AreaLatLngGroupVo>();
		if(commands!=null){
			for (GroupCommand com : commands) {
				LOGGER.info("总的分组个数：" + com.getNGroups().longValue());
				for (Group group : com.getValues()) {
					/*System.out.println(group.getGroupValue() + ","
							+ (int) group.getResult().getNumFound());*/

					AreaLatLngGroupVo vo = new AreaLatLngGroupVo();
				
					/*System.out.println(group.getResult());*/
					vo.setCount((int) group.getResult().getNumFound());
					SolrDocumentList sds = group.getResult();
					
					if(group.getGroupValue()!=null){
						for (SolrDocument doc : sds) {
							if(doc.getFieldValue("dicName") !=null){
								vo.setId(doc.getFieldValue("dicId").toString());
								vo.setName(doc.getFieldValue("dicName").toString());
								if(doc.getFieldValue("accuracy")!=null){
									vo.setLat(doc.getFieldValue("accuracy").toString());
								}
								if(doc.getFieldValue("dimension")!=null){
									vo.setLng(doc.getFieldValue("dimension").toString());
								}
								lists.add(vo);
							}
						}
					}
				}
			}
			
		}
		return lists;
	}

}

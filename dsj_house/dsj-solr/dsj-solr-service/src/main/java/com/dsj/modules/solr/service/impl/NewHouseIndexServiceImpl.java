package com.dsj.modules.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import com.dsj.common.enums.FrontPriceEnum;
import com.dsj.common.enums.FrontRefrencePriceEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.service.CommonIndexService;
import com.dsj.modules.solr.service.ErshoufangIndexService;
import com.dsj.modules.solr.service.NewHouseCommonIndexService;
import com.dsj.modules.solr.service.NewHouseIndexService;
import com.dsj.modules.solr.utils.SolrContent;
import com.dsj.modules.solr.utils.SolrStatementUtils;
import com.dsj.modules.solr.utils.SolrSynaxConstant;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.CommonIndexFiled;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;
import com.dsj.modules.solr.vo.ErshoufangSolrVo;
import com.dsj.modules.solr.vo.newHouse.NewHouseAgentVo;
import com.dsj.modules.solr.vo.newHouse.NewHouseIndexFiled;
import com.dsj.modules.solr.vo.newHouse.NewHouseQueryVo;
import com.dsj.modules.solr.vo.newHouse.NewHouseSolrVo;

@Service
public class NewHouseIndexServiceImpl implements NewHouseIndexService {
	
	@Autowired
	CommonIndexService commonIndexService;
	@Autowired
	NewHouseCommonIndexService  newHouseCommonIndexService;
	
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseIndexServiceImpl.class);
	
	@Autowired
	SolrContent solrContent;
	SolrClient solrClinet;
	public  SolrClient getInterface() {
		if (solrClinet == null) {
			solrClinet =solrContent.getNewHouseSolrClient();
		}
		return solrClinet;
	}
	
	public void addItemIndexs(List<NewHouseIndexFiled> fieldsList) throws SolrServerException, IOException {
		
		ArrayList<CommonIndexFiled> commonIndexList = new ArrayList<CommonIndexFiled>();
		for (NewHouseIndexFiled newHouseIndexFiled : fieldsList) {
			LOGGER.info("solr添加新房:{}", newHouseIndexFiled.getId());
			commonIndexList.clear();
			//地区
			CommonIndexFiled addressCommonIndexFiled = new CommonIndexFiled();
			addressCommonIndexFiled.setName(newHouseIndexFiled.getAreaName3());
			addressCommonIndexFiled.setType(1);
			addressCommonIndexFiled.setId(newHouseIndexFiled.getAreaCode3());
			commonIndexList.add(addressCommonIndexFiled);
			//商圈
			CommonIndexFiled tradeCommonIndexFiled = new CommonIndexFiled();
			tradeCommonIndexFiled.setName(newHouseIndexFiled.getTradingAreaName());
			tradeCommonIndexFiled.setType(2);
			tradeCommonIndexFiled.setId(newHouseIndexFiled.getTradingAreaId().toString());
			commonIndexList.add(tradeCommonIndexFiled);
			//楼盘名称
			CommonIndexFiled newHouseCommonIndexFiled = new CommonIndexFiled();
			newHouseCommonIndexFiled.setName(newHouseIndexFiled.getName());
			newHouseCommonIndexFiled.setType(3);
			newHouseCommonIndexFiled.setId(newHouseIndexFiled.getId());
			commonIndexList.add(newHouseCommonIndexFiled);
			//查询联想添加
			newHouseCommonIndexService.addItemIndexs(commonIndexList);
			
		}
		 getInterface().addBeans(fieldsList);
		 getInterface().commit();

	}

	public void deleteItemIndex(String id) throws SolrServerException, IOException {
		LOGGER.info("solr删除新房:{}", id);
		 getInterface().deleteById(id);
		 getInterface().commit();

	}

	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException {
		 getInterface().deleteByQuery(query);
		 getInterface().commit();

	}

	@Override
	public PageBean getNewHouseSolr(int pageSize, NewHouseQueryVo queryVo) {

		PageBean page = null;

		String params = dealSolrQuery(queryVo);;
		
		LOGGER.info("新房房查询参数" + params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		Integer pageNum = CommConst.PAGE_NUM;
		if (queryVo.getPageNum() != null) {
			pageNum = queryVo.getPageNum();
		}
		List<NewHouseSolrVo> recordList = new ArrayList<NewHouseSolrVo>();
		query.setStart((pageNum - 1) * pageSize).setRows(pageSize);
		
		// 排序方式
		switch (queryVo.getOrdering()) {
		case 1:
			query.addSort("orderNum", ORDER.asc);
			query.addSort("isSaleOut", ORDER.desc);
			query.addSort("authTime", ORDER.desc);
			break;
		case 2:
			query.addSort("hasPrice", ORDER.asc);
			query.addSort("aroundPriceMin", ORDER.asc);
			query.addSort("totalPriceMin", ORDER.asc);
			query.addSort("referencePriceMin", ORDER.asc);
			break;
		case 3:
			query.addSort("authTime", ORDER.desc);
			break;
		}
		
		try {
			QueryResponse resp =  getInterface().query(query);
			recordList = resp.getBeans(NewHouseSolrVo.class);
			for (NewHouseSolrVo newHouseSolrVo : recordList) {
				
				List<NewHouseAgentVo> agentList = new ArrayList<NewHouseAgentVo>();
				
				if(newHouseSolrVo.getAgent()!=null){
					String agent = newHouseSolrVo.getAgent();
					String[] split = agent.split(",");
					for (String string : split) {
						String[] split2 = string.split("@_s_@");
						NewHouseAgentVo newHouseAgentVo = new NewHouseAgentVo();
						newHouseAgentVo.setId(split2[0]);
						newHouseAgentVo.setHeadImgUrl(split2[1]);
						newHouseAgentVo.setUsername(split2[2]);
						newHouseAgentVo.setRealname(split2[3]);
						newHouseAgentVo.setMobile(split2[4]);
						newHouseAgentVo.setCompanyname(split2[5]);
						newHouseAgentVo.setWorkyear((split2[6]));
						newHouseAgentVo.setJgarea((split2[7]));
						newHouseAgentVo.setDjch(split2[8]);
						newHouseAgentVo.setDjtb(split2[9]);
						newHouseAgentVo.setIslz(Integer.parseInt(split2[10]));
						agentList.add(newHouseAgentVo);
					}
				}
				List<NewHouseAgentVo> agentNewList = new ArrayList<NewHouseAgentVo>();
				
				for (int i = 0; i < agentList.size(); i++) {
					agentNewList.add(i, agentList.get(agentList.size()-i-1));
				}
				
				newHouseSolrVo.setAgentList(agentNewList);
			}
			page = new PageBean(pageNum, pageSize, new Long(resp.getResults().getNumFound()).intValue(), recordList);
		} catch (SolrServerException | IOException e) {
			LOGGER.error("solr服务器错误：", e);
		}
		return page;
	}

	@Override
	public List<AreaLatLngGroupVo> getGroupAreaCode3(NewHouseQueryVo queryVo) throws SolrServerException, IOException {
		String params=dealSolrQuery(queryVo);
		LOGGER.info("新房楼盘查询参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		query.setFacetLimit(-1);
		query.setParam("group", true);
		query.setParam("group.field", "areaCode3");
		query.setParam("group.ngroups", true);
		query.setStart(0).setRows(30);
		QueryResponse resp =  getInterface().query(query);
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
							if(doc.getFieldValue("areaName3")!=null){
								vo.setName(doc.getFieldValue("areaName3").toString());
							}
						
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
		private String dealSolrQuery(NewHouseQueryVo queryVo){
			String params="";
			Map<String,String> caseMap=new HashMap<String,String>();
			if (queryVo.getIsSaleOut()!=null) {
				params="isSaleOut:2";
			}
			//地区
			if (StringUtils.isNotBlank(queryVo.getAreaCode2())) {
				caseMap.put("areaCode2", queryVo.getAreaCode2());
			}
			if (StringUtils.isNotBlank(queryVo.getAreaCode3())) {
				caseMap.put("areaCode3", queryVo.getAreaCode3());
			}
			if (null!= queryVo.getTradingAreaId()) {
				caseMap.put("tradingAreaId", queryVo.getTradingAreaId().toString());
			}
			if(caseMap.size()>0){
				params=SolrStatementUtils.generateAndMatchStatement(caseMap);
			}
			//价格区间
			if(queryVo.getPrice()!=null){
				if(StringUtils.isNotBlank(params)){
					params+=" && ";
				}
				params+=SolrStatementUtils.generateLongRangeMatchStatementEnumTwo("referencePriceMin",FrontRefrencePriceEnum.getEnum(queryVo.getPrice()).getDesc(),queryVo.getPrice());
			}

			//物业类型 
			if (queryVo.getWyType() != null) {
				if (StringUtils.isNotBlank(params)) {
					params += " && ";
				}
				params+=" wyTypeStr:*,"+queryVo.getWyType().toString()+",*";
			}
			
			//特色
		     if (StringUtils.isNotBlank(queryVo.getDictrait())) {
				if (StringUtils.isNotBlank(params)) {
					params += " && ";
				}
				params+=" dicTraitName:*,"+queryVo.getDictrait().toString()+",*";
			}

			if (queryVo.getRoom() != null) {
				String room = queryVo.getRoom().toString();
				if (StringUtils.isNotBlank(params)) {
					params += " && ";
				}
				if (queryVo.getRoom() < FrontRoomEnum.FIVE.getValue()) {
					params+=" rooms:*,"+room+",*";
				} else {
					params +="(rooms:*,5,* or rooms:*,6,* or rooms:*,7,* or rooms:*,8,* or rooms:*,9,* or rooms:*,10,*)";
				}

			}
			
			if(StringUtils.isNotBlank(queryVo.getKeywords())){
				Boolean flag = false;
				if(StringUtils.isNotBlank(params)){
					flag = true;
					params+=" && ";
				}
				params+="  (name:"+SolrSynaxConstant.ANY +queryVo.getKeywords().trim()+SolrSynaxConstant.ANY;
				params+=" or areaName3:"+SolrSynaxConstant.ANY +queryVo.getKeywords().trim()+SolrSynaxConstant.ANY;
				params+=" or tradingAreaName:"+SolrSynaxConstant.ANY +queryVo.getKeywords().trim()+SolrSynaxConstant.ANY;
					params+=")";
			}
			
			if(StringUtils.isNotBlank(queryVo.getKw())){
				Boolean flag = false;
				if(StringUtils.isNotBlank(params)){
					flag = true;
				}
				params+=" ( name:\""+queryVo.getKw().trim();
				params+="\" or areaName3:\""+queryVo.getKw().trim();
				params+="\" or tradingAreaName:\""+queryVo.getKw().trim();
					params+="\")";
			}
			
			
			if(queryVo.getPmn()!=null&&queryVo.getPmx()!=null){
				if(StringUtils.isNotBlank(params)){
					params+=" && ";
				}
				params+=SolrStatementUtils.generateRangeMatchStatement("referencePriceMin",queryVo.getPmn().doubleValue()*10000,queryVo.getPmx().doubleValue()*10000);
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
			
				
			if(StringUtils.isNotBlank(queryVo.getDicIds())){
				if(StringUtils.isNotBlank(params)){
					params+=" && ";
				}
				String strs[]=queryVo.getDicIds().split(",");
				for(int i=0;i<strs.length;i++){
					if(i==0){
						params+="(id:"+strs[i];
					
					}else{
							params+=SolrSynaxConstant.OR+"id:"+strs[i];
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
				
			if(StringUtils.isBlank(params)){
				params=SolrStatementUtils.generateMatchAnyStatement();
			}
			return params;
		}

	@Override
	public PageBean getGroupAreaCode3(int pageSize, NewHouseQueryVo queryVo) throws SolrServerException, IOException {
		PageBean page=getNewHouseSolr(pageSize,queryVo);
		Map<String,Object> countResultMap=new HashMap<String,Object>();
		countResultMap.put("mapCount", getGroupAreaCode3(queryVo));
		page.setCountResultMap(countResultMap);
		return page;
	}


	@Override
	public PageBean getGroupDic(int pageSize, NewHouseQueryVo queryVo) throws SolrServerException, IOException {
		PageBean page=getNewHouseSolr(pageSize,queryVo);
		Map<String,Object> countResultMap=new HashMap<String,Object>();
		countResultMap.put("mapCount", getGroupDic(queryVo));
		page.setCountResultMap(countResultMap);
		return page;
	}


	@Override
	public List<AreaLatLngGroupVo> getGroupDic(NewHouseQueryVo queryVo) throws SolrServerException, IOException {
		String params=dealSolrQuery(queryVo);
		LOGGER.info("新房楼盘查询参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		query.setFacetLimit(-1);
		query.setParam("group", true);
		query.setParam("group.field", "id");
		query.setParam("group.ngroups", true);
		query.setStart(0).setRows(1000);
		QueryResponse resp =  getInterface().query(query);
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
					
						for (SolrDocument doc : sds) {
							vo.setId(doc.getFieldValue("id").toString());
							vo.setName(doc.getFieldValue("name").toString());
							if(doc.getFieldValue("dimension")!=null){
								vo.setLat(doc.getFieldValue("dimension").toString());
							}
							if(doc.getFieldValue("accuracy")!=null){
								vo.setLng(doc.getFieldValue("accuracy").toString());
							}
							lists.add(vo);
						}
				}
			}
			
		}
		return lists;
	}

}

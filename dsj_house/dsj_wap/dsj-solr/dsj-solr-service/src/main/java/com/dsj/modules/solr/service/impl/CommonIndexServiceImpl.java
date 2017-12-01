package com.dsj.modules.solr.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.CommConst;
import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.service.CommonIndexService;
import com.dsj.modules.solr.utils.SolrContent;
import com.dsj.modules.solr.utils.SolrStatementUtils;
import com.dsj.modules.solr.utils.SolrSynaxConstant;
import com.dsj.modules.solr.vo.CommonIndexFiled;
import com.dsj.modules.solr.vo.ErshoufangSolrVo;
@Service
public class CommonIndexServiceImpl implements CommonIndexService{
	private final Logger LOGGER = LoggerFactory.getLogger(CommonIndexServiceImpl.class);

	@Autowired
	SolrContent solrContent;
	
	 SolrClient solrClinet;
	
	 SolrClient newHouseSolrClinet;
	
	public  SolrClient getInterface() {
		if (solrClinet == null) {
			solrClinet =solrContent.getCommonClient();
		}
		return solrClinet;
	}
	
	public void addItemIndex(CommonIndexFiled fields) throws IOException, SolrServerException{
		//LOGGER.info("solr save:");
		getInterface().addBean(fields);
		getInterface().commit();
		
	}

	public void addItemIndexs(List<CommonIndexFiled> fieldsList) throws SolrServerException, IOException{
		
		
		getInterface().addBeans(fieldsList);
		getInterface().commit();
		
	}
	
	public void deleteItemIndex(String id) throws SolrServerException, IOException{
		
		getInterface().deleteById(id);
		getInterface().commit();
		
	}
	
	public void updateItemIndex(CommonIndexFiled fields) throws SolrServerException, IOException{
		
		getInterface().deleteById(fields.getId());
		getInterface().commit();
		
	}
	
	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException{
		getInterface().deleteByQuery(query);
		getInterface().commit();
	
	}

	@Override
	public PageBean getSearchSolr(int pageSize,String name) {
		
		PageBean page=null;
		List<String> statementList=new ArrayList<String>();
		String params="";
		if(StringUtils.isNotBlank(name)){
			statementList.add(SolrStatementUtils.generateBlurMatchStatement("name",name));
			statementList.add(SolrStatementUtils.generateBlurMatchStatement("fullPinyin",name));
			statementList.add(SolrStatementUtils.generateBlurMatchStatement("jianPin",name));
		}
		
		if(statementList.size()>0){
			params=SolrStatementUtils.generateOrQueryByList(statementList);
		}
		
		if(StringUtils.isBlank(params)){
			params=SolrStatementUtils.generateMatchAnyStatement();
		}
		
		LOGGER.info("solr联想参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		Integer pageNum=CommConst.PAGE_NUM;
		
		List<CommonIndexFiled> recordList=new ArrayList<CommonIndexFiled>();
		query.setStart(0).setRows(pageSize);
		
		try {
			QueryResponse resp = getInterface().query(query);
			recordList=resp.getBeans(CommonIndexFiled.class);
			page=new PageBean(pageNum, pageSize, new Long(resp.getResults().getNumFound()).intValue(), recordList);
		} catch (SolrServerException | IOException e) {
			LOGGER.error("solr服务器错误：",e);
		} 
		return page;
	}
	
	@Override
	public PageBean getNewHouseSearchSolr(int pageSize,String name) {
		
		PageBean page=null;
		List<String> statementList=new ArrayList<String>();
		String params="";
		if(StringUtils.isNotBlank(name)){
			statementList.add(SolrStatementUtils.generateBlurMatchStatement("name",name));
			statementList.add(SolrStatementUtils.generateBlurMatchStatement("fullPinyin",name));
			statementList.add(SolrStatementUtils.generateBlurMatchStatement("jianPin",name));
		}
		
		if(statementList.size()>0){
			params=SolrStatementUtils.generateOrQueryByList(statementList);
		}
		
		if(StringUtils.isBlank(params)){
			params=SolrStatementUtils.generateMatchAnyStatement();
		}
		
		LOGGER.info("solr联想参数"+params);
		SolrQuery query = new SolrQuery();
		query.setQuery(params);
		Integer pageNum=CommConst.PAGE_NUM;
		
		List<CommonIndexFiled> recordList=new ArrayList<CommonIndexFiled>();
		query.setStart(0).setRows(pageSize);
		
		try {
			QueryResponse resp = solrContent.getNewHouseSolrClient().query(query);
			recordList=resp.getBeans(CommonIndexFiled.class);
			page=new PageBean(pageNum, pageSize, new Long(resp.getResults().getNumFound()).intValue(), recordList);
		} catch (SolrServerException | IOException e) {
			LOGGER.error("solr服务器错误：",e);
		} 
		return page;
	}

}

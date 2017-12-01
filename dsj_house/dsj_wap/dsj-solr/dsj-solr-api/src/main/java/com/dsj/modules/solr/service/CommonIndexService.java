package com.dsj.modules.solr.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.vo.CommonIndexFiled;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
@Service
public interface CommonIndexService {
	public void addItemIndex(CommonIndexFiled fields) throws SolrServerException, IOException;
	
	public void addItemIndexs(List<CommonIndexFiled> fieldsList)  throws SolrServerException, IOException;
	
	public void deleteItemIndex(String id)  throws SolrServerException, IOException;
	
	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException;
	
	public void updateItemIndex(CommonIndexFiled fields)  throws SolrServerException, IOException;

	public PageBean getSearchSolr(int pageSize,String name);

	PageBean getNewHouseSearchSolr(int pageSize, String name);
	
}






































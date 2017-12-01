package com.dsj.modules.solr.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;
import com.dsj.modules.solr.vo.newHouse.NewHouseIndexFiled;
import com.dsj.modules.solr.vo.newHouse.NewHouseQueryVo;
@Service
public interface NewHouseIndexService {
	
	public void addItemIndexs(List<NewHouseIndexFiled> fieldsList)  throws SolrServerException, IOException;
	
	public void deleteItemIndex(String id)  throws SolrServerException, IOException;
	
	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException;
	
	public PageBean getNewHouseSolr(int pageSize,NewHouseQueryVo queryVo);
	
	List<AreaLatLngGroupVo> getGroupAreaCode3(NewHouseQueryVo queryVo) throws SolrServerException, IOException;
	/**
	 * 区域地区查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupAreaCode3(int pageSize, NewHouseQueryVo queryVo)
			throws SolrServerException, IOException;
	
	
	
	/**
	 *小区查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupDic(int pageSize, NewHouseQueryVo queryVo)
			throws SolrServerException, IOException;


	List<AreaLatLngGroupVo> getGroupDic(NewHouseQueryVo queryVo) throws SolrServerException, IOException;
}






































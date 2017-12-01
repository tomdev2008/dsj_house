package com.dsj.modules.solr.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;
import com.dsj.modules.solr.vo.RentHouseIndexFiled;
import com.dsj.modules.solr.vo.RentHouseQueryVo;
@Service
public interface RentHouseIndexService {
	public void addItemIndex(RentHouseIndexFiled fields) throws SolrServerException, IOException;
	
	public void addItemIndexs(List<RentHouseIndexFiled> fieldsList)  throws SolrServerException, IOException;
	
	public void deleteItemIndex(String id,String type)  throws SolrServerException, IOException;
	
	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException;
	
	public void updateItemIndex(RentHouseIndexFiled fields)  throws SolrServerException, IOException;

	public PageBean getRentHouseSolr(int pageSize,RentHouseQueryVo queryVo);

	List<AreaLatLngGroupVo> getGroupAreaCode3(RentHouseQueryVo queryVo) throws SolrServerException, IOException;
	/**
	 * 区域地区查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupAreaCode3(int pageSize, RentHouseQueryVo queryVo)
			throws SolrServerException, IOException;
	
	
	/**
	 *商圈查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupTrade(int pageSize, RentHouseQueryVo queryVo)
			throws SolrServerException, IOException;
	
	/**
	 *小区查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupDic(int pageSize, RentHouseQueryVo queryVo)
			throws SolrServerException, IOException;

	List<AreaLatLngGroupVo> getGroupTrade(RentHouseQueryVo queryVo) throws SolrServerException, IOException;

	List<AreaLatLngGroupVo> getGroupDic(RentHouseQueryVo queryVo) throws SolrServerException, IOException;
	
}






































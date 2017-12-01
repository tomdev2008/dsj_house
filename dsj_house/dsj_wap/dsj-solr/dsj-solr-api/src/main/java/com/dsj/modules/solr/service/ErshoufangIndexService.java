package com.dsj.modules.solr.service;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.stereotype.Service;

import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;
@Service
public interface ErshoufangIndexService {
	public void addItemIndex(ErshoufangIndexFiled fields) throws SolrServerException, IOException;
	
	public void addItemIndexs(List<ErshoufangIndexFiled> fieldsList)  throws SolrServerException, IOException;
	
	public void deleteItemIndex(String id)  throws SolrServerException, IOException;
	
	public void deleteItemIndexByQuery(String query) throws SolrServerException, IOException;
	
	public void updateItemIndex(ErshoufangIndexFiled fields)  throws SolrServerException, IOException;

	public PageBean getErshoufangSolr(int pageSize,ErShoufangQueryVo queryVo);

	List<AreaLatLngGroupVo> getGroupAreaCode3(ErShoufangQueryVo queryVo) throws SolrServerException, IOException;
	/**
	 * 区域地区查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupAreaCode3(int pageSize, ErShoufangQueryVo queryVo)
			throws SolrServerException, IOException;
	
	
	/**
	 *商圈查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupTrade(int pageSize, ErShoufangQueryVo queryVo)
			throws SolrServerException, IOException;
	
	/**
	 *小区查询
	 * @param pageSize分页大小
	 * @param queryVo查询条件
	 * @return
	 * @throws SolrServerException
	 * @throws IOException
	 */
	PageBean getGroupDic(int pageSize, ErShoufangQueryVo queryVo)
			throws SolrServerException, IOException;

	List<AreaLatLngGroupVo> getGroupTrade(ErShoufangQueryVo queryVo) throws SolrServerException, IOException;

	List<AreaLatLngGroupVo> getGroupDic(ErShoufangQueryVo queryVo) throws SolrServerException, IOException;
	
}






































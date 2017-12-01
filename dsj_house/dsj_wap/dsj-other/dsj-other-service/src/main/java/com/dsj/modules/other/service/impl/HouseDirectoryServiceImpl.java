package com.dsj.modules.other.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.modules.other.dao.HouseDirectoryDao;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.other.vo.HouseDirectoryVo;
import com.dsj.modules.solr.service.CommonIndexService;
import com.dsj.modules.solr.vo.CommonIndexFiled;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-16 08:34:03.
 * @版本: 1.0 .
 */
@Service
public class HouseDirectoryServiceImpl  extends BaseServiceImpl<HouseDirectoryDao,HouseDirectoryPo> implements HouseDirectoryService {
	@Autowired
	CommonIndexService commonIndexService;
	
	@Autowired
	AreaService areaService;
	
	@Autowired
	TradeAreaService tradeAreaService;
	
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPageList");
	}

	@Override
	public long saveDirectory(HouseDirectoryVo vo) throws Exception {
		HouseDirectoryPo po = new HouseDirectoryPo();
		MyBeanUtils.copyBean2Bean(po, vo);
		//经纬度
		String[] coordinate = vo.getCoordinate().split(",");
		po.setAccuracy(coordinate[1]);
		po.setDimension(coordinate[0]);
		po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		return dao.insertDynamic(po);
	}

	@Override
	public void updateDirectory(HouseDirectoryVo vo) throws Exception {
		HouseDirectoryPo po = new HouseDirectoryPo();
		MyBeanUtils.copyBean2Bean(po, vo);
		//经纬度
		String[] coordinate = vo.getCoordinate().split(",");
		po.setAccuracy(coordinate[1]);
		po.setDimension(coordinate[0]);
		dao.updateDynamic(po);
	}

	@Override
	public void updateDeleteFlag(String[] ids, Integer deleteFlag) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("deleteFlag", deleteFlag);
		dao.updateDeleteFlag(map);
	}

	@Override
	public List<HouseDirectoryPo> getByNamePreMatchding(String name) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", name);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.getByNamePreMatchding(map);
	}

	@Override
	public List<HouseDirectoryPo> getByNameOldHouse(String name) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("name", name);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.getByNameOldHouse(map);
	}

	@Override
	public void saveHouseDicSolr() {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<HouseDirectoryPo> lists=listBy(paramMap);
		List<CommonIndexFiled> fieldsList=new ArrayList<CommonIndexFiled>();
		for(HouseDirectoryPo po:lists){
			CommonIndexFiled filed=new CommonIndexFiled();
			filed.setId(BusinessConst.SOLR_DIC+po.getId());
			filed.setName(po.getSprayName());
			filed.setFullPinyin(po.getSpellName());
			filed.setType(1);
			filed.setJianPin(po.getSpellHead());
			fieldsList.add(filed);
		}
		try {
			commonIndexService.addItemIndexs(fieldsList);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveHouseAreaSolr() {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("parentId", BusinessConst.BEIJING_AREA_CODE);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> areaFirstList = areaService.listBy(paramMap);
		List<CommonIndexFiled> fieldsList=new ArrayList<CommonIndexFiled>();
		for(AreaPo po:areaFirstList){
			CommonIndexFiled filed=new CommonIndexFiled();
			filed.setId(BusinessConst.SOLR_AREA+po.getId());
			filed.setName(po.getName());
			filed.setFullPinyin(po.getEnglishName());
			filed.setType(3);
			filed.setJianPin(po.getLikePinyin());
			fieldsList.add(filed);
		}
		try {
			commonIndexService.addItemIndexs(fieldsList);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void saveHouseTardeSolr() {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<TradeAreaPo> tradeAreaList = tradeAreaService.listBy(paramMap);
		List<CommonIndexFiled> fieldsList=new ArrayList<CommonIndexFiled>();
		for(TradeAreaPo po:tradeAreaList){
			CommonIndexFiled filed=new CommonIndexFiled();
			filed.setId(BusinessConst.SOLR_TRADE+po.getId());
			filed.setName(po.getName());
			filed.setFullPinyin(po.getEnglishName());
			filed.setType(3);
			filed.setJianPin(po.getLikePinyin());
			fieldsList.add(filed);
		}
		try {
			commonIndexService.addItemIndexs(fieldsList);
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Long saveByHouseId(HouseDirectoryPo houseDirectory) {
		return dao.insertDynamic(houseDirectory);
	}

	@Override
	public List<HouseDirectoryPo> listByLimit(Map<String, Object> map) {
		return dao.listByLimit(map);
	}
	
}
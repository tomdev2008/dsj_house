package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.dao.NewHouseDirectorySortDao;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseDirectorySortPo;
import com.dsj.modules.newhouse.service.NewHouseDirectorySortService;
import com.mysql.fabric.xmlrpc.base.Data;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Service
public class NewHouseDirectorySortServiceImpl  extends BaseServiceImpl<NewHouseDirectorySortDao,NewHouseDirectorySortPo> implements NewHouseDirectorySortService {

	@Override
	public List<NewHouseDirectorySortPo> selectList() {
		return dao.selectList();
	}

	@Override
	public NewHouseDirectorySortPo getSortDirectory(Long sortId) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("sortVal", sortId);
		return dao.selectOne(paramMap);
	}

	@Override
	public void addNewHouseSortDirectory(NewHouseDirectorySortPo directoryPo) {
         dao.insertDynamic(directoryPo);		
	}

	@Override
	public int updateNewHouseSortDirectory(NewHouseDirectoryAuthPo directoryAuth, Long sortId, long createPerson) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("createTime", new Data());
		paramMap.put("loupanId", directoryAuth.getId());
		paramMap.put("updateTime", directoryAuth.getUpdateTime());
		paramMap.put("updatePerson", createPerson);
		paramMap.put("sortVal", sortId);
		return dao.updateNewHouseSort(paramMap);
	}

	@Override
	public NewHouseDirectorySortPo getSortOne(Long id) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("loupanId", id);
		return dao.selectOne(paramMap);
	}

	@Override
	public int updateSortDirectory(Long sort) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("sortVal", sort);
		return dao.deleteSort(paramMap);
	}
	
}
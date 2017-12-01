package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthHistoryService;
import com.dsj.modules.newhouse.dao.NewHouseTypeAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Service
public class NewHouseTypeAuthHistoryServiceImpl  extends BaseServiceImpl<NewHouseTypeAuthHistoryDao,NewHouseTypeAuthHistoryPo> implements NewHouseTypeAuthHistoryService {

	@Override
	public List<NewHouseTypeAuthPo> findHouseTypeList(int room, Long newHouseId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("room", room);
		paramMap.put("dicId", newHouseId);
		return dao.selectHouseTypeList(paramMap);
	}

	@Override
	public long findHouseTypeCount(int room, Long newHouseId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("room", room);
		paramMap.put("dicId", newHouseId);
		return dao.selectHouseTypeCount(paramMap);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		dao.saveList(map);
	}

}
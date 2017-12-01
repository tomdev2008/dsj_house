package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.newhouse.service.NewHouseSubwayAuthHistoryService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.newhouse.dao.NewHouseSubwayAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-24 16:36:40.
 * @版本: 1.0 .
 */
@Service
public class NewHouseSubwayAuthHistoryServiceImpl  extends BaseServiceImpl<NewHouseSubwayAuthHistoryDao,NewHouseSubwayAuthHistoryPo> implements NewHouseSubwayAuthHistoryService {

	@Autowired
	private SubwayService subwayService;
	
	@Override
	public void saveList(HashMap<String, Object> map) {
		dao.saveList(map);
	}

	@Override
	public List<NewHouseSubwayAuthPo> listNewBy(HashMap<String, Object> map) {
		return dao.listNewBy(map);
	}
	
}
package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.service.NewHouseOpenHandTimeAuthHistoryService;
import com.dsj.modules.newhouse.dao.NewHouseOpenHandTimeAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Service
public class NewHouseOpenHandTimeAuthHistoryServiceImpl  extends BaseServiceImpl<NewHouseOpenHandTimeAuthHistoryDao,NewHouseOpenHandTimeAuthHistoryPo> implements NewHouseOpenHandTimeAuthHistoryService {

	@Override
	public List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map) {
		return dao.listNewBy(map);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		dao.saveList(map);
	}
	
}
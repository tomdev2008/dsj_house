package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.service.NewHouseWyMsgAuthHistoryService;
import com.dsj.modules.newhouse.dao.NewHouseWyMsgAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Service
public class NewHouseWyMsgAuthHistoryServiceImpl  extends BaseServiceImpl<NewHouseWyMsgAuthHistoryDao,NewHouseWyMsgAuthHistoryPo> implements NewHouseWyMsgAuthHistoryService {

	@Override
	public List<NewHouseWyMsgAuthPo> listNewBy(HashMap<String, Object> map) {
		return dao.listNewBy(map);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		dao.saveList(map);
	}
	
}
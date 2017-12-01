package com.dsj.modules.rent.service.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.rent.service.RentHouseEntrustService;
import com.dsj.modules.rent.dao.RentHouseEntrustDao;
import com.dsj.modules.rent.po.RentHouseEntrustPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
@Service
public class RentHouseEntrustServiceImpl  extends BaseServiceImpl<RentHouseEntrustDao,RentHouseEntrustPo> implements RentHouseEntrustService {

	@Override
	public void updateRentHouseEntrust(String[] ids, int status) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("status", status);
		dao.updateRentHouseEntrust(map);
	}
	
}
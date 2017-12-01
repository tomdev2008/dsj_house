package com.dsj.modules.rent.service.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.rent.service.RentHouseRequireService;
import com.dsj.modules.rent.dao.RentHouseRequireDao;
import com.dsj.modules.rent.po.RentHouseRequirePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-07 17:59:45.
 * @版本: 1.0 .
 */
@Service
public class RentHouseRequireServiceImpl  extends BaseServiceImpl<RentHouseRequireDao,RentHouseRequirePo> implements RentHouseRequireService {

	@Override
	public void updateRentHouseRequire(String[] ids, int status) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("status", status);
		dao.updateRentHouseRequire(map);
	}
	
}
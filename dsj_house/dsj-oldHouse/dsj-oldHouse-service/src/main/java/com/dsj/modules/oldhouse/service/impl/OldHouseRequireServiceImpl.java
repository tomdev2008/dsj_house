package com.dsj.modules.oldhouse.service.impl;

import java.util.HashMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldhouse.service.OldHouseRequireService;
import com.dsj.modules.oldhouse.dao.OldHouseRequireDao;
import com.dsj.modules.oldhouse.po.OldHouseRequirePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 17:13:07.
 * @版本: 1.0 .
 */
@Service
public class OldHouseRequireServiceImpl  extends BaseServiceImpl<OldHouseRequireDao,OldHouseRequirePo> implements OldHouseRequireService {

	@Override
	public void updateOldHouseRequire(String[] ids, int status) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("status", status);
		dao.updateOldHouseRequire(map);
	}
	
}
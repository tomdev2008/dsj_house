package com.dsj.modules.oldHouseParser.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.service.ZydcOldhouseFlagService;
import com.dsj.modules.oldHouseParser.dao.ZydcOldhouseFlagDao;
import com.dsj.modules.oldHouseParser.po.ZydcOldhouseFlagPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class ZydcOldhouseFlagServiceImpl  extends BaseServiceImpl<ZydcOldhouseFlagDao,ZydcOldhouseFlagPo> implements ZydcOldhouseFlagService {

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}

	@Override
	public ZydcOldhouseFlagPo getLastPo() {
		return dao.getLastPo();
	}

	@Override
	public List<ZydcOldhouseFlagPo> getCount(String loupanUrl) {
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("loupanUrl",loupanUrl);
		return dao.getCount(map);
	}

	@Override
	public void insertZydc(ZydcOldhouseFlagPo zydc) {
		dao.insertZydc(zydc);
		
	}

	@Override
	public void deleteWawjAll() {
		dao.deleteWawjAll();
	}
	
}
package com.dsj.modules.oldHouseParser.service.impl;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.oldHouseParser.dao.MtfcOldhouseFlagDao;
import com.dsj.modules.oldHouseParser.po.MtfcOldhouseFlagPo;
import com.dsj.modules.oldHouseParser.service.MtfcOldhouseFlagService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 15:27:00.
 * @版本: 1.0 .
 */
@Service
public class MtfcOldhouseFlagServiceImpl  extends BaseServiceImpl<MtfcOldhouseFlagDao,MtfcOldhouseFlagPo> implements MtfcOldhouseFlagService {

	@Override
	public void deleteAll() {
		dao.deleteAll();
	}
	
}
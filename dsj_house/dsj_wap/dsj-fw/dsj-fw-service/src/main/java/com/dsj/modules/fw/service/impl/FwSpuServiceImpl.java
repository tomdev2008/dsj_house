package com.dsj.modules.fw.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.dao.FwSpuDao;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.fw.vo.FwSpuVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
@Service
public class FwSpuServiceImpl  extends BaseServiceImpl<FwSpuDao,FwSpuPo> implements FwSpuService {

	@Override
	public List<FwSpuVo> getFwSpuVoList(HashMap<String, Object> map) {
		return dao.getFwSpuVoList(map);
	}

	@Override
	public List<FwSpuVo> getSanFwSpuVoList(HashMap<String, Object> map) {
		return dao.getSanFwSpuVoList(map);
	}

	@Override
	public List<FwSpuPo> getThree() {
		
		return dao.getThree();
	}
	
	

	
}
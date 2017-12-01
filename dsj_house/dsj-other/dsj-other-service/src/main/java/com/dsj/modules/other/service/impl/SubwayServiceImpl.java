package com.dsj.modules.other.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.other.dao.SubwayDao;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.service.SubwayService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-13 10:33:23.
 * @版本: 1.0 .
 */
@Service
public class SubwayServiceImpl  extends BaseServiceImpl<SubwayDao,SubwayPo> implements SubwayService {

	@Override
	public List<SubwayPo> getByAllsubwayLine() {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pid", 0);
		return listBy(paramMap);
	}

	@Override
	public List<SubwayPo> getByPid(String pid) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("pid", pid);
		return listBy(paramMap);
	}

	@Override
	public List<SubwayPo> getSubWayLine(HashMap<String, Object> map) {
		
		return dao.getSubWayLine(map);
	}

	@Override
	public List<SubwayPo> getNewSubWayLine(HashMap<String, Object> map) {
		return dao.getNewSubWayLine(map);
	}
	
}
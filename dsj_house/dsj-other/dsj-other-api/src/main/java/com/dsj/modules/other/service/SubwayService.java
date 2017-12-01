package com.dsj.modules.other.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.SubwayPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-13 10:33:22.
 * @版本: 1.0 .
 */
public interface SubwayService extends BaseService<SubwayPo>{

	List<SubwayPo> getByAllsubwayLine();

	List<SubwayPo> getByPid(String pid);

	List<SubwayPo> getSubWayLine(HashMap<String, Object> map);

	List<SubwayPo> getNewSubWayLine(HashMap<String, Object> map);
	
}
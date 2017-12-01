package com.dsj.modules.fw.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.vo.FwSpuVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public interface FwSpuService extends BaseService<FwSpuPo>{

	List<FwSpuVo> getFwSpuVoList(HashMap<String, Object> map);

	List<FwSpuVo> getSanFwSpuVoList(HashMap<String, Object> map);

	List<FwSpuPo> getThree();

	
}
package com.dsj.modules.fw.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.vo.FwSkuVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public interface FwSkuService extends BaseService<FwSkuPo>{

	List<FwSkuVo> getFwSkuVoList(HashMap<String, Object> map);

	List<FwSkuVo> getSkuNameAndTypeId(Map<String, Object> hashMap);

	FwSkuVo getSkuVoBySpuId(Long spuId);

	FwSkuVo getFwSkuVoById(Long skuId);



	
}
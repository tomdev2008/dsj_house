package com.dsj.modules.fw.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.dao.FwSkuDao;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.vo.FwSkuVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
@Service
public class FwSkuServiceImpl  extends BaseServiceImpl<FwSkuDao,FwSkuPo> implements FwSkuService {

	@Override
	public List<FwSkuVo> getFwSkuVoList(HashMap<String, Object> map) {
		return dao.getFwSkuVoList(map);
	}

	@Override
	public List<FwSkuVo> getSkuNameAndTypeId(Map<String, Object> hashMap) {
		return dao.getSkuNameAndTypeId(hashMap);
	}

	@Override
	public FwSkuVo getSkuVoBySpuId(Long spuId) {
		return dao.getSkuVoBySpuId(spuId);
	}

	@Override
	public FwSkuVo getFwSkuVoById(Long skuId) {
		return dao.getFwSkuVoById(skuId);
	}



	
}
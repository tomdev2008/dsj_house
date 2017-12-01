package com.dsj.modules.fw.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.vo.FwSkuVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public interface FwSkuDao extends BaseDao<FwSkuPo> {

	List<FwSkuVo> getFwSkuVoList(HashMap<String, Object> map);

	List<FwSkuVo> getSkuNameAndTypeId(Map<String, Object> hashMap);

	FwSkuVo getSkuVoBySpuId(Long spuId);

	FwSkuVo getFwSkuVoById(Long skuId);

	
}
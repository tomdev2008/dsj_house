package com.dsj.modules.fw.dao;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.vo.FwSpuVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public interface FwSpuDao extends BaseDao<FwSpuPo> {

	List<FwSpuVo> getFwSpuVoList(HashMap<String, Object> map);

	List<FwSpuVo> getSanFwSpuVoList(HashMap<String, Object> map);

	List<FwSpuPo> getThree();
	
}
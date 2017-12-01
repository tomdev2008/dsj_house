package com.dsj.modules.comment.dao;

import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.comment.po.FwContentPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-09-04 13:55:25.
 * @版本: 1.0 .
 */
public interface FwContentDao extends BaseDao<FwContentPo> {

	void downByIds(Map<String, Object> map);
	
}
package com.dsj.modules.system.dao;


import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.MemberPo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 19:51:03.
 * @版本: 1.0 .
 */
public interface MemberDao extends BaseDao<MemberPo> {
	void updateRemoveBlack(Map<String,Object> map);
	void updateBlackMany(Map<String,Object> map);
}
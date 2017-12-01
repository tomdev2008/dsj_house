package com.dsj.modules.fw.dao;

import java.util.HashMap;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.FwuserCommentPo;
import com.dsj.modules.fw.vo.FwuserCommentVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
public interface FwuserCommentDao extends BaseDao<FwuserCommentPo> {

	FwuserCommentVo getCommentByDetailId(HashMap<String, Object> hashMap);

	FwuserCommentPo getByOrderId(Long orderId);

	FwuserCommentVo getPingLv(Long id);

	FwuserCommentVo getPingLvBySpu(Long spuId);


	
}
package com.dsj.modules.fw.dao;

import java.util.List;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.fw.po.FwuserComPo;
import com.dsj.modules.fw.vo.FwuserComVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-09-13 09:57:29.
 * @版本: 1.0 .
 */
public interface FwuserComDao extends BaseDao<FwuserComPo> {

	List<FwuserComVo> getCommCount(Long id);
	
}
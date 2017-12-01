package com.dsj.modules.fw.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.fw.dao.FwuserComDao;
import com.dsj.modules.fw.po.FwuserComPo;
import com.dsj.modules.fw.vo.FwuserComVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-09-13 09:57:29.
 * @版本: 1.0 .
 */
@Repository
public class FwuserComDaoImpl extends BaseDaoImpl<FwuserComPo> implements FwuserComDao{

	@Override
	public List<FwuserComVo> getCommCount(Long id) {
		return sessionTemplate.selectList("getCommCount", id);
	}
	
}
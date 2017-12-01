package com.dsj.modules.fw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.fw.service.FwuserComService;
import com.dsj.modules.fw.vo.FwuserComVo;
import com.dsj.modules.fw.dao.FwuserComDao;
import com.dsj.modules.fw.po.FwuserComPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-09-13 09:57:29.
 * @版本: 1.0 .
 */
@Service
public class FwuserComServiceImpl  extends BaseServiceImpl<FwuserComDao,FwuserComPo> implements FwuserComService {

	@Override
	public List<FwuserComVo> getCommCount(Long id) {
		return dao.getCommCount(id);
	}
	
}
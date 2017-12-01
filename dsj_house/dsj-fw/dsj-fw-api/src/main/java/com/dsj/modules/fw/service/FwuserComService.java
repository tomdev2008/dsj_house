package com.dsj.modules.fw.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.FwuserComPo;
import com.dsj.modules.fw.vo.FwuserComVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-09-13 09:57:29.
 * @版本: 1.0 .
 */
public interface FwuserComService extends BaseService<FwuserComPo>{

	List<FwuserComVo> getCommCount(Long id);


	
}
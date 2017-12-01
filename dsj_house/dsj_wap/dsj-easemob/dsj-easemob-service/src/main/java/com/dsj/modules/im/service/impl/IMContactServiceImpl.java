package com.dsj.modules.im.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.im.service.IMContactService;
import com.dsj.modules.im.dao.IMContactDao;
import com.dsj.modules.im.po.IMContactPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-02 16:35:02.
 * @版本: 1.0 .
 */
@Service("imContactServiceImpl")
public class IMContactServiceImpl  extends BaseServiceImpl<IMContactDao,IMContactPo> implements IMContactService {
	
	@Autowired
	private IMContactDao imContactDao;
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMContactServiceImpl.class);
	
	/**
	 * 根据条件查询[最多查rowCount条] listLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<IMContactPo> listLimitBy(Map<String, Object> paramMap) {
		return imContactDao.listLimitBy(paramMap);
	}
	
	/**
	 * 根据条件删除 listLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public void deleteLimitBy(Map<String, Object> paramMap) {
		imContactDao.deleteMoreLimitBy(paramMap);
	}
	
}
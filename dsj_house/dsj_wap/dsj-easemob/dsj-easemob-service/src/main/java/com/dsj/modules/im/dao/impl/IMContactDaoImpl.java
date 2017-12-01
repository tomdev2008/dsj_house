package com.dsj.modules.im.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.im.dao.IMContactDao;
import com.dsj.modules.im.po.IMContactPo;
import com.dsj.modules.im.service.impl.IMContactServiceImpl;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-02 16:35:02.
 * @版本: 1.0 .
 */
@Repository
public class IMContactDaoImpl extends BaseDaoImpl<IMContactPo> implements IMContactDao{
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMContactDaoImpl.class);
	
	/**
	 * 根据条件查询[最多查rowCount条] listLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<IMContactPo> listLimitBy(Map<String, Object> paramMap) {
		return sessionTemplate.selectList("listLimitBy", paramMap);
	}
	
	/**
	 * 根据条件删除  deleteMoreLimitBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public void deleteMoreLimitBy(Map<String, Object> paramMap) {
		sessionTemplate.delete("deleteMoreLimitBy", paramMap);
	}
	
}
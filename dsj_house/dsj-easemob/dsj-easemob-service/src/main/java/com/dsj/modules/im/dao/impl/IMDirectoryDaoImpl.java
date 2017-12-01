package com.dsj.modules.im.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.im.dao.IMDirectoryDao;
import com.dsj.modules.im.po.IMDirectoryPo;
import com.dsj.modules.im.vo.IMDirectoryVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-07-20 18:17:27.
 * @版本: 1.0 .
 */
@Repository
public class IMDirectoryDaoImpl extends BaseDaoImpl<IMDirectoryPo> implements IMDirectoryDao{
	
	/**
	 * 根据ID查找对象.
	 * 
	 * @param id
	 * @return IMDirectoryPo .
	 */
	public IMDirectoryVo getVoById(long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}
	
	/**
	 * 根据条件查询 listVoBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	public List<IMDirectoryVo> listVoBy(Map<String, Object> paramMap) {
		return sessionTemplate.selectList(getStatement("listVoBy"), paramMap);
	}
	
	/**
	 * 获得绑定经纪人数量.
	 * 
	 * @return
	 */
	public Long getIMDirectoryCount(Map<String, Object> paramMap) {
		return sessionTemplate
				.selectOne(getStatement(SQL_LIST_PAGE_COUNT), paramMap);
	}
	
}
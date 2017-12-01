package com.dsj.modules.im.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.im.po.IMDirectoryPo;
import com.dsj.modules.im.vo.IMDirectoryVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: wangjl.
 * @创建时间: 2017-07-20 18:17:27.
 * @版本: 1.0 .
 */
public interface IMDirectoryDao extends BaseDao<IMDirectoryPo> {
	
	/**
	 * 根据ID查找对象.
	 * 
	 * @param id
	 * @return IMDirectoryPo .
	 */
	public IMDirectoryVo getVoById(long id);
	
	/**
	 * 根据条件查询 listVoBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	public List<IMDirectoryVo> listVoBy(Map<String, Object> paramMap);
	
	/**
	 * 获得绑定经纪人数量.
	 * 
	 * @param paramMap
	 * @return
	 */
	public Long getIMDirectoryCount(Map<String, Object> paramMap);
	
}
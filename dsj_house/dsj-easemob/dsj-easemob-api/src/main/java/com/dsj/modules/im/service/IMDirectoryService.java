package com.dsj.modules.im.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.im.po.IMDirectoryPo;
import com.dsj.modules.im.vo.IMDirectoryVo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-07-20 18:17:27.
 * @版本: 1.0 .
 */
public interface IMDirectoryService extends BaseService<IMDirectoryPo>{

	/**
	 * 将楼盘维护人设为楼盘绑定经纪人.
	 * 
	 * @param houseId
	 * @param agentId
	 * @param personId
	 * @param isDuty
	 * @return IMDirectoryPo .
	 */
	public void addIMDirectory(Long houseId, Long agentId, int personId, 
			int isDuty);

	/*
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
	 * @param houseId
	 * @return
	 */
	public Long getIMDirectoryCount(Long houseId);
	
	
}
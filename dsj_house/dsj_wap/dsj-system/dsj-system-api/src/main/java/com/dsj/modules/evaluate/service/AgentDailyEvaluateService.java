package com.dsj.modules.evaluate.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.evaluate.po.AgentDailyEvaluatePo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:54:23.
 * @版本: 1.0 .
 */
public interface AgentDailyEvaluateService extends BaseService<AgentDailyEvaluatePo>{

	/**
	 * 计算经纪人日评价  addDailyEvaluate: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param paragraphNo 评价项目   服务态度: 79401  专业水平 ：79402
	 * @param itemMark 评价标识   1：好    2：中   3：差
	 * @param date 评价日期      eg：2017-09-01
	 * @return 
	 */
	public void addDailyEvaluate(Long agentCode, String agentName, 
			Long cityCode, String cityName, int paragraphNo, int itemMark, 
			String date, Integer personId);
	
}
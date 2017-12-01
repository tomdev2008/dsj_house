package com.dsj.modules.evaluate.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.evaluate.po.AgentDailyScorePo;
import com.dsj.modules.evaluate.po.AgentStandardPo;

/**
 *
 * @描述: Service接口.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:53:42.
 * @版本: 1.0 .
 */
public interface AgentDailyScoreService extends BaseService<AgentDailyScorePo>{

	/**
	 * 计算房源日得分  addDailyHouseScore: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param itemMark 评价标识   1：房源信息    2：业主姓名电话   3：实堪图片  4：钥匙
	 * @param date 评价日期      eg：2017-09-01
	 * @param personId 操作人
	 * @return 
	 */
	public void addDailyHouseScore(Long agentCode, String agentName, 
			Long cityCode, String cityName, int itemMark, String date, 
			Integer personId);
	
	/**
	 * 计算经纪人业务日得分  addDailyBusinessScore: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param agentName 经纪人姓名
	 * @param cityCode 城市编号
	 * @param cityName 城市名称
	 * @param paragraphNo 评价项目   带看量: 79302  成交数 ：79303 培训考试: 79304  活跃度 ：79305
	 * @param date 评价日期      eg：2017-09-01
	 * @param personId 操作人
	 * @return 
	 */
	public void addDailyBusinessScore(Long agentCode, String agentName, 
			Long cityCode, String cityName, int paragraphNo, String date, 
			Integer personId);
	
	/**
	 * 统计经纪人得分  AgentDailyScorePo: <br/>
	 * 
	 * @param agentCode 经纪人编号
	 * @param startDate 开始日期    eg：2017-09-01
	 * @param endDate 结束日期      eg：2017-09-01
	 * @return 
	 */
	public List<AgentDailyScorePo> listStatisticsBy(Long agentCode, 
			String startDate, String endDate);
	
}
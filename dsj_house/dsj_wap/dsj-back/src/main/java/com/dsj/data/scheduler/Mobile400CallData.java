package com.dsj.data.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsj.modules.mobile400.service.AgentMobileService;
@Component
public class Mobile400CallData {
	private final Logger LOGGER = LoggerFactory.getLogger(Mobile400CallData.class);
	
	@Autowired
	private AgentMobileService agentMobileService;
	
	@Scheduled(cron = "0 0 0 * * ?")
	public void main(){
		try {
			long id = agentMobileService.saveToLeadAgent();
			System.out.println(id);
		} catch (Exception e) {
			LOGGER.error("【定时任务】:经纪人来电数据导表失败",e);
		}
	}
	
	//初始化经纪人400数据统计
	/*INSERT INTO dsj_agent_mobile (
		agent_code,
		call_count,
		call_success,
		call_busy,
		call_not,
		stat_time
	) SELECT
		a.agent_code,
		IFNULL(a.callCount, 0) AS call_count,
		IFNULL(b.successCount, 0) AS call_success,
		IFNULL(c.busyCount, 0) AS call_busy,
		IFNULL(d.notCount, 0) AS call_not,
		a.stat_time AS stat_time
	FROM
		(
			SELECT
				count(1) AS callCount,
				agent_code,
				DATE_FORMAT(create_time, '%Y-%m-%d') AS stat_time
			FROM
				dsj_mobile_detail
			WHERE
				! ISNULL(agent_code)
			AND to_days(create_time) != to_days(now())
			GROUP BY
				agent_code,
				DATE_FORMAT(create_time, '%Y-%m-%d')
		) a
	LEFT JOIN (
		SELECT
			count(1) AS successCount,
			agent_code,
			DATE_FORMAT(create_time, '%Y-%m-%d') AS stat_time
		FROM
			dsj_mobile_detail
		WHERE
			! ISNULL(agent_code)
		AND callresult = 0
		AND to_days(create_time) != to_days(now())
		GROUP BY
			agent_code,
			DATE_FORMAT(create_time, '%Y-%m-%d')
	) b ON a.agent_code = b.agent_code
	AND a.stat_time = b.stat_time
	LEFT JOIN (
		SELECT
			count(1) AS busyCount,
			agent_code,
			DATE_FORMAT(create_time, '%Y-%m-%d') AS stat_time
		FROM
			dsj_mobile_detail
		WHERE
			! ISNULL(agent_code)
		AND (
			callresult = 1
			OR callresult = 2
			OR callresult = 3
			OR callresult = 1000
		)
		AND to_days(create_time) != to_days(now())
		GROUP BY
			agent_code,
			DATE_FORMAT(create_time, '%Y-%m-%d')
	) c ON a.agent_code = c.agent_code
	AND a.stat_time = c.stat_time
	LEFT JOIN (
		SELECT
			count(1) AS notCount,
			agent_code,
			DATE_FORMAT(create_time, '%Y-%m-%d') AS stat_time
		FROM
			dsj_mobile_detail
		WHERE
			! ISNULL(agent_code)
		AND (
			callresult = 11
			OR callresult = 201
			OR callresult = 1002
		)
		AND to_days(create_time) != to_days(now())
		GROUP BY
			agent_code,
			DATE_FORMAT(create_time, '%Y-%m-%d')
	) d ON a.agent_code = d.agent_code
	AND a.stat_time = d.stat_time
	ORDER BY
		a.stat_time ASC,
		a.agent_code ASC*/
}

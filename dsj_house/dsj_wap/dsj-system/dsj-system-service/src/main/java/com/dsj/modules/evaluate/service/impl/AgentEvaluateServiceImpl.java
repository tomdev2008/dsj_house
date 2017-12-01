package com.dsj.modules.evaluate.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.evaluate.service.AgentEvaluateService;
import com.dsj.modules.evaluate.dao.AgentEvaluateDao;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;

/**
 *
 * @鎻忚堪: Service鎺ュ彛瀹炵幇绫�.
 * @浣滆��: wangjl.
 * @鍒涘缓鏃堕棿: 2017-08-17 14:31:17.
 * @鐗堟湰: 1.0 .
 */
@Service
public class AgentEvaluateServiceImpl  extends BaseServiceImpl<AgentEvaluateDao,AgentEvaluatePo> implements AgentEvaluateService {
	
	/**
	 * 鍒嗛〉鏉′欢鏌ヨ .
	 * 
	 * @param pageParam
	 *            鍒嗛〉鍙傛暟.
	 * @param paramMap
	 *            涓氬姟鏉′欢鏌ヨ鍙傛暟.
	 * @return
	 */
	@Override
	public PageBean listPageByParams(PageParam pageParam,
			Map<String, Object> paramMap) {
		return dao.listPageByParams(pageParam, paramMap);
	}

	@Override
	public Map<String, Object> getRankAndScore(int agentId) {
		return dao.getRankAndScore(agentId);
	}
	
}
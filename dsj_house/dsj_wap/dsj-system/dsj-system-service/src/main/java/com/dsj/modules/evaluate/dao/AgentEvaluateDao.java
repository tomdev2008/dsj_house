package com.dsj.modules.evaluate.dao;

import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;

/**
 *
 * @鎻忚堪: DAO鏁版嵁璁块棶灞傛帴鍙�.
 * @浣滆��: wangjl.
 * @鍒涘缓鏃堕棿: 2017-08-17 14:31:17.
 * @鐗堟湰: 1.0 .
 */
public interface AgentEvaluateDao extends BaseDao<AgentEvaluatePo> {
	
	/**
	 * 鍒嗛〉鏉′欢鏌ヨ .
	 * 
	 * @param pageParam
	 *            鍒嗛〉鍙傛暟.
	 * @param paramMap
	 *            涓氬姟鏉′欢鏌ヨ鍙傛暟.
	 * @return
	 */
	public PageBean listPageByParams(PageParam pageParam, 
			Map<String, Object> paramMap);
	Map<String,Object> getRankAndScore(int agentId);
}
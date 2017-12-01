package com.dsj.modules.evaluate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.evaluate.dao.AgentEvaluateDao;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;

/**
 *
 * @鎻忚堪: DAO鏁版嵁璁块棶灞傛帴鍙ｅ疄鐜扮被.
 * @浣滆��: wangjl.
 * @鍒涘缓鏃堕棿: 2017-08-17 14:31:17.
 * @鐗堟湰: 1.0 .
 */
@Repository
public class AgentEvaluateDaoImpl extends BaseDaoImpl<AgentEvaluatePo> implements AgentEvaluateDao{
	
	/**
	 * 鍒嗛〉鏉′欢鏌ヨ .
	 * 
	 * @param pageParam
	 *            鍒嗛〉鍙傛暟.
	 * @param paramMap
	 *            涓氬姟鏉′欢鏌ヨ鍙傛暟.
	 * @return pageBean .
	 */
	public PageBean listPageByParams(PageParam pageParam, 
			Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 鏍规嵁椤甸潰浼犳潵鐨勫垎椤靛弬鏁版瀯閫燬QL鍒嗛〉鍙傛暟
		paramMap.put("pageFirst", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("pageSize", pageParam.getNumPerPage());
		paramMap.put("startRowNum", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("endRowNum", pageParam.getPageNum() * pageParam.getNumPerPage());
		// 缁熻鎬昏褰曟暟
		Long count = sessionTemplate.selectOne(getStatement("listPageCountByParams"), paramMap);
		// 鑾峰彇鍒嗛〉鏁版嵁闆�
		List<Object> list = sessionTemplate.selectList(getStatement("listPageByParams"), paramMap);
		// 鏋勯�犲垎椤靛璞�
		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
	}

	@Override
	public Map<String, Object> getRankAndScore(int agentId) {
		return sessionTemplate.selectOne(getStatement("getRankAndScore"), agentId);
	}
	
}
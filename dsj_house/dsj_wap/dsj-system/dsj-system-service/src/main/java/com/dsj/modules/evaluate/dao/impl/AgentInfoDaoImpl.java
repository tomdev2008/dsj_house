package com.dsj.modules.evaluate.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.evaluate.dao.AgentInfoDao;
import com.dsj.modules.evaluate.po.AgentInfoPo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-08-17 14:52:42.
 * @版本: 1.0 .
 */
@Repository
public class AgentInfoDaoImpl extends BaseDaoImpl<AgentInfoPo> implements AgentInfoDao{
	
	/**
	 * 分页条件查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return pageBean .
	 */
	public PageBean listPageByParams(PageParam pageParam, 
			Map<String, Object> paramMap) {
		if (paramMap == null) {
			paramMap = new HashMap<String, Object>();
		}
		// 根据页面传来的分页参数构造SQL分页参数
		paramMap.put("pageFirst", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("pageSize", pageParam.getNumPerPage());
		paramMap.put("startRowNum", (pageParam.getPageNum() - 1) * pageParam.getNumPerPage());
		paramMap.put("endRowNum", pageParam.getPageNum() * pageParam.getNumPerPage());
		// 统计总记录数
		Long count = sessionTemplate.selectOne(getStatement("listPageCountByParams"), paramMap);
		// 获取分页数据集
		List<Object> list = sessionTemplate.selectList(getStatement("listPageByParams"), paramMap);
		// 构造分页对象
		return new PageBean(pageParam.getPageNum(), pageParam.getNumPerPage(), count.intValue(), list);
	}
	
}
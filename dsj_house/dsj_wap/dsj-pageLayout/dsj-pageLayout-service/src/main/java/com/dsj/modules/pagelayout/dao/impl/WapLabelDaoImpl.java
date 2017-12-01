package com.dsj.modules.pagelayout.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.pagelayout.dao.WapLabelDao;
import com.dsj.modules.pagelayout.po.WapLabelPo;
import com.dsj.modules.pagelayout.vo.LabelTypeVo;
import com.dsj.modules.pagelayout.vo.WapIndexPageListVo;
import com.dsj.modules.pagelayout.vo.WeightAndTypeVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-22 17:08:04.
 * @版本: 1.0 .
 */
@Repository
public class WapLabelDaoImpl extends BaseDaoImpl<WapLabelPo> implements WapLabelDao{

	@Override
	public List<String> getTypeGroup() {
		return sessionTemplate.selectList(getStatement("getTypeGroup"));
	}

	@Override
	public List<LabelTypeVo> getType() {
		return sessionTemplate.selectList(getStatement("getType"));
	}

	@Override
	public List<WeightAndTypeVo> getWeightAndTypeVo(List<Integer> list) {
		return sessionTemplate.selectList(getStatement("getWeightAndTypeVo"),list);
	}

	@Override
	public List<WapIndexPageListVo> getWapIndexPageList(Map<String, Object> map) {
		return sessionTemplate.selectList(getStatement("getWapIndexPageList"),map);
	}
	
}
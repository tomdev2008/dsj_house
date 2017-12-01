package com.dsj.modules.pagelayout.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.pagelayout.po.WapLabelPo;
import com.dsj.modules.pagelayout.vo.LabelTypeVo;
import com.dsj.modules.pagelayout.vo.WapIndexPageListVo;
import com.dsj.modules.pagelayout.vo.WeightAndTypeVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-22 17:08:04.
 * @版本: 1.0 .
 */
public interface WapLabelDao extends BaseDao<WapLabelPo> {
	List<String> getTypeGroup();
	
	List<LabelTypeVo> getType();
	
	List<WeightAndTypeVo> getWeightAndTypeVo(List<Integer> list);
	
	List<WapIndexPageListVo> getWapIndexPageList(Map<String,Object> map);
}
package com.dsj.modules.pagelayout.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.dao.PcLabelDao;
import com.dsj.modules.pagelayout.enums.LabelTypeStatusEnum;
import com.dsj.modules.pagelayout.po.PcLabelPo;
import com.dsj.modules.pagelayout.service.PcLabelService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Service
public class PcLabelServiceImpl  extends BaseServiceImpl<PcLabelDao,PcLabelPo> implements PcLabelService {

	@Override
	public List<PcLabelPo> getLableList() {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("type", LabelTypeStatusEnum.NEWHOUSE.getValue());
		return dao.listBy(paramMap);
	}

	@Override
	public List<PcLabelPo> getLableOldList() {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("type", LabelTypeStatusEnum.OLDHOUSE.getValue());
		return dao.listBy(paramMap);
	}
	
}
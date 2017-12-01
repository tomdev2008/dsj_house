package com.dsj.modules.comment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.comment.dao.FwContentDao;
import com.dsj.modules.comment.enums.FwContentStatusEnum;
import com.dsj.modules.comment.po.FwContentPo;
import com.dsj.modules.comment.service.FwContentService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-09-04 13:55:25.
 * @版本: 1.0 .
 */
@Service
public class FwContentServiceImpl  extends BaseServiceImpl<FwContentDao,FwContentPo> implements FwContentService {

	@Override
	public void updateMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			Integer upDownLine = FwContentStatusEnum.XIA_XIAN.getValue();
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("upDownLine", upDownLine);
			dao.downByIds(map);
		}
	}

	
}
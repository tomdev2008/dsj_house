package com.dsj.modules.oldhouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.oldhouse.service.OldHouseEntrustService;
import com.dsj.modules.oldhouse.dao.OldHouseEntrustDao;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 17:13:06.
 * @版本: 1.0 .
 */
@Service
public class OldHouseEntrustServiceImpl  extends BaseServiceImpl<OldHouseEntrustDao,OldHouseEntrustPo> implements OldHouseEntrustService {

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public void updateOldHouseEntrust(String[] ids, int status) {
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("ids", ids);
		map.put("status", status);
		dao.updateOldHouseEntrust(map);
	}
	
}
package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHouseTypeAuthHistoryService extends BaseService<NewHouseTypeAuthHistoryPo>{

	List<NewHouseTypeAuthPo> findHouseTypeList(int i, Long newHouseId);

	long findHouseTypeCount(int i, Long newHouseId);

	void saveList(HashMap<String, Object> map);
	
}
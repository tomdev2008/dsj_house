package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-24 16:36:39.
 * @版本: 1.0 .
 */
public interface NewHouseSubwayAuthService extends BaseService<NewHouseSubwayAuthPo>{

	void saveList(long newHouseId, List<NewHouseSubwayAuthPo> subWayList);

	void deleteByNewHouseId(Long id);

	List<NewHouseSubwayAuthPo> listNewBy(HashMap<String, Object> map);

	void deleteByEditYesByNewHouseId(Long id, Long id2);


	
}
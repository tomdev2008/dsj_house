package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseOpenHandTimeAuthService extends BaseService<NewHouseOpenHandTimeAuthPo>{

	void saveList(long newHouseId, List<NewHouseOpenHandTimeAuthPo> openHandTimelist);

	void deleteByNewHouseId(Long id);

	void deleteByEditYesByNewHouseId(Long id, Long id2);

	NewHouseOpenHandTimeAuthPo getNewBy(HashMap<String, Object> map);

	List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map);

	NewHouseOpenHandTimeAuthPo getNewBy2(HashMap<String, Object> map);

	
}
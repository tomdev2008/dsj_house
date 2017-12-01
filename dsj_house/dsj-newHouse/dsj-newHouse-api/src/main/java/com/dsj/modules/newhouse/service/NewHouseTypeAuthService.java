package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseTypeCountVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseTypeAuthService extends BaseService<NewHouseTypeAuthPo>{

	void saveLayoutAdd(NewHouseTypeAuthPo po);

	List<NewHouseTypeAuthPo> findHouseTypeList(Integer room, Long newHouseId);

	long findHouseTypeCount(int room, Long newHouseId);

	NewHouseTypeAuthPo findOneNewHouse(long id);

	void updateLayoutAdd(NewHouseTypeAuthPo po, Long houseId);

	void deleteByEditYesByNewHouseId(Long id, Long id2);

	void updateDeleteFlagNewHouses(List<Integer> ids, Integer value);

	List<NewHouseTypeAuthPo> getNewHouseType(int agentId);

	String getNewHouseTypeByHouseId(Long id);

	List<NewHouseTypeCountVo> getHouseTypeCount(HashMap<String, Object> map);

	HashMap<String, Object> getNewHouseTypeListAndCountById(Long id);


	List<NewHouseTypeAuthPo> getByHouseId(Long id);

	void deleteByNewHouseId(Long id);



	
}
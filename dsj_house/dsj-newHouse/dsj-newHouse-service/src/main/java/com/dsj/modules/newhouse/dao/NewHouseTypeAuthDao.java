package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseTypeCountVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseTypeAuthDao extends BaseDao<NewHouseTypeAuthPo> {

	List<NewHouseTypeAuthPo> selectHouseTypeList(Map<String, Object> paramMap);

	long selectHouseTypeCount(Map<String, Object> paramMap);

	void deleteByNewHouseId(Long yesId);

	void updateNewHouseId(HashMap<String, Object> map);

	void updateDeleteFlagNewHouseTypes(HashMap<String, Object> hashMap);

	List<NewHouseTypeAuthPo> getNewHouseType(Map<String, Object> paramMap);

	String getNewHouseTypeByHouseId(Long id);

	List<NewHouseTypeCountVo> getHouseTypeCount(HashMap<String, Object> map);

	List<NewHouseTypeCountVo> getNewHouseTypeListAndCountById(Long id);

	void updateHouseType(NewHouseTypeAuthPo po);

	
}
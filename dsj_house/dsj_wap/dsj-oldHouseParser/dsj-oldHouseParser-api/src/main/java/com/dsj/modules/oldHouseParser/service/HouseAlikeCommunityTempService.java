package com.dsj.modules.oldHouseParser.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityTempPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:21.
 * @版本: 1.0 .
 */
public interface HouseAlikeCommunityTempService extends BaseService<HouseAlikeCommunityTempPo>{
    //所有临时表的小区
	List<HouseAlikeCommunityTempPo> selectList();

	HouseAlikeCommunityTempPo selectIdCommunit(long id);

	List<HouseAlikeCommunityTempPo> selectListCommunity(String name);



	
}
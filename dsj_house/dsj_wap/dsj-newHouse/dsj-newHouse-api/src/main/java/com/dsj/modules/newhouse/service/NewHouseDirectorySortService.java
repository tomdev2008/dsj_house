package com.dsj.modules.newhouse.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseDirectorySortPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseDirectorySortService extends BaseService<NewHouseDirectorySortPo>{

	List<NewHouseDirectorySortPo> selectList();

	NewHouseDirectorySortPo getSortDirectory(Long sortId);

	void addNewHouseSortDirectory(NewHouseDirectorySortPo directoryPo);

	int updateNewHouseSortDirectory(NewHouseDirectoryAuthPo directoryAuth, Long sortId, long createPerson);

	NewHouseDirectorySortPo getSortOne(Long id);

	int updateSortDirectory(Long sort);


	
}
package com.dsj.modules.other.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.vo.HouseDirectoryVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-16 08:34:03.
 * @版本: 1.0 .
 */
public interface HouseDirectoryService extends BaseService<HouseDirectoryPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	long saveDirectory(HouseDirectoryVo vo) throws Exception;

	void updateDirectory(HouseDirectoryVo vo) throws Exception;

	void updateDeleteFlag(String[] ids, Integer value);
	
	/**
	 * 前置匹配楼盘字典
	 * @param name
	 * @return
	 */
	List<HouseDirectoryPo> getByNamePreMatchding(String name);

	List<HouseDirectoryPo> getByNameOldHouse(String name);

	//List<HouseDirectoryPo> getByNameOldHouse(String name);
	void saveHouseDicSolr();

	void saveHouseAreaSolr();

	void saveHouseTardeSolr();

	Long saveByHouseId(HouseDirectoryPo houseDirectory);

	List<HouseDirectoryPo> listByLimit(Map<String, Object> map);
}
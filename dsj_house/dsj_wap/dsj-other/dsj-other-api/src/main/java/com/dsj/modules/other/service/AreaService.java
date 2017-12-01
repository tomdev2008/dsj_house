package com.dsj.modules.other.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.other.po.AreaPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 15:25:36.
 * @版本: 1.0 .
 */
public interface AreaService extends BaseService<AreaPo>{
	String findNameByAreaCode(String areaCode);

	AreaPo getMaxIDArea(HashMap<String, Object> map);

	List<AreaPo> listParent(HashMap<String, Object> map);
    /**
     * 查询所有的区域表数据
     * @return
     */
	List<AreaPo> getAreaList();

	void saveCrawlerArea();
	
	void saveCrawlerSubway();

	List<AreaPo> getRrareaList();

	void updateTrea(Map<String, Object> map);

	List<AreaPo> getAreaList(Map<String, Object> map);
}
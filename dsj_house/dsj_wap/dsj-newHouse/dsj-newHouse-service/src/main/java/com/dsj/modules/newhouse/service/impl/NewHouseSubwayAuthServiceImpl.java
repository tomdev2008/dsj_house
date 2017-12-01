package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.newhouse.service.NewHouseSubwayAuthService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.newhouse.dao.NewHouseSubwayAuthDao;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-24 16:36:39.
 * @版本: 1.0 .
 */
@Service
public class NewHouseSubwayAuthServiceImpl  extends BaseServiceImpl<NewHouseSubwayAuthDao,NewHouseSubwayAuthPo> implements NewHouseSubwayAuthService {

	@Autowired
	private SubwayService subwayService;
	
	@Override
	public void saveList(long newHouseId, List<NewHouseSubwayAuthPo> subWayList) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("subWayList", subWayList);
		map.put("newHouseId", newHouseId);
		dao.saveList(map);
	}

	@Override
	public void deleteByNewHouseId(Long id) {
		dao.deleteByNewHouseId(id);
	}

	@Override
	public List<NewHouseSubwayAuthPo> listNewBy(HashMap<String, Object> map) {
		List<NewHouseSubwayAuthPo> list = dao.listBy(map);
		for (NewHouseSubwayAuthPo newHouseSubwayAuthPo : list) {
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("pid", newHouseSubwayAuthPo.getLine());
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			newHouseSubwayAuthPo.setStationList(subwayService.listBy(hashMap));
		}
		return list;
	}

	public void deleteByEditYesByNewHouseId(Long yesId, Long noId) {
		dao.deleteByNewHouseId(yesId);
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("yesId", yesId);
		map.put("noId", noId);
		dao.updateNewHouseId(map);
	}
	
}
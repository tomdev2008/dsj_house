package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseTypeCountVo;
import com.dsj.modules.newhouse.dao.NewHouseTypeAuthDao;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Service
public class NewHouseTypeAuthServiceImpl  extends BaseServiceImpl<NewHouseTypeAuthDao,NewHouseTypeAuthPo> implements NewHouseTypeAuthService {
	@Override
	public void saveLayoutAdd(NewHouseTypeAuthPo po) {
		dao.insertDynamic(po);
	}

	@Override
	public NewHouseTypeAuthPo findOneNewHouse(long id) {
		return dao.getById(id);
	}

	@Override
	public List<NewHouseTypeAuthPo> findHouseTypeList(Integer room, Long newHouseId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("room", room);
		paramMap.put("dicId", newHouseId);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.selectHouseTypeList(paramMap);
	}

	@Override
	public long findHouseTypeCount(int room, Long newHouseId) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("room", room);
		paramMap.put("dicId", newHouseId);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		return dao.selectHouseTypeCount(paramMap);
	}
	@Override
	public void updateLayoutAdd(NewHouseTypeAuthPo po, Long houseId) {
		po.setId(houseId);
        dao.updateHouseType(po);
	}
	
	@Override
	public void deleteByEditYesByNewHouseId(Long yesId, Long noId) {
		dao.deleteByNewHouseId(yesId);
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("yesId", yesId);
		map.put("noId", noId);
		dao.updateNewHouseId(map);
	}

	@Override
	public void updateDeleteFlagNewHouses(List<Integer> ids, Integer value) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", ids);
		hashMap.put("deleteFlag", value);
		dao.updateDeleteFlagNewHouseTypes(hashMap);
	}

	@Override
	public List<NewHouseTypeAuthPo> getNewHouseType(int agentId) {
	Map<String, Object> paramMap=new HashMap<>();
	paramMap.put("agentId", agentId);
	return dao.getNewHouseType(paramMap);
	}

	@Override
	public String getNewHouseTypeByHouseId(Long id) {
		return dao.getNewHouseTypeByHouseId(id);
	}

	@Override
	public List<NewHouseTypeCountVo> getHouseTypeCount(HashMap<String, Object> map) {
		return dao.getHouseTypeCount(map);
	}

	@Override
	public HashMap<String, Object> getNewHouseTypeListAndCountById(Long id) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("houseId", id);
		List<NewHouseTypeCountVo> list = dao.getHouseTypeCount(map);
		
		map.put("houseTypeListAll", list);
		map.clear();
		map.put("dicId", id);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		int totalCount = 0;
		Boolean falg = false;
		for (int i = 0; i < list.size(); i++) {
			NewHouseTypeCountVo newHouseTypeCountVo = list.get(i);
			Integer room = newHouseTypeCountVo.getRoom();
			totalCount = totalCount + newHouseTypeCountVo.getCount();
			if(room<5){
				map.put("room", room);
				List<NewHouseTypeAuthPo> houseTypeList = dao.selectHouseTypeList(map);
				newHouseTypeCountVo.setHouseTypeList(houseTypeList);
			}else{
				falg=true;
				list.remove(i);
			}
			
		}
		if(falg){
			NewHouseTypeCountVo vo = new NewHouseTypeCountVo();
			map.put("room", 5);
			List<NewHouseTypeAuthPo> houseTypeList = dao.selectHouseTypeList(map);
			vo.setCount(houseTypeList.size());
			vo.setRoom(5);
			vo.setRoomName("五居室及以上");
			vo.setHouseTypeList(houseTypeList);
			list.add(vo);
		}
		
		map.clear();
		map.put("houseTypeList", list);
		map.put("totalCount", totalCount);
	
		return map;
	}

	@Override
	public List<NewHouseTypeAuthPo> getByHouseId(Long newHouseId) {
		HashMap<String, Object> paramMap = new HashMap<String,Object>();
		paramMap.put("dicId", newHouseId);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		return listBy(paramMap);
	}

	@Override
	public void deleteByNewHouseId(Long id) {
		dao.deleteByNewHouseId(id);
	}



	
}
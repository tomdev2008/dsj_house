package com.dsj.modules.other.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.utils.redis.constant.JedisConstant;
import com.dsj.common.utils.redis.one.RedisPoolUtil;
import com.dsj.modules.other.dao.GroupTypeDao;
import com.dsj.modules.other.po.GroupTypePo;
import com.dsj.modules.other.service.GroupTypeService;
import com.google.common.collect.Maps;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-29 13:46:52.
 * @版本: 1.0 .
 */
@Service
public class GroupTypeServiceImpl  extends BaseServiceImpl<GroupTypeDao,GroupTypePo> implements GroupTypeService {

	@Override
	public Map<String, Object> getGroupTypeMapByPid(String houseType) {
		Object obj= RedisPoolUtil.getObj(JedisConstant.DIC_TYPE);
		
		if(obj==null){
			Map<String, Object> resultmap=getGroupTypeByMysql();
			RedisPoolUtil.set(JedisConstant.DIC_TYPE, resultmap);
		}
		
		if(obj!=null ){
			Map<String,Object> map=(Map<String, Object>)obj;
			if(map!=null){
				Object okey= map.get(houseType);
				if(okey!=null){
					return (Map<String, Object>)map.get(houseType);
				}
			}
		}
		return null;
	}
	
	
	@Override
	public Map<String, Object> getHouseGroupType() {
		try{
			Object obj= RedisPoolUtil.getObj(JedisConstant.HOUSE_DIC_TYPE);
			if(obj!=null ){
				Map<String,Object> map=(Map<String, Object>)obj;
				return map;
			}else{
				Map<String, Object> resultmap = getFrontGroupType();

				RedisPoolUtil.set(JedisConstant.HOUSE_DIC_TYPE, resultmap);
				return resultmap;
			}
		}catch(Exception e){
			Map<String, Object> resultmap = getFrontGroupType();
			return resultmap;
		}
		
	}


	@Override
	public Map<String, Object> getFrontGroupType() {
		Map<String, Object> paramMap = Maps.newLinkedHashMap();
		paramMap.put("del", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("parentId", 0);
		List<GroupTypePo> pos = dao.listBy(paramMap);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		for (GroupTypePo po : pos) {//DICTRAIT
			if(po.getId()==Integer.parseInt(BusinessConst.RENVATION)||
					po.getId()==Integer.parseInt(BusinessConst.ORIENTATIONS)||
					po.getId()==Integer.parseInt(BusinessConst.ACHTYPE)||
					po.getId()==Integer.parseInt(BusinessConst.PAY_TYPE)||
					po.getId()==Integer.parseInt(BusinessConst.ACH_H_TYPE)||
					po.getId()==Integer.parseInt(BusinessConst.CERTIFICATE)||
					po.getId()==Integer.parseInt(BusinessConst.WY_TYPE)||
					po.getId()==Integer.parseInt(BusinessConst.FEATURES)||
							po.getId()==Integer.parseInt(BusinessConst.HEATING_MODE)||
							po.getId()==Integer.parseInt(BusinessConst.DICTRAIT)||
							po.getId()==Integer.parseInt(BusinessConst.HOUSE_TYPE)){
				paramMap.put("parentId", po.getId());
				List<GroupTypePo> pos1 = dao.listBy(paramMap);
				Map<String, Object> dataMap =Maps.newLinkedHashMap();
				for (GroupTypePo po1 : pos1) {
					dataMap.put(po1.getId().toString(), po1.getTypegroupname());
				}
				resultmap.put(po.getId().toString(), dataMap);
			}
		}
		return resultmap;
	}


	@Override
	public Map<String, Object> getDictrait() {
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", BusinessConst.DICTRAIT);
		map.put("count", 24);
		List<GroupTypePo> list = dao.getDictrait(map);
		Map<String, Object> resMap = new LinkedHashMap<>();
		for(GroupTypePo po : list){
			resMap.put(po.getId().toString(), po.getTypegroupname());
		}
		return resMap;
	}


	@Override
	public Map<String, Object> getGroupTypeBackMapByPid(String features) {
		Map<String, Object> map = new HashMap<>();
		map.put("parentId",features);
		map.put("del", DeleteStatusEnum.NDEL.getValue());
		List<GroupTypePo> list = dao.getGroupTypeBackMapByPid(map);
		Map<String, Object> resMap = new LinkedHashMap<>();
		for(GroupTypePo po : list){
			resMap.put(po.getId().toString(), po.getTypegroupname());
		}
		return resMap;
	}


	@Override
	public Map<String, Object> getGroupTypeByMysql() {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("del", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("parentId", 0);
		List<GroupTypePo> pos =listBy(paramMap);
		Map<String, Object> resultmap = new HashMap<String, Object>();
		for (GroupTypePo po : pos) {
			paramMap.put("parentId", po.getId());
			List<GroupTypePo> pos1 = listBy(paramMap);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			for (GroupTypePo po1 : pos1) {
				dataMap.put(po1.getId().toString(), po1.getTypegroupname());
			}
			resultmap.put(po.getId().toString(), dataMap);
		}
		return resultmap;
	}


	@Override
	public Long saveIdByPidAndOname(Integer pid, String name) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("del", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("parentId", pid);
		paramMap.put("typegroupname", name);
		GroupTypePo pos = getBy(paramMap);
		if (pos != null) {
			return pos.getId();
		}else {
			GroupTypePo po = new GroupTypePo();
			po.setParentId(pid.longValue());
			po.setTypegroupname(name);
			po.setSort(0);
			po.setDel(DeleteStatusEnum.NDEL.getValue());
			return saveDynamic(po);
		}
	}
}









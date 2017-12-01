package com.dsj.modules.comment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.vo.HouseNewsVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 13:36:52.
 * @版本: 1.0 .
 */
public interface HouseNewsDao extends BaseDao<HouseNewsPo> {
	void downByIds(Map<String,Object> map);
	void updateAuditMany(Map<String,Object> map);
	HouseNewsVo getVoById(long id);
	void updateRemoveStick(Map<String, Object> map);
	void updateAddStick(Map<String, Object> map);
	void updateDeleteFlag(Map<String, Object> map);
	HouseNewsVo getOneBy(HashMap<String, Object> map);
	void updateLineFlag(Map<String, Object> map);
	void updateDeleteByCreateUserIds(Map<String, Object> map);
	Long getHouseNewsCountBy(HashMap<String, Object> map1);
	List<HouseNewsVo> getRelatedNews(HashMap<String, Object> map);
}
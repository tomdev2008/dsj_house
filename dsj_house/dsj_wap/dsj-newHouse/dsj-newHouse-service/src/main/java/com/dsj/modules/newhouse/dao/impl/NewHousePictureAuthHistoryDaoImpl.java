package com.dsj.modules.newhouse.dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.newhouse.dao.NewHousePictureAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHousePictureAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Repository
public class NewHousePictureAuthHistoryDaoImpl extends BaseDaoImpl<NewHousePictureAuthHistoryPo> implements NewHousePictureAuthHistoryDao{

	@Override
	public List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("getListCount"), hashMap);
	}

	@Override
	public List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap) {
		return sessionTemplate.selectList(getStatement("listVoBy"), hashMap);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		 sessionTemplate.selectList(getStatement("saveList"), map);
	}
	
}
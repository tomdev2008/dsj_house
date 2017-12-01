package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.service.NewHousePictureAuthHistoryService;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;
import com.dsj.modules.newhouse.dao.NewHousePictureAuthHistoryDao;
import com.dsj.modules.newhouse.po.NewHousePictureAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Service
public class NewHousePictureAuthHistoryServiceImpl  extends BaseServiceImpl<NewHousePictureAuthHistoryDao,NewHousePictureAuthHistoryPo> implements NewHousePictureAuthHistoryService {

	@Override
	public List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap) {
		return dao.getListCount(hashMap);
	}

	@Override
	public List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap) {
		return dao.listVoBy(hashMap);
	}

	@Override
	public void saveList(HashMap<String, Object> map) {
		dao.saveList(map);
	}

	
}
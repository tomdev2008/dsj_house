package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHousePictureAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.vo.NewHousePictureAuthVo;
import com.dsj.modules.newhouse.vo.NewHousePictureCountVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHousePictureAuthHistoryService extends BaseService<NewHousePictureAuthHistoryPo>{

	List<NewHousePictureCountVo> getListCount(HashMap<String, Object> hashMap);

	List<NewHousePictureAuthVo> listVoBy(HashMap<String, Object> hashMap);

	void saveList(HashMap<String, Object> map);


	
}
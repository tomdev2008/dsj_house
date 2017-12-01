package com.dsj.modules.newhouse.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseWyMsgAuthService extends BaseService<NewHouseWyMsgAuthPo>{

	void saveList(long newHouseId, List<NewHouseWyMsgAuthPo> wyMsgList);

	void deleteByNewHouseId(Long id);

	void deleteByEditYesByNewHouseId(Long id, Long id2);

	Double getMinRefrencePrice(Map<String, Object> map);

	Double getMinTotalPrice(Map<String, Object> map);

	List<NewHouseRecommendVo> listRecommendNewHouseBysq(Map<String, Object> map);

	List<NewHouseRecommendVo> listRecommendNewHouseBysq2(Map<String, Object> map);

	List<NewHouseRecommendVo> listRecommendNewHouseBysq3(Map<String, Object> map);


	
}
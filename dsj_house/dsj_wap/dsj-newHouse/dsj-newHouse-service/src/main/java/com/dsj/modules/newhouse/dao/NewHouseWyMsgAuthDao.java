package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
public interface NewHouseWyMsgAuthDao extends BaseDao<NewHouseWyMsgAuthPo> {

	void deleteByNewHouseId(Long id);

	void updateNewHouseId(HashMap<String, Object> map);

	void saveList(HashMap<String, Object> map);

	Double getMinRefrencePrice(Map<String, Object> map);

	Double getMinTotalPrice(Map<String, Object> map);

	List<NewHouseRecommendVo> listRecommendNewHouseBysq(Map<String, Object> map);

	List<NewHouseRecommendVo> listRecommendNewHouseBysq2(Map<String, Object> map);

	List<NewHouseRecommendVo> listRecommendNewHouseBysq3(Map<String, Object> map);

}
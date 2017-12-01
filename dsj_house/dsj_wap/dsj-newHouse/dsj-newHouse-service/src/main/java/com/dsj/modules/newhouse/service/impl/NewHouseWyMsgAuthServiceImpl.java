package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.service.NewHouseWyMsgAuthService;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;
import com.dsj.modules.newhouse.dao.NewHouseWyMsgAuthDao;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Service
public class NewHouseWyMsgAuthServiceImpl  extends BaseServiceImpl<NewHouseWyMsgAuthDao,NewHouseWyMsgAuthPo> implements NewHouseWyMsgAuthService {

	/**
	 *
	 * @return 
	 * @描述: 批量添加
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void saveList(long newHouseId, List<NewHouseWyMsgAuthPo> wyMsgList) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("wyMsgList", wyMsgList);
		map.put("newHouseId", newHouseId);
		dao.saveList(map);
	}

	/**
	 *
	 * @return 
	 * @描述: 删除楼盘下所有物业信息
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void deleteByNewHouseId(Long id) {
		dao.deleteByNewHouseId(id);
	}

	/**
	 *
	 * @return 
	 * @描述: 未展示物业,审核通过后覆盖原相册展示物业
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void deleteByEditYesByNewHouseId(Long yesId, Long noId) {
		//删除原上架中的新房相关
		dao.deleteByNewHouseId(yesId);
		//把修改后的新房更新到原上架中的新房id
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("yesId", yesId);
		map.put("noId", noId);
		dao.updateNewHouseId(map);
	}

	/**
	 *
	 * @return 
	 * @描述: 最小价格
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public Double getMinRefrencePrice(Map<String, Object> map) {
		return dao.getMinRefrencePrice(map);
	}

	/**
	 *
	 * @return 
	 * @描述: 最小总价
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public Double getMinTotalPrice(Map<String, Object> map) {
		return dao.getMinTotalPrice(map);
	}

	@Override
	public List<NewHouseRecommendVo> listRecommendNewHouseBysq(Map<String, Object> map) {
		return dao.listRecommendNewHouseBysq(map);
	}

	@Override
	public List<NewHouseRecommendVo> listRecommendNewHouseBysq2(Map<String, Object> map) {
		return dao.listRecommendNewHouseBysq2(map);
	}

	@Override
	public List<NewHouseRecommendVo> listRecommendNewHouseBysq3(Map<String, Object> map) {
		return dao.listRecommendNewHouseBysq3(map);
	}
	
}
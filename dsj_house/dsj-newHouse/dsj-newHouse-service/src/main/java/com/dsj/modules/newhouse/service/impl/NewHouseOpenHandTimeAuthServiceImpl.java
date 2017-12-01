package com.dsj.modules.newhouse.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.newhouse.service.NewHouseOpenHandTimeAuthService;
import com.dsj.modules.newhouse.dao.NewHouseOpenHandTimeAuthDao;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 15:40:06.
 * @版本: 1.0 .
 */
@Service
public class NewHouseOpenHandTimeAuthServiceImpl  extends BaseServiceImpl<NewHouseOpenHandTimeAuthDao,NewHouseOpenHandTimeAuthPo> implements NewHouseOpenHandTimeAuthService {

	/**
	 *
	 * @return 
	 * @描述: 批量添加新房开盘/交房时间
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void saveList(long newHouseId, List<NewHouseOpenHandTimeAuthPo> openHandTimelist) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("newHouseId", newHouseId);
		map.put("openHandTimelist", openHandTimelist);
		dao.saveList(map);
	}

	/**
	 *
	 * @return 
	 * @描述: 删除楼盘下的所有开盘.交房时间
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
	 * @描述: 展示  未展示数据覆盖
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void deleteByEditYesByNewHouseId(Long yesId, Long noId) {
		dao.deleteByNewHouseId(yesId);
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("yesId", yesId);
		map.put("noId", noId);
		dao.updateNewHouseId(map);
	}

	@Override
	public NewHouseOpenHandTimeAuthPo getNewBy(HashMap<String, Object> map) {
		return dao.getNewBy(map);
	}

	@Override
	public List<NewHouseOpenHandTimeAuthPo> listNewBy(HashMap<String, Object> map) {
		return dao.listNewBy(map);
	}

	@Override
	public NewHouseOpenHandTimeAuthPo getNewBy2(HashMap<String, Object> map) {
		return dao.getNewBy2(map);
	}
	
}
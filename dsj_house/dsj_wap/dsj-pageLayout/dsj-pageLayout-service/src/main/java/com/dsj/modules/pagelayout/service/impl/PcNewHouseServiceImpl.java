package com.dsj.modules.pagelayout.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.service.PcNewHouseService;
import com.dsj.modules.pagelayout.vo.NewHouseLabelVo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHouseWyMsgAuthService;
import com.dsj.modules.pagelayout.dao.PcNewHouseDao;
import com.dsj.modules.pagelayout.po.PcNewHousePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
@Service
public class PcNewHouseServiceImpl  extends BaseServiceImpl<PcNewHouseDao,PcNewHousePo> implements PcNewHouseService {

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private NewHouseWyMsgAuthService newHouseWyMsgAuthService;
	@Override
	public List<NewHouseLabelVo> getLableNewHouseList() {
		List<NewHouseLabelVo> newHouseListPage = dao.getLableNewHouseList();
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		for (NewHouseLabelVo newHouseLabelVo : newHouseListPage) {
			hashMap.put("houseId", newHouseLabelVo.getNewHouseId());
			HashMap<String, String> priceMap = newHouseDirectoryAuthService.getPrice(newHouseWyMsgAuthService.listBy(hashMap), newHouseLabelVo.getReferencePriceMin(), newHouseLabelVo.getReferencePriceMan());
			String indexPrice = priceMap.get("indexPrice");
			String pricedw = priceMap.get("pricedw");
			String priceName = priceMap.get("priceName");
			if (StringUtils.isNotBlank(indexPrice)) {
				newHouseLabelVo.setIndexPrice((int) (Double.parseDouble(indexPrice)));
				newHouseLabelVo.setPricedw(pricedw);
				newHouseLabelVo.setPriceName(priceName);
			}
		}
		return newHouseListPage;
	}

	@Override
	public PcNewHousePo getNewHouse(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("id", id);
		return dao.getNewHouse(paramMap);
	}

	@Override
	public void updateNewHouse(PcNewHousePo pcNewHousePo) {

		dao.updateDynamic(pcNewHousePo);
	}

	@Override
	public void updateNewHousePage(PcNewHousePo pcNewhouse) {
		dao.updateNewHousePage(pcNewhouse);
	}

	@Override
	public List<NewHouseLabelVo> getLableNewHouseListPage() {
		List<NewHouseLabelVo> newHouseListPage = dao.getLableNewHouseListPage();
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		for (NewHouseLabelVo newHouseLabelVo : newHouseListPage) {
			hashMap.put("houseId", newHouseLabelVo.getNewHouseId());
			HashMap<String, String> priceMap = newHouseDirectoryAuthService.getPrice(newHouseWyMsgAuthService.listBy(hashMap), newHouseLabelVo.getReferencePriceMin(), newHouseLabelVo.getReferencePriceMan());
			String indexPrice = priceMap.get("indexPrice");
			String pricedw = priceMap.get("pricedw");
			String priceName = priceMap.get("priceName");
			if (StringUtils.isNotBlank(indexPrice)) {
				newHouseLabelVo.setIndexPrice((int) (Double.parseDouble(indexPrice)));
				newHouseLabelVo.setPricedw(pricedw);
				newHouseLabelVo.setPriceName(priceName);
			}
		}
		return newHouseListPage;
	}

	@Override
	public List<PcNewHousePo> getFindAll() {
		
		return dao.getFindAll();
	}

	
}
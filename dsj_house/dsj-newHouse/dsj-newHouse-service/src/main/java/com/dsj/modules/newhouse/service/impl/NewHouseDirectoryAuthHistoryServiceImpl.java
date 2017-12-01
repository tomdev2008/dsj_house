package com.dsj.modules.newhouse.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseOpenHandTimeAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseSubwayAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseSubwayAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseWyMsgAuthHistoryService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.dao.NewHouseDirectoryAuthHistoryDao;
import com.dsj.modules.newhouse.enums.NewHouseAuthStatusEnum;
import com.dsj.modules.newhouse.enums.NewHouseOpenHandTimeTypeEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
@Service
public class NewHouseDirectoryAuthHistoryServiceImpl
		extends BaseServiceImpl<NewHouseDirectoryAuthHistoryDao, NewHouseDirectoryAuthHistoryPo>
		implements NewHouseDirectoryAuthHistoryService {

	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseDirectoryAuthHistoryServiceImpl.class);
	
	@Autowired
	private NewHouseWyMsgAuthHistoryService newHouseWyMsgAuthHistoryService;

	@Autowired
	private NewHouseOpenHandTimeAuthHistoryService newHouseOpenHandTimeAuthHistoryService;

	@Autowired
	private NewHousePictureAuthHistoryService newHousePictureAuthHistoryService;

	@Autowired
	private NewHouseTypeAuthHistoryService newHouseTypeAuthHistoryService;

	@Autowired
	private NewHouseSubwayAuthHistoryService newHouseSubwayAuthHistoryService;

	@Override
	public void saveNewHouseAuthHistory(NewHouseDirectoryAuthPo directoryAuthPo, List<NewHouseWyMsgAuthPo> wyMsgList,
			List<NewHouseOpenHandTimeAuthPo> openHandTimeList, List<NewHouseTypeAuthPo> houseTypeList,
			List<NewHousePictureAuthPo> pictureList, List<NewHouseSubwayAuthPo> subwayList) throws Exception {

		NewHouseDirectoryAuthHistoryPo newHouseDirectoryAuthHistoryPo = new NewHouseDirectoryAuthHistoryPo();
		MyBeanUtils.copyBean2Bean(newHouseDirectoryAuthHistoryPo, directoryAuthPo);
		LOGGER.info("提交审核,历史表追加一条待审核数据!");
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("authStatus", NewHouseAuthStatusEnum.WAIT_AUTH.getValue());
		hashMap.put("code", newHouseDirectoryAuthHistoryPo.getCode());
		NewHouseDirectoryAuthHistoryPo po = dao.getBy(hashMap);
		if(null!=po){
			dao.deleteById(po.getId());
		}
		
		long newHouseAuthHistoryId = dao.insertDynamic(newHouseDirectoryAuthHistoryPo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("newHouseAuthHistoryId", newHouseAuthHistoryId);
		if(!wyMsgList.isEmpty()){
			map.put("wyMsgList", wyMsgList);
			newHouseWyMsgAuthHistoryService.saveList(map);
		}
		map.remove("wyMsgList");
		if(!openHandTimeList.isEmpty()){
			map.put("openHandTimeList", openHandTimeList);
			newHouseOpenHandTimeAuthHistoryService.saveList(map);
		}
		
		map.remove("openHandTimeList");
		if(!houseTypeList.isEmpty()){
			map.put("houseTypeList", houseTypeList);
			newHouseTypeAuthHistoryService.saveList(map);
		}
		
		map.remove("houseTypeList");
		if(!pictureList.isEmpty()){
			map.put("pictureList", pictureList);
			newHousePictureAuthHistoryService.saveList(map);
		}
		
		map.remove("pictureList");
		if(!subwayList.isEmpty()){
			map.put("subWayList", subwayList);
			newHouseSubwayAuthHistoryService.saveList(map);
		}
	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	// 回显楼盘信息
	@Override
	public NewHouseDirectoryAuthVo getVoById(Long id) {
		NewHouseDirectoryAuthVo vo = dao.getVoById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", vo.getId());
		vo.setWyMsgList(newHouseWyMsgAuthHistoryService.listNewBy(map));
		vo.setSubWayList(newHouseSubwayAuthHistoryService.listNewBy(map));
		String wyType = vo.getWyType();
		String[] wyStr = wyType.split(",");
		map.put("type", NewHouseOpenHandTimeTypeEnum.OPEN.getValue());
		ArrayList<List<NewHouseOpenHandTimeAuthPo>> list1 = new ArrayList<List<NewHouseOpenHandTimeAuthPo>>();
		for (String string : wyStr) {
			map.put("wyType", string);
			List<NewHouseOpenHandTimeAuthPo> timeList = newHouseOpenHandTimeAuthHistoryService.listNewBy(map);
			list1.add(timeList);
		}
		vo.setOpenTimeList(list1);

		map.put("type", NewHouseOpenHandTimeTypeEnum.HAND.getValue());
		ArrayList<List<NewHouseOpenHandTimeAuthPo>> list2 = new ArrayList<List<NewHouseOpenHandTimeAuthPo>>();
		for (String string : wyStr) {
			map.put("wyType", string);
			List<NewHouseOpenHandTimeAuthPo> timeList = newHouseOpenHandTimeAuthHistoryService.listNewBy(map);
			list2.add(timeList);
		}
		vo.setHandTimeList(list2);
		if (vo.getPhone() != null) {
			vo.setPhoneList(vo.getPhone().split(","));
		}
		if (StringUtils.isNotBlank(vo.getDiscount())) {
			vo.setDiscountList(vo.getDiscount().split(","));
		}
		if (StringUtils.isNotBlank(vo.getPrep())) {
			vo.setPrepList(vo.getPrep().split(","));
		}
		return vo;
	}

}
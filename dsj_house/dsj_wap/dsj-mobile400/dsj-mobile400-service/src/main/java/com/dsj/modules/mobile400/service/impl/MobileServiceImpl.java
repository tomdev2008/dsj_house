package com.dsj.modules.mobile400.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.exceptions.CommonException;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.URLUtils;
import com.dsj.common.utils.json.JsonMapper;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.modules.mobile400.dao.MobileDao;
import com.dsj.modules.mobile400.enums.MobileBindingStatusEnum;
import com.dsj.modules.mobile400.enums.MobileTypeEnum;
import com.dsj.modules.mobile400.po.MobileHistoryPo;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileHistoryService;
import com.dsj.modules.mobile400.service.MobileService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-17 13:41:36.
 * @版本: 1.0 .
 */
@Service
public class MobileServiceImpl extends BaseServiceImpl<MobileDao, MobilePo> implements MobileService {

	private final Logger LOGGER = LoggerFactory.getLogger(MobileServiceImpl.class);

	@Autowired
	private MobileHistoryService mobileHistoryService;

	@Override
	public void saveMobile(MobilePo po) {
		String mobile = dao.getMaxMobile();
		if (StringUtils.isNotBlank(mobile)) {
			int newMobile = Integer.parseInt(mobile);
			newMobile++;
			po.setMobile(newMobile + "");
		} else {
			po.setMobile("100");
		}
		dao.insertDynamic(po);
	}

	// 绑定
	@Override
	public boolean saveBindingMobile(MobilePo po) throws CommonException {
		String phone = po.getPhone();
		String[] phoneList = phone.split(",");
		String newPhone = "";
		for (String str : phoneList) {
			if (StringUtils.isNotBlank(str)) {
				newPhone += "," + str;
			}
		}
		po.setPhone(newPhone.substring(1));
		po.setLastBindingTime(new Date());
		po.setStatus(MobileBindingStatusEnum.YES.getValue());
		// 修改绑定状态
		dao.updateDynamic(po);
		// 添加历史记录
		addMobileBindingHistory(po);
		boolean result = AddWorkGroup(dao.getById(po.getId()));
		if (!result) {
			throw new CommonException("400 exception");
		}
		return result;
	}

	// 编辑
	@Override
	public void saveUpdateBindingMobile(MobilePo po) throws CommonException {
		String phone = po.getPhone();
		String[] phoneList = phone.split(",");
		String newPhone = "";
		for (String str : phoneList) {
			if (StringUtils.isNotBlank(str)) {
				newPhone += "," + str;
			}
		}
		po.setPhone(newPhone.substring(1));
		po.setLastBindingTime(new Date());
		dao.updateDynamic(po);
		// 修改绑定状态
		boolean result = EditWorkGroup(dao.getById(po.getId()));;
		if (!result) {
			throw new CommonException("400 exception");
		}
	}

	// 解绑
	@Override
	public boolean saveRemoveBindingMobile(Long id, Long updatePreson) throws CommonException {
		MobilePo mobilePo = dao.getById(id);
		// 修改绑定状态.删除绑定信息
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("id", id);
		hashMap.put("updatePreson", updatePreson);
		dao.updateRemoveBinding(hashMap);
		// 添加历史记录
		mobilePo.setStatus(MobileBindingStatusEnum.NO.getValue());
		addMobileBindingHistory(mobilePo);
		boolean result = true;
		if(mobilePo.getMobile().length()!=5){
			result = DelWorkGroupTwo(mobilePo);
		}else{
			result = DelWorkGroup(mobilePo.getMobile());
		}
		
		
		if (!result) {
			throw new CommonException("400 exception");
		}
		return result;
	}

	// 绑定历史
	public void addMobileBindingHistory(MobilePo po) {
		MobileHistoryPo mobileHistoryPo = new MobileHistoryPo();
		mobileHistoryPo.setStatus(po.getStatus());
		mobileHistoryPo.setMobile(po.getMobile());
		mobileHistoryPo.setMobileId(po.getId());
		mobileHistoryPo.setAgentId(po.getAgentId());
		mobileHistoryPo.setAgentName(po.getAgentName());
		mobileHistoryPo.setHouseId(po.getHouseId());
		mobileHistoryPo.setHouseName(po.getHouseName());
		mobileHistoryPo.setCreateTime(new Date());
		mobileHistoryPo.setPropertyId(po.getPropertyId());
		mobileHistoryPo.setPropertyName(po.getPropertyName());
		mobileHistoryService.saveDynamic(mobileHistoryPo);
	}

	/*
	 * 1.新建分机并添加接线员号电话 AddWorkGroup
	 */
	public boolean AddWorkGroup(MobilePo po) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			List<Object> list = new ArrayList<Object>();
			Map<String, Object> resultvalue = new HashMap<String, Object>();
			map.put("custid", ConfigUtils.instance.getMobileCustid());// 商户 ID必传
			if(po.getMobile().length()!=5){
				map.put("bigcode400", po.getMobile().substring(0, 12).replace("-", ""));// 400
				resultvalue.put("extcode", po.getMobile().substring(13, po.getMobile().length()));// 分机号必传，可多个用逗号分公开
			}else{
				map.put("bigcode400", ConfigUtils.instance.getMobileBigCode400());// 400
				resultvalue.put("extcode", po.getMobile());// 分机号必传，可多个用逗号分公开
			}
			
			String workgroupname = "";
			if (po.getType() == MobileTypeEnum.HOUSE.getValue()) {
				workgroupname = po.getType() + "," + po.getHouseName() + "," + po.getHouseCode() + ","
						+ po.getChannel();
			}else if(po.getType() == MobileTypeEnum.AGENT.getValue()){
				workgroupname = po.getType() + "," + po.getAgentName() + "," + po.getAgentCode();
			}else if(po.getType() == MobileTypeEnum.PROPERTY.getValue()){
				workgroupname = po.getType() + "," + po.getPropertyName() + "," + po.getPropertyId();
			}
			resultvalue.put("workgroupname", workgroupname);// 分机号名称
			resultvalue.put("content", "");// 提示音
			resultvalue.put("acdtype", po.getWay());// 轮询模式
			resultvalue.put("msgtel", po.getMsgPhone());// 短信号码
			resultvalue.put("timeout", po.getTimeOut());// 超时时间
			resultvalue.put("tellist", po.getPhone());// 接线员号码必传
			list.add(resultvalue);
			map.put("extinfo", list);
			String sendPost = URLUtils.sendPost(JsonMapper.toJsonString(map), ConfigUtils.instance.getMobileAddurl(),
					ConfigUtils.instance.getMobileLoginName(), ConfigUtils.instance.getMobileRedispwd());
			LOGGER.info("400 TEL request Add Work Group result:{}", sendPost);
			Map<String, Object> fromJsonString = (Map<String, Object>) JsonMapper.fromJsonString(sendPost, Map.class);
			Integer result = (Integer) fromJsonString.get("result");
			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("400 --1 TEL resquest add Work Group error:", e);
		}
		return false;
	}

	/*
	 * 2.修改分机信息及呼叫转移 EditWorkGroup
	 */
	public boolean EditWorkGroup(MobilePo po) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			if(po.getMobile().length()!=5){
				map.put("bigcode400", po.getMobile().substring(0, 12).replace("-", ""));// 400
				map.put("extcode", po.getMobile().substring(13, po.getMobile().length()));// 分机号必传，可多个用逗号分公开
			}else{
				map.put("bigcode400", ConfigUtils.instance.getMobileBigCode400());// 400
				map.put("extcode", po.getMobile());// 分机号必传，可多个用逗号分公开
			}
			String workgroupname = "";
			if (po.getType() == MobileTypeEnum.HOUSE.getValue()) {
				workgroupname = po.getType() + "," + po.getHouseName() + "," + po.getHouseCode() + ","
						+ po.getChannel();
			}else if(po.getType() == MobileTypeEnum.AGENT.getValue()){
				workgroupname = po.getType() + "," + po.getAgentName() + "," + po.getAgentCode();
			}else if(po.getType() == MobileTypeEnum.PROPERTY.getValue()){
				workgroupname = po.getType() + "," + po.getPropertyName() + "," + po.getPropertyId();
			}
			map.put("workgroupname", workgroupname);// 分机号名称
			map.put("content", "");// 提示音
			map.put("acdtype", po.getWay());// 轮询模式
			map.put("msgtel", po.getMsgPhone());// 短信号码
			map.put("timeout", po.getTimeOut());// 超时时间
			map.put("tellist", po.getPhone());// 接线员号码必传
			String sendPost = URLUtils.sendPost(JsonMapper.toJsonString(map), ConfigUtils.instance.getMobileEditUrl(),
					ConfigUtils.instance.getMobileLoginName(), ConfigUtils.instance.getMobileRedispwd());
			LOGGER.info("400 TEL resquest Edit Work Group result: {}", sendPost);
			Map<String, Object> fromJsonString = (Map<String, Object>) JsonMapper.fromJsonString(sendPost, Map.class);
			Integer result = (Integer) fromJsonString.get("result");
			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("400 --2 TEL resquest edit Work Group error:", e);
		}
		return false;
	}

	/*
	 * 3.删除分机并删除接线员号电话 DelWorkGroup
	 */
	public boolean DelWorkGroup(String extcode) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bigcode400", ConfigUtils.instance.getMobileBigCode400());// 400
																				// 号码必传
			map.put("extcode", extcode);// 分机号必传，可多个用逗号分公开
			map.put("guid", "");
			String sendPost = URLUtils.sendPost(JsonMapper.toJsonString(map), ConfigUtils.instance.getMobileDelUrl(),
					ConfigUtils.instance.getMobileLoginName(), ConfigUtils.instance.getMobileRedispwd());
			LOGGER.info("400 TEL resquest Edit Work Group result: {}", sendPost);
			Map<String, Object> fromJsonString = (Map<String, Object>) JsonMapper.fromJsonString(sendPost, Map.class);
			Integer result = (Integer) fromJsonString.get("result");
			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("400 --3 TEL resquest delete Work Group error:", e);
		}
		return false;
	}
	//代办人删除
	public boolean DelWorkGroupTwo(MobilePo po) {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bigcode400", po.getMobile().substring(0, 12).replace("-", ""));// 400
			map.put("extcode", po.getMobile().substring(13, po.getMobile().length()));// 分机号必传，可多个用逗号分公开
			map.put("guid", "");
			String sendPost = URLUtils.sendPost(JsonMapper.toJsonString(map), ConfigUtils.instance.getMobileDelUrl(),
					ConfigUtils.instance.getMobileLoginName(), ConfigUtils.instance.getMobileRedispwd());
			LOGGER.info("400 TEL resquest Edit Work Group result: {}", sendPost);
			Map<String, Object> fromJsonString = (Map<String, Object>) JsonMapper.fromJsonString(sendPost, Map.class);
			Integer result = (Integer) fromJsonString.get("result");
			if (result == 1) {
				return true;
			}
		} catch (Exception e) {
			LOGGER.error("400 --3 TEL resquest delete Work Group error:", e);
		}
		return false;
	}

	@Override
	public void updateMobileByFwId(MobilePo mobilePo) {
		dao.updateMobileByFwId(mobilePo);
	}

	//经纪人修改电话 重新绑定400
	@Override
	public void updateAgentPhone400(String oldPhone, String phone, Long userId) {
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("agentId", userId);
		MobilePo mobilePo = dao.getBy(hashMap);
		if(null!=mobilePo){
			mobilePo.setPhone(mobilePo.getPhone().replace(oldPhone, phone));
			mobilePo.setMsgPhone(mobilePo.getMsgPhone().replace(oldPhone, phone));
			EditWorkGroup(mobilePo);
		}
		mobilePo.setLastBindingTime(new Date());
		dao.updateDynamic(mobilePo);
	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

}
package com.dsj.modules.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.CommConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.dao.PropertyDao;
import com.dsj.modules.system.dao.UserDao;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.vo.PropertyVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-21 17:37:37.
 * @版本: 1.0 .
 */
@Service
public class PropertyServiceImpl  extends BaseServiceImpl<PropertyDao,PropertyPo> implements PropertyService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SmsLogsService smsLogsService;
	
	
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> paramMap) {
		return dao.listPage(pageParam, paramMap, "listNewPageCount", "listNewPageList");
	}

	@Override
	public void savePropertyAdd(PropertyVo vo) throws Exception {
				// 添加账号信息--1
				UserPo userPo = new UserPo();
				userPo.setRealname(vo.getName());
				userPo.setUsername(vo.getTellPhone());
				userPo.setPassword(ShiroSaltAndMd5Utils.getMD5(CommConst.INIT_PWD));
				userPo.setUserType(UserType.WARRANT.getValue());
				userPo.setStatus(UserStatusEnum.NO_AUDIT.getValue());
				userPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
				userPo.setAvatar(vo.getAvatarUrl());
				userPo.setPhone(vo.getTellPhone());
				userPo.setCreatePerson(Integer.parseInt(vo.getCreateUser().toString()));
				Long userId = userDao.insertDynamic(userPo);
				
				// 添加权证代办人信息--2
				PropertyPo propertyPo = new PropertyPo();
				MyBeanUtils.copyBean2Bean(propertyPo,vo);
				String[] bidList = propertyPo.getBusiness().split(",");
				String newBid = "";
				for (String idStr : bidList) {
					if (StringUtils.isNotBlank(idStr)) {
						newBid += "," + idStr;
					}
				}
				String[] bnameList = propertyPo.getBusinessName().split(",");
				String newBname = "";
				for (String nameStr : bnameList) {
					if (StringUtils.isNotBlank(nameStr)) {
						newBname += "," + nameStr;
					}
				}
				propertyPo.setBusiness(newBid.substring(1));
				propertyPo.setBusinessName(newBname.substring(1));
				propertyPo.setUserId(userId);
				propertyPo.setCreateTime(new Date());
				propertyPo.setAuditStatus(1);
				propertyPo.setRecommend(0);
				dao.insertDynamic(propertyPo);
				
				//发送短信给当前通过的代办人
				if (propertyPo.getTellPhone() != null && StringUtils.isNumeric(propertyPo.getTellPhone())) {
					smsLogsService.saveLogsAndsend(propertyPo.getTellPhone(),SMSTemplate.getDrawingTemplateTwo(SMSTemplate.ZCCG,vo.getTellPhone()));
				}
	}

	@Override
	public Integer getTuiJianPropertyCount() {
		return dao.getTuiJianPropertyCount();
	}
	
	/**
	 * 服务者列表
	 * @author gaocj
	 * @return PageBean
	 */
	@Override
	public PageBean listFwUserPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listFwUserPageCount", "listFwUserPage");
	}

	@Override
	public List<PropertyPo> listNewBy(HashMap<String, Object> map) {
		return dao.listNewBy(map);
	}

	@Override
	public void updatePhoto(UserPo user) {
		dao.updatePhoto(user);
	}

	@Override
	public void updatePhoneUserById(PropertyPo property) {
		dao.updatePhoneUserById(property);
	}

	@Override
	public void updateOrUserDynamic(PropertyPo propertyPo, UserPo user) {
		userDao.updateDynamic(user);
		dao.updateDynamic(propertyPo);
	}

	@Override
	public PageBean listNewFrontPage(PageParam pageParam, Map<String, Object> requestMap) {
		 PageBean page = dao.listPage(pageParam, requestMap, "listFwUserPageCount", "listFwUserPage");
		List<?> recordList = page.getRecordList();
		for (Object object : recordList) {
			PropertyVo vo = (PropertyVo)object;
			if(null!=vo.getOrderCount()){
				vo.setOrderCount(vo.getDeal()+vo.getOrderCount());
			}else{
				vo.setOrderCount(vo.getDeal());
			}
		}
		return page;
		
	}

	@Override
	public List<PropertyVo> getProByTuiJian() {
		List<PropertyVo> proByTuiJian = dao.getProByTuiJian();
		for (PropertyVo vo : proByTuiJian) {
			if(null!=vo.getOrderCount()){
				vo.setOrderCount(vo.getDeal()+vo.getOrderCount());
			}else{
				vo.setOrderCount(vo.getDeal());
			}
		}
		return  proByTuiJian;
	}

	@Override
	public String getAboutArea(String spuId) {
		return dao.getAboutArea(spuId);
	}

	@Override
	public PropertyVo getVoById(Long id) {
		PropertyVo vo = dao.getVoById(id);
		if(null!=vo.getOrderCount()){
			vo.setOrderCount(vo.getDeal()+vo.getOrderCount());
		}else{
			vo.setOrderCount(vo.getDeal());
		}
		return vo;
	}

	@Override
	public PropertyPo getTuiJianTimeLimitYi() {
		return dao.getTuiJianTimeLimitYi();
	}

	@Override
	public List<Integer> getProIdByOrderStatus() {
		return dao.getProIdByOrderStatus();
	}

	@Override
	public void updateResetPwdMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			UserPo user =new UserPo();
			Map<String,Object> map = new HashMap<String,Object>();

			map.put("password", ShiroSaltAndMd5Utils.getMD5(CommConst.INIT_PWD));
			map.put("list", idlist);
			dao.resetPwdMany(map);
			
				
		}
		
	}

	@Override
	public long getPoCountByPhone(HashMap<String, Object> map) {
		return dao.getPoCountByPhone(map);
	}


}
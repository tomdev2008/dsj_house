package com.dsj.modules.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.CommConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.utils.converter.DateConverter;
import com.dsj.modules.system.dao.FlatUserDao;
import com.dsj.modules.system.dao.UserDao;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.FlatUserPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.FlatUserService;
import com.dsj.modules.system.vo.FlatUserVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-06-15 19:11:19.
 * @版本: 1.0.
 */
@Service
public class FlatUserServiceImpl  
	extends BaseServiceImpl<FlatUserDao,FlatUserPo> implements FlatUserService {
	
	private final Logger logger = LoggerFactory
			.getLogger(FlatUserServiceImpl.class);
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public void saveFlatUserAdd(FlatUserVo vo) throws Exception {
		UserPo userPo = new UserPo();
		userPo.setUsername(vo.getUsername());
		Integer salt = new Random().nextInt(20) + 1;
		userPo.setSalt(salt.toString());
		// 对照shiro的md5方式加密
		SimpleHash hashpwd = new SimpleHash(Md5Hash.ALGORITHM_NAME, 
				CommConst.INIT_PWD, SimpleHash.toBytes(salt.toString()), 1024);
		userPo.setPassword(hashpwd.toString());
		userPo.setUserType(UserType.FLAT.getValue());
		userPo.setStatus(UserStatusEnum.NO_AUDIT.getValue());
		userPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
		userPo.setUpdatePerson(Integer.parseInt(vo.getUpdatePerson().toString()));
		userPo.setCreatePerson(Integer.parseInt(vo.getCreatePerson().toString()));
		Long userId = userDao.insertDynamic(userPo);

		FlatUserPo flatUserPo = new FlatUserPo();
		MyBeanUtils.copyBean2Bean(flatUserPo, vo);
		flatUserPo.setUserId(userId);
		dao.insertDynamic(flatUserPo);
		
		//默认品牌公寓角色
		HashMap<String,Object> hashMap = new HashMap<String,Object>();
		hashMap.put("userId", userId);
		hashMap.put("roleId", 64);
		userDao.saveUserRole(hashMap);
		
	}

	@Override
	public void saveFlatUserUpdate(FlatUserVo vo) throws Exception {
		// 修改账号信息
		FlatUserPo flatUserPo = new FlatUserPo();
		flatUserPo.setId(vo.getId());
		//只有审核被拒绝时才能修改的内容
		if(vo.getStatus()==UserStatusEnum.NO.getValue()){
			MyBeanUtils.copyBean2Bean(flatUserPo, vo);
			UserPo userPo = new UserPo();
			userPo.setId(vo.getUserId());
			userPo.setUpdatePerson(Integer.parseInt(vo.getUpdatePerson().toString()));
			userPo.setUpdateTime(new Date());
			userDao.updateDynamic(userPo);
			
		}
		// 修改开发商信息信息
		flatUserPo.setContact(vo.getContact());
		flatUserPo.setContactPhone(vo.getContactPhone());
		flatUserPo.setContactIdCard(vo.getContactIdCard());
		dao.updateDynamic(flatUserPo);
	}
	
	@Override
	public void deleteFlatUsers(String ids) {
		dao.deleteByIds(ids);
	}

	@Override
	public FlatUserVo getVoById(Long id) {
		return dao.getVoById(id);
	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPageList");
	}
}
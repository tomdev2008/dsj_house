package com.dsj.modules.system.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.system.dao.EvelopersDao;
import com.dsj.modules.system.dao.UserDao;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.EvelopersService;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 *
 * @閹诲繗鍫�: Service閹恒儱褰涚�圭偟骞囩猾锟�.
 * @娴ｆ粏锟斤拷: gaocj
 * @閸掓稑缂撻弮鍫曟？: 2017-06-15 14:38:53.
 * @閻楀牊婀�: 1.0 .
 */
@Service
public class EvelopersServiceImpl extends BaseServiceImpl<EvelopersDao, EvelopersPo> implements EvelopersService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;

	@Override
	public void saveEvelopersAdd(EvelopersVo vo) throws Exception {
		// 濞ｈ濮炵拹锕�褰挎穱鈩冧紖--1
		UserPo userPo = new UserPo();
		userPo.setCreateTime(new Date());
		userPo.setUpdateTime(new Date());
		userPo.setDelFlag(DeleteStatusEnum.NDEL.getValue());
		userPo.setCreatePerson(vo.getCreatePerson());
		userPo.setUpdatePerson(vo.getCreatePerson());
		if(vo.getStatus()==null){
			userPo.setStatus(UserStatusEnum.WAIT.getValue());
		}else{
			userPo.setStatus(vo.getStatus());
		}
		userPo.setUsername(vo.getUsername());
		userPo.setUserType(UserType.DEVELOPER.getValue());
		userPo.setPhone(vo.getOperationPhone());
		userPo.setPassword(ShiroSaltAndMd5Utils.getMD5(vo.getPassword()));
		String loupanIds=vo.getLoupanName();
		long userId = userDao.insertDynamic(userPo);
		
		// 濞ｈ濮炲锟介崣鎴濇櫌娣団剝浼呮穱鈩冧紖--2
		EvelopersPo evelopersPo = new EvelopersPo();
		MyBeanUtils.copyBean2Bean(evelopersPo,vo);
		evelopersPo.setOperationPhone(userPo.getPhone());
		evelopersPo.setUserId(userId);
		evelopersPo.setCreateTime(new Date());
		dao.insertDynamic(evelopersPo);
		if(loupanIds!=null){
			String[] loupanName=loupanIds.split(",");
			for(int i=0;i<loupanName.length;i++){
				Map<String, Object> paramMap=new HashMap<>();
				paramMap.put("userId", userId);
				paramMap.put("id", Long.parseLong(loupanName[i]));
				newHouseDirectoryAuthService.updateEvelopersPerson(paramMap);
				
			}
		}
		
	}

	@Override
	public void saveEvelopersUpdate(EvelopersVo vo) throws Exception {
		//驳回的被修改   1.状态待审核 2如果是超级管理员 ,设置下用户名称
		if (vo.getStatus()!=null && vo.getStatus() == UserStatusEnum.NO.getValue()) {
			UserPo userPo = new UserPo();
			userPo.setStatus(UserStatusEnum.NO_AUDIT.getValue());
			userPo.setId(vo.getUserId());
			userPo.setUpdatePerson(vo.getUpdatePerson());
			userPo.setUpdateTime(new Date());
			if(vo.getUpdateWhat().equals("1")){
				userPo.setUsername(vo.getUsername());
			}
			userDao.updateDynamic(userPo);
		}else if("1".equals(vo.getUpdateWhat())){
		//超级管理员修改user表
			UserPo userPo = new UserPo();
			userPo.setId(vo.getUserId());
			userPo.setUpdatePerson(vo.getUpdatePerson());
			userPo.setUpdateTime(new Date());
			userPo.setUsername(vo.getUsername());
			userDao.updateDynamic(userPo);
		}
		//非超级管理员可修改的信息
		EvelopersPo evelopersPo = new EvelopersPo();
		MyBeanUtils.copyBean2Bean(evelopersPo, vo);
		evelopersPo.setUpdateTime(new Date());
		dao.updateDynamic(evelopersPo);

	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPageList");
	}

	@Override
	public EvelopersVo getVoById(Long id) {
		EvelopersVo vo = dao.getVoById(id);
		String loupanIds = vo.getLoupanName();
		if(StringUtils.isNotBlank(loupanIds)){
			List<NewHouseDirectoryAuthPo> list = newHouseDirectoryAuthService.getIdAndName(loupanIds);
			String str = ","+loupanIds+",";
			while(str.indexOf(",1,")>-1){
				NewHouseDirectoryAuthPo po = new NewHouseDirectoryAuthPo();
				po.setId(1L);
				po.setName("鍏朵粬");
				list.add(po);
				str = str.replace(",1,", ",");
			}
			vo.setIdAndNameList(list);
			
		}
		return vo;
	}

	@Override
	public List<EvelopersVo> listByIds(String ids) {
		return dao.listByIds(ids);
	}

	@Override
	public PageBean listEvelopersPage(PageParam pageParam, Map<String, Object> requestMap) {
		 return dao.listPage(pageParam, requestMap, "listEvelopersCount", "listEvelopersList");
	}

	@Override
	public EvelopersVo getVoBy(Map<String, Object> evelopersMap) {
		return dao.getVoBy(evelopersMap);
	}

	@Override
	public void updateOrUserDynamic(EvelopersPo evelopers, UserPo user) {
		updateDynamic(evelopers);
		userDao.updateDynamic(user);
	}

	@Override
	public void updateEveloper(Map<String, Object> paramMap) {
		dao.updateEveloper(paramMap);
		
	}

	@Override
	public EvelopersPo getEveloper(Long id) {
		Map<String, Object> paramMap=new HashMap<>();
		paramMap.put("userId", id);
		return dao.getEveloper(paramMap);
	}

}
package com.dsj.modules.system.service.impl;

import java.util.ArrayList;
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
import com.dsj.common.enums.RoleChecked;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.vo.RoleVo;
import com.dsj.modules.system.dao.EmployeeDao;
import com.dsj.modules.system.dao.UserDao;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.EmployeePo;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;

@Service
public class EmployeeServiceImpl  extends BaseServiceImpl<EmployeeDao,EmployeePo> implements EmployeeService {
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private UserDao userDao;
	@Override
	public void modifyPassword(String newPwd,long id, Integer updatePerson) {
		UserPo user =new UserPo();
		Integer salt = new Random().nextInt(20) + 1;
		
		SimpleHash hashpwd = new SimpleHash(Md5Hash.ALGORITHM_NAME, 
				newPwd, SimpleHash.toBytes(salt.toString()), 1024);
		
		user.setPassword(hashpwd.toString());
		user.setSalt(salt.toString());
		user.setId(id);
		user.setUpdatePerson(updatePerson);
		userDao.updateDynamic(user);
	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listPageCount", "listPage");
	}

	@Override
	public void insertEmp(EmployeePo emp) {
		
		employeeDao.insert(emp);
	}
	@Override
	public void deleteUser(String ids) {
		if(StringUtils.isNotBlank(ids)){
			Integer delFlag = DeleteStatusEnum.DEL.getValue();
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			employeeDao.deleteByIds(idlist,delFlag);
			
				
		}
		
	}

	@Override
	public long addUser(UserPo user) {
		
		user.setPassword(ShiroSaltAndMd5Utils.getMD5(CommConst.INIT_PWD));
		user.setStatus(UserStatusEnum.NO_AUDIT.getValue());
		user.setDelFlag(DeleteStatusEnum.NDEL.getValue());

		Long userId = userDao.insertDynamic(user);
		return userId;
	}
	@Override
	public boolean findPasswordById(long id, String oldPwd) {
		UserPo user = userDao.getById(id);
		String salt =user.getSalt();
		SimpleHash hashpwd = new SimpleHash(Md5Hash.ALGORITHM_NAME, 
				oldPwd, SimpleHash.toBytes(salt.toString()), 1024);
		if(hashpwd.toString().equals(user.getPassword())){
			return true;
		}else{
			return false;
		}
	}


	@Override
	public EmployeePo getUser(long id) {
		return employeeDao.getById(id);
	}
	@Override
	public Long selectUser(Long id) {
		Map<String, Object> paramMap=new HashMap<String,Object>();
		paramMap.put("default_role", id);
		return employeeDao.selectUser(paramMap);
	}

	@Override
	public void insertRole(String role, long userId) {
		String[] roleList = role.split(",");
		if(roleList.length>0){
			List<Integer> list = new ArrayList<Integer>();
			for(int i=0;i<roleList.length;i++){
				list.add(Integer.valueOf(roleList[i]));
			}
			employeeDao.insertRole(list,userId);
		}
		
	}
	@Override
	public List<Integer> getRoleIdList(long userId) {
		
		return employeeDao.getRoleIdList(userId);
	}
	@Override
	public List<RoleVo> getRoleName(List<Integer> roleIdList) {
		List<RoleVo> roleNamList = new ArrayList<RoleVo>();
		if(roleIdList.size()>0){
			List<RolePo> list = employeeDao.getRoleNameList();
			for (RolePo rolePo : list) {
				int flag = 0;
				for(Integer roleId : roleIdList){
					RoleVo role = new RoleVo();
					if(Long.valueOf(roleId)==rolePo.getId()){
						role.setIsChecked(RoleChecked.CHECKED.getCode());
						role.setId(rolePo.getId());
						role.setName(rolePo.getName());
						flag = 1;
						roleNamList.add(role);
					}
				}
				if(flag == 0){
					RoleVo role = new RoleVo();					
					role.setIsChecked(RoleChecked.UNCHECKED.getCode());
					role.setId(rolePo.getId());
					role.setName(rolePo.getName());
					roleNamList.add(role);
				}				
			}
			return roleNamList;
		}else{
			List<RolePo> list = employeeDao.getRoleNameList();
			for (RolePo rolePo : list) {
				RoleVo role = new RoleVo();					
				role.setIsChecked(RoleChecked.UNCHECKED.getCode());
				role.setId(rolePo.getId());
				role.setName(rolePo.getName());
				roleNamList.add(role);
			}
			return roleNamList;
		}
	}
	@Override
	public List<RoleVo> getAllRole() {
		List<RolePo> list = employeeDao.getRoleNameList();
		List<RoleVo> roleNamList = new ArrayList<RoleVo>();
		for (RolePo rolePo : list) {
			RoleVo role = new RoleVo();					
			role.setIsChecked(RoleChecked.UNCHECKED.getCode());
			role.setId(rolePo.getId());
			role.setName(rolePo.getName());
			roleNamList.add(role);
		}
		return roleNamList;
	}
	@Override
	public String getRoleNameString(List<Integer> roleIdList) {
		List<String> nameList = employeeDao.getRoleNameString(roleIdList);
		String name = "";
		if(nameList.size()>0){
			for(String item:nameList){
				name = name+item+",";
			}
			name = name.substring(0,name.length()-1);
		}
		return name;
	}
	@Override
	public void deleteRole(long userId) {
		employeeDao.deleteRole(userId);
		
	}

	@Override
	public String getNewEmpNum() {
		List<Integer> empNumlist = employeeDao.getEmpNums();
		if(empNumlist.size()>0){
			List<Integer> empNumlistTemp = new ArrayList<Integer>();
			for(Integer item : empNumlist){
				if(String.valueOf(item).length()==5 && "10".equals(String.valueOf(item).substring(0, 2))){
					empNumlistTemp.add(item);
				}
			}
			if(empNumlistTemp.size()>0){
				int max = 0;
				for(Integer itemTemp : empNumlistTemp){
					if(itemTemp > max){
						max = itemTemp;
					}
				}
				return String.valueOf(max+1);
			}else{
				return CommConst.EMP_NUM_START;
			}
		}
		return CommConst.EMP_NUM_START;
	}
	
	
	
}
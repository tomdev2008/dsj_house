package com.dsj.data.shiro.realm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.utils.redis.one.RedisPoolUtil;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.FunctionService;
import com.dsj.modules.system.service.RoleService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.SysResourceVo;



public class MyRealm extends AuthorizingRealm{

	private final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private FunctionService functionService;
	
	
	public MyRealm(){
		
		HashedCredentialsMatcher cm=new HashedCredentialsMatcher();
		cm.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
		cm.setHashIterations(1);
		setCredentialsMatcher(cm);
	}
	
	/**
	 * shiro权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		LOGGER.info("userName{}",userName);
		UserPo admin=userService.getUserByName(userName);
		LOGGER.info("admin.id{}",admin.getId());
		LOGGER.info("admin.name{}",admin.getUsername());
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		//获取用户的角色
		List<String> roles=roleService.getRoles(admin);
		Set<String> rolesSet = new HashSet<String>(roles);
		//获取用户角色对象
		if(!roles.isEmpty()){
			authorizationInfo.setRoles(rolesSet);
		}
		List<RolePo> rolelist=roleService.getRolesList(roles);
		List<String> roleStrs=new ArrayList<String>();
		
		List<FunctionPo> list= functionService.listMenus(rolelist);
		
		List<SysResourceVo> volist = new ArrayList<SysResourceVo>();
		List<FunctionPo> polist = new ArrayList<FunctionPo>();
		//访问權限集合
		List<String> patterns = new ArrayList<String>();
		patterns=functionService.getPatternsList(admin);
		
		for (int i = 0; i < list.size(); i++) {
			//如果没有pid,把vo存入新的list内
			if(list.get(i).getPid()==null||list.get(i).getPid()==0){
				//对象转换
				SysResourceVo vo = new SysResourceVo(list.get(i));
				//收集父菜单
				volist.add(vo);
			}else{
				//收集非父菜单
				polist.add(list.get(i));
			}
		}
		
		for (int i = 0; i < volist.size(); i++) {
			//新建空子菜单集合
			ArrayList<FunctionPo> newlist = new ArrayList<FunctionPo>();
			for (int j = 0; j < polist.size(); j++) {
				if (polist.get(j).getPid().longValue()==volist.get(i).getId()) {
					newlist.add(polist.get(j));
				}
			}
			//把子菜单添加到对应的父菜单下
			volist.get(i).setResourcePo(newlist);
		}
		List<Long> roleIdList = new ArrayList<Long>();
		for(RolePo role:rolelist){
			roleIdList.add(role.getId());
			roleStrs.add(role.getNameCode());
		}

		SecurityUtils.getSubject().getSession().setAttribute("sessionUser", admin);
		SecurityUtils.getSubject().getSession().setAttribute("menus", volist);
	
		SecurityUtils.getSubject().getSession().setAttribute("menusPatterns", patterns);
		ShiroUtils.setSessionUser(admin);
		authorizationInfo.addStringPermissions((List<String>)SecurityUtils.getSubject().getSession().getAttribute("menusPatterns"));
		authorizationInfo.addRoles(roleStrs);
		return authorizationInfo;
	}

	/**
	 * shiro登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	
		String username=(String)token.getPrincipal();
		
		/*  byte key[]={-84, -19, 0, 5, 115, 114, 0, 50, 111, 114, 103, 46, 97, 112, 97, 99, 104, 101, 46, 115, 104, 105, 114, 111, 46, 115, 117, 98, 106, 101, 99, 116, 46, 83, 105, 109, 112, 108, 101, 80, 114, 105, 110, 99, 105, 112, 97, 108, 67, 111, 108, 108, 101, 99, 116, 105, 111, 110, -88, 127, 88, 37, -58, -93, 8, 74, 3, 0, 1, 76, 0, 15, 114, 101, 97, 108, 109, 80, 114, 105, 110, 99, 105, 112, 97, 108, 115, 116, 0, 15, 76, 106, 97, 118, 97, 47, 117, 116, 105, 108, 47, 77, 97, 112, 59, 120, 112, 115, 114, 0, 23, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 76, 105, 110, 107, 101, 100, 72, 97, 115, 104, 77, 97, 112, 52, -64, 78, 92, 16, 108, -64, -5, 2, 0, 1, 90, 0, 11, 97, 99, 99, 101, 115, 115, 79, 114, 100, 101, 114, 120, 114, 0, 17, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 72, 97, 115, 104, 77, 97, 112, 5, 7, -38, -63, -61, 22, 96, -47, 3, 0, 2, 70, 0, 10, 108, 111, 97, 100, 70, 97, 99, 116, 111, 114, 73, 0, 9, 116, 104, 114, 101, 115, 104, 111, 108, 100, 120, 112, 63, 64, 0, 0, 0, 0, 0, 12, 119, 8, 0, 0, 0, 16, 0, 0, 0, 1, 116, 0, 34, 99, 111, 109, 46, 100, 115, 106, 46, 100, 97, 116, 97, 46, 115, 104, 105, 114, 111, 46, 114, 101, 97, 108, 109, 46, 77, 121, 82, 101, 97, 108, 109, 95, 48, 115, 114, 0, 23, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 76, 105, 110, 107, 101, 100, 72, 97, 115, 104, 83, 101, 116, -40, 108, -41, 90, -107, -35, 42, 30, 2, 0, 0, 120, 114, 0, 17, 106, 97, 118, 97, 46, 117, 116, 105, 108, 46, 72, 97, 115, 104, 83, 101, 116, -70, 68, -123, -107, -106, -72, -73, 52, 3, 0, 0, 120, 112, 119, 12, 0, 0, 0, 16, 63, 64, 0, 0, 0, 0, 0, 1, 116, 0, 5, 97, 100, 109, 105, 110, 120, 120, 0, 119, 1, 1, 113, 0, 126, 0, 5, 120};
		  RedisPoolUtil.del(key);*/
		//@add 20170919 redis 
		removeUserAuthorizationInfoCache(username);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("login_flag", YesNoEnum.YES.getValue());
		map.put("del_flag",DeleteStatusEnum.NDEL.getValue());
		map.put("user_type", UserType.EMPLOYEE.getValue());
		UserPo admin = userService.getBy(map);
			if(admin!=null){
				//byte[] salt=SimpleHash.toBytes(admin.getSalt());
				//AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),getName());
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),getName());
				//clearCachedAuthorizationInfo();
				return authcInfo;
			}else{
				return null;				
			}
	}


    /**
     * 清空用户关联权限认证，待下次使用时重新加载。
     * 
     * @param principal
     */
    public void clearCachedAuthorizationInfo(String principal) {
        SimplePrincipalCollection principals = new SimplePrincipalCollection(
                principal, getName());
        clearCachedAuthorizationInfo(principals);
    }

    /**
     * 清空所有关联认证
     */
    public void clearAllCachedAuthorizationInfo() {
        Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
        if (cache != null) {
            for (Object key : cache.keys()) {
                cache.remove(key);
            }
        }
    }
    
    public void removeUserAuthorizationInfoCache(String username) {  
        SimplePrincipalCollection pc = new SimplePrincipalCollection();  
        pc.add(username, super.getName());  
        super.clearCachedAuthorizationInfo(pc);  
    }  

}

package com.dsj.data.shiro.realm;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;



public class MyRealm extends AuthorizingRealm{

	private final Logger LOGGER = LoggerFactory.getLogger(MyRealm.class);

	@Autowired
	private UserService userService;
	
	public MyRealm(){
		
		HashedCredentialsMatcher cm=new HashedCredentialsMatcher();
		cm.setHashAlgorithmName(Md5Hash.ALGORITHM_NAME);
		cm.setHashIterations(1024);
		setCredentialsMatcher(cm);
	}
	
	/**
	 * shiro权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("username", userName);
		map.put("userType",UserType.WARRANT.getValue());
		UserPo admin=userService.getByLeftPropert(map);
		ShiroUtils.setSessionUser(admin);
		LOGGER.info("-----------------session set userMsg success----------------------");
		return authorizationInfo;
	}

	/**
	 * shiro登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		String username=(String)token.getPrincipal();
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("loginFlag", YesNoEnum.YES.getValue());
		map.put("delFlag",DeleteStatusEnum.NDEL.getValue());
		map.put("userType",UserType.WARRANT.getValue());
		UserPo admin = userService.getBy(map);
			if(admin!=null){
				//byte[] salt=SimpleHash.toBytes(admin.getSalt());
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),getName());
				//AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(admin.getUsername(),admin.getPassword(),ByteSource.Util.bytes(salt),getName());
				//clearCachedAuthorizationInfo();
				LOGGER.info("-----------------login-success----------------------");
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

}

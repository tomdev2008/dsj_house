package com.dsj.data.wap.utils;


//import com.eqiao.city.modules.user.po.SysAdminPo;
import org.apache.shiro.SecurityUtils;

import com.dsj.common.constants.BusinessConst;
import com.dsj.modules.system.po.UserPo;

/**
 * Created by wdg on 2016/11/4.
 */
public class ShiroUtils {

    public static UserPo getSessionUser(){
    	 return (UserPo) SecurityUtils.getSubject().getSession().getAttribute(BusinessConst.WAP_USER_SIESSION);
    }

    public static void setSessionUser(Object admin){
        SecurityUtils.getSubject().getSession().setAttribute(BusinessConst.WAP_USER_SIESSION,admin);
    }

}

package com.dsj.data.web.utils;


//import com.eqiao.city.modules.user.po.SysAdminPo;
import org.apache.shiro.SecurityUtils;

import com.dsj.modules.system.po.UserPo;

/**
 * Created by wdg on 2016/11/4.
 */
public class ShiroUtils {

    public static UserPo getSessionUser(){
    	 return (UserPo) SecurityUtils.getSubject().getSession().getAttribute("sessionUser");
    }

    public static void setSessionUser(Object admin){
        SecurityUtils.getSubject().getSession().setAttribute("sessionUser",admin);
    }

}

package com.dsj.data.wap.utils;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dsj.common.constants.BusinessConst;
import com.dsj.modules.system.po.UserPo;

/**
 * Created by shao on 8/10/17.
 * LIMU
 */
public class UserUtil {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserUtil.class);
    /***
     * get current login id based on current http servlet requset
     * @param request
     * @return
     */
    public static UserPo getCurrentUser(HttpServletRequest request){
        UserPo user = (UserPo)request.getSession().getAttribute(BusinessConst.WAP_USER_SIESSION);
        return user;
    }

    public static Long getCurrentUserLoginId(HttpServletRequest request){
        Long userId = 0L;
        UserPo user = (UserPo)request.getSession().getAttribute(BusinessConst.WAP_USER_SIESSION);
        if(user != null) {
            userId = user.getId();
        } else {
            userId = 1L;
        }
        return userId;
    }


    public static void removeUserSession(HttpServletRequest request, String sessionKey) {
        request.getSession().removeAttribute(sessionKey);
    }

}

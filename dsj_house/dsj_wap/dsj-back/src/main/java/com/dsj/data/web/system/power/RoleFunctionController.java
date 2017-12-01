package com.dsj.data.web.system.power;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.system.service.RoleFunctionService;
@Controller
@RequestMapping(value = "back/**/roleFunction")
public class RoleFunctionController {
	private final Logger LOGGER = LoggerFactory.getLogger(RoleFunctionController.class);
    @Autowired
	private RoleFunctionService roleFunctionService;
    
    @RequestMapping("updateRoleFunction")
    @ResponseBody
    public AjaxResultVo updateRoleFunction(HttpServletRequest req,Integer roleId){
    	AjaxResultVo j=new AjaxResultVo();
		try {
			String[] funList=req.getParameterValues("functionListId");
			roleFunctionService.updateRoleFunction(funList,roleId);
			j.setMessage("保存成功");
			j.setStatus(0);
		} catch (Exception e) {
			LOGGER.info("给角色赋权限失败",e.getMessage(),e);
			j.setMessage("给角色赋权限失败");
			j.setStatus(1);
		}
		return j;
	}
	
}

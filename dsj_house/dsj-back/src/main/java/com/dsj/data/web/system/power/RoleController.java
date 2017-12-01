package com.dsj.data.web.system.power;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.service.RoleFunctionService;
import com.dsj.modules.system.service.RoleService;
@Controller
@RequestMapping(value = "back/**/role")
public class RoleController {
	private final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleFunctionService roleFunctionService;
	@Autowired
	private EmployeeService employeeService;

   /***
    * 添加角色
    * @param name
    * @param nameCode
    * @param request
    * @return
    */
	@RequestMapping("role_add")
	@ResponseBody
	public AjaxResultVo addRole(RolePo rolePo,HttpServletRequest request) {
		AjaxResultVo ajax=new AjaxResultVo();
		RolePo po = roleService.getRoleByName(rolePo.getName());
		RolePo poCodeModel=roleService.getRoleByCodeName(rolePo.getNameCode());
		if(null!=poCodeModel){
			ajax.setMessage("该角色编码已存在!");
		}else{
			// 添加结果
			try {
				if (null == po) {
					rolePo.setCreateTime(new Date());
					roleService.saveDynamic(rolePo);
					ajax.setStatusCode(StatusCode.SUCCESS);
				} else {
					ajax.setStatusCode(StatusCode.ROLE_HAD);
				}
			} catch (Exception e) {
				LOGGER.error(CommConst.QUERY_DATA_ERROR, e);
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
		}
		
		return ajax;
	}
	
	/**
	 * 跳转到角色页面
	 * @return
	 */
	@RequestMapping("rolePage")
	public String rolePage(){
		
		return "system/roleFunction/role";
	}
	
	@RequestMapping("toRoleAdd")
	public String toRoleAdd(){
		
		return "system/roleFunction/role_add";
	}
	
	/**
	 * 查询所有的角色，带分页
	 * @param request
	 * @param current
	 * @return
	 */
	@RequestMapping("findAllRole")
	@ResponseBody
	public PageDateTable<?> findAllRole(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = roleService.listNewRolePage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.info("角色查询异常", e.getMessage(), e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 删除角色
	 * @param req
	 * @param roleId
	 * @return
	 */
	@RequestMapping("delRole")
	@ResponseBody
     public AjaxResultVo evelopersReset(Long id) {
		AjaxResultVo ajax=new AjaxResultVo();
	   try {
		   long count=employeeService.selectUser(id);
			if(count >=1){
				ajax.setMessage("有使用角色的员工，不能删除");
			}else{
				roleService.delRole(id);
				roleFunctionService.delRoleFunction(id);
				ajax.setStatusCode(StatusCode.SUCCESS);
			}	
		} catch (Exception e) {
			LOGGER.error("删除角色异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			ajax.setMessage("有使用角色的员工，不能删除");
		}
		return ajax;
	}

}

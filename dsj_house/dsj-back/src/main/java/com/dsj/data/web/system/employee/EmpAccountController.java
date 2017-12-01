package com.dsj.data.web.system.employee;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EmployeeVo;
import com.dsj.modules.system.vo.RoleVo;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.EmployeePo;
import com.dsj.modules.system.po.UserPo;


/**
 * 后台员工管理
 */
@Controller
@RequestMapping(value = "back/**/employee")
public class EmpAccountController {
	private final Logger LOGGER = LoggerFactory.getLogger(EmpAccountController.class);
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private UserService userService;
	
	
	@RequestMapping("employeeList")
    public ModelAndView index(HttpServletRequest request,
            HttpServletResponse response) { 
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/employee/employee_list");
		//mav.addObject(attributeName, attributeValue);
		return mav;
	}
	@RequestMapping("dataList")
	@ResponseBody
	public PageDateTable<?> adminList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		
		PageBean page = null;
		try {
			page = employeeService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("用户账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	/**
	 * 增加员工页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("add")
    public ModelAndView add(HttpServletRequest request,
            HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/employee/employee_add");
		List<RoleVo> roleList = employeeService.getAllRole();
		String empNum = employeeService.getNewEmpNum();
		mav.addObject("roles",roleList);
		mav.addObject("empNum",empNum);
		return mav;
	}
	
	/**
	 * 增加员工
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("addEmployee")
	@ResponseBody
    public AjaxResultVo addEmployee(HttpServletRequest request,
            HttpServletResponse response,EmployeeVo emp) {
		AjaxResultVo result = new AjaxResultVo();
		int count = userService.findByUsername(emp.getTellPhone());
		if(count!=0){
			result.setStatusCode(StatusCode.PHONE_EXIST);
		}else{
			UserPo user = new UserPo();
			user.setUsername(emp.getTellPhone());
			user.setRealname(emp.getRealName());
			user.setCreatePerson(ShiroUtils.getSessionUser().getId().intValue());
			user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
			user.setCreateTime(new Date());
			user.setUserType(UserType.EMPLOYEE.getValue());
			user.setPhone(emp.getTellPhone());
			try {
				long userId = employeeService.addUser(user);
				employeeService.insertRole(emp.getRole(),userId);
				List<Integer> roleIdList = employeeService.getRoleIdList(userId);
				emp.setRoleName(employeeService.getRoleNameString(roleIdList));
				emp.setUserId(userId);
				emp.setCreateTime(new Date());
				employeeService.insertEmp(emp);
				result.setStatusCode(StatusCode.SUCCESS);

			} catch (Exception e) {
				LOGGER.error("添加用户异常", e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}
		}
		
		return result;
		
	}

	/**
	 * 编辑员工信息信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("edit")
    public ModelAndView edit(long id) {
		
		ModelAndView mav =new ModelAndView();
		mav.setViewName("system/employee/employee_edit");
		try {
			EmployeePo employee = employeeService.getUser(id);
			List<Integer> roleIdList = employeeService.getRoleIdList(employee.getUserId());
			List<RoleVo> roleNameList = employeeService.getRoleName(roleIdList);
			mav.addObject("roles", roleNameList);
			mav.addObject("emp", employee);

		} catch (Exception e) {
			LOGGER.error("查询用户异常", e);
			
		}

		return mav;
	}
	/**
	 * 提交编辑员工信息信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("editEmployee")
	@ResponseBody
    public AjaxResultVo editEmployee(EmployeeVo emp) {
		AjaxResultVo result = new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("phone", emp.getTellPhone());
		UserPo u = userService.getBy(map);
		if(u!=null&&!emp.getUserId().equals(u.getId())){
			result.setStatusCode(StatusCode.PHONE_EXIST);
			result.setMessage("手机号已注册");
			return result;
		}else{
			try {
				if(emp.getRole()!=null){
					employeeService.deleteRole(emp.getUserId());
					employeeService.insertRole(emp.getRole(),emp.getUserId());
					List<Integer> roleIdList = employeeService.getRoleIdList(emp.getUserId());
					emp.setRoleName(employeeService.getRoleNameString(roleIdList));
				}
				
				emp.setUpdateTime(new Date());
				employeeService.updateDynamic(emp);
				
				
				UserPo user = new UserPo();
				user.setUsername(emp.getTellPhone());
				user.setRealname(emp.getRealName());
				user.setUpdatePerson(ShiroUtils.getSessionUser().getId().intValue());
				user.setPhone(emp.getTellPhone());
				user.setId(emp.getUserId());
				userService.updateDynamic(user);
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				result.setStatusCode(StatusCode.SERVER_ERROR);
				LOGGER.error("更新异常", e);		
			}
		}
			
		return result;
	}
	/**
	 * 员工个人信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("empInfo")
    public ModelAndView userInfo(long id) {
		ModelAndView mav = new ModelAndView();
		try {
			EmployeePo employee = employeeService.getUser(id);
			List<Integer> roleIdList = employeeService.getRoleIdList(employee.getUserId());
			List<RoleVo> roleNameList = employeeService.getRoleName(roleIdList);
			mav.addObject("roleList", roleNameList);
			mav.setViewName("system/employee/empInfo");
			mav.addObject("emp", employee);
		} catch (Exception e) {
			LOGGER.error("查询用户信息异常", e);

		}		
		return mav;
	}
	@RequestMapping("passwordPage")
	@ResponseBody
    public ModelAndView passwordPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("system/employee/password");
		//mav.addObject("userId", userId);
		return mav;
	}
	/**
	 * 修改密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("modifyPassWord")
	@ResponseBody
    public AjaxResultVo password(String oldPwd,String newPwd) {
		AjaxResultVo result = new AjaxResultVo();		
		boolean isTrue = employeeService.findPasswordById(ShiroUtils.getSessionUser().getId().intValue(),oldPwd);
		if(isTrue){
			try {
				employeeService.modifyPassword(newPwd,ShiroUtils.getSessionUser().getId().intValue(),ShiroUtils.getSessionUser().getId().intValue());
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("修改密码异常", e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}			
		}else{
			
			result.setStatusCode(StatusCode.NO_RESULT);
			result.setMessage("原密码输入不正确");
			result.setData(null);
		}
		return result;
	}
	/**
	 * 初始化密码
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("initPassword")
	@ResponseBody
    public AjaxResultVo initPassword(Long id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			userService.updateEvelopersReset(id);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("重置密码异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 删除user
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("deleteUser")
	@ResponseBody
    public AjaxResultVo deleteUser(String ids) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			employeeService.deleteUser(ids);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("删除用户异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
					
		return result;
	}
	/**
	 * 员工修改信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("editInfo")
    public ModelAndView editInfo() {
		
		ModelAndView mav =new ModelAndView();
		mav.setViewName("system/employee/empInfo");
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId",ShiroUtils.getSessionUser().getId());
			EmployeePo employee = employeeService.getBy(map);
			List<Integer> roleIdList = employeeService.getRoleIdList(employee.getUserId());
			List<RoleVo> roleNameList = employeeService.getRoleName(roleIdList);
			mav.addObject("roles", roleNameList);
			mav.addObject("emp", employee);

		} catch (Exception e) {
			LOGGER.error("查寻登陆用户异常", e);
			
		}

		return mav;
	}
	
	
	
}

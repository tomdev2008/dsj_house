package com.dsj.data.web.system.department;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.common.web.BaseController;
import com.dsj.data.shiro.realm.MyRealm;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.system.po.DepartmentPo;
import com.dsj.modules.system.service.DepartmentService;
import com.dsj.modules.system.vo.EasyuiSelectTreeVo;

/**
 * 部门商管理
 */
@Controller
@RequestMapping(value = "back/**/system/department")
public class DepartmentController extends BaseController {
	private final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	@Autowired
	DepartmentService departmentService;
	
	@Autowired
	MyRealm myRealm;

	/**
	 * 部门列表页面
	 * 
	 * @return
	 */
	@RequestMapping("department_list")
	public String toEvelopersList() {
		/*if(ShiroUtils.getSessionUser()!=null){
			myRealm.removeUserAuthorizationInfoCache(ShiroUtils.getSessionUser().getUsername());
		}*/
		return "system/department/department_list";
	}

	/**
	 * 部门列表
	 * 
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> adminList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		PageBean page = null;
		try {
			page = departmentService.listPageTree(pageParam, requestMap);

		} catch (Exception e) {
			LOGGER.error("部门查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	/**
	 * 添加页面
	 * 
	 * @param repsonse
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("department_add")
	public String departmentAdd(ServletResponse repsonse, Model model, ServletRequest request) {
		return "system/department/department_add";
	}

	/**
	 * 修改页面
	 * 
	 * @param repsonse
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("department_edit")
	public String departmentEdit(Long id, ServletResponse repsonse, Model model, ServletRequest request) {
		DepartmentPo department = departmentService.getById(id);
		model.addAttribute("department", department);
		return "system/department/department_add";
	}

	/**
	 * 下拉树
	 * 
	 * @param repsonse
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("tree/list")
	@ResponseBody
	public List<EasyuiSelectTreeVo> treeList(Long id, ServletResponse repsonse, Model model, ServletRequest request) {
		List<EasyuiSelectTreeVo> trees = new ArrayList<EasyuiSelectTreeVo>();
		trees = departmentService.getEasyuiSelectTrees(id);
		return trees;
	}

	@RequestMapping("department_saveOrUpdate")
	@ResponseBody
	public AjaxResultVo experienceAdd(DepartmentPo po) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		if (po.getParentId() == null) {
			po.setParentId(0l);
		} else {
			DepartmentPo dep1 = departmentService.getById(po.getParentId());
			po.setPrefix(String.valueOf(po.getParentId()));
			if (po!=null&& dep1!=null&&dep1.getParentId() != null & dep1.getParentId() != 0) {
				DepartmentPo dep2 = departmentService.getById(dep1.getParentId());
				po.setPrefix(String.valueOf(dep2.getId()) + "," + dep1.getId());
			}
		}
		try {
			if (po.getId() == null) {
				po.setCreateTime(new Date());
				po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());

				departmentService.saveDynamic(po);
			} else {
				departmentService.updateDynamic(po);
			}
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("部门保存修改错误", e);
		}
		return ajaxVo;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @param repsonse
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("delete")
	@ResponseBody
	public AjaxResultVo delete(Long id, ServletResponse repsonse, Model model, ServletRequest request) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		try {
			DepartmentPo department = new DepartmentPo();
			department.setDeleteFlag(DeleteStatusEnum.DEL.getValue());
			department.setId(id);
			departmentService.updateDynamic(department);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("部门删除错误", e);
		}
		return ajaxVo;
	}

}

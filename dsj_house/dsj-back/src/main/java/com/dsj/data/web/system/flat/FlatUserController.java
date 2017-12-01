package com.dsj.data.web.system.flat;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.DownloadOtherUtil;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.system.enums.UserStatusEnum;
import com.dsj.modules.system.po.FlatUserPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.FlatUserService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.EvelopersVo;
import com.dsj.modules.system.vo.FlatUserVo;

/**
 * 品牌公寓账号管理
 * 
 * @author wangjl
 * @since 2017-06-15
 * @version V1.0 
 */
@Controller
@RequestMapping(value = "back/**/flat")
public class FlatUserController {

	private final Logger logger = LoggerFactory
			.getLogger(FlatUserController.class);

	@Autowired
	private FlatUserService flatUserService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private UserService userService;
	
	
	@RequestMapping({"to_flat_list",""})
	public String toFlatList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/flat/flat_list";
	}
	
	@RequestMapping({"to_flat_audit_list","audit"})
	public String toAuditFlatList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/flat/flat_audit_list";
	}
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageFlatList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		PageBean page = null;
		try {
			page = flatUserService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			logger.error("开发商账号查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("to_flat_add")
	public String toFlatAdd(HttpServletRequest request, FlatUserVo vo) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
		} catch (Exception e) {
			logger.error("地区查询异常", e);
		}
		request.setAttribute("firstAreaList", firstAreaList);
		return "system/flat/flat_add";
	}
	
	@RequestMapping("flat_add")
	@ResponseBody
	public AjaxResultVo flatAdd(HttpServletRequest request, FlatUserVo vo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			vo.setCreatePerson(ShiroUtils.getSessionUser().getId());
			vo.setUpdatePerson(ShiroUtils.getSessionUser().getId());
			flatUserService.saveFlatUserAdd(vo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("修改品牌公寓账号异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("to_flat_update")
	public String goto_flat_update(Model model,Long id) {
		FlatUserVo flatUserVo = flatUserService.getVoById(id);
		model.addAttribute("flatUserVo", flatUserVo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		List<AreaPo> twoAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
			map.put("parentId", flatUserVo.getProvinceCode());
			twoAreaList = areaService.listBy(map);
		} catch (Exception e) {
			logger.error("地区查询异常", e);
		}
		model.addAttribute("firstAreaList", firstAreaList);
		model.addAttribute("twoAreaList", twoAreaList);
		return "system/flat/flat_update";
	}
	
	@RequestMapping("flat_update")
	@ResponseBody
	public AjaxResultVo toFlatUpdate(FlatUserVo vo,FlatUserPo po) throws Exception {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			vo.setUpdatePerson(ShiroUtils.getSessionUser().getId());
			flatUserService.saveFlatUserUpdate(vo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("修改品牌公寓账号异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("to_flat_audit")
	public String to_flat_audit(Model model,Long id) {
		FlatUserVo flatUserVo = flatUserService.getVoById(id);
		model.addAttribute("flatUserVo", flatUserVo);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", 0);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = null;
		List<AreaPo> twoAreaList = null;
		try {
			firstAreaList = areaService.listBy(map);
			map.put("parentId", flatUserVo.getProvinceCode());
			twoAreaList = areaService.listBy(map);
		} catch (Exception e) {
			logger.error("地区查询异常", e);
		}
		model.addAttribute("firstAreaList", firstAreaList);
		model.addAttribute("twoAreaList", twoAreaList);
		return "system/flat/flat_audit";
	}
	
	@RequestMapping("flats_audit_yes")
	@ResponseBody
	public AjaxResultVo flatsAuditYes(String ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersAudit(ids, UserStatusEnum.YES.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("品牌公寓审核通过异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("flats_audit_no")
	@ResponseBody
	public AjaxResultVo flatsAuditNo(String ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersAudit(ids, UserStatusEnum.NO.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("品牌公寓审核拒绝异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	@RequestMapping("flat_reset")
	@ResponseBody
	public AjaxResultVo flatReset(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersReset(id);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("密码重置异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("flat_delete")
	@ResponseBody
	public AjaxResultVo flatDelete(String ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			userService.updateEvelopersDeleteFlag(ids, DeleteStatusEnum.DEL.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("品牌公寓账号删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("export")
	@ResponseBody
	public void export(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fullFileName = request.getSession().getServletContext().getRealPath("/") + "\\WEB-INF\\classes\\docx\\";
		DownloadOtherUtil.downLoadFile(ConfigUtils.instance.getBucket(), "docx", fullFileName, response);
	}
	
	private List<AreaPo> findAreas(int parentId) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<AreaPo> list = null;
		try {
			map.put("parentId", parentId);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			list = areaService.listBy(map);
		} catch (Exception e) {
			logger.error("地区查询异常", e);
		}
		return list;
	}
	
}


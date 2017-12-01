package com.dsj.data.web.im.bind;

import java.util.Collections;
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
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.im.po.IMDirectoryPo;
import com.dsj.modules.im.service.IMDirectoryService;
import com.dsj.modules.im.vo.IMDirectoryVo;
import com.dsj.modules.newhouse.enums.NewHouseEditEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.other.service.GroupTypeService;

/**
 * 楼盘IM管理
 */
@Controller
@RequestMapping(value = "back/**/im/directory")
public class IMDirectoryController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMDirectoryController.class);

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	@Autowired
	private IMDirectoryService imDirectoryService;

	@Autowired
	private GroupTypeService groupTypeService;
	
	@RequestMapping({ "im_list", "" })
	public String bindStatusList(Model model) {
		Map<String, Object> bindStatus = new HashMap<String, Object>();
		bindStatus.put("0", "未绑定");
		bindStatus.put("1", "已绑定");
		model.addAttribute("bindStatus", bindStatus);
		return "im/directory/directory_bind_list";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> directoryPageList(ServletResponse repsonse, 
			Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("edit", NewHouseEditEnum.YES.getValue());
		Integer pageNumber = Integer
				.parseInt((String)requestMap.get("iDisplayStart"));
		Integer pageSize = Integer
				.parseInt((String)requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService
					.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房列表查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("directory_bind")
	public String directoryBind(Model model, Long id) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("houseId", id);
		List<IMDirectoryVo> voList = imDirectoryService.listVoBy(requestMap);
		if (CollectionUtils.isEmpty(voList)) {
			for (int i = 1; i < 11; i++) { 
				IMDirectoryVo vo = new IMDirectoryVo();
				vo.setHouseId(id);
				vo.setIsDuty(2);
				vo.setPosition(i);
				voList.add(vo);
			}
		} else {
			for (int i = 0, j = 0;i < 10; i++) { 
				if (j < voList.size() && voList.get(j).getPosition() == i + 1) {
					j++;
					continue;
				}
				IMDirectoryVo vo = new IMDirectoryVo();
				vo.setHouseId(id); 
				vo.setIsDuty(2);
				vo.setPosition(i + 1);
				voList.add(vo);
			}
		}
		model.addAttribute("voList", voList);
		return "im/directory/directory_bind";
	}
	
	@RequestMapping("bind_save")
	@ResponseBody
	public AjaxResultVo bindSave(IMDirectoryPo pcAgentAddPo){
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			paramMap.put("houseId", pcAgentAddPo.getHouseId());
			paramMap.put("agentId", pcAgentAddPo.getAgentId());
			IMDirectoryPo po = imDirectoryService.getBy(paramMap);
			if (po != null) {
				if (pcAgentAddPo.getId() == null 
						|| pcAgentAddPo.getId() != po.getId()) {
					ajax.setStatusCode(StatusCode.EDIT_DATE_ERROR);
					ajax.setMessage("此经纪人IM已被绑定");
					LOGGER.error("此经纪人IM已被绑定");
					return ajax;
				}
			}
			
			if (pcAgentAddPo.getId() != null) {
				pcAgentAddPo.setUpdatePerson(ShiroUtils.getSessionUser().getId()
						.intValue());
				imDirectoryService.updateDynamic(pcAgentAddPo);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newHouseIds", pcAgentAddPo.getHouseId());
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			} else {
				Long count = imDirectoryService
						.getIMDirectoryCount(pcAgentAddPo.getHouseId());
				if (count == 0) {
					paramMap.clear();
					paramMap.put("code", pcAgentAddPo.getHouseId());
					List<NewHouseDirectoryAuthPo> newDirectoryList 
						= newHouseDirectoryAuthService.listBy(paramMap);
					if (!CollectionUtils.isEmpty(newDirectoryList)) {
						for (NewHouseDirectoryAuthPo dirPo : newDirectoryList) {
							dirPo.setIsBind(1);
							newHouseDirectoryAuthService.updateDynamic(dirPo);
						}
					}
				}
				pcAgentAddPo.setCreatePerson(ShiroUtils.getSessionUser().getId()
						.intValue());
				pcAgentAddPo.setUpdatePerson(ShiroUtils.getSessionUser().getId()
						.intValue());
				pcAgentAddPo.setIsDuty(0);
				imDirectoryService.saveDynamic(pcAgentAddPo);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newHouseIds", pcAgentAddPo.getHouseId());
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("添加楼盘绑定经纪人信息异常", e);
		}
		return ajax;
	}
	
	@RequestMapping("bind_sort")
	@ResponseBody
	public AjaxResultVo bindSort(@RequestBody List<IMDirectoryPo> list){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (!CollectionUtils.isEmpty(list)) {
				list.removeAll(Collections.singleton(null));
				for (IMDirectoryPo po : list) {
					po.setUpdatePerson(ShiroUtils.getSessionUser().getId()
							.intValue());
					imDirectoryService.updateDynamic(po);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改楼盘绑定经纪人顺序异常", e);
		}
		return ajax;
	}
	
	@RequestMapping("bind_add")
	public String bindAdd(Model model, Long id, Long houseId, 
			String position) {
		int pos = 0;
		IMDirectoryVo vo = null;
		if (id != null) {
			vo = imDirectoryService.getVoById(id);
		}
		if (vo == null) {
			vo = new IMDirectoryVo();
			vo.setHouseId(houseId);
			if (StringUtils.isNotEmpty(position)) {
				try {
					pos = Integer.parseInt(position);
					vo.setPosition(pos);
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
			}
		}
		model.addAttribute("vo", vo);
		return "im/directory/directory_bind_add";
	}
	
	@RequestMapping("bind_delete")
	@ResponseBody
	public AjaxResultVo bindDelete(Long id){
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		try {
			if (id != null) {
				IMDirectoryPo po = imDirectoryService.getById(id);
				if (po != null) {
					imDirectoryService.deleteById(id);
					Long count = imDirectoryService
							.getIMDirectoryCount(po.getHouseId());
					if (count == 0) {
						paramMap.put("code", po.getHouseId());
						List<NewHouseDirectoryAuthPo> newDirectoryList 
							= newHouseDirectoryAuthService.listBy(paramMap);
						if (!CollectionUtils.isEmpty(newDirectoryList)) {
							for (NewHouseDirectoryAuthPo dirPo : newDirectoryList) {
								dirPo.setIsBind(0);
								newHouseDirectoryAuthService.updateDynamic(dirPo);
							}
						}
					}
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("newHouseIds", po.getHouseId());
					newHouseDirectoryAuthService.saveNewHouseToSolr(map);
				}
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("删除楼盘绑定经纪人信息失败", e);
		}
		return ajax;
	}

}

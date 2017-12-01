package com.dsj.data.web.agent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.AgentNewsEnum;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.evaluate.service.AgentDailyScoreService;
import com.dsj.modules.newhouse.enums.NewHouseAuthStatusEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;


@Controller
@RequestMapping(value = "back/**/houseNews")
public class HouseNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseNewsController.class);
	
	@Autowired
	private HouseNewsService houseNewsservice; 
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService; 
	
	@Autowired
	private AgentService agentService;
	@Autowired
	private AgentDailyScoreService agentDailyScoreService;
	
	int pageSize = 5;
	
	//楼盘动态查询
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("createUser", ShiroUtils.getSessionUser().getId());
		PageBean page = null;
		try {
			page = houseNewsservice.listHouseNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//业内楼盘动态查询
	@RequestMapping("getIndustryNews")
	@ResponseBody
	public PageDateTable<?> getIndustryNews(
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		//查询业内动态的条件限制
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("upDownLine", HouseNewsStatusEnum.UP.getValue());
		requestMap.put("userType", UserType.AGENT.getValue());
		
		PageBean page = null;
		try {
			page = houseNewsservice.listIndustryNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("业内动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("to_house_news_form")
	@ResponseBody
    public AjaxResultVo toHouseNewsForm(Long id) { 
		AjaxResultVo result = new AjaxResultVo();
		Map<String, Object> resData = new HashMap<>();
		try {
			
			if (id != null) {
				HouseNewsPo houseNewsPo = houseNewsservice.getById(id);
				
				resData.put("houseNewsPo", houseNewsPo);
			}
			Map<String, Object> paraMap = new HashMap<>();
			paraMap.put("agentId", ShiroUtils.getSessionUser().getId());
			List<NewHouseDirectoryAuthPo> authList = 
					newHouseDirectoryAuthService.getAuthHousesByAgentId(paraMap);
			resData.put("authList", authList);
			result.setData(resData);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("跳转楼盘动态异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

	@RequestMapping("addHouseNews")
	@ResponseBody
    public AjaxResultVo addHouseNews(HouseNewsPo houseNews) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			if (houseNews.getId()==null) {
				houseNews.setType(UserType.AGENT.getValue());
				houseNews.setAuditStatus(HouseNewsStatusEnum.UN_AUDIT.getValue());
				houseNews.setCreateUser(ShiroUtils.getSessionUser().getId().intValue());
				houseNews.setCreateTime(new Date());
				houseNews.setUpdateTime(new Date());
				houseNews.setDelFlag(DeleteStatusEnum.NDEL.getValue());
				houseNews.setUpDownLine(HouseNewsStatusEnum.NONE.getValue());
				Long id = houseNewsservice.saveDynamic(houseNews);
				map.put("newHouseIds", id);
				
				try {
					//评价体系
					AgentVo vo = agentService.getScoreVoById(ShiroUtils.getSessionUser().getId());
					agentDailyScoreService.addDailyBusinessScore
						(vo.getAgentCode().longValue(), vo.getName(), 
							Long.parseLong(vo.getCity()), vo.getCityName(), 79305, DateUtils.formatDate(new Date()), null);
				} catch (Exception e) {
					LOGGER.error("新增评价体系异常" ,e);
				}
				
			}else {
				houseNewsservice.updateDynamic(houseNews);
				HouseNewsPo pohouseNews = houseNewsservice.getById(houseNews.getId());
				pohouseNews.setUpdateTime(new Date());
				if (pohouseNews.getAuditStatus() == HouseNewsStatusEnum.AUDIT_REFUSE.getValue()) {
					pohouseNews.setAuditStatus(HouseNewsStatusEnum.UN_AUDIT.getValue());
				}
				houseNewsservice.updateDynamic(pohouseNews);
				map.put("newHouseIds", houseNews.getId());
			}
			//newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("经纪人新增或修改楼盘动态异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//删除动态
	@RequestMapping("delNews")
	@ResponseBody
    public AjaxResultVo delNews(@RequestParam("ids[]")String[] ids) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			houseNewsservice.updateDeleteFlag(ids,DeleteStatusEnum.DEL.getValue());
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("经纪人楼盘动态删除异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//下线动态
	@RequestMapping("downNews")
	@ResponseBody
    public AjaxResultVo downNews(@RequestParam("ids[]")String[] ids) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			houseNewsservice.updateLineFlag(ids,HouseNewsStatusEnum.DOWN.getValue());
			
			for (String houseNewsId : ids) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("newHouseIds", houseNewsId);
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			}
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("经纪人楼盘动态删除异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//置顶动态
	@RequestMapping("set_stick")
	@ResponseBody
    public AjaxResultVo stick(long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("stick", AgentNewsEnum.NOT_STICK.getCode());
			map.put("agentId", ShiroUtils.getSessionUser().getId());
			houseNewsservice.updateRemoveStick(map);
			map.put("id",id);
			map.put("stick", AgentNewsEnum.STICK.getCode());
			houseNewsservice.updateAddStick(map);
			
			HouseNewsPo houseNewsPo = houseNewsservice.getById(id);
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("newHouseIds", houseNewsPo.getHouseId());
			newHouseDirectoryAuthService.saveNewHouseToSolr(map1);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//取消置顶动态
	@RequestMapping("set_un_stick")
	@ResponseBody
    public AjaxResultVo unStick(long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",id);
			map.put("stick", AgentNewsEnum.NOT_STICK.getCode());
			houseNewsservice.updateAddStick(map);
			
			HouseNewsPo houseNewsPo = houseNewsservice.getById(id);
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			map1.put("newHouseIds", houseNewsPo.getHouseId());
			newHouseDirectoryAuthService.saveNewHouseToSolr(map1);
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

}

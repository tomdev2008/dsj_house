package com.dsj.data.web.dynamic;

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
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.comment.enums.AgentNewsEnum;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.service.EvelopersService;


@Controller
@RequestMapping(value = "back/houseNews")
public class HouseNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseNewsController.class);
	
	@Autowired
	private HouseNewsService houseNewsservice; 
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService; 
	
	@Autowired
	private EvelopersService evelopersService;
	
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		int pageSize = 5;
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		
		if(requestMap.get("pageSize")!=null){
			pageSize = Integer.parseInt((String) requestMap.get("pageSize"));
		}
		
		
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("createUser", ShiroUtils.getSessionUser().getId());
		PageBean page = null;
		try {
			page = houseNewsservice.listDeveloperHouseNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("开发商人动态查询异常", e);
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
			
			EvelopersPo epo= evelopersService.getById(ShiroUtils.getSessionUser().getEveloperId());
			
			if(StringUtils.isNotBlank(epo.getLoupanName())){
				Map<String,Object> paramMap=new HashMap<String,Object>();
				paramMap.put("ids", epo.getLoupanName());
				List<NewHouseDirectoryAuthPo> authList = 
					newHouseDirectoryAuthService.listBy(paramMap);
				resData.put("authList", authList);
			}
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
			if (houseNews.getId()==null) {
				houseNews.setType(UserType.DEVELOPER.getValue());
				houseNews.setAuditStatus(HouseNewsStatusEnum.UN_AUDIT.getValue());
				houseNews.setCreateUser(ShiroUtils.getSessionUser().getId().intValue());
				houseNews.setCreateTime(new Date());
				houseNews.setUpdateTime(new Date());
				houseNews.setDelFlag(DeleteStatusEnum.NDEL.getValue());
				houseNews.setUpDownLine(HouseNewsStatusEnum.NONE.getValue());
				houseNewsservice.saveDynamic(houseNews);
			}else {
				houseNews.setUpdateTime(new Date());
				houseNews.setAuditStatus(HouseNewsStatusEnum.UN_AUDIT.getValue());
				houseNewsservice.updateDynamic(houseNews);
			}
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("开发商新增或修改楼盘动态异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//删除
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
	

	//上下线动态
	@RequestMapping("upDownLine")
	@ResponseBody
    public AjaxResultVo upDownLine(long id,Integer upDownLine) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			HouseNewsPo houseNews=new HouseNewsPo();
			houseNews.setId(id);
			houseNews.setUpDownLine(upDownLine);
			houseNews.setUpdateTime(new Date());
			houseNewsservice.updateDynamic(houseNews);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("开发商上下线异常" ,e);
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
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	//置顶动态
	@RequestMapping("set_un_stick")
	@ResponseBody
    public AjaxResultVo unStick(long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("id",id);
			map.put("stick", AgentNewsEnum.NOT_STICK.getCode());
			houseNewsservice.updateAddStick(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询用户异常" ,e);
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

}

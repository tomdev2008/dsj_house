package com.dsj.data.web.newHouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentDirectoryVo;
import com.dsj.modules.system.vo.FindVo;

@Controller
@RequestMapping(value = "agent/back/**/newHouse/responsible")
public class ResponsibleController {
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseController.class);
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private NewHouseTypeAuthService newHouseTypeAuthService;
	
	private static Integer pageSize=9;
	@RequestMapping("newHouseResponsibleList")
	@ResponseBody
	public Map<String,Object> newHouseResponsibleList(FindVo findVo, Model model, ServletRequest request
			) {
        Map<String, Object> paramMap=new HashMap<>();
		Map<String, Object> requestMap = new HashMap<String, Object>();
		int agentId=ShiroUtils.getSessionUser().getId().intValue();
		requestMap.put("status", findVo.getStatus());
		requestMap.put("authStatus", findVo.getAuthStatus());
		requestMap.put("isTure", findVo.getIsTure());
		requestMap.put("agentId", agentId);
		requestMap.put("name", findVo.getName());
		Integer pageNumber=1;
		if(findVo.getPage()!=null){
			 pageNumber = findVo.getPage();
		}
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listAgentNewHouseResponPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("新房列表查询异常", e);
		}
		//上架楼盘
		List<NewHouseDirectoryAuthVo> data=(List<NewHouseDirectoryAuthVo>) page.getRecordList();
		paramMap.put("totalPage", page.getPageCount());
		paramMap.put("data", data);
		return paramMap;
	}
	
	/**
	 * 下架
	 */
	@RequestMapping("shelves")
	@ResponseBody
	public AjaxResultVo shelves(Long newHouseId){
		AjaxResultVo ajax=new AjaxResultVo();
		try {
			 NewHouseDirectoryAuthVo  newHouseDirectory=new NewHouseDirectoryAuthVo();
			 newHouseDirectory.setIsTure(NewHouseIsTrueEnum.DOWN.getValue());
			 newHouseDirectory.setId(newHouseId);
			 int result=newHouseDirectoryAuthService.updateDynamic(newHouseDirectory);
			 if(result>0){
		    	 ajax.setStatusCode(StatusCode.SUCCESS);
		    	 ajax.setMessage("下架成功");
		     }else{
		    	 ajax.setStatusCode(StatusCode.SERVER_ERROR);
		    	 ajax.setMessage("下架失败");
		     }
		} catch (Exception e) {
          LOGGER.info("系统错误",e.getMessage(),e);
          ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

}

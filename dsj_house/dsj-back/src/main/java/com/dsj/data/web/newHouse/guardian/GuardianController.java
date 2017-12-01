package com.dsj.data.web.newHouse.guardian;

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

import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.im.service.IMDirectoryService;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.system.enums.AgentStatus;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.EvelopersService;

@Controller
@RequestMapping(value = "back/**/newHouse/guardian")
public class GuardianController {
	private final Logger LOGGER = LoggerFactory.getLogger(GuardianController.class);

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private EvelopersService evelopersService;
	@Autowired
	private IMDirectoryService iMDirectoryService;
	/**
	 * 进入楼盘管理人页面
	 * @return
	 */
	@RequestMapping("newHouse_guardian")
	public String newHouse_developer() {
		return "newHouse/guardian/newHouse_guardian";
	}
	
	/**
	 * 查询楼盘管理人信息
	 * 
	 * @return
	 */
	@RequestMapping("findDeveloper")
	@ResponseBody
	public PageDateTable<?> findDeveloper(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
//		requestMap.put("is_ture", NewHouseIsTrueEnum.UP.getValue());
//		requestMap.put("not_ture", NewHouseIsTrueEnum.DOWN.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listDirectoryPage(pageParam, requestMap);
			List<NewHouseDirectoryAuthVo> roleIdList = (List<NewHouseDirectoryAuthVo>) page.getRecordList();
			for (int i = 0; i < roleIdList.size(); i++) {
				NewHouseDirectoryAuthVo newhouse = roleIdList.get(i);
				if (newhouse.getIsTure().equals(NewHouseIsTrueEnum.UP.getValue())) {
					newhouse.setShelves("已上架");
				} else if(newhouse.getIsTure().equals(NewHouseIsTrueEnum.NOUP.getValue())){
					newhouse.setShelves("未上架");
				}else if(newhouse.getIsTure().equals(NewHouseIsTrueEnum.DOWN.getValue())){
					newhouse.setShelves("已下架");
				}

			}

		} catch (Exception e) {
			LOGGER.info("新房楼盘维护人管理查询错误", e.getMessage(), e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 进入经纪人设置跳转页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("toSetUp")
	public String toSetUp(Long id, Model model) {
		model.addAttribute("id", id);
		return "newHouse/guardian/toSetUp";
	}

	@RequestMapping("findAgrentList")
	@ResponseBody
	public PageDateTable<?> findAgrentList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("auditStatus", AgentStatus.AUDIT_SUCCESS.getCode());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = agentService.listAgentPage(pageParam, requestMap);

		} catch (Exception e) {
			LOGGER.info("已认证经纪人查询错误", e.getMessage(), e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	/**
	 * 经纪人设置楼盘维护人绑定
	 * @param id
	 * @param agentId
	 * @return
	 */
	@RequestMapping("checkOkHouse")
	@ResponseBody
	public AjaxResultVo checkOkHouse(Long userId, Long houseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			int result = newHouseDirectoryAuthService.updateNewHouse(houseId, userId);
			int personId=ShiroUtils.getSessionUser().getId().intValue();
			iMDirectoryService.addIMDirectory(houseId, userId, personId, 1);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("newHouseIds", houseId);
			newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			if (result > 0) {
				ajax.setStatusCode(StatusCode.SUCCESS);
			} else {
				ajax.setStatusCode(StatusCode.ROLE_HAD);
			}
		} catch (Exception e) {
			LOGGER.info(CommConst.QUERY_DATA_ERROR, e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
  
	/**
	 * 经纪人解绑楼盘维护人
	 * @param id
	 * @return
	 */
	@RequestMapping("unboundNewHouse")
	@ResponseBody
	public AjaxResultVo unboundNewHouse(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			int personId=ShiroUtils.getSessionUser().getId().intValue();
			NewHouseDirectoryAuthPo newHouseDirectoryAuthPo=newHouseDirectoryAuthService.getById(id);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("newHouseIds", id);
			newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			iMDirectoryService.addIMDirectory(id, newHouseDirectoryAuthPo.getMaintainPerson(), personId, 0);
			int result = newHouseDirectoryAuthService.updateNewHouseUn(id);
			if (result > 0) {
				ajax.setStatusCode(StatusCode.SUCCESS);
			} else {
				ajax.setStatusCode(StatusCode.ROLE_HAD);
			}
		} catch (Exception e) {
			LOGGER.info(CommConst.QUERY_DATA_ERROR, e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/**
	 * 进入开发商楼盘设置页面
	 * 
	 * @return
	 */
	@RequestMapping("newHouse_evelopers")
	public String newHouse_evelopers() {
		return "newHouse/guardian/newHouse_evelopers";
	}
	
	
	@RequestMapping("findEveloper")
	@ResponseBody
	public PageDateTable<?> findEveloper(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
//		requestMap.put("is_ture", NewHouseIsTrueEnum.UP.getValue());
//		requestMap.put("not_ture", NewHouseIsTrueEnum.DOWN.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);
		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listEveloperCountPage(pageParam, requestMap);
			List<NewHouseDirectoryAuthVo> roleIdList = (List<NewHouseDirectoryAuthVo>) page.getRecordList();
			for (int i = 0; i < roleIdList.size(); i++) {
				NewHouseDirectoryAuthVo newhouse = roleIdList.get(i);
				if (newhouse.getIsTure().equals(NewHouseIsTrueEnum.UP.getValue())) {
					newhouse.setShelves("上架");
				} else {
					newhouse.setShelves("下架");
				}

			}

		} catch (Exception e) {
			LOGGER.info("开发商管理查询错误", e.getMessage(), e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	
	@RequestMapping("toSetUpEvelopers")
	public String toSetUpEvelopers(Long id, Model model) {
		model.addAttribute("id", id);
		return "newHouse/guardian/toSetUpEvelopers";
	}
	
	/**
	 * 查询开发商
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("findEvelopersList")
	@ResponseBody
	public PageDateTable<?> findEvelopersList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("status", 2);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = evelopersService.listEvelopersPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("开发商查询错误", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 开发商绑定
	 * @param id
	 * @param evelopersId
	 * @return
	 */
	@RequestMapping("checkOkEvelopers")
	@ResponseBody
	public AjaxResultVo checkOkEvelopers(Long id, Long evelopersId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			int result = newHouseDirectoryAuthService.updateEvelopers(evelopersId, id);
			if (result > 0) {
				ajax.setStatusCode(StatusCode.SUCCESS);
			} else {
				ajax.setStatusCode(StatusCode.ROLE_HAD);
			}
			EvelopersPo evelo=evelopersService.getEveloper(id);
			String loupanName=evelo.getLoupanName();
			if(loupanName.equals("")){
				loupanName=evelopersId.toString();
			}else{
			if(loupanName.contains(evelopersId.toString())){
				
			}else{
				String[] loupan=loupanName.split(",");
				if(loupan.length==1){
					loupanName=loupanName+","+evelopersId.toString();
				}else{
				loupanName=loupanName+","+evelopersId.toString();
				}
			}
			}
			Map<String, Object> paramMap=new HashMap<>();
			paramMap.put("userId", id);
			paramMap.put("loupanName", loupanName);
			evelopersService.updateEveloper(paramMap);
		} catch (Exception e) {
			LOGGER.info(CommConst.QUERY_DATA_ERROR, e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 开发商解绑
	 * @param id
	 * @return
	 */
	@RequestMapping("unboundEveloper")
	@ResponseBody
	public AjaxResultVo unboundEveloper(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		NewHouseDirectoryAuthPo newHosue=newHouseDirectoryAuthService.getById(id);
		try {
			int result = newHouseDirectoryAuthService.updateEveloper(id);
			if (result > 0) {
				ajax.setStatusCode(StatusCode.SUCCESS);
			} else {
				ajax.setStatusCode(StatusCode.ROLE_HAD);
			}
			Long evelo=newHosue.getEvelopersPerson();
			EvelopersPo eve=evelopersService.getEveloper(evelo);
			String loupanName=eve.getLoupanName();
			if(loupanName.equals("")){
				
			}else{
			if(loupanName.contains(id.toString())){
				String[] loupan=loupanName.split(",");
				String loupanNames="";
				for(int i=0;i<loupan.length;i++){
					 if(loupan[i].equals(id.toString())){
						 
					 }else{
						 loupanNames+=loupan[i]+",";
					 }
				}
				if(loupan.length==1){
					loupanName="";
				}else{
				loupanName=loupanNames.substring(0, loupanNames.length()-1);
				}
			 }
			}
			Map<String, Object> paramMap=new HashMap<>();
			paramMap.put("userId", eve.getUserId());
			paramMap.put("loupanName", loupanName);
			evelopersService.updateEveloper(paramMap);
		} catch (Exception e) {
			LOGGER.info(CommConst.QUERY_DATA_ERROR, e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

}

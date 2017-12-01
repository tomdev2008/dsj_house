package com.dsj.data.web.im;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.im.po.IMContactPo;
import com.dsj.modules.im.service.IMContactService;
import com.dsj.modules.im.service.IMDirectoryService;
import com.dsj.modules.im.vo.IMDirectoryVo;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.UserVo;

/**
 * IM用户管理
 */
@Controller
@RequestMapping(value = "front/im/user")
public class IMUserController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMUserController.class);

	@Autowired
	private EasemobUserService easemobUserService;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	@Autowired
	private OldHouseMasterService oldHouseMasterService;

	@Autowired
	private IMDirectoryService imDirectoryService;
	
	@Autowired
	private IMContactService imContactService;
	
	@Autowired
	private MobileService mobileService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private UserService userService;

	@RequestMapping("list")
	@ResponseBody
	public AjaxResultVo getIMAgentList(Model model, Long userId, Long houseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		JSONObject agents = null;
		JSONObject contacts = null;
		
		try {
			if (houseId != null) {
				paramMap.put("houseId", houseId);
				List<IMDirectoryVo> imDirectoryList = imDirectoryService
						.listVoBy(paramMap);
				if (!CollectionUtils.isEmpty(imDirectoryList)) 
					agents = getAgentList(imDirectoryList);
			}
			
			paramMap.clear();
			paramMap.put("userId", userId);
			List<IMContactPo> imContactList = imContactService
					.listLimitBy(paramMap);
			if (!CollectionUtils.isEmpty(imContactList)) 
				contacts = getContactList(imContactList);

			paramMap.clear();
			paramMap.put("agents", agents);
			paramMap.put("contacts", contacts);
			ajax.setData(paramMap);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("获取IM绑定楼盘经纪人信息异常", e);
		}
		return ajax;
	}

	@RequestMapping("add_contact")
	@ResponseBody
	public String addIMContact(HttpServletRequest request, Model model, 
			Long userId, String username, String callback) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			if (username == null || callback == null) {
				paramMap.put("status", StatusCode.SERVER_ERROR);
				paramMap.put("message", "参数错误");
			    JSONObject jsonObject = JSONObject.fromObject(paramMap);
				LOGGER.error("参数错误");
				return callback + "(" + jsonObject.toString() + ")";
			}
			
			paramMap.put("username", username);
			UserPo user = userService.getBy(paramMap);
			if (user == null) {
				resultMap.put("status", StatusCode.SERVER_ERROR);
				resultMap.put("message", "参数错误");
			    JSONObject jsonObject = JSONObject.fromObject(resultMap);
				LOGGER.info("经纪人信息未找到");
				return callback + "(" + jsonObject.toString() + ")";
			}
			
			paramMap.clear();
			paramMap.put("userId", userId);//用户的id
			paramMap.put("agentId", user.getId());//经纪人id
			IMContactPo po = imContactService.getBy(paramMap);
			if (po == null) {
				po = new IMContactPo();
				po.setUserId(userId);
				po.setAgentId(user.getId());
				po.setCount(1);
				po.setCreatePerson(UserUtil
						.getCurrentUserLoginId(request).intValue());
				po.setUpdatePerson(UserUtil
						.getCurrentUserLoginId(request).intValue());
				imContactService.saveDynamic(po);
				//deleteMoreIMConcacts(userId);
			} else {
				po.setCount(po.getCount() + 1);
				po.setUpdatePerson(UserUtil
						.getCurrentUserLoginId(request).intValue());
				imContactService.updateDynamic(po);
			}
			resultMap.put("status", StatusCode.SUCCESS);
		} catch (Exception e) {
			resultMap.put("status", StatusCode.SERVER_ERROR);
			LOGGER.error("用户最近联系人信息添加失败", e);
		}
		JSONObject jsonObject = JSONObject.fromObject(resultMap);
		return callback + "(" + jsonObject.toString() + ")";
	}
	
	@RequestMapping("addContact")
	@ResponseBody
	public AjaxResultVo addContact(HttpServletRequest request, Model model, Long userId, 
			String username,Long newHouseId,Long oldHouserId){
		AjaxResultVo result = new AjaxResultVo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			if (username == null) {
				result.setStatusCode(StatusCode.PARAMS_ERROR);
				LOGGER.error("参数错误");
				return result;
			}
			paramMap.put("username", username);
			UserPo user = userService.getBy(paramMap);
			if (user == null) {
				result.setStatusCode(StatusCode.NO_RESULT);
				LOGGER.info("经纪人信息未找到");
				return result;
			}
			paramMap.clear();
			paramMap.put("userId", userId);//用户的id
			paramMap.put("agentId", user.getId());//经纪人id
			IMContactPo po = imContactService.getBy(paramMap);
			if (po == null) {
				po = new IMContactPo();
				po.setUserId(userId);
				po.setAgentId(user.getId());
				po.setCount(1);
				po.setCreatePerson(UserUtil
						.getCurrentUserLoginId(request).intValue());
				po.setUpdatePerson(UserUtil
						.getCurrentUserLoginId(request).intValue());
				imContactService.saveDynamic(po);
			} else {
				po.setCount(po.getCount() + 1);
				po.setUpdatePerson(UserUtil
						.getCurrentUserLoginId(request).intValue());
				imContactService.updateDynamic(po);
			}
			
			if(newHouseId != null){
				NewHouseDirectoryAuthPo newHouseDirectoryAuthPo = newHouseDirectoryAuthService.getById(newHouseId);
				paramMap.put("newHousePo", newHouseDirectoryAuthPo);
			}
			if(oldHouserId != null){
				OldHouseMasterPo oldHouseMasterPo = oldHouseMasterService.getById(oldHouserId);
				paramMap.put("oldHousePo", oldHouseMasterPo);
			}
			result.setData(paramMap);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("用户最近联系人信息添加失败", e);
		}
		return result;
	}
	
	/**
	 * 删除用户7天以前的最近联系人 
	 * 
	 * @return
	 */
	private void deleteMoreIMConcacts(Long userId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("userId", userId);
		paramMap.put("limitTime", 
				System.currentTimeMillis()/1000  - 7 * 24 * 60);
		imContactService.deleteLimitBy(paramMap);
	}

	private JSONObject getAgentList(List<IMDirectoryVo> imDirectoryList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		JSONObject json = null;
		List<UserVo> listOnline = new ArrayList<UserVo>();
		List<UserVo> listOffline = new ArrayList<UserVo>();
		
		for (IMDirectoryVo vo : imDirectoryList) { 
			UserPo user = userService.getById(vo.getAgentId());
			if (user == null) continue;
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);

			paramMap.put("agentId", vo.getAgentId());
			MobilePo po = mobileService.getBy(paramMap);
			if(po != null) userVo.setExt(po.getMobile());
			
			Object obj = easemobUserService.getEasemobUserStatus(
					user.getUsername());
			if (obj != null && obj.toString().indexOf("online") > 0) {
				userVo.setOnLine(1);
				listOnline.add(userVo);
			} else {
				userVo.setOnLine(0);
				listOffline.add(userVo);
			}
		}
		if (!CollectionUtils.isEmpty(listOnline) 
				|| !CollectionUtils.isEmpty(listOffline)) {
			json = new JSONObject();
			json.put("onlineCount", listOnline.size());
			listOnline.addAll(listOffline);
			JSONArray agentList = JSONArray.fromObject(listOnline);
			json.put("agentList", agentList);
			json.put("totalcount", listOnline.size());
		}
		return json;
	}
	
	private JSONObject getContactList(List<IMContactPo> imContactList) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		JSONObject json = null;
		List<UserVo> listOnline = new ArrayList<UserVo>();
		List<UserVo> listOffline = new ArrayList<UserVo>();
		
		for (IMContactPo vo : imContactList) { 
			UserPo user = userService.getById(vo.getAgentId());
			if (user == null) continue;
			UserVo userVo = new UserVo();
			BeanUtils.copyProperties(user, userVo);
			
			paramMap.put("userId", vo.getAgentId());
			AgentPo agent = agentService.getBy(paramMap);
			if (agent != null) {
				paramMap.clear();
				paramMap.put("agentId", agent.getId());
				MobilePo po = mobileService.getBy(paramMap);
				if(po != null) userVo.setExt(po.getMobile());
			}
			
			Date date = new Date();
			long diff = date.getTime() - vo.getUpdateTime().getTime();
			if(diff<0)diff=0;
			long days = diff/(1000*60*60*24);
			if(days==0){//当天
				SimpleDateFormat d = new SimpleDateFormat("HH:mm");
				userVo.setOpenChatTime(d.format(vo.getUpdateTime()));
			}else if(days>0 && days<=7){//大于当天小于一周
				String[] week = new String[] { "日", "一", "二", "三", "四", "五", "六" };
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(vo.getUpdateTime());
				int w = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				if(w<0)w=0;
				userVo.setOpenChatTime("星期"+week[w]);
			}else if(days>7){//大于一周
				SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd");
				userVo.setOpenChatTime(d.format(vo.getUpdateTime()));
			};
			Object obj = easemobUserService.getEasemobUserStatus(
					user.getUsername());
			if (obj != null && obj.toString().indexOf("online") > 0) {
				userVo.setOnLine(1);
				listOnline.add(userVo);
			} else {
				userVo.setOnLine(0);
				listOffline.add(userVo);
			}
		}
		
		if (!CollectionUtils.isEmpty(listOnline) 
				|| !CollectionUtils.isEmpty(listOffline)) {
			json = new JSONObject();
			json.put("onlineCount", listOnline.size());
			listOnline.addAll(listOffline);
			JSONArray agentList = JSONArray.fromObject(listOnline);
			json.put("agentList", agentList);
			json.put("totalcount", listOnline.size());
		}
		return json;
	}
}

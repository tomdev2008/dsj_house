package com.dsj.data.wap.im;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.ShiroUtils;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.im.po.IMContactPo;
import com.dsj.modules.im.service.IMContactService;
import com.dsj.modules.im.service.IMDirectoryService;
import com.dsj.modules.im.vo.IMDirectoryVo;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileService;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.UserVo;

@Controller
@RequestMapping(value = "im")
public class IMUserController {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMUserController.class);

	@Autowired
	private EasemobUserService easemobUserService;

	@Autowired
	private IMDirectoryService imDirectoryService;
	
	@Autowired
	private IMContactService imContactService;
	
	@Autowired
	private MobileService mobileService;
	
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
	public String addIMContact(Model model, Long userId, 
			Long agentMoblie, String callback) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		try {
			if (agentMoblie == null || callback == null) {
				paramMap.put("status", StatusCode.SERVER_ERROR);
				paramMap.put("message", "参数错误");
			    JSONObject jsonObject = JSONObject.fromObject(paramMap);
				LOGGER.error("参数错误");
				return callback + "(" + jsonObject.toString() + ")";
			}
			
			paramMap.put("username", agentMoblie);
			UserPo user = userService.getBy(paramMap);
			if (user == null) {
				resultMap.put("status", StatusCode.SERVER_ERROR);
				resultMap.put("message", "参数错误");
			    JSONObject jsonObject = JSONObject.fromObject(resultMap);
				LOGGER.info("经纪人信息未找到");
				return callback + "(" + jsonObject.toString() + ")";
			}
			
			paramMap.clear();
			paramMap.put("userId", userId);
			paramMap.put("agentId", user.getId());
			IMContactPo po = imContactService.getBy(paramMap);
			if (po == null) {
				po = new IMContactPo();
				po.setUserId(userId);
				po.setAgentId(user.getId());
				po.setCount(1);
				po.setCreatePerson(ShiroUtils.getSessionUser().getId()
						.intValue());
				po.setUpdatePerson(ShiroUtils.getSessionUser().getId()
						.intValue());
				imContactService.saveDynamic(po);
				deleteMoreIMConcacts(userId);
			} else {
				po.setCount(po.getCount() + 1);
//				po.setUpdatePerson(ShiroUtils.getSessionUser().getId()
//						.intValue());
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
	
}

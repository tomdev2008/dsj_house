package com.dsj.data.web.mobile.house;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.BeanToMap;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.common.web.ResponseUtils;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.mobile400.enums.MobileTypeEnum;
import com.dsj.modules.mobile400.po.MobileDetailPo;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileHistoryService;
import com.dsj.modules.mobile400.service.MobileService;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;

/**
 * 新房编辑管理
 */
@Controller
@RequestMapping(value = "back/**/mobileManager/house")
public class HouseMobileManagerController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseMobileManagerController.class);

	@Autowired
	private MobileService mobileService;

	@Autowired
	private MobileHistoryService mobileHistoryService;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;

	@RequestMapping({ "mobile_house_list", "" })
	public String mobileHouseList(Model model) {
		return "mobileManager/house/mobile_house_list";
	}

	@RequestMapping("mobile_house_binding_history")
	public String mobileHouseHistoryList(Model model, Long id) {
		model.addAttribute("id", id);
		return "mobileManager/house/mobile_house_binding_history";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("type", MobileTypeEnum.HOUSE.getValue());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = mobileService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("400楼盘管理查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("historyPage/list")
	@ResponseBody
	public PageDateTable<?> historyPageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = mobileHistoryService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("绑定历史查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	@RequestMapping("addMobile")
	@ResponseBody
	public AjaxResultVo changeMore(MobilePo po) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			mobileService.saveMobile(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号生成异常", e);
		}
		return result;
	}

	@RequestMapping("binding")
	public String binding(Model model, Long id) {
		MobilePo mobilePo = mobileService.getById(id);
		model.addAttribute("mobilePo", mobilePo);
		return "mobileManager/house/mobile_house_binding";
	}

	@RequestMapping("editBinding")
	public String updateBinding(Model model, Long id) {
		MobilePo mobilePo = mobileService.getById(id);
		model.addAttribute("mobilePo", mobilePo);
		return "mobileManager/house/mobile_house_updateBinding";
	}

	@RequestMapping("saveBinding")
	@ResponseBody
	public AjaxResultVo saveBinding(MobilePo po) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("channel",mobileService.getById(po.getId()).getChannel());
			map.put("houseId",po.getHouseId());
			List<MobilePo> list = mobileService.listBy(map);
			if(null!=list&&list.size()!=0){
				result.setStatusCode(StatusCode.SERVER_ERROR);
				result.setMessage("该楼盘已绑定此渠道对应的小号");
			}else{
				mobileService.saveBindingMobile(po);
				//楼盘绑定400.重置solr
				map.clear();
				map.put("newHouseIds", po.getHouseId());
				newHouseDirectoryAuthService.saveNewHouseToSolr(map);
				result.setStatusCode(StatusCode.SUCCESS);
			}
			
			
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号绑定异常", e);
		}
		return result;
	}

	@RequestMapping("saveUpdateBinding")
	@ResponseBody
	public AjaxResultVo saveUpdateBinding(MobilePo po) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			mobileService.saveUpdateBindingMobile(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号绑定异常", e);
		}
		return result;
	}

	@RequestMapping("removeBinding")
	@ResponseBody
	public AjaxResultVo removeBinding(Long id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			//楼盘取消绑定400.重置solr
			map.put("newHouseIds", mobileService.getById(id).getHouseId());
			mobileService.saveRemoveBindingMobile(id, ShiroUtils.getSessionUser().getId());
			newHouseDirectoryAuthService.saveNewHouseToSolr(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("小号解除异常", e);
		}
		return result;
	}
}

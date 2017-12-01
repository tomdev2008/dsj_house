package com.dsj.data.web.rentHouse;

import java.util.HashMap;
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

import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.rentHouse.RentHouseRequireController;
import com.dsj.modules.other.enums.HouseEntrustAndRequireStatus;
import com.dsj.modules.rent.service.RentHouseRequireService;

/**
 * 租房委托管理
 */
@Controller
@RequestMapping(value = "back/**/rentHouse/general/require")
public class RentHouseRequireController {
	private final Logger LOGGER = LoggerFactory.getLogger(RentHouseRequireController.class);

	@Autowired
	private RentHouseRequireService rentHouseRequireService;

	@RequestMapping({"to_require_list",""})
	public String rentHouseRequireList(Model model) {
		return "rentHouse/general/require/require_list";
	}
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> rentHouseRequireList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = rentHouseRequireService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("租房需求查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("dealRequires")
	@ResponseBody
	public AjaxResultVo dealRequires(@RequestParam("ids[]")String[] ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			rentHouseRequireService.updateRentHouseRequire(ids, HouseEntrustAndRequireStatus.YES.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("租房需求处理异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
}

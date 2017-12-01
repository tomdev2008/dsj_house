package com.dsj.data.web.oldHouse;

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
import com.dsj.data.web.newHouse.edit.NewHouseController;
import com.dsj.modules.oldhouse.service.OldHouseEntrustService;
import com.dsj.modules.other.enums.HouseEntrustAndRequireStatus;
import com.dsj.modules.system.enums.UserStatusEnum;

/**
 * 二手房委托管理
 */
@Controller
@RequestMapping(value = "back/**/oldHouseEntrust")
public class OldHouseEntrustController {
	private final Logger LOGGER = LoggerFactory.getLogger(OldHouseEntrustController.class);

	@Autowired
	private OldHouseEntrustService oldHouseEntrustService;

	@RequestMapping({"to_entrust_list",""})
	public String oldHouseEntrustList() {
		return "oldHouse/entrust/entrust_list";
	}
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> oldHouseEntrustList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = oldHouseEntrustService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("二手房委托查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("dealEntrusts")
	@ResponseBody
	public AjaxResultVo dealEntrusts(@RequestParam("ids[]") String[] ids) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			oldHouseEntrustService.updateOldHouseEntrust(ids, HouseEntrustAndRequireStatus.YES.getValue());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("二手房委托处理异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
}

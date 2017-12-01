package com.dsj.data.web.evaluate;

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
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwuserCommentService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.fw.vo.FwuserCommentVo;

/**
 * 权证商家评价管理
 */
@Controller
@RequestMapping(value = "back/**/warrant/evaluate")
public class EvaluateController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(EvaluateController.class);
	
	@Autowired
	private FwuserCommentService fwuserCommentService;
	@Autowired
	private FwOrderService fwOrderService;
	
	@RequestMapping({"evaluate_list",""})
	public String toEvelopersList(Model model) {
		return "evaluate/evaluate_list";
	}
	
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("fwuserId", ShiroUtils.getSessionUser().getPropertyId());
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = fwuserCommentService.evaluatePage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("权证评价查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//查看评价
	@RequestMapping("evaluate_check")
    public String edit(long id,Model model) { 
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderDetailId", id);
		FwOrderVo fwOrdervo = fwOrderService.getOrderDetailVoByDetailId(hashMap);
		FwuserCommentVo fwuserCommentVo= fwuserCommentService.getCommentByDetailId(hashMap);
		model.addAttribute("detailVo", fwOrdervo);
		model.addAttribute("fwuserCommentVo", fwuserCommentVo);
		return "evaluate/evaluate_checkU";
	}
	
}

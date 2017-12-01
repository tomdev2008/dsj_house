package com.dsj.data.web.order;

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

import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.fw.po.NodeFieldPo;
import com.dsj.modules.fw.po.OrderNodeAuthPo;
import com.dsj.modules.fw.po.OrderNodeJdPo;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.NodeFieldService;
import com.dsj.modules.fw.service.OrderNodeAuthService;
import com.dsj.modules.fw.service.OrderNodeDetailService;
import com.dsj.modules.fw.service.OrderNodeJdService;
import com.dsj.modules.fw.vo.FwOrderVo;

/**
 * 权证订单管理
 */
@Controller
@RequestMapping(value = "back/**/warrant/order")
public class OrderDetailController {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderDetailController.class);

	@Autowired
	private FwOrderService fwOrderService;
	
	@Autowired
	private OrderNodeJdService orderNodeJdService;
	
	@Autowired
	private NodeFieldService nodeFieldService;
	
	@Autowired
	private OrderNodeAuthService orderNodeAuthService;
	
	
	@RequestMapping("detail")
	public String detail(Long id,Model model,int type) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderDetailId", id);
		List<OrderNodeJdPo> list = orderNodeJdService.listBy(hashMap);
		FwOrderVo vo = fwOrderService.getOrderDetailVoByDetailId(hashMap);
		model.addAttribute("nodeList",list);
		model.addAttribute("detailVo",vo);
		model.addAttribute("type",type);
		return "order/detail/order_detail";
	}
	
	@RequestMapping("getNodeFields")
	@ResponseBody
	public AjaxResultVo getNodeFields(Long nodeId,Long orderDetailId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("nodeId", nodeId);
			hashMap.put("orderDetailId", orderDetailId);
			List<NodeFieldPo> list = nodeFieldService.listNewBy(hashMap);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("获取节点字段异常",e);
		}
		return ajax;
	}
	
	@RequestMapping("toGetAuthHistory")
	public String getAuthHistory(Long nodeId,Long orderDetailId,Model model) {
		model.addAttribute("nodeId",nodeId);
		model.addAttribute("orderDetailId",orderDetailId);
		return "order/detail/auth_history";
	}
	
	@RequestMapping("toGetHistory")
	public String getAuthHistory(Long orderDetailId,Model model) {
		model.addAttribute("orderDetailId",orderDetailId);
		return "order/fwuser/history";
	}
	
	@RequestMapping("getAuthHistory")
	@ResponseBody
	public PageDateTable<?> getAuthHistory(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = orderNodeAuthService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("订单审核历史查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("auth")
	@ResponseBody
	public AjaxResultVo auth(OrderNodeAuthPo authPo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			orderNodeAuthService.saveOrderNodeAuth(authPo,ShiroUtils.getSessionUser().getId(),ShiroUtils.getSessionUser().getUsername());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("节点审核异常",e);
		}
		return ajax;
	}
}

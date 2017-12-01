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
import org.springframework.web.bind.annotation.RequestBody;
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
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.NodeFieldPo;
import com.dsj.modules.fw.po.OrderNodeJdPo;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.NodeFieldService;
import com.dsj.modules.fw.service.OrderNodeAuthService;
import com.dsj.modules.fw.service.OrderNodeDetailService;
import com.dsj.modules.fw.service.OrderNodeJdService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.fw.vo.FwSkuVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;

/**
 * 权证订单管理
 */
@Controller
@RequestMapping(value = "back/**/warrant/order")
public class OrderController {

	private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private FwOrderService fwOrderService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private FwSkuService fwSkuService;
	
	@Autowired
	private OrderNodeJdService orderNodeJdService;
	
	@Autowired
	private NodeFieldService nodeFieldService;
	
	@Autowired
	private OrderNodeAuthService orderNodeAuthService;
	
	@Autowired
	private OrderNodeDetailService orderNodeDetailService;
	
	@RequestMapping("order_list")
	public String updatePassword(Model model) {
		//地区信息
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		
		//商品信息
		hashMap.clear();
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<FwSkuVo> skuList = fwSkuService.getSkuNameAndTypeId(hashMap);
		model.addAttribute("skuList", skuList);
		
		return "order/list/order_list";
	}
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("dbuserid", ShiroUtils.getSessionUser().getPropertyId());
		requestMap.put("noStatus", "1,2,3");
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = fwOrderService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("订单列表查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
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
	
	@RequestMapping("start_fw_jd")
	@ResponseBody
	public AjaxResultVo startFw(Long orderId,Long currentNodeIdLong,Long nodeId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			fwOrderService.updateFwJd(orderId,currentNodeIdLong,nodeId,ShiroUtils.getSessionUser().getId(),ShiroUtils.getSessionUser().getUsername());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("开启服务异常",e);
		}
		return ajax;
	}
	
	@RequestMapping("end_fw_jd")
	@ResponseBody
	public AjaxResultVo endFwJd(Long id,Long currentNodeId,Long orderDetailId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			//订单状态修改
			FwOrderPo fwOrderPo = new FwOrderPo();
			fwOrderPo.setId(id);
			//待买家确认
			fwOrderPo.setStatus(OrderStatusTypeEnum.WAIT_PRESON_COMFIRE.getValue());
			fwOrderService.updateEndDynamic(fwOrderPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("结束服务异常",e);
		}
		return ajax;
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
	
	@RequestMapping("commit_node")
	@ResponseBody
	public AjaxResultVo commitNode(@RequestBody HashMap<String, Object> map) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			orderNodeDetailService.saveNodeList(map, ShiroUtils.getSessionUser().getId(), ShiroUtils.getSessionUser().getUsername());
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("提交审核异常",e);
		}
		return ajax;
	}
	
	@RequestMapping("toGetAuthHistory")
	public String getAuthHistory(Long nodeId,Long orderDetailId,Model model) {
		model.addAttribute("nodeId",nodeId);
		model.addAttribute("orderDetailId",orderDetailId);
		return "order/detail/auth_history";
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
			page = orderNodeAuthService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("订单列表查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
}

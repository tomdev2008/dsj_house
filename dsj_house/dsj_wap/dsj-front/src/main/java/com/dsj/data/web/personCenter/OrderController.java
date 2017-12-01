package com.dsj.data.web.personCenter;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.data.web.utils.payUtil.ZGTUtils;
import com.dsj.modules.fw.enums.OrderStatusOneEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.OrderNodeJdPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.OrderNodeJdService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.UserService;

/**
 * 新房编辑管理
 */
@Controller
@RequestMapping(value = "front/personCenter/order")
public class OrderController {
	private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

	@Autowired
	private OrderNodeJdService orderNodeJdService;
	
	@Autowired
	private FwOrderService fwOrderService;
	@Autowired
	private FwOrderDetailService fwOrderDetailService;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private SmsLogsService smsLogsService;
	
	@RequestMapping("detail")
	public String detail(Long id,Model model) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderDetailId", id);
		List<OrderNodeJdPo> list = null;
		FwOrderVo vo = null;
		try {
			list = orderNodeJdService.listNewBy(hashMap);
			vo = fwOrderService.getOrderDetailVoByDetailId(hashMap);
		} catch (Exception e) {
			LOGGER.error("订单性情查询错误",e.getMessage());
		}
		
		model.addAttribute("nodeList",list);
		model.addAttribute("detailVo",vo);
		return "personCenter/warrant_order_detail";
	}
	
	/**
	 * 重新下单
	 */
	@RequestMapping("gotoReOrder")
	@ResponseBody
	public AjaxResultVo gotoReOrder(Long orderDetailId,Model model) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> map = new HashMap<>();
		try {
			FwOrderDetailPo orderDetailPo = fwOrderDetailService.getById(orderDetailId);
			FwOrderPo orderPo = fwOrderService.getById(orderDetailPo.getOrderId());
			//orderPo.setDeleteFlag(DeleteStatusEnum.DEL.getValue());
			orderPo.setStatus(OrderStatusTypeEnum.CANCEL.getValue());
			orderPo.setStatusone(OrderStatusOneEnum.FIX.getValue());
			fwOrderService.updateDynamic(orderPo);
			
			map.put("userId", orderDetailPo.getDbuserid());
			map.put("skuId", orderDetailPo.getProductSkuId());
			map.put("areaCodeThree", orderPo.getAreaCodeThree());
			ajax.setData(map);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("订单处理失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 取消订单
	 */
	@RequestMapping("gotoCancel")
	@ResponseBody
	public AjaxResultVo gotoCancel(Long orderId,Model model) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			FwOrderPo orderPo = fwOrderService.getById(orderId);
			orderPo.setStatus(OrderStatusTypeEnum.CANCEL.getValue());
			orderPo.setStatusone(OrderStatusOneEnum.FIX.getValue());
			orderPo.setUpdateTime(new Date());
			fwOrderService.updateDynamic(orderPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("订单处理失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 买家确认完成
	 */
	@RequestMapping("gotoSucess")
	@ResponseBody
	public AjaxResultVo gotoSucess(Long orderId,Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		AjaxResultVo ajax = new AjaxResultVo();
		ajax.setStatusCode(StatusCode.SERVER_ERROR);
		try {
			FwOrderPo orderPo = fwOrderService.getById(orderId);
			
			//获取请求参数
			String orderrequestid	= orderPo.getRequestid();
			Map<String, String> params	= new HashMap<String, String>();
			params.put("orderrequestid", orderrequestid);
			//第一步 生成密文data
			String data	= ZGTUtils.buildData(params, ZGTUtils.SETTLECONFIRMAPI_REQUEST_HMAC_ORDER);
			//第二步 发起请求
			String requestUrl = ConfigUtils.instance.getSettleConfirmApi();
			Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
			//第三步 判断请求是否成功，
			if(responseMap.containsKey("code")) {
				LOGGER.info("订确认担保请求成功：");
			}
			//第四步 解密同步响应密文data，获取明文参数
			String responseData	= responseMap.get("data");
			Map<String, String> responseDataMap	= ZGTUtils.decryptData(responseData);
			//第五步 code=1时，方表示接口处理成功
			if(!"1".equals(responseDataMap.get("code"))) {
				ajax.setData("订确认担保操作失败："+responseDataMap);
				LOGGER.error("订确认担保操作失败："+responseDataMap);
				return ajax;
			}
			//第六步 hmac签名验证
			if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.SETTLECONFIRMAPI_RESPONSE_HMAC_ORDER)) {
				ajax.setData("订确认担保hmac签名验证失败");
				LOGGER.error("订确认担保hmac签名验证失败"+responseDataMap);
				return ajax;
			}
			
			//第七步 进行业务处理
			orderPo.setStatus(OrderStatusTypeEnum.WAIT_COMMENTED.getValue());
			orderPo.setUpdateTime(new Date());
			orderPo.setStatusone(OrderStatusOneEnum.FOUR.getValue());
			UserPo sessionUser = UserUtil.getCurrentUser(request);
			fwOrderService.updateSucOrder(orderPo,sessionUser.getId(),sessionUser.getUsername());
			
			//发短信通知客户
			Map<String, Object> map1 = new HashMap<>();
			map1.put("orderId", orderPo.getId());
			UserPo userPo = userService.getById(orderPo.getUserId());
			smsLogsService.saveLogsAndsend(userPo.getPhone(),SMSTemplate.getDrawingTemplateTwo(SMSTemplate.QRFW,null));
			
			LOGGER.info("订单号"+orderrequestid+"确认担保交易成功，返回数据:"+responseDataMap);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("确认担保处理失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 申请退款
	 */
	@RequestMapping("gotoRefund")
	@ResponseBody
	public AjaxResultVo gotoRefund( Long orderId , String refundReason ,Integer refundtype, Model model) throws UnsupportedEncodingException {
		AjaxResultVo ajax = new AjaxResultVo();
		ajax.setStatusCode(StatusCode.SERVER_ERROR);
		try {
			FwOrderPo orderPo = fwOrderService.getById(orderId);
			orderPo.setRefundReason(refundReason);
			orderPo.setRefundtype(refundtype);
			orderPo.setStatus(OrderStatusTypeEnum.RETURNING.getValue());
			orderPo.setStatusone(OrderStatusOneEnum.THREE.getValue());
			orderPo.setRefunddate(new Date());
			fwOrderService.updateDynamic(orderPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("申请退款处理失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
}

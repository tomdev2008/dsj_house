package com.dsj.data.wap.warrant;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.UUIDTool;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.PinyinUtil;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.data.wap.utils.payUtil.ZGTUtils;
import com.dsj.modules.fw.enums.OrderStatusOneEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.fw.service.FwuserComService;
import com.dsj.modules.fw.service.FwuserCommentService;
import com.dsj.modules.fw.vo.FwSkuVo;
import com.dsj.modules.fw.vo.FwuserCommentVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.service.UserService;

/**
 * 订单
 */
@Controller
@RequestMapping(value = "order")
public class OrderController {
	private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
	
	@Autowired
	private FwOrderService fwOrderService;
	
	@Autowired
	private FwSpuService fwSpuService;

	@Autowired
	private FwSkuService fwSkuService;
	
	@Autowired
	private FwuserCommentService fwuserCommentService;

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private PropertyService propertyService;
	
	@Autowired
	private FwOrderDetailService fwOrderDetailService;
	
	@Autowired
	private FwuserComService fwuserComService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsLogsService smsLogsService;
	
	@RequestMapping("orderPage")
	@ResponseBody
	public PageDateTable<?> orderPage(Integer pageNumber ,Integer pageSize,Integer type ,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
//		Long userId = (long) 557;
		Long userId = UserUtil.getCurrentUser(request).getId();
		map.put("userId",userId);	
		if (type != 0 ) {
			if (type == 1) { // 待付款
				map.put("statusS", "(1,3)"); //1待付款  3已过期
			}else if (type == 4) { //已付款
				map.put("statusS", "(4,7,11,12,13)");//4已付款  7待买家确认  11待开启服务 12已开启服务 13待结束服务
			}else if (type == 9) { //待评论
				map.put("statusS", "(9)"); //9 待评论
			}else if (type == 10) { //已评论
				map.put("statusS", "(10)");// 10 已评论
			}else if (type == 2) { //已取消
				map.put("statusS", "(2)");// 2已取消
			}else if (type == 5) {
				map.put("statusS", "(5,6)");//5退款受理中 6退款完成
			}
		}
		PageParam pageParam = new PageParam( pageNumber , pageSize);
		PageBean page = null;
		try {
			page = fwOrderService.listOrderPage(pageParam, map);
		} catch (Exception e) {
			LOGGER.error("动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	/**
	 * 确认订单
	 */
	@RequestMapping("orderComfirm")
	@ResponseBody
	public AjaxResultVo orderComfirm(Long fwUserId, Long skuId ,Long areaCodeThree,Model model) {
		AjaxResultVo ajax = new AjaxResultVo();
		Map<String, Object> map = new HashMap<>();
		try {
			PropertyPo propertyPo = propertyService.getById(fwUserId);
			FwuserCommentVo fwuserComment = fwuserCommentService.getPingLv(fwUserId);
			AreaPo areaPo = areaService.getById(areaCodeThree);
			
			FwSkuPo skuPo = fwSkuService.getById(skuId);
			FwSpuPo spuPo = fwSpuService.getById(skuPo.getSpuId());
			
			
			
			map.put("propertyPo", propertyPo);
			map.put("fwuserComment", fwuserComment);
			map.put("areaPo", areaPo);
			map.put("skuPo", skuPo);
			map.put("spuPo", spuPo);
			ajax.setData(map);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("订单处理失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
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
	
	// ajax去支付
	@RequestMapping("gotoPay")
	@ResponseBody
	public AjaxResultVo gotoPay(Long orderId ,HttpServletRequest request) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			FwOrderPo order = fwOrderService.getById(orderId);
			order.setRequestid(UUIDTool.getUUID());
			Map<String, String> responseDataMap = dealPayParm(order, request);
			if(responseDataMap.get("fail-msg")!=null){
				ajax.setData(responseDataMap.get("fail-msg"));
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}else {
				fwOrderService.updateDynamic(order);
				ajax.setData(responseDataMap.get("payurl"));
				ajax.setStatusCode(StatusCode.SUCCESS);
			}
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
	
	/**
	 * 买家确认完成
	 */
	@RequestMapping("gotoSucess")
	@ResponseBody
	public AjaxResultVo gotoSucess(Long orderId,Model model,HttpServletRequest request) throws UnsupportedEncodingException {
		AjaxResultVo ajax = new AjaxResultVo();
		ajax.setStatusCode(StatusCode.SERVER_ERROR);
		try {
			UserPo sessionUser = UserUtil.getCurrentUser(request);
			if (sessionUser == null) {
				ajax.setData("订确认担保操作失败：请登录");
				LOGGER.error("订确认担保操作失败：请登录");
				return ajax;
			}
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
	
	// ajax处理订单
	@RequestMapping("doOrder")
	@ResponseBody
	public AjaxResultVo doOrder(Long fwuserId,String areaCodeThree, 
			String areaCodeThreeName, Long skuId , HttpServletRequest request) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			Date date = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
	        FwSkuVo skuVo = fwSkuService.getFwSkuVoById(skuId);
	        
	        FwOrderPo order = new FwOrderPo();
	        order.setFwuserId(fwuserId);
	        order.setAreaCodeThree(areaCodeThree);
	        order.setAreaCodeThreeName(areaCodeThreeName);
	        //生成订单号
			String orderNo = PinyinUtil.getFirstWord(skuVo.getName())+sdf.format(date);
			order.setOrderNo(orderNo);
			order.setOrderPrice(skuVo.getPrice());
			order.setPayment(skuVo.getPrice());
			order.setOrderName(skuVo.getName());
			order.setRequestid(UUIDTool.getUUID());
			
			Map<String, String> responseDataMap = dealPayParm(order, request);
			if(responseDataMap.get("fail-msg")!=null){
				ajax.setData(responseDataMap.get("fail-msg"));
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}else {
				order.setUserId(UserUtil.getCurrentUser(request).getId());
				order.setSum(1); // 购买数量
				order.setStatus(OrderStatusTypeEnum.WAIT_pay.getValue());//待支付
				order.setReviewStatus(2);// 2-未评论 1-已评论
				order.setCreateTime(date); //订单生产时间
				order.setPayurl(responseDataMap.get("payurl"));
				order.setAreaCodeOne("110000");
				order.setAreaCodeOneName("北京");
				order.setAreaCodeTwo("110100");
				order.setAreaCodeTwoName("北京市");
				LOGGER.info(">>>>>>>>>>>>>>>>>>>saveOrder前");
				fwOrderService.saveOrder(order , skuId);
				LOGGER.info(">>>>>>>>>>>>>>>>>>>saveOrder后");
				
				ajax.setData(responseDataMap.get("payurl"));
				ajax.setStatusCode(StatusCode.SUCCESS);
			}
			
		} catch (Exception e) {
			LOGGER.error("订单处理失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	public Map<String, String> dealPayParm(FwOrderPo order ,HttpServletRequest request ){
		Map<String, String> responseDataMap = new HashMap<>();
		String _url = request.getRequestURL().toString().replace(request.getRequestURI(), "") + request.getContextPath();
		
		//获取各个请求参数
		String requestid = formatStr(order.getRequestid());//uuid
    	String amount = formatStr(order.getOrderPrice().toString());//支付金额[amount]
    	String assure = formatStr("1");//是否担保[assure] 0否 1是
    	String productname = formatStr(order.getOrderName());//商品名称[productname]
    	String productcat = formatStr(order.getOrderName());//商品种类[productcat]
    	String productdesc = formatStr(order.getOrderName());//商品描述[productdesc]
    	String divideinfo = formatStr("");//分账信息[divideinfo]
    	String callbackurl = formatStr(ZGTUtils.getCallbackurl()+"/front/warrant/callbackurl");//后台通知地址[callbackurl] 通知后台交易结果
    	String webcallbackurl = formatStr(ZGTUtils.getWapwebcallbackurl());//页面通知地址[webcallbackurl] 返回url
    	String bankid = formatStr("");//银行编码[bankid]
    	String period = formatStr(ZGTUtils.getPeriod());//担保有效期时间[period]
    	String memo = formatStr("");//订单备注[memo]
    	String orderexpdate = formatStr("30");//订单有效期[orderexpdate]
    	String payproducttype = formatStr("ONEKEY");//支付类型[payproducttype] pc端网银支付
    	String userno = formatStr("");//用户标识[userno]
    	String ip = formatStr("");//ip
    	String cardname = formatStr("");//持卡人姓名[cardname]
    	String idcard = formatStr("");//身份证号[idcard]
    	String bankcardnum = formatStr("");//银行卡号[bankcardnum]
    	String mobilephone = formatStr("");//预留手机号[mobilephone]
    	String appid = formatStr("");//微信公众号appid[appid]
    	String openid = formatStr("");//公众号用户openid[openid]
    	String directcode = formatStr("");//直连代码[directcode]
    	
		Map<String, String> params	= new HashMap<String, String>();
		params.put("requestid", 	requestid);
		params.put("amount", 		amount);
		params.put("assure", 		assure);
		params.put("productname", 	productname);
		params.put("productcat", 	productcat);
		params.put("productdesc", 	productdesc);
		params.put("divideinfo", 	divideinfo);
		params.put("callbackurl", 	callbackurl);
		params.put("webcallbackurl", webcallbackurl);
		params.put("bankid",		bankid);
		params.put("period", 		period);
		params.put("memo", 			memo);
		params.put("orderexpdate", 	orderexpdate);
		params.put("payproducttype", payproducttype);
		params.put("userno", 		userno);
		params.put("ip", 			ip);
		params.put("cardname", 		cardname);
		params.put("idcard", 		idcard);
		params.put("bankcardnum", 	bankcardnum);
		params.put("mobilephone", 	mobilephone);
		params.put("appid", 		appid);
		params.put("openid", 		openid);
		params.put("directcode", 	directcode);

		//第一步 生成密文data
		String data	= ZGTUtils.buildData(params, ZGTUtils.PAYAPI_REQUEST_HMAC_ORDER);
		
		//第二步 发起请求
		String requestUrl = ConfigUtils.instance.getPayApi();
		Map<String, String> responseMap	= ZGTUtils.httpPost(requestUrl, data);
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>支付接口返回加密："+responseMap.toString());
		//第三步 判断请求是否成功，
		if(!responseMap.containsKey("data")) {
			responseDataMap.put("fail-msg", "对不起,请求易宝返回数据失败");
			LOGGER.error(responseMap.toString());
			return responseDataMap;
		}

		//第四步 解密同步响应密文data，获取明文参数
		String responseData	= responseMap.get("data");
		responseDataMap	= ZGTUtils.decryptData(responseData);
		LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>>支付接口返回："+responseDataMap.toString());
		//第五步 code=1时，方表示接口处理成功
		if(!"1".equals(responseDataMap.get("code"))) {
			responseDataMap.put("fail-msg", "接口处理未成功");
			LOGGER.error(responseDataMap.toString());
			return responseDataMap;
		}
		
		//第六步 hmac签名验证
		if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.PAYAPI_RESPONSE_HMAC_ORDER)) {
			responseDataMap.put("fail-msg", "hmac签名验证失败");
			LOGGER.error(responseDataMap.toString());
			return responseDataMap;
		}
		
		//第七步 进行业务处理
		LOGGER.info("易宝支付接口sucess:"+responseDataMap);
		return responseDataMap;
	}
	
	public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
	
}


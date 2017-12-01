package com.dsj.data.web.warrant;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.utils.UUIDTool;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.PinyinUtil;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.data.web.utils.payUtil.ZGTUtils;
import com.dsj.modules.fw.enums.OrderStatusOneEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.po.FwuserComPo;
import com.dsj.modules.fw.po.FwuserCommentPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.fw.service.FwuserComService;
import com.dsj.modules.fw.service.FwuserCommentService;
import com.dsj.modules.fw.vo.FwOrderDetailVo;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.fw.vo.FwSkuVo;
import com.dsj.modules.fw.vo.FwSpuVo;
import com.dsj.modules.fw.vo.FwuserComVo;
import com.dsj.modules.fw.vo.FwuserCommentVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.po.CompanyPo;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.PropertyVo;

/**
 * 权证
 */
@Controller
@RequestMapping(value = "front/warrant")
public class WarrantController {
	private final Logger LOGGER = LoggerFactory.getLogger(WarrantController.class);

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
	private FwOrderService fwOrderService;
	
	@Autowired
	private FwuserComService fwuserComService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SmsLogsService smsLogsService;
	
	// 权证商品列表
	@RequestMapping("list")
	public String fwList(Model model) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<FwSpuVo> list = null;
		try {
			list = fwSpuService.getFwSpuVoList(map);
		} catch (Exception e) {
			LOGGER.error("权证商品列表异常", e);
		}
		model.addAttribute("list", list);
		return "warrant/warrant_list";
	}

	// 权证商品详情
	@RequestMapping("detail")
	public String detail(Model model, Long id) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		// 默认取北京的
		map.put("parentId", "110100");
		map.put("isCustom", AreaEnum.NOT_CUSTOM.getValue());
		try {
			FwSkuPo fwSkuPo = fwSkuService.getById(id);
			FwSpuVo vo = new FwSpuVo();
			FwSpuPo fwSpuPo = fwSpuService.getById(fwSkuPo.getSpuId());
			//spu下的所有评论
			FwuserCommentVo commentMsg = fwuserCommentService.getPingLvBySpu(fwSkuPo.getSpuId());
			model.addAttribute("commentMsg", commentMsg);
			
			String aboutAreaIds = propertyService.getAboutArea(fwSkuPo.getSpuId().toString());
			if(StringUtils.isNotBlank(aboutAreaIds)){
				String ids = "";
				TreeSet<String> ts=new TreeSet<>();
				String[] split = aboutAreaIds.split(",");
				for (String str : split) {
					ts.add(str);
				}
				Iterator<String> i=ts.iterator();
				while(i.hasNext()){
					ids+=","+i.next();
				}

				map.put("ids", ids.substring(1));
				map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				// 北京的三级地区
				List<AreaPo> threeAreaList = areaService.listBy(map);
				model.addAttribute("threeAreaList", threeAreaList);
			}
			
			// 所有的类型
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			map.put("spuId", fwSkuPo.getSpuId());
			List<FwSkuVo> skuList = fwSkuService.getFwSkuVoList(map);
			model.addAttribute("skuList", skuList);
			// spu
			MyBeanUtils.copyBean2Bean(vo, fwSpuPo);
			vo.setMinPrice(fwSkuPo.getPrice());
			vo.setDescribes(fwSkuPo.getDescribes());
			vo.setGuarantee(fwSkuPo.getGuarantee());
			model.addAttribute("spu", vo);
			// 推荐的服务商品
			map.clear();
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<FwSpuVo> spuList = fwSpuService.getFwSpuVoList(map);
			model.addAttribute("spuList", spuList);
		} catch (Exception e) {
			LOGGER.error("权证商品详情查询异常", e);
		}
		return "warrant/warrant_detail";
	}

	@RequestMapping("commentList")
	@ResponseBody
	public Map<String, Object> newHouseDirectoryList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = fwuserCommentService.listNewPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("开发商账号查询异常", e);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("commentList", page.getRecordList());
		return map;
	}

	// 选择服务者列表
	@RequestMapping("toFwUserList")
	public String houseDynamic(Model model, Long skuId, String areaCodeThree) {
		FwSkuPo skuPo = fwSkuService.getById(skuId);
		model.addAttribute("spuId", skuPo.getSpuId());
		model.addAttribute("skuId", skuId);
		model.addAttribute("areaCodeThree", areaCodeThree);
		
		// 北京的三级地区 默认取北京的
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", "110100");
		map.put("isCustom", AreaEnum.NOT_CUSTOM.getValue());
		String aboutAreaIds = propertyService.getAboutArea(skuPo.getSpuId().toString());
		if(StringUtils.isNotBlank(aboutAreaIds)){
			String ids = "";
			TreeSet<String> ts=new TreeSet<>();
			String[] split = aboutAreaIds.split(",");
			for (String str : split) {
				ts.add(str);
			}
			Iterator<String> i=ts.iterator();
			while(i.hasNext()){
				ids+=","+i.next();
			}

			map.put("ids", ids.substring(1));
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			// 北京的三级地区
			List<AreaPo> threeAreaList = areaService.listBy(map);
			model.addAttribute("threeAreaList", threeAreaList);
		}
		return "warrant/warrant_property_list";
	}

	
	@RequestMapping("fwUserList")
	@ResponseBody
	public PageDateTable<?> newHouseResponsibleList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
		requestMap.put("auditStatus", 1);
        
        Integer pageSize=10;
        Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		
		if(requestMap.get("pageSize")!=null){
			pageSize = Integer.parseInt((String) requestMap.get("pageSize"));
		}
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		PageBean page = null;
		try {
			page = propertyService.listNewFrontPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("服务人员列表查询异常", e);
		}
		
		@SuppressWarnings("rawtypes")
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	//商品的评论
	@RequestMapping("shopCommentList")
	@ResponseBody
	public PageDateTable<?> shopCommentList(@RequestParam(value = "aoData", defaultValue = "") String aoData) {
		
		Map<String, Object> requestMap = new HashMap<String, Object>();
		if(StringUtils.isNotBlank(aoData)){
			requestMap = JsonTools.parsePageMap(aoData);
		}
        
        Integer pageSize=30;
        Integer pageNumber=1;
		if(requestMap.get("page")!=null){
			 pageNumber = Integer.parseInt((String) requestMap.get("page"));
		}
		
		if(requestMap.get("pageSize")!=null){
			pageSize = Integer.parseInt((String) requestMap.get("pageSize"));
		}
		requestMap.put("auditStatus", 1);//上线的代办人
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		PageBean page = null;
		try {
			page = fwuserCommentService.listNewCommentPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("商品评论列表查询异常", e);
		}
		
		@SuppressWarnings("rawtypes")
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	// 服务人员详情
	
	// 确认订单
	@RequestMapping("orderComfirm")
	public String goBuy( Long userId, Long skuId ,Long areaCodeThree, Model model) {
		PropertyPo propertyPo = propertyService.getById(userId);
		UserPo userPo = userService.getById(propertyPo.getUserId());
		CompanyPo companyPo = companyService.getById(propertyPo.getCompany());
		AreaPo areaPo = areaService.getById(areaCodeThree);
		
		FwSkuPo skuPo = fwSkuService.getById(skuId);
		FwSpuPo spuPo = fwSpuService.getById(skuPo.getSpuId());
		
		model.addAttribute("propertyPo", propertyPo);
		model.addAttribute("userPo", userPo);
		model.addAttribute("companyPo", companyPo);
		model.addAttribute("areaPo", areaPo);
		model.addAttribute("skuPo", skuPo);
		model.addAttribute("spuPo", spuPo);
		
		return "warrant/warrant_orderComfirm";
	}
	
	// ajax处理订单
	@RequestMapping("doOrder")
	@ResponseBody
	public AjaxResultVo doOrder(FwOrderPo order , Long skuId , HttpServletRequest request) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			Date date = new Date();  
	        SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMddHHmmssSSS");
	        FwSkuVo skuVo = fwSkuService.getFwSkuVoById(skuId);
	        
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
				order.setStatusone(OrderStatusOneEnum.ONE.getValue());
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
	
	/*	
	 * 后台通知	
	 * */
	@RequestMapping("callbackurl")
	public void callbackurl(HttpServletRequest request,HttpServletResponse response, Model model) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		String data	= request.getParameter("data");
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
			LOGGER.error("hmac签名验证失败,dataMap:"+dataMap);
			return ;
		}
		String notifytype	= dataMap.get("notifytype");
		try {
			if("SERVER".equals(notifytype)) {
				Map<String, Object> paramMap = new HashMap<>();
				String requestid = dataMap.get("requestid");//订单号
				paramMap.put("requestid", requestid);
				FwOrderPo orderPo = fwOrderService.getBy(paramMap);
				if (orderPo.getPayTime() == null) {
					orderPo.setPayNo(dataMap.get("externalid"));// 交易流水号
					orderPo.setStatus(OrderStatusTypeEnum.SUCCESS_PAY.getValue());
					//待办
					orderPo.setStatusone(OrderStatusOneEnum.ONE.getValue());
					orderPo.setPayTime(new Date());//支付时间
					orderPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
					fwOrderService.updateOrderAndDetail(orderPo);
					//发短信通知代办人
					Map<String, Object> map1 = new HashMap<>();
					map1.put("orderId", orderPo.getId());
					FwOrderVo orderVo = fwOrderService.findVoByMap(map1);
					String msg=orderVo.getRealName()+"@_s_@"+orderVo.getProductName();
					smsLogsService.saveLogsAndsend(orderVo.getDbPhone(),SMSTemplate.getDrawingTemplateTwo(SMSTemplate.KHFKWC,msg));
					
					//通知平台成功
					response.setCharacterEncoding("UTF-8");
					response.setContentType("text/html");
					PrintWriter out	= response.getWriter();
					out.println("SUCCESS");
					out.flush();
					out.close();
				}
			}
		} catch (Exception e) {
			LOGGER.error("异步回掉处理数据失败,dataMap:"+dataMap, e);
			return ;
		}
		LOGGER.info("异步回掉后台接收成功,dataMap:"+dataMap);
	}
	
	/*	
	 * 后台通知	返回页面
	 * */
	@RequestMapping("webcallbackurl")
	public String webcallbackurl(HttpServletRequest request, Model model,HttpServletResponse response) throws IOException {
		//UTF-8编码
		request.setCharacterEncoding("UTF-8");
		//第一步 获取回调密文data
		String data					= request.getParameter("data");
		//第二步 解密密文data，获取明文参数
		Map<String, String> dataMap	= ZGTUtils.decryptData(data);
		//第三步 hmac签名验证
		if(!ZGTUtils.checkHmac(dataMap, ZGTUtils.PAYAPICALLBACK_HMAC_ORDER)) {
			LOGGER.error("hmac签名验证失败,dataMap:"+dataMap);
		}
		Map<String, Object> paramMap = new HashMap<>();
		String requestid = dataMap.get("requestid");//订单号
		paramMap.put("requestid", requestid);
		FwOrderPo orderPo = fwOrderService.getBy(paramMap);
			orderPo.setPayNo(dataMap.get("externalid"));// 交易流水号
			orderPo.setStatus(OrderStatusTypeEnum.SUCCESS_PAY.getValue());
			orderPo.setPayTime(new Date());//支付时间
			orderPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			fwOrderService.updateOrderAndDetail(orderPo);
			//发短信通知代办人
			Map<String, Object> map1 = new HashMap<>();
			map1.put("orderId", orderPo.getId());
			FwOrderVo orderVo = fwOrderService.findVoByMap(map1);
			String msg=orderVo.getRealName()+"@_s_@"+orderVo.getProductName();
			smsLogsService.saveLogsAndsend(orderVo.getDbPhone(),SMSTemplate.getDrawingTemplateTwo(SMSTemplate.KHFKWC,msg));
			
			//通知平台成功
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out	= response.getWriter();
			out.println("SUCCESS");
			out.flush();
			out.close();
		request.setAttribute("dataMap", dataMap);
		return "warrant/pay_success";
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
    	String webcallbackurl = formatStr(ZGTUtils.getPcwebcallbackurl());//页面通知地址[webcallbackurl] 返回url
    	String bankid = formatStr("");//银行编码[bankid]
    	String period = formatStr(ZGTUtils.getPeriod());//担保有效期时间[period]
    	String memo = formatStr("");//订单备注[memo]
    	String orderexpdate = formatStr("30");//订单有效期[orderexpdate]
    	String payproducttype = formatStr("SALES");//支付类型[payproducttype] pc端网银支付
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
	
	/*	
	 * 	通过订单ID查出服务id、用户id、服务者id，
	 * 	跳转到warrant_addComment.ftl页面
	 * */
	@RequestMapping("addComment")
	public String addUserComm(Long orderId,Model model) {
		/*FwOrderDetailVo fwOrderDetailVo = fwOrderDetailService.getFwComment(orderId);
		model.addAttribute("fwOrderDetailVo", fwOrderDetailVo);*/
		model.addAttribute("orderId", orderId);
		return "warrant/warrant_evaluate";
	}
	
	//保存用户评价
	@RequestMapping("saveComment")
	@ResponseBody
	public AjaxResultVo saveComment(FwuserCommentPo fwuserComment,Model model) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			fwuserCommentService.saveFwuserComment(fwuserComment);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("评论异常", e);
		}
		return ajax;
	}
	
	//查看用户评价
	@RequestMapping("checkComment")
	public String checkComment(Long orderId,Model model) {
		FwuserCommentPo fwuserCommentPo ;
		Map<String,Object> params = new HashMap<String,Object>();
		try {
			fwuserCommentPo = fwuserCommentService.getByOrderId(orderId);
			params.put("commentId", fwuserCommentPo.getId());
			List<FwuserComPo> fwuserComList = fwuserComService.listBy(params);
			model.addAttribute("fwuserComment", fwuserCommentPo);
			model.addAttribute("fwuserComList", fwuserComList);
		} catch (Exception e) {
			LOGGER.error("查看评论异常", e);
		}
		return "warrant/warrant_check_evaluate";
	}
	
	private FwOrderDetailPo orderToDetail(FwOrderPo order) {
		FwOrderDetailPo detailPo = new FwOrderDetailPo();
		detailPo.setCreateTime(new Date());
		return detailPo;
	}
	
	public String formatStr(String text) {
		return text == null ? "" : text.trim();
	}
	
	//代办人详情
	@RequestMapping("fwuserDetail")
	public String checkFwuserDetail(Long id,Model model,Long spuId,String areaCodeThree ) {
		PropertyVo propertyvo = null;
		try {
			propertyvo = propertyService.getVoById(id);
			
			
			String area = propertyvo.getArea();
			if(StringUtils.isNotBlank(area)){
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("ids", area);
				map.put("isCustom", AreaEnum.NOT_CUSTOM.getValue());
				map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				List<AreaPo> threeAreaList = areaService.listBy(map);
				model.addAttribute("threeAreaList", threeAreaList);
			}
			
			FwuserCommentVo fwuserComment = fwuserCommentService.getPingLv(id);
			model.addAttribute("fwuserComment", fwuserComment);
			model.addAttribute("property", propertyvo);
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			hashMap.put("ids", propertyvo.getBusiness());
			List<FwSpuVo> list =  fwSpuService.getFwSpuVoList(hashMap);
			model.addAttribute("fwSpuList", list);
			List<FwuserComVo> listBy = fwuserComService.getCommCount(id);
			model.addAttribute("FwuserComList", listBy);
			
			
		} catch (Exception e) {
			LOGGER.error("代办人详情异常", e);
		}
		model.addAttribute("spuId", spuId);
		model.addAttribute("areaCodeThree", areaCodeThree);
		model.addAttribute("id", id);
		return "warrant/warrant_personal_detail";
	}
	
		//权证首页
		@RequestMapping("index")
		public String fwuserIndex(Model model ) {
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				List<FwSpuVo> list = null;
				List<PropertyVo> proList = null;
				try {
					list = fwSpuService.getSanFwSpuVoList(map);
					proList = propertyService.getProByTuiJian();
				} catch (Exception e) {
					LOGGER.error("权证商品列表异常", e);
				}
				model.addAttribute("list", list);
				model.addAttribute("proList", proList);
			return "warrant/warrant_index";
		}
}

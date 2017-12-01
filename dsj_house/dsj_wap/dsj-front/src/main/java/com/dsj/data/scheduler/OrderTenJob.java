package com.dsj.data.scheduler;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.ui.Model;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.personCenter.OrderController;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.data.web.utils.payUtil.ZGTUtils;
import com.dsj.modules.fw.enums.OrderStatusOneEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.mobile400.service.MobileDetailService;
import com.dsj.modules.mobile400.service.MobileHistoryService;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.UserService;

public class OrderTenJob  implements Runnable {
	
	private final Logger LOGGER = LoggerFactory.getLogger(OrderTenJob.class);
	
	private String cronExpression;

	private int shutdownTimeout = Integer.MAX_VALUE;

	private ThreadPoolTaskScheduler threadPoolTaskScheduler;
	
	@Autowired
	private FwOrderService fwOrderService;
	
	@Autowired
	private  UserService userService;
	
	@Autowired
	private SmsLogsService smsLogsService;
	
	@PostConstruct
	public void start() {
		Validate.notBlank(cronExpression);

		threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
		threadPoolTaskScheduler.setThreadNamePrefix("SpringCronJob");
		threadPoolTaskScheduler.initialize();

		threadPoolTaskScheduler.schedule(this, new CronTrigger(cronExpression));
	}

	@PreDestroy
	public void stop() {
		ScheduledExecutorService scheduledExecutorService = threadPoolTaskScheduler
				.getScheduledExecutor();
		Threads.normalShutdown(scheduledExecutorService, shutdownTimeout,
				TimeUnit.SECONDS);
	}

	@Override
	public void run() {
		String str = fwOrderService.updateDealOrderQueryTime();
		if(StringUtils.isNotBlank(str)){
			String[] split = str.split(",");
			System.out.println("order activityTenJob:");
			for (String string : split) {
				LOGGER.info("24小时未确认,系统自动确认。id---{}",string);
				gotoSucess(Long.parseLong(string));
			}
		}
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	/**
	 * 设置normalShutdown的等待时间,单位秒.
	 */
	public void setShutdownTimeout(int shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
	}

	public void gotoSucess(Long orderId){
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
				LOGGER.error("订确认担保操作失败："+responseDataMap);
			}
			//第六步 hmac签名验证
			if(!ZGTUtils.checkHmac(responseDataMap, ZGTUtils.SETTLECONFIRMAPI_RESPONSE_HMAC_ORDER)) {
				LOGGER.error("订确认担保hmac签名验证失败"+responseDataMap);
			}
			
			//第七步 进行业务处理
			UserPo userPo = userService.getById(orderPo.getUserId());
			
			orderPo.setStatus(OrderStatusTypeEnum.WAIT_COMMENTED.getValue());
			orderPo.setUpdateTime(new Date());
			orderPo.setStatusone(OrderStatusOneEnum.FOUR.getValue());
			fwOrderService.updateSucOrder(orderPo,userPo.getId(),userPo.getUsername());
			
			//发短信通知客户
			Map<String, Object> map1 = new HashMap<>();
			map1.put("orderId", orderPo.getId());
			smsLogsService.saveLogsAndsend(userPo.getPhone(),SMSTemplate.getDrawingTemplateTwo(SMSTemplate.QRFW,null));
			
			LOGGER.info("订单号"+orderrequestid+"确认担保交易成功，返回数据:"+responseDataMap);
		} catch (Exception e) {
			LOGGER.error("确认担保处理失败", e);
		}
	}
}

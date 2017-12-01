package com.dsj.data.web.mobile.detail;

import java.util.Date;
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

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.Base64SecurityUtil;
import com.dsj.common.utils.BeanToMap;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.URLUtils;
import com.dsj.common.utils.json.JsonMapper;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.web.ResponseUtils;
import com.dsj.modules.mobile400.enums.MobileTypeEnum;
import com.dsj.modules.mobile400.po.MobileDetailPo;
import com.dsj.modules.mobile400.po.MobilePo;
import com.dsj.modules.mobile400.service.MobileDetailService;

/**
 * 来电明细管理
 */
@Controller
@RequestMapping(value = "back/**/mobileManager/detail")
public class Mobile400DetailController {
	private final Logger LOGGER = LoggerFactory.getLogger(Mobile400DetailController.class);

	@Autowired
	private MobileDetailService mobileDetailService;

	@RequestMapping({ "mobile_house_list", "" })
	public String mobileHouseList(Model model) {
		return "mobileManager/detail/mobile_house_list";
	}

	@RequestMapping("mobile_agent_list")
	public String mobileAgentList(Model model) {
		return "mobileManager/detail/mobile_agent_list";
	}

	@RequestMapping("mobile_property_list")
	public String mobilePropertyList(Model model) {
		return "mobileManager/detail/mobile_property_list";
	}

	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = mobileDetailService.listPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("400楼盘来电管理查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	/**
	 * 录音下载
	 * 
	 * @param response
	 * @return
	 */
	@RequestMapping("record")
	@ResponseBody
	public Object record(HttpServletResponse response, String record, String ani, String startDate) {
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=\"" + ani + ".mp3\"");
		Date parseDate = DateUtils.parseDate(startDate, DateUtils.Format.YYYY_MM_DD_HH_MM_SS.value());
		String formatDate = DateUtils.formatDate(parseDate, DateUtils.Format.YYYYMMDDHHMMSS.value());
		return GetRecorder(record, formatDate);
	}

	/*
	 * 
	 * GetRecorder
	 */
	public byte[] GetRecorder(String record, String recorderDate) {
		byte[] decodeFromString = null;
		try {
			Map<String, Object> postData = new HashMap<String, Object>();
			postData.put("recordid", record);
			postData.put("date", recorderDate);
			String sendPost = URLUtils.sendPost(JsonMapper.toJsonString(postData),
					ConfigUtils.instance.getGetRecorderUrl(), ConfigUtils.instance.getMobileLoginName(),
					ConfigUtils.instance.getMobileRedispwd());
			LOGGER.info("400 TEL resquest Edit Work Group result: {}", sendPost);
			Map<String, Object> fromJsonString = (Map<String, Object>) JsonMapper.fromJsonString(sendPost, Map.class);
			Integer result = (Integer) fromJsonString.get("result");
			if (result == 1) {
				String message = (String) fromJsonString.get("message");
				System.out.println(message);
				decodeFromString = Base64SecurityUtil.getDecryptArr(message);
				return decodeFromString;
			}
		} catch (Exception e) {
			LOGGER.error("400 --3 TEL resquest delete Work Group error:", e);
		}
		return decodeFromString;
	}

	// 导出
	@RequestMapping("exportNewHouse")
	@ResponseBody
	public Object exportNewHouse(HttpServletResponse response, MobilePo vo) throws Exception {
		BeanToMap beanToMap = new BeanToMap();
		Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
		return ResponseUtils.downloadExcel(ConfigUtils.instance.getNewHouseMobileDetailExcel(),
				(PageParam pageParam) -> {
					PageBean page = null;
					try {
						page = mobileDetailService.listPage(pageParam, requestMap);
						List<?> list = page.getRecordList();
						for (Object object : list) {
							MobileDetailPo po = (MobileDetailPo) object;
							if (Integer.parseInt(po.getCallresult()) == 0) {
								po.setCallresultName("成功");
							} else if (Integer.parseInt(po.getCallresult()) == 1) {
								po.setCallresultName("忙");
							} else if (Integer.parseInt(po.getCallresult()) == 2) {
								po.setCallresultName("无应答");
							} else if (Integer.parseInt(po.getCallresult()) == 3) {
								po.setCallresultName("客户提前挂机");
							} else if (Integer.parseInt(po.getCallresult()) == 11) {
								po.setCallresultName("客户主动放弃");
							} else if (Integer.parseInt(po.getCallresult()) == 201) {
								po.setCallresultName("无效分机号");
							} else if (Integer.parseInt(po.getCallresult()) == 1000) {
								po.setCallresultName("非工作时间");
							} else if (Integer.parseInt(po.getCallresult()) == 1002) {
								po.setCallresultName("欠费");
							}
							if (po.getChannel() == 1) {
								po.setChannelName("PC");
							} else if (po.getChannel() == 2) {
								po.setChannelName("WAP");
							} else {
								po.setChannelName("APP");
							}
						}
					} catch (Exception e) {
						LOGGER.error("导出新房来电明细异常", e);
						e.printStackTrace();
					}
					return page;
				}, "ani", "dni", "houseName", "houseCode", "extcode", "channelName", "callertime", "callresultName",
				"startdate", "cityname");
	}
	
	// 导出
		@RequestMapping("exportAgent")
		@ResponseBody
		public Object exportAgent(HttpServletResponse response, MobilePo vo) throws Exception {
			BeanToMap beanToMap = new BeanToMap();
			Map<String, Object> requestMap = beanToMap.transBean2Map(vo);
			return ResponseUtils.downloadExcel(ConfigUtils.instance.getAgentMobileDetailExcel(),
					(PageParam pageParam) -> {
						PageBean page = null;
						try {
							page = mobileDetailService.listPage(pageParam, requestMap);
							List<?> list = page.getRecordList();
							for (Object object : list) {
								MobileDetailPo po = (MobileDetailPo) object;
								if (Integer.parseInt(po.getCallresult()) == 0) {
									po.setCallresultName("成功");
								} else if (Integer.parseInt(po.getCallresult()) == 1) {
									po.setCallresultName("忙");
								} else if (Integer.parseInt(po.getCallresult()) == 2) {
									po.setCallresultName("无应答");
								} else if (Integer.parseInt(po.getCallresult()) == 3) {
									po.setCallresultName("客户提前挂机");
								} else if (Integer.parseInt(po.getCallresult()) == 11) {
									po.setCallresultName("客户主动放弃");
								} else if (Integer.parseInt(po.getCallresult()) == 201) {
									po.setCallresultName("无效分机号");
								} else if (Integer.parseInt(po.getCallresult()) == 1000) {
									po.setCallresultName("非工作时间");
								} else if (Integer.parseInt(po.getCallresult()) == 1002) {
									po.setCallresultName("欠费");
								}
							}
						} catch (Exception e) {
							LOGGER.error("导出新房来电明细异常", e);
							e.printStackTrace();
						}
						return page;
					}, "ani", "dni", "agentName", "agentCode", "extcode", "callertime", "callresultName",
					"startdate", "cityname");
		}
}

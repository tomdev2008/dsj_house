package com.dsj.data.web.order;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
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

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JSONConvertUtils;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.common.web.ResponseUtils;
import com.dsj.modules.fw.enums.OrderStatusOneEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.fw.vo.FwSkuVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;

/**
 * 后台权证订单
 */
@Controller
@RequestMapping(value = "back/**/order/fwuser")
public class OrderFwuserController {
	private final Logger LOGGER = LoggerFactory.getLogger(OrderFwuserController.class);
	
	@Autowired
	private AreaService areaService;
	@Autowired
	private FwSkuService fwSkuService;
	@Autowired
	private FwOrderService fwOrderService;
	
	
	@RequestMapping({ "order_fwuser_list", "" })
	public String newHouseList(Model model) {
		//地区信息
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		
		//业务类型
		hashMap.clear();
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<FwSkuVo> skuList = fwSkuService.getSkuNameAndTypeId(hashMap);
		model.addAttribute("skuList", skuList);
		
		return "order/fwuser/fwuser_order_list";
	}
	
	
	@RequestMapping("page/list")
	@ResponseBody
	public PageDateTable<?> pageList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
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
	//确认结算
	@RequestMapping("jiesuan")
	@ResponseBody
	public AjaxResultVo jiesuan(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			FwOrderPo fwOrderPo = new FwOrderPo();
			fwOrderPo.setId(id);
			fwOrderPo.setStatusone(OrderStatusOneEnum.FIVE.getValue());
			fwOrderService.updateDynamic(fwOrderPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("节点审核异常",e);
		}
		return ajax;
	}
	
	//确认退款
	@RequestMapping("tuikuan")
	@ResponseBody
	public AjaxResultVo tuikuan(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			FwOrderPo fwOrderPo = new FwOrderPo();
			fwOrderPo.setId(id);
			fwOrderPo.setStatus(OrderStatusTypeEnum.RETURNCOMPLITE.getValue());
			fwOrderPo.setStatusone(OrderStatusOneEnum.FIVE.getValue());
			fwOrderService.updateDynamic(fwOrderPo);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("节点审核异常",e);
		}
		return ajax;
	}
	
	//导出
	@RequestMapping("export")
	@ResponseBody
	public Object export(HttpServletResponse response,FwOrderVo vo) throws Exception{
		Map<String, Object> requestMap = transBean2Map(vo);
		return ResponseUtils.downloadExcel(ConfigUtils.instance.getOrderExcelPath(),(PageParam pageParam)->{
			requestMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
            PageBean page = null;
    		try {
    			page = fwOrderService.listNewPage(pageParam, requestMap);
    		} catch (Exception e) {
    			LOGGER.error("导出异常", e);
    			e.printStackTrace();
    		}
        return page;
	},"createTime","areaName","productName","orderNo","payment","username","phone","dbUserName","dbPhone","dbCompanyName","status","refunddate","refundReason","nodeName","authStatus");
		
	}
	// Bean --> Map 1: 利用Introspector和PropertyDescriptor 将Bean --> Map  
	public  Map<String, Object> transBean2Map(Object obj) {  
	    if (obj == null) {  
	        return null;  
	    }  
	    Map<String, Object> map = new HashMap<String, Object>();  
	    try {  
	        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());  
	        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();  
	        for (PropertyDescriptor property : propertyDescriptors) {  
	            String key = property.getName();  
	            // 过滤class属性  
	            if (!key.equals("class")) {  
	                // 得到property对应的getter方法  
	                Method getter = property.getReadMethod();  
	                Object value = getter.invoke(obj);  
	  
	                map.put(key, value);  
	            }  
	  
	        }  
	    } catch (Exception e) {  
	    	LOGGER.error("transBean2Map Error {}" ,e);  
	    }  
	    return map;  
	  
	} 
	
}

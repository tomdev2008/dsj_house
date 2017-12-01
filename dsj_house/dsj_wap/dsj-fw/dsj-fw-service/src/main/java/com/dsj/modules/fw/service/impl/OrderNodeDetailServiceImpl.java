package com.dsj.modules.fw.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.modules.fw.dao.OrderNodeDetailDao;
import com.dsj.modules.fw.enums.AuthStatusTypeEnum;
import com.dsj.modules.fw.enums.IsAuthEnum;
import com.dsj.modules.fw.po.FwNodeFieldPo;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.FwTypeNodePo;
import com.dsj.modules.fw.po.OrderNodeAuthPo;
import com.dsj.modules.fw.po.OrderNodeDetailPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwTypeNodeService;
import com.dsj.modules.fw.service.OrderNodeAuthService;
import com.dsj.modules.fw.service.OrderNodeDetailService;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.service.UserService;

import net.sf.json.JSONArray;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
@Service
public class OrderNodeDetailServiceImpl  extends BaseServiceImpl<OrderNodeDetailDao,OrderNodeDetailPo> implements OrderNodeDetailService {

	@Autowired
	private FwOrderService fwOrderService;
	@Autowired
	private FwTypeNodeService fwTypeNodeService;
	@Autowired
	private FwOrderDetailService fwOrderDetailService;
	@Autowired
	private OrderNodeAuthService orderNodeAuthService;
	@Autowired
	SmsLogsService smsLogsService;
	@Autowired
	PropertyService propertyService;
	@Autowired
	UserService userService;
	
	@Override
	public void saveNodeList(HashMap<String, Object> map, Long id, String username) {

		// 节点对应的表单值
		List<OrderNodeDetailPo> OrderNodeDetailList = (List<OrderNodeDetailPo>) JSONArray
				.toList(JSONArray.fromObject(map.get("orderNodeDetail").toString()), OrderNodeDetailPo.class);
		OrderNodeDetailPo orderNodeDetailPo = OrderNodeDetailList.get(0);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("orderDetailId", orderNodeDetailPo.getOrderDetailId());
		hashMap.put("nodeId", orderNodeDetailPo.getNodeId());
		dao.deleteByOrderDetailIdAndNodeId(hashMap);
		dao.saveList(OrderNodeDetailList);
		
		String content = "";
		for (OrderNodeDetailPo po : OrderNodeDetailList) {
			if("备注".equals(po.getFieldName())){
				content = po.getFieldVal();
				break;
			}
			
		}
		
		
		Integer isauth = Integer.parseInt(map.get("isauth").toString());
		if(isauth==IsAuthEnum.YES.getValue()){//需要审核的节点
			//记录表添加数据
			OrderNodeAuthPo orderNodeAuthPo = new OrderNodeAuthPo();
			orderNodeAuthPo.setNodeId(orderNodeDetailPo.getNodeId());
			orderNodeAuthPo.setContent(content);
			orderNodeAuthPo.setOrderId(orderNodeDetailPo.getOrderId());
			orderNodeAuthPo.setOrderDetailId(orderNodeDetailPo.getOrderDetailId());
			orderNodeAuthPo.setStatus(AuthStatusTypeEnum.AUTH_WAIT.getValue());
			orderNodeAuthPo.setCreateTime(new Date());
			orderNodeAuthPo.setCreatePresonId(id);
			orderNodeAuthPo.setCreatePresonName(username);
			orderNodeAuthService.saveDynamic(orderNodeAuthPo);
			//更新order_detail表未待审核
			FwOrderDetailPo fwOrderDetailPo = new FwOrderDetailPo();
			fwOrderDetailPo.setId(orderNodeDetailPo.getOrderDetailId());
			fwOrderDetailPo.setAuthStatus(AuthStatusTypeEnum.AUTH_WAIT.getValue());
			fwOrderDetailService.updateDynamic(fwOrderDetailPo);
		}else{//如果是不用审核的节点
			FwTypeNodePo fwTypeNodePo = fwTypeNodeService.getById(orderNodeDetailPo.getNodeId());
			//记录表添加数据
			OrderNodeAuthPo orderNodeAuthPo = new OrderNodeAuthPo();
			orderNodeAuthPo.setNodeId(orderNodeDetailPo.getNodeId());
			orderNodeAuthPo.setContent(content);
			orderNodeAuthPo.setOrderId(orderNodeDetailPo.getOrderId());
			orderNodeAuthPo.setOrderDetailId(orderNodeDetailPo.getOrderDetailId());
			orderNodeAuthPo.setStatus(AuthStatusTypeEnum.AUTH_SUCCESS.getValue());
			orderNodeAuthPo.setCreateTime(new Date());
			orderNodeAuthPo.setCreatePresonId(id);
			orderNodeAuthPo.setCreatePresonName(username);
			orderNodeAuthService.saveDynamic(orderNodeAuthPo);
			//更新进度  
			fwOrderService.updateFwJd(orderNodeDetailPo.getOrderDetailId(), fwTypeNodePo.getId(), fwTypeNodePo.getNext(),id,username);
		}
		
		//缴税/过户 发送短信
		FwOrderDetailPo orderDetailPo = fwOrderDetailService.getById(orderNodeDetailPo.getOrderDetailId());
		PropertyPo propertyPo = propertyService.getById(orderDetailPo.getDbuserid());
		
		FwOrderPo orderPo = fwOrderService.getById(orderNodeDetailPo.getOrderId());
		String phone = userService.getById(orderPo.getUserId()).getPhone();
		
		int way = 0;
		String str = propertyPo.getName()+"@_s_@"+propertyPo.getTellPhone();
		String fieldName="";
		for (OrderNodeDetailPo po : OrderNodeDetailList) {
			fieldName+=po.getFieldName();
		}
		if(fieldName.indexOf("缴税地址")>-1){//ok
			way = 1;
			for (OrderNodeDetailPo po : OrderNodeDetailList) {
				if("预约时间".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}else if("缴税地址".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}
			}
		}else if(fieldName.indexOf("过户地址")>-1){//ok
			way = 2;
			for (OrderNodeDetailPo po : OrderNodeDetailList) {
				if("预约时间".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}else if("过户地址".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}
			}
		}else if(fieldName.indexOf("公正受理日")>-1){//ok
			way = 3;
			for (OrderNodeDetailPo po : OrderNodeDetailList) {
				if("公正受理日".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}else if("地址".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}
			}
		}else if(fieldName.indexOf("预约办产权证时间")>-1){//ok
			way = 4;
			for (OrderNodeDetailPo po : OrderNodeDetailList) {
				if("预约办产权证时间".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}else if("预约办产权证地址".equals(po.getFieldName())){
					str+="@_s_@"+po.getFieldVal();
				}
			}
		}
		
		switch (way) {
		case 1:
			smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplateTwo(SMSTemplate.YYJS,str));
			break;
		case 2:
			smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplateTwo(SMSTemplate.YYGH,str));
			break;
		case 3:
			smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplateTwo(SMSTemplate.GZYSL,str));
			break;
		case 4:
			smsLogsService.saveLogsAndsend(phone,SMSTemplate.getDrawingTemplateTwo(SMSTemplate.YYXCQBL,str));
			break;
		}
		
	}

	@Override
	public List<FwNodeFieldPo> getDateAndAddress(HashMap<String, Object> map) {
		return dao.getDateAndAddress(map);
	}
	
}
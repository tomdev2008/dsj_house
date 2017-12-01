package com.dsj.modules.fw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.modules.fw.dao.FwOrderDao;
import com.dsj.modules.fw.enums.AuthStatusTypeEnum;
import com.dsj.modules.fw.enums.NodeStatusEnum;
import com.dsj.modules.fw.enums.OrderStatusOneEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.po.FwTypeNodePo;
import com.dsj.modules.fw.po.FwTypePo;
import com.dsj.modules.fw.po.OrderNodeJdPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.FwTypeNodeService;
import com.dsj.modules.fw.service.FwTypeService;
import com.dsj.modules.fw.service.OrderNodeJdService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.other.service.SmsLogsService;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.service.UserService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-29 16:19:35.
 * @版本: 1.0 .
 */
@Service
public class FwOrderServiceImpl extends BaseServiceImpl<FwOrderDao, FwOrderPo> implements FwOrderService {

	private final Logger LOGGER = LoggerFactory.getLogger(FwOrderServiceImpl.class);

	@Autowired
	private FwOrderDetailService fwOrderDetailService;

	@Autowired
	private OrderNodeJdService orderNodeJdService;

	@Autowired
	private FwTypeNodeService fwTypeNodeService;

	@Autowired
	private FwTypeService fwTypeService;

	@Autowired
	private FwSkuService fwSkuService;

	@Autowired
	SmsLogsService smsLogsService;

	@Autowired
	UserService userService;

	@Autowired
	PropertyService propertyService;

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public FwOrderVo getOrderDetailVoByDetailId(HashMap<String, Object> hashMap) {
		return dao.getOrderDetailVoByDetailId(hashMap);
	}

	// 推进服务进度
	@Override
	public void updateFwJd(Long orderDetailId, Long currentNodeId, Long nextNodeId, Long userId, String username) {
		FwTypeNodePo nodePo = fwTypeNodeService.getById(nextNodeId);
		// 子订单信息
		FwOrderDetailPo orderDetailPo = fwOrderDetailService.getById(orderDetailId);
		// 用户手机号
		FwOrderPo orderPo = dao.getById(orderDetailPo.getOrderId());
		String phone = userService.getById(orderPo.getUserId()).getPhone();

		if ("服务开始".equals(orderDetailPo.getNodeName())) {
			// 订单变成办理中
			FwOrderPo fwOrderPo = new FwOrderPo();
			fwOrderPo.setId(orderDetailPo.getOrderId());
			// 已开启服务
			fwOrderPo.setStatus(OrderStatusTypeEnum.OPEND.getValue());
			// 在办订单
			fwOrderPo.setStatusone(OrderStatusOneEnum.TWO.getValue());
			dao.updateDynamic(fwOrderPo);

			PropertyPo propertyPo = propertyService.getById(orderDetailPo.getDbuserid());
			// 发送信息
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.FW_START,
					propertyPo.getName() + "@_s_@" + propertyPo.getTellPhone()));
		}
		// 订单详情表
		FwOrderDetailPo fwOrderDetailPo = new FwOrderDetailPo();
		fwOrderDetailPo.setId(orderDetailId);
		fwOrderDetailPo.setNodeId(nextNodeId);
		fwOrderDetailPo.setNodeName(nodePo.getName());
		if (nodePo.getName().equals("服务完成")) {
			fwOrderDetailPo.setAuthStatus(AuthStatusTypeEnum.WAIT_END.getValue());

			// 订单变成办理中
			FwOrderPo fwOrderPo = new FwOrderPo();
			fwOrderPo.setId(orderDetailPo.getOrderId());
			// 带结束服务
			fwOrderPo.setStatus(OrderStatusTypeEnum.WAIT_OVER.getValue());
			dao.updateDynamic(fwOrderPo);

		} else {
			fwOrderDetailPo.setAuthStatus(AuthStatusTypeEnum.WAIT_COMMIT.getValue());
		}

		fwOrderDetailService.updateDynamic(fwOrderDetailPo);
		// 服务进度表
		OrderNodeJdPo orderNodeJdPo = new OrderNodeJdPo();
		orderNodeJdPo.setOrderDetailId(orderDetailId);
		orderNodeJdPo.setNodeId(currentNodeId);
		orderNodeJdPo.setDealUserId(userId);
		orderNodeJdPo.setDealUserName(username);
		orderNodeJdPo.setDealTime(new Date());
		orderNodeJdPo.setStatus(AuthStatusTypeEnum.AUTH_SUCCESS.getValue());
		orderNodeJdService.updateByOrderDetailId(orderNodeJdPo);
		String currentNodeName = fwTypeNodeService.getById(currentNodeId).getPcname();
		if (currentNodeName.equals("房屋与购房资格核验通过")) {//ok
			// 房屋与购房资格核验通过发送信息
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.FWYGFZZSHTG, null));
		} else if (currentNodeName.equals("购房资格核验通过")) {//ok
			// 购房资格核验通过
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.YGFZZSHTG, null));
		} else if (currentNodeName.equals("网签成功")) {//ok
			// 网签成功
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.WQCG, null));
		} else if (currentNodeName.equals("过户成功")) {//ok
			// 过户成功
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.GHCG, null));
		} else if (currentNodeName.equals("已公正")) {//ok
			// 已公正
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.YGZ, null));
		} else if (currentNodeName.equals("补证成功")) {//ok
			// 补证成功
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.BZCG, null));
		} else if (currentNodeName.equals("测绘结果已上传")) {//ok
			// 补证成功
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.CHJGSC, null));
		} else if (currentNodeName.equals("新房产权办理完成")) {//ok
			// 新房产权办理完成
			smsLogsService.saveLogsAndsend(phone, SMSTemplate.getDrawingTemplateTwo(SMSTemplate.XFCQBLWC, null));
		}

	}

	// 结束服务进度
	@Override
	public void updateEndFwJd(Long id, Long currentNodeId, Long orderDetailId, Long userId, String username) {
		FwOrderDetailPo fwOrderDetailPo = new FwOrderDetailPo();
		fwOrderDetailPo.setId(orderDetailId);
		fwOrderDetailPo.setAuthStatus(AuthStatusTypeEnum.AUTH_SUCCESS.getValue());
		fwOrderDetailService.updateDynamic(fwOrderDetailPo);

		// 服务进度表
		OrderNodeJdPo orderNodeJdPo = new OrderNodeJdPo();
		orderNodeJdPo.setOrderDetailId(orderDetailId);
		orderNodeJdPo.setNodeId(currentNodeId);
		orderNodeJdPo.setDealUserId(userId);
		orderNodeJdPo.setDealUserName(username);
		orderNodeJdPo.setDealTime(new Date());
		orderNodeJdPo.setStatus(AuthStatusTypeEnum.AUTH_SUCCESS.getValue());
		orderNodeJdService.updateByOrderDetailId(orderNodeJdPo);

	}

	@Override
	public Long saveOrder(FwOrderPo order, Long skuId) {
		if (skuId == null) {
			LOGGER.error("》》》》》》》》》》》》》skuId是空的");
		}
		FwSkuPo skuPo = fwSkuService.getById(skuId);
		if (skuPo == null) {
			LOGGER.error("》》》》》》》》》》》》》skuPo是空的");
		}
		Long orderId = dao.insertDynamic(order);
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("typeId", skuPo.getTypeId());
		List<FwTypeNodePo> listTypeNode = fwTypeNodeService.listBy(paramMap);

		FwOrderDetailPo detailPo = new FwOrderDetailPo();
		detailPo = orderToDetail(order);
		detailPo.setTypeId(skuPo.getTypeId());
		detailPo.setOrderId(orderId);
		detailPo.setProductPirce(order.getOrderPrice());
		detailPo.setProductSkuId(skuId);
		//detailPo.setNodeId(listTypeNode.get(0).getId());
		//detailPo.setNodeName(listTypeNode.get(0).getName());
		//detailPo.setAuthStatus(6);//待开启
		Long orderDetailId = fwOrderDetailService.saveDynamic(detailPo);

		List<OrderNodeJdPo> listJd = new ArrayList<OrderNodeJdPo>();
		for (FwTypeNodePo node : listTypeNode) {
			OrderNodeJdPo jdpo = new OrderNodeJdPo();
			jdpo.setCreateTime(new Date());
			jdpo.setIsauth(node.getIsauth());
			jdpo.setStatus(NodeStatusEnum.FAIL.getValue());
			jdpo.setOrderId(orderId);
			jdpo.setOrderDetailId(orderDetailId);
			jdpo.setNodeId(node.getId());
			jdpo.setNodeName(node.getName());
			jdpo.setPcname(node.getPcname());
			listJd.add(jdpo);
		}
		orderNodeJdService.save(listJd);

		return orderId;
	}

	private FwOrderDetailPo orderToDetail(FwOrderPo order) {
		FwOrderDetailPo detailPo = new FwOrderDetailPo();
		detailPo.setCreateTime(order.getCreateTime());
		detailPo.setNum((long) 1); // 购买数量
		detailPo.setOrderNo(order.getOrderNo());
		detailPo.setProductName(order.getOrderName());
		detailPo.setDbuserid(order.getFwuserId());
		return detailPo;
	}

	@Override
	public List<FwOrderVo> findOrderPage(Map<String, Object> map) {
		return dao.findOrderPage(map);
	}

	@Override
	public Long findOrderPageCount(Map<String, Object> map) {
		return dao.findOrderPageCount(map);
	}

	@Override
	public PageBean listOrderPage(PageParam pageParam, Map<String, Object> map) {
		return dao.listPage(pageParam, map, "findOrderPageCount", "findOrderPage");
	}

	@Override
	public FwOrderVo findVoByMap(Map<String, Object> map) {
		return dao.findVoByMap(map);
	}

	@Override
	public void updateDealOrderOverTime() {
		dao.updateOverTimeOrder();
	}

	@Override
	public void updateSucOrder(FwOrderPo orderPo, Long id, String username) {
		dao.updateDynamic(orderPo);
		Map<String, Object> map = new HashMap<>();
		map.put("orderId", orderPo.getId());
		FwOrderDetailPo orderDetailPo = fwOrderDetailService.getBy(map);
		updateEndFwJd(orderPo.getId(), orderDetailPo.getNodeId(), orderDetailPo.getId(), id, username);
	}

	@Override
	public void updateEndDynamic(FwOrderPo fwOrderPo) {
		dao.updateDynamic(fwOrderPo);
		
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("orderId", fwOrderPo.getId());
		FwOrderDetailPo orderDetailPo = fwOrderDetailService.getBy(hashMap);
		String phone = userService.getById(dao.getById(orderDetailPo.getOrderId()).getUserId()).getPhone();
		smsLogsService.saveLogsAndsend(phone,
				SMSTemplate.getDrawingTemplateTwo(SMSTemplate.FWWCQR, orderDetailPo.getProductName()));

	}

	@Override
	public void updateOrderAndDetail(FwOrderPo orderPo) {
		dao.updateDynamic(orderPo);
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("orderId", orderPo.getId());
		FwOrderDetailPo detailPo = fwOrderDetailService.getBy(map);
		map.clear();
		map.put("typeId", detailPo.getTypeId());
		List<FwTypeNodePo> listTypeNode = fwTypeNodeService.listBy(map);
		detailPo.setNodeId(listTypeNode.get(0).getId());
		detailPo.setNodeName(listTypeNode.get(0).getName());
		detailPo.setAuthStatus(6);//待开启
		fwOrderDetailService.updateDynamic(detailPo);
		
	}

	@Override
	public String updateDealOrderQueryTime() {
		return dao.getDealOrderQueryIds();
	}
}
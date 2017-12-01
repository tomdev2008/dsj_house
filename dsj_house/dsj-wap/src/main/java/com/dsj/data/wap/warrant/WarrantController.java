package com.dsj.data.wap.warrant;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import org.apache.commons.lang.StringUtils;
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
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.fw.po.FwSkuPo;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.po.FwuserComPo;
import com.dsj.modules.fw.po.FwuserCommentPo;
import com.dsj.modules.fw.po.OrderNodeJdPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.fw.service.FwuserComService;
import com.dsj.modules.fw.service.FwuserCommentService;
import com.dsj.modules.fw.service.OrderNodeJdService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.fw.vo.FwSkuVo;
import com.dsj.modules.fw.vo.FwSpuVo;
import com.dsj.modules.fw.vo.FwuserComVo;
import com.dsj.modules.fw.vo.FwuserCommentVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.service.PropertyService;
import com.dsj.modules.system.vo.PropertyVo;

/**
 * 权证
 */
@Controller
@RequestMapping(value = "warrant")
public class WarrantController {
	private final Logger LOGGER = LoggerFactory.getLogger(WarrantController.class);

	@Autowired
	private FwSpuService fwSpuService;

	@Autowired
	private FwSkuService fwSkuService;

	@Autowired
	private FwuserCommentService fwuserCommentService;
	
	@Autowired
	private OrderNodeJdService orderNodeJdService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private CompanyService companyService;

	@Autowired
	private PropertyService propertyService;

	@Autowired
	private FwOrderDetailService fwOrderDetailService;

	@Autowired
	private FwOrderService fwOrderService;

	@Autowired
	private FwuserComService fwuserComService;

	// 权证首页
	@RequestMapping("index")
	@ResponseBody
	public AjaxResultVo fwuserIndex(Model model) {
		AjaxResultVo ajaxVo = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		try {
			List<FwSpuVo> list = fwSpuService.getFwSpuVoList(map);
			List<PropertyVo> proList = propertyService.getProByTuiJian();
			map.clear();
			map.put("spuList", list);
			map.put("proList", proList);
			ajaxVo.setStatusCode(StatusCode.SUCCESS);
			ajaxVo.setData(map);

		} catch (Exception e) {
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("权证商品列表异常", e);
		}
		return ajaxVo;
	}

	// 权证商品详情
	@RequestMapping("detail")
	@ResponseBody
	public AjaxResultVo detail(Long id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			// spu
			FwSkuPo fwSkuPo = fwSkuService.getById(id);
			FwSpuVo vo = new FwSpuVo();
			FwSpuPo fwSpuPo = fwSpuService.getById(fwSkuPo.getSpuId());
			MyBeanUtils.copyBean2Bean(vo, fwSpuPo);
			vo.setSkuId(fwSkuPo.getId());
			vo.setMinPrice(fwSkuPo.getPrice());
			vo.setDescribes(fwSkuPo.getDescribeswap());
			vo.setGuarantee(fwSkuPo.getGuaranteewap());
			map.put("vo", vo);
			// spu下的所有评论
			FwuserCommentVo commentMsg = fwuserCommentService.getPingLvBySpu(fwSkuPo.getSpuId());
			map.put("commentMsg", commentMsg);
			result.setData(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("权证商品详情查询异常", e);
		}
		return result;
	}

	// 商品的评论
	@RequestMapping("shopCommentList")
	@ResponseBody
	public PageDateTable<?> shopCommentList(Long type, Long spuId, Long fwuserId,Integer pageNumber, Integer pageSize) {
		HashMap<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("type", type);
		requestMap.put("spuId", spuId);
		requestMap.put("fwUserId", fwuserId);
		requestMap.put("auditStatus", 1); //过滤上线的代办人
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		PageBean page = null;
		try {
			page = fwuserCommentService.listNewCommentPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("商品评论列表查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	//订单详情
	@RequestMapping("orderDetail")
	@ResponseBody
	public AjaxResultVo detail(Long id, Model model) {
		AjaxResultVo result = new AjaxResultVo();
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("orderDetailId", id);
		List<OrderNodeJdPo> list = null;
		FwOrderVo vo = null;
		try {
			list = orderNodeJdService.listNewBy(hashMap);
			vo = fwOrderService.getOrderDetailVoByDetailId(hashMap);
			hashMap.clear();
			hashMap.put("nodeList", list);
			hashMap.put("detailVo", vo);
			result.setData(hashMap);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("订单性情查询错误", e.getMessage());
		}
		return result;
	}

	// 评论

	@RequestMapping("addComment")
	@ResponseBody
	public AjaxResultVo addComment(@RequestBody FwuserCommentPo fwuserComment) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			fwuserCommentService.saveFwuserComment(fwuserComment);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("评论异常异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	
	//代办人详情
	@RequestMapping("fwuserDetail")
	@ResponseBody
	public AjaxResultVo checkFwuserDetail(Long id,Long skuId,Long areaCodeThree) {
		AjaxResultVo result = new AjaxResultVo();
		HashMap<String, Object> map1 = new HashMap<String, Object>();
		List<FwSkuVo> skuList = new ArrayList<FwSkuVo>();
		PropertyVo propertyvo = null;
		AreaPo areaThreePo = new AreaPo();
		try {
			if(null!=areaCodeThree){
				areaThreePo = areaService.getById(areaCodeThree);
			}
			propertyvo = propertyService.getVoById(id);
			String[] split = propertyvo.getBusiness().split(",");
			for (String str : split) {
				FwSkuVo fwSkuVo = fwSkuService.getSkuVoBySpuId(Long.valueOf(str));
				skuList.add(fwSkuVo);
			}
			FwuserCommentVo fwuserComment = fwuserCommentService.getPingLv(id);
			HashMap<String, Object> hashMap = new HashMap<String,Object>();
			List<FwuserComVo> listBy = fwuserComService.getCommCount(id);
			map1.put("property", propertyvo);
			map1.put("fwSkuList", skuList);
			map1.put("fwuserComment", fwuserComment);
			map1.put("fwuserComList", listBy);
			map1.put("areaThree", areaThreePo);
			map1.put("skuId", skuId);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(map1);
		} catch (Exception e) {
			LOGGER.error("代办人详情异常", e);
		}
		return result;
	}
	
	
		//查看用户评价
		@RequestMapping("checkComment")
		@ResponseBody
		public AjaxResultVo checkComment(Long orderId,Model model) {
			AjaxResultVo result = new AjaxResultVo();
			FwuserCommentPo fwuserCommentPo ;
			Map<String,Object> params = new HashMap<String,Object>();
			HashMap<String, Object> map = new HashMap<String, Object>();
			try {
				fwuserCommentPo = fwuserCommentService.getByOrderId(orderId);
				params.put("commentId", fwuserCommentPo.getId());
				List<FwuserComPo> fwuserComList = fwuserComService.listBy(params);
				map.put("fwuserComment", fwuserCommentPo);
				map.put("fwuserComList", fwuserComList);
				result.setData(map);
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("查看评论异常", e);
			}
			return result;
		}
		
		//选择地区
		@RequestMapping("shopChooseArea")
		@ResponseBody
		public AjaxResultVo shopChooseArea(Long skuId) {
			
			AjaxResultVo result = new AjaxResultVo();
			HashMap<String, Object> map = new HashMap<String, Object>();
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			FwSkuPo fwSkuPo = fwSkuService.getById(skuId);
			
			try {
				map.put("parentId", "110100");
				map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
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
					// 北京的三级地区
					List<AreaPo> threeAreaList = areaService.listBy(map);
					map1.put("threeAreaList", threeAreaList);
				}
				map1.put("skuId", skuId);
				result.setData(map1);
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("地区查询异常", e);
				result.setStatusCode(StatusCode.SERVER_ERROR);
			}
			return result;
		}
		
		//代办人详情页面选择地区
		@RequestMapping("chooseArea")
		@ResponseBody
		public AjaxResultVo chooseArea(Long propertyId,Long areaId) {
			AjaxResultVo result = new AjaxResultVo();
			HashMap<String, Object> map1 = new HashMap<String, Object>();
			// 默认取代办人名下北京的
			try {
				PropertyPo propertyPo = propertyService.getById(propertyId);
				map1.put("ids", propertyPo.getArea());
				map1.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				List<AreaPo> threeAreaList = areaService.listBy(map1);
				map1.put("areaId", areaId);
				map1.put("threeAreaList", threeAreaList);
				result.setData(map1);
				result.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("选择地区异常", e);
			}
			return result;
		}
		
		
		//选择服务者列表页
		@RequestMapping("fwUserList")
		@ResponseBody
		public PageDateTable<?> newHouseResponsibleList(Integer pageNumber, Integer pageSize,Long skuId, String areaCodeThree, String type) {
			HashMap<String, Object> requestMap = new HashMap<String, Object>();
			requestMap.put("auditStatus", 1);
			requestMap.put("orderType", type);
			requestMap.put("business", skuId);
			requestMap.put("area", areaCodeThree);
			PageParam pageParam = new PageParam(pageNumber, pageSize);
			PageBean page = null;
			try {
				page = propertyService.listNewFrontPage(pageParam, requestMap);
			} catch (Exception e) {
				LOGGER.error("服务人员列表查询异常", e);
			}
			PageDateTable<?> pageImpl = new PageDateTable(page);
			return pageImpl;
		}
		
		
		

}

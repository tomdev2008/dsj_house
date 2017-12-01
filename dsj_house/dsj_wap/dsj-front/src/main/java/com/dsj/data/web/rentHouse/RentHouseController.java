package com.dsj.data.web.rentHouse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.enums.FrontRentAreaEnum;
import com.dsj.common.enums.FrontRentPriceEnum;
import com.dsj.common.enums.FrontRentTypeEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.enums.PictureObjectEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.rent.po.RentHousePicturePo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.service.RentHousePictureService;
import com.dsj.modules.rent.vo.RentCountMapInfoVo;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.solr.service.RentHouseIndexService;
import com.dsj.modules.solr.vo.RentHouseQueryVo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.vo.AgentVo;
import com.google.common.collect.Maps;

/**
 * 租房管理
 */
@Controller
@RequestMapping(value = "rentHouse")
public class RentHouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(RentHouseController.class);
	
	@Autowired
	RentHouseIndexService rentHouseIndexService;
	
	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	@Autowired
	private RentHousePictureService rentHousePictureService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	
	@Autowired
	AreaService areaService;
	@Autowired
	TradeAreaService tradeAreaService;
	@Autowired
	SubwayService subwayService;
	
	static Map<String, Object> priceMap = Maps.newLinkedHashMap();
	static Map<String, Object> areaMap = Maps.newLinkedHashMap();
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();
	static Map<String, Object> rentTypeMap = Maps.newLinkedHashMap();
	static Map<String, Object> floorTypeMap = Maps.newLinkedHashMap();
	static Map<String,Object> companyTypeMap= Maps.newLinkedHashMap();
	
	static {
		priceMap=FrontRentPriceEnum.toMap();
		rentTypeMap=FrontRentTypeEnum.toMap();
		areaMap=FrontRentAreaEnum.toMap();
		roomMap=FrontRoomEnum.toMap();
		floorTypeMap=FloorTypeEnum.toMap();
		companyTypeMap=FrontCompanyTypeEnum.toMap();
	}
	
	//租房查询列表页
	@RequestMapping
	public String rentHouseIndex( String areaCode2, String areaCode3,Long tradingAreaId, String subwayLineId,String subwayId,
			@RequestParam(value="params", defaultValue="") String params,String k, Model model,
			String line) {
		String url="";
		//初始化二手房检索条件
		Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();

		addSearchModel(mapSearch,model);
		
		//参数处理
		RentHouseQueryVo queryVo = dealParams(params,areaCode3,tradingAreaId, subwayLineId, subwayId,line,model,mapSearch,k);
		
		
		Map<String, Object> mapArea = new HashMap<String, Object>();
		mapArea.put("parentId", BusinessConst.BEIJING_AREA_CODE);
		mapArea.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> areaFirstLists = areaService.listBy(mapArea);
		
		//区域
		if (StringUtils.isNotBlank(areaCode3)) {
			queryVo.setAreaCode3(areaCode3);
			mapArea.put("parentId", areaCode3);
			List<TradeAreaPo> tradeAreaList = tradeAreaService.listBy(mapArea);
			model.addAttribute("tradeAreaList", tradeAreaList);
		}
		//商圈
		if (tradingAreaId!=null) {
			queryVo.setTradingAreaId(tradingAreaId);
		}
		
		//地铁线路
		List<SubwayPo> subwayLineList=subwayService.getByAllsubwayLine();
		model.addAttribute("subwayLineList", subwayLineList);
		if(StringUtils.isNotBlank(subwayLineId)){
			queryVo.setSubwayline(subwayLineId);
			List<SubwayPo> subwayList=subwayService.getByPid(subwayLineId);
			model.addAttribute("subwayList", subwayList);
		}
		//地铁站点
		if (subwayId!=null) {
			queryVo.setSubway(subwayId);
		}
	
		PageBean page = rentHouseIndexService.getRentHouseSolr( 1,queryVo);
		model.addAttribute("areaFirstLists", areaFirstLists);
		model.addAttribute("page", page);
		model.addAttribute("areaCode2", areaCode2);
		model.addAttribute("areaCode3", areaCode3);
		model.addAttribute("tradingAreaId", tradingAreaId);
		model.addAttribute("subwayLineId", subwayLineId);
		model.addAttribute("subwayId", subwayId);
		model.addAttribute("params", params);
		model.addAttribute("url", url);
		model.addAttribute("line", line);
		return "rentHouse/rent_house_list";
	}
	
	//租房详情页
	@RequestMapping({"detail"})
	public String rentHouseDetails(Model model, Long id ){
		//房源详情
		RentHouseOriginVo originVo = rentHouseOriginService.getVoById(id);
		//小区
		HouseDirectoryPo directoryPo = houseDirectoryService.getById(originVo.getDicId());
		//经纪人详情
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("houseId", id);
		List<AgentVo> agentVoList = agentService.getVoByRentHouseId(paramMap);
		AgentVo agentVo = new AgentVo();
		if (agentVoList!=null) {
			agentVo = agentVoList.get(0);
		}
		//房源图片
		paramMap.clear();
		paramMap.put("objId", id);
		paramMap.put("objType", PictureObjectEnum.RENT_ORIGIN.getValue());
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<RentHousePicturePo> picList = rentHousePictureService.listBy(paramMap);
		
		model.addAttribute("originVo", originVo);
		model.addAttribute("agentVo", agentVo);
		model.addAttribute("picList", picList);
		model.addAttribute("directoryPo", directoryPo);
		//字典表回写
		setGroupTypeName(model,originVo);
		
		return "rentHouse/rent_house_details";
	}
	
	// 获取租房推荐
	@RequestMapping("getRecommend")
	@ResponseBody
	public AjaxResultVo getRecommend(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			List<RentHouseOriginVo> recommendList = rentHouseOriginService.getRecommendList(id);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(recommendList);
		} catch (Exception e) {
			LOGGER.error("楼盘相册查询失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 通过区域等级获取地图信息
	 * @param level 传入参数级别  城市2  区县3 商圈4
	 * @param id 传入参数的id
	 * @return
	 */
	@RequestMapping("getRentByAreaId")
	@ResponseBody
	public AjaxResultVo getRentByAreaId(Integer level , Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			Map<String, Object> paramMap = new HashMap<>();
			List<RentCountMapInfoVo> res = new ArrayList<>();
			paramMap.put("id", id);
			if (AreaEnum.CITY_LV.getValue()==level) {
				res = rentHouseOriginService.getRentByCity(paramMap);
			}else if(AreaEnum.COUNTY_LV.getValue()==level) {
				res = rentHouseOriginService.getRentByCounty(paramMap);
			}else if(AreaEnum.TRADE_LV.getValue()==level) {
				res = rentHouseOriginService.getRentByTrade(paramMap);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(res);
		} catch (Exception e) {
			LOGGER.error("通过区域等级获取地图信息失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	private void setGroupTypeName(Model model,RentHouseOriginVo originVo ) {
		// 房屋类型
		Map<String, Object> wyTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.HOUSE_TYPE);
		// 装修情况
		Map<String, Object> zxTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.RENVATION);
		// 朝向
		Map<String, Object> orientationsMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.ORIENTATIONS);
		// 付款方式
		Map<String, Object> payTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.PAY_TYPE);
		// 性别限制
		Map<String, Object> sexTypeMap = groupTypeService.getGroupTypeMapByPid(BusinessConst.SEX_TYPE);
		
		//付款
		model.addAttribute("payTypeName", payTypeMap.get(originVo.getPayType().toString()));
		//朝向
		model.addAttribute("orientationName", orientationsMap.get(originVo.getOrientation().toString()));
		//性别
		model.addAttribute("sexTypeName", sexTypeMap.get(originVo.getGenderType().toString()));
		//装修
		model.addAttribute("zxTypeName", zxTypeMap.get(originVo.getZxType().toString()));
		//物业
		model.addAttribute("wyTypeName", wyTypeMap.get(originVo.getWyType().toString()));
	}
	
	
	private void addSearchModel(Map<String, Object> mapSearch, Model model) {
		if(mapSearch.get(BusinessConst.ACHTYPE)!=null){//建筑类
			model.addAttribute("buildType", mapSearch.get(BusinessConst.ACHTYPE));
		}
		
		if(mapSearch.get(BusinessConst.ORIENTATIONS)!=null){//朝向
			model.addAttribute("orientations", mapSearch.get(BusinessConst.ORIENTATIONS));
		}
		if(mapSearch.get(BusinessConst.WY_TYPE)!=null){//物业类型
			model.addAttribute("wyType", mapSearch.get(BusinessConst.WY_TYPE));
		}
		if(mapSearch.get(BusinessConst.PAY_TYPE)!=null){//付款类型
			model.addAttribute("payType", mapSearch.get(BusinessConst.PAY_TYPE));
		}
		
		if(mapSearch.get(BusinessConst.RENVATION)!=null){//装修情况
			model.addAttribute("renvation", mapSearch.get(BusinessConst.RENVATION));
		}
		
		if(mapSearch.get(BusinessConst.SEX_TYPE)!=null){//性别限制
			model.addAttribute("sexMap", mapSearch.get(BusinessConst.SEX_TYPE));
		}
		
		model.addAttribute("priceMap", priceMap);
		model.addAttribute("areaMap", areaMap);
		model.addAttribute("rentTypeMap", rentTypeMap);
		model.addAttribute("roomMap",roomMap);
		model.addAttribute("floorTypeMap",floorTypeMap);
		model.addAttribute("companyTypeMap", companyTypeMap);
		
	}
	
	//seo参数优化
	@SuppressWarnings("unchecked")
	private RentHouseQueryVo dealParams(String params,String areaCode3,Long tradingAreaId,
			 String subwayLineId,String subwayId,String line,Model model,Map<String,Object> mapSearch,String k) {
		RentHouseQueryVo vo = new RentHouseQueryVo();
		Map<String,Object> conditionMap= Maps.newLinkedHashMap();
		
		if(tradingAreaId!=null){
			params=areaCode3+"_"+tradingAreaId+"/"+params;
		}
		
		if(StringUtils.isNotBlank(areaCode3)&&tradingAreaId==null){
			params="d"+areaCode3+"/"+params;
		}
		
		if(StringUtils.isNotBlank( subwayId)&&StringUtils.isNotBlank(line)){
			params=line+"/"+subwayLineId+"_"+ subwayId+"/"+params;
		}
		
		if(StringUtils.isBlank(subwayId)&&StringUtils.isNotBlank(subwayLineId)&&StringUtils.isNotBlank(line)){
			params=line+"/"+"s"+subwayLineId+"/"+params;
		}
		
		Pattern p = Pattern.compile(BusinessConst.PAGE_NUM_P);
		Matcher m = p.matcher(params);
		if (m.find(0)) {
			
				vo.setPageNum(Integer.parseInt(m.group(0).replace(BusinessConst.PAGE_NUM, "")));
				model.addAttribute("url_pg", "/rentHouse/"+params.replace(m.group(0), "")+BusinessConst.PAGE_NUM);
				params=params.replace(m.group(0), "");
			
		}else{
			vo.setPageNum(CommConst.PAGE_NUM);
			model.addAttribute("url_pg", "/rentHouse/"+params+BusinessConst.PAGE_NUM);
			
		}

		if (StringUtils.isNotBlank(params)) {
			String url="";
			// 分页
			
			// 价格
			p = Pattern.compile(BusinessConst.PRICE_MAX_P);
		    m = p.matcher(params);
			Pattern pin = Pattern.compile(BusinessConst.PRICE_MIN_P);
			Matcher min = pin.matcher(params);
			if(m.find(0)&& min.find(0)){
			
					Integer price=Integer.parseInt(m.group(0).replace(BusinessConst.PRICE_MAX, ""));
					model.addAttribute("pmx", price);
					vo.setPmx(price);
					Integer pricemin=Integer.parseInt(min.group(0).replace(BusinessConst.PRICE_MIN, ""));
					model.addAttribute("pmn", pricemin);
					vo.setPmn(pricemin);
					url=params.replace(m.group(0), "").replace(min.group(), "");
					model.addAttribute("url_pr", url);
			}else{
				p = Pattern.compile(BusinessConst.PRICE_P);
			    m = p.matcher(params);
				if (m.find(0)) {
					Integer price=Integer.parseInt(m.group(0).replace(BusinessConst.PRICE, ""));
					vo.setPrice(price);
					url=params.replace(m.group(0), "");
					model.addAttribute("url_pr", url);
					conditionMap.put(FrontRentPriceEnum.getEnum(price).getDesc(), url);
				}else{
					model.addAttribute("url_pr",params);
				}
			}
			
			// 面积
			p = Pattern.compile(BusinessConst.BUILD_AREA_MAX_P);
			m = p.matcher(params);
			pin = Pattern.compile(BusinessConst.BUILD_AREA_MIN_P);
			min = pin.matcher(params);
			if(m.find(0)&& min.find(0)){
				Integer area=Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_AREA_MAX, ""));
				model.addAttribute("amx", area);
				vo.setAmx(area);
				Integer areamin=Integer.parseInt(min.group(0).replace(BusinessConst.BUILD_AREA_MIN, ""));
				model.addAttribute("amn", areamin);
				vo.setAmn(areamin);
				url=params.replace(m.group(0), "").replace(min.group(), "");
				model.addAttribute("url_a", url);
			}else{
				p = Pattern.compile(BusinessConst.BUILD_AREA_P);
				m = p.matcher(params);
				if (m.find(0)) {
					Integer buildArea=Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_AREA, ""));
					vo.setBuildArea(buildArea);
					url=params.replace(m.group(0), "");
					model.addAttribute("url_a", url);
					conditionMap.put(FrontRentAreaEnum.getEnum(buildArea).getDesc(), url);
				}else{
					model.addAttribute("url_a",params);
				}
			}

			// 户型 ROOM_P
			p = Pattern.compile(BusinessConst.ROOM_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer room=Integer.parseInt(m.group(0).replace(BusinessConst.ROOM, ""));
				vo.setRoom(room);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_rm", params.replace(m.group(0), ""));
				conditionMap.put(FrontRoomEnum.getEnum(room).getDesc(), url);
			}else{
				model.addAttribute("url_rm",params);
			}
			
			// 出租类型 
			p = Pattern.compile(BusinessConst.RENTTYPE_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer rentType=Integer.parseInt(m.group(0).replace(BusinessConst.RENTTYPE, ""));
				vo.setRentType(rentType);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_rtp", params.replace(m.group(0), ""));
				conditionMap.put(FrontRentTypeEnum.getEnum(rentType).getDesc(), url);
			}else{
				model.addAttribute("url_rtp",params);
			}
			
			//付款类型
			p = Pattern.compile(BusinessConst.PAYTYPE_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer payType=Integer.parseInt(m.group(0).replace(BusinessConst.PAYTYPE, ""));
				vo.setPayType(payType);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_pt", params.replace(m.group(0), ""));
				Map<String,String> cmap=(Map<String, String>) mapSearch.get(BusinessConst.PAY_TYPE);
				conditionMap.put(cmap.get(payType.toString()), url);
			}else{
				model.addAttribute("url_pt",params);
			}

			// 来源 COMPANY_P
			p = Pattern.compile(BusinessConst.COMPANY_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer companyType=Integer.parseInt(m.group(0).replace(BusinessConst.COMPANY, ""));
				vo.setCompanyType(companyType);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_ct", params.replace(m.group(0), ""));
				
				conditionMap.put(FrontCompanyTypeEnum.getEnum(companyType).getDesc(), url);
			}else{
				model.addAttribute("url_ct",params);
			}
			
			// 物业 WY_TYPE_SEO
			p = Pattern.compile(BusinessConst.WY_TYPE_SEO_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer wyType=Integer.parseInt(m.group(0).replace(BusinessConst.WY_TYPE_SEO, ""));
				vo.setWyType(Integer.parseInt(m.group(0).replace(BusinessConst.WY_TYPE_SEO, "")));
				url=params.replace(m.group(0), "");
				model.addAttribute("url_wy", params.replace(m.group(0), ""));
				Map<String,String> cmap=(Map<String, String>) mapSearch.get(BusinessConst.WY_TYPE);
				conditionMap.put(cmap.get(wyType.toString()), url);
			}else{
				model.addAttribute("url_wy",params);
			}

			// 朝向 ORIENTATIONS_P
			p = Pattern.compile(BusinessConst.ORIENTATIONS_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer orirenation=Integer.parseInt(m.group(0).replace(BusinessConst.ORIENTATIONS_O, ""));
				vo.setOrientations(orirenation);
				
				url=params.replace(m.group(0), "");
				model.addAttribute("url_o",url);
				Map<String,String> cmap=(Map<String, String>) mapSearch.get(BusinessConst.ORIENTATIONS);
				conditionMap.put(cmap.get(orirenation.toString()), url);
			}else{
				model.addAttribute("url_o",params);
			}
			
			// 装修情况 RENOVATION_P
			p = Pattern.compile(BusinessConst.RENOVATION_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer renovation=Integer.parseInt(m.group(0).replace(BusinessConst.RENOVATION_RT, ""));
				vo.setRenovation(renovation);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_rt", params.replace(m.group(0), ""));
				Map<String,String> cmap=(Map<String, String>)mapSearch.get(BusinessConst.RENVATION);
				conditionMap.put(cmap.get(renovation.toString()), url);
			}else{
				model.addAttribute("url_rt",params);
			}
			
			// 楼层 FLOOR_TYPE_P
			p = Pattern.compile(BusinessConst.FLOOR_TYPE_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer floorType=Integer.parseInt(m.group(0).replace(BusinessConst.FLOOR_TYPE, ""));
				vo.setFloorType(floorType);;
				url=params.replace(m.group(0), "");
				model.addAttribute("url_tf", params.replace(m.group(0), ""));
				conditionMap.put(FloorTypeEnum.getEnum(floorType).getDesc(), url);
			}else{
				model.addAttribute("url_tf",params);
			}
			
			//排序规则
			p = Pattern.compile(BusinessConst.ORDER_OD_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer orderding=Integer.parseInt(m.group(0).replace(BusinessConst.ORDER_OD, ""));
				vo.setOrdering(orderding);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_orderding", params.replace(m.group(0), ""));
				model.addAttribute("orderding",orderding);
			}else{
				model.addAttribute("url_orderding",params);
			}
		
			model.addAttribute("conditionMap", conditionMap);
			
		}
		vo.setKeywords(k);
		model.addAttribute("keywords", k);
		if(StringUtils.isNotBlank(k)){
			model.addAttribute("url_kw", "?k"+k);
		}
		return vo;
	}
	
}

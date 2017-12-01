package com.dsj.data.web.ershoufang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.FrontAreaEnum;
import com.dsj.common.enums.FrontBuildYearEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.enums.FrontErshoufangWyTypeEnum;
import com.dsj.common.enums.FrontOrientationsEnum;
import com.dsj.common.enums.FrontPriceEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.page.PageBean;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.service.OldHouseAgentService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.service.OldHousePictureService;
import com.dsj.modules.oldhouse.vo.OldHouseRecommendVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.HousePictureService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.solr.service.ErshoufangIndexService;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;
import com.google.common.collect.Maps;

/**
 * 新房管理
 */
@Controller
@RequestMapping
public class ErshoufangController {
	private final Logger logger = LoggerFactory.getLogger(ErshoufangController.class);

	@Autowired
	ErshoufangIndexService ershoufangIndexService;

	@Autowired
	AreaService areaServiceTradeAreaService;

	@Autowired
	AreaService areaService;

	@Autowired
	TradeAreaService tradeAreaService;
	
	@Autowired
	GroupTypeService groupTypeService;
	
	@Autowired
	SubwayService subwayService;
	
	@Autowired
	OldHouseMasterService oldHouseMasterService;
	
	@Autowired
	HouseDirectoryService houseDirectoryService;
	
	@Autowired
	HousePictureService housePictureService;
	
	@Autowired
	OldHousePictureService oldHousePictureService;
	
	@Autowired
	OldHouseAgentService oldHouseAgentService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	UserService userService;
	
	static Map<String, Object> priceMap = Maps.newLinkedHashMap();
	static Map<String, Object> areaMap = Maps.newLinkedHashMap();
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();
	static Map<String, Object> buildYearMap = Maps.newLinkedHashMap();
	static Map<String, Object> floorTypeMap = Maps.newLinkedHashMap();
	static Map<String,Object> companyTypeMap= Maps.newLinkedHashMap();
	
	static Map<String,Object> orientationsMap= Maps.newLinkedHashMap();
	
	static Map<String,Object> wyTypeMap=Maps.newLinkedHashMap();
	
	static {
		priceMap=FrontPriceEnum.toMap();
		areaMap=FrontAreaEnum.toMap();
		roomMap=FrontRoomEnum.toMap();
		buildYearMap=FrontBuildYearEnum.toMap();
		floorTypeMap=FloorTypeEnum.toMap();
		companyTypeMap=FrontCompanyTypeEnum.toMap();
		
		orientationsMap=FrontOrientationsEnum.toMap();
		wyTypeMap=FrontErshoufangWyTypeEnum.toMap();
	}


	@RequestMapping(value = "ershoufang")
	public String ershoufangIndex( String areaCode2, String areaCode3,Long tradingAreaId, String subwayLineId,String subwayId,
			@RequestParam(value="params", defaultValue="") String params,String k, Model model,
			String line,String kw) {
		logger.info("areaCode3:{},tradingAreaId:{},subwayLineId:{},subwayId:{},line{},params:{}",areaCode3,tradingAreaId,subwayLineId,subwayId,line,params);
		ershoufangParma(  areaCode2,  areaCode3, tradingAreaId,  subwayLineId, subwayId,
				 params, k, model, line,kw); 
		return "ershoufang/master/master_list";
	}
	
	
	
	public void ershoufangParma( String areaCode2, String areaCode3,Long tradingAreaId, String subwayLineId,String subwayId,
			 String params,String k, Model model,
			String line,String kw) {
		
		String url="";
		//初始化二手房检索条件
		Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();

		addSearchModel(mapSearch,model);
		System.out.println("line:"+line);
		//参数处理
		ErShoufangQueryVo queryVo = dealParams(params,areaCode3,tradingAreaId, subwayLineId, subwayId,line,model,mapSearch,k,kw);
		
		
		Map<String, Object> mapArea = new HashMap<String, Object>();
		mapArea.put("parentId", BusinessConst.BEIJING_AREA_CODE);
		mapArea.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> areaFirstLists = areaService.listBy(mapArea);
		
		if (StringUtils.isNotBlank(areaCode3)) {
			queryVo.setAreaCode3(areaCode3);
			mapArea.put("parentId", areaCode3);
			Map<String,List<TradeAreaPo>> tradeAreaMap = tradeAreaService.getMapByOrderByLp(mapArea);
			model.addAttribute("tradeAreaMap", tradeAreaMap);
		}
		
		//地铁查询
		List<SubwayPo> subwayLineList=subwayService.getByAllsubwayLine();
		model.addAttribute("subwayLineList", subwayLineList);
		if(StringUtils.isNotBlank(subwayLineId)){
			List<SubwayPo> subwayList=subwayService.getByPid(subwayLineId);
			model.addAttribute("subwayList", subwayList);
		}
		
		if (tradingAreaId!=null) {
			queryVo.setTradingAreaId(tradingAreaId);
		}
	
		PageBean page = ershoufangIndexService.getErshoufangSolr(30,queryVo);
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
	}


	private void addSearchModel(Map<String, Object> mapSearch, Model model) {
		/*if(mapSearch.get(BusinessConst.HOUSE_TYPE)!=null){//建筑类
			model.addAttribute("buildType", mapSearch.get(BusinessConst.HOUSE_TYPE));
		}*/
		
		/*if(mapSearch.get(BusinessConst.ORIENTATIONS)!=null){//朝向
			model.addAttribute("orientations", mapSearch.get(BusinessConst.ORIENTATIONS));
		}*/
		/*if(mapSearch.get(BusinessConst.WY_TYPE)!=null){//物业类型
			model.addAttribute("wyType", mapSearch.get(BusinessConst.WY_TYPE));
		}*/
		
		if(mapSearch.get(BusinessConst.RENVATION)!=null){//装修情况
			model.addAttribute("renvation", mapSearch.get(BusinessConst.RENVATION));
		}
		
		if(mapSearch.get(BusinessConst.FEATURES)!=null){//装修情况
			model.addAttribute("features", mapSearch.get(BusinessConst.FEATURES));
		}
		
		model.addAttribute("priceMap", priceMap);
		model.addAttribute("areaMap", areaMap);
		model.addAttribute("roomMap",roomMap);
		model.addAttribute("buildYearMap",buildYearMap);
		model.addAttribute("floorTypeMap",floorTypeMap);
		model.addAttribute("companyTypeMap", companyTypeMap);
		model.addAttribute("orientations", orientationsMap);
		model.addAttribute("buildType",wyTypeMap);
		
	}

	//seo参数优化
	private ErShoufangQueryVo dealParams(String params,String areaCode3,Long tradingAreaId,
			 String subwayLineId,String subwayId,String line,Model model,Map<String,Object> mapSearch,String k,String kw) {
		ErShoufangQueryVo vo = new ErShoufangQueryVo();
		Map<String,Object> conditionMap= Maps.newLinkedHashMap();
		
		Boolean isShow=false;
		
		if(tradingAreaId!=null){
			conditionMap.put(areaService.getById(Long.parseLong(areaCode3)).getName(), params);
			conditionMap.put(tradeAreaService.getById(tradingAreaId).getName(),"d"+areaCode3+"/"+params);
			params="d"+areaCode3+"_"+tradingAreaId+"/"+params;
			
		
		}
		
		if(StringUtils.isNotBlank(areaCode3)&&tradingAreaId==null){
			conditionMap.put(areaService.getById(Long.parseLong(areaCode3)).getName(), params);
			params="d"+areaCode3+"/"+params;
			
		}
		
		if(StringUtils.isNotBlank( subwayId)&&StringUtils.isNotBlank(line)){
			conditionMap.put(subwayService.getById(Long.parseLong(subwayId)).getName(), line+"/s"+subwayLineId+"/"+params);
			conditionMap.put(subwayService.getById(Long.parseLong(subwayLineId)).getName(), params);
			params=line+"/s"+subwayLineId+"_"+ subwayId+"/"+params;
			vo.setSubway(subwayId);
		}
		
		if(StringUtils.isBlank(subwayId)&&StringUtils.isNotBlank(subwayLineId)&&StringUtils.isNotBlank(line)){
			conditionMap.put(subwayService.getById(Long.parseLong(subwayLineId)).getName(), params);
			params=line+"/s"+subwayLineId+"/"+params;
			vo.setSubwayline(subwayLineId);
		}
		
		if(StringUtils.isBlank(subwayId)&&StringUtils.isBlank(subwayLineId)&&StringUtils.isNotBlank(line)){
			params=line+"/"+params;
		}
		Pattern p = Pattern.compile(BusinessConst.PAGE_NUM_P);
		Matcher m = p.matcher(params);
		if (m.find(0)) {
			
				vo.setPageNum(Integer.parseInt(m.group(0).replace(BusinessConst.PAGE_NUM, "")));
				model.addAttribute("url_pg", "/ershoufang/"+params.replace(m.group(0), "")+BusinessConst.PAGE_NUM);
				params=params.replace(m.group(0), "");
			
		}else{
			vo.setPageNum(CommConst.PAGE_NUM);
			model.addAttribute("url_pg", "/ershoufang/"+params+BusinessConst.PAGE_NUM);
			
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
					conditionMap.put(FrontPriceEnum.getEnum(price).getDesc(), url);
				}else{
					model.addAttribute("url_pr",params);
				}
			}
			
			// 地区BUILD_AREA_P
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
				model.addAttribute("url_ar", url);
			}else{
				p = Pattern.compile(BusinessConst.BUILD_AREA_P);
				m = p.matcher(params);
				
				
				if (m.find(0)) {
					Integer buildArea=Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_AREA, ""));
					vo.setBuildArea(buildArea);
					url=params.replace(m.group(0), "");
					model.addAttribute("url_ar", url);
					conditionMap.put(FrontAreaEnum.getEnum(buildArea).getDesc(), url);
				}else{
					model.addAttribute("url_ar",params);
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
			
			// 来源 COMPANY_P
			p = Pattern.compile(BusinessConst.COMPANY_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer companyType=Integer.parseInt(m.group(0).replace(BusinessConst.COMPANY, ""));
				vo.setCompanyType(companyType);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_ct", params.replace(m.group(0), ""));
				
				conditionMap.put(FrontCompanyTypeEnum.getEnum(companyType).getDesc(), url);
				isShow=true;
			}else{
				model.addAttribute("url_ct",params);
			}
			
		

			// 年代 BUILD_YEAR_P
			p = Pattern.compile(BusinessConst.BUILD_YEAR_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer buildYear=Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_YEAR, ""));
				vo.setBuildYear(Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_YEAR, "")));
				url=params.replace(m.group(0), "");
				model.addAttribute("url_by", params.replace(m.group(0), ""));
				//FrontBuildYearEnum
				//Map<String,String> cmap=(Map<String, String>)mapSearch.get(BusinessConst.BUILD_YEAR);
				conditionMap.put(FrontBuildYearEnum.getEnum(buildYear).getDesc(), url);
				isShow=true;
			}else{
				model.addAttribute("url_by",params);
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
				isShow=true;
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
				isShow=true;
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
				isShow=true;
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
				isShow=true;
			}else{
				model.addAttribute("url_tf",params);
			}
			
			//排序规则
			p = Pattern.compile(BusinessConst.ORDER_OD_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer ordering=Integer.parseInt(m.group(0).replace(BusinessConst.ORDER_OD, ""));
				vo.setOrdering(ordering);
				url=params.replace(m.group(0), "");
				model.addAttribute("url_orderding", params.replace(m.group(0), ""));
				model.addAttribute("ordering",ordering);
			}else{
				model.addAttribute("url_orderding",params);
			}
			
			
			model.addAttribute("isShow",isShow);
			model.addAttribute("conditionMap", conditionMap);
			
		}
		vo.setKeywords(k);
		vo.setKw(kw);
		model.addAttribute("keywords", k);
		if(StringUtils.isNotBlank(kw)){
			model.addAttribute("keywords", kw);
		}
		if(StringUtils.isNotBlank(k)){
			model.addAttribute("url_kw", "?k="+k);
		}
		if(StringUtils.isNotBlank(kw)){
			model.addAttribute("url_kw", "?kw="+kw);
		}
		return vo;
	}
	
	
	@RequestMapping(value="oldmaster")
	public String ershoufangDetail(Long id,Model model,HttpServletRequest request){
		OldHouseMasterPo oldHouseMaster=oldHouseMasterService.getById(id);
		if(oldHouseMaster.getDicId()!=null){
			HouseDirectoryPo dicPo=houseDirectoryService.getById(oldHouseMaster.getDicId());
			if(dicPo!=null){
				//获取封面
				HousePicturePo houseDicPic =housePictureService.getIsCoverByObjId(dicPo.getId());
				
				model.addAttribute("houseDicPic", houseDicPic);
			}
			model.addAttribute("dicPo", dicPo);
		}
		List<OldHousePicturePo> oldHousePictures=oldHousePictureService.getByMasterId(id);
		model.addAttribute("oldHousePictures", oldHousePictures);
	
		
		//初始化二手房字典
		Map<String,Object> groupTypes=groupTypeService.getHouseGroupType();
		if(groupTypes.get(BusinessConst.ORIENTATIONS)!=null){//朝向
			model.addAttribute("orientationsMap", groupTypes.get(BusinessConst.ORIENTATIONS));
		}
		
		if(groupTypes.get(BusinessConst.FEATURES)!=null){//特色
			model.addAttribute("featuresMap", groupTypes.get(BusinessConst.FEATURES));
			String featureNames="";
			if(StringUtils.isNotBlank(oldHouseMaster.getFeatures())){
				String[] strs=oldHouseMaster.getFeatures().split(",");
				for(String str:strs){
					featureNames+= ((Map<String,Object>)groupTypes.get(BusinessConst.FEATURES)).get(str)+",";
				}
				
			}
			
			if(StringUtils.isNotBlank(featureNames)){
				oldHouseMaster.setFeatureNames(featureNames.substring(0,featureNames.length()-1));
			}
		}
		
		if(groupTypes.get(BusinessConst.FEATURES)!=null){//供暖方式
			model.addAttribute("heatingModeMap", groupTypes.get(BusinessConst.HEATING_MODE));
		}
		
		if(groupTypes.get(BusinessConst.WY_TYPE)!=null){//物业类型
			model.addAttribute("wyTypeMap", groupTypes.get(BusinessConst.WY_TYPE));
		}
		
		if(groupTypes.get(BusinessConst.RENVATION)!=null){//装修
			model.addAttribute("renovationMap", groupTypes.get(BusinessConst.RENVATION));
		}
		
		if(groupTypes.get(BusinessConst.HOUSE_TYPE)!=null){//房屋类型
			model.addAttribute("houseTypeMap", groupTypes.get(BusinessConst.HOUSE_TYPE));
		}
		
		//推荐
		List<OldHouseRecommendVo> recommendVos=oldHouseMasterService.getOldHouseRecommendById(id);
		model.addAttribute("recommendVos",recommendVos);
		
		//经纪人
		if(oldHouseMaster!=null || oldHouseMaster.getFatherId()!=0){
			Map<String,Object> agentMap=new HashMap<String,Object>();
			agentMap.put("fatherId", oldHouseMaster.getId());
			List<OldHouseAgentPo> listAgents=oldHouseAgentService.getJoinMasterLefJoinAgent(agentMap);
			
			model.addAttribute("listAgents",listAgents);
			
		}
		if(UserUtil.getCurrentUserLoginId(request)!=null){
			int followId=agentService.findIsFollow(id, UserUtil.getCurrentUserLoginId(request), FollowEnums.OLDHOUSE.getValue());
			model.addAttribute("followId",followId);
			
			//浏览历史
			userService.updateHandleLookHistory(FollowEnums.OLDHOUSE.getValue(),id, UserUtil.getCurrentUserLoginId(request));
		}
		model.addAttribute("oldHouseMaster", oldHouseMaster);
		return "ershoufang/master/master_detail";
	}
	
	public static void main(String[] args) {
		Pattern p = Pattern.compile("[pmx([0-9]+)][pmn([0-9]+)]");
		Matcher m = p.matcher("pmn1pmx1o421");
		if (m.find(0)) {
			System.out.println(m.group(0));
		}
	}
}

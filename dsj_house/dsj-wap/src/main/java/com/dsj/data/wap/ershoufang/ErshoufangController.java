package com.dsj.data.wap.ershoufang;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.FrontAreaEnum;
import com.dsj.common.enums.FrontBuildYearEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.enums.FrontOrientationsEnum;
import com.dsj.common.enums.FrontPriceEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.ershoufang.vo.AuxiliaryVo;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.vo.OldHouseMasterDetailVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
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
@RequestMapping(value = "ershoufang")
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
	UserService userService;
	
	@Autowired
	AgentService agentService;
	
	@Autowired
	HouseDirectoryService houseDirectoryService;
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	static Map<String, Object> priceMap = Maps.newLinkedHashMap();
	static Map<String, Object> areaMap = Maps.newLinkedHashMap();
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();
	static Map<String, Object> buildYearMap = Maps.newLinkedHashMap();
	static Map<String, Object> floorTypeMap = Maps.newLinkedHashMap();
	static Map<String,Object> companyTypeMap= Maps.newLinkedHashMap();
	static Map<String,Object> orientationsMap= Maps.newLinkedHashMap();
	
	static {
		priceMap=FrontPriceEnum.toMap();
		areaMap=FrontAreaEnum.toMap();
		roomMap=FrontRoomEnum.toMap();
		buildYearMap=FrontBuildYearEnum.toMap();
		floorTypeMap=FloorTypeEnum.toMap();
		companyTypeMap=FrontCompanyTypeEnum.toMap();
		orientationsMap=FrontOrientationsEnum.toMap();
	}

	@RequestMapping
	@ResponseBody
	public AjaxResultVo ershoufangIndex(@RequestBody ErShoufangQueryVo queryVo) {
		PageBean page = ershoufangIndexService.getErshoufangSolr( 10,queryVo);
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		//初始化二手房检索条件
		Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
		resultMap.put("floorTypeMap",JsonTools.mapToKvJson( floorTypeMap));
		resultMap.put("companyTypeMap", JsonTools.mapToKvJson( companyTypeMap));
		resultMap.put("featuresMap", JsonTools.mapToKvJson((Map<String,Object>)mapSearch.get(BusinessConst.FEATURES)));
		
		Map<String,Object> resultDate=new HashMap<String,Object>();
		
		resultDate.put("page", page);
		resultDate.put("resultMap", resultMap);
		
		AjaxResultVo ajaxVo=new AjaxResultVo();
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(resultDate);
		return ajaxVo;
	}
	
	@RequestMapping(value="conditions")
	@ResponseBody
	public AjaxResultVo ershoufangConditions () {
		
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		//初始化二手房检索条件
		Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
		resultMap.put("floorTypeMap",JsonTools.mapToKvJson( floorTypeMap));
		resultMap.put("featuresMap", JsonTools.mapToKvJson((Map<String,Object>)mapSearch.get(BusinessConst.FEATURES)));
		
		if(mapSearch.get(BusinessConst.ACHTYPE)!=null){//建筑类
			resultMap.put("buildType", JsonTools.mapToKvJson((Map<String,Object>)mapSearch.get(BusinessConst.ACHTYPE)));
		}
		if(mapSearch.get(BusinessConst.WY_TYPE)!=null){//物业类型
			resultMap.put("wyType",JsonTools.mapToKvJson((Map<String,Object>) mapSearch.get(BusinessConst.WY_TYPE)));
		}
		
		if(mapSearch.get(BusinessConst.RENVATION)!=null){//装修情况
			resultMap.put("renvation", JsonTools.mapToKvJson((Map<String,Object>)mapSearch.get(BusinessConst.RENVATION)));
		}
		
		if(mapSearch.get(BusinessConst.FEATURES)!=null){//装修情况
			resultMap.put("features", JsonTools.mapToKvJson((Map<String,Object>)mapSearch.get(BusinessConst.FEATURES)));
		}
		
		//地区
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("parentId", BusinessConst.BEIJING_AREA_CODE);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> areaFirstList = areaService.listBy(paramMap);
		
		//地铁
		List<SubwayPo> subwayLineList=subwayService.getByAllsubwayLine();
		
		resultMap.put("priceMap", JsonTools.mapToKvJson(priceMap));
		resultMap.put("areaMap", JsonTools.mapToKvJson(areaMap));
		resultMap.put("roomMap",JsonTools.mapToKvJson(roomMap));
		resultMap.put("buildYearMap",JsonTools.mapToKvJson(buildYearMap));
		resultMap.put("floorTypeMap",JsonTools.mapToKvJson(floorTypeMap));
		resultMap.put("companyTypeMap", JsonTools.mapToKvJson(companyTypeMap));
		
		resultMap.put("orientations",  JsonTools.mapToKvJson(orientationsMap));
		resultMap.put("areaFirstList",areaFirstList);
		resultMap.put("subwayLineList",subwayLineList);
		
		AjaxResultVo ajaxVo=new AjaxResultVo();
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(resultMap);
		return ajaxVo;
	}
	
	
	@RequestMapping(value="detail")
	@ResponseBody
	public AjaxResultVo ershoufangDetail(Long id,HttpServletRequest request){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		OldHouseMasterDetailVo detailVo= oldHouseMasterService.getByWapOldMasterId(id);
		detailVo.setChecked(false);
		if(UserUtil.getCurrentUser(request)!=null){
			int followId=agentService.findIsFollow(id, UserUtil.getCurrentUserLoginId(request), FollowEnums.OLDHOUSE.getValue());
			if(followId>0){
				detailVo.setChecked(true);
			}
			//浏览历史
			userService.updateHandleLookHistory(FollowEnums.OLDHOUSE.getValue(),id, UserUtil.getCurrentUserLoginId(request));
			detailVo.setLoginStatus(true);
		}else{
			detailVo.setLoginStatus(false);
		}
		
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(detailVo);
		return ajaxVo;
	}

	
	@RequestMapping(value="auxiliary")
	@ResponseBody
	public AjaxResultVo auxiliary(Long id,Integer type,HttpServletRequest request){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		AuxiliaryVo vo=new AuxiliaryVo();
		if(type==1){
			HouseDirectoryPo houseDirectory=houseDirectoryService.getById(id);
			vo.setAccuracy(houseDirectory.getDimension());
			vo.setDimension(houseDirectory.getAccuracy());
			vo.setName(houseDirectory.getSprayName());
		}else{
			NewHouseDirectoryAuthPo newHouseDirectoryAuth=newHouseDirectoryAuthService.getById(id);
			vo.setAccuracy(newHouseDirectoryAuth.getAccuracy());
			vo.setDimension(newHouseDirectoryAuth.getDimension());
			vo.setName(newHouseDirectoryAuth.getName());
		}
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(vo);
		return ajaxVo;
	}


}

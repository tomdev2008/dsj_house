package com.dsj.data.wap.newHouse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FrontRefrencePriceEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.enums.FrontWyTypeEnum;
import com.dsj.common.enums.NewHouseTsEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseTypeCountVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.solr.service.NewHouseIndexService;
import com.dsj.modules.solr.vo.newHouse.NewHouseQueryVo;
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;
import com.google.common.collect.Maps;

/**
 * 新房编辑管理
 */
@Controller
@RequestMapping(value = "newHouse")
public class NewHouseController {
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseController.class);

	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;

	@Autowired
	private NewHousePictureAuthService newHousePictureAuthService;

	@Autowired
	private NewHouseTypeAuthService newHouseTypeAuthService;
	
	@Autowired
	private AgentService agentService;
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private HouseNewsService houseNewsservice; 
	
	@Autowired
	private GroupTypeService groupTypeService;
	
	@Autowired
	private AreaService areaService;

	@Autowired
	private TradeAreaService tradeAreaService;
	
	@Autowired
	private SubwayService subwayService;
	
	@Autowired
	private NewHouseIndexService newHouseIndexService;
	
	@Autowired
	UserService userService;
	
	//价格
	static Map<String, Object> priceMap = Maps.newLinkedHashMap();
	//户型
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();	
	// 前端10个特色
		static Map<String, Object> tsMap = Maps.newLinkedHashMap();
		// 类型
		static Map<String, Object> wyMap = Maps.newLinkedHashMap();
	static {
		priceMap=FrontRefrencePriceEnum.toMap();
		roomMap=FrontRoomEnum.toMap();
		
		tsMap = NewHouseTsEnum.toMap();
		wyMap = FrontWyTypeEnum.toMap();
		
	}
	

	@RequestMapping
	@ResponseBody
	public AjaxResultVo ershoufangIndex( NewHouseQueryVo queryVo) {
		PageBean page = newHouseIndexService.getNewHouseSolr(8,queryVo);
		Map<String,Object> resultMap=new HashMap<String,Object>();
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
	public AjaxResultVo newHouseConditions () {
		
		Map<String,Object> resultMap=new HashMap<String,Object>();
		
		//初始化二手房检索条件
		Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
		
		
		//地区
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("parentId", BusinessConst.BEIJING_AREA_CODE);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> areaFirstList = areaService.listBy(paramMap);
		
		//地铁
		List<SubwayPo> subwayLineList=subwayService.getByAllsubwayLine();
		
		resultMap.put("priceMap", JsonTools.mapToKvJson(priceMap));
	
		resultMap.put("roomMap",JsonTools.mapToKvJson(roomMap));
		
		resultMap.put("wyTypeMap",JsonTools.mapToKvJson(wyMap));
		
		resultMap.put("dictraitMap", JsonTools.mapToKvJson(tsMap));

	
	
		resultMap.put("areaFirstList",areaFirstList);
		resultMap.put("subwayLineList",subwayLineList);
		
		AjaxResultVo ajaxVo=new AjaxResultVo();
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(resultMap);
		return ajaxVo;
	}
	
	
	
	// 新房详情
	@RequestMapping("index_detail")
	@ResponseBody
	public AjaxResultVo indexDetail(Model model, Long id,HttpServletRequest request) {
		AjaxResultVo ajax = new AjaxResultVo();
		
		NewHouseFrontVo vo = new NewHouseFrontVo();
		
		try {
			vo = newHouseDirectoryAuthService.getIndexNewHouseFrontVo(id);
			vo.setChecked(false);
			if(UserUtil.getCurrentUser(request)!=null){
				 userService.updateHandleLookHistory(FollowEnums.NEWHOUSE.getValue(),id,UserUtil.getCurrentUser(request).getId());    
				int followId=agentService.findIsFollow(id, UserUtil.getCurrentUserLoginId(request), FollowEnums.NEWHOUSE.getValue());
				if(followId>0){
					vo.setChecked(true);
				}
				vo.setLoginStatus(true);
				
				if (UserType.AGENT.getValue() == UserUtil.getCurrentUser(request).getUserType()) {
					vo.setTabType(3);
				}else {
					vo.setTabType(4);
				}
			}else{
				vo.setLoginStatus(false);
			}
			
			
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(vo);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("详情首页查询失败", e);
		}
		
		return ajax;
	}

	// 新房详情
	@RequestMapping("detail")
	@ResponseBody
	public AjaxResultVo detail(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		NewHouseFrontVo vo = new NewHouseFrontVo();
		try {
			vo = newHouseDirectoryAuthService.getNewHouseFrontVo(id);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(vo);
		} catch (Exception e) {
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("详情查询失败", e);
		}
		
		return ajax;
	}



	// ajax获取户型
	@RequestMapping("newHouse_type")
	@ResponseBody
	public AjaxResultVo newHouseType(Integer pageFirst, Integer room, Long newHouseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			PageParam pageParam = new PageParam(pageFirst / 100 + 1, 100);
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("dicId", newHouseId);
		
			
			if(room!=null && room==3 ){
				map.put("room3", room);
			}else{
				map.put("room", room);
			}
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			PageBean pageBean = newHouseTypeAuthService.listPage(pageParam, map);
			
			Map<String, Object> countResultMap=new HashMap<String, Object>();
			
			HashMap hashMap= new HashMap<String, Object>();
			hashMap.put("houseId", newHouseId);
			List<NewHouseTypeCountVo> types= newHouseTypeAuthService.getHouseTypeCount(hashMap);
		
			Integer countType=0;
			Integer threeTypeCount=0;
			Integer oneTypeCount=0;
			Integer twoTypeCount=0;
			for(NewHouseTypeCountVo vo:types){
				if(vo.getRoom()==1){
					oneTypeCount=vo.getCount();
				}
				if(vo.getRoom()==2){
					twoTypeCount= vo.getCount();
				}
				if(vo.getRoom()>=3){
					threeTypeCount+=vo.getCount();
				}
				countType+=vo.getCount();
			}
			
			countResultMap.put("threeType", threeTypeCount);
			countResultMap.put("twoType", twoTypeCount);
			countResultMap.put("oneType", oneTypeCount);
			
			countResultMap.put("allType", countType);
			pageBean.setCountResultMap(countResultMap);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(pageBean);
		} catch (Exception e) {
			LOGGER.error("楼盘户型查询失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}


	// 新房户型
	@RequestMapping("houseType")
	@ResponseBody
	public AjaxResultVo houseType( Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		NewHouseTypeAuthPo newHouseTypeAuth=newHouseTypeAuthService.getById(id);
		ajax.setStatusCode(StatusCode.SUCCESS);
		ajax.setData(newHouseTypeAuth);
		return ajax;
	}

	// ajax获取新房图片
	@RequestMapping("newHouse_picture")
	@ResponseBody
	public AjaxResultVo newHousePicture(Long id) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {

			List<NewHousePictureFrontVo> list = newHousePictureAuthService.getPictureList(id);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(list);
		} catch (Exception e) {
			LOGGER.error("楼盘相册查询失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}


}

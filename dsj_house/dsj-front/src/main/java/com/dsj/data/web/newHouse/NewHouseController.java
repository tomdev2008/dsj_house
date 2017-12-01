package com.dsj.data.web.newHouse;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

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

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FrontAreaEnum;
import com.dsj.common.enums.FrontRefrencePriceEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.enums.FrontWyTypeEnum;
import com.dsj.common.enums.NewHouseTsEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.ClickHouseNewsPo;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.service.ClickHouseNewsService;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.comment.vo.HouseNewsVo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseLikeVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.solr.service.NewHouseIndexService;
import com.dsj.modules.solr.vo.newHouse.NewHouseQueryVo;
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentVo;
import com.google.common.collect.Maps;

/**
 * 新房编辑管理
 */
@Controller
@RequestMapping(value = "front/newHouse")
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
	private UserService userService;

	@Autowired
	private SubwayService subwayService;

	@Autowired
	private NewHouseIndexService newHouseIndexService;
	
	@Autowired
	private ClickHouseNewsService clickHouseNewsService;

	// 价格
	static Map<String, Object> priceMap = Maps.newLinkedHashMap();
	// 户型
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();
	// 前端10个特色
	static Map<String, Object> tsMap = Maps.newLinkedHashMap();
	// 类型
	static Map<String, Object> wyMap = Maps.newLinkedHashMap();

	static {
		priceMap = FrontRefrencePriceEnum.toMap();
		roomMap = FrontRoomEnum.toMap();
		tsMap = NewHouseTsEnum.toMap();
		wyMap = FrontWyTypeEnum.toMap();
	}

	// 新房列表页
	@RequestMapping("list")
	public String newHouseList(String areaCode2, String areaCode3, Long tradingAreaId, String subwayLineId,
			String subwayId, @RequestParam(value = "params", defaultValue = "") String params, String k, String kw, Model model,
			String line) {
		String url = "";
		// 初始化新房检索条件
		Map<String, Object> mapSearch = groupTypeService.getHouseGroupType();
		addSearchModel(mapSearch, model);

		// 参数处理
		NewHouseQueryVo queryVo = dealParams(params, areaCode3, tradingAreaId, subwayLineId, subwayId, line, model,
				mapSearch, k,kw);

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

		// 地铁查询
		List<SubwayPo> subwayLineList = subwayService.getByAllsubwayLine();
		model.addAttribute("subwayLineList", subwayLineList);
		if (StringUtils.isNotBlank(subwayLineId)) {
			List<SubwayPo> subwayList = subwayService.getByPid(subwayLineId);
			model.addAttribute("subwayList", subwayList);
		}

		if (tradingAreaId != null) {
			queryVo.setTradingAreaId(tradingAreaId);
		}

		PageBean page = newHouseIndexService.getNewHouseSolr(30, queryVo);
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
		return "newHouse/newHouse_list";
	}
	
		
	// seo参数优化
	private NewHouseQueryVo dealParams(String params, String areaCode3, Long tradingAreaId, String subwayLineId,
			String subwayId, String line, Model model, Map<String, Object> mapSearch, String k, String kw) {
		NewHouseQueryVo vo = new NewHouseQueryVo();
		Map<String, Object> conditionMap = Maps.newLinkedHashMap();

		if(tradingAreaId!=null){
			conditionMap.put(areaService.getById(Long.parseLong(areaCode3)).getName(), params);
			conditionMap.put(tradeAreaService.getById(tradingAreaId).getName(),"d"+areaCode3+"/"+params);
			params="d"+areaCode3+"_"+tradingAreaId+"/"+params;
			vo.setTradingAreaId(tradingAreaId);
		
		}
		
		if(StringUtils.isNotBlank(areaCode3)&&tradingAreaId==null){
			conditionMap.put(areaService.getById(Long.parseLong(areaCode3)).getName(), params);
			vo.setAreaCode3(areaCode3);
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
			model.addAttribute("url_pg",
					"/front/newHouse/list/" + params.replace(m.group(0), "") + BusinessConst.PAGE_NUM);
			params = params.replace(m.group(0), "");

		} else {
			vo.setPageNum(CommConst.PAGE_NUM);
			model.addAttribute("url_pg", "/front/newHouse/list/" + params + BusinessConst.PAGE_NUM);

		}

		if (StringUtils.isNotBlank(params)) {
			String url = "";
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
					conditionMap.put(FrontRefrencePriceEnum.getEnum(price).getDesc(), url);
				}else{
					model.addAttribute("url_pr",params);
				}
			}

			// 地区BUILD_AREA_P
			p = Pattern.compile(BusinessConst.BUILD_AREA_P);
			m = p.matcher(params);

			if (m.find(0)) {
				Integer buildArea = Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_AREA, ""));
				vo.setBuildArea(buildArea);
				url = params.replace(m.group(0), "");
				model.addAttribute("url_a", url);
				conditionMap.put(FrontAreaEnum.getEnum(buildArea).getDesc(), url);
			} else {
				model.addAttribute("url_a", params);
			}

			// 户型 ROOM_P
			p = Pattern.compile(BusinessConst.ROOM_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer room = Integer.parseInt(m.group(0).replace(BusinessConst.ROOM, ""));
				vo.setRoom(room);
				url = params.replace(m.group(0), "");
				model.addAttribute("url_rm", params.replace(m.group(0), ""));
				conditionMap.put(FrontRoomEnum.getEnum(room).getDesc(), url);
			} else {
				model.addAttribute("url_rm", params);
			}

			// 特色 TS
			p = Pattern.compile(BusinessConst.TS_P);
			m = p.matcher(params);
			if (m.find(0)) {
				String ts = m.group(0).replace(BusinessConst.TS, "");
				vo.setDictrait(ts);
				url = params.replace(m.group(0), "");
				model.addAttribute("url_ts", params.replace(m.group(0), ""));

				Map<String, String> cmap = (Map<String, String>) mapSearch.get(BusinessConst.DICTRAIT);
				
				conditionMap.put(cmap.get(ts), url);
			} else {
				model.addAttribute("url_ts", params);
			}

			// 物业 WY_TYPE_SEO
			p = Pattern.compile(BusinessConst.WY_TYPE_SEO_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer wyType = Integer.parseInt(m.group(0).replace(BusinessConst.WY_TYPE_SEO, ""));
				vo.setWyType(wyType);
				url = params.replace(m.group(0), "");
				model.addAttribute("url_wy", params.replace(m.group(0), ""));
				Map<String, String> cmap = (Map<String, String>) mapSearch.get(BusinessConst.WY_TYPE);
				conditionMap.put(cmap.get(wyType.toString()), url);
			} else {
				model.addAttribute("url_wy", params);
			}

			// 排序 ORDER_OD_P
			p = Pattern.compile(BusinessConst.ORDER_OD_P);
			m = p.matcher(params);
			if (m.find(0)) {
				Integer ordering = Integer.parseInt(m.group(0).replace(BusinessConst.ORDER_OD, ""));
				vo.setOrdering(ordering);
				url = params.replace(m.group(0), "");
				model.addAttribute("url_orderding", params.replace(m.group(0), ""));
				model.addAttribute("ordering", ordering);
			} else {
				model.addAttribute("url_orderding", params);
			}

			model.addAttribute("conditionMap", conditionMap);

		}
		vo.setKeywords(k);
		model.addAttribute("keywords", k);
		vo.setKw(kw);
		model.addAttribute("kw", kw);
		if (StringUtils.isNotBlank(k)) {
			model.addAttribute("url_kw", "?k" + k);
		}
		return vo;
	}

	private void addSearchModel(Map<String, Object> mapSearch, Model model) {
		model.addAttribute("wyType", wyMap);
		model.addAttribute("priceMap", priceMap);
		model.addAttribute("roomMap", roomMap);
		model.addAttribute("tsMap", tsMap);

	}

	// 新房详情
	@RequestMapping("index_detail")
	public String indexDetail(Model model, Long id,HttpServletRequest request) {
		NewHouseFrontVo vo = new NewHouseFrontVo();
		try {
			vo = newHouseDirectoryAuthService.getIndexNewHouseFrontVo(id);
			if(UserUtil.getCurrentUserLoginId(request)!=null){
				 userService.updateHandleLookHistory(FollowEnums.NEWHOUSE.getValue(),id,UserUtil.getCurrentUser(request).getId());    
				int followId=agentService.findIsFollow(id, UserUtil.getCurrentUserLoginId(request), FollowEnums.NEWHOUSE.getValue());
				model.addAttribute("followId",followId);
			}
		} catch (Exception e) {
			LOGGER.error("详情首页查询失败", e);
		}
		model.addAttribute("newHouse", vo);
		model.addAttribute("newHouseId", vo.getId().toString());
		return "newHouse/newHouse_index_detail";
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

	// ajax获取户型
	@RequestMapping("newHouse_type")
	@ResponseBody
	public AjaxResultVo newHouseType(Integer pageFirst, Integer pageSize, Integer room, Long newHouseId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			PageParam pageParam = new PageParam(pageFirst / pageSize + 1, pageSize);
			List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.findHouseTypeList(room, newHouseId);
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(houseTypeList);
		} catch (Exception e) {
			LOGGER.error("楼盘户型查询失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	// ajax获取新房动态
	@RequestMapping("newHouse_dynamic_page")
	@ResponseBody
	public AjaxResultVo newHouseDynamicPage() {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			ajax.setStatusCode(StatusCode.SUCCESS);
			ajax.setData(null);
		} catch (Exception e) {
			LOGGER.error("楼盘户型查询失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	// 新房户型
	@RequestMapping("house_type")
	public String houseType(Model model, Long id) {
		try {
			model.addAttribute("newHouseId", id.toString());
			Map<String, Object> map = new HashMap<String, Object>();

			map = newHouseTypeAuthService.getNewHouseTypeListAndCountById(id);
			model.addAttribute("houseTypeMap", map);
			model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		} catch (Exception e) {
			LOGGER.error("新房详情查询失败", e);
		}
		return "newHouse/newHouseHouseType";
	}

	// 新房相册
	@RequestMapping("house_picture")
	public String housePicture(Model model, Long id) {

		try {
			model.addAttribute("newHouseId", id.toString());
			Map<String, Object> map = new HashMap<String, Object>();
			map = newHousePictureAuthService.getNewHousePictureListAndCountById(id);
			model.addAttribute("housePictureMap", map);
			model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		} catch (Exception e) {
			LOGGER.error("新房详情查询失败", e);
		}

		return "newHouse/newHousePicture";
	}

	// 新房动态列表
	@RequestMapping("house_dynamic")
	public String houseDynamic(
			@RequestParam(value = "pageNumber", required = true, defaultValue = "1") Integer pageNumber, Model model,
			Long id) {
		Integer pageSize = 5;
		Map<String, Object> requestMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam(pageNumber, pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("upDownLine", HouseNewsStatusEnum.UP.getValue());
		requestMap.put("houseId", id);
		PageBean page = null;
		NewHouseFrontVo vo = new NewHouseFrontVo();
		try {
			page = houseNewsservice.listPCHouseNewsPage(pageParam, requestMap);
			vo = newHouseDirectoryAuthService.getIndexNewHouseFrontVo(id);
		} catch (Exception e) {
			LOGGER.error("经纪人动态查询异常", e);
		}
		
		model.addAttribute("url_pg", "/front/newHouse/house_dynamic?id=" + id + "&pageNumber=");
		model.addAttribute("page", page);
		model.addAttribute("newsList", page.getRecordList());
		model.addAttribute("newHouseId", id.toString());
		model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		model.addAttribute("newHouse", vo);
		
		return "newHouse/newHouse_dynamic";
	}

	// 新房动态详情
	@RequestMapping("house_dynamic_detail")
	public String house_dynamic_detail(Model model, Long id, Long newHouseId) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", id);
		HouseNewsVo houseNews = null;
		NewHouseFrontVo vo = new NewHouseFrontVo();
		try {
			houseNews = houseNewsservice.getOneBy(map);
			vo = newHouseDirectoryAuthService.getIndexNewHouseFrontVo(newHouseId);
			//动态详情被点击统计
			Integer pccount = houseNews.getPccount();
			if (pccount == null) {
				pccount = 0 ;
			}
			houseNews.setPccount(pccount+1);
			houseNewsservice.updateDynamic(houseNews);
		} catch (Exception e) {
			LOGGER.error("新房动态详情查询异常", e);
		}
		map.clear();
		map.put("houseId", newHouseId);
		map.put("auditStatus", HouseNewsStatusEnum.AUDIT_SUCCESS.getValue());
		map.put("upDownLine", HouseNewsStatusEnum.UP.getValue());
		map.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		List<HouseNewsVo> relatedNewsList = houseNewsservice.getRelatedNews(map);
		model.addAttribute("houseNews", houseNews);
		model.addAttribute("newHouseId", newHouseId.toString());
		model.addAttribute("newHouseName", getNewHouseMsg(newHouseId).getName());
		model.addAttribute("newHouse", vo);
		model.addAttribute("relatedNewsList", relatedNewsList);
		return "newHouse/newHouse_dynamic_detail";
	}
	
	// 动态点击记录获取
	@RequestMapping("house_dynamic_data")
	@ResponseBody
	public AjaxResultVo house_dynamic_data(HttpServletRequest request) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (UserUtil.getCurrentUser(request)!=null) {
				Map<String, Object> map = new HashMap<>();
				map.put("createUser", UserUtil.getCurrentUser(request).getId());
				map.put("objType", 1);
				List<ClickHouseNewsPo> list = clickHouseNewsService.listBy(map);
				ajax.setStatusCode(StatusCode.SUCCESS);
				ajax.setData(list);
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("动态点击记录获取失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	// 更新点击记录获取
	@RequestMapping("house_dynamic_update")
	@ResponseBody
	public AjaxResultVo house_dynamic_update(HttpServletRequest request,String newsStr) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (UserUtil.getCurrentUser(request)!=null && StringUtils.isNotEmpty(newsStr)) {
				String[] ids = newsStr.split(",");
				Map<String, Object> map = null;
				for (int i = 0; i < ids.length; i++) {
					if (StringUtils.isNotEmpty(ids[i])) {
						map = new HashMap<>();
						map.put("createUser", UserUtil.getCurrentUser(request).getId());
						map.put("objType", 1);
						map.put("objId", ids[i]);
						List<ClickHouseNewsPo> newsList = clickHouseNewsService.listBy(map);
						if (newsList != null && newsList.size() == 0) {
							ClickHouseNewsPo newsPo = new ClickHouseNewsPo();
							newsPo.setCreateUser(UserUtil.getCurrentUser(request).getId().intValue());
							newsPo.setObjType(1);
							newsPo.setObjId(Long.parseLong(ids[i]));
							newsPo.setCreateTime(new Date());
							clickHouseNewsService.saveDynamic(newsPo);
						}
					}
				}
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
		} catch (Exception e) {
			LOGGER.error("动态点击记录获取失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	// 新房点评 type 3：经纪人对楼盘点评；4：普通用户点评
	@RequestMapping("house_comment")
	public String houseComment(
			@RequestParam(value = "pageNumber", required = true, defaultValue = "1") Integer pageNumber, Model model,
			Integer type, Long id) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("houseId", id);
		requestMap.put("objectType", CommentEnum.COMMENT__HOUSE_REMARK.getCode());
		Long countJjr = commentService.getCountByHT(requestMap);
		requestMap.put("objectType", CommentEnum.GENERAL_HOUSE_REMARK.getCode());
		Long countPtyh = commentService.getCountByHT(requestMap);
		model.addAttribute("houseId", id);
		model.addAttribute("type", type);
		model.addAttribute("newHouseId", id.toString());
		model.addAttribute("countJjr", countJjr);
		model.addAttribute("countPtyh", countPtyh);
		model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		return "newHouse/newHouse_comment";
	}

	// 新房详情
	@RequestMapping("detail")
	public String detail(Model model, Long id) {
		NewHouseFrontVo vo = new NewHouseFrontVo();
		try {
			vo = newHouseDirectoryAuthService.getNewHouseFrontVo(id);
		} catch (Exception e) {
			LOGGER.error("详情页查询失败", e);
		}
		model.addAttribute("newHouse", vo);
		model.addAttribute("newHouseId", id.toString());
		model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		return "newHouse/newHouse_detail";
	}

	// 经纪人列表
	@RequestMapping("newHouse_agent")
	public String newHouseAgent(Model model, Long id) {
		List<AgentVo> agentList = new ArrayList<AgentVo>();
		try {
			agentList =  agentService.listByNewHouseId(id);
		} catch (Exception e) {
			LOGGER.error("经纪人列表查询失败", e);
		}
		model.addAttribute("agentList", agentList);
		model.addAttribute("newHouseId", id.toString());
		model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		return "newHouse/newHouse_agent";
	}

	// 新房周边配套
	@RequestMapping("map")
	public String map(Model model, Long id) {
		NewHouseDirectoryAuthPo po = new NewHouseDirectoryAuthPo();
		try {
			po = newHouseDirectoryAuthService.getById(id);
		} catch (Exception e) {
			LOGGER.error("详情页查询失败", e);
		}
		model.addAttribute("newHouse", po);
		model.addAttribute("newHouseId", id.toString());
		model.addAttribute("newHouseName", getNewHouseMsg(id).getName());
		return "newHouse/newHouse_map";
	}

	// 新房信息
	public NewHouseDirectoryAuthPo getNewHouseMsg(Long id) {
		NewHouseDirectoryAuthPo po = new NewHouseDirectoryAuthPo();
		try {
			po = newHouseDirectoryAuthService.getById(id);
		} catch (Exception e) {
			LOGGER.error("新房信息查询失败", e);
		}
		return po;
	}
	
	// 新房列表页
			@RequestMapping("jdlist")
			public String jdNewHouseList(String areaCode2, String areaCode3, Long tradingAreaId, String subwayLineId,
					String subwayId, @RequestParam(value = "params", defaultValue = "") String params, String k, String kw, Model model,
					String line) {
				String url = "";
				// 初始化新房检索条件
				Map<String, Object> mapSearch = groupTypeService.getHouseGroupType();
				addSearchModel(mapSearch, model);

				// 参数处理
				NewHouseQueryVo queryVo = dealParams_jd(params, areaCode3, tradingAreaId, subwayLineId, subwayId, line, model,
						mapSearch, k,kw);

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

				// 地铁查询
				List<SubwayPo> subwayLineList = subwayService.getByAllsubwayLine();
				model.addAttribute("subwayLineList", subwayLineList);
				if (StringUtils.isNotBlank(subwayLineId)) {
					List<SubwayPo> subwayList = subwayService.getByPid(subwayLineId);
					model.addAttribute("subwayList", subwayList);
				}

				if (tradingAreaId != null) {
					queryVo.setTradingAreaId(tradingAreaId);
				}

				PageBean page = newHouseIndexService.getNewHouseSolr(30, queryVo);
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
				return "newHouse/newHouse_list_jd";
			}

			// seo参数优化
			private NewHouseQueryVo dealParams_jd(String params, String areaCode3, Long tradingAreaId, String subwayLineId,
					String subwayId, String line, Model model, Map<String, Object> mapSearch, String k, String kw) {
				NewHouseQueryVo vo = new NewHouseQueryVo();
				Map<String, Object> conditionMap = Maps.newLinkedHashMap();

				if(tradingAreaId!=null){
					conditionMap.put(areaService.getById(Long.parseLong(areaCode3)).getName(), params);
					conditionMap.put(tradeAreaService.getById(tradingAreaId).getName(),"d"+areaCode3+"/"+params);
					params="d"+areaCode3+"_"+tradingAreaId+"/"+params;
					vo.setTradingAreaId(tradingAreaId);
				
				}
				
				if(StringUtils.isNotBlank(areaCode3)&&tradingAreaId==null){
					conditionMap.put(areaService.getById(Long.parseLong(areaCode3)).getName(), params);
					vo.setAreaCode3(areaCode3);
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
					model.addAttribute("url_pg",
							"/front/newHouse/jdlist/" + params.replace(m.group(0), "") + BusinessConst.PAGE_NUM);
					params = params.replace(m.group(0), "");

				} else {
					vo.setPageNum(CommConst.PAGE_NUM);
					model.addAttribute("url_pg", "/front/newHouse/jdlist/" + params + BusinessConst.PAGE_NUM);

				}

				if (StringUtils.isNotBlank(params)) {
					String url = "";
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
							conditionMap.put(FrontRefrencePriceEnum.getEnum(price).getDesc(), url);
						}else{
							model.addAttribute("url_pr",params);
						}
					}

					// 地区BUILD_AREA_P
					p = Pattern.compile(BusinessConst.BUILD_AREA_P);
					m = p.matcher(params);

					if (m.find(0)) {
						Integer buildArea = Integer.parseInt(m.group(0).replace(BusinessConst.BUILD_AREA, ""));
						vo.setBuildArea(buildArea);
						url = params.replace(m.group(0), "");
						model.addAttribute("url_a", url);
						conditionMap.put(FrontAreaEnum.getEnum(buildArea).getDesc(), url);
					} else {
						model.addAttribute("url_a", params);
					}

					// 户型 ROOM_P
					p = Pattern.compile(BusinessConst.ROOM_P);
					m = p.matcher(params);
					if (m.find(0)) {
						Integer room = Integer.parseInt(m.group(0).replace(BusinessConst.ROOM, ""));
						vo.setRoom(room);
						url = params.replace(m.group(0), "");
						model.addAttribute("url_rm", params.replace(m.group(0), ""));
						conditionMap.put(FrontRoomEnum.getEnum(room).getDesc(), url);
					} else {
						model.addAttribute("url_rm", params);
					}

					// 特色 TS
					p = Pattern.compile(BusinessConst.TS_P);
					m = p.matcher(params);
					if (m.find(0)) {
						String ts = m.group(0).replace(BusinessConst.TS, "");
						vo.setDictrait(ts);
						url = params.replace(m.group(0), "");
						model.addAttribute("url_ts", params.replace(m.group(0), ""));

						Map<String, String> cmap = (Map<String, String>) mapSearch.get(BusinessConst.DICTRAIT);
						
						conditionMap.put(cmap.get(ts), url);
					} else {
						model.addAttribute("url_ts", params);
					}

					// 物业 WY_TYPE_SEO
					p = Pattern.compile(BusinessConst.WY_TYPE_SEO_P);
					m = p.matcher(params);
					if (m.find(0)) {
						Integer wyType = Integer.parseInt(m.group(0).replace(BusinessConst.WY_TYPE_SEO, ""));
						vo.setWyType(wyType);
						url = params.replace(m.group(0), "");
						model.addAttribute("url_wy", params.replace(m.group(0), ""));
						Map<String, String> cmap = (Map<String, String>) mapSearch.get(BusinessConst.WY_TYPE);
						conditionMap.put(cmap.get(wyType.toString()), url);
					} else {
						model.addAttribute("url_wy", params);
					}

					// 排序 ORDER_OD_P
					p = Pattern.compile(BusinessConst.ORDER_OD_P);
					m = p.matcher(params);
					if (m.find(0)) {
						Integer ordering = Integer.parseInt(m.group(0).replace(BusinessConst.ORDER_OD, ""));
						vo.setOrdering(ordering);
						url = params.replace(m.group(0), "");
						model.addAttribute("url_orderding", params.replace(m.group(0), ""));
						model.addAttribute("ordering", ordering);
					} else {
						model.addAttribute("url_orderding", params);
					}

					model.addAttribute("conditionMap", conditionMap);

				}
				vo.setKeywords(k);
				model.addAttribute("keywords", k);
				vo.setKw(kw);
				model.addAttribute("kw", kw);
				if (StringUtils.isNotBlank(k)) {
					model.addAttribute("url_kw", "?k" + k);
				}
				return vo;
			}
			

}

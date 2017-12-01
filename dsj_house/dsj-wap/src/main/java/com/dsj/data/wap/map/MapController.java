package com.dsj.data.wap.map;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FloorTypeEnum;
import com.dsj.common.enums.FrontAreaEnum;
import com.dsj.common.enums.FrontBuildYearEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.enums.FrontPriceEnum;
import com.dsj.common.enums.FrontRefrencePriceEnum;
import com.dsj.common.enums.FrontRentAreaEnum;
import com.dsj.common.enums.FrontRentPriceEnum;
import com.dsj.common.enums.FrontRentTypeEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.enums.NewHouseTsEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.solr.service.ErshoufangIndexService;
import com.dsj.modules.solr.service.NewHouseIndexService;
import com.dsj.modules.solr.service.RentHouseIndexService;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;
import com.dsj.modules.solr.vo.RentHouseIndexFiled;
import com.dsj.modules.solr.vo.RentHouseQueryVo;
import com.dsj.modules.solr.vo.newHouse.NewHouseQueryVo;
import com.google.common.collect.Maps;

@Controller
@RequestMapping("map")																																																																																
public class MapController {
	private final Logger LOGGER = LoggerFactory.getLogger(MapController.class);
    @Autowired
	private AreaService areaService;
    @Autowired
    private ErshoufangIndexService ershoufangIndexService;
    @Autowired
    private RentHouseIndexService rentHouseIndexService;
    @Autowired
    private NewHouseIndexService  newHouseIndexService;
    @Autowired
	private GroupTypeService groupTypeService;
    @Autowired
    SubwayService subwayService;
	
    static Map<String, Object> priceMap = Maps.newLinkedHashMap();
	static Map<String, Object> areaMap = Maps.newLinkedHashMap();
	static Map<String, Object> roomMap = Maps.newLinkedHashMap();
	static Map<String, Object> buildYearMap = Maps.newLinkedHashMap();
	static Map<String, Object> floorTypeMap = Maps.newLinkedHashMap();
	static Map<String,Object> companyTypeMap= Maps.newLinkedHashMap();
	static Map<String,Object> styleMap= Maps.newLinkedHashMap();
	static Map<String,Object> rentAreaMap= Maps.newLinkedHashMap();
	static Map<String,Object> rentPriceMap= Maps.newLinkedHashMap();
	static Map<String,Object> tsMap= Maps.newLinkedHashMap();
	// 价格
	   static Map<String, Object> newPriceMap = Maps.newLinkedHashMap();
	static {
		priceMap=FrontPriceEnum.toMap();
		newPriceMap = FrontRefrencePriceEnum.toMap();
		areaMap=FrontAreaEnum.toMap();
		roomMap=FrontRoomEnum.toMap();
		buildYearMap=FrontBuildYearEnum.toMap();
		floorTypeMap=FloorTypeEnum.toMap();
		companyTypeMap=FrontCompanyTypeEnum.toMap();
		styleMap=FrontRentTypeEnum.toMap();
		rentAreaMap=FrontRentAreaEnum.toMap();
		rentPriceMap=FrontRentPriceEnum.toMap();
		tsMap=NewHouseTsEnum.toMap();
	}
	/**
	 * 根据线路id查找站点集合
	 * 
	 * @param subwayId 地铁线路id
	 * @return
	 */
	@RequestMapping("findStations")
	@ResponseBody
	public AjaxResultVo findStations(Integer subwayId) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<SubwayPo> list = null;
		try {
			map.put("pid", subwayId);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			list = subwayService.getSubWayLine(map);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("地铁站查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	    //二手房详情页
		@RequestMapping({"oldMap"})
		@ResponseBody
		public AjaxResultVo oldHouseList(Model model){
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			paramMap.put("priceMap", priceMap);//价格
			paramMap.put("areaMap", areaMap);//面积
			paramMap.put("roomMap",roomMap);//户型
			paramMap.put("companyTypeMap", companyTypeMap);//所属公司
			Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
			paramMap.put("orientations", mapSearch.get(BusinessConst.ORIENTATIONS));//朝向
			paramMap.put("renvation", mapSearch.get(BusinessConst.RENVATION));//装修
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		/**
		 * 二手房坐标查询
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("oldRegion")
		@ResponseBody
		public AjaxResultVo oldRegion(Model model,ErShoufangQueryVo queryVo) throws SolrServerException, IOException{
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			int pageSize=10;
			if(queryVo.getPageNum()==null){
				queryVo.setPageNum(1);
			}
			PageBean region=ershoufangIndexService.getGroupAreaCode3(pageSize, queryVo);
			Map countResultMap=region.getCountResultMap();
			List<AreaLatLngGroupVo> AreaLatLngGroupList =(List) countResultMap.get("mapCount");
			paramMap.put("AreaLatLngGroupList", AreaLatLngGroupList);
			PageBean area=ershoufangIndexService.getGroupTrade(pageSize, queryVo);
			Map countResultMap1=area.getCountResultMap();
			List<AreaLatLngGroupVo> areaList =(List) countResultMap1.get("mapCount");
			paramMap.put("areaList", areaList);
			PageBean quarters=ershoufangIndexService.getGroupDic(pageSize, queryVo);
			Map countResultMap2=quarters.getCountResultMap();
			List<AreaLatLngGroupVo> quartersList =(List) countResultMap2.get("mapCount");
			paramMap.put("quartersList", quartersList);
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		/**
		 * 详情列表页(二手房)
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("arrayDrectory")
		@ResponseBody
		public AjaxResultVo arrayDrectory(ErShoufangQueryVo queryVo,int types) throws SolrServerException, IOException{
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			int pageSize=4;//每页条数
			if(types==1){
				PageBean region=ershoufangIndexService.getGroupAreaCode3(pageSize, queryVo);
				List<ErshoufangIndexFiled> recordList=(List<ErshoufangIndexFiled>) region.getRecordList();
				paramMap.put("recordList", recordList);
			}else if(types==2){
				PageBean area=ershoufangIndexService.getGroupTrade(pageSize, queryVo);
				List<ErshoufangIndexFiled> recordList=(List<ErshoufangIndexFiled>) area.getRecordList();
				paramMap.put("recordList", recordList);
			}else if(types==3){
				PageBean quarters=ershoufangIndexService.getGroupDic(pageSize, queryVo);
				List<ErshoufangIndexFiled> recordList=(List<ErshoufangIndexFiled>) quarters.getRecordList();
				paramMap.put("recordList", recordList);
			}
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		//租房详情页
		@RequestMapping({"rentMap"})
		public AjaxResultVo rentHouseList(Model model){
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			paramMap.put("rentPriceMap", rentPriceMap);//价格
			paramMap.put("rentAreaMap", rentAreaMap);//面积
			paramMap.put("roomMap",roomMap);//户型
			paramMap.put("styleMap", styleMap);
			Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
			paramMap.put("payTypes", mapSearch.get(BusinessConst.PAY_TYPE));//付款方式
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		//新房详情页
		@RequestMapping({"newMap"})
		public AjaxResultVo newHouseList(Model model){
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			paramMap.put("priceMap", newPriceMap);//价格
			paramMap.put("roomMap",roomMap);//户型
			paramMap.put("tsMap", tsMap);
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		/**
		 * 租房坐标查询
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("rentRegion")
		@ResponseBody
		public AjaxResultVo rentRegion(Model model,RentHouseQueryVo queryVo) throws SolrServerException, IOException{
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			int pageSize=10;
			if(queryVo.getPageNum()==null){
				queryVo.setPageNum(1);
			}
			PageBean region=rentHouseIndexService.getGroupAreaCode3(pageSize, queryVo);
			Map countResultMap=region.getCountResultMap();
			List<AreaLatLngGroupVo> AreaLatLngGroupList =(List) countResultMap.get("mapCount");
			paramMap.put("AreaLatLngGroupList", AreaLatLngGroupList);
			PageBean area=rentHouseIndexService.getGroupTrade(pageSize, queryVo);
			Map countResultMap1=area.getCountResultMap();
			List<AreaLatLngGroupVo> areaList =(List) countResultMap1.get("mapCount");
			paramMap.put("areaList", areaList);
			PageBean quarters=rentHouseIndexService.getGroupDic(pageSize, queryVo);
			Map countResultMap2=quarters.getCountResultMap();
			List<AreaLatLngGroupVo> quartersList =(List) countResultMap2.get("mapCount");
			paramMap.put("quartersList", quartersList);
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		/**
		 * 详情列表页(租房)
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("rentArrayDrectory")
		@ResponseBody
		public AjaxResultVo rentArrayDrectory(RentHouseQueryVo queryVo,int types) throws SolrServerException, IOException{
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			int pageSize=4;//每页条数
			if(types==1){
				PageBean region=rentHouseIndexService.getGroupAreaCode3(pageSize, queryVo);
				List<RentHouseIndexFiled> recordList=(List<RentHouseIndexFiled>) region.getRecordList();
				paramMap.put("recordList", recordList);
			}else if(types==2){
				PageBean area=rentHouseIndexService.getGroupTrade(pageSize, queryVo);
				List<RentHouseIndexFiled> recordList=(List<RentHouseIndexFiled>) area.getRecordList();
				paramMap.put("recordList", recordList);
			}else if(types==3){
				PageBean quarters=rentHouseIndexService.getGroupDic(pageSize, queryVo);
				List<RentHouseIndexFiled> recordList=(List<RentHouseIndexFiled>) quarters.getRecordList();
				paramMap.put("recordList", recordList);
			}
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		/**
		 * 新房坐标查询
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("newHouseRegion")
		@ResponseBody
		public AjaxResultVo newHouseRegion(Model model,NewHouseQueryVo queryVo) throws SolrServerException, IOException{
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			int pageSize=10;
			if(queryVo.getPageNum()==null){
				queryVo.setPageNum(1);
			}
			PageBean region=newHouseIndexService.getGroupAreaCode3(pageSize, queryVo);
			Map countResultMap=region.getCountResultMap();
			List<AreaLatLngGroupVo> AreaLatLngGroupList =(List) countResultMap.get("mapCount");
			paramMap.put("AreaLatLngGroupList", AreaLatLngGroupList);
			PageBean quarters=newHouseIndexService.getGroupDic(pageSize, queryVo);
			Map countResultMap2=quarters.getCountResultMap();
			List<AreaLatLngGroupVo> quartersList =(List) countResultMap2.get("mapCount");
			paramMap.put("quartersList", quartersList);
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
		
		/**
		 * 详情列表页(新房)
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("newHouseArrayDrectory")
		@ResponseBody
		public AjaxResultVo newHouseArrayDrectory(NewHouseQueryVo queryVo,int types) throws SolrServerException, IOException{
			AjaxResultVo ajax=new AjaxResultVo();
			Map<String, Object> paramMap=new HashMap<>();
			int pageSize=4;//每页条数
			if(types==1){
				PageBean region=newHouseIndexService.getGroupAreaCode3(pageSize, queryVo);
				List<RentHouseIndexFiled> recordList=(List<RentHouseIndexFiled>) region.getRecordList();
				paramMap.put("recordList", recordList);
			}else if(types==2){
				PageBean quarters=newHouseIndexService.getGroupDic(pageSize, queryVo);
				List<RentHouseIndexFiled> recordList=(List<RentHouseIndexFiled>) quarters.getRecordList();
				paramMap.put("recordList", recordList);
			}
			ajax.setData(paramMap);
			ajax.setStatus(1);
			return ajax;
		}
}

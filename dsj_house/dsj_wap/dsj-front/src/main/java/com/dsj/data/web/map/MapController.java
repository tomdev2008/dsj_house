package com.dsj.data.web.map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
import com.dsj.common.enums.FrontAreaEnum;
import com.dsj.common.enums.FrontBuildYearEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.enums.FrontOrientationsEnum;
import com.dsj.common.enums.FrontPriceEnum;
import com.dsj.common.enums.FrontRefrencePriceEnum;
import com.dsj.common.enums.FrontRentAreaEnum;
import com.dsj.common.enums.FrontRentPriceEnum;
import com.dsj.common.enums.FrontRentTypeEnum;
import com.dsj.common.enums.FrontRoomEnum;
import com.dsj.common.enums.FrontWyTypeEnum;
import com.dsj.common.enums.NewHouseTsEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.po.AreaPo;
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

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "map")
public class MapController {
	private final Logger LOGGER = LoggerFactory.getLogger(MapController.class);
    @Autowired
	 AreaService areaService;
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
	static Map<String,Object> companyTypeMap= Maps.newLinkedHashMap();
	static Map<String,Object> styleMap= Maps.newLinkedHashMap();
	static Map<String,Object> rentAreaMap= Maps.newLinkedHashMap();
	static Map<String,Object> rentPriceMap= Maps.newLinkedHashMap();
	static Map<String,Object> tsMap= Maps.newLinkedHashMap();
	static Map<String,Object> orientationsMap= Maps.newLinkedHashMap();
	// 类型
    static Map<String, Object> wyMap = Maps.newLinkedHashMap();
	// 价格
   static Map<String, Object> newPriceMap = Maps.newLinkedHashMap();
	static {
		priceMap=FrontPriceEnum.toMap();
		newPriceMap = FrontRefrencePriceEnum.toMap();
		areaMap=FrontAreaEnum.toMap();
		roomMap=FrontRoomEnum.toMap();
		buildYearMap=FrontBuildYearEnum.toMap();
		companyTypeMap=FrontCompanyTypeEnum.toMap();
		styleMap=FrontRentTypeEnum.toMap();
		rentAreaMap=FrontRentAreaEnum.toMap();
		rentPriceMap=FrontRentPriceEnum.toMap();
		tsMap=NewHouseTsEnum.toMap();
		orientationsMap=FrontOrientationsEnum.toMap();
		wyMap = FrontWyTypeEnum.toMap();
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
		public String oldHouseList(Model model){
			model.addAttribute("priceMap", priceMap);//价格
			model.addAttribute("areaMap", areaMap);//面积
			model.addAttribute("roomMap",roomMap);//户型
			model.addAttribute("companyTypeMap", companyTypeMap);//所属公司
			Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
			model.addAttribute("orientations", orientationsMap);//朝向
			model.addAttribute("renvation", mapSearch.get(BusinessConst.RENVATION));//装修
			Map<String, Object> map=new HashMap<>();
			map.put("pid", 0);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<SubwayPo> list = subwayService.listBy(map);
			model.addAttribute("subList", list);
			return "map/oldHouse-map";
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
		 * 二手房坐标查询
		 * @param model
		 * @param queryVo
		 * @return
		 * @throws SolrServerException
		 * @throws IOException
		 */
		@RequestMapping("oldRegionQuery")
		@ResponseBody
		public AjaxResultVo oldRegionQuery(Model model,ErShoufangQueryVo queryVo) throws SolrServerException, IOException{
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
		public String rentHouseList(Model model){
			model.addAttribute("rentPriceMap", rentPriceMap);//价格
			model.addAttribute("rentAreaMap", rentAreaMap);//面积
			model.addAttribute("roomMap",roomMap);//户型
			model.addAttribute("styleMap", styleMap);
			Map<String,Object> mapSearch=groupTypeService.getHouseGroupType();
			model.addAttribute("payTypes", mapSearch.get(BusinessConst.PAY_TYPE));//付款方式
			Map<String, Object> map=new HashMap<>();
			map.put("pid", 0);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<SubwayPo> list = subwayService.listBy(map);
			model.addAttribute("subList", list);
			return "map/rentHouse-map";
		}
		
		//新房详情页
		@RequestMapping({"newMap"})
		public String newHouseList(Model model){
			model.addAttribute("priceMap", newPriceMap);//价格
			model.addAttribute("roomMap",roomMap);//户型
			model.addAttribute("tsMap", tsMap);
			model.addAttribute("wyMap", wyMap);
			Map<String, Object> map=new HashMap<>();
			map.put("pid", 0);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<SubwayPo> list = subwayService.listBy(map);
			model.addAttribute("subList", list);
			return "map/newHouse-map";
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
		
		/**
		 * 根据线路id查找站点集合(新房)
		 * 
		 * @param subwayId 地铁线路id
		 * @return
		 */
		@RequestMapping("findNewStations")
		@ResponseBody
		public AjaxResultVo findNewStations(Integer subwayId) {
			AjaxResultVo ajax = new AjaxResultVo();
			HashMap<String, Object> map = new HashMap<String,Object>();
			List<SubwayPo> list = null;
			try {
				map.put("pid", subwayId);
				map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
				list = subwayService.getNewSubWayLine(map);
				ajax.setData(list);
				ajax.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("地铁站查询异常", e);
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
			return ajax;
		}
		
		/**
		 * 根据北京ID查商圈
		 * 
		 * @param subwayId 商圈父ID
		 * @return
		 */
		@RequestMapping("area")
		@ResponseBody
		public AjaxResultVo area(Integer subwayId) {
			AjaxResultVo ajax = new AjaxResultVo();
			Map<String, Object> map=new HashMap<>();
			List<AreaPo> list = null;
			try {
				map.put("pid", subwayId);
				list = areaService.getAreaList(map);
				ajax.setData(list);
				ajax.setStatusCode(StatusCode.SUCCESS);
			} catch (Exception e) {
				LOGGER.error("地铁站查询异常", e);
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
			return ajax;
		}
		
		
		/**
		 * 获取商圈，添加坐标
		 * @return
		 */
		@RequestMapping({"lay"})
		public String lay(){
			List<AreaPo> areaList=areaService.getRrareaList();
			for (AreaPo areapo : areaList) {
				String name=areapo.getName();
				long parentId=areapo.getParentId();
				AreaPo area=areaService.getById(parentId);
				if(area==null){
					
				}else{
				String areaName=area.getName();
				String name1="北京市"+areaName+name;
				String http="http://api.map.baidu.com/geocoder/v2/?output=json&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka&address="+name1;
				String[] dd=latLng(http);
				Map<String, Object> map=new HashMap<>();
				map.put("dimension", dd[0]);
				map.put("accuracy", dd[1]);
				map.put("id", areapo.getId());
				areaService.updateTrea(map);
				System.out.println(name);
				}
			}
			
			
			return "";
		}
		
		@RequestMapping({"ouer"})
		public String ouer(){
			List<AreaPo> areaList=areaService.getAreaList();
			for (AreaPo areaPo : areaList) {
				AreaPo area=null;
			Long parentId=areaPo.getParentId();
			String name=areaPo.getName();
			if(parentId==0){
				String http="http://api.map.baidu.com/geocoder/v2/?output=json&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka&address="+name;
				String[] dd=latLng(http);
				JSONObject jsStr = JSONObject.fromObject(dd);
				String result=jsStr.getString("result");
				JSONObject point = JSONObject.fromObject(result);
				String location=point.getString("location");
				JSONObject ccc = JSONObject.fromObject(location);
				String lng=ccc.getString("lng");
				String lat=ccc.getString("lat");
				area=new AreaPo();
				area.setDimension(lng);
				area.setAccuracy(lat);
				area.setId(areaPo.getId());
				areaService.updateDynamic(area);
			}else{
				area=areaService.getById(parentId);
				Long pid=area.getParentId();
				String name1=area.getName();
				if(pid==0){
					String na=name1+name;
					String http="http://api.map.baidu.com/geocoder/v2/?output=json&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka&address="+na;
					String[] dd=latLng(http);
					JSONObject jsStr = JSONObject.fromObject(dd);
					String result=jsStr.getString("result");
					JSONObject point = JSONObject.fromObject(result);
					String location=point.getString("location");
					JSONObject ccc = JSONObject.fromObject(location);
					String lng=ccc.getString("lng");
					String lat=ccc.getString("lat");
					area=new AreaPo();
					area.setDimension(lng);
					area.setAccuracy(lat);
					area.setId(areaPo.getId());
					areaService.updateDynamic(area);
				}else{
					area=areaService.getById(pid);
					Long pd=area.getParentId();
					String name2=area.getName();
					if(pd==0){
						String name3=name2+name1+name;
						String http="http://api.map.baidu.com/geocoder/v2/?output=json&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka&address="+name3;
						String[] dd=latLng(http);
						JSONObject jsStr = JSONObject.fromObject(dd);
						String result=jsStr.getString("result");
						JSONObject point = JSONObject.fromObject(result);
						String location=point.getString("location");
						JSONObject ccc = JSONObject.fromObject(location);
						String lng=ccc.getString("lng");
						String lat=ccc.getString("lat");
						area=new AreaPo();
						area.setDimension(lng);
						area.setAccuracy(lat);
						area.setId(areaPo.getId());
						areaService.updateDynamic(area);
					}
				}
			}
			
             
			}
			latLng("2316465");
			return "";
		}
		public static String[] latLng(String name){
			String urlStr="http://api.map.baidu.com/geocoder/v2/?output=json&ak=BspeSHUsaqF8dsvansDKdoA2VRvtOdka&address="+name;
			  /** 网络的url地址 */
	        URL url = null;
	        /** http连接 */
	        HttpURLConnection httpConn = null;
	        /**//** 输入流 */
	        BufferedReader in = null;
	        StringBuffer sb = new StringBuffer();
	        try {
	        	 url = new URL(urlStr);
	             in = new BufferedReader(new InputStreamReader(url.openStream(), "GBK"));
	             String str = null;
	             while ((str = in.readLine()) != null) {
	                 sb.append(str);
	             }
			} catch (Exception e) {
				
			}finally{
				try {
	                if (in != null) {
	                    in.close();
	                }
	            } catch (IOException ex) {
	            }
			}
	        String result1 = sb.toString();
	        JSONObject jsStr = JSONObject.fromObject(result1);
	        String status=jsStr.getString("status");
	        Integer statu=Integer.parseInt(status);
	        if(statu==0){
	        	String result=jsStr.getString("result");
				JSONObject point = JSONObject.fromObject(result);
				String location=point.getString("location");
				JSONObject ccc = JSONObject.fromObject(location);
				String lng=ccc.getString("lng");
				String lat=ccc.getString("lat");
				String[] A =new String[2];
				A[0]=lng;
				A[1]=lat;
				return A;
	        }else{
	        	String[] A =new String[2];
	        	return A;
	        }
	        
		}
}

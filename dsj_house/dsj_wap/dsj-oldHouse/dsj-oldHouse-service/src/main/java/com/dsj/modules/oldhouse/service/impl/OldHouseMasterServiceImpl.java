package com.dsj.modules.oldhouse.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrServerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.FrontCompanyTypeEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.modules.oldhouse.dao.OldHouseMasterDao;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.oldhouse.enums.HouseStatusEnum;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.service.OldHouseAgentService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.service.OldHousePictureService;
import com.dsj.modules.oldhouse.vo.HouseDirectoryAppVo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterAppVo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterDetailVo;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.oldhouse.vo.OldHouseRecommendVo;
import com.dsj.modules.other.enums.SubwayObjectTypeEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.HousePicturePo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.HousePictureService;
import com.dsj.modules.other.service.SubwayObjService;
import com.dsj.modules.other.service.TradeAreaService;
import com.dsj.modules.solr.enums.ErshouSearcheTypeEnum;
import com.dsj.modules.solr.service.CommonIndexService;
import com.dsj.modules.solr.service.ErshoufangIndexService;
import com.dsj.modules.solr.vo.CommonIndexFiled;
import com.dsj.modules.solr.vo.ErshoufangIndexFiled;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-28 14:44:14.
 * @版本: 1.0 .
 */
@Service
public class OldHouseMasterServiceImpl  extends BaseServiceImpl<OldHouseMasterDao,OldHouseMasterPo> implements OldHouseMasterService {
	private final Logger LOGGER = LoggerFactory.getLogger(OldHouseMasterServiceImpl.class);

	@Autowired
	private OldHouseAgentService oldHouseAgentService;
	
	@Autowired
	private ErshoufangIndexService ershoufangIndexService;
	
	@Autowired
	private SubwayObjService subwayObjService;
	
	@Autowired 
	private HouseDirectoryService houseDirectoryService;
	
	@Autowired
	private AreaService areaService;
	
	@Autowired
	private TradeAreaService tradeAreaService;
	
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private CommonIndexService commonIndexService;
	
	
	@Autowired
	HousePictureService housePictureService;
	
	@Autowired
	OldHousePictureService oldHousePictureService;
	
	
	@Override
	public PageBean listOldMasterPage(PageParam pageParam, Map<String, Object> paramMap) {
		return dao.listPage(pageParam, paramMap, "listOldMasterPageCount", "listOldMasterPage");
	}

	@Override
	public void updateDeleteFlag(String[] ids, Integer deleteFlag) {
		dao.updateDeleteFlag(ids,deleteFlag);
	}

	@Override
	public int updateMasterStatus(String[] ids, Integer status) {
		
		return dao.updateMasterStatus(ids,status);
	}

	@Override
	public int updateImageUrlById(Long id, String imageUrl) {
		return dao.updateImageUrlById(id,imageUrl);
	}

	@Override
	public PageBean listAgentOldMasterPage(PageParam pageParam, Map<String, Object> paramMap) {
		return dao.listPage(pageParam, paramMap, "listAgentOldMasterPageCount", "listAgentOldMasterPage");

	}


	@Override
	public Long saveOldMaster(OldHouseMasterPo po, Long userId) {
		po.setCreatePerson(userId.intValue());
		po.setCreateTime(new Date());
		po.setUpdatePreson(userId.intValue());
		po.setUpdateTime(new Date());
		Long oldId= saveDynamic(po);
		OldHouseAgentPo oldHouseAgent=saveOldHouseAgent( po.getUserId(),userId,oldId);
		
		oldHouseAgentService.saveDynamic(oldHouseAgent);
		return oldId;
	}

	@Override
	public void updateOldMaster(OldHouseMasterPo po,Long userId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("userId", po.getUserId());
		paramMap.put("oldMasterId", po.getId());
		OldHouseAgentPo oldHouseAgentPo=oldHouseAgentService.getBy(paramMap);
		if(oldHouseAgentPo==null){
			OldHouseAgentPo oldHouseAgent=saveOldHouseAgent( po.getUserId(),userId, po.getId());
			oldHouseAgentService.saveDynamic(oldHouseAgent);
		}else{
			oldHouseAgentPo.setUpdateTime(new Date());
			oldHouseAgentPo.setUpdatePreson(userId);
			oldHouseAgentPo.setUserId( po.getUserId());
			oldHouseAgentService.updateDynamic(oldHouseAgentPo);
		}
		po.setUpdatePreson(userId.intValue());
		po.setUpdateTime(new Date());
		updateDynamic(po);
	}
	
	@Override
	public void updateOldMasterBack(OldHouseMasterPo po,Long userId) {
		Map<String,Object> paramMap=new HashMap<String,Object>();
		
		paramMap.put("oldMasterId", po.getId());
		OldHouseAgentPo oldHouseAgentPo=oldHouseAgentService.getByUserNotNull(paramMap);
		if(oldHouseAgentPo==null){
			OldHouseAgentPo oldHouseAgent=saveOldHouseAgent( po.getUserId(),userId, po.getId());
			oldHouseAgentService.saveDynamic(oldHouseAgent);
		}else{
			oldHouseAgentPo.setUpdateTime(new Date());
			oldHouseAgentPo.setUpdatePreson(userId);
			oldHouseAgentPo.setUserId( po.getUserId());
			oldHouseAgentService.updateDynamic(oldHouseAgentPo);
		}
		po.setUpdatePreson(userId.intValue());
		po.setUpdateTime(new Date());
		updateDynamic(po);
	}
	

	
	private OldHouseAgentPo saveOldHouseAgent(Long agentId,Long userId,Long oldId){
		OldHouseAgentPo oldHouseAgent=new OldHouseAgentPo();
		oldHouseAgent.setUserId(agentId);
		oldHouseAgent.setOldMasterId(oldId);
		oldHouseAgent.setUpdateTime(new Date());
		oldHouseAgent.setCreatePerson(userId);
		oldHouseAgent.setUpdatePreson(userId);
		oldHouseAgent.setCreateTime(new Date());
		oldHouseAgent.setCompanyType(CompanyTypeEnum.DSJ.getValue());
		oldHouseAgent.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		return oldHouseAgent;
	}

	@Override
	public List<String> getSameVillageList(Map<String, Object> map) {
		return dao.getSameVillageList(map);
	}

	@Override
	public List<String> getSameTradeList(Map<String, Object> map) {
		return dao.getSameTradeList(map);
	}

	@Override
	public List<String> getSimilarList(Map<String, Object> map) {
		return dao.getSimilarList(map);
	}

	@Override
	public List<String> getLateList(Map<String, Object> map) {
		return dao.getLateList(map);
	}
	
	@Override
	public void saveErshoufangSolr(String[] ids){
		
		Map<String,Object> groopTypeMap=groupTypeService.getGroupTypeMapByPid(BusinessConst.FEATURES);
		
		List<ErshoufangIndexFiled>  fieldsList=getErshoufangSolrByIds(ids);
		
		List<CommonIndexFiled> fieldsCommonList=new ArrayList<CommonIndexFiled>();//地区
		
		for(ErshoufangIndexFiled filed:fieldsList){
			String subwayline= subwayObjService.getLineBy(SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue(), filed.getDicId());
			filed.setSubwayline(","+subwayline+",");
			String subway= subwayObjService.getStationBy(SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue(), filed.getDicId());
			filed.setSubway(","+subway+",");
			//特色
			String featuresName="";
			if(StringUtils.isNotBlank(filed.getFeatures())){
				String[] features=filed.getFeatures().split(",");
				for(String str:features){
					if(groopTypeMap.get(str)!=null){
						featuresName+=groopTypeMap.get(str)+",";
					}
				}
				if(StringUtils.isNotBlank(featuresName)){
					featuresName=featuresName.substring(0, featuresName.length()-1);
				}
			}
			String companyTypeNames="";
			//公司类型
			if(StringUtils.isNotBlank(filed.getCompanyTypes())){
				String[] companyTypes=filed.getCompanyTypes().split(",");
				for(String str:companyTypes){
					companyTypeNames=CompanyTypeEnum.getEnum(Integer.parseInt(str)).getDesc()+",";				
				}
			
			}
			Map<String,Object> agentMap=new HashMap<String,Object>();
			
			agentMap.put("oldMasterId", filed.getId());
			//查询该二手房下的经济人
			List<OldHouseAgentPo> agentLists= oldHouseAgentService.getJoinMasterLefJoinAgentUserId(agentMap);
			
			String companyIoc="";
			for(OldHouseAgentPo po:agentLists){
				companyTypeNames+=po.getDsjShortName()+",";
			
				if(po.getUserId()!=null && StringUtils.isNotBlank(filed.getCompanyTypes()) 
						&& StringUtils.isNotBlank(po.getCcode())
						&& !filed.getCompanyTypes().contains(po.getCcode())){//添加的经纪人是 爬来的数据
					filed.setCompanyTypes(filed.getCompanyTypes()+","+po.getCcode());
				}else if(StringUtils.isBlank(filed.getCompanyTypes()) && StringUtils.isNotBlank(po.getCcode())){
					filed.setCompanyTypes(po.getCcode());
				
				}else if(StringUtils.isNotBlank(filed.getCompanyTypes()) && 
				
						!filed.getCompanyTypes().contains(FrontCompanyTypeEnum.OTHER.getValue().toString())){//其他
						
					filed.setCompanyTypes(filed.getCompanyTypes()+","+FrontCompanyTypeEnum.OTHER.getValue());
					
					if(StringUtils.isNotBlank(po.getIco())){
						companyIoc+=po.getIco()+";";
					}
				}else if(StringUtils.isBlank(filed.getCompanyTypes())){//其他
					filed.setCompanyTypes(FrontCompanyTypeEnum.OTHER.getValue().toString());
					
					if(StringUtils.isNotBlank(po.getIco())){
						companyIoc+=po.getIco()+";";
					}
				}
			}
			
			//经纪人公司logon
			if(StringUtils.isNotBlank(companyIoc)){
				filed.setIco(companyIoc.substring(0, companyIoc.length()-1));
			}
			if(StringUtils.isNotBlank(companyTypeNames)&&companyTypeNames.contains(",")){
				companyTypeNames=companyTypeNames.substring(0, companyTypeNames.length()-1);	
			}
			filed.setFeaturesName(featuresName);
			filed.setCompanyTypeNames(companyTypeNames);
			
			if(filed.getDicId()!=null){
				HouseDirectoryPo houseDirectory=houseDirectoryService.getById(filed.getDicId());
				fieldsCommonList.add(saveHouseDicSolr(houseDirectory));
				
				if(houseDirectory.getTradingAreaId()!=null){
					TradeAreaPo tradeArea=tradeAreaService.getById(houseDirectory.getTradingAreaId());
					if(tradeArea!=null){
						fieldsCommonList.add(saveHouseTardeSolr(tradeArea));
					}
				}
	
				if(StringUtils.isNotBlank(houseDirectory.getAreaCode3())){
					Map<String,Object> paramMap=new HashMap<String,Object>();
					paramMap.put("areaCode", houseDirectory.getAreaCode1());
					AreaPo area=areaService.getBy(paramMap);
					if(area!=null){
						fieldsCommonList.add(saveHouseAreaSolr(area));
					}
				}
			}
			
		}
		
		
		try {
			//二手房solr
			ershoufangIndexService.addItemIndexs(fieldsList);
			if(fieldsCommonList.size()>0){
				commonIndexService.addItemIndexs(fieldsCommonList);
			}
		} catch (Exception e) {
			LOGGER.error("二手房solr错误",e);
		} 
	}
	
	public CommonIndexFiled saveHouseDicSolr(HouseDirectoryPo po) {
		CommonIndexFiled filed=new CommonIndexFiled();
		filed.setId(BusinessConst.SOLR_DIC+po.getId());
		filed.setName(po.getSprayName());
		filed.setFullPinyin(po.getSpellName());
		filed.setType(ErshouSearcheTypeEnum.DIC.getValue());
		filed.setJianPin(po.getSpellHead());
		return filed;
	}
	
	public CommonIndexFiled saveHouseAreaSolr(AreaPo po) {
		CommonIndexFiled filed=new CommonIndexFiled();
		filed.setId(BusinessConst.SOLR_AREA+po.getId());
		filed.setName(po.getName());
		filed.setFullPinyin(po.getEnglishName());
		filed.setType(ErshouSearcheTypeEnum.AREA.getValue());
		filed.setJianPin(po.getLikePinyin());
		return filed;
	}
	
	public CommonIndexFiled saveHouseTardeSolr(TradeAreaPo po) {
		CommonIndexFiled filed=new CommonIndexFiled();
		filed.setId(BusinessConst.SOLR_TRADE+po.getId());
		filed.setName(po.getName());
		filed.setFullPinyin(po.getEnglishName());
		filed.setType(ErshouSearcheTypeEnum.TRADE.getValue());
		filed.setJianPin(po.getLikePinyin());
		return filed;
	}
	
	
	public  List<ErshoufangIndexFiled>  getErshoufangSolrByIds(String[] ids) {
		return dao.getErshoufangSolrByIds(ids);
	}
	

	@Override
	public void deleteOldRecommend(Long id) {
		dao.deleteOldRecommend(id);
	}

	@Override
	public void updateOldRecommend(Long id, List<String> ids) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseId", id);
		map.put("ids", ids);
		dao.updateOldRecommend(map);
	}

	@Override
	public Integer listCount(Map<String, Object> map) {
		return dao.listCount(map);
	}

	@Override
	public List<OldHouseMasterPo> selectByLimit(Map<String, Object> map) {
		return dao.selectByLimit(map);
	}

	@Override
	public List<OldHouseRecommendVo> getOldHouseRecommendById(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return dao.getOldHouseRecommendById(map);
	}

	@Override
	public List<OldHouseMasterVo> findAgentOldHouse(Map<String, Object> map) {
		return dao.findAgentOldHouse(map);
	}

	@Override
	public int findAgentOldHouseCount(Map<String, Object> map) {
		return dao.findAgentOldHouseCount(map);
	}

	@Override
	public List<OldHouseMasterVo> findFollow(Map<String, Object> map) {
		return dao.findFollow(map);
	}

	@Override
	public int findFollowCount(Map<String, Object> map) {
		return dao.findFollowCount(map);
	}

	@Override
	public List<OldHouseMasterVo> lookHistory(Map<String, Object> map) {
		return dao.lookHistory(map);
	}

	@Override
	public int lookHistoryCount(Map<String, Object> map) {
		return dao.lookHistoryCount(map);
	}

	@Override
	public void deleteErshoufangSolr(String[] ids) {
		for(String id:ids){
			try {
				ershoufangIndexService.deleteItemIndex(id);
				
				OldHouseMasterPo master= getById(Long.parseLong(id));
				if(master.getDicId()!=null){
					Map<String, Object> map = new HashMap<>();
					map.put("dicId", master.getDicId());
					map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
					map.put("status", HouseStatusEnum.ON.getValue());
					Long count=getCount(map);
					if(count==0){
						commonIndexService.deleteItemIndex(BusinessConst.SOLR_DIC+id);
					}
				}
			} catch (SolrServerException|IOException e) {
				LOGGER.error("二手房删除solr错误",e);
			}
		}
	}
	
	private Long getCount(Map<String, Object> map) {
		
		return dao.getCount(map);
	}

	@Override
	public List<OldHouseMasterVo> getPcByNamePreMatchding(String name) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", name);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		map.put("status", HouseStatusEnum.ON.getValue());
		return dao.getPcByNamePreMatchding(map);
	}

	@Override
	public OldHouseMasterDetailVo getByWapOldMasterId(Long id) {
		OldHouseMasterDetailVo detailVo=new OldHouseMasterDetailVo();
		
		Map<String,Object> groupTypesMap=new HashMap<String,Object>();
		//初始化二手房字典
		Map<String,Object> groupTypes=groupTypeService.getHouseGroupType();
		if(groupTypes.get(BusinessConst.ORIENTATIONS)!=null){//朝向
			groupTypesMap.put("orientationsMap", JsonTools.mapToKvJson((Map<String,Object>)groupTypes.get(BusinessConst.ORIENTATIONS)));
		}
		
		if(groupTypes.get(BusinessConst.FEATURES)!=null){//特色
			groupTypesMap.put("featuresMap",JsonTools.mapToKvJson((Map<String,Object>) groupTypes.get(BusinessConst.FEATURES)));
		}
		
		if(groupTypes.get(BusinessConst.HEATING_MODE)!=null){//供暖方式
			groupTypesMap.put("heatingModeMap",JsonTools.mapToKvJson((Map<String,Object>) groupTypes.get(BusinessConst.HEATING_MODE)));
		}
		
		
		if(groupTypes.get(BusinessConst.HOUSE_TYPE)!=null){//房屋类型
			groupTypesMap.put("houseTypeMap",JsonTools.mapToKvJson((Map<String,Object>) groupTypes.get(BusinessConst.HOUSE_TYPE)));
		}
		
		if(groupTypes.get(BusinessConst.RENVATION)!=null){//房屋类型
			groupTypesMap.put("renvationMap",JsonTools.mapToKvJson((Map<String,Object>) groupTypes.get(BusinessConst.RENVATION)));
		}

		detailVo.setGroupTypesMap(groupTypesMap);
		
		//二手房数据
		OldHouseMasterPo oldHouseMaster=getById(id);
		 OldHouseMasterAppVo oldHouseMasterAppVo=new OldHouseMasterAppVo();
		BeanUtils.copyProperties(oldHouseMaster, oldHouseMasterAppVo);
		
		if(groupTypes.get(BusinessConst.HOUSE_TYPE)!=null&&StringUtils.isNotBlank(oldHouseMaster.getHouseType())){
			oldHouseMasterAppVo.setHouseTypeName(((Map<String,Object>)groupTypes.get(BusinessConst.HOUSE_TYPE)).get(oldHouseMaster.getHouseType()).toString());
		}
		
		if(groupTypes.get(BusinessConst.RENVATION)!=null&&oldHouseMaster.getRenovation()!=null){
			oldHouseMasterAppVo.setRenovationName(((Map<String,Object>)groupTypes.get(BusinessConst.RENVATION)).get(oldHouseMaster.getRenovation().toString()).toString());
		}
		
		if(groupTypes.get(BusinessConst.FEATURES)!=null&&StringUtils.isNotBlank(oldHouseMaster.getFeatures())){
			String featureNames="";
			Map<String,Object> map=(Map<String,Object>) groupTypes.get(BusinessConst.FEATURES);
			String[] strs=oldHouseMaster.getFeatures().split(",");
			for(String str:strs){
				featureNames+=map.get(str)+",";
			}
			if(StringUtils.isNotBlank(featureNames)){
				featureNames=featureNames.substring(0, featureNames.length()-1).replace("null", "");
			}
			oldHouseMasterAppVo.setFeatureNames(featureNames);
		}
		detailVo.setOldHouseMaster(oldHouseMasterAppVo);
		
		//楼盘字典
		if(oldHouseMaster.getDicId()!=null){
			HouseDirectoryPo dicPo=houseDirectoryService.getById(oldHouseMaster.getDicId());
			HouseDirectoryAppVo dicAppPo=new HouseDirectoryAppVo();
			if(dicPo!=null){
				BeanUtils.copyProperties(dicPo, dicAppPo);
				if(dicPo!=null){
					//获取封面
					HousePicturePo houseDicPic =housePictureService.getIsCoverByObjId(dicPo.getId());
					if(houseDicPic!=null){
						dicAppPo.setImageUrl(houseDicPic.getPictureUrl());
					}
				}
			}
			detailVo.setDicPo(dicAppPo);
		}
		
		//图片
		List<OldHousePicturePo> oldHousePictures=oldHousePictureService.getByMasterId(id);
		detailVo.setOldHousePictures(oldHousePictures);
		
	
		//推荐
		List<OldHouseRecommendVo> recommendVos=getOldHouseRecommendById(id);
		for(OldHouseRecommendVo rvo:recommendVos){
			if(groupTypes.get(BusinessConst.FEATURES)!=null&&StringUtils.isNotBlank(rvo.getFeatures())){
				String featureNames="";
				Map<String,Object> map=(Map<String,Object>) groupTypes.get(BusinessConst.FEATURES);
				String[] strs=rvo.getFeatures().split(",");
				for(String str:strs){
					featureNames+=map.get(str)+",";
				}
				if(StringUtils.isNotBlank(featureNames)){
					featureNames=featureNames.substring(0, featureNames.length()-1).replace("null", "");
				}
				rvo.setFeatureNames(featureNames);
			}
		}
		detailVo.setRecommendVos(recommendVos);
		
		//经纪人
		if(oldHouseMaster!=null || oldHouseMaster.getFatherId()!=0){
			Map<String,Object> agentMap=new HashMap<String,Object>();
			agentMap.put("fatherId", oldHouseMaster.getId());
			List<OldHouseAgentPo> listAgents=oldHouseAgentService.getJoinMasterLefJoinAgent(agentMap);
			detailVo.setListAgents(listAgents);
		}
		return detailVo;
	}

	@Override
	public List<OldHouseMasterVo> findPcPageOldHouse(Map<String, Object> map) {
		return dao.findPcPageOldHouse(map);
	}

	@Override
	public int findPcPageOldHouseCount(Map<String, Object> map) {
		return dao.findPcPageOldHouseCount(map);
	}

	@Override
	public List<OldHouseMasterPo> listNewPage(HashMap<String, Object> hashMap) {
		return dao.listNewPage(hashMap);
	}

	@Override
	public List<OldHouseMasterPo> listJoinDicBy(HashMap<String, Object> map) {
		
		return dao.listJoinDicBy(map);
	}

	@Override
	public void updateCompanyTypes(String companyTypes, Long id) {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("companyTypes", companyTypes);
		map.put("id", id);
		dao.updateCompanyTypes(map);
	}

	@Override
	public Long getUpAllCount() {
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("status", HouseStatusEnum.ON.getValue());
		return dao.getCount(map);
	}

	@Override
	public List<OldHouseMasterPo> listLeftAgentPage(HashMap<String, Object> listLeftAgentPage) {
	
		return dao.listLeftAgentPage(listLeftAgentPage);
	}

}
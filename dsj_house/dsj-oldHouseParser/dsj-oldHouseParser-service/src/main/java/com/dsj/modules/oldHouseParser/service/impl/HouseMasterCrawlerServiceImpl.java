package com.dsj.modules.oldHouseParser.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.constants.BusinessConst;
import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.MyBeanUtils;
import com.dsj.common.utils.PinyinUtil;
import com.dsj.common.utils.code.CodeUtils;
import com.dsj.common.utils.lngLat.LngLatUtil;
import com.dsj.modules.oldHouseParser.dao.HouseMasterCrawlerDao;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAgentCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityTempPo;
import com.dsj.modules.oldHouseParser.po.HouseLianjiaCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.po.HouseTradeInfoCrawlerPo;
import com.dsj.modules.oldHouseParser.po.MatserHouseTypesPo;
import com.dsj.modules.oldHouseParser.po.OldHousePictureCrawlerPo;
import com.dsj.modules.oldHouseParser.service.DicDealLogsCrawlerService;
import com.dsj.modules.oldHouseParser.service.HouseAgentCrawlerService;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityService;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityTempService;
import com.dsj.modules.oldHouseParser.service.HouseLianjiaCommunityService;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldHouseParser.service.HousePictureCrawlerService;
import com.dsj.modules.oldHouseParser.service.HouseTradeInfoCrawlerService;
import com.dsj.modules.oldHouseParser.service.MatserHouseTypesService;
import com.dsj.modules.oldHouseParser.service.OldHousePictureCrawlerService;
import com.dsj.modules.oldhouse.enums.CompanyTypeEnum;
import com.dsj.modules.oldhouse.enums.HouseStatusEnum;
import com.dsj.modules.oldhouse.po.OldHouseAgentPo;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.po.OldHousePicturePo;
import com.dsj.modules.oldhouse.service.OldHouseAgentService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.service.OldHousePictureService;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.TradeAreaService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class HouseMasterCrawlerServiceImpl extends BaseServiceImpl<HouseMasterCrawlerDao, HouseMasterCrawlerPo>
		implements HouseMasterCrawlerService {
	@Autowired
	private HouseAlikeCommunityService houseAlikeCommunityService;

	@Autowired
	private HouseAlikeCommunityTempService houseAlikeCommunityTempService;
	@Autowired
	private OldHousePictureCrawlerService oldhousePictureCrawlerService;
	@Autowired
	private HousePictureCrawlerService housePictureCrawlerService;
	@Autowired
	private HouseAgentCrawlerService houseAgentCrawlerService;
	@Autowired
	private GroupTypeService groupTypeService;
	@Autowired
	private AreaService areaService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private HouseLianjiaCommunityService houseLianjiaCommunityService;
	@Autowired
	private HouseDirectoryService  houseDirectoryService;
	
	
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	@Autowired
	private OldHousePictureService oldHousePictureService;
	@Autowired
	private OldHouseAgentService oldHouseAgentService;
	
	@Autowired
	private DicDealLogsCrawlerService dicDealLogsCrawlerService;
	
	@Autowired
	private HouseTradeInfoCrawlerService houseTradeInfoCrawlerService;
	
	@Autowired
	private MatserHouseTypesService matserHouseTypesService;

	@Override
	public void saveHouseMasterCrawler(HouseMasterCrawlerPo houseMasterCrawler, HouseAlikeCommunityPo dic,
			List<HousePictureCrawlerPo> dicPicList, List<OldHousePictureCrawlerPo> oldPicList,
			HouseAgentCrawlerPo agent, CompanyTypeEnum companyTypeEnum) {
		//System.err.println(dic.getName());
		//判断是否有二手房数据
		
		Map<String,Object> oldParamMap=new HashMap<String,Object>();
		oldParamMap.put("originHouseId", houseMasterCrawler.getOriginHouseId());
		oldParamMap.put("company", companyTypeEnum.getValue());
		HouseMasterCrawlerPo hasOldPo=getBy(oldParamMap);
		if(hasOldPo==null){
			// 数据转化
			dealOldMaster(houseMasterCrawler);
			// 小区
			dealDic(dic);

			Map<String, Object> dicAllParamMap = new HashMap<String, Object>();
			String company = CompanyTypeEnum.getCompanyTypeStr(companyTypeEnum);
			dic.setCompany(company);
			
			//链家，我爱我家，麦田，中原
			switch (companyTypeEnum){
				case LIANJIA:
					houseMasterCrawler.setShowxs(1);
					break;
				case WAWJ:
					houseMasterCrawler.setShowxs(2);
					break;
				case MAITIAN:
					houseMasterCrawler.setShowxs(3);
					break;
				case ZHONGYUAN:
					houseMasterCrawler.setShowxs(4);
					break;
			}
			
			if (StringUtils.isNotBlank(company)) {
				dicAllParamMap.put("company", company);
				dicAllParamMap.put("name", dic.getName());

				List<HouseAlikeCommunityPo> houseAlikeCommunityList = houseAlikeCommunityService.listBy(dicAllParamMap);
				HouseAlikeCommunityPo houseAlikeCommunity = null;

				if (houseAlikeCommunityList.size() > 0) {
					houseAlikeCommunity = houseAlikeCommunityList.get(0);
				}

				if (houseAlikeCommunity != null) {// 小区存在
					houseMasterCrawler.setDicId(houseAlikeCommunity.getLianjiaId());
					houseMasterCrawler.setHasDicStatus(YesNoEnum.NO.getValue());
				} else {
					HouseAlikeCommunityTempPo tempDic = new HouseAlikeCommunityTempPo();
					tempDic.setCreateTime(new Date());
					try {
						MyBeanUtils.copyBean2Bean(tempDic, dic);
					} catch (Exception e) {
					}
					Map<String,Object> tempParamMap=new HashMap<String,Object>();
					tempParamMap.put("company", company);
					tempParamMap.put("originCommunityId", tempDic.getOriginCommunityId());
					HouseAlikeCommunityTempPo tempPo=houseAlikeCommunityTempService.getBy(tempParamMap);
					if(tempPo==null){
						//处理商圈
						if(StringUtils.isBlank(tempDic.getTradingAreaId())){
							TradeAreaPo tradeAreaPo=new TradeAreaPo();
							
								tradeAreaPo.setName(dic.getTradingAreaName());
								tradeAreaPo.setParentId(Long.parseLong(dic.getAreaCode3()));
								tradeAreaPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
								tradeAreaPo.setCreateTime(new Date());
								buildTradeAreaPo(tradeAreaPo);
								
								tradeAreaService.saveDynamic(tradeAreaPo);
								tradeAreaPo.setAreaCode(tradeAreaPo.getId().toString());
								tradeAreaService.updateDynamic(tradeAreaPo);
								tempDic.setTradingAreaId(tradeAreaPo.getId().toString());
						}
						tempDic.setCreateTime(new Date());
						houseAlikeCommunityTempService.saveDynamic(tempDic);// 保存临时表小区id
						houseMasterCrawler.setDicTempId(tempDic.getId());
					}else{
						houseMasterCrawler.setDicTempId(tempPo.getId());
					}
					houseMasterCrawler.setHasDicStatus(YesNoEnum.YES.getValue());
				}
				
				houseMasterCrawler.setHouseId(CodeUtils.getErshouCode());
				houseMasterCrawler.setCreateTime(new Date());
				// 保存二手房
				saveDynamic(houseMasterCrawler);
				
				
				// 保存二手房图片
				Map<String, Object> oldpicParamMap = new HashMap<String, Object>();
				oldpicParamMap.put("originObjId", houseMasterCrawler.getOriginDicId());
				oldpicParamMap.put("compayType", houseMasterCrawler.getCompanyType());
				
				List<OldHousePictureCrawlerPo> opcList=oldhousePictureCrawlerService.listBy(oldpicParamMap);
				
				if(opcList.isEmpty()){
					oldhousePictureCrawlerService.save(oldPicList);
				}
				
				Map<String, Object> dicpicParamMap = new HashMap<String, Object>();
				dicpicParamMap.put("originObjId", dic.getOriginCommunityId());
				dicpicParamMap.put("compayType", houseMasterCrawler.getCompanyType());
				
				List<HousePictureCrawlerPo> dpcList=housePictureCrawlerService.listBy(dicpicParamMap);
				// 保存小区图片
				if(dpcList.isEmpty()){
					housePictureCrawlerService.save(dicPicList);
				}
				
				Map<String, Object> agentParamMap = new HashMap<String, Object>();
				agentParamMap.put("originHouseId", houseMasterCrawler.getOriginHouseId());
				agentParamMap.put("compayType", houseMasterCrawler.getCompanyType());
				List<HouseAgentCrawlerPo> houseAgentCrawlerList=houseAgentCrawlerService.listBy(agentParamMap);
				// 保存经纪人
				if(houseAgentCrawlerList.size()==0){
					houseAgentCrawlerService.save(agent);
				}
				
				/**
				 * 交易信息
				 */
				
				if(houseMasterCrawler.getTradeInfo()!=null){
					Map<String, Object> tardeParamMap = new HashMap<String, Object>();
					tardeParamMap.put("originHouseId", houseMasterCrawler.getOriginHouseId());
					HouseTradeInfoCrawlerPo hasTradeInfo=houseTradeInfoCrawlerService.getBy(tardeParamMap);
					if(hasTradeInfo==null){
						HouseTradeInfoCrawlerPo tradeInfo=houseMasterCrawler.getTradeInfo();
						tradeInfo.setOriginHouseId(houseMasterCrawler.getOriginHouseId());
						houseTradeInfoCrawlerService.saveDynamic(tradeInfo);
					}
				}
				
				if(houseMasterCrawler.getMatserHouseTypeList()!=null&&houseMasterCrawler.getMatserHouseTypeList().size()>0){
					Map<String, Object> tardeParamMap = new HashMap<String, Object>();
					tardeParamMap.put("originHouseId", houseMasterCrawler.getOriginHouseId());
					List<MatserHouseTypesPo> typeList=matserHouseTypesService.listBy(tardeParamMap);
					if(typeList.size()==0){
						for(MatserHouseTypesPo typePo:houseMasterCrawler.getMatserHouseTypeList()){
							typePo.setCreateTime(new Date());
							typePo.setOriginHouseId(houseMasterCrawler.getOriginHouseId());
							matserHouseTypesService.saveDynamic(typePo);
						}
					}
				}
				
				//如果是麦田处理交易记录
				if(houseMasterCrawler.getCompanyType()==CompanyTypeEnum.MAITIAN.getValue()){
					List<DicDealLogsCrawlerPo> dealList=houseMasterCrawler.getDealList();
					if(dealList!=null){
						for(DicDealLogsCrawlerPo dealPo:dealList){
							Map<String,Object> paramMap=new HashMap<String,Object>();
							paramMap.put("room", dealPo.getRoom());
							paramMap.put("hall", dealPo.getHall());
							paramMap.put("price", dealPo.getPrice());
							paramMap.put("buildArea", dealPo.getBuildArea());
							paramMap.put("contractDate", DateUtils.date2String(dealPo.getContractDate()));
							List<DicDealLogsCrawlerPo> oldDealLists=dicDealLogsCrawlerService.listBy(paramMap);
							if(oldDealLists.size()>0){
								break;
							}else{
								dealPo.setCreateTime(new  Date());
								dicDealLogsCrawlerService.saveDynamic(dealPo);
							}
						}
					}
				}
			}
		}

	}

	private void dealOldMaster(HouseMasterCrawlerPo houseMasterCrawler) {
		if (StringUtils.isNotBlank(houseMasterCrawler.getFeaturesName())) {// 特色名称
			String[] featuresStr = houseMasterCrawler.getFeaturesName().split(",");
			String features = "";
			for (String str : featuresStr) {
				if (StringUtils.isNotBlank(str)) {
					features += groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.FEATURES), str)
							+ ",";
				}
			}
			if (StringUtils.isNotBlank(features)) {
				houseMasterCrawler.setFeatures(features.substring(0, features.length() - 1));
			}
		}

		if (StringUtils.isNotBlank(houseMasterCrawler.getHouseTypeName())) {// 房屋类型
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.HOUSE_TYPE),
					houseMasterCrawler.getHouseTypeName());
			houseMasterCrawler.setHouseType(groupTypeId.toString());
		}
		if (StringUtils.isNotBlank(houseMasterCrawler.getRenovationName())) {// 装修情况
			if(!"暂无数据".equals(houseMasterCrawler.getRenovationName())){
				Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.RENVATION),
						houseMasterCrawler.getRenovationName());
				houseMasterCrawler.setRenovation(groupTypeId.intValue());
			}
		}

		
		if (StringUtils.isNotBlank(houseMasterCrawler.getOrientationsName())) {
			//处理朝向
			String orientation =dealOrientation(houseMasterCrawler.getOrientationsName());
			
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.ORIENTATIONS),
					orientation);
			houseMasterCrawler.setOrientations(groupTypeId.intValue());
		}
		
		if(StringUtils.isNotBlank(houseMasterCrawler.getCertificateName())){
			
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.CERTIFICATE),
					houseMasterCrawler.getCertificateName());
			houseMasterCrawler.setCertificate(groupTypeId.intValue());
		}
	}

	private String dealOrientation(String orientation) {
					orientation=orientation.trim().replace(" ", "");
					 if (orientation.length() == 1) {
							orientation = orientation + "向";
						} else if ("南北".equals(orientation)) {
							orientation = orientation + "通透";
						} else if ("南西".equals(orientation)
								|| "南西南".equals(orientation)
								|| "西南西".equals(orientation)
								|| "南西南西".equals(orientation)) {
							orientation = "西南朝向";
						} else if ("北西".equals(orientation)
								|| "西北北".equals(orientation)
								|| "西西北".equals(orientation)) {
							orientation = "西北朝向";
						} else if ("北东".equals(orientation)
								|| "东东北".equals(orientation)
								|| "北东北".equals(orientation)) {
							orientation = "东北朝向";
						} else if ("南东".equals(orientation)
								|| "东南南".equals(orientation)
								|| "东东南".equals(orientation)
								|| "东东南南".equals(orientation)) { 
							orientation = "东南朝向";
						} else if (orientation.length() == 2) {
							orientation = orientation + "朝向";
						} else if ("南北东".equals(orientation)
								|| "南北西".equals(orientation)
								|| "东南北".equals(orientation)
								|| "东北南".equals(orientation)
								|| "西南北".equals(orientation)
								|| "南西北".equals(orientation)
								|| "南东北".equals(orientation)
								|| "东南南北".equals(orientation)
								|| "东东南北".equals(orientation)
								|| "东南东北".equals(orientation)
								|| "南西南北".equals(orientation)
								|| "南北东北".equals(orientation)
								|| "西南西北".equals(orientation)
								|| "东南南北东北".equals(orientation)
								|| "东东南东北".equals(orientation)) {
							orientation = "南北通透";
						} else if ("东西北".equals(orientation)
								|| "西东北".equals(orientation)
								|| "东西西北".equals(orientation)
								|| "西北东北".equals(orientation)
								|| "东西西北东北".equals(orientation)
								|| "东西北东北".equals(orientation)
								|| "东西北东北".equals(orientation)
								|| "东西北东北".equals(orientation)) {
							orientation = "北向";
						} else if ("东西南".equals(orientation)
								|| "南西东".equals(orientation)
								|| "东南西".equals(orientation)
								|| "东东南西".equals(orientation)
								|| "东南西南".equals(orientation)) {
							orientation = "南向";
						} else if ("东西南北".equals(orientation) 
								|| "东南西北".equals(orientation)
								|| "南西北东".equals(orientation)
								|| "南东北西".equals(orientation)
								|| "东南北西".equals(orientation)
								|| "西南东北".equals(orientation)
								|| "东南西北北".equals(orientation)
								|| "东南西南北".equals(orientation)
								|| "东南南西北".equals(orientation)
								|| "南西南东北".equals(orientation)
								|| "东南西南东北".equals(orientation)
								|| "东南西南西北".equals(orientation)
								|| "西南西北东北".equals(orientation)
								|| "东南西北东北".equals(orientation)) {
							orientation = "东西南北";
						} else {
							logger.error("链家地产方向数据错误 orientation = " + orientation );
						}
			
			return orientation;
	}

	private void dealDic(HouseAlikeCommunityPo dic) {
		if (StringUtils.isNotBlank(dic.getAreaName3()) && StringUtils.isNotBlank(dic.getAreaCode2())) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("name", dic.getAreaName3());
			paramMap.put("parentId", dic.getAreaCode2());
			AreaPo area = areaService.getBy(paramMap);
			if (area != null) {
				dic.setAreaCode3(area.getId().toString());
			}
		}

		if (StringUtils.isNotBlank(dic.getTradingAreaName())) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("name", dic.getTradingAreaName());
			paramMap.put("parentId", dic.getAreaCode3());
			TradeAreaPo tradeArea = tradeAreaService.getBy(paramMap);
			if (tradeArea != null) {
				dic.setTradingAreaId(tradeArea.getId().toString());
			}/*else{
				
				//商圈不存在需要保存
				TradeAreaPo tradeAreaPo=new TradeAreaPo();
				
				tradeAreaPo.setName(dic.getTradingAreaName());
				tradeAreaPo.setParentId(Long.parseLong(dic.getAreaCode3()));
				tradeAreaPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
				tradeAreaPo.setCreateTime(new Date());
				buildTradeAreaPo(tradeAreaPo);
				if(tradeAreaPo!=null && StringUtils.isNotBlank(tradeAreaPo.getName())){
					tradeAreaService.saveDynamic(tradeArea);
				}
			}*/
		}

		if (StringUtils.isNotBlank(dic.getHeatingModeName())) {
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.HEATING_MODE),
					dic.getHeatingModeName());
			dic.setHeatingMode(groupTypeId.intValue());
		} else {
			dic.setHeatingModeName("其他");
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.HEATING_MODE),
					dic.getHeatingModeName());
			dic.setHeatingMode(groupTypeId.intValue());
		}

		if (StringUtils.isNotBlank(dic.getBuildingTypeName())) {// 建筑类
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.ACH_H_TYPE),
					dic.getBuildingTypeName());
			dic.setBuildingType(groupTypeId.toString());
		}

		if (StringUtils.isNotBlank(dic.getCertificateName())) {// 产权年限
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.CERTIFICATE),
					dic.getCertificateName());
			dic.setCertificate(groupTypeId.intValue());
		}

		if (StringUtils.isNotBlank(dic.getPropertyRightName())) {// 产权性质
			Long groupTypeId = groupTypeService.saveIdByPidAndOname(Integer.parseInt(BusinessConst.PROPERTY_RIGHT),
					dic.getPropertyRightName());
			dic.setPropertyRight(groupTypeId.intValue());
		}
		try{
			if(StringUtils.isBlank(dic.getAccuracy())||"0".equals(dic.getAccuracy())){
				dic.setDimension(LngLatUtil.latLng(dic.getAreaName2()+dic.getAreaCode3()+dic.getName())[0]);
				dic.setAccuracy(LngLatUtil.latLng(dic.getAreaName2()+dic.getAreaCode3()+dic.getName())[1]);
			}
		}catch(Exception e){
			logger.error("zuobiao error:",e);
		}
	
	}
	
	private void buildTradeAreaPo(TradeAreaPo tradeAreaPo) {
		tradeAreaPo.setLikePinyin(PinyinUtil.getFirstWord(tradeAreaPo.getName()));
		tradeAreaPo.setEnglishHead(PinyinUtil.getFirstWords(tradeAreaPo.getName()));
		tradeAreaPo.setEnglishName(PinyinUtil.getPinyin(tradeAreaPo.getName()));
		if (tradeAreaPo.getParentId()!=null) {
			AreaPo fatherPo = areaService.getById(tradeAreaPo.getParentId());
			AreaPo grandfatherPo = areaService.getById(fatherPo.getParentId());
			tradeAreaPo.setDimension(LngLatUtil.latLng(grandfatherPo.getName()+fatherPo.getName()+tradeAreaPo.getName())[0]);
			tradeAreaPo.setAccuracy(LngLatUtil.latLng(grandfatherPo.getFullName()+fatherPo.getName()+tradeAreaPo.getName())[1]);
		}
	}


	// 下架
	@Override
	public void saveDownOldHouse(Integer pageNum, Integer pageSize) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageFirst", pageNum);
		hashMap.put("pageSize", pageSize);
		List<OldHouseMasterPo> list = oldHouseMasterService.listLeftAgentPage(hashMap);
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (OldHouseMasterPo oldHouseMasterPo : list) {
			// 厅
			map.put("hall", oldHouseMasterPo.getHall());
			// 室
			map.put("room", oldHouseMasterPo.getRoom());
			// 小区id
			/*HouseLianjiaCommunityPo po = houseLianjiaCommunityService.getById(oldHouseMasterPo.getDicId());
			map.put("dicId", po.getDicId());*/
			// 朝向
		//	map.put("orientations", oldHouseMasterPo.getOrientations());
			// 面积
			map.put("buildArea", oldHouseMasterPo.getBuildArea());
			// 公司类型
			map.put("companyType", oldHouseMasterPo.getCompanyType());
			
			map.put("title", oldHouseMasterPo.getTitle());
			
			List<HouseMasterCrawlerPo> listCrawler = dao.listBy(map);
			if(listCrawler.isEmpty()){
				String[] ids = { String.valueOf(oldHouseMasterPo.getId()) };
				oldHouseMasterService.updateMasterStatus(ids,3);
				if(null == oldHouseMasterPo.getFatherId()){
					oldHouseMasterService.deleteErshoufangSolr(ids);
				}
			}
		}
		
	}
	
	
	//保存二手房其他的数据
	public Long saveOldMasterOtherDate(HouseMasterCrawlerPo houseMasterCrawlerPo,OldHouseMasterPo oldHouseMasterPo){
		/**
		 * 1保存图片
		 */
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("originObjId", houseMasterCrawlerPo.getOriginHouseId());
		
		paramMap.put("compayType", houseMasterCrawlerPo.getCompanyType());
		
		
		List<OldHousePictureCrawlerPo> oldPicList= oldhousePictureCrawlerService.listBy(paramMap);
		if(oldPicList.size()>0){
			oldHouseMasterPo.setImageUrl(oldPicList.get(0).getPictureUrl());
		}
		oldHouseMasterPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
		Long newId=oldHouseMasterService.saveDynamic(oldHouseMasterPo);
		
		List<OldHousePicturePo>  ywOldOpicList=new ArrayList<OldHousePicturePo>();
		for(OldHousePictureCrawlerPo po:oldPicList){
			OldHousePicturePo oldHousePicturePo=new OldHousePicturePo();
			
			try {
				MyBeanUtils.copyBean2Bean(oldHousePicturePo, po);
			} catch (Exception e) {
				e.printStackTrace();
			}
			oldHousePicturePo.setObjType(2);
			oldHousePicturePo.setObjId(newId);
			//oldHousePictureService.saveDynamic(oldHousePicturePo);
			oldHousePicturePo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			ywOldOpicList.add(oldHousePicturePo);
		}
		
		if(oldPicList.size()>0){
			oldHousePictureService.save(ywOldOpicList);
		}
		/**
		 * 2保存经纪人
		 */
		paramMap.remove("originObjId");
		paramMap.put("originHouseId", houseMasterCrawlerPo.getOriginHouseId());
		List<HouseAgentCrawlerPo> houseAgentCrawlerList=houseAgentCrawlerService.listBy(paramMap);
		if(houseAgentCrawlerList.size()>1){
			houseAgentCrawlerService.deleteById(houseAgentCrawlerList.get(1).getId());
		}
		if(houseAgentCrawlerList.size()>0){
			OldHouseAgentPo oldHouseAgent=new OldHouseAgentPo();
			oldHouseAgent.setOldMasterId(newId);
			try {
				MyBeanUtils.copyBean2Bean(oldHouseAgent, houseAgentCrawlerList.get(0));
			} catch (Exception e) {
				e.printStackTrace();
			}
			oldHouseAgent.setId(null);
			oldHouseAgentService.saveDynamic(oldHouseAgent);
		}
		
		return newId; 
	}

	@Override
	public List<HouseMasterCrawlerPo> selectHouseMasterCrawler(long id) {
		Map<String, Object> map=new HashMap<>();
		map.put("dicTempId", id);
		return dao.getSelectHouseMasterCrawler(map);
	}

	@Override
	public List<HouseMasterCrawlerPo> listNewPage(HashMap<String, Object> hashMap) {
		return dao.listNewPage(hashMap);
	}

	@Override
	public void saveDynamicCrawler(List<OldHouseMasterPo> fathferList,HouseMasterCrawlerPo houseMasterCrawlerPo) {
		// 如果有则合并,没有就直接添加并上架
		if (fathferList.size()>0) {//没有符ID
			OldHouseMasterPo nofathferPo = fathferList.get(0);
			
			houseMasterCrawlerPo.setFatherId(nofathferPo.getId());
			OldHouseMasterPo oldHouseMasterPo = new OldHouseMasterPo();
			try {
				MyBeanUtils.copyBean2Bean(oldHouseMasterPo, houseMasterCrawlerPo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			oldHouseMasterPo.setCreateTime(new Date());
			oldHouseMasterPo.setUpdateTime(new Date());
			if(oldHouseMasterPo.getDicId()!=null){
				setDicId(oldHouseMasterPo);
			}else{
				oldHouseMasterPo.setStatus(HouseStatusEnum.DOWN.getValue());
			}
			oldHouseMasterPo.setIsCrawlerAdd(YesNoEnum.YES.getValue());
			oldHouseMasterPo.setCompanyTypes(String.valueOf(oldHouseMasterPo.getCompanyType()));
			oldHouseMasterPo.setId(null);
			
			
			Boolean houseFlag=true;
			if(StringUtils.isNotBlank(oldHouseMasterPo.getHouseId())){
				Map<String,Object> oldParamMap=new HashMap<String,Object>();
				oldParamMap.put("houseId", oldHouseMasterPo.getHouseId());
				OldHouseMasterPo hasOld= oldHouseMasterService.getBy(oldParamMap);
				
				if(hasOld!=null){
					houseFlag=false;
				}
			}else{
				oldHouseMasterPo.setHouseId(CodeUtils.getErshouCode());
			}
			
			if(houseFlag&&oldHouseMasterPo.getDicId()!=null){
			//	
				
				saveOldMasterOtherDate( houseMasterCrawlerPo,oldHouseMasterPo);
				//更父id companyTypes
				
				if(null !=oldHouseMasterPo.getCompanyType()){
					if(StringUtils.isNotBlank(nofathferPo.getCompanyTypes())){
						oldHouseMasterService.updateCompanyTypes(nofathferPo.getCompanyTypes()+","+oldHouseMasterPo.getCompanyType(),
								nofathferPo.getId());
					
					}
					
				}
				
				/* 合并后上架父数据
				 */
				if (nofathferPo.getStatus() == HouseStatusEnum.ON.getValue()) {
					String[] ids = { String.valueOf(nofathferPo.getId()) };
					oldHouseMasterService.saveErshoufangSolr(ids);
				}
				
			}
		} else {
			OldHouseMasterPo oldHouseMasterPo = new OldHouseMasterPo();
			try {
				MyBeanUtils.copyBean2Bean(oldHouseMasterPo, houseMasterCrawlerPo);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			oldHouseMasterPo.setCreateTime(new Date());
			oldHouseMasterPo.setUpdateTime(new Date());
		
			oldHouseMasterPo.setStatus(HouseStatusEnum.ON.getValue());
			if(oldHouseMasterPo.getDicId()!=null){
				setDicId(oldHouseMasterPo);
			}else{
				oldHouseMasterPo.setStatus(HouseStatusEnum.DOWN.getValue());
			}
			oldHouseMasterPo.setIsCrawlerAdd(YesNoEnum.YES.getValue());
			oldHouseMasterPo.setCompanyTypes(String.valueOf(oldHouseMasterPo.getCompanyType()));
			oldHouseMasterPo.setId(null);
			
			
			Boolean houseFlag=true;
			if(StringUtils.isNotBlank(oldHouseMasterPo.getHouseId())){
				Map<String,Object> oldParamMap=new HashMap<String,Object>();
				oldParamMap.put("houseId", oldHouseMasterPo.getHouseId());
				OldHouseMasterPo hasOld= oldHouseMasterService.getBy(oldParamMap);
				
				if(hasOld!=null){
					houseFlag=false;
				}
			}else{
				oldHouseMasterPo.setHouseId(CodeUtils.getErshouCode());
			}
			if(houseFlag&&oldHouseMasterPo.getDicId()!=null){
				
				Long newId= saveOldMasterOtherDate( houseMasterCrawlerPo,oldHouseMasterPo);
				String[] ids = { String.valueOf(newId) };
				if(oldHouseMasterPo.getStatus()==HouseStatusEnum.ON.getValue()){
					oldHouseMasterService.saveErshoufangSolr(ids);
				}
				
			}
		}
		
	}
	
	private void setDicId(OldHouseMasterPo oldHouseMasterPo){
		oldHouseMasterPo.setStatus(HouseStatusEnum.ON.getValue());
		HouseLianjiaCommunityPo houseLianjiaCommunity =houseLianjiaCommunityService.getById(oldHouseMasterPo.getDicId());
		if(houseLianjiaCommunity.getDicId()!=null){
			oldHouseMasterPo.setDicId(houseLianjiaCommunity.getDicId());
			
			if(StringUtils.isNotBlank(houseLianjiaCommunity.getTradingAreaId())){
				oldHouseMasterPo.setStatus(HouseStatusEnum.ON.getValue());
			}else{
				oldHouseMasterPo.setStatus(HouseStatusEnum.DOWN.getValue());
			}
		}else{
			Map<String,Object> dicparamMap=new HashMap<String,Object>();
			
			dicparamMap.put("sprayName", houseLianjiaCommunity.getName());
			dicparamMap.put("tradingAreaId", houseLianjiaCommunity.getTradingAreaId());
			List<HouseDirectoryPo> houseDirectoryList=houseDirectoryService.listBy(dicparamMap);
			if(!houseDirectoryList.isEmpty()){
				oldHouseMasterPo.setDicId(houseDirectoryList.get(0).getId());
				oldHouseMasterPo.setStatus(HouseStatusEnum.ON.getValue());
			}else{
				oldHouseMasterPo.setStatus(HouseStatusEnum.DOWN.getValue());
			}
			
		}
	}

	@Override
	public Long getAllCount() {
		return dao.getAllCount();
	}

	@Override
	public void updateIsDeal(Integer isDeal, Long id) {
		dao.updateIsDeal(isDeal,id);
	}
}
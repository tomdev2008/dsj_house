package com.dsj.modules.oldHouseParser.biz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.enums.YesNoEnum;
import com.dsj.modules.oldHouseParser.po.HouseAlikeCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseLianjiaCommunityPo;
import com.dsj.modules.oldHouseParser.po.HouseMasterCrawlerPo;
import com.dsj.modules.oldHouseParser.service.HouseAlikeCommunityService;
import com.dsj.modules.oldHouseParser.service.HouseLianjiaCommunityService;
import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class HouseMasterCrawlerBizImpl  implements HouseMasterCrawlerBiz {
	private static final Logger logger = LoggerFactory.getLogger(HouseMasterCrawlerBizImpl.class);

	@Autowired
	private HouseLianjiaCommunityService houseLianjiaCommunityService;
	
	@Autowired
	private HouseAlikeCommunityService houseAlikeCommunityService;
	
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	
	@Autowired
	private HouseMasterCrawlerService houseMasterCrawlerService;


	
	/**
	 * 
	 * @param houseMasterCrawler 二手房
	 * 面积，朝向，小区，厅室 都一样
	 * @throws Exception 
	 */
	// 合并/上架
	@Override
	public void saveHeBingOldHouse(Integer pageNum, Integer pageSize) throws Exception {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("pageFirst", pageNum);
		hashMap.put("pageSize", pageSize);
		
		hashMap.put("notDicId", YesNoEnum.YES.getValue());
		List<HouseMasterCrawlerPo> list = houseMasterCrawlerService.listNewPage(hashMap);
		HashMap<String, Object> map = new HashMap<String, Object>();
		for (HouseMasterCrawlerPo houseMasterCrawlerPo : list) {
			try{
				if(houseMasterCrawlerPo.getDicId()!=null 
						&&houseMasterCrawlerPo.getIsDeal()!=YesNoEnum.YES.getValue()){
					map.clear();
					// 厅
					map.put("hall", houseMasterCrawlerPo.getHall());
					// 室
					map.put("room", houseMasterCrawlerPo.getRoom());
					// 小区id
					HouseLianjiaCommunityPo po = houseLianjiaCommunityService.getById(houseMasterCrawlerPo.getDicId());
					//map.put("dicId", po.getDicId());
					
					if(po!=null){
						Map<String,Object> dicMap=new HashMap<String,Object>();
						dicMap.put("company", po.getCompany());
						dicMap.put("lianjiaId", po.getId());
						List<HouseAlikeCommunityPo> houseAlikeCommunitys =houseAlikeCommunityService.listBy(dicMap);
						
						if(houseAlikeCommunitys.size()>0){
							map.put("dicName", houseAlikeCommunitys.get(0).getName());
						}
					}
					// 朝向
					map.put("orientations", houseMasterCrawlerPo.getOrientations());
					// 面积
					map.put("buildArea", houseMasterCrawlerPo.getBuildArea());
					// 公司类型
					map.put("companyType", houseMasterCrawlerPo.getCompanyType());
					
					map.put("title", houseMasterCrawlerPo.getTitle());//标题
					
					List<OldHouseMasterPo> oldList = oldHouseMasterService.listJoinDicBy(map);
					// 如果之前数据不存在此类型的数据
					if (oldList.isEmpty() ) {
						map.remove("companyType");
						map.remove("title");
						map.put("nofathful", "1");
						map.put("notCompanyTypes",  houseMasterCrawlerPo.getCompanyType());
						// 查询是否有满足合并规则的数据
						List<OldHouseMasterPo> fathferList = new ArrayList<OldHouseMasterPo>();
						if(map.get("dicName")!=null){
							
							fathferList=oldHouseMasterService.listJoinDicBy(map); //有父id的数据
							
						}
						houseMasterCrawlerService.saveDynamicCrawler(fathferList, houseMasterCrawlerPo);
						
					} else {
						// 如果存在,但之前的数据是下架状态,那就把它上架
						for (OldHouseMasterPo oldHouseMasterPo : oldList) {
							if (null == oldHouseMasterPo.getFatherId() && oldHouseMasterPo.getStatus() != 2 
									&& StringUtils.isNotBlank(oldHouseMasterPo.getTitle()) && oldHouseMasterPo.getDicId()!=null) {
								String[] ids = { String.valueOf(oldHouseMasterPo.getId()) };
								oldHouseMasterService.saveErshoufangSolr(ids);
								break;
							}
						}
					}
				}
				
				houseMasterCrawlerService.updateIsDeal(YesNoEnum.YES.getValue(),houseMasterCrawlerPo.getId());
			}catch(Exception e){
				logger.error("hebing erro:",e);
			}
		}
	}
	
}
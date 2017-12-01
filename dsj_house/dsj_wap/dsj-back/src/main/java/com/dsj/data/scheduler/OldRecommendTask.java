package com.dsj.data.scheduler;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.oldhouse.enums.HouseStatusEnum;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.HouseDirectoryService;

@Component
public class OldRecommendTask {
	
	private final Logger LOGGER = LoggerFactory.getLogger(OldRecommendTask.class);

	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	
	@Autowired
	private HouseDirectoryService houseDirectoryService;

	@Scheduled(cron = "0 0 2 * * ? ") 
	public void tatk() {
		try {
			//1 把总数据分成几大份开线程跑 
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			paramMap.put("status", HouseStatusEnum.ON.getValue());
			Integer count = oldHouseMasterService.listCount(paramMap);
			int group = 6;
			int n = count/group;
			
			if (n == 0) {
				TaskData task = new TaskData(0,count,paramMap,oldHouseMasterService,houseDirectoryService,LOGGER);
				new Thread(task,"T0").start();
			}
			for (int i = 1; i <= group; i++) {
				if (group == i) {
					TaskData task = new TaskData((i-1)*n,count,paramMap,oldHouseMasterService,houseDirectoryService,LOGGER);
					System.out.println("二手房推荐线程T"+i+"开始");
					new Thread(task,"T"+i).start();
				}else{
					TaskData task = new TaskData((i-1)*n, i*n,paramMap,oldHouseMasterService,houseDirectoryService,LOGGER);
					System.out.println("二手房推荐线程T"+i+"开始");
					new Thread(task,"T"+i).start();
				}
				
			}
		} catch (Exception e) {
			LOGGER.error("二手房推荐任务出错",e);
		}
	}

}

class TaskData implements Runnable {
	
	private Integer cup = 1000 ;
	
	private Integer start ;
	private Integer end ;
	private Map<String, Object> map ;
	private Logger LOGGER;
	
	private OldHouseMasterService oldHouseMasterService;
	
	private HouseDirectoryService houseDirectoryService;
	
	public TaskData(Integer start,Integer end ,Map<String, Object> map,
			OldHouseMasterService oldHouseMasterService,
			HouseDirectoryService houseDirectoryService,
			Logger LOGGER){
		this.start = start;
		this.end = end;
		this.oldHouseMasterService = oldHouseMasterService;
		this.houseDirectoryService = houseDirectoryService;
		this.map = map;
		this.LOGGER = LOGGER;
	}
	
	@Override
	public void run() {
		Integer count = end - start ;
		Integer group = count/cup;//把数据每份1000分成group组
		try {
			if (group == 0) group = 1;
			
			for (int i = 1; i <= group; i++) {
				LOGGER.info(">>>>>>>>>OldRecommendTaski:"+Thread.currentThread().getName()+"--"+count);
				LOGGER.info(">>>>>>>>>OldRecommendTasktime:"+new Date());
				map.put("startNum", (i-1)*cup+start);
				if (group == i) {
					map.put("endNum", count - cup*(group-1));
				}else {
					map.put("endNum", cup);
				}
				List<OldHouseMasterPo>  poList = oldHouseMasterService.selectByLimit(map);
				for (OldHouseMasterPo po : poList) {
					List<String> ids = getRecommendList(po);
					oldHouseMasterService.deleteOldRecommend(po.getId());
					oldHouseMasterService.updateOldRecommend(po.getId(),ids);
				}
				LOGGER.info(">>>>>>>>>OldRecommendTaski:"+Thread.currentThread().getName()+"--"+count);
				LOGGER.info(">>>>>>>>>OldRecommendTasktime:"+new Date());
			}
		} catch (Exception e) {
			LOGGER.error(">>>>>>>>>OldRecommendTasktime error",e);
		}
		
		
	}
	
	public List<String> getRecommendList(OldHouseMasterPo originPo) {
		//推荐规则：
		//同小区（按价格）
		//同商圈（按价格）
		//同价格（按距离排序，距离近的优先展示）租房500浮动 二手房50万浮动
		List<String> ids = new ArrayList<String>();
		
		if (originPo.getDicId() == null) {//测试用
			originPo.setDicId((long) 13197);
		}
		HouseDirectoryPo houseDirectoryPo = houseDirectoryService.getById(originPo.getDicId());
		if (houseDirectoryPo == null ) {
			houseDirectoryPo = houseDirectoryService.getById(13197);
		}

		Integer size = 8;//每条房源的推荐生成数量
		Map<String, Object> map = new HashMap<>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		map.put("status", HouseStatusEnum.ON.getValue());
		map.put("size", size);//保存推荐个数
		map.put("dicId", houseDirectoryPo.getId());
		map.put("houseId", originPo.getId());
		map.put("rentPrice", originPo.getPrice());
		List<String> villageList = oldHouseMasterService.getSameVillageList(map);
		
		ids = distinctIds(ids , villageList);
		if (!ids.isEmpty() && ids.size() >=size ) {
			return ids.subList(0, size);
		}
		
		map.put("tradingId", houseDirectoryPo.getTradingAreaId());
		List<String> tradeList = oldHouseMasterService.getSameTradeList(map);
		
		ids = distinctIds(ids , tradeList);
		if (!ids.isEmpty() && ids.size() >= size ) {
			return ids.subList(0, size);
		}
		
		map.put("dimension", houseDirectoryPo.getDimension());//纬度
		map.put("accuracy", houseDirectoryPo.getAccuracy());//经度
		map.put("price", 500000);//价格偏差 50万
		List<String> similarList = oldHouseMasterService.getSimilarList(map);
		
		ids = distinctIds(ids , similarList);
		if (!ids.isEmpty() && ids.size() >= size ) {
			return ids.subList(0, size);
		}
		
		//条件任然不满足 则选取最新房源填补空余
		List<String> lateList = oldHouseMasterService.getLateList(map);
		ids = distinctIds(ids , lateList);
		if (!ids.isEmpty() && ids.size() >= size ) {
			return ids.subList(0, size);
		}
		
		return ids;
	}

	private List<String> distinctIds(List<String> ids, List<String> idsNew) {
		for(String id : idsNew){  
		    if(!ids.contains(id)){  
		    	ids.add(id);
		    }  
		}
		return ids;  
	}
	
}


package com.dsj.data.scheduler;

import java.util.ArrayList;
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
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.rent.enums.RentHouseStatusEnum;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.service.RentHouseOriginService;

@Component
public class RentRecommendTask {
	
	private final Logger LOGGER = LoggerFactory.getLogger(RentRecommendTask.class);
	
	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	
	@Scheduled(cron = "0 0 2 * * ? ")
	public void tatk() {
		try {
			//1 把总数据分成几大份开线程跑 
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			paramMap.put("status", HouseStatusEnum.ON.getValue());
			Integer count = rentHouseOriginService.listCount(paramMap);
			int group = 6;
			int n = count/group;
			
			if (n == 0) {
				TaskRentData task = new TaskRentData(0,count,paramMap,rentHouseOriginService,houseDirectoryService);
				new Thread(task,"T0").start();
			}
			
			for (int i = 1; i <= group; i++) {
				TaskRentData task = null;
				if (group == i) {
					task = new TaskRentData((i-1)*n,count,paramMap,rentHouseOriginService,houseDirectoryService);
				}else{
					task = new TaskRentData((i-1)*n, i*n,paramMap,rentHouseOriginService,houseDirectoryService);
				}
				new Thread(task,"T"+i).start();
			}
		} catch (Exception e) {
			LOGGER.error("租房推荐任务出错",e);
		}
		
	}
	
}

class TaskRentData implements Runnable {
	
	private Integer cup = 1000 ;
	
	private Integer start ;
	private Integer end ;
	private Map<String, Object> map ;
	
	private RentHouseOriginService rentHouseOriginService;
	
	private HouseDirectoryService houseDirectoryService;
	
	public TaskRentData(Integer start,Integer end ,Map<String, Object> map,
			RentHouseOriginService rentHouseOriginService,
			HouseDirectoryService houseDirectoryService){
		this.start = start;
		this.end = end;
		this.rentHouseOriginService = rentHouseOriginService;
		this.houseDirectoryService = houseDirectoryService;
		this.map = map;
	}
	
	@Override
	public void run() {
		Integer count = end - start ;
		Integer group = count/cup;//把数据每份1000分成group组
		
		if (group == 0) {
			List<RentHouseOriginPo> poList = null;
			map.put("startNum", start);
			map.put("endNum", end);
			poList = rentHouseOriginService.selectByLimit(map);
			System.out.println(Thread.currentThread().getName()+"--"+
					map.get("startNum")+"--"+map.get("endNum"));
			for (RentHouseOriginPo po : poList) {
				List<String> ids = getRecommendList(po);
				rentHouseOriginService.deleteRentRecommend(po.getId());
				rentHouseOriginService.updateRentRecommend(po.getId(),ids);
			}
		}
		
		for (int i = 1; i <= group; i++) {
			List<RentHouseOriginPo> poList = null;
			if ( group == i ) {
				map.put("startNum", (i-1)*cup+start);
				map.put("endNum", end);
				poList = rentHouseOriginService.selectByLimit(map);
			}else{
				map.put("startNum", (i-1)*cup+start);
				map.put("endNum", i*cup + start);
				poList = rentHouseOriginService.selectByLimit(map);
			}
			System.out.println(Thread.currentThread().getName()+"--"+
						map.get("startNum")+"--"+map.get("endNum"));
			for (RentHouseOriginPo po : poList) {
				List<String> ids = getRecommendList(po);
				rentHouseOriginService.deleteRentRecommend(po.getId());
				rentHouseOriginService.updateRentRecommend(po.getId(),ids);
			}
		}
	}
	
	public List<String> getRecommendList(RentHouseOriginPo originPo) {
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
		
		Integer size = 8;
		Map<String, Object> map = new HashMap<>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		map.put("status", RentHouseStatusEnum.ON.getValue());
		map.put("size", size);//保存推荐个数
		map.put("dicId", houseDirectoryPo.getId());
		map.put("houseId", originPo.getId());
		map.put("rentPrice", originPo.getRentPrice());
		List<String> villageList = rentHouseOriginService.getSameVillageList(map);
		
		ids = distinctIds(ids , villageList);
		if (!ids.isEmpty() && ids.size() >=size ) {
			return ids.subList(0, size);
		}
		
		map.put("tradingId", houseDirectoryPo.getTradingAreaId());
		List<String> tradeList = rentHouseOriginService.getSameTradeList(map);
		
		ids = distinctIds(ids , tradeList);
		if (!ids.isEmpty() && ids.size() >= size ) {
			return ids.subList(0, size);
		}
		
		map.put("dimension", houseDirectoryPo.getDimension());//纬度
		map.put("accuracy", houseDirectoryPo.getAccuracy());//经度
		map.put("price", 500);//租金偏差
		List<String> similarList = rentHouseOriginService.getSimilarList(map);
		
		ids = distinctIds(ids , similarList);
		if (!ids.isEmpty() && ids.size() >= size ) {
			return ids.subList(0, size);
		}
		
		//条件任然不满足 则选取最新房源填补空余
		List<String> lateList = rentHouseOriginService.getLateList(map);
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

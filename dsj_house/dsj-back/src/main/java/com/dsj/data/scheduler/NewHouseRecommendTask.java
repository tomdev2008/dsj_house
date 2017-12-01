package com.dsj.data.scheduler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsj.modules.newhouse.enums.NewHouseEditEnum;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.enums.NewHouseSaleStatusEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;

@Component
public class NewHouseRecommendTask {
	
	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseRecommendTask.class);
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	@Scheduled(cron = "0 0/30 * * * ?")
	public void tatk() {
		try {
			//1 把总数据分成几大份开线程跑 
			Map<String, Object> paramMap = new HashMap<>();
			paramMap.put("isTrue", NewHouseIsTrueEnum.UP.getValue());
			Integer count = newHouseDirectoryAuthService.listCount(paramMap);
			int group = 6;
			int n = count/group;
			
			if (n == 0) {
				TaskNewHouseData task = new TaskNewHouseData(0,count,paramMap,newHouseDirectoryAuthService);
				new Thread(task,"T0").start();
			}
			
			for (int i = 1; i <= group; i++) {
				TaskNewHouseData task = new TaskNewHouseData((i-1)*n, n,paramMap,newHouseDirectoryAuthService);
				//System.out.println((i-1)*n+"--"+n);
				new Thread(task,"T"+i).start();
			}
		} catch (Exception e) {
			LOGGER.error("新房推荐任务出错",e);
		}
		
	}
	
}

class TaskNewHouseData implements Runnable {
	
	//private Integer cup = 1000 ;
	
	private Integer start ;
	private Integer end ;
	private Map<String, Object> map ;
	
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	public TaskNewHouseData(Integer start,Integer end ,Map<String, Object> map,
			NewHouseDirectoryAuthService newHouseDirectoryAuthService){
		this.start = start;
		this.end = end;
		this.newHouseDirectoryAuthService = newHouseDirectoryAuthService;
		this.map = map;
	}
	
	@Override
	public void run() {
		map.put("startNum", start);
		map.put("endNum", end);
		map.put("edit", NewHouseEditEnum.YES.getValue());
		map.put("isTrue", NewHouseIsTrueEnum.UP.getValue());
		List<NewHouseDirectoryAuthPo> poList = null;
		poList = newHouseDirectoryAuthService.selectByLimit(map);
		System.out.println(Thread.currentThread().getName()+"--"+
				start+"--"+end);
		for (NewHouseDirectoryAuthPo po : poList) {
			newHouseDirectoryAuthService.setRecommendList(po);
		}
	}
	
	
}

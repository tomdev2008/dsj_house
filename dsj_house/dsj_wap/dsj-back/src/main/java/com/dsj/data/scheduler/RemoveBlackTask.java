package com.dsj.data.scheduler;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.dsj.common.utils.DateUtils;
import com.dsj.modules.system.enums.MemberEnum;
import com.dsj.modules.system.po.MemberPo;
import com.dsj.modules.system.service.MemberService;

@Component
public class RemoveBlackTask { 
	private final Logger LOGGER = LoggerFactory.getLogger(RemoveBlackTask.class);
	
	@Autowired
	private MemberService memberService;
	
	@Scheduled(cron = "0 0 2 * * ? ")
	public void task(){
		try {
			Map<String,Object> paramMap = new HashMap<String,Object>();
			paramMap.put("isBlack",MemberEnum.IS_BLACK.getCode());
			List<MemberPo> list = memberService.listBy(paramMap);
			String ids = "";
			for(MemberPo item : list){
				if(item.getBlackNum()==1){
					if(DateUtils.getDistanceOfTwoDate(item.getBlackTime(),new Date())>=3);{
						ids = ids+item.getId()+",";
					}
				}else if(item.getBlackNum()==2){
					if(DateUtils.getDistanceOfTwoDate(item.getBlackTime(),new Date())>=7);{
						ids = ids+item.getId()+",";
					}
				}
			}
			if(!ids.equals("")){
				ids = ids.substring(0,ids.length() - 1);
			}
			memberService.updateRemoveBlack(ids);
		} catch (Exception e) {
			LOGGER.error("【定时任务】:接触拉黑异常",e);
		}
		
		
	}
}

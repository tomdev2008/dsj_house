package com.dsj.data.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.dsj.modules.system.po.AgentStatisticsPo;
import com.dsj.modules.system.po.MemberPo;
import com.dsj.modules.system.service.AgentStatisticsService;

@Component
public class AgentStatisticsTask {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentStatisticsTask.class);
	
	@Autowired
	private AgentStatisticsService agentStatisticsService;
	
	@Scheduled(cron = "0 5 0 * * ? ")
	public void task(){
		try {
			List<Map<String,Object>> houseNews = agentStatisticsService.getHouseNewsYesterday();
			List<Map<String,Object>> agentNews = agentStatisticsService.getAgentNewsYesterday();
			List<Map<String,Object>> agentNewsLike = agentStatisticsService.getAgentNewsLikeYesterday();
			List<Map<String,Object>> agentNewsrReply = agentStatisticsService.getAgentNewsReplyYesterday();
			List<Map<String,Object>> houseRemark = agentStatisticsService.getHouseRemarkYesterday();
			List<Map<String,Object>> houseRemarkLike = agentStatisticsService.getHouseRemarkLikeYesterday();
			List<Map<String,Object>> houseRemarkReply = agentStatisticsService.getHouseRemarkReplyYesterday();
			List<Map<String,Object>> agentGrade = agentStatisticsService.getAgentGradeYesterday();
			
			
			
			List<AgentStatisticsPo> list = null;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			
			//1
			if(houseNews!=null&&houseNews.size()>0){
				for(Map<String,Object> item:houseNews){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setHouseNewsNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));

					paramMap.put("time",item.get("time").toString());
					
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功1");
						} catch (Exception e) {
							LOGGER.error("插入失败1",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败1",e);
						}
						
					}
						
				}
			}
			//2
			if(agentNews!=null&&agentNews.size()>0){
				for(Map<String,Object> item:agentNews){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setAgentNewsLikeNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功2");
						} catch (Exception e) {
							LOGGER.error("插入失败2",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败2",e);
						}
						
					}
						
				}
			}
			//3
			if(agentNewsLike!=null&&agentNewsLike.size()>0){
				for(Map<String,Object> item:agentNewsLike){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setAgentNewsLikeNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功3");
						} catch (Exception e) {
							LOGGER.error("插入失败3",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败3",e);
						}
						
					}
						
				}
			}
			//4
			if(agentNewsrReply!=null&&agentNewsrReply.size()>0){
				for(Map<String,Object> item:agentNewsrReply){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setAgentNewsReplyNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功4");
						} catch (Exception e) {
							LOGGER.error("插入失败4",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败4",e);
						}
						
					}
						
				}
			}
			//5
			if(houseRemark!=null&&houseRemark.size()>0){
				for(Map<String,Object> item:houseRemark){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setHouseRemarkNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功5");
						} catch (Exception e) {
							LOGGER.error("插入失败5",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败5",e);
						}
						
					}
						
				}
			}
			//6
			if(houseRemarkLike!=null&&houseRemarkLike.size()>0){
				for(Map<String,Object> item:houseRemarkLike){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setHouseRemarkLikeNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功6");
						} catch (Exception e) {
							LOGGER.error("插入失败6",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败6",e);
						}
						
					}
						
				}
			}
			//7
			if(houseRemarkReply!=null&&houseRemarkReply.size()>0){
				for(Map<String,Object> item:houseRemarkReply){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setHouseRemarkReplyNum(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功7");
						} catch (Exception e) {
							LOGGER.error("插入失败7",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败7",e);
						}
						
					}	
				}
			}
			//8
			if(agentGrade!=null&&agentGrade.size()>0){
				for(Map<String,Object> item:agentGrade){
					AgentStatisticsPo po = new AgentStatisticsPo();
					po.setAgentGrade(Integer.valueOf(item.get("number").toString()));
					if(item.get("user")==null){
						continue;
					}
					po.setUserId(Long.valueOf(item.get("user").toString()));
					try {
						po.setTime(sdf.parse(item.get("time").toString()));
					} catch (ParseException e) {
						e.printStackTrace();
						LOGGER.error("时间转换出错：",e);
					}
					
					Map<String,Object> paramMap = new HashMap<String,Object>();
					paramMap.put("userId", item.get("user"));
					paramMap.put("time",item.get("time").toString());
					AgentStatisticsPo p = agentStatisticsService.getBy(paramMap);
					
					if(p==null){
						try {
							po.setCreateTime(new Date());
							agentStatisticsService.saveDynamic(po);
							System.out.println("插入成功8");
						} catch (Exception e) {
							LOGGER.error("插入失败8",e);
						}
						
					}else{
						try {
							po.setId(p.getId());
							agentStatisticsService.updateDynamic(po);
							System.out.println("更新成功");
						} catch (Exception e) {
							LOGGER.error("更新失败8",e);
						}
						
					}
				}
			}
			System.out.println("经纪人统计定时任务结束");
		} catch (Exception e) {
			LOGGER.error("【定时任务】:统计经纪人昨日数据异常",e);
		}
		
		
	}
}

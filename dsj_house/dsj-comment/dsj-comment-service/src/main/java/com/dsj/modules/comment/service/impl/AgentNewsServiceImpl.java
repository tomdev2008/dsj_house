package com.dsj.modules.comment.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;

import com.dsj.modules.comment.service.AgentNewsService;
import com.dsj.modules.comment.vo.AgentNewsVo;
import com.dsj.modules.comment.dao.AgentNewsDao;
import com.dsj.modules.comment.enums.AgentNewsEnum;
import com.dsj.modules.comment.po.AgentNewsPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-11 12:08:06.
 * @版本: 1.0 .
 */
@Service
public class AgentNewsServiceImpl  extends BaseServiceImpl<AgentNewsDao,AgentNewsPo> implements AgentNewsService {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentNewsServiceImpl.class);
	@Autowired
	private AgentNewsDao agentNewsDao;
	@Override
	public void insertAgentNews(AgentNewsPo agentNews) {
		agentNewsDao.insert(agentNews);
		
	}
	@Override
	public List<AgentNewsPo> getNewsByUserId(Map<String,Object> map) {
		// TODO Auto-generated method stub
		return agentNewsDao.getNewsByUserId(map);
	}
	@Override
	public void updateRemoveStick(Map<String,Object> map) {
		agentNewsDao.updateRemoveStick(map);
		
	}
	@Override
	public void updateAddStick(Map<String, Object> map) {
		
		agentNewsDao.updateAddStick(map);
	}
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}
	@Override
	public void deleteMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			Integer delFlag = DeleteStatusEnum.DEL.getValue();
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("delFlag", delFlag);
 			agentNewsDao.deleteByIds(map);
			
				
		}
		
	}
	@Override
	public PageBean listAgentNewsPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap,"listAgentNewsCount","listAgentNewsPage");
	}
	@Override
	public AgentNewsVo getVoById(long id) {
		return agentNewsDao.getVoById(id);
	}
	
} 
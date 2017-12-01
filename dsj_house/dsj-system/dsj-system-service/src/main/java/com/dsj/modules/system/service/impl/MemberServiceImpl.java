package com.dsj.modules.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.system.enums.MemberEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.system.service.MemberService;
import com.dsj.modules.system.dao.MemberDao;
import com.dsj.modules.system.po.MemberPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 19:51:03.
 * @版本: 1.0 .
 */
@Service
public class MemberServiceImpl  extends BaseServiceImpl<MemberDao,MemberPo> implements MemberService {

	@Autowired
	private MemberDao memberDao;
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public void updateBlackMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("isBlack", MemberEnum.IS_BLACK.getCode());	
			map.put("blackTime",new Date());
			memberDao.updateBlackMany(map);
			
		}
		
	}

	@Override
	public void updateRemoveBlack(String id) {
		if(StringUtils.isNotBlank(id)){			
			String[] arrayId = id.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("isBlack", MemberEnum.NOT_BALCK.getCode());	
			map.put("updateTime",new Date());
			memberDao.updateRemoveBlack(map);				
		}
	
	}
}
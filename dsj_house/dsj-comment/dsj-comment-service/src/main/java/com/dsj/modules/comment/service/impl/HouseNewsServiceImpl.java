package com.dsj.modules.comment.service.impl;

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
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.comment.vo.HouseNewsVo;
import com.dsj.modules.comment.dao.HouseNewsDao;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.HouseNewsPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-11 13:36:52.
 * @版本: 1.0 .
 */
@Service
public class HouseNewsServiceImpl  extends BaseServiceImpl<HouseNewsDao,HouseNewsPo> implements HouseNewsService {
	@Autowired
	private HouseNewsDao houseNewsDao;
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public PageBean listIndustryNewsPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listIndustryNewsCount", "listIndustryNewsPage");
	}
	
	@Override
	public void insertHouseNews(HouseNewsPo houseNews) {
		houseNewsDao.insertDynamic(houseNews);
		
	}

	@Override
	public void updateMany(String ids) {
		if(StringUtils.isNotBlank(ids)){
			Integer upDownLine = HouseNewsStatusEnum.DOWN.getValue();
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("upDownLine", upDownLine);
			map.put("stick", 0);//不置顶
			houseNewsDao.downByIds(map);
			
				
		}
		
	}

	@Override
	public void updateAuditMany(String ids,int status,String msg, Integer auditPerson) {
		if(StringUtils.isNotBlank(ids)){
			Integer auditStatus = null;
			Integer upDownLine = null;
			if(status==HouseNewsStatusEnum.AUDIT_SUCCESS.getValue()){
				auditStatus = HouseNewsStatusEnum.AUDIT_SUCCESS.getValue();
				upDownLine = HouseNewsStatusEnum.UP.getValue();
			}else{
				auditStatus = HouseNewsStatusEnum.AUDIT_REFUSE.getValue();
				upDownLine = HouseNewsStatusEnum.NONE.getValue();
			}
			
			String[] arrayId = ids.split(",");
			List<Integer> idlist = new ArrayList<Integer>();
			for(int i=0;i<arrayId.length;i++){
				idlist.add(Integer.valueOf(arrayId[i]));
			}
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("list", idlist);
			map.put("auditStatus", auditStatus);
			map.put("auditUser", auditPerson);
			map.put("auditTime", new Date());
			map.put("auditReason", msg);
			map.put("upDownLine", upDownLine);
			houseNewsDao.updateAuditMany(map);
			
				
		}
		
	}

	@Override
	public HouseNewsVo getVoById(long id) {
		return houseNewsDao.getVoById(id);
	}

	@Override
	public PageBean listHouseNewsPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listHouseNewsCount", "listHouseNewsPage");
	}
	
	@Override
	public PageBean listPCHouseNewsPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listPCHouseNewsCount", "listPCHouseNewsPage");
	}

	@Override
	public void updateRemoveStick(Map<String, Object> map) {
		dao.updateRemoveStick(map);
	}

	@Override
	public void updateAddStick(Map<String, Object> map) {
		dao.updateAddStick(map);
		
		
	}

	@Override
	public void updateDeleteFlag(String[] ids, Integer value) {
		Map<String, Object> map = new HashMap<>();
		map.put("ids", ids);
		map.put("delFlag", value);
		dao.updateDeleteFlag(map);
	}

	@Override
	public PageBean listDeveloperHouseNewsPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listDeveloperHouseNewsPageCount", "listDeveloperHouseNewsPage");

	}

	@Override
	public HouseNewsVo getOneBy(HashMap<String, Object> map) {
		return dao.getOneBy(map);
	}

	@Override
	public void updateLineFlag(String[] ids, Integer value) {
		Map<String, Object> map = new HashMap<>();
		map.put("ids", ids);
		map.put("upDownLine", value);
		map.put("stick", 0);
		dao.updateLineFlag(map);
	}

	@Override
	public void updateDeleteByCreateUserIds(List<Integer> idlist, Integer value) {
		Map<String, Object> map = new HashMap<>();
		map.put("list", idlist);
		map.put("deleteFlag", value);
		dao.updateDeleteByCreateUserIds(map);
	}

	@Override
	public Long getHouseNewsCountBy(HashMap<String, Object> map1) {
		return dao.getHouseNewsCountBy(map1);
	}

	@Override
	public void updateNewHouseNews() {
		HashMap<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("content", "销售热线");
		List<HouseNewsPo> list = dao.listBy(hashMap);
		for (HouseNewsPo houseNewsPo : list) {
			String content = houseNewsPo.getContent();
			content = content.replace("咨询热线", "销售热线");
			String[] split = content.split("销售热线");
			String newContent = split[0];
			if(split.length>1){
				String[] split2 = split[1].split("，");
				for (int i = 0; i < split2.length; i++) {
					if(i!=0){
						newContent+=split2[i];
					}
				}
			}
			System.out.println(newContent);
			houseNewsPo.setContent(newContent);
			dao.updateDynamic(houseNewsPo);
		}
	}

	@Override
	public List<HouseNewsVo> getRelatedNews(HashMap<String, Object> map) {
		return dao.getRelatedNews(map);
	}

}
package com.dsj.modules.comment.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.vo.HouseNewsVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-11 13:36:52.
 * @版本: 1.0 .
 */
public interface HouseNewsService extends BaseService<HouseNewsPo>{
	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);
	
	PageBean listPCHouseNewsPage(PageParam pageParam, Map<String, Object> requestMap);

	void insertHouseNews(HouseNewsPo houseNews);
	
	void updateMany(String ids);
	void updateAuditMany(String ids,int status,String msg, Integer auditPerson);
	
	HouseNewsVo getVoById(long id);

	PageBean listHouseNewsPage(PageParam pageParam, Map<String, Object> requestMap);

	void updateRemoveStick(Map<String, Object> map);

	void updateAddStick(Map<String, Object> map);

	void updateDeleteFlag(String[] ids, Integer value);
	/**
	 * 开发商分页查询
	 * @param pageParam
	 * @param requestMap
	 * @return
	 */
	PageBean listDeveloperHouseNewsPage(PageParam pageParam, Map<String, Object> requestMap);

	PageBean listIndustryNewsPage(PageParam pageParam, Map<String, Object> requestMap);

	HouseNewsVo getOneBy(HashMap<String, Object> map);

	void updateLineFlag(String[] ids, Integer value);

	void updateDeleteByCreateUserIds(List<Integer> idlist, Integer value);

	Long getHouseNewsCountBy(HashMap<String, Object> map1);

	void updateNewHouseNews();

	List<HouseNewsVo> getRelatedNews(HashMap<String, Object> map);

}
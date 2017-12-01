package com.dsj.modules.system.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.vo.PropertyVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-21 17:37:37.
 * @版本: 1.0 .
 */
public interface PropertyService extends BaseService<PropertyPo>{

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	void savePropertyAdd(PropertyVo vo) throws Exception;

	Integer getTuiJianPropertyCount();

	PageBean listFwUserPage(PageParam pageParam, Map<String, Object> requestMap);
	
	List<PropertyPo> listNewBy(HashMap<String, Object> map);

	void updatePhoto(UserPo user);
	
	void updateOrUserDynamic(PropertyPo propertyPo, UserPo user);

	void updatePhoneUserById(PropertyPo property);

	PageBean listNewFrontPage(PageParam pageParam, Map<String, Object> requestMap);

	List<PropertyVo> getProByTuiJian();

	String getAboutArea(String string);

	PropertyVo getVoById(Long id);

	PropertyPo getTuiJianTimeLimitYi();

	List<Integer> getProIdByOrderStatus();

	long getPoCountByPhone(HashMap<String, Object> map);

	void updateResetPwdMany(String id);




	
}
package com.dsj.modules.system.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.vo.PropertyVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-21 17:37:37.
 * @版本: 1.0 .
 */
public interface PropertyDao extends BaseDao<PropertyPo> {

	Integer getTuiJianPropertyCount();//查找推荐位个数

	List<PropertyPo> listNewBy(HashMap<String, Object> map);

	void updatePhoto(UserPo user);

	void updatePhoneUserById(PropertyPo property);

	List<PropertyVo> getProByTuiJian();

	String getAboutArea(String spuId);

	PropertyVo getVoById(Long id);

	PropertyPo getTuiJianTimeLimitYi();

	List<Integer> getProIdByOrderStatus();

	long getPoCountByPhone(HashMap<String, Object> map);

	void resetPwdMany(Map<String, Object> map);

	
}
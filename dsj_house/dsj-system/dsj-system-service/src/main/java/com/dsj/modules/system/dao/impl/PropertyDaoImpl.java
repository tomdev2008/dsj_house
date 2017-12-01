package com.dsj.modules.system.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dsj.common.core.dao.BaseDaoImpl;
import com.dsj.modules.system.dao.PropertyDao;
import com.dsj.modules.system.po.PropertyPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.vo.PropertyVo;

/**
 *
 * @描述: DAO数据访问层接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-21 17:37:37.
 * @版本: 1.0 .
 */
@Repository
public class PropertyDaoImpl extends BaseDaoImpl<PropertyPo> implements PropertyDao{

	@Override
	public Integer getTuiJianPropertyCount() {
		return sessionTemplate.selectOne("getTuiJianPropertyCount");
	}

	@Override
	public List<PropertyPo> listNewBy(HashMap<String, Object> map) {
		return sessionTemplate.selectList(getStatement("listNewBy"), map);
	}

	@Override
	public void updatePhoto(UserPo user) {
		sessionTemplate.update("updatePhoto", user);
	}

	@Override
	public void updatePhoneUserById(PropertyPo property) {
		sessionTemplate.update("updatePhoneUserById",property);
	}

	@Override
	public List<PropertyVo> getProByTuiJian() {
		return sessionTemplate.selectList("getProByTuiJian");
	}

	@Override
	public String getAboutArea(String spuId) {
		return sessionTemplate.selectOne(getStatement("getAboutArea"),spuId);
	}

	@Override
	public PropertyVo getVoById(Long id) {
		return sessionTemplate.selectOne(getStatement("getVoById"), id);
	}

	@Override
	public PropertyPo getTuiJianTimeLimitYi() {
		return sessionTemplate.selectOne("getTuiJianTimeLimitYi");
	}

	@Override
	public List<Integer> getProIdByOrderStatus() {
		return sessionTemplate.selectList("getProIdByOrderStatus");
	}

	@Override
	public void resetPwdMany(Map<String, Object> map) {
		sessionTemplate.update(getStatement("updateResetPwdMany"),map);	
	}

	@Override
	public long getPoCountByPhone(HashMap<String, Object> map) {
		return sessionTemplate.selectOne("getPoCountByPhone", map);
	}


}
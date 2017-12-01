package com.dsj.modules.system.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.system.po.EvelopersPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.vo.EvelopersVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
public interface EvelopersService extends BaseService<EvelopersPo>{

	void saveEvelopersAdd(EvelopersVo vo) throws Exception;

	void saveEvelopersUpdate(EvelopersVo vo) throws Exception;

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	EvelopersVo getVoById(Long id);

	List<EvelopersVo> listByIds(String ids);

	PageBean listEvelopersPage(PageParam pageParam, Map<String, Object> requestMap);

	EvelopersVo getVoBy(Map<String, Object> evelopersMap);

	void updateOrUserDynamic(EvelopersPo evelopers, UserPo user);

	void updateEveloper(Map<String, Object> paramMap);

	EvelopersPo getEveloper(Long id);



	
}
package com.dsj.common.core.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.common.entity.BaseEntity;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;

public abstract  class BaseServiceImpl<D extends BaseDao<T>,T extends BaseEntity> implements BaseService<T>{
	
	protected  final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 持久层对象
	 */
	@Autowired
	protected D dao;

	/**
	 * 根据实体对象新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	public long save(T entity){
		return dao.insert(entity);
	}
	
	/**
	 * 动态新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	public long saveDynamic(T entity){
		return dao.insertDynamic(entity);
	}

	/**
	 * 批量保存对象.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	public long save(List<T> list){
		return dao.insert(list);
	}

	/**
	 * 更新实体对应的记录.
	 * 
	 * @param entity
	 *            .
	 * @return
	 */
	public int update(T entity){
		return dao.update(entity);
	}
	
	
	/**
	 * 动态更新实体对应的记录.
	 * 
	 * @param entity
	 *            .
	 * @return
	 */
	public int updateDynamic(T entity){
		return dao.updateDynamic(entity);
	}

	/**
	 * 批量更新对象.
	 * 
	 * @param entity
	 *            .
	 * @return int .
	 */
	public int update(List<T> list){
		return dao.update(list);
	}

	/**
	 * 根据ID查找记录.
	 * 
	 * @param id
	 *            .
	 * @return entity .
	 */
	public T getById(long id){
		return dao.getById(id);
	}

	/**
	 * 根据ID删除记录.
	 * 
	 * @param id
	 *            .
	 * @return
	 */
	public int deleteById(long id){
		return dao.deleteById(id);
	}

	/**
	 * 分页查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap){
		return dao.listPage(pageParam, paramMap);
	}

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	public List<T> listBy(Map<String, Object> paramMap){
		return dao.listBy(paramMap);
	}

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	public T getBy(Map<String, Object> paramMap){
		return dao.getBy(paramMap);
	}
}

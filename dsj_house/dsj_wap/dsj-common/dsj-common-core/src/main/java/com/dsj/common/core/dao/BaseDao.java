package com.dsj.common.core.dao;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;


/**
 * 
 * @描述: 数据访问层基础支撑接口.
 * @作者: WuShuicheng .
 * @创建时间: 2013-7-22,下午4:52:52 .
 * @版本: 1.0 .
 * @param <T>
 */
public interface BaseDao<T> {
	
	/**
	 * 根据实体对象新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	long insert(T entity);
	
	/**
	 * 动态新增记录.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	long insertDynamic(T entity);

	/**
	 * 批量保存对象.
	 * 
	 * @param entity
	 *            .
	 * @return id .
	 */
	long insert(List<T> list);

	/**
	 * 更新实体对应的记录.
	 * 
	 * @param entity
	 *            .
	 * @return
	 */
	int update(T entity);
	
	
	/**
	 * 动态更新实体对应的记录.
	 * 
	 * @param entity
	 *            .
	 * @return
	 */
	int updateDynamic(T entity);

	/**
	 * 批量更新对象.
	 * 
	 * @param entity
	 *            .
	 * @return int .
	 */
	int update(List<T> list);

	/**
	 * 根据ID查找记录.
	 * 
	 * @param id
	 *            .
	 * @return entity .
	 */
	T getById(long id);

	/**
	 * 根据ID删除记录.
	 * 
	 * @param id
	 *            .
	 * @return
	 */
	int deleteById(long id);

	/**
	 * 分页查询 .
	 * 
	 * @param pageParam
	 *            分页参数.
	 * @param paramMap
	 *            业务条件查询参数.
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap);
	
	/**
	 * 传sql的分页查询
	 * @param pageParam
	 * @param paramMap
	 * @param countSql
	 * @param sql
	 * @return
	 */
	public PageBean listPage(PageParam pageParam, Map<String, Object> paramMap,String countSql,String sql);

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回集合
	 */
	public List<T> listBy(Map<String, Object> paramMap);

	/**
	 * 根据条件查询 listBy: <br/>
	 * 
	 * @param paramMap
	 * @return 返回实体
	 */
	public T getBy(Map<String, Object> paramMap);

}

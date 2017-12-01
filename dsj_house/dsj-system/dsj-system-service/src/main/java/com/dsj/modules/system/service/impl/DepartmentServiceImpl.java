package com.dsj.modules.system.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.system.dao.DepartmentDao;
import com.dsj.modules.system.po.DepartmentPo;
import com.dsj.modules.system.service.DepartmentService;
import com.dsj.modules.system.vo.DepartmentTreeVo;
import com.dsj.modules.system.vo.EasyuiSelectTreeVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentDao, DepartmentPo> implements DepartmentService {
	private static String TREE_OPEN="open";
	@Override
	public List<EasyuiSelectTreeVo> getEasyuiSelectTrees(Long id) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<DepartmentPo> lists = listBy(paramMap);
		Map<Long,List<EasyuiSelectTreeVo>> listMap=new HashMap<Long,List<EasyuiSelectTreeVo>>();
		
		List<EasyuiSelectTreeVo> firstlists=new ArrayList<EasyuiSelectTreeVo>();
		EasyuiSelectTreeVo treevo = new EasyuiSelectTreeVo();
		treevo.setText("无");
		treevo.setState(TREE_OPEN);
		treevo.setId(0l);
		firstlists.add(treevo);
		for(DepartmentPo po:lists){
			EasyuiSelectTreeVo vo=voToPo(po,id);
			if(po.getParentId()==null||po.getParentId()==0){
				firstlists.add(vo);
			}else{
				List<EasyuiSelectTreeVo> vos=new ArrayList<EasyuiSelectTreeVo>();
				if(listMap.get(po.getParentId())==null){
					vos.add(vo);
					
				}else{
					vos=listMap.get(po.getParentId());
					vos.add(vo);
				}
				listMap.put(po.getParentId(), vos);
			}
		}
	
		return getEasyuiSelectTrees(firstlists,listMap);
	}
	
	/**
	 * 处理子节点
	 * @param firstlists
	 * @param listMap
	 * @return
	 */
	private List<EasyuiSelectTreeVo> getEasyuiSelectTrees(List<EasyuiSelectTreeVo> firstlists,Map<Long,List<EasyuiSelectTreeVo>> listMap) {
		for(EasyuiSelectTreeVo vo:firstlists){
			vo.setChildren(listMap.get(vo.getId()));
		}
		return firstlists;
	}
	
	/**
	 * vo转po
	 * @param po
	 * @param id
	 * @return
	 */
	private EasyuiSelectTreeVo voToPo(DepartmentPo po,Long id){
		EasyuiSelectTreeVo vo = new EasyuiSelectTreeVo();
		//if(id!=null&&po.getId()!=id){
		vo.setText(po.getDeptName());
		vo.setState(TREE_OPEN);
		vo.setId(po.getId());
			if(id==po.getParentId()){
			vo.setChecked(true);
			}
		
		return vo;
	}

	@Override
	public PageBean listPageTree(PageParam pageParam, Map<String, Object> paramMap) throws IllegalAccessException, InvocationTargetException {
		paramMap.put("parentId", 0);
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		PageBean page = listPage(pageParam, paramMap);
		paramMap.remove("parentId");
		List<DepartmentPo> listpos=listBy(paramMap);
		List<DepartmentTreeVo> trees = new ArrayList<DepartmentTreeVo>();
		for (Object o : page.getRecordList()) {// list
			DepartmentPo po = (DepartmentPo) o;
			DepartmentTreeVo tree = new DepartmentTreeVo();
			BeanUtils.copyProperties(tree, po);
			trees.add(tree);
		}
		
		page.setRecordList(getDatatableTrees(trees,listpos));
		
		return page;
	}

	private Map<Long,List<DepartmentTreeVo>>  getMapTreesList(List<DepartmentPo> listpos) throws IllegalAccessException, InvocationTargetException{
		Map<Long,List<DepartmentTreeVo>> mapList=new HashMap<Long,List<DepartmentTreeVo>>();
		for(DepartmentPo po: listpos){
			if(po.getParentId()!=null&&po.getParentId()!=0){
				DepartmentTreeVo tree = new DepartmentTreeVo();
				BeanUtils.copyProperties(tree, po);
				List<DepartmentTreeVo> treeVoList;
				if(mapList.get(po.getParentId())==null){
					treeVoList=new ArrayList<DepartmentTreeVo>();
				}else{
					treeVoList=mapList.get(po.getParentId());
				}
				treeVoList.add(tree);
				mapList.put(po.getParentId(), treeVoList);
			}
		}
		return mapList;
	}
	
	private List<DepartmentTreeVo>  getDatatableTrees(List<DepartmentTreeVo> trees,List<DepartmentPo> listpos) throws IllegalAccessException, InvocationTargetException{
		Map<Long,List<DepartmentTreeVo>> mapList=getMapTreesList(listpos);
		List<DepartmentTreeVo> returntrees = new ArrayList<DepartmentTreeVo>();
	
		for(DepartmentTreeVo tree:trees){
			if(mapList.get(tree.getId())!=null){
				tree.setChildren(getDatatableTrees(mapList.get(tree.getId()),listpos));
			}
			returntrees.add(tree);
		}
	
		return returntrees;
	}
	
	

}
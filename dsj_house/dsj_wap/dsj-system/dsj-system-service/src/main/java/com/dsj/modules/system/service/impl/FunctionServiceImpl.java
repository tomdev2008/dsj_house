package com.dsj.modules.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.system.dao.FunctionDao;
import com.dsj.modules.system.po.FunctionPo;
import com.dsj.modules.system.po.RoleFunctionPo;
import com.dsj.modules.system.po.RolePo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.FunctionService;
import com.dsj.modules.system.service.RoleFunctionService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Service
public class FunctionServiceImpl  extends BaseServiceImpl<FunctionDao,FunctionPo> implements FunctionService {
    @Autowired
    private RoleFunctionService roleFunctionService;
	@Override
	public List<String> getPatternsList(UserPo user) {
		return dao.getPatternsList(user);
	}

	@Override
	public List<FunctionPo> listMenus(List<RolePo> rolelist) {
		return dao.getListMenus(rolelist);
	}

	@Override
	public List<FunctionPo> findAllFunction(Long roleId) {
		//查询一级菜单
		 List<FunctionPo> root = dao.findAllFunction(0);
		//查询二级菜单
		 List<FunctionPo> seconds = dao.findAllFunctionTwo();
		//查询按钮
		 List<FunctionPo> buttons = dao.findAllFunctionButtons();
		 //角色权限集合
		 List<RoleFunctionPo> roleFun = roleFunctionService.findById(roleId);
		 List<Integer> functionIds=new ArrayList<Integer>();
		 //将权限ID放入集合
		 for(int i=0;i<roleFun.size();i++){
			 functionIds.add(roleFun.get(i).getFuncId());
		 }
		 //按钮集合
		 Map<String,List<FunctionPo>> btnmap = btnmapFunction(functionIds,buttons);
		 //二级菜单集合
		 Map<String,List<FunctionPo>> menumap = menumapFunction(seconds,functionIds,btnmap);
		 
		 for (FunctionPo function : root) {
				List<FunctionPo> children = menumap.get("pid"+function.getId());
				if (children == null) {
					children = new ArrayList<FunctionPo>();
				}
				function.setChildren(children);
		}
		return root;
	}
    /**
     * 将按钮集合放入二级菜单
     * @param seconds
     * @param functionIds
     * @param btnmap
     * @return
     */
	private Map<String, List<FunctionPo>> menumapFunction(List<FunctionPo> seconds, List<Integer> functionIds, Map<String, List<FunctionPo>> btnmap) {
		Map<String,List<FunctionPo>> menumap=new HashMap<String,List<FunctionPo>>();
		 for (FunctionPo function : seconds) {
				String funId=function.getId().toString();
				if (functionIds.contains(Integer.parseInt(funId))){
					function.setChecked(true);
				}
				List<FunctionPo> btns = btnmap.get("pid"+funId);
				if (btns == null) {
					btns = new ArrayList<FunctionPo>();
				}
				function.setChildren(btns);
				
				Integer pid = function.getPid();
				if (menumap.containsKey("pid"+pid)) {
					menumap.get("pid"+pid).add(function);
				} else {
					List<FunctionPo> tmplist = new ArrayList<FunctionPo>();
					tmplist.add(function);
					menumap.put("pid"+pid, tmplist);
				}
			}
		return menumap;
	}
    /**
     * 将按钮放入集合
     * @param functionIds
     * @param buttons
     * @return
     */
	private Map<String, List<FunctionPo>> btnmapFunction(List<Integer> functionIds, List<FunctionPo> buttons) {
		 Map<String,List<FunctionPo>> btnmap=new HashMap<String,List<FunctionPo>>();
		 for (FunctionPo function : buttons) {
				String funId=function.getId().toString();
				if (functionIds.contains(Integer.parseInt(funId))){
					function.setChecked(true);
				}
				Integer pid = function.getPid();
				if (btnmap.containsKey("pid"+pid)) {
					btnmap.get("pid"+pid).add(function);
				} else {
					List<FunctionPo> tmplist = new ArrayList<FunctionPo>();
					tmplist.add(function);
					btnmap.put("pid"+pid, tmplist);
				}
			}
		return btnmap;
	}

	@Override
	public List<String> getPatternsAllList() {
		return dao.getPatternsList(null);
	}
}
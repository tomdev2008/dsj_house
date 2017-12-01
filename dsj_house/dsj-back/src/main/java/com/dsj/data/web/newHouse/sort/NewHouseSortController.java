package com.dsj.data.web.newHouse.sort;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.constants.CommConst;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.newHouse.guardian.GuardianController;
import com.dsj.data.web.utils.ShiroUtils;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseDirectorySortPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHouseDirectorySortService;
import com.dsj.modules.system.po.AgentPo;

@Controller
@RequestMapping(value = "back/**/newHouse/sort")
public class NewHouseSortController {
	private final Logger LOGGER = LoggerFactory.getLogger(GuardianController.class);
    @Autowired
	private NewHouseDirectorySortService newHouseDirectorySortService;
	@Autowired
    private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	/**
	 * 进入排序页面
	 * 
	 * @return
	 */
	@RequestMapping("newHouse_sort")
	public String newHouse_sort(Model model) {
		List<NewHouseDirectorySortPo> sortList=newHouseDirectorySortService.selectList();
		List<NewHouseDirectorySortPo> sortListTemp = new ArrayList<NewHouseDirectorySortPo>();
		if(sortList.size()!=20){
			for(int i=1;i<=20;i++){
				int flag = 0;
				for(NewHouseDirectorySortPo directory : sortList){
					if(i == directory.getSortVal()){
						flag = 1;
						sortListTemp.add(directory);
					}
				}
				if(flag == 0){	
					NewHouseDirectorySortPo a = new NewHouseDirectorySortPo();
					a.setSortVal(i);
					sortListTemp.add(a);
				}
			}
			model.addAttribute("sortList", sortListTemp);
		}else{
			model.addAttribute("sortList", sortList);
		}	
		
		return "newHouse/sort/newHouse_sort";
	}
	
	/**
	 * 楼盘排序拖动
	 */
	@RequestMapping("changeMore")
	@ResponseBody
	public AjaxResultVo changeMore(@RequestBody List<NewHouseDirectorySortPo> list){
		AjaxResultVo result = new AjaxResultVo();
		try {
			for(NewHouseDirectorySortPo newSort :list){
				if(!(newSort.getLoupanId()==null)){
					newHouseDirectorySortService.updateDynamic(newSort);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("newHouseIds", newSort.getLoupanId());
					newHouseDirectoryAuthService.saveNewHouseToSolr(map);
				}
			}
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		

		return result;
	}
	
	@RequestMapping("toSetUp")
	public String toSetUp(Long id,Model model){
		model.addAttribute("id",id);
		return "newHouse/sort/toSetUp";
	}
	
	/**
	 * 查询上架楼盘
	 * @param repsonse
	 * @param model
	 * @param request
	 * @param aoData
	 * @return
	 */
	@RequestMapping("findLoupanList")
	@ResponseBody
	public PageDateTable<?> findLoupanList(ServletResponse repsonse, Model model, ServletRequest request,
			@RequestParam(value = "aoData", defaultValue = "") String aoData) {

		Map<String, Object> requestMap = new HashMap<String, Object>();
		requestMap = JsonTools.parsePageMap(aoData);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("isTure", NewHouseIsTrueEnum.UP.getValue());
		requestMap.put("edit", 1);		
		Integer pageNumber = Integer.parseInt((String) requestMap.get("iDisplayStart"));
		Integer pageSize = Integer.parseInt((String) requestMap.get("iDisplayLength"));
		PageParam pageParam = new PageParam(pageNumber / pageSize + 1, pageSize);

		PageBean page = null;
		try {
			page = newHouseDirectoryAuthService.listNewHousePage(pageParam, requestMap);

		} catch (Exception e) {
			LOGGER.info("已上架楼盘查询错误", e.getMessage(), e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}

	/**
	 * 设置绑定
	 */
	@RequestMapping("checkOkHouse")
	@ResponseBody
	public AjaxResultVo checkOkHouse(Long id, Long sortId) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			NewHouseDirectorySortPo directory = newHouseDirectorySortService.getSortDirectory(sortId);
            NewHouseDirectoryAuthPo directoryAuth=newHouseDirectoryAuthService.getWyType(id);
            NewHouseDirectorySortPo directorys = newHouseDirectorySortService.getSortOne(directoryAuth.getId());
			long createPerson=ShiroUtils.getSessionUser().getId();
            if(directory==null){
            	if(directorys!=null){
            		ajax.setMessage("该楼盘已经存在");
            	}else{
            		NewHouseDirectorySortPo directoryPo=new NewHouseDirectorySortPo();
                	directoryPo.setCreatePerson(createPerson);
                    directoryPo.setCreateTime(new Date());
                    directoryPo.setLoupanId(directoryAuth.getId());
                    directoryPo.setSortVal(sortId.intValue());
                    newHouseDirectorySortService.addNewHouseSortDirectory(directoryPo);
                    HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("newHouseIds", directoryAuth.getId());
					newHouseDirectoryAuthService.saveNewHouseToSolr(map);
                    ajax.setStatusCode(StatusCode.SUCCESS);
            	}
            }else{
                 if(directorys==null){
                	 int result=newHouseDirectorySortService.updateNewHouseSortDirectory(directoryAuth,sortId,createPerson);
                     HashMap<String, Object> map = new HashMap<String, Object>();
 					 map.put("newHouseIds", directoryAuth.getId());
 					 newHouseDirectoryAuthService.saveNewHouseToSolr(map);
                     if(result>0){
                    	 ajax.setStatusCode(StatusCode.SUCCESS); 
                     }else{
                    	 ajax.setStatusCode(StatusCode.SERVER_ERROR);
                     }
                 }else{
                	 ajax.setMessage("该楼盘已经存在");
                 }
			}
		} catch (Exception e) {
			LOGGER.info(CommConst.QUERY_DATA_ERROR, e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

	/**
	 * 取消设置
	 */
	@RequestMapping("delSort")
	@ResponseBody
	public AjaxResultVo delSort(Long sort,Long loupanId){
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			NewHouseDirectorySortPo directory = newHouseDirectorySortService.getSortDirectory(sort);
			if(directory==null){
				ajax.setStatusCode(StatusCode.SUCCESS);
			}else{
				int result=newHouseDirectorySortService.updateSortDirectory(sort);
				HashMap<String, Object> map = new HashMap<String, Object>();
				 map.put("newHouseIds", loupanId);
				 newHouseDirectoryAuthService.saveNewHouseToSolr(map);
				 if(result>0){
                	 ajax.setStatusCode(StatusCode.SUCCESS); 
                 }else{
                	 ajax.setStatusCode(StatusCode.SERVER_ERROR);
                 }
			}
		} catch (Exception e) {
			LOGGER.info(CommConst.QUERY_DATA_ERROR, e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return ajax;
	}

}

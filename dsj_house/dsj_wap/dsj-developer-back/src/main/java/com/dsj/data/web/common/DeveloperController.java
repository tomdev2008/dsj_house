package com.dsj.data.web.common;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.HouseDirectoryService;

/**
 * 地区
 */
@Controller
@RequestMapping(value = "back/common")
public class DeveloperController {
	private final Logger LOGGER = LoggerFactory.getLogger(DeveloperController.class);

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	@Autowired
	private HouseDirectoryService houseDirectoryService;
	/**
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("areaList")
	@ResponseBody
    public AjaxResultVo areaList(Integer parentId,HttpServletRequest request,
            HttpServletResponse response) { 
		AjaxResultVo result = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parentId", parentId);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		try {
			List<AreaPo>  areaList = areaService.listBy(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(areaList);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	
	/**
	 * 根据输入,模糊查询新房楼盘名称
	 * @param name
	 * @return
	 */
	@RequestMapping("getNewHouseName")
	@ResponseBody
    public AjaxResultVo getNewHouseName(String name) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("isTure", NewHouseIsTrueEnum.UP.getValue());
			List<NewHouseDirectoryAuthPo> nameList = newHouseDirectoryAuthService.listBy(map);
			
			NewHouseDirectoryAuthPo other=new NewHouseDirectoryAuthPo();
			other.setId(0l);
			other.setName("其他");
			nameList.add(other);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setData(nameList);
		} catch (Exception e) {
			LOGGER.error("楼盘名称查询异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}
	


}

package com.dsj.data.wap.area;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.TradeAreaService;

@Controller
@RequestMapping(value = "area")
public class AreaController {

	private final Logger LOGGER = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private AreaService areaService;
	
	@Autowired
	private TradeAreaService tradeAreaService;
	
	/**
	 * 根据父级地区编码查询地区
	 * 
	 * @param areaCode 父级地区编码
	 * @param isCustom 是否查询标准区域
	 * @return
	 */
	@RequestMapping("area_list")
	@ResponseBody
	public AjaxResultVo findAreas(Integer areaCode , Integer isCustom) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<AreaPo> list = null;
		try {
			map.put("parentId", areaCode);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			if(AreaEnum.NOT_CUSTOM.getValue().equals(isCustom)){
				map.put("isCustom", isCustom);
			}
			list = areaService.listBy(map);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 根据父级地区编码查询商圈
	 * 
	 * @param areaCode 父级地区编码
	 * @return
	 */
	@RequestMapping("business")
	@ResponseBody
	public AjaxResultVo business(Integer areaCode) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<TradeAreaPo> list = null;
		
		try {
			map.put("parentId", areaCode);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			list = tradeAreaService.listBy(map);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("地区查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}

}


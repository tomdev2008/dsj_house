package com.dsj.data.wap.common;

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
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.GroupTypeService;
import com.dsj.modules.other.service.SubwayService;
import com.dsj.modules.other.service.TradeAreaService;

@Controller
@RequestMapping(value = "subway")
public class SubwayController {

	private final Logger LOGGER = LoggerFactory.getLogger(SubwayController.class);

	GroupTypeService groupTypeService;
	
	@Autowired
	SubwayService subwayService;
	
	
	/**
	 * 根据父级地区编码查询地区
	 * 
	 * @param areaCode 父级地区编码
	 * @param isCustom 是否查询标准区域
	 * @return
	 */
	@RequestMapping("subway_list")
	@ResponseBody
	public AjaxResultVo findAreas(String subwayLineId) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<AreaPo> list = null;
		try {
			List<SubwayPo> subwayList=subwayService.getByPid(subwayLineId);
			ajax.setData(subwayList);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("地铁查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	
}


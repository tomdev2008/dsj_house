package com.dsj.data.web.other.subway;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.po.SubwayPo;
import com.dsj.modules.other.service.SubwayService;

/**
 * 地铁管理
 */
@Controller
@RequestMapping(value = "back/**/subway")
public class SubwayController {
	private final Logger logger = LoggerFactory.getLogger(SubwayController.class);

	@Autowired
	private SubwayService subwayService;
	
	/**
	 * 根据线路id查找站点集合
	 * 
	 * @param subwayId 地铁线路id
	 * @return
	 */
	@RequestMapping("findStations")
	@ResponseBody
	public AjaxResultVo findStations(Integer subwayId) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<SubwayPo> list = null;
		try {
			map.put("pid", subwayId);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			list = subwayService.listBy(map);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("地铁站查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
}

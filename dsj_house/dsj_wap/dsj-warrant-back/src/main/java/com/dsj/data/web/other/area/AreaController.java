package com.dsj.data.web.other.area;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.redis.JedisProxy;
import com.dsj.common.utils.redis.constant.JedisConstant;
import com.dsj.common.utils.redis.serialize.SerializingUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.TradeAreaService;

import net.sf.ehcache.pool.impl.FromLargestCacheOnDiskPoolEvictor;

/**
 * 地区信息查询
 * 
 * @author wangjl
 * @since 2017-06-16
 * @version V1.0 
 */
@Controller
@RequestMapping(value = "back/**/area")
public class AreaController {

	private final Logger logger = LoggerFactory.getLogger(AreaController.class);

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
			logger.error("地区查询异常", e);
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
			logger.error("地区查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	/**
	 * 重新生成Area数据并保存在redis
	 * @param areaCode
	 * @param isCustom
	 * @return
	 */
	@RequestMapping("save_redis")
	@ResponseBody
	public AjaxResultVo saveRedis(String areaCode , String isCustom) {
		AjaxResultVo ajaxResult = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		String tempData = "dsj_area";
		if(AreaEnum.NOT_CUSTOM.getValue().equals(isCustom)){
			map.put("isCustom", isCustom);//获取正规数据
			tempData = "dsj_area_regular";
		}
		//先从缓存查；查不到再查库，并做缓存
		List<AreaPo> list = null;
		if (JedisProxy.exists(tempData)) {
			Object obj= JedisProxy.get(tempData);
			if (obj!=null) {
				try {
					Map<String, List<AreaPo>> areaMap = (Map<String, List<AreaPo>>) obj;
					list = areaMap.get(areaCode);
				} catch (Exception e) {
					logger.error("地区查询异常" , e);
				}
			}
			if(list != null){
				ajaxResult.setStatus(200);
				ajaxResult.setData(list);
				return ajaxResult;
			}
		}else{
			List<AreaPo> parentList = areaService.listParent(map);
			List<AreaPo> alllist = areaService.listBy(map);
			Map<String, List<AreaPo>> areaMap = new HashMap<>();
			for(AreaPo parentPo : parentList){
				List<AreaPo> tempList = new ArrayList<>();
				for(AreaPo po : alllist ){
					if (parentPo.getParentId().toString().equals( po.getParentId().toString()) ) {
						tempList.add(po);
					}
				}
				areaMap.put(parentPo.getParentId().toString(), tempList);
			}
			JedisProxy.setByte(SerializingUtil.serialize(tempData), areaMap);
		}
		Object obj= JedisProxy.get(tempData);
		Map<String, List<AreaPo>> areaMap = (Map<String, List<AreaPo>>) obj;
		list = areaMap.get(areaCode);
		ajaxResult.setData(list);
		ajaxResult.setStatusCode(StatusCode.SUCCESS);
		return ajaxResult;
	}
	
}


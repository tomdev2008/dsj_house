package com.dsj.data.web.other.shop;

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
import com.dsj.modules.fw.po.FwTypeNodePo;
import com.dsj.modules.fw.service.FwSkuService;
import com.dsj.modules.fw.service.FwTypeNodeService;
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
@RequestMapping(value = "back/**/shop")
public class ShopController {

	private final Logger logger = LoggerFactory.getLogger(ShopController.class);

	@Autowired
	private FwTypeNodeService fwTypeNodeService;
	
	/**
	 * 根据父级地区编码查询地区
	 * 
	 * @param areaCode 父级地区编码
	 * @param isCustom 是否查询标准区域
	 * @return
	 */
	@RequestMapping("node_list")
	@ResponseBody
	public AjaxResultVo nodeList(Integer typeId) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		List<FwTypeNodePo> list = null;
		try {
			map.put("typeId", typeId);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			list = fwTypeNodeService.listBy(map);
			ajax.setData(list);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("服务节点查询异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
}


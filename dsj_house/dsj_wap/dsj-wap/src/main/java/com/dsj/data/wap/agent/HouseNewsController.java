package com.dsj.data.wap.agent;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageDateTable;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.JsonTools;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.AgentNewsPo;
import com.dsj.modules.comment.po.ClickHouseNewsPo;
import com.dsj.modules.comment.po.CommentPo;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.service.ClickHouseNewsService;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.comment.vo.HouseNewsVo;


@Controller
@RequestMapping(value = "houseNews")
public class HouseNewsController {
	private final Logger LOGGER = LoggerFactory.getLogger(HouseNewsController.class);
	
	@Autowired
	private HouseNewsService houseNewsservice; 
	
	@Autowired
	private ClickHouseNewsService clickHouseNewsService;
	
	//楼盘动态查询
	@RequestMapping("getNews")
	@ResponseBody
	public PageDateTable<?> getNews(Integer pageFirst ,Integer pageSize, Long houseId,Long userId) {
		Map<String, Object> requestMap = new HashMap<String, Object>();
		PageParam pageParam = new PageParam( pageFirst , pageSize);
		requestMap.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		requestMap.put("upDownLine", HouseNewsStatusEnum.UP.getValue());
		if (houseId !=null ) requestMap.put("houseId", houseId);
		if (userId !=null ) requestMap.put("createUser", userId);
		PageBean page = null;
		try {
			page = houseNewsservice.listHouseNewsPage(pageParam, requestMap);
		} catch (Exception e) {
			LOGGER.error("动态查询异常", e);
		}
		PageDateTable<?> pageImpl = new PageDateTable(page);
		return pageImpl;
	}
	
	@RequestMapping("detail")
	@ResponseBody
	public AjaxResultVo detail(Long id) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			HouseNewsVo vo = houseNewsservice.getVoById(id);
			result.setData(vo);
			result.setStatusCode(StatusCode.SUCCESS);
			try {
				HashMap<String, Object> map = new HashMap<>();
				map.put("id", id);
				HouseNewsVo houseNews = houseNewsservice.getOneBy(map);
				if (houseNews!=null) {
					//动态详情被点击统计
					Integer pccount = houseNews.getPccount();
					if (pccount == null) {
						pccount = 0 ;
					}
					houseNews.setPccount(pccount+1);
					houseNewsservice.updateDynamic(houseNews);
				}
			} catch (Exception e) {
				
			}
		} catch (Exception e) {
			LOGGER.error("查询动态异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	// 更新点击记录获取
	@RequestMapping("house_dynamic_update")
	@ResponseBody
	public AjaxResultVo house_dynamic_update(HttpServletRequest request,String newsStr) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (UserUtil.getCurrentUser(request)!=null && !newsStr.equals("null")) {
				String[] ids = newsStr.split(",");
				Map<String, Object> map = null;
				for (int i = 0; i < ids.length; i++) {
					if (StringUtils.isNotEmpty(ids[i])) {
						map = new HashMap<>();
						map.put("createUser", UserUtil.getCurrentUser(request).getId());
						map.put("objType", 1);
						map.put("objId", ids[i]);
						List<ClickHouseNewsPo> newsList = clickHouseNewsService.listBy(map);
						if (newsList != null && newsList.size() == 0) {
							ClickHouseNewsPo newsPo = new ClickHouseNewsPo();
							newsPo.setCreateUser(UserUtil.getCurrentUser(request).getId().intValue());
							newsPo.setObjType(1);
							newsPo.setObjId(Long.parseLong(ids[i]));
							newsPo.setCreateTime(new Date());
							clickHouseNewsService.saveDynamic(newsPo);
						}
					}
				}
				
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
			if (UserUtil.getCurrentUser(request)!=null) {
				Map<String, Object> map2 = new HashMap<>();
				map2.put("createUser", UserUtil.getCurrentUser(request).getId());
				map2.put("objType", 1);
				List<ClickHouseNewsPo> list = clickHouseNewsService.listBy(map2);
				ajax.setStatusCode(StatusCode.SUCCESS);
				ajax.setData(list);
			}
		} catch (Exception e) {
			LOGGER.error("动态点击记录获取失败", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
}

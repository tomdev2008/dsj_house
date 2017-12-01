package com.dsj.data.wap.index;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.modules.comment.po.FwContentPo;
import com.dsj.modules.comment.service.FeedbackService;
import com.dsj.modules.comment.service.FwContentService;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;
import com.dsj.modules.evaluate.service.AgentEvaluateService;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.pagelayout.enums.WapPublishStatusEnums;
import com.dsj.modules.pagelayout.po.WapItemSetPo;
import com.dsj.modules.pagelayout.po.WapLabelPo;
import com.dsj.modules.pagelayout.po.WapSwiperPo;
import com.dsj.modules.pagelayout.service.WapItemSetService;
import com.dsj.modules.pagelayout.service.WapLabelService;
import com.dsj.modules.pagelayout.service.WapSwiperService;
import com.dsj.modules.pagelayout.vo.WapIndexPageListVo;
import com.dsj.modules.pagelayout.vo.WeightAndTypeVo;

@Controller
@RequestMapping(value = "wapIndex")
public class IndexController {
	private final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private WapSwiperService wapSwiperService;
	@Autowired
	private WapLabelService wapLabelService;
	@Autowired
	private WapItemSetService wapItemSetService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AgentEvaluateService agentEvaluateService;
	@Autowired
	private FwContentService fwContentService;
	/**
	 * 获取首页轮播
	 * @return
	 */
	@RequestMapping("getSwiper")
	@ResponseBody
	public AjaxResultVo getSwiper(){
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", WapPublishStatusEnums.PUBLISH.getValue());
			List<WapSwiperPo> list = wapSwiperService.listBy(map);
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("轮播获取失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	
	/**
	 * 获取首页页签
	 * @return
	 */
	@RequestMapping("getLabel")
	@ResponseBody
	public AjaxResultVo getLabel(){
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", WapPublishStatusEnums.PUBLISH.getValue());
			List<WapLabelPo> list = wapLabelService.listBy(map);
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("首页页签获取失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	
	/**
	 * 获取所有页签的前两项
	 * @return
	 */
	@RequestMapping("getItems")
	@ResponseBody
	public AjaxResultVo getItems(Integer labelId){
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("status", WapPublishStatusEnums.PUBLISH.getValue());
			map.put("labelId", labelId);
			List<WapItemSetPo> list = wapItemSetService.listBy(map);
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("首页页签获前两项取失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	/**
	 * 首页列表
	 * @param labelId
	 * @return
	 */
	@RequestMapping("getList")
	@ResponseBody
	public AjaxResultVo getList(long labelId,int page){
		AjaxResultVo result = new AjaxResultVo();
		try {
			WapLabelPo po = wapLabelService.getById(labelId);
			List<WeightAndTypeVo> paramList = new ArrayList<WeightAndTypeVo>();
			paramList = wapLabelService.getWeightAndTypeVo(po.getIncludeType(),po.getWeight(),page);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("page", page);
			map.put("list", paramList);
			map.put("listMaxIndex",paramList.size()-1);
			List<WapIndexPageListVo> list = wapLabelService.getWapIndexPageList(map);
			for(WapIndexPageListVo i:list){
				if(i.getType()==1||i.getType()==2){
					NewHouseFrontVo vo = newHouseDirectoryAuthService.getNewHousePrice(i.getObjectId());
					if(vo.getIndexPrice()!=null){
						i.setPrice(vo.getPriceName()+vo.getIndexPrice()+vo.getPricedw());
					}
					
				}
				if(i.getType()==13){
					if(i.getContent4()!=null){
						Map<String,Object> map1 = new HashMap<String,Object>();
						map1.put("agentId", i.getContent5());//经纪人编号
						map1.put("paragraphNo",79402);
						AgentEvaluatePo p = agentEvaluateService.getBy(map1);
						if(p!=null){
							NumberFormat numberFormat = NumberFormat.getPercentInstance();
							numberFormat.setMaximumFractionDigits(0);
							i.setRate(numberFormat.format((float) p.getHighCount()/p.getTotalCount()));
						}
						
					}
					
				}
			}
			result.setData(list);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("首页list获取失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	
	/**
	 * 获取服务detail
	 * @return
	 */
	@RequestMapping("getFw")
	@ResponseBody
	public AjaxResultVo getFw(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			FwContentPo po = fwContentService.getById(id);
			result.setData(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取服务detail失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	
	/**
	 * 增加阅读
	 * @return
	 */
	@RequestMapping("addRead")
	@ResponseBody
	public AjaxResultVo addRead(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			FwContentPo po = fwContentService.getById(id);
			if(po.getRead1()==null){
				po.setRead1(1);
			}else{
				po.setRead1(po.getRead1()+1);
			}
			
			fwContentService.updateDynamic(po);
			//result.setData(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("增加阅读量失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	/**
	 * 获取服务detail
	 * @return
	 */
	@RequestMapping("addLike")
	@ResponseBody
	public AjaxResultVo addLike(long id){
		AjaxResultVo result = new AjaxResultVo();
		try {
			FwContentPo po = fwContentService.getById(id);
			if(po.getDianZan()==null){
				po.setDianZan(1);
			}else{
				po.setDianZan(po.getDianZan()+1);
			}
			
			fwContentService.updateDynamic(po);
			//result.setData(po);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("服务点赞失败 ",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
	
		
		return result;
	}
	
	
	
	
}

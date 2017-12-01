package com.dsj.data.web.agent;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.PersonCenterVo;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.modules.evaluate.po.AgentEvaluatePo;
import com.dsj.modules.evaluate.service.AgentEvaluateService;
import com.dsj.modules.fw.po.FwSpuPo;
import com.dsj.modules.fw.service.FwSpuService;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.oldhouse.po.OldHouseMasterPo;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.RecommendVo;

@Controller
@RequestMapping(value = "front/agentIndex")
public class AgentIndexController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentIndexController.class);
//	private final long userId = 557;
	@Autowired
	private AgentService agentService;
	@Autowired
	private UserService userService;
	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AgentEvaluateService agentEvaluateService;
	
	@Autowired
	private FwSpuService fwSpuService;
	/**
	 * 经纪人个人页 卡片信息
	 * @param id
	 * @return
	 */
	@RequestMapping("info")
	public ModelAndView info(long userId,HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		try{
			//增加浏览历史
			
			if(UserUtil.getCurrentUser(request)!=null){
				/**
				 * 参数3个
				 * 1. type:枚举类型
				 * 2. objectId: 经纪人/新房/二手房/租房 id
				 * 3. userId:登录用户的Id
				 */
				userService.updateHandleLookHistory(FollowEnums.AGENT.getValue(),userId,UserUtil.getCurrentUser(request).getId());				
			}
			
			UserPo user = userService.getById(userId);
			AgentVo agent = agentService.getByUserId(userId);
			int isfollow=0;
			if(UserUtil.getCurrentUser(request)!=null){
				isfollow = agentService.findIsFollow(agent.getUserId(),UserUtil.getCurrentUser(request).getId(),FollowEnums.AGENT.getValue());
			}

			List<RecommendVo> list = agentService.getRecommend(agent.getUserId().intValue());
			List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
			if(list!=null){
				for(RecommendVo item : list){
					Map<String,Object> map = new HashMap<String,Object>();
					if(item.getType()==Integer.valueOf(FollowEnums.NEWHOUSE.getValue())){
						NewHouseDirectoryAuthVo p = newHouseDirectoryAuthService.getRecommendNewHouse(item.getHouseId());
						if(p!=null){
							map.put("houseId", p.getId());
							map.put("title", p.getName());
							map.put("pictureUrl", p.getPictureUrl());											
							map.put("type", item.getType());
							resultList.add(map);
						}
						
						
					}else if(item.getType()==Integer.valueOf(FollowEnums.OLDHOUSE.getValue())){
						OldHouseMasterPo p = oldHouseMasterService.getById(item.getHouseId());
						if(p!=null){
							map.put("houseId", p.getId());
							map.put("title", p.getTitle());
							map.put("pictureUrl", p.getImageUrl());
							map.put("type", item.getType());
							resultList.add(map);
						}
						
					}else{
						RentHouseOriginPo p = rentHouseOriginService.getById(item.getHouseId());
						map.put("houseId", p.getId());
						map.put("title", p.getHouseTitle());
						map.put("pictureUrl", p.getPictureUrl());
						map.put("type", item.getType());
						resultList.add(map);
					}
				}
			}
			
			//推荐房源小于3，取权证  房屋过户，企业产权过户，补证登记  对应id 1,5,7
			if(resultList.size()<3){
				List<FwSpuPo> fwList = fwSpuService.getThree();
				Map<String,Object> fwmap = new HashMap<String,Object>();
				if(resultList.size()==0){
					for(FwSpuPo item : fwList){
						Map<String,Object> fwmap1 = new HashMap<String,Object>();
						fwmap1.put("houseId", item.getId());
						fwmap1.put("title", item.getName());
						fwmap1.put("pictureUrl", item.getImg());
						fwmap1.put("type", 5);//商品
						resultList.add(fwmap1);
					}
				}else if(resultList.size()==1){
					
					fwmap.put("houseId", fwList.get(0).getId());
					fwmap.put("title", fwList.get(0).getName());
					fwmap.put("pictureUrl", fwList.get(0).getImg());
					fwmap.put("type", 5);//商品
					resultList.add(fwmap);
					
					Map<String,Object> fwmap2 = new HashMap<String,Object>();
					fwmap2.put("houseId", fwList.get(1).getId());
					fwmap2.put("title", fwList.get(1).getName());
					fwmap2.put("pictureUrl", fwList.get(1).getImg());
					fwmap2.put("type", 5);//商品
					resultList.add(fwmap2);
				}else if(resultList.size()==2){
					
					fwmap.put("houseId", fwList.get(0).getId());
					fwmap.put("title", fwList.get(0).getName());
					fwmap.put("pictureUrl", fwList.get(0).getImg());
					fwmap.put("type", 5);//商品
					resultList.add(fwmap);
				}
				
			}
			mav.setViewName("agent/agentIndex");
			mav.addObject("agent", agent);
			mav.addObject("recommend", resultList);
			if(UserUtil.getCurrentUser(request)!=null){
				mav.addObject("loginStatus",1);//登录
			}else{
				mav.addObject("loginStatus",2);//未登录
			}
			mav.addObject("username",user.getUsername());
			//是否登陆判断
//			if(){
//				isfollow = 0;
//			}
			
			mav.addObject("follow", isfollow);

		}catch (Exception e) {
			LOGGER.error("查询经纪人信息异常",e);

		}
		return mav;
	}
	/**
	 * 增加关注经纪人
	 * @param userId
	 * @return
	 */
	@RequestMapping("addFollow")
	@ResponseBody
	public AjaxResultVo addFollow(long objectId, int type,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("objectId", objectId);
		map.put("userId",UserUtil.getCurrentUser(request).getId());
		map.put("type", type);//经纪人类型
		if(agentService.findIsFollow(objectId,UserUtil.getCurrentUser(request).getId(),type)>0){
			result.setStatusCode(StatusCode.SERVER_ERROR);			
			result.setMessage("已关注，请勿重复操作");
			return result;
		}
		
		map.put("createTime",new Date());
		try {
			userService.insertFollow(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setMessage("关注成功");
		} catch (Exception e) {
			LOGGER.error("关注失败" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);			
			result.setMessage("关注失败");
		}
		return result;
	}
	
	/**
	 * 取消关注经纪人
	 * @param userId
	 * @return
	 */
	@RequestMapping("cancleFollow")
	@ResponseBody
	public AjaxResultVo cancleFollow(long objectId,int type,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("objectId", objectId);
		map.put("userId",UserUtil.getCurrentUser(request).getId() );
		map.put("type",type);//经纪人类型
		try {
			userService.deleteFollowByMap(map);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setMessage("取消成功");
		} catch (Exception e) {
			LOGGER.error("取消关注失败" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);			
			result.setMessage("取消失败");
		}
		return result;
	}
	/**
	 * 获取经纪人所属房源  推荐的放前边
	 * @param request
	 * @param agentId
	 * @param type
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("getHouse")
	@ResponseBody
	public AjaxResultVo getHouse(HttpServletRequest request,long agentId,int type,int page,int pageSize){
		AjaxResultVo result = new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		String time = request.getParameter("time");
		String price = request.getParameter("price");
		
		if(time!=null && !time.equals("")){
			map.put("time", time);
		}
		if(price!=null && !price.equals("")){
			map.put("price", price);
		}
		map.put("pageFirst", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("userId",agentId );
		PersonCenterVo personCenter = new PersonCenterVo();
		try {
			if(type==3){//租房
				List<RentHouseOriginVo> list = rentHouseOriginService.findAgentRentHouse(map);
				int count = rentHouseOriginService.findAgentRentHouseCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==2){//二手房
				
				List<OldHouseMasterVo> list = oldHouseMasterService.findAgentOldHouse(map);
				int count = oldHouseMasterService.findAgentOldHouseCount(map);
				if(count==0){
					personCenter.setFlag(1);
					list = oldHouseMasterService.findPcPageOldHouse(map);
					count = oldHouseMasterService.findPcPageOldHouseCount(map);
				}else{
					personCenter.setFlag(0);
				}
				
				
				personCenter.setList(list);
				personCenter.setCount(count);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==1){//新房
				List<NewHouseDirectoryAuthVo> list = newHouseDirectoryAuthService.findAgentNewHouse(map);
				int count = newHouseDirectoryAuthService.findAgentNewHouseCount(map);
				
				if(count==0){
					personCenter.setFlag(1);
//					map.put("dataType", 1);//大搜客
//					map.put("userId", null);
//					list = newHouseDirectoryAuthService.findAgentNewHouse(map);
//					count = newHouseDirectoryAuthService.findAgentNewHouseCount(map);
					list = newHouseDirectoryAuthService.getIndexNewHouse(map);
					count = newHouseDirectoryAuthService.getIndexNewHouseCount(map);
				}else{
					personCenter.setFlag(0);
				}
				//价格
				for(int i = 0;i<list.size();i++){
					if(null!=list.get(i)){
						NewHouseFrontVo vo = newHouseDirectoryAuthService.getNewHousePrice(list.get(i).getId());
						if(vo.getIndexPrice()!=null){
							list.get(i).setReferencePrice(vo.getPriceName()+vo.getIndexPrice()+vo.getPricedw());
						}
						
						list.get(i).setPriceName(vo.getPriceName());
						list.get(i).setPricedw(vo.getPricedw());
						list.get(i).setIndexPrice(vo.getIndexPrice());
						
					}else{
						list.remove(i);
						count--;
					}
				}
				
					
				personCenter.setList(list);
				personCenter.setCount(count);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("查询经纪人所属房源失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("grade")
	@ResponseBody
    public AjaxResultVo grade(long userId) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentVo a = agentService.getByUserId(userId);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("agentId", a.getAgentCode());
			List<AgentEvaluatePo>  list= agentEvaluateService.listBy(map);
			if(list.size()>0){
				result.setData(list);
			}else{
				result.setData(0);//为空
			}
			
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("评价获取异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;

	}
	
	/**
	 * 获取等级
	 * @return
	 */
	@RequestMapping("getRank")
	@ResponseBody
    public AjaxResultVo getRank(long userId) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			AgentVo a = agentService.getByUserId(userId);
			Map<String,Object> map = new HashMap<String,Object>();
			
			map = agentEvaluateService.getRankAndScore(a.getAgentCode());
			result.setData(map);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取等级异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;

	}

	
	
}

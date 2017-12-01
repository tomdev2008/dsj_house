package com.dsj.data.wap.agent;

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

import com.dsj.common.enums.StatusCode;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.MyFollowVo;
import com.dsj.data.wap.utils.UserUtil;
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
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentVo;
import com.dsj.modules.system.vo.RecommendVo;


@Controller
@RequestMapping(value = "agentInfo")
public class AgentInfoController {
	private final Logger LOGGER = LoggerFactory.getLogger(AgentNewsController.class);
	
	@Autowired
	private UserService userService;
	@Autowired
	private AgentService agentService;
//	@Autowired
//	private RentHouseOriginService rentHouseOriginService;
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	@Autowired
	private AgentEvaluateService agentEvaluateService;
	@Autowired
	private FwSpuService fwSpuService;
	/**
	 * 获取经纪人信息
	 * @param userId
	 * @return
	 */
	@RequestMapping("agent")
	@ResponseBody
	public AjaxResultVo agent(long userId,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
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
			AgentVo agent = agentService.getByUserId(userId);
			result.setData(agent);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("获取经纪人异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 获取经纪人推荐
	 * @param userId
	 * @return
	 */
	@RequestMapping("getRecommend")
	@ResponseBody
	public AjaxResultVo getRecommend(Integer userId){
		AjaxResultVo result = new AjaxResultVo();
		try {
			List<RecommendVo> list = agentService.getRecommend(userId);
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
					}
//					}else{
//						RentHouseOriginPo p = rentHouseOriginService.getById(item.getHouseId());
//						map.put("houseId", p.getId());
//						map.put("title", p.getHouseTitle());
//						map.put("pictureUrl", p.getPictureUrl());
//						map.put("type", item.getType());
//					}
					
				}	
				//推荐房源小于3，取权证  房屋过户，企业产权过户，补证登记  对应id 1,5,7
				if(resultList.size()<3){
					List<FwSpuPo> fwList = fwSpuService.getThree();
					Map<String,Object> fwmap = new HashMap<String,Object>();
					if(resultList.size()==0){
						for(FwSpuPo item1 : fwList){
							Map<String,Object> fwmap1 = new HashMap<String,Object>();
							fwmap1.put("houseId", item1.getId());
							fwmap1.put("title", item1.getName());
							fwmap1.put("pictureUrl", item1.getImg());
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
				result.setData(resultList);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("获取经纪人推荐异常",e);
			result.setStatusCode(StatusCode.SUCCESS);
		}
		return result;
		
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
	public AjaxResultVo getHouse(HttpServletRequest request,long userId,int type,int page,int pageSize){
		AjaxResultVo result = new AjaxResultVo();
		Map<String,Object> map = new HashMap<String,Object>();
		String time = request.getParameter("time");
		String price = request.getParameter("price");
		String timeOld = request.getParameter("timeOld");
		String priceHigh = request.getParameter("priceHigh");
		
		if(time!=null && !time.equals("")){
			map.put("time", time);
		}
		if(price!=null && !price.equals("")){
			map.put("price", price);
		}
		if(timeOld!=null && !timeOld.equals("")){
			map.put("timeOld", timeOld);
		}
		if(priceHigh!=null && !priceHigh.equals("")){
			map.put("priceHigh", priceHigh);
		}
		map.put("pageFirst", (page-1)*pageSize);
		map.put("pageSize", pageSize);
		map.put("userId",userId );
		MyFollowVo vo = new MyFollowVo();
		try {
			if(type==3){//租房
//				List<RentHouseOriginVo> list = rentHouseOriginService.findAgentRentHouse(map);
//				int count = rentHouseOriginService.findAgentRentHouseCount(map);
//				vo.setCount(count);
//				vo.setList(list);
//				result.setData(vo);
//				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==2){//二手房
				
				List<OldHouseMasterVo> list = oldHouseMasterService.findAgentOldHouse(map);
				int count = oldHouseMasterService.findAgentOldHouseCount(map);
				if(count==0){
					list = oldHouseMasterService.findPcPageOldHouse(map);
					count = oldHouseMasterService.findPcPageOldHouseCount(map);
				}
				vo.setList(list);
				vo.setCount(count);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==1){//新房
				List<NewHouseDirectoryAuthVo> list = newHouseDirectoryAuthService.findAgentNewHouse(map);
				int count = newHouseDirectoryAuthService.findAgentNewHouseCount(map);
				
				if(count==0){
					//map.put("dataType", 1);//大搜客
					//map.put("userId", null);
					list = newHouseDirectoryAuthService.getIndexNewHouse(map);
					count = newHouseDirectoryAuthService.getIndexNewHouseCount(map);
				}
				//价格
				for(int i = 0;i<list.size();i++){
					if(null!=list.get(i)){
						NewHouseFrontVo vo1 = newHouseDirectoryAuthService.getNewHousePrice(list.get(i).getId());
						if(vo1.getIndexPrice()!=null){
							list.get(i).setReferencePrice(vo1.getPriceName()+vo1.getIndexPrice()+vo1.getPricedw());
						}
						
					}else{
						list.remove(i);
						count--;
					}
				}
				
				vo.setList(list);
				vo.setCount(count);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("查询经纪人所属房源失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 查询是否已关注
	 * @param objectId
	 * @param request
	 * @return
	 */
	@RequestMapping("followStatus")
 	@ResponseBody
 	public AjaxResultVo followSatus(long objectId,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
			int isfollow = 0;
			if(UserUtil.getCurrentUser(request)!=null){
				isfollow = agentService.findIsFollow(objectId,UserUtil.getCurrentUser(request).getId(),FollowEnums.AGENT.getValue());
			}
			result.setData(isfollow);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询是否关注异常");
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		
		return result;
	}

	/**
	 * 查询是否经纪人自己登陆，显示发动态按钮
	 * @param objectId
	 * @param request
	 * @return
	 */
	@RequestMapping("loginUser")
 	@ResponseBody
 	public AjaxResultVo loginUser(long objectId,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
			int loginStatus = 0;
			if(UserUtil.getCurrentUser(request)!=null){
				if(UserUtil.getCurrentUser(request).getId()==objectId){
					loginStatus = 1;
				}
			} 
			result.setData(loginStatus);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询是否经纪人本人登陆异常");
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
    public AjaxResultVo getRank(long objectId,HttpServletRequest request) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			if(request.getParameter("agent")!=null){
				objectId=UserUtil.getCurrentUser(request).getId();
			}
			AgentVo a = agentService.getByUserId(objectId);
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
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("grade")
	@ResponseBody
    public AjaxResultVo grade(long objectId,HttpServletRequest request) { 
		AjaxResultVo result = new AjaxResultVo();
		try {
			if(request.getParameter("agent")!=null){
				objectId=UserUtil.getCurrentUser(request).getId();
			}
			AgentVo a = agentService.getByUserId(objectId);
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

}

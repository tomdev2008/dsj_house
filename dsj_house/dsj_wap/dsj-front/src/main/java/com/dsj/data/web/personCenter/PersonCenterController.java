package com.dsj.data.web.personCenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.service.UploadService;
import com.dsj.data.web.utils.PersonCenterVo;
import com.dsj.data.web.utils.UserUtil;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.po.FeedbackPo;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.service.FeedbackService;
import com.dsj.modules.comment.vo.CommentVo;
import com.dsj.modules.easemob.po.EasemobUserPo;
import com.dsj.modules.easemob.service.EasemobUserService;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.vo.FwOrderVo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;
import com.dsj.modules.oldhouse.service.OldHouseEntrustService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.rent.po.RentHouseEntrustPo;
import com.dsj.modules.rent.service.RentHouseEntrustService;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.AgentPo;
import com.dsj.modules.system.po.MemberPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.service.MemberService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentVo;


@Controller
@RequestMapping(value = "front/personCenter")
public class PersonCenterController {
	private final Logger LOGGER = LoggerFactory.getLogger(PersonCenterController.class);
	
	//private final long userId = 557;
	
	@Autowired
	private OldHouseEntrustService oldHouseEntrustService;
	@Autowired
	private RentHouseEntrustService rentHouseEntrustService;
	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private RentHouseOriginService rentHouseOriginService;
	@Autowired
	private CommentService commentService;
	@Autowired
	UploadService uploadService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private FwOrderService fwOrderService;
	
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	@Autowired
	private EasemobUserService easemobUserService;
	/**
	 * 个人中心首页
	 * @return
	 */
	@RequestMapping("center")
	public ModelAndView center(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		try {
			UserPo user = userService.getById(UserUtil.getCurrentUser(request).getId());
			
			if(user.getUserType()==UserType.AGENT.getValue()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", UserUtil.getCurrentUser(request).getId());
				AgentPo agent = agentService.getBy(map);
				user.setUsername(agent.getName());
			}else if(user.getUserType()==UserType.MEMBER.getValue()){
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", UserUtil.getCurrentUser(request).getId());
				//System.out.println(UserUtil.getCurrentUser(request).getId());
				MemberPo member = memberService.getBy(map);
				if(StringUtils.isNotBlank(member.getNikename())){
					user.setUsername(member.getNikename());	
				}else{
					String phone = member.getTellPhone();
					user.setUsername(phone.substring(0, 3)+"*****"+phone.substring(phone.length()-3));
				}
			}	
			mav.setViewName("personCenter/personCenter");
			if(request.getParameter("typeOfPage")!=null){
				System.out.println(request.getParameter("typeOfPage"));
				mav.addObject("typeOfPage", request.getParameter("typeOfPage"));
			}
			if ("yes".equals(request.getParameter("pay"))) {
				mav.addObject("pay", "yes");
			}
			
			mav.addObject("user", user);

			
		} catch (Exception e) {
			LOGGER.error("用户信息查询失败",e);
		}

		return mav;
	}
	
	@RequestMapping("order")
	@ResponseBody
	public AjaxResultVo order(int type,int page,int pageSize,HttpServletRequest request) {
		AjaxResultVo result = new AjaxResultVo();
		int pageFirst = (page-1)*pageSize;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId",UserUtil.getCurrentUser(request).getId());	
			if (type != 0 ) {
				if (type == 1) { // 待付款
					map.put("statusS", "(1,3)"); //1待付款  3已过期
				}else if (type == 4) { //已付款
					map.put("statusS", "(4,7,11,12,13)");//4已付款  7待买家确认  11待开启服务 12已开启服务 13待结束服务
				}else if (type == 9) { //待评论
					map.put("statusS", "(9)"); //9 待评论
				}else if (type == 10) { //已评论
					map.put("statusS", "(10)");// 10 已评论
				}else if (type == 2) { //已取消
					map.put("statusS", "(2)");// 2已取消
				}else if (type == 5) {
					map.put("statusS", "(5,6)");//5退款受理中 6退款完成
				}
			}
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			map.put("pageFirst", pageFirst);
			map.put("pageSize", pageSize);
			PersonCenterVo personCenter = new PersonCenterVo();
			List<FwOrderVo> list = fwOrderService.findOrderPage(map);
			long count = fwOrderService.findOrderPageCount(map);
			//头部数字
			getHeadNum(personCenter,UserUtil.getCurrentUser(request).getId());
			personCenter.setCount(count);
			personCenter.setList(list);
			result.setData(personCenter);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("订单查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	public void getHeadNum(PersonCenterVo personCenter,Long userId){
		//全部订单
		Map<String, Object> map = new HashMap<>();
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		map.put("userId",userId);
		long allOrderNum = fwOrderService.findOrderPageCount(map);
		//待付款
		map.put("statusS", "(1,3)");
		long waitOrderNum = fwOrderService.findOrderPageCount(map);
		//已付款
		map.put("statusS", "(4,7,11,12,13)");
		long finishOrderNum = fwOrderService.findOrderPageCount(map);
		//待评论
		map.put("statusS", "(9)"); 
		long waitViewNum = fwOrderService.findOrderPageCount(map);
		//已评论
		map.put("statusS", "(10)");
		long finishViewNum = fwOrderService.findOrderPageCount(map);
		//已取消
		map.put("statusS", "(2)");
		long cancelNum = fwOrderService.findOrderPageCount(map);
		//已退款
		map.put("statusS", "(5,6)");
		long refundNum = fwOrderService.findOrderPageCount(map);
		personCenter.setAllOrderNum(allOrderNum);
		personCenter.setCancelNum(cancelNum);
		personCenter.setFinishOrderNum(finishOrderNum);
		personCenter.setFinishViewNum(finishViewNum);
		personCenter.setRefundNum(refundNum);
		personCenter.setWaitOrderNum(waitOrderNum);
		personCenter.setWaitViewNum(waitViewNum);
	}
	
	/**
	 * 我的关注
	 * @param userId
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("follow")
	@ResponseBody
	public AjaxResultVo follow(int type,int page,int pageSize,HttpServletRequest request) {
		AjaxResultVo result = new AjaxResultVo();
		int pageFirst = (page-1)*pageSize;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId",UserUtil.getCurrentUser(request).getId());	
			map.put("pageFirst", pageFirst);
			map.put("pageSize", pageSize);
			map.put("type", type);
			PersonCenterVo personCenter = new PersonCenterVo();
			if(type==FollowEnums.AGENT.getValue()){//关注的经纪人				
				List<AgentVo> list = agentService.findFollow(map);
				long count = agentService.findFollowCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.NEWHOUSE.getValue()){//我的关注--新房
				List<NewHouseDirectoryAuthVo> list = newHouseDirectoryAuthService.findFollow(map);
				int count = newHouseDirectoryAuthService.findFollowCount(map);
				
				if(list.size()>0){
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
				}
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.OLDHOUSE.getValue()){//我的关注--二手房
				List<OldHouseMasterVo> list = oldHouseMasterService.findFollow(map);
				int count = oldHouseMasterService.findFollowCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.RENTHOUSE.getValue()){//我的关注--租房
				List<RentHouseOriginVo> list = rentHouseOriginService.findFollow(map);
				long count = rentHouseOriginService.findFollowCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}


			
		} catch (Exception e) {
			LOGGER.error("关注查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	
	/**
	 * 我的浏览历史
	 * @param userId
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("lookHistory")
	@ResponseBody
	public AjaxResultVo lookHistory(int type,int page,int pageSize,HttpServletRequest request) {
		AjaxResultVo result = new AjaxResultVo();
		int pageFirst = (page-1)*pageSize;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", UserUtil.getCurrentUser(request).getId());	
			map.put("pageFirst", pageFirst);
			map.put("pageSize", pageSize);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			Date date = new Date(new Date().getTime() - (long)7* 24 * 60 * 60 * 1000);
			
			map.put("time", date);
			map.put("type", type);
			PersonCenterVo personCenter = new PersonCenterVo();
			if(type==FollowEnums.AGENT.getValue()){//浏览历史--经纪人			
				List<AgentVo> list = agentService.lookHistory(map);
				long count = agentService.lookHistoryCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.NEWHOUSE.getValue()){//浏览历史--新房
				List<NewHouseDirectoryAuthVo> list = newHouseDirectoryAuthService.lookHistory(map);
				int count = newHouseDirectoryAuthService.lookHistoryCount(map);
				if(list.size()>0){
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
				}
				
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.OLDHOUSE.getValue()){//浏览历史--二手房
				List<OldHouseMasterVo> list = oldHouseMasterService.lookHistory(map);
				int count = oldHouseMasterService.lookHistoryCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.RENTHOUSE.getValue()){//浏览历史--租房
				List<RentHouseOriginVo> list = rentHouseOriginService.lookHistory(map);
				long count = rentHouseOriginService.lookHistoryCount(map);
				personCenter.setCount(count);
				personCenter.setList(list);
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}

			
		} catch (Exception e) {
			LOGGER.error("浏览历史查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 我的委托
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("entrust")
	@ResponseBody
	public AjaxResultVo entrust(int type,int page,HttpServletRequest request) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("createPerson", UserUtil.getCurrentUser(request).getId());	

			PersonCenterVo personCenter = new PersonCenterVo();
			if(type==1){//我的委托--卖房
				List<OldHouseEntrustPo> list = oldHouseEntrustService.listBy(map);
				personCenter.setList(list);
				personCenter.setCount(list.size());
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==2){//我的委托--租房
				List<RentHouseEntrustPo> list = rentHouseEntrustService.listBy(map);
				personCenter.setList(list);
				personCenter.setCount(list.size());
				result.setData(personCenter);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("关注查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 取消关注
	 * @param type
	 * @param page
	 * @return
	 */
	@RequestMapping("cancleFollow")
	@ResponseBody
	public AjaxResultVo cancleFollow(long id) {
		AjaxResultVo result = new AjaxResultVo();
		try {
			userService.deleteFollow(id);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("取消关注失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 楼盘点评
	 * @param userId
	 * @param page
	 * @return
	 */
	@RequestMapping("comment")
	@ResponseBody
	public AjaxResultVo comment(int page,int pageSize,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			PersonCenterVo personCenter = new PersonCenterVo();
			map.put("commentUser", UserUtil.getCurrentUser(request).getId());
			map.put("pageFirst",(page-1)*pageSize);
			map.put("pageSize", pageSize);
		
			if(UserUtil.getCurrentUser(request).getUserType()==UserType.AGENT.getValue()){
				map.put("objectType",CommentEnum.COMMENT__HOUSE_REMARK.getCode());
			}else if(UserUtil.getCurrentUser(request).getUserType()==UserType.MEMBER.getValue()){
				map.put("objectType",CommentEnum.GENERAL_HOUSE_REMARK.getCode());
			}
			
			List<CommentVo> list = commentService.getUserComment(map);
			long count = commentService.getUserCommentCount(map);
 			personCenter.setList(list);
			personCenter.setCount(count);
			result.setData(personCenter);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询楼盘点评异常" ,e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	/**
	 * 头像
	 * @param taBase64
	 * @param reBase64
	 * @param id
	 * @return
	 */
	@RequestMapping("avatar")
	@ResponseBody
	public AjaxResultVo avatar(String base64,HttpServletRequest request){
		AjaxResultVo ajaxVo=new AjaxResultVo();
		try{
		String taImage=uploadService.uploadHeadProtrait(base64);//正方形

		UserPo user=new UserPo();
		user.setId(UserUtil.getCurrentUser(request).getId());
		user.setAvatar(taImage);
		userService.updateDynamic(user);
		
		
		ajaxVo.setStatusCode(StatusCode.SUCCESS);
		ajaxVo.setData(taImage);
		}catch(Exception e){
			ajaxVo.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("头像上传错误",e);
		}
		return ajaxVo;
	}
	/**
	 * 修改昵称
	 * @param nikename
	 * @param id
	 * @return
	 */
	@RequestMapping("nikename")
	@ResponseBody
	public AjaxResultVo nikename(String nikename,HttpServletRequest request){
		AjaxResultVo result=new AjaxResultVo();
		try{
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", UserUtil.getCurrentUser(request).getId());
			MemberPo member = memberService.getBy(map);
			member.setNikename(nikename);
			member.setUpdateTime(new Date());
			memberService.updateDynamic(member);
			/*im 更改昵称*/
			//UserPo po = userService.getById(UserUtil.getCurrentUser(request).getId());
			EasemobUserPo easemobUser = new EasemobUserPo();
			easemobUser.setUserName(member.getTellPhone());
			easemobUser.setNickName(member.getNikename());
			easemobUserService.modifyEasemobUserNickNameWithAdminToken(
					easemobUser);
			
			result.setStatusCode(StatusCode.SUCCESS);
			
		}catch(Exception e){
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改昵称异常",e);
		}
		return result;
	}
	/**
	 * 修改密码
	 * @param newpwd
	 * @param oldpwd
	 * @return
	 */
	@RequestMapping("password")
	@ResponseBody
	public AjaxResultVo password(String newpwd,String oldpwd,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try{
			
			if(ShiroSaltAndMd5Utils.getMD5(oldpwd).equals(UserUtil.getCurrentUser(request).getPassword())){
				UserPo user = new UserPo();
				user.setId(UserUtil.getCurrentUser(request).getId());
				user.setPassword(ShiroSaltAndMd5Utils.getMD5(newpwd));
				try {
					userService.updateDynamic(user);
					result.setStatusCode(StatusCode.SUCCESS);
				} catch (Exception e) {
					LOGGER.error("修改密码异常", e);
					result.setStatusCode(StatusCode.SERVER_ERROR);
				}
				
			}else{
				result.setStatusCode(StatusCode.NO_RESULT);
				result.setMessage("原密码输入不正确");
				result.setData(null);
			}
		}catch(Exception e){
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改密码异常",e);
		}
		return result;
	}
	/**
	 * 修改手机号
	 * @param 
	 * @return
	 */
	@RequestMapping("phone")
	@ResponseBody
	public AjaxResultVo phone(String phone,String vcode,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		VerifyCodePo v = verifyCodeService.getVerifyByPhoneLast(phone, null);
		if(v.getVerifyCode().equals(vcode)){

			UserPo u = UserUtil.getCurrentUser(request);
			u.setPhone(phone);
			//u.setUpdatePerson(userId);
			u.setUpdateTime(new Date());
			userService.updateDynamic(u);
			
			Map<String,Object> map1 = new HashMap<String,Object>();
			map1.put("phone", phone);
			UserPo user = userService.getBy(map1);
			if(user==null){
				if(u.getUserType()==UserType.AGENT.getValue()){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", UserUtil.getCurrentUser(request).getId());
					AgentPo agent = agentService.getBy(map);
					agent.setTellPhone(phone);
					agent.setUpdateTime(new Date());
					agentService.updateDynamic(agent);
					result.setStatusCode(StatusCode.SUCCESS);
				}else if(u.getUserType()==UserType.MEMBER.getValue()){
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("userId", UserUtil.getCurrentUser(request).getId());
					MemberPo member = memberService.getBy(map);
					member.setTellPhone(phone);
					member.setUpdateTime(new Date());
					memberService.updateDynamic(member);
					result.setStatusCode(StatusCode.SUCCESS);
				}
			}else{
				result.setStatusCode(StatusCode.PHONE_EXIST);
				result.setMessage("手机号已注册");
			}
				

			
		}else{			
			result.setStatusCode(StatusCode.NO_RESULT);
			result.setMessage("验证码输入有误");
		}
		
		
		
		return result;
	}
	
	/**
	 * 新增反馈
	 * @param feedback
	 * @return
	 */
	@RequestMapping("feedback")
	@ResponseBody
	private AjaxResultVo feedback(FeedbackPo feedback,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
			feedback.setCreateTime(new Date());
			feedback.setCreateUser(UserUtil.getCurrentUser(request).getId().intValue());
			feedbackService.saveDynamic(feedback);
			result.setStatusCode(StatusCode.SUCCESS);
			
		} catch (Exception e) {
			LOGGER.error("新增反馈异常",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
}

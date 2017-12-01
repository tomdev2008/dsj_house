package com.dsj.data.wap.center;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.ShiroSaltAndMd5Utils;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.sms.SMSUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.MyFollowVo;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.modules.comment.enums.CommentEnum;
import com.dsj.modules.comment.po.FeedbackPo;
import com.dsj.modules.comment.service.CommentService;
import com.dsj.modules.comment.service.FeedbackService;
import com.dsj.modules.comment.vo.CommentVo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;
import com.dsj.modules.oldhouse.service.OldHouseEntrustService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.oldhouse.vo.OldHouseMasterVo;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.VerifyCodeService;
import com.dsj.modules.system.enums.FollowEnums;
import com.dsj.modules.system.enums.UserType;
import com.dsj.modules.system.po.MemberPo;
import com.dsj.modules.system.po.UserPo;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.EmployeeService;
import com.dsj.modules.system.service.MemberService;
import com.dsj.modules.system.service.UserService;
import com.dsj.modules.system.vo.AgentVo;

@Controller
@RequestMapping(value = "center")
public class CenterController {
	private final Logger LOGGER = LoggerFactory.getLogger(CenterController.class);
	

	@Autowired
	private UserService userService;
	@Autowired
	private MemberService memberService;
	@Autowired
	private AgentService agentService;

	@Autowired
	private CommentService commentService;
	@Autowired
	private OldHouseEntrustService oldHouseEntrustService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private OldHouseMasterService oldHouseMasterService;
	
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;
	
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping("checkLogin")
	@ResponseBody
	public AjaxResultVo checkLogin(HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		if(UserUtil.getCurrentUser(request)==null){
			result.setData(0);//未登录
			result.setStatusCode(StatusCode.SUCCESS);
		}else{
			result.setData(UserUtil.getCurrentUser(request));//登陆
			result.setStatusCode(StatusCode.SUCCESS);
		}
		return result;
	}
	
	@RequestMapping("getLoginUser")
	@ResponseBody
	public AjaxResultVo getLoginUser(int userType,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
			if(userType==UserType.AGENT.getValue()){
				AgentVo vo = agentService.getByUserId(UserUtil.getCurrentUser(request).getId());
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}else{
				Map<String,Object> map = new HashMap<String,Object>();
				map.put("userId", UserUtil.getCurrentUser(request).getId());
				MemberPo vo = memberService.getBy(map);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}
			
		} catch (Exception e) {
			LOGGER.error("获取用户失败",e);
		}
		
		return result;
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
	public AjaxResultVo follow(int type, int page, int pageSize, HttpServletRequest request) {
		AjaxResultVo result = new AjaxResultVo();
		int pageFirst = (page-1)*pageSize;
		try {
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", UserUtil.getCurrentUser(request).getId());	
			map.put("pageFirst", pageFirst);
			map.put("pageSize", pageSize);
			map.put("type", type);
			MyFollowVo vo = new MyFollowVo();
			if(type==FollowEnums.AGENT.getValue()){
				List<AgentVo> list = agentService.findFollow(map);
				vo.setList(list);
			}else if(type==FollowEnums.NEWHOUSE.getValue()){
				List<NewHouseDirectoryAuthVo> list = newHouseDirectoryAuthService.findFollow(map);
				if(list.size()>0){
					for(int i = 0;i<list.size();i++){
						if(null!=list.get(i)){
							NewHouseFrontVo vo1 = newHouseDirectoryAuthService.getNewHousePrice(list.get(i).getId());
							if(vo1.getIndexPrice()!=null){
								list.get(i).setReferencePrice(vo1.getPriceName()+vo1.getIndexPrice()+vo1.getPricedw());
							}
							
						}else{
							list.remove(i);
						}
					}
				}
				vo.setList(list);
			}else if(type==FollowEnums.OLDHOUSE.getValue()){
				List<OldHouseMasterVo> list = oldHouseMasterService.findFollow(map);
				vo.setList(list);
			}
			
			//long count = agentService.findFollowCount(map);

			
			//int count = newHouseDirectoryAuthService.findFollowCount(map);
		
			
			//int count = oldHouseMasterService.findFollowCount(map);
			result.setData(vo);
			result.setStatusCode(StatusCode.SUCCESS);

		} catch (Exception e) {
			LOGGER.error("关注查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 批量取消关注
	 * @param ids
	 * @return
	 */
	@RequestMapping("cancleFollow")
	@ResponseBody
	public AjaxResultVo cancleFollow(@RequestBody List<Integer> ids){
		AjaxResultVo result = new AjaxResultVo();
		try {
			userService.deleteManyFollow(ids);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("批量取消关注异常",e);
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
			MyFollowVo vo = new MyFollowVo();
			if(type==FollowEnums.AGENT.getValue()){//浏览历史--经纪人			
				List<AgentVo> list = agentService.lookHistory(map);

				vo.setList(list);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.NEWHOUSE.getValue()){//浏览历史--新房
				List<NewHouseDirectoryAuthVo> list = newHouseDirectoryAuthService.lookHistory(map);
				
				if(list.size()>0){
					for(int i = 0;i<list.size();i++){
						if(null!=list.get(i)){
							NewHouseFrontVo vo1 = newHouseDirectoryAuthService.getNewHousePrice(list.get(i).getId());
							if(vo1.getIndexPrice()!=null){
								list.get(i).setReferencePrice(vo1.getPriceName()+vo1.getIndexPrice()+vo1.getPricedw());
							}
							
						}else{
							list.remove(i);
						}
					}
				}
				vo.setList(list);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==FollowEnums.OLDHOUSE.getValue()){//浏览历史--二手房
				List<OldHouseMasterVo> list = oldHouseMasterService.lookHistory(map);

				vo.setList(list);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}

			
		} catch (Exception e) {
			LOGGER.error("浏览历史查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}

		return result;
	}
	/**
	 * 验证码
	 * @param phone
	 * @return
	 */
	@RequestMapping("vcode")
	@ResponseBody
	public AjaxResultVo vcode(String phone){
		AjaxResultVo result = new AjaxResultVo();
		int code = new Random().nextInt(899999) + 100000;;
		VerifyCodePo co = new VerifyCodePo();
		try {
			SMSUtil.sendSMS(SMSTemplate.getDrawingTemplate(SMSTemplate.XCX_ZC, String.valueOf(code)), phone);
			co.setPhone(phone);
			co.setVerifyCode(String.valueOf(code));
			co.setCreateTime(new Date());
			verifyCodeService.save(co);
			result.setStatusCode(StatusCode.SUCCESS);
			result.setMessage("验证码发送成功");  
		} catch (Exception e) {
			LOGGER.error("验证码发送异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
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

			UserPo u = userService.getById(UserUtil.getCurrentUser(request).getId());
			u.setPhone(phone);
			u.setUsername(phone);
			//u.setUpdatePerson(userId);
			u.setUpdateTime(new Date());
			userService.updateDynamic(u);

			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", u.getId());
			MemberPo member = memberService.getBy(map);
			member.setTellPhone(phone);
			member.setUpdateTime(new Date());
			memberService.updateDynamic(member);
			result.setStatusCode(StatusCode.SUCCESS);
		}else{			
			result.setStatusCode(StatusCode.NO_RESULT);
			result.setMessage("验证码输入有误");
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
		
			UserPo u = userService.getById(UserUtil.getCurrentUser(request).getId());
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("userId", u.getId());
			MemberPo member = memberService.getBy(map);
			member.setNikename(nikename);
			member.setUpdateTime(new Date());
			memberService.updateDynamic(member);
		
		result.setStatusCode(StatusCode.SUCCESS);
		}catch(Exception e){
			result.setStatusCode(StatusCode.SERVER_ERROR);
			LOGGER.error("修改昵称异常",e);
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
			MyFollowVo vo = new MyFollowVo();
			map.put("commentUser", UserUtil.getCurrentUser(request).getId());
			map.put("pageFirst",(page-1)*pageSize);
			map.put("pageSize", pageSize);
			map.put("objectType", CommentEnum.GENERAL_HOUSE_REMARK.getCode());
			List<CommentVo> list = commentService.getUserComment(map);
			long count = commentService.getUserCommentCount(map);
			vo.setList(list);
			vo.setCount(count);
			result.setData(vo);
			result.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			LOGGER.error("查询楼盘点评异常" ,e);
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

			MyFollowVo vo = new MyFollowVo();
			if(type==1){//我的委托--卖房
				List<OldHouseEntrustPo> list = oldHouseEntrustService.listBy(map);
				vo.setList(list);
				vo.setCount(list.size());
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}else if(type==2){//我的委托--租房
				//List<RentHouseEntrustPo> list = rentHouseEntrustService.listBy(map);
				vo.setList(null);
				vo.setCount(0);
				result.setData(vo);
				result.setStatusCode(StatusCode.SUCCESS);
			}
		} catch (Exception e) {
			LOGGER.error("委托查询失败",e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
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
	


package com.dsj.data.wap.entrust;

import java.util.Date;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.sms.SMSTemplate;
import com.dsj.common.utils.sms.SMSUtil;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.wap.utils.UserUtil;
import com.dsj.modules.oldhouse.po.OldHouseEntrustPo;
import com.dsj.modules.oldhouse.service.OldHouseEntrustService;
import com.dsj.modules.other.po.VerifyCodePo;
import com.dsj.modules.other.service.VerifyCodeService;



@Controller
@RequestMapping(value = "entrust")
public class EntrustController {
	private final Logger LOGGER = LoggerFactory.getLogger(EntrustController.class);
	
	@Autowired
	private OldHouseEntrustService oldHouseEntrustService;
//	@Autowired
//	private RentHouseEntrustService rentHouseEntrustService;
	@Autowired
	private VerifyCodeService verifyCodeService;
	
	@RequestMapping("sell")
	public ModelAndView center() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("personCenter/entrust");

		return mav;
	}
	/**
	 * 新增卖房委托
	 * @param p
	 * @return
	 */
	@RequestMapping("addOldHouse")
	@ResponseBody
	public AjaxResultVo addOldHOuse(OldHouseEntrustPo p,String vcode,HttpServletRequest request){
		AjaxResultVo result = new AjaxResultVo();
		try {
			VerifyCodePo v = verifyCodeService.getVerifyByPhoneLast(p.getEntrustPhone(), null);
			if(v.getVerifyCode().equals(vcode)){
				p.setCreatePerson(UserUtil.getCurrentUser(request).getId().intValue());
				p.setStatus(1);//1:新添加--未处理 2:已处理
				p.setCreateTime(new Date());
				oldHouseEntrustService.saveDynamic(p);
				result.setStatusCode(StatusCode.SUCCESS);
			}else{
				result.setStatusCode(StatusCode.USER_VCODE_ERROR);
			}
			
		} catch (Exception e) {
			LOGGER.error("新增卖房委托异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}
	
	/**
	 * 新增租房委托
	 * @param p
	 * @return
	 */
//	@RequestMapping("addRent")
//	@ResponseBody
//	public AjaxResultVo addRent(RentHouseEntrustPo p,String vcode){
//		AjaxResultVo result = new AjaxResultVo();
//		try {
//			VerifyCodePo v = verifyCodeService.getVerifyByPhoneLast(p.getEntrustPhone(), null);
//			if(v.getVerifyCode().equals(vcode)){
//				p.setCreatePerson(userId);
//				p.setStatus(1);//新添加--未处理
//				p.setCreateTime(new Date());
//				rentHouseEntrustService.saveDynamic(p);
//				result.setStatusCode(StatusCode.SUCCESS);
//			}else{
//				result.setStatusCode(StatusCode.USER_VCODE_ERROR);
//			}
//			
//		} catch (Exception e) {
//			LOGGER.error("新增卖房委托异常", e);
//			result.setStatusCode(StatusCode.SERVER_ERROR);
//		}
//		return result;
//	}
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
			result.setMessage("短信发送成功");  
		} catch (Exception e) {
			LOGGER.error("验证码发送异常异常", e);
			result.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return result;
	}

}

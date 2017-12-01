package com.dsj.common.utils.sms;

public  final  class SMSTemplate {
	public static final String XCX_ZC="【大搜家】尊敬的用户，您的验证码为'.$code.'，有效期为5分钟，如有疑虑请详询400-898-6868转888(客服电话)";
	public static final String AUTH_RESULT="【大搜家】尊敬的用户，您的账号已审核'.$code.'，如有疑虑请详询400-898-6868转888(客服电话)";
	public static final String EVELOPER_AUTH_RESULT="【大搜家】恭喜你企业账号开通成功.账号是'.$code.'初始密码是123456,请尽快修改密码.入驻大搜家平台,与百家房企共同为用户买房提供多种解决方法.未来的日子我们一起努力，如有疑虑请详询400-898-6868转888(客服电话)";
	public static final String AUDIT_SUCCESS="【大搜家】恭喜您经纪人账号开通成功。账号是'.$code.'，初始密码是123456,请尽快修改密码。入住大搜家，您可以发布房源，与网友互动，如有疑问请咨询400-898-6868转888(客服电话)";
	public static final String AUDIT_FAIL="【大搜家】您的账号审核失败。请核对个人信息，补全必填信息。如有疑问请咨询400-898-6868转888(客服电话)";
	
	//开始服务  ok
	public static final String FW_START="【大搜家】尊敬的客户，您好！感谢您使用大搜家权证过户服务，我是负责办理您过户的权政专员:'.$code0.'，电话：'.$code1.'。之后的业务办理将由我全程序陪伴您，如有疑义，请致电客服4008100686";
	
	//房屋与购房资格核验通过 ok
	public static final String FWYGFZZSHTG="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的房屋与购房资格核验已通过，接下来将进行网签。如有疑义，请致电客服4008100686";
	//购房资格核验通过 ok
	public static final String YGFZZSHTG="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的购房资格核验已通过，接下来将进行网签。如有疑义，请致电客服4008100686";
	//网签成功 ok
	public static final String WQCG="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的网签已通过。如有疑义，请致电客服4008100686";
	//预约缴税 ok
	public static final String YYJS="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，我是为您负责过户的权证专员'.$code0.'，联系电话'.$code1.'，现已成功为您预约缴税，时间为'.$code2.'，在'.$code3.'（地址），在您到达税务局后请联系我。您需要准备的资料包括：夫妻双方的身份证、户口本、结婚证、房本原件，"
										+ "所有的原件请您务必在缴税时携带至税务局，实际税费以税务局计税为准。如有疑义，请致电客服4008100686";
	//预约过户ok
	public static final String YYGH="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务。我是为您负责过户的权证专员'.$code0.'，联系电话'.$code1.'。现已成功为您预约过户，时间为'.$code2.'，在'.$code3.'（地址），在您到达房管局(建委)后请联系我。您需要准备的资料包括：夫妻双方的身份证、户口本、结婚证、房本原件，"
									+ "所有的原件请您务必在过户时携带至房管局。如有疑义，请致电客服4008100686";
	//过户成功 ok
	public static final String GHCG="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的不动产证办理成功，感谢您对大搜家的信任。如有任何问题，请致电客服4008100686";
	//商家申请服务完成后ok
	public static final String FWWCQR="【大搜家】尊敬的客户，您好！感谢您使用大搜家权证过户服务，您办理的'.$code0.'业务，所有流程已办理完毕，请在订单详情中进行服务完成确认，并对权证专员进行评价。如有疑义，请致电客服4008100686";
	//服务完成
	public static final String QRFW="【大搜家】尊敬的客户，您好！感谢您使用大搜家权证过户服务，本次服务已结束。如有疑义，请致电客服4008100686";
	//公正已受理ok
	public static final String GZYSL = "【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务。我是您的权证专员：'.$code0.'，联系电话'.$code1.'。您预约的公证时间为'.$code2.'，在'.$code3.'（地址），到公证处后请联系我。您需要准备的资料包括：夫妻双方的身份证、户口本、结婚证、房本原件，所有的原件请您务必在过户时携带至公证处。"
									+ "如有疑义，请致电客服4008100686";
	//已公正ok
	public static final String YGZ="尊敬的客户，您好！感谢您使用大搜家权证过户服务，您办理的房屋继承业务，已公正成功。如有疑义，请致电客服4008100686";
	//预约新产权证办理
	public static final String YYXCQBL="【大搜家】尊敬的客户，您好！感谢您选择大400-898-6868搜家为您服务。我是为您负责过户的权证专员'.$code0.'，联系电话'.$code1.'。为您预约的过户时间为'.$code2.'，在'.$code3.'（地址），到房管局(建委)后请联系我。您需要准备的资料包括：夫妻双方的身份证、户口本、结婚证、房本原件，所有的原件请您务必在过户时携带至房管局。如有疑义，请致电客服4008100686";
	//测绘结果已上传ok
	public static final String CHJGSC="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的测绘结果已上传。如有疑义，请致电客服4008100686";
	//新房产权办理完成ok
	public static final String XFCQBLWC="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的新房产证办理已完成，感谢您对大搜家的信任。如有疑义，请致电客服4008100686";
	//补证成功ok
	public static final String BZCG="【大搜家】尊敬的客户，您好！感谢您选择大搜家为您服务，您的补证已完成，感谢您对大搜家的信任。如有任何问题，请致电客服4008100686";
	//客户付款成功ok
	public static final String KHFKWC="【大搜家】'.$code0.'，您好。有客户对您办理的'.$code1.'服务进行了下单并缴费，请及时联系客户进行下一步操作。如有疑义，请致电客服4008100686";
	//权证供应商用户注册成功ok
	public static final String ZCCG="【大搜家】恭喜您成功入驻大搜家，成为大搜家权证专员。您的账号是'.$code0.'，初始密码是123456，请尽快修改密码。如有疑义，请致电客服4008100686";
	
	
	public static final String AGENT_AUDIT_SUCCESS = "【大搜家】恭喜您的账户已审核通过。成为大搜家独立经纪人，未来的日子我们一起努力，如有任何疑问请咨询400-898-6868转888";
	//pc快捷登录
	public static final String PC_LOGIN="【大搜家】尊敬的用户，您的验证码为'.$code.'，有效期为5分钟，如有疑虑请详询400-898-6868转888(客服电话)";
	//经纪人提交审核，通知管理员
	public static final String AUDIT_NOTICE = "【大搜家】有新的经纪人入住大搜家平台，姓名：'.$code0.'，电话：'.$code1.'请进行审核！";
	public static String getDrawingTemplate(String templete,String code){
		if(null != code){
			return templete.replace("'.$code.'", code);
		}
		return null ;
	}
	
	//多参数
	public static String getDrawingTemplateTwo(String templete,String code){
		if(null != code){
			String[] split = code.split("@_s_@");
			for (int i = 0; i < split.length; i++) {
				templete = templete.replace("'.$code"+i+".'", split[i]);
			}
			return templete;
		}
		return templete ;
	}
}

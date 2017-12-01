package com.dsj.modules.other.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.IsSuccessEnum;
import com.dsj.common.utils.sms.SMSUtil;
import com.dsj.modules.other.dao.SmsLogsDao;
import com.dsj.modules.other.po.SmsLogsPo;
import com.dsj.modules.other.service.SmsLogsService;
@Service
public class SmsLogsServiceImpl extends BaseServiceImpl<SmsLogsDao,SmsLogsPo> implements SmsLogsService {
	private static final Logger logger = LoggerFactory.getLogger(SmsLogsServiceImpl.class);
	

	@Override
	public Boolean saveLogsAndsend(String phone, String content) {
		Boolean b=true;
		SmsLogsPo smsLogs =new SmsLogsPo();
		
		smsLogs.setCreateTime(new Date());
		smsLogs.setPhone(phone);
		smsLogs.setContent(content);
		try {
			String result=SMSUtil.sendSMS(content,phone);
			if(StringUtils.isNotBlank(result)&&result.equals("0")){
				//smsLogs.setStatus(SmsStatusEnums.SUCESS.getValue());
				smsLogs.setIsSussess(IsSuccessEnum.YES.getValue());
			}else{
			//	smsLogs.setStatus(SmsStatusEnums.FAUILD.getValue());
				smsLogs.setIsSussess(IsSuccessEnum.NO.getValue());
				b=false;
			}
			//IsSuccessEnum
			saveDynamic(smsLogs);
		} catch (Exception e) {
			b=false;
			logger.error("发送短信错误",e.getMessage(),e);
		}
		return b;
	}
	
}

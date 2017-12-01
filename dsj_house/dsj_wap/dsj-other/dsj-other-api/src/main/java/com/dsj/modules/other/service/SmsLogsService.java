package com.dsj.modules.other.service;

import java.util.Map;

public interface SmsLogsService {
	
	/**
	 * 发送短信
	 * @param phone
	 * @param content
	 * @return
	 */
	Boolean saveLogsAndsend(String phone, String content);
	
}

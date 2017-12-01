package com.dsj.modules.oldHouseParser.po;

import com.dsj.common.entity.BaseEntity;
import java.util.Date;

/**
 *
 * @描述: PO.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:21.
 * @版本: 1.0 .
 */
public class IpPoolPo extends BaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	private String ip;		// ip
	private String port;		// 端口
	private Long time;		// 执行时间
	
	public IpPoolPo() {
		super();
	}

	public IpPoolPo(Long id){
		this();
	}
	

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
	public Long getTime() {
		return time;
	}

	public void setTime(Long time) {
		this.time = time;
	}
	
}
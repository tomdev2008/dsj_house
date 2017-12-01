package com.dsj.modules.oldHouseParser.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldHouseParser.po.ZydcOldhouseFlagPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-10-31 14:27:21.
 * @版本: 1.0 .
 */
public interface ZydcOldhouseFlagService extends BaseService<ZydcOldhouseFlagPo>{

	void deleteAll();
	List<ZydcOldhouseFlagPo> getCount(String loupanUrl);

	void insertZydc(ZydcOldhouseFlagPo zydc);

	ZydcOldhouseFlagPo getLastPo();
	
	/**
	 * 删除我爱我家
	 */
	void deleteWawjAll();
}
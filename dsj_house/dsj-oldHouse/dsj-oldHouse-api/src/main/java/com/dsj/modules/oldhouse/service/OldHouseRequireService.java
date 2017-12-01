package com.dsj.modules.oldhouse.service;

import com.dsj.common.service.BaseService;
import com.dsj.modules.oldhouse.po.OldHouseRequirePo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-06-27 17:13:07.
 * @版本: 1.0 .
 */
public interface OldHouseRequireService extends BaseService<OldHouseRequirePo>{

	void updateOldHouseRequire(String[] ids, int value);

}
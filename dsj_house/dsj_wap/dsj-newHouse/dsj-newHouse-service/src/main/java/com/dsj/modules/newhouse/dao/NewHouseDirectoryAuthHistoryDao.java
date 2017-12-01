package com.dsj.modules.newhouse.dao;

import java.util.HashMap;
import java.util.Map;

import com.dsj.common.core.dao.BaseDao;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;

/**
 *
 * @描述: DAO数据访问层接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHouseDirectoryAuthHistoryDao extends BaseDao<NewHouseDirectoryAuthHistoryPo> {

	NewHouseDirectoryAuthVo getVoById(Long id);

	
}
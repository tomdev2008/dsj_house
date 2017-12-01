package com.dsj.modules.oldhouse.service.impl;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.modules.oldhouse.dao.OldMasterAgentRecommendDao;
import com.dsj.modules.oldhouse.po.OldMasterAgentRecommendPo;
import com.dsj.modules.oldhouse.service.OldMasterAgentRecommendService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-18 18:23:40.
 * @版本: 1.0 .
 */
@Service
public class OldMasterAgentRecommendServiceImpl  extends BaseServiceImpl<OldMasterAgentRecommendDao,OldMasterAgentRecommendPo> implements OldMasterAgentRecommendService {

	@Override
	public void saveOrDel(Long id, Integer type,Long userId) {
		if(type==1){
			OldMasterAgentRecommendPo po=new OldMasterAgentRecommendPo();
			po.setUserId(userId);
			po.setOldMasterId(id);
			po.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
			po.setCreateTime(new Date());
			saveDynamic(po);
		}else if(type==2){
			deletegByUserIdOrMasterId(id,userId);
		}
	}
	
	private void deletegByUserIdOrMasterId(Long oldMasterId, Long userId) {
		dao.deletegByUserIdOrMasterId( oldMasterId,  userId);
	}

	@Override
	public void updateDeleteFlagByUserIdOrMasterId(Long oldMasterId, Long userId) {
		dao.updateDeleteFlagByUserIdOrMasterId(oldMasterId,userId);
	}
	
}
package com.dsj.modules.fw.service.impl;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwOrderService;
import com.dsj.modules.fw.service.FwTypeNodeService;
import com.dsj.modules.fw.service.OrderNodeAuthService;
import com.dsj.modules.fw.dao.OrderNodeAuthDao;
import com.dsj.modules.fw.enums.AuthStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderDetailPo;
import com.dsj.modules.fw.po.OrderNodeAuthPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
@Service
public class OrderNodeAuthServiceImpl  extends BaseServiceImpl<OrderNodeAuthDao,OrderNodeAuthPo> implements OrderNodeAuthService {

	@Autowired
	private FwOrderService fwOrderService;
	@Autowired
	private FwTypeNodeService fwTypeNodeService;
	@Autowired
	private FwOrderDetailService fwOrderDetailService;
	
	@Override
	public void saveOrderNodeAuth(OrderNodeAuthPo authPo,Long id, String username) {
		//添加审核记录
		authPo.setCreatePresonId(id);
		authPo.setCreateTime(new Date());
		authPo.setCreatePresonName(username);
		dao.insertDynamic(authPo);
		if(authPo.getStatus()==AuthStatusTypeEnum.AUTH_SUCCESS.getValue()){
			//更新进度
			fwOrderService.updateFwJd(authPo.getOrderDetailId(), authPo.getNodeId(), fwTypeNodeService.getById(authPo.getNodeId()).getNext(),id,username);
		}else{
			//拒绝
			FwOrderDetailPo fwOrderDetailPo = new FwOrderDetailPo();
			fwOrderDetailPo.setAuthStatus(AuthStatusTypeEnum.AUTH_FAIL.getValue());
			fwOrderDetailPo.setId(authPo.getOrderDetailId());
			fwOrderDetailService.updateDynamic(fwOrderDetailPo);
		}
		
	}

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}
	
}
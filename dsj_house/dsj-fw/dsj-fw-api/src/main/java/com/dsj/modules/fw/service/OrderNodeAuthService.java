package com.dsj.modules.fw.service;

import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.fw.po.OrderNodeAuthPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-08-30 17:09:03.
 * @版本: 1.0 .
 */
public interface OrderNodeAuthService extends BaseService<OrderNodeAuthPo>{

	void saveOrderNodeAuth(OrderNodeAuthPo authPo, Long id, String username);

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);


	
}
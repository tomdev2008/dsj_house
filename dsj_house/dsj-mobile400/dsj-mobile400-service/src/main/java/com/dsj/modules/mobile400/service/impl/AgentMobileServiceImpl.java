package com.dsj.modules.mobile400.service.impl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.mobile400.dao.AgentMobileDao;
import com.dsj.modules.mobile400.po.AgentMobilePo;
import com.dsj.modules.mobile400.service.AgentMobileService;
import com.dsj.modules.mobile400.vo.AgentMobileVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-15 15:08:50.
 * @版本: 1.0 .
 */
@Service
public class AgentMobileServiceImpl  extends BaseServiceImpl<AgentMobileDao,AgentMobilePo> implements AgentMobileService {

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> paramMap) {
		PageBean page = dao.listPage(pageParam, paramMap, "listNewPageCount", "listNewPageList");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			AgentMobileVo vo = (AgentMobileVo)object;
			if(vo!=null){
				String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
				vo.setCallSuccessLv(callSuccess);
				String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
				vo.setCallBusyLv(callBusy);
				String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
				vo.setCallNotLv(callNot);
			}
		}
		return page;
	}

	@Override
	public PageBean newPageList(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "newPageListCount", "newPageList");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			AgentMobileVo vo = (AgentMobileVo)object;
			if(vo!=null){
				String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
				vo.setCallSuccessLv(callSuccess);
				String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
				vo.setCallBusyLv(callBusy);
				String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
				vo.setCallNotLv(callNot);
			}else{
				page.setTotalCount(0);
				page.setPageCount(0);
				recordList.clear();
				page.setRecordList(recordList);
				break;
			}
		}
		return page;
	}

	@Override
	public PageBean agentYesterday(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "agentPageCount", "agentPageList");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			AgentMobileVo vo = (AgentMobileVo)object;
			if(vo!=null){
				String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
				vo.setCallSuccessLv(callSuccess);
				String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
				vo.setCallBusyLv(callBusy);
				String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
				vo.setCallNotLv(callNot);
			}
		}
		return page;
	}

	@Override
	public PageBean agentTotal(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "agentTotalCount", "agentTotalList");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			AgentMobileVo vo = (AgentMobileVo)object;
			if(vo!=null){
				String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
				vo.setCallSuccessLv(callSuccess);
				String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
				vo.setCallBusyLv(callBusy);
				String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
				vo.setCallNotLv(callNot);
			}
		}
		return page;
	}

	@Override
	public long saveToLeadAgent() {
		return dao.saveToLeadAgent();
	}
	
}
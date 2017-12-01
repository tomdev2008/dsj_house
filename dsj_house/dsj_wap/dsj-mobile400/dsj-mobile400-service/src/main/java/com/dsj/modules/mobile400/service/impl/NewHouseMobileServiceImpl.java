package com.dsj.modules.mobile400.service.impl;

import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.mobile400.service.NewHouseMobileService;
import com.dsj.modules.mobile400.vo.AgentMobileVo;
import com.dsj.modules.mobile400.vo.NewHouseMobileVo;
import com.dsj.modules.mobile400.dao.NewHouseMobileDao;
import com.dsj.modules.mobile400.po.NewHouseMobilePo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-11-16 14:47:02.
 * @版本: 1.0 .
 */
@Service
public class NewHouseMobileServiceImpl  extends BaseServiceImpl<NewHouseMobileDao,NewHouseMobilePo> implements NewHouseMobileService {

	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		 PageBean page = dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			NewHouseMobileVo vo = (NewHouseMobileVo)object;
			String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
			vo.setCallSuccessLv(callSuccess);
			String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
			vo.setCallBusyLv(callBusy);
			String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
			vo.setCallNotLv(callNot);
		}
		return page;
	}

	@Override
	public PageBean listTotalNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "newPageListCount", "newPageList");
			List<?> recordList = page.getRecordList();
			//获取格式化对象  
	        NumberFormat nt = NumberFormat.getPercentInstance();  
	        //设置百分数精确度2即保留两位小数  
	        nt.setMinimumFractionDigits(2);
			for (Object object : recordList) {
				NewHouseMobileVo vo = (NewHouseMobileVo)object;
				String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
				vo.setCallSuccessLv(callSuccess);
				String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
				vo.setCallBusyLv(callBusy);
				String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
				vo.setCallNotLv(callNot);
			}
			return page;
	}

	@Override
	public PageBean newHouseTotal(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "newHouseTotalCount", "newHouseTotalList");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			NewHouseMobileVo vo = (NewHouseMobileVo)object;
			String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
			vo.setCallSuccessLv(callSuccess);
			String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
			vo.setCallBusyLv(callBusy);
			String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
			vo.setCallNotLv(callNot);
		}
		return page;
	}

	@Override
	public PageBean newHouseYesterday(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "newHouseYesterdayCount", "newHouseYesterdayList");
		List<?> recordList = page.getRecordList();
		//获取格式化对象  
        NumberFormat nt = NumberFormat.getPercentInstance();  
        //设置百分数精确度2即保留两位小数  
        nt.setMinimumFractionDigits(2);
		for (Object object : recordList) {
			NewHouseMobileVo vo = (NewHouseMobileVo)object;
			String callSuccess = nt.format((double)vo.getCallSuccess()/vo.getCallCount());
			vo.setCallSuccessLv(callSuccess);
			String callBusy = nt.format((double)vo.getCallBusy()/vo.getCallCount());
			vo.setCallBusyLv(callBusy);
			String callNot = nt.format((double)vo.getCallNot()/vo.getCallCount());
			vo.setCallNotLv(callNot);
		}
		return page;
	}

	@Override
	public void saveYearterDayData() {
		dao.saveYearterDayData();
	}
	
}
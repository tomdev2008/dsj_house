package com.dsj.modules.fw.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.fw.dao.FwOrderDao;
import com.dsj.modules.fw.dao.FwuserComDao;
import com.dsj.modules.fw.dao.FwuserCommentDao;
import com.dsj.modules.fw.enums.EvaluateReviewStatusEnum;
import com.dsj.modules.fw.enums.EvaluateTypeEnum;
import com.dsj.modules.fw.enums.OrderStatusTypeEnum;
import com.dsj.modules.fw.po.FwOrderPo;
import com.dsj.modules.fw.po.FwuserComPo;
import com.dsj.modules.fw.po.FwuserCommentPo;
import com.dsj.modules.fw.service.FwOrderDetailService;
import com.dsj.modules.fw.service.FwuserCommentService;
import com.dsj.modules.fw.vo.FwDetailCommentVo;
import com.dsj.modules.fw.vo.FwOrderDetailVo;
import com.dsj.modules.fw.vo.FwuserCommentVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-24 14:22:54.
 * @版本: 1.0 .
 */
@Service
public class FwuserCommentServiceImpl  extends BaseServiceImpl<FwuserCommentDao,FwuserCommentPo> implements FwuserCommentService {
	
	@Autowired
	private FwuserComDao fwuserComDao;
	
	@Autowired
	private FwOrderDao fwOrderDao;
	
	@Autowired
	private FwOrderDetailService fwOrderDetailService;
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public PageBean evaluatePage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "evaluatePageCount","evaluatePage");
	}

	@Override
	public FwuserCommentVo getCommentByDetailId(HashMap<String, Object> hashMap) {
		return dao.getCommentByDetailId(hashMap);
	}

	@Override
	public void saveFwuserComment(FwuserCommentPo fwuserComment) {
		//往dsj_fwuser_comment中添加
		Integer ping = fwuserComment.getAttitude()+fwuserComment.getMajor()+fwuserComment.getSpeed();
		if(ping>=2 && ping<=6){
			fwuserComment.setType(EvaluateTypeEnum.HAO_PING.getValue());
		}else if(ping>=-2 && ping<=1){
			fwuserComment.setType(EvaluateTypeEnum.ZHONG_PING.getValue());
		}else if(ping>=-6 && ping<=-3){
			fwuserComment.setType(EvaluateTypeEnum.CHA_PING.getValue());
		}
		fwuserComment.setCreateTime(new Date());
		FwOrderDetailVo fwOrderDetailVo = fwOrderDetailService.getFwComment(fwuserComment.getOrderId());
		fwuserComment.setFwuserId(fwOrderDetailVo.getPropertyId().longValue());
		fwuserComment.setUserId(fwOrderDetailVo.getUserId().longValue());
		fwuserComment.setSpuId(fwOrderDetailVo.getSpuId());
		
		long commentId = dao.insert(fwuserComment);
		
		//修改订单表中评价的状态
		FwOrderPo fwOrderPo = new FwOrderPo();
		fwOrderPo.setId(fwuserComment.getOrderId());
		fwOrderPo.setStatus(OrderStatusTypeEnum.PRESON_COMMENTED.getValue());
		fwOrderPo.setReviewStatus(EvaluateReviewStatusEnum.YES.getValue());
		fwOrderPo.setReviewDate(new Date());
		fwOrderDao.updateDynamic(fwOrderPo);
		
		//往dsj_fwuser_comm中添加
		List<FwuserComPo> fwuserComList = new ArrayList<FwuserComPo>();
		String[] labelList = fwuserComment.getLabel().split(",");
		for (String idStr : labelList) {
			if (StringUtils.isNotBlank(idStr)) {
				FwuserComPo fwuserCom = new FwuserComPo();
				fwuserCom.setCommentId(commentId);
				fwuserCom.setEvaluateNum(Long.parseLong(idStr));
				fwuserComList.add(fwuserCom);
			}
		}
		fwuserComDao.insert(fwuserComList);
	}

	@Override
	public FwuserCommentPo getByOrderId(Long orderId) {
		return dao.getByOrderId(orderId);
	}

	@Override
	public PageBean listNewCommentPage(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean page = dao.listPage(pageParam, requestMap, "listNewCommentPageCount", "listNewCommentPage");
		List<?> recordList = page.getRecordList();
		for (Object object : recordList) {
			FwDetailCommentVo vo = (FwDetailCommentVo)object;
			if(null!=vo.getOrderCount()){
				vo.setOrderCount(vo.getDeal()+vo.getOrderCount());
			}else{
				vo.setOrderCount(vo.getDeal());
			}
			if(StringUtils.isNotBlank(vo.getLabel())){
				vo.setLableList(vo.getLabel().split(","));
			}
		}
		return page;
	}

	@Override
	public FwuserCommentVo getPingLv(Long id) {
		return dao.getPingLv(id);
	}

	@Override
	public FwuserCommentVo getPingLvBySpu(Long spuId) {
		return dao.getPingLvBySpu(spuId);
	}

	
}
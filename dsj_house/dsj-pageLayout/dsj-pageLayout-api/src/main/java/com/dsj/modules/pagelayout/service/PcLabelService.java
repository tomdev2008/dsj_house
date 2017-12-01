package com.dsj.modules.pagelayout.service;

import java.util.List;

import com.dsj.common.service.BaseService;
import com.dsj.modules.pagelayout.po.PcLabelPo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-17 11:04:17.
 * @版本: 1.0 .
 */
public interface PcLabelService extends BaseService<PcLabelPo>{
   /**
    * 新房标签查询
    * @return
    */
	List<PcLabelPo> getLableList();
	/**
	    * 二手房标签查询
	    * @return
	    */
    List<PcLabelPo> getLableOldList();


	
}
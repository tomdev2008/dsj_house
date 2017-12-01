package com.dsj.modules.pagelayout.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.pagelayout.service.WapLabelService;
import com.dsj.modules.pagelayout.vo.LabelTypeVo;
import com.dsj.modules.pagelayout.vo.WapIndexPageListVo;
import com.dsj.modules.pagelayout.vo.WapLabelVo;
import com.dsj.modules.pagelayout.vo.WeightAndTypeVo;
import com.dsj.modules.pagelayout.dao.WapLabelDao;
import com.dsj.modules.pagelayout.po.WapLabelPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-08-22 17:08:04.
 * @版本: 1.0 .
 */
@Service
public class WapLabelServiceImpl  extends BaseServiceImpl<WapLabelDao,WapLabelPo> implements WapLabelService {
	
	@Autowired 
	private WapLabelDao wapLabelDao;
	@Override
	public List<String> getTypeGroup() {
		return wapLabelDao.getTypeGroup();
	}
	@Override
	public List<LabelTypeVo> getType() {
		return wapLabelDao.getType();
	}
	@Override
	public List<WapLabelVo> getNewList(List<WapLabelPo> list) {
		List<WapLabelVo> resList = new ArrayList<WapLabelVo>();
		for(WapLabelPo item:list){
			WapLabelVo vo =new WapLabelVo();
			vo.setId(item.getId());
			vo.setName(item.getName());
			vo.setStatus(item.getStatus());
			if(item.getIncludeType()!=null){
				String[] str = item.getIncludeType().split(",");
				List<Integer> paramList = new ArrayList<Integer>();
				for(int i=0;i<str.length;i++){
					paramList.add(Integer.valueOf(str[i]));
				}
				List<WeightAndTypeVo> resultList = wapLabelDao.getWeightAndTypeVo(paramList);
				String[] w = item.getWeight().split(",");
				for(int j=0;j<resultList.size();j++){
					resultList.get(j).setWeight(Integer.valueOf(w[j]));
				}				
				vo.setTypeList(resultList);
			}
			resList.add(vo);
			
		}
		return resList;
	}
	@Override
	public List<WeightAndTypeVo> getWeightAndTypeVo(String types, String weights,int page) {
		List<WeightAndTypeVo> paramList = new ArrayList<WeightAndTypeVo>();
		if(!StringUtils.isBlank(types)){
			String[] typeArr = types.split(",");
			String[] weightArr = weights.split(",");
			for(int i=0;i<typeArr.length;i++){
				WeightAndTypeVo v = new WeightAndTypeVo();
				v.setPageFirst((page-1)*Integer.valueOf(weightArr[i]));
				v.setType(Integer.valueOf(typeArr[i]));
				v.setWeight(Integer.valueOf(weightArr[i]));
				paramList.add(v);
			}
		}
		return paramList;
	}
	@Override
	public List<WapIndexPageListVo> getWapIndexPageList(Map<String, Object> map) {
		return wapLabelDao.getWapIndexPageList(map);
	}
	
}
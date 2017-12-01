package com.dsj.modules.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.RoleChecked;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.system.service.CompanyService;
import com.dsj.modules.system.vo.RoleVo;
import com.dsj.modules.system.dao.CompanyDao;
import com.dsj.modules.system.enums.ServiceTypeEnums;
import com.dsj.modules.system.po.CompanyPo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-15 14:38:53.
 * @版本: 1.0 .
 */
@Service
public class CompanyServiceImpl  extends BaseServiceImpl<CompanyDao,CompanyPo> implements CompanyService {
	@Autowired
	private CompanyDao companyDao;
	
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
	}

	@Override
	public void insertCompany(CompanyPo company) {
		companyDao.insert(company);
		
	}

	@Override
	public List<RoleVo> serviceTypeList() {
		List<RoleVo> list = new ArrayList<RoleVo>();
		for (ServiceTypeEnums os : ServiceTypeEnums.values()) {
			RoleVo r = new RoleVo();
			r.setName(os.getServiceName());
			r.setNameCode(os.getValue().toString());
			r.setIsChecked(RoleChecked.UNCHECKED.getCode());
			list.add(r);
        }
		return list;
	}


	@Override
	public List<RoleVo> editPageServiceTypeList(String[] serviceTypes) {
		List<RoleVo> list = new ArrayList<RoleVo>();
		int flag = 0;
		for (ServiceTypeEnums os : ServiceTypeEnums.values()) {
			flag = 0;
			RoleVo r = new RoleVo();
			for(int i = 0;i<serviceTypes.length;i++){
				if(serviceTypes[i].equals(os.getValue().toString())){
					flag=1;
					r.setName(os.getServiceName());
					r.setNameCode(os.getValue().toString());
					r.setIsChecked(RoleChecked.CHECKED.getCode());
				}	
			}
			if(flag==0){
				r.setName(os.getServiceName());
				r.setNameCode(os.getValue().toString());
				r.setIsChecked(RoleChecked.UNCHECKED.getCode());
			}
			list.add(r);
        }
		return list;
	}
	
}
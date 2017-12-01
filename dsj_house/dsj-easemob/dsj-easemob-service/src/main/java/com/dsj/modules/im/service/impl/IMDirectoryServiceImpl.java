package com.dsj.modules.im.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.modules.im.dao.IMDirectoryDao;
import com.dsj.modules.im.po.IMDirectoryPo;
import com.dsj.modules.im.service.IMDirectoryService;
import com.dsj.modules.im.vo.IMDirectoryVo;

/**
 *
 * @描述: Service接口实现类.
 * @作者: wangjl.
 * @创建时间: 2017-07-20 18:17:27.
 * @版本: 1.0 .
 */
@Service("imDirectoryServiceImpl")
public class IMDirectoryServiceImpl  
	extends BaseServiceImpl<IMDirectoryDao,IMDirectoryPo> 
	implements IMDirectoryService {
	
	private final Logger LOGGER 
		= LoggerFactory.getLogger(IMDirectoryServiceImpl.class);

	/**
	 * 将楼盘维护人设为IM绑定楼盘经纪人.
	 * 
	 * @param code
	 * @param agentId
	 * @param person
	 * @return .
	 */
	@Override
	public void addIMDirectory(Long houseId, Long agentId, int personId, 
			int isDuty) {
		if (agentId != null) {
			IMDirectoryPo po = new IMDirectoryPo();
			po.setAgentId(agentId);
			po.setHouseId(houseId);
			po.setPosition(1);
			po.setIsDuty(isDuty);
			po.setCreatePerson(personId);
			po.setUpdatePerson(personId);
			addIMDirectory(po);
		}
	}
	
	/**
	 * 根据ID查找对象.
	 * 
	 * @param id
	 * @return IMDirectoryPo .
	 */
	@Override
	public IMDirectoryVo getVoById(long id) {
		return dao.getVoById(id);
	}
	
	/**
	 * 根据条件查询 listVoBy: <br/>
	 * 
	 * @param paramMap
	 * @return
	 */
	@Override
	public List<IMDirectoryVo> listVoBy(Map<String, Object> paramMap) {
		return dao.listVoBy(paramMap);
	}
	
	private void addIMDirectory(IMDirectoryPo po) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		IMDirectoryPo imDirectoryPo = null;
		IMDirectoryPo imDirectoryPo1 = null;
		IMDirectoryPo imDirectoryPo2 = null;
		
		paramMap.put("houseId", po.getHouseId());
		paramMap.put("agentId", po.getAgentId());
		imDirectoryPo1 = dao.getBy(paramMap);
		if (po.getIsDuty() == 1) {
			paramMap.clear();
			paramMap.put("houseId", po.getHouseId());
			paramMap.put("isDuty", po.getIsDuty());
			imDirectoryPo2 = dao.getBy(paramMap);
			if (imDirectoryPo2 == null) {
				imDirectoryPo = imDirectoryPo1;
			} else if (imDirectoryPo1 != null) {
				if (imDirectoryPo1.getAgentId() 
						!= imDirectoryPo2.getAgentId()) {
					imDirectoryPo2.setIsDuty(0);
					imDirectoryPo2.setUpdatePerson(po.getUpdatePerson());
					dao.updateDynamic(imDirectoryPo2);
					imDirectoryPo = imDirectoryPo1;
				} else {
					imDirectoryPo = imDirectoryPo2;
				}
			} else {
				imDirectoryPo = imDirectoryPo2;
			}
		} else {
			imDirectoryPo = imDirectoryPo1;
		}
		
		if (imDirectoryPo != null) {
			imDirectoryPo.setIsDuty(po.getIsDuty());
			imDirectoryPo.setAgentId(po.getAgentId());
			imDirectoryPo.setUpdatePerson(po.getUpdatePerson());
			dao.updateDynamic(imDirectoryPo);
		} else {
			paramMap.clear();
			paramMap.put("houseId", po.getHouseId());
			paramMap.put("isDuty", 0);
			List<IMDirectoryPo> list = dao.listBy(paramMap);
			if (!CollectionUtils.isEmpty(list)) {
				for (int i = 0; i < list.size(); i++) { 
					if (list.get(i).getPosition() == i + 1) {
						if (i == 9) {
							dao.deleteById(list.get(i).getId());
							break;
						}
						IMDirectoryPo po2 = list.get(i);
						po2.setPosition(po2.getPosition() + 1);
						po2.setUpdatePerson(po.getUpdatePerson());
						dao.updateDynamic(po2);
					} else {
						break;
					}
				}
				dao.insert(po);
			} else {
				dao.insert(po);
			}
		}
	}
	
	/**
	 * 获得绑定经纪人数量.
	 * 
	 * @param houseId
	 * @return
	 */
	public Long getIMDirectoryCount(Long houseId) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("houseId", houseId);
		return dao.getIMDirectoryCount(paramMap);
	}
	
}
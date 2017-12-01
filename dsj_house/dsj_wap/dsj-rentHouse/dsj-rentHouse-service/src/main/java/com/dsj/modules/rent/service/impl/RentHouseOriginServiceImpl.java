package com.dsj.modules.rent.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.RecommendEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.modules.other.enums.HouseTypeEnum;
import com.dsj.modules.other.enums.SubwayObjectTypeEnum;
import com.dsj.modules.other.service.SubwayObjService;
import com.dsj.modules.rent.dao.RentHouseOriginDao;
import com.dsj.modules.rent.enums.RentHouseStatusEnum;
import com.dsj.modules.rent.po.RentHouseOriginPo;
import com.dsj.modules.rent.service.RentHouseOriginService;
import com.dsj.modules.rent.vo.RentCountMapInfoVo;
import com.dsj.modules.rent.vo.RentHouseOriginVo;
import com.dsj.modules.rent.vo.WarrantOriginVo;
import com.dsj.modules.solr.service.RentHouseIndexService;
import com.dsj.modules.solr.vo.RentHouseIndexFiled;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-10 11:15:27.
 * @版本: 1.0 .
 */
@Service
public class RentHouseOriginServiceImpl  extends BaseServiceImpl<RentHouseOriginDao,RentHouseOriginPo> implements RentHouseOriginService {

	private final Logger LOGGER = LoggerFactory.getLogger(RentHouseOriginServiceImpl.class);
	
	@Autowired
	private RentHouseIndexService rentHouseIndexService;
	
	@Autowired
	private SubwayObjService subwayObjService;
	
	@Override
	public PageBean listRentOriginPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap,"listRentOriginPageCount","listRentOriginPage");
	}
	
	@Override
	public PageBean listAgentRentOriginPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap,"listAgentRentOriginPageCount","listAgentRentOriginPage");
	}

	@Override
	public void updateOriginStatus(String[] ids, Integer status) throws Exception {
		if (status == RentHouseStatusEnum.ON.getValue()) {
			//上架时存进solr中
			List<RentHouseIndexFiled> fieldsList = getRentHouseSolrByIds(ids);
			detail(fieldsList);
			rentHouseIndexService.addItemIndexs(fieldsList);
			dao.updateOriginStatus(ids,status);
		}else if (status == RentHouseStatusEnum.DOWN.getValue()) {
			//下架时删除solr数据
			for (int i = 0; i < ids.length; i++) {
				rentHouseIndexService.deleteItemIndex(ids[i], null);
			}
			dao.updateOriginStatus(ids,status);
		}
		
	}

	private void detail(List<RentHouseIndexFiled> fieldsList) {
		for (RentHouseIndexFiled r : fieldsList) {
			//计算楼层中低高层
			Integer n = r.getFloorNum();
			Integer m = r.getTotalFloors();
			if (n !=null && m != null) {
				if ( m/3 >=n ) {
					r.setFloorType(1);
				}else if(n > m/3 && n <= m*2/3 ) {
					r.setFloorType(2);
				}else {
					r.setFloorType(3);
				}
			}else {
				r.setFloorType(1);
			}
			//地铁
			String subwayline= subwayObjService.getLineBy(SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue(), r.getDicId());
			r.setSubwayline(","+subwayline+",");
			String subway= subwayObjService.getStationBy(SubwayObjectTypeEnum.HOUSE_DIRECTORY.getValue(), r.getDicId());
			r.setSubway(","+subway+",");
		}
	}

	private List<RentHouseIndexFiled> getRentHouseSolrByIds(String[] ids) {
		return dao.getRentHouseSolrByIds(ids);
	}

	@Override
	public void updateDeleteFlag(String[] ids, Integer value ,Long userId) {
		dao.updateDeleteFlag(ids,value,userId);
	}

	@Override
	public List<RentHouseOriginPo> getRentHouse(HashMap<String, Object> map) {
		return dao.getRentHouseList(map);
	}

	@Override
	public void updatePictureUrl(Long id, int size, String picUrl) {
		if (size == 0) {
			RentHouseOriginPo po = dao.getById(id);
			po.setPictureUrl(picUrl);
			dao.updateDynamic(po);
		}
	}

	@Override
	public List<RentHouseOriginVo> getRentHouseList() {
		return dao.getRentHouse();
	}

	@Override
	public void saveAgentOrigin(int houseType, Long houseId, Long userId, Long creatUser) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseType", houseType);
		map.put("houseId", houseId);
		map.put("userId", userId);
		map.put("isDuty", 1);
		map.put("creatUser", creatUser);
		map.put("recommend", RecommendEnum.UN_RECOMMEND.getValue());
		map.put("creatTime", new Date());
		dao.saveAgentOrigin(map);
	}

	@Override
	public void deleteAgentOrigin(int houseType, Long houseId) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseType", houseType);
		map.put("houseId", houseId);
		dao.deleteAgentOrigin(map);
	}

	@Override
	public RentHouseOriginVo getVoById(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return dao.getVoById(map);
	}

	@Override
	public PageBean listDetailOriginPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap,"listDetailOriginCount","listDetailOriginPage");
	}

	@Override
	public List<String> getSameVillageList(Map<String, Object> map) {
		return dao.getSameVillageList(map);
	}

	@Override
	public List<String> getSameTradeList(Map<String, Object> map) {
		return dao.getSameTradeList(map);
	}

	@Override
	public List<String> getSimilarList(Map<String, Object> map) {
		return dao.getSimilarList(map);
	}
	
	@Override
	public List<String> getLateList(Map<String, Object> map) {
		return dao.getLateList(map);
	}

	@Override
	public List<RentHouseOriginVo> findFollow(Map<String, Object> map) {
		
		return dao.findFollow(map);
	}

	@Override
	public long findFollowCount(Map<String, Object> map) {
		return dao.findFollowCount(map);
	}

	@Override
	public List<RentHouseOriginVo> lookHistory(Map<String, Object> map) {
		return dao.lookHistory(map);
	}

	@Override
	public long lookHistoryCount(Map<String, Object> map) {
		return dao.lookHistoryCount(map);
	}
	@Override
	public List<RentHouseOriginVo> getRecommendList(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseId", id);
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		map.put("status", RentHouseStatusEnum.ON.getValue());
		map.put("size", 6);
		return dao.getRecommendList(map);
	}

	@Override
	public void deleteRentRecommend(Long id) {
		dao.deleteRentRecommend(id);
	}

	@Override
	public void updateRentRecommend(Long id, List<String> ids) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseId", id);
		map.put("ids", ids);
		dao.updateRentRecommend(map);
	}

	@Override
	public Integer listCount(Map<String, Object> map) {
		return dao.listCount(map);
	}

	@Override
	public List<RentHouseOriginPo> selectByLimit(Map<String, Object> map) {
		return dao.selectByLimit(map);
	}

	@Override
	public List<RentCountMapInfoVo> getRentByCity(Map<String, Object> paramMap) {
		return dao.getRentByCity(paramMap);
	}

	@Override
	public List<RentCountMapInfoVo> getRentByCounty(Map<String, Object> paramMap) {
		return dao.getRentByCounty(paramMap);
	}

	@Override
	public List<RentCountMapInfoVo> getRentByTrade(Map<String, Object> paramMap) {
		return dao.getRentByTrade(paramMap);
	}

	@Override
	public List<RentHouseOriginVo> getRentHouseListPage() {
		return dao.getRentHouseListPage();
	}

	@Override
	public List<RentHouseOriginVo> findAgentRentHouse(Map<String, Object> map) {
		return dao.findAgentRentHouse(map);
	}

	@Override
	public int findAgentRentHouseCount(Map<String, Object> map) {
		return dao.findAgentRentHouseCount(map);
	}

	@Override
	public void updateOriginRecommend(String[] ids, Integer recommend, Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("recommend", recommend);
		map.put("houseId", ids[0]);
		map.put("userId", id);
		dao.updateOriginRecommend(map);
	}

	@Override
	public Integer getCountRecommend(Long usrId, Integer value) {
		Map<String, Object> map = new HashMap<>();
		map.put("recommend", value);
		map.put("houseType", HouseTypeEnum.RENT_ORIGIN.getValue());
		map.put("userId", usrId);
		return dao.getCountRecommend(map);
	}

	@Override
	public Integer getCountShow(Long id ) {
		Map<String, Object> map = new HashMap<>();
		map.put("agentId", id);
		return dao.getCountShow(map);
	}

	@Override
	public void saveAgentShow(String[] ids, Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseId", ids[0]);
		map.put("agentId", id);
		map.put("type", 3);//租房类型
		dao.saveAgentShow(map);
	}

	@Override
	public void deleteAgentShow(String[] ids, Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("houseId", ids[0]);
		map.put("agentId", id);
		map.put("type", 3);//租房类型
		dao.deleteAgentShow(map);
	}

	@Override
	public List<WarrantOriginVo> getWarrantList() {
		
		return dao.getWarrantList();
	}

	@Override
	public WarrantOriginVo getWarrant(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return dao.getWarrant(map);
	}

	@Override
	public void updateWarrant(WarrantOriginVo warrantOriginVo) {
		dao.updateWarrant(warrantOriginVo);
	}

	@Override
	public WarrantOriginVo getWarrantVo(Long id) {
		Map<String, Object> map = new HashMap<>();
		map.put("id", id);
		return dao.getWarrantVo(map);
	}

	@Override
	public void updateWarrantPage(WarrantOriginVo warrantOriginVo) {
		 dao.updateWarrantPage(warrantOriginVo);
	}

}

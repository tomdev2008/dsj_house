package com.dsj.modules.newhouse.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.YesNoEnum;
import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.json.JsonMapper;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.modules.comment.enums.HouseNewsStatusEnum;
import com.dsj.modules.comment.po.HouseNewsPo;
import com.dsj.modules.comment.service.HouseNewsService;
import com.dsj.modules.comment.vo.HouseNewsVo;
import com.dsj.modules.newhouse.dao.NewHouseDirectoryAuthDao;
import com.dsj.modules.newhouse.enums.NewHouseAuthStatusEnum;
import com.dsj.modules.newhouse.enums.NewHouseEditEnum;
import com.dsj.modules.newhouse.enums.NewHouseIsTrueEnum;
import com.dsj.modules.newhouse.enums.NewHouseOpenHandTimeTypeEnum;
import com.dsj.modules.newhouse.enums.NewHousePictureIsFirstEnum;
import com.dsj.modules.newhouse.enums.NewHouseSaleStatusEnum;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.newhouse.service.NewHouseOpenHandTimeAuthService;
import com.dsj.modules.newhouse.service.NewHousePictureAuthService;
import com.dsj.modules.newhouse.service.NewHouseSubwayAuthService;
import com.dsj.modules.newhouse.service.NewHouseTypeAuthService;
import com.dsj.modules.newhouse.service.NewHouseWyMsgAuthService;
import com.dsj.modules.newhouse.vo.AgentFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseAgentFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseAuthMsgVo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;
import com.dsj.modules.newhouse.vo.NewHouseFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseLikeVo;
import com.dsj.modules.newhouse.vo.NewHousePictureFrontVo;
import com.dsj.modules.newhouse.vo.NewHouseRecommendVo;
import com.dsj.modules.newhouse.vo.NewHouseTypeCountVo;
import com.dsj.modules.other.service.SubwayObjService;
import com.dsj.modules.solr.service.NewHouseIndexService;
import com.dsj.modules.solr.vo.newHouse.NewHouseIndexFiled;
import com.dsj.modules.system.service.AgentService;
import com.dsj.modules.system.service.UserService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-06-27 18:38:48.
 * @版本: 1.0 .
 */
@Service
public class NewHouseDirectoryAuthServiceImpl extends BaseServiceImpl<NewHouseDirectoryAuthDao, NewHouseDirectoryAuthPo>
		implements NewHouseDirectoryAuthService {

	private final Logger LOGGER = LoggerFactory.getLogger(NewHouseDirectoryAuthServiceImpl.class);

	@Autowired
	private NewHouseWyMsgAuthService newHouseWyMsgAuthService;

	@Autowired
	private NewHouseOpenHandTimeAuthService newHouseOpenHandTimeAuthService;

	@Autowired
	private NewHouseDirectoryAuthHistoryService NewHouseDirectoryAuthHistoryService;

	@Autowired
	private NewHousePictureAuthService newHousePictureAuthService;

	@Autowired
	private NewHouseTypeAuthService newHouseTypeAuthService;

	@Autowired
	private NewHouseSubwayAuthService newHouseSubwayAuthService;

	@Autowired
	private HouseNewsService houseNewsService;

	@Autowired
	private UserService userService;

	@Autowired
	private NewHouseIndexService newHouseIndexService;
	@Autowired
	private SubwayObjService subwayObjService;
	@Autowired
	private AgentService agentService;

	/**
	 * @描述: 楼盘列表
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean listPage = dao.listPage(pageParam, requestMap, "listNewPageCount", "listNewPage");
		List<?> recordList = listPage.getRecordList();
		for (Object object : recordList) {
			NewHouseDirectoryAuthVo vo = (NewHouseDirectoryAuthVo) object;
			NewHouseAuthMsgVo NewHouseAuthMsgVo = dao.getNowStatus(vo.getCode());
			if (null != NewHouseAuthMsgVo) {
				vo.setAuthStatus(NewHouseAuthMsgVo.getStatus());
				vo.setContent(NewHouseAuthMsgVo.getShMsg());
			}
		}
		return listPage;
	}

	/**
	 *
	 * @return
	 * @throws ParseException
	 * @描述: 添加楼盘
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public long saveOrUpdateNewHouse(HashMap<String, Object> newHouseMap, Long createPerson) throws ParseException {
		// 楼盘对象
		NewHouseDirectoryAuthPo po = (NewHouseDirectoryAuthPo) JsonMapper
				.fromJsonString(newHouseMap.get("newHousePo").toString(), NewHouseDirectoryAuthPo.class);

		// 地铁信息
		List<NewHouseSubwayAuthPo> subWayList = (List<NewHouseSubwayAuthPo>) JSONArray
				.toList(JSONArray.fromObject(newHouseMap.get("subWayList").toString()), NewHouseSubwayAuthPo.class);
		// 物业信息添加
		List<NewHouseWyMsgAuthPo> wyMsgList = (List<NewHouseWyMsgAuthPo>) JSONArray
				.toList(JSONArray.fromObject(newHouseMap.get("wyMsgList").toString()), NewHouseWyMsgAuthPo.class);
		// 开盘交房时间
		List<NewHouseOpenHandTimeAuthPo> openHandTimelist = new ArrayList<>();
		JSONArray openHandTimelists = JSONArray.fromObject(newHouseMap.get("openHandTimeList").toString());
		NewHouseOpenHandTimeAuthPo handTime = null;
		for (int i = 0; i < openHandTimelists.size(); i++) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			JSONObject dd = (JSONObject) openHandTimelists.get(i);
			handTime = new NewHouseOpenHandTimeAuthPo();
			handTime.setDescribes(dd.getString("describes"));
			if (StringUtils.isNotBlank(dd.getString("openHandTime"))) {
				handTime.setOpenHandTime(sdf.parse(dd.getString("openHandTime")));
			}
			handTime.setType(dd.getInt("type"));
			handTime.setWyType(dd.getLong("wyType"));
			handTime.setWyTypeName(dd.getString("wyTypeName"));
			openHandTimelist.add(handTime);
		}
		po.setAccuracy(po.getCoordinate().split(",")[0]);
		po.setDimension(po.getCoordinate().split(",")[1]);
		po.setUpdateTime(new Date());
		po.setAuthStatus(NewHouseAuthStatusEnum.WAIT_COMMIT.getValue());
		if (null == po.getId()) {
			// 添加楼盘对象
			po.setEdit(NewHouseEditEnum.YES.getValue());
			// po.setCode(CodeUtils.getYMDHMS4NumCode());
			po.setIsTure(NewHouseIsTrueEnum.NOUP.getValue());
			po.setCreatePreson(createPerson);
			po.setCreateTime(new Date());
			dao.insertNewDynamic(po);
			long newHouseId = po.getId();
			// 添加地铁信息
			if (!subWayList.isEmpty()) {
				newHouseSubwayAuthService.saveList(po.getId(), subWayList);
			}
			// 添加物业信息
			newHouseWyMsgAuthService.saveList(newHouseId, wyMsgList);
			// 添加开盘时间
			newHouseOpenHandTimeAuthService.saveList(newHouseId, openHandTimelist);
			return newHouseId;
		} else {
			return updateNewHouse(po, createPerson, wyMsgList, openHandTimelist, subWayList);
		}
	}

	/**
	 *
	 * @return
	 * @描述: 修改新房信息
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	public long updateNewHouse(NewHouseDirectoryAuthPo po, Long createPerson, List<NewHouseWyMsgAuthPo> wyMsgList,
			List<NewHouseOpenHandTimeAuthPo> openHandTimelist, List<NewHouseSubwayAuthPo> subWayList) {
		NewHouseDirectoryAuthPo authPo = dao.getById(po.getId());

		// 如果是已上架
		if (authPo.getIsTure() == NewHouseIsTrueEnum.UP.getValue()) {
			// 如果修改的是不展示数据
			if (po.getEdit() == NewHouseEditEnum.NO.getValue()) {
				po.setUpdatePreson(createPerson);
				dao.updateDynamic(po);

				newHouseWyMsgAuthService.deleteByNewHouseId(po.getId());
				newHouseOpenHandTimeAuthService.deleteByNewHouseId(po.getId());
				newHouseSubwayAuthService.deleteByNewHouseId(po.getId());
				// 添加物业信息
				newHouseWyMsgAuthService.saveList(po.getId(), wyMsgList);

				// 添加地铁信息
				if (!subWayList.isEmpty()) {
					newHouseSubwayAuthService.saveList(po.getId(), subWayList);
				}

				// 添加开盘时间
				newHouseOpenHandTimeAuthService.saveList(po.getId(), openHandTimelist);
			} else {
				// 如果修改的是展示中数据

				// 如果是浏览器回退再修改.删除第一次生成的
				HashMap<String, Object> hashMap = new HashMap<String, Object>();
				hashMap.put("edit", 2);
				hashMap.put("code", authPo.getCode());
				NewHouseDirectoryAuthPo newHouseDirectoryAuthPo = dao.getBy(hashMap);
				long newHouseId;
				if (null != newHouseDirectoryAuthPo) {
					po.setEdit(NewHouseEditEnum.NO.getValue());
					po.setCode(authPo.getCode());
					po.setCreatePreson(createPerson);
					po.setUpdatePreson(createPerson);
					po.setCreateTime(new Date());
					po.setIsTure(NewHouseIsTrueEnum.UP.getValue());
					dao.insertNewDynamic(po);
					newHouseId = po.getId();
					// 添加物业信息
					newHouseWyMsgAuthService.saveList(newHouseId, wyMsgList);
					// 添加地铁信息
					if (!subWayList.isEmpty()) {
						newHouseSubwayAuthService.saveList(po.getId(), subWayList);
					}
					// 添加开盘时间
					newHouseOpenHandTimeAuthService.saveList(newHouseId, openHandTimelist);
					// 添加相册图片 第一次编辑过的
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("objectId", newHouseDirectoryAuthPo.getId());
					List<NewHousePictureAuthPo> pictureList = newHousePictureAuthService.listBy(map);
					for (NewHousePictureAuthPo newHousePictureAuthPo : pictureList) {
						newHousePictureAuthPo.setObjectId(newHouseId);
						newHousePictureAuthPo.setUpdatePreson(createPerson);
						newHousePictureAuthPo.setUpdateDate(new Date());
						newHousePictureAuthService.saveDynamic(newHousePictureAuthPo);
					}
					// 添加户型
					map.put("dicId", newHouseDirectoryAuthPo.getId());
					List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.listBy(map);
					for (NewHouseTypeAuthPo newHouseTypeAuthPo : houseTypeList) {
						newHouseTypeAuthPo.setDicId(newHouseId);
						newHouseTypeAuthPo.setUpdateDate(new Date());
						newHouseTypeAuthPo.setUpdatePreson(createPerson);
						newHouseTypeAuthService.saveDynamic(newHouseTypeAuthPo);
					}

					// 删除楼盘
					dao.deleteById(newHouseDirectoryAuthPo.getId());
					// 删除物业信息
					newHouseWyMsgAuthService.deleteByNewHouseId(newHouseDirectoryAuthPo.getId());
					// 删除开盘交房时间
					newHouseOpenHandTimeAuthService.deleteByNewHouseId(newHouseDirectoryAuthPo.getId());
					// 删除地铁
					newHouseSubwayAuthService.deleteByNewHouseId(newHouseDirectoryAuthPo.getId());
					// 删除图片
					newHousePictureAuthService.deleteByNewHouseId(newHouseDirectoryAuthPo.getId());
					// 删除物业类型
					newHouseTypeAuthService.deleteByNewHouseId(newHouseDirectoryAuthPo.getId());
				} else {
					po.setEdit(NewHouseEditEnum.NO.getValue());
					po.setCode(authPo.getCode());
					po.setCreatePreson(createPerson);
					po.setUpdatePreson(createPerson);
					po.setCreateTime(new Date());
					po.setIsTure(NewHouseIsTrueEnum.UP.getValue());
					dao.insertNewDynamic(po);
					newHouseId = po.getId();
					// 添加物业信息
					newHouseWyMsgAuthService.saveList(newHouseId, wyMsgList);
					// 添加地铁信息
					if (!subWayList.isEmpty()) {
						newHouseSubwayAuthService.saveList(po.getId(), subWayList);
					}
					// 添加开盘时间
					newHouseOpenHandTimeAuthService.saveList(newHouseId, openHandTimelist);
					// 添加相册图片
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("objectId", authPo.getId());
					List<NewHousePictureAuthPo> pictureList = newHousePictureAuthService.listBy(map);
					for (NewHousePictureAuthPo newHousePictureAuthPo : pictureList) {
						newHousePictureAuthPo.setObjectId(newHouseId);
						newHousePictureAuthPo.setUpdatePreson(createPerson);
						newHousePictureAuthPo.setUpdateDate(new Date());
						newHousePictureAuthService.saveDynamic(newHousePictureAuthPo);
					}
					// 添加户型
					map.put("dicId", authPo.getId());
					List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.listBy(map);
					for (NewHouseTypeAuthPo newHouseTypeAuthPo : houseTypeList) {
						newHouseTypeAuthPo.setDicId(newHouseId);
						newHouseTypeAuthPo.setUpdateDate(new Date());
						newHouseTypeAuthPo.setUpdatePreson(createPerson);
						newHouseTypeAuthService.saveDynamic(newHouseTypeAuthPo);
					}
				}

				return newHouseId;
			}

		} else {
			po.setUpdatePreson(createPerson);
			po.setCreatePreson(createPerson);
			dao.update(po);
			// 删除之前的相关数据
			newHouseWyMsgAuthService.deleteByNewHouseId(po.getId());
			newHouseOpenHandTimeAuthService.deleteByNewHouseId(po.getId());
			newHouseSubwayAuthService.deleteByNewHouseId(po.getId());
			// 添加物业信息
			newHouseWyMsgAuthService.saveList(po.getId(), wyMsgList);

			// 添加地铁信息
			if (!subWayList.isEmpty()) {
				newHouseSubwayAuthService.saveList(po.getId(), subWayList);
			}

			// 添加开盘时间
			newHouseOpenHandTimeAuthService.saveList(po.getId(), openHandTimelist);
		}
		return po.getId();
	}

	/**
	 *
	 * @return
	 * @描述: 修改查询
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public NewHouseDirectoryAuthVo getVoById(Long id) {
		NewHouseDirectoryAuthVo vo = dao.getVoById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", vo.getId());
		vo.setWyMsgList(newHouseWyMsgAuthService.listBy(map));
		vo.setSubWayList(newHouseSubwayAuthService.listNewBy(map));
		String wyType = vo.getWyType();
		if (StringUtils.isNotBlank(wyType)) {
			String[] wyStr = wyType.split(",");
			map.put("type", NewHouseOpenHandTimeTypeEnum.OPEN.getValue());
			ArrayList<List<NewHouseOpenHandTimeAuthPo>> list1 = new ArrayList<List<NewHouseOpenHandTimeAuthPo>>();
			for (String string : wyStr) {
				map.put("wyType", string);
				List<NewHouseOpenHandTimeAuthPo> timeList = newHouseOpenHandTimeAuthService.listBy(map);
				list1.add(timeList);
			}
			vo.setOpenTimeList(list1);
			map.put("type", NewHouseOpenHandTimeTypeEnum.HAND.getValue());
			ArrayList<List<NewHouseOpenHandTimeAuthPo>> list2 = new ArrayList<List<NewHouseOpenHandTimeAuthPo>>();
			for (String string : wyStr) {
				map.put("wyType", string);
				List<NewHouseOpenHandTimeAuthPo> timeList = newHouseOpenHandTimeAuthService.listBy(map);
				list2.add(timeList);
			}
			vo.setHandTimeList(list2);
		}

		if (vo.getPhone() != null) {
			vo.setPhoneList(vo.getPhone().split(","));
		}
		if (vo.getDiscount() != null) {
			vo.setDiscountList(vo.getDiscount().split(","));
		}
		if (vo.getPrep() != null) {
			vo.setPrepList(vo.getPrep().split(","));
		}

		return vo;
	}

	/**
	 *
	 * @return
	 * @throws Exception
	 * @描述: 提交审核
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void updateNewHouseExamine(Long id, Long commitPerson) throws Exception {
		LOGGER.info("楼盘提交提交审核,id为{}",id);
		// 1 修改本条数据状态
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("authStatus", NewHouseAuthStatusEnum.WAIT_AUTH.getValue());
		paramMap.put("commitPerson", commitPerson);
		paramMap.put("commitTime", new Date());
		paramMap.put("id", id);
		dao.updateNewHouseExamine(paramMap);

		// 2提交审核的数据插入审核历史表
		NewHouseDirectoryAuthPo directoryAuthPo = dao.getById(id);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", id);
		List<NewHouseWyMsgAuthPo> WyMsgList = newHouseWyMsgAuthService.listBy(map);
		List<NewHouseOpenHandTimeAuthPo> openHandTimeList = newHouseOpenHandTimeAuthService.listBy(map);
		map.put("objectId", id);
		List<NewHousePictureAuthPo> pictureList = newHousePictureAuthService.listBy(map);
		map.put("dicId", id);
		List<NewHouseTypeAuthPo> houseTypeList = newHouseTypeAuthService.listBy(map);
		List<NewHouseSubwayAuthPo> subwayList = newHouseSubwayAuthService.listBy(map);
		NewHouseDirectoryAuthHistoryService.saveNewHouseAuthHistory(directoryAuthPo, WyMsgList, openHandTimeList,
				houseTypeList, pictureList, subwayList);

	}

	/**
	 * @return
	 * @描述: 楼盘设置封面图
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void updateFengMianImage(Long id) {
		dao.updateFengMianImage(id);
	}

	/**
	 * @return
	 * @描述: 楼盘删除
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void updateDeleteFlagNewHouses(List<Integer> ids, Long id) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", ids);
		hashMap.put("updatePreson", id);
		hashMap.put("isTrue", NewHouseIsTrueEnum.DELETE.getValue());
		dao.updateDeleteFlagNewHouses(hashMap);
	}

	/**
	 *
	 * @return
	 * @描述: 下架楼盘
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void updateIsTrueNewHouses(List<Integer> ids, Long id) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("list", ids);
		hashMap.put("updatePreson", id);
		hashMap.put("isTrue", NewHouseIsTrueEnum.DOWN.getValue());
		dao.updateIsTrueNewHouses(hashMap);
	}

	@Override
	public NewHouseDirectoryAuthPo getWyType(Long id) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		return dao.selectNewHouseDirectory(paramMap);
	}

	@Override
	public PageBean listDirectoryPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewHouseCount", "listNewHouseList");
	}

	/**
	 *
	 * @return
	 * @描述: 新房审核
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void saveNewHousesAuth(List<NewHouseDirectoryAuthPo> list, long authPerson) throws Exception {
		for (NewHouseDirectoryAuthPo po : list) {

			// 审核楼盘
			po.setAuthPerson(authPerson);
			po.setAuthTime(new Date());
			if (po.getAuthStatus() == NewHouseAuthStatusEnum.AUTH_SUCCESS.getValue()) {
				po.setIsTure(NewHouseIsTrueEnum.UP.getValue());
			}
			dao.updateDynamic(po);

			// 修改历史表状态
			NewHouseDirectoryAuthPo directoryAuthPo = dao.getById(po.getId());
			String code = directoryAuthPo.getCode();
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("code", code);
			map.put("authStatus", NewHouseAuthStatusEnum.WAIT_AUTH.getValue());
			NewHouseDirectoryAuthHistoryPo newHouseDirectoryAuthHistoryPo = NewHouseDirectoryAuthHistoryService
					.getBy(map);
			newHouseDirectoryAuthHistoryPo.setAuthPerson(po.getAuthPerson());
			newHouseDirectoryAuthHistoryPo.setAuthTime(po.getAuthTime());
			newHouseDirectoryAuthHistoryPo.setContent(po.getContent());
			newHouseDirectoryAuthHistoryPo.setIsTure(po.getIsTure());
			newHouseDirectoryAuthHistoryPo.setAuthStatus(po.getAuthStatus());
			NewHouseDirectoryAuthHistoryService.updateDynamic(newHouseDirectoryAuthHistoryPo);

			// 审核通过(已上架楼盘操作)
			if (po.getAuthStatus() == NewHouseAuthStatusEnum.AUTH_SUCCESS.getValue()) {
				deleteEditNewHouse(directoryAuthPo);
			}
		}
	}

	/**
	 *
	 * @return
	 * @描述: 删除临时数据
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public void deleteEditNewHouse(NewHouseDirectoryAuthPo authPo) {
		// 如果新房是已上架的新房
		if (authPo.getEdit() == NewHouseEditEnum.NO.getValue()) {

			Long editId = authPo.getId();
			// 获取前台上架中的新房
			HashMap<String, Object> hashMap = new HashMap<String, Object>();
			hashMap.put("edit", NewHouseEditEnum.YES.getValue());
			hashMap.put("code", authPo.getCode());
			NewHouseDirectoryAuthPo editYesPo = dao.getBy(hashMap);

			// 修改后的楼盘 更改成上架中的楼盘
			authPo.setId(editYesPo.getId());
			authPo.setEdit(NewHouseEditEnum.YES.getValue());
			authPo.setCommitTime(editYesPo.getCommitTime());
			authPo.setCommitPerson(editYesPo.getCommitPerson());
			authPo.setCreatePreson(editYesPo.getCreatePreson());
			authPo.setCreateTime(editYesPo.getCreateTime());

			authPo.setEvelopersPerson(editYesPo.getEvelopersPerson());
			authPo.setMaintainPerson(editYesPo.getMaintainPerson());
			// 把之前的楼盘修改成现在的数据
			dao.updateTwo(authPo);

			// 删除编辑的楼盘
			dao.deleteById(editId);

			// 删除后编辑的新房相关的其他表
			newHouseWyMsgAuthService.deleteByEditYesByNewHouseId(editYesPo.getId(), editId);

			newHouseOpenHandTimeAuthService.deleteByEditYesByNewHouseId(editYesPo.getId(), editId);

			newHousePictureAuthService.deleteByEditYesByNewHouseId(editYesPo.getId(), editId);

			newHouseTypeAuthService.deleteByEditYesByNewHouseId(editYesPo.getId(), editId);

			newHouseSubwayAuthService.deleteByEditYesByNewHouseId(editYesPo.getId(), editId);

		}
	}

	@Override
	public int updateNewHouse(Long newHouseId, Long agentCode) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("maintainPerson", agentCode);
		paramMap.put("id", newHouseId);
		paramMap.put("isBind", 1);
		return dao.update(paramMap);
	}

	@Override
	public int updateNewHouseUn(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("maintainPerson", null);
		paramMap.put("id", id);
		paramMap.put("isBind", 0);
		return dao.update(paramMap);
	}

	@Override
	public PageBean listEveloperCountPage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listEveloperCount", "listEveloperList");
	}

	@Override
	public int updateEvelopers(Long evelopersId, Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("evelopersPerson", id);
		paramMap.put("id", evelopersId);
		return dao.updateEvelopers(paramMap);
	}

	@Override
	public int updateEveloper(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("evelopersPerson", null);
		paramMap.put("id", id);
		return dao.updateEvelopers(paramMap);
	}

	@Override
	public PageBean listNewHousePage(PageParam pageParam, Map<String, Object> requestMap) {
		return dao.listPage(pageParam, requestMap, "listNewHousePageCount", "listNewHousePageList");
	}

	/**
	 *
	 * @return
	 * @描述: 楼盘基本信息回显
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public NewHouseDirectoryAuthVo getVoBy(HashMap<String, Object> hashMap) {
		NewHouseDirectoryAuthVo vo = dao.getVoBy(hashMap);
		if (vo == null) {
			return null;
		} else {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("houseId", vo.getId());
			List<NewHouseWyMsgAuthPo> wyMsgList = newHouseWyMsgAuthService.listBy(map);
			vo.setWyMsgList(wyMsgList);
			vo.setSubWayList(newHouseSubwayAuthService.listNewBy(map));

			String wyType = vo.getWyType();
			if (StringUtils.isNotBlank(wyType)) {
				String[] wyStr = wyType.split(",");
				map.put("type", NewHouseOpenHandTimeTypeEnum.OPEN.getValue());
				ArrayList<List<NewHouseOpenHandTimeAuthPo>> list1 = new ArrayList<List<NewHouseOpenHandTimeAuthPo>>();
				for (String string : wyStr) {
					map.put("wyType", string);
					List<NewHouseOpenHandTimeAuthPo> timeList = newHouseOpenHandTimeAuthService.listBy(map);
					list1.add(timeList);
				}
				vo.setOpenTimeList(list1);

				map.put("type", NewHouseOpenHandTimeTypeEnum.HAND.getValue());
				ArrayList<List<NewHouseOpenHandTimeAuthPo>> list2 = new ArrayList<List<NewHouseOpenHandTimeAuthPo>>();
				for (String string : wyStr) {
					map.put("wyType", string);
					List<NewHouseOpenHandTimeAuthPo> timeList = newHouseOpenHandTimeAuthService.listBy(map);
					list2.add(timeList);
				}
				vo.setHandTimeList(list2);
			}

			if (vo.getPhone() != null) {
				vo.setPhoneList(vo.getPhone().split(","));
			}
			if (vo.getDiscount() != null) {
				vo.setDiscountList(vo.getDiscount().split(","));
			}
			if (vo.getPrep() != null) {
				vo.setPrepList(vo.getPrep().split(","));
			}
			return vo;
		}
	}

	@Override
	public String getNamesByIds(String ids) {
		return dao.getNamesByIds(ids);
	}

	@Override
	public PageBean listAgentNewHousePage(PageParam pageParam, Map<String, Object> requestMap) {

		return dao.listPage(pageParam, requestMap, "AgentNewHousePageCount", "AgentNewHousePageList");
	}

	@Override
	public List<NewHouseDirectoryAuthVo> findPictureList() {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pictureFrist", NewHousePictureIsFirstEnum.YES.getValue());
		paramMap.put("isTure", NewHouseIsTrueEnum.UP.getValue());
		return dao.getPictureList(paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> findPicture(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("pictureFrist", NewHousePictureIsFirstEnum.YES.getValue());
		paramMap.put("lableId", id);
		return dao.getPicture(paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> getPictureList(Long id) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("lableId", id);
		return dao.getPictureLists(paramMap);
	}

	@Override
	public PageBean listAgentNewHouseResponPage(PageParam pageParam, Map<String, Object> requestMap) {
		PageBean listPage = dao.listPage(pageParam, requestMap, "AgentNewHouseResponPageCount",
				"AgentNewHouseResponPageList");
		List<?> recordList = listPage.getRecordList();
		for (Object object : recordList) {
			NewHouseDirectoryAuthVo vo = (NewHouseDirectoryAuthVo) object;
			NewHouseAuthMsgVo NewHouseAuthMsgVo = dao.getNowStatus(vo.getCode());
			if (null != NewHouseAuthMsgVo) {
				vo.setAuthStatus(NewHouseAuthMsgVo.getStatus());
				vo.setContent(NewHouseAuthMsgVo.getShMsg());
			}
		}
		return listPage;
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getAuthHousesByAgentId(Map<String, Object> paraMap) {
		return dao.getAuthHousesByAgentId(paraMap);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getIdAndName(String loupanIds) {

		return dao.getIdAndName(loupanIds);
	}

	// 前端
	/**
	 *
	 * @return NewHouseFrontVo
	 * @param newHouseId
	 * @描述: 前端新房详情
	 * @作者: gaocj
	 * @创建时间: 2017-06-27 18:38:48.
	 * @版本: 1.0 .
	 */
	@Override
	public NewHouseFrontVo getIndexNewHouseFrontVo(Long id) {
		// 基本信息
		NewHouseFrontVo vo = dao.getNewHouseFrontVo(id);
		// 楼盘特点
		if (StringUtils.isNotBlank(vo.getDicTraitName())) {
			vo.setDicTraitList(vo.getDicTraitName().split(","));
		}
		if (vo.getAuthTime() != null) {
			vo.setUpdateTimeName(DateUtils.date2String(vo.getAuthTime()));
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", id);

		List<NewHouseWyMsgAuthPo> wyMsgList = newHouseWyMsgAuthService.listBy(map);
		vo.setWyMsgList(wyMsgList);

		HashMap<String, String> priceMap = getPrice(wyMsgList, vo.getAroundPriceMin(), vo.getAroundPriceMax());
		String indexPrice = priceMap.get("indexPrice");
		String pricedw = priceMap.get("pricedw");
		String priceName = priceMap.get("priceName");
		if (StringUtils.isNotBlank(indexPrice)) {
			vo.setIndexPrice((int) (Double.parseDouble(indexPrice)));
			vo.setPricedw(pricedw);
			vo.setPriceName(priceName);
		}

		// 开盘时间
		map.put("houseId", id);
		NewHouseOpenHandTimeAuthPo handTimeAuthPo = newHouseOpenHandTimeAuthService.getNewBy(map);
		if(null == handTimeAuthPo){
			handTimeAuthPo = newHouseOpenHandTimeAuthService.getNewBy2(map);
		}
		if (null != handTimeAuthPo && null != handTimeAuthPo.getOpenHandTime()) {
			String ymdDate = DateUtils.getYMDDate(handTimeAuthPo.getOpenHandTime());
			vo.setNewOpenTime(handTimeAuthPo.getWyTypeName() + " " + handTimeAuthPo.getDescribes() + " " + ymdDate);
		}

		HashMap<String, Object> map1 = new HashMap<String, Object>();
		map1.put("delFlag", DeleteStatusEnum.NDEL.getValue());
		map1.put("upDownLine", HouseNewsStatusEnum.UP.getValue());
		map1.put("houseId", id);

		// 楼盘动态
		HouseNewsVo houseNewsPo = houseNewsService.getOneBy(map1);
		Long houseNewsCount = houseNewsService.getHouseNewsCountBy(map1);
		vo.setNewsPo(houseNewsPo);
		vo.setHouseNewsCount(houseNewsCount);

		// 户型数量
		Integer fiveAndUp = 0;
		map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<NewHouseTypeCountVo> houseTypeList = newHouseTypeAuthService.getHouseTypeCount(map);
		List<NewHouseTypeCountVo> newHouseTypeList = new ArrayList<NewHouseTypeCountVo>();
		for (NewHouseTypeCountVo newHouseTypeCountVo : houseTypeList) {
			if (newHouseTypeCountVo.getRoom() < 5) {
				newHouseTypeList.add(newHouseTypeCountVo);
			} else {
				fiveAndUp += newHouseTypeCountVo.getCount();
				;
			}
		}
		if (fiveAndUp != 0) {
			NewHouseTypeCountVo fiveNewHouseType = new NewHouseTypeCountVo();
			fiveNewHouseType.setRoom(5);
			fiveNewHouseType.setRoomName("五居室及以上");
			fiveNewHouseType.setCount(fiveAndUp);
			newHouseTypeList.add(fiveNewHouseType);
		}
		vo.setHouseTypeList(newHouseTypeList);

		// 户型
		List<NewHouseTypeAuthPo> newHouseTypeListWap = newHouseTypeAuthService.getByHouseId(id);
		vo.setNewHouseTypeList(newHouseTypeListWap);

		// 楼盘户型字符串
		String houseTypeStrPc = "";
		String houseTypeStr = "";
		Integer count = 0;
		for (int j = 0; j < newHouseTypeList.size(); j++) {
			NewHouseTypeCountVo newHouseTypeCountVo = newHouseTypeList.get(j);
			houseTypeStrPc += "、<a href = '"+ConfigUtils.instance.getBasePath()+"/front/newHouse/house_type?id="+id+"#houseType"+j+"'>" + newHouseTypeCountVo.getRoomName() + "(" + newHouseTypeCountVo.getCount() + ")</a>";
			count = count + newHouseTypeCountVo.getCount();
		}
		if (StringUtils.isNotBlank(houseTypeStrPc)) {
			houseTypeStrPc = houseTypeStrPc.substring(1);
		}
		for (NewHouseTypeCountVo newHouseTypeCountVo : newHouseTypeList) {
			houseTypeStr += "、" + newHouseTypeCountVo.getRoomName() + "(" + newHouseTypeCountVo.getCount() + ")";
		}
		if (StringUtils.isNotBlank(houseTypeStr)) {
			houseTypeStr = houseTypeStr.substring(1);
		}
		vo.setHouseTypeMsg(houseTypeStr);
		vo.setHouseTypeStrPc(houseTypeStrPc);
		vo.setHouseTypeTotalCount(count);

		// 推荐经纪人 1楼主. 2,3 IM前2名
		map.put("limitNum", 3);
		//
		List<AgentFrontVo> agentList = agentService.listByMap(map);
		vo.setAgentVoList(agentList);
		for (AgentFrontVo agentVo : agentList) {
			if (agentVo.getIsDuty() != null && agentVo.getIsDuty() == 1) {
				vo.setAgentVo(agentVo);
			}
		}

		// 猜你喜欢
		List<NewHouseLikeVo> likeList = dao.getLikeNewHouse(id);
		vo.setLikeList(likeList);

		String imageUrl = "";
		Integer imageSize = 0;
		List<NewHousePictureFrontVo> pics = newHousePictureAuthService.getPictureList(id);
		if (pics != null && pics.size() > 0) {
			if (StringUtils.isNotBlank(pics.get(0).getImgStr())) {
				imageUrl = pics.get(0).getImgStr().split(",")[0];
			}
			for (NewHousePictureFrontVo pic : pics) {
				imageSize += pic.getImgList().length;
			}
		}
		vo.setImageSize(imageSize);
		vo.setImageUrl(imageUrl);
		return vo;
	}
	
	public List<NewHouseLikeVo> getLikeList(Long id){
		List<NewHouseLikeVo> likeList = dao.getLikeNewHouse(id);
		return likeList;
	} 

	public HashMap<String, String> getPrice(List<NewHouseWyMsgAuthPo> wyMsgList, String min, String max) {
		// 首页展示价格 1均价 2总价 3周边单价
		String indexPrice = "";
		String priceName = "";
		String pricedw = "";
		// 多个物业类型
		if (null != wyMsgList && wyMsgList.size() > 1) {
			ArrayList<Double> list = new ArrayList<Double>();
			Double totalReferencePriceMin = 0.0;
			Double totalPriceMin = 0.0;
			for (NewHouseWyMsgAuthPo newHouseWyMsgAuthPo : wyMsgList) {
				if (null != newHouseWyMsgAuthPo.getReferencePriceMin()) {
					list.add(newHouseWyMsgAuthPo.getReferencePriceMin());
					totalReferencePriceMin = totalReferencePriceMin+=newHouseWyMsgAuthPo.getReferencePriceMin();
				}
			}
			if (!list.isEmpty()) {
				indexPrice = Collections.min(list).toString();
				if(Collections.min(list)<totalReferencePriceMin/list.size()){
					pricedw = "元/平 起";
				}else{
					pricedw = "元/平 ";
				}
				
				priceName = "单价";
				list.clear();
			}
			if (StringUtils.isBlank(indexPrice)) {
				for (NewHouseWyMsgAuthPo newHouseWyMsgAuthPo : wyMsgList) {
					if (null != newHouseWyMsgAuthPo.getTotalPriceMin()) {
						list.add(newHouseWyMsgAuthPo.getTotalPriceMin());
						totalPriceMin = totalPriceMin+=newHouseWyMsgAuthPo.getTotalPriceMin();
					}

				}
				if (!list.isEmpty()) {
					indexPrice = Collections.min(list).toString();
					if(Collections.min(list)<totalPriceMin/list.size()){
						pricedw = "万元/套 起";
					}else{
						pricedw = "万元/套 ";
					}
					
					priceName = "总价";
				}

			}
			// 单个物业类型
		} else if (null != wyMsgList && wyMsgList.size() == 1) {
			NewHouseWyMsgAuthPo newHouseWyMsgAuthPo = wyMsgList.get(0);
			if (null != newHouseWyMsgAuthPo.getReferencePriceMin()
					&& null != newHouseWyMsgAuthPo.getReferencePriceMax()) {
				priceName = "单价";
				if (!newHouseWyMsgAuthPo.getReferencePriceMin().toString().equals(newHouseWyMsgAuthPo.getReferencePriceMax().toString())) {
					indexPrice = newHouseWyMsgAuthPo.getReferencePriceMin().toString();
					pricedw = "元/平 起";
				} else {
					indexPrice = newHouseWyMsgAuthPo.getReferencePriceMin().toString();
					pricedw = "元/平 ";
				}
			}
			if (StringUtils.isBlank(indexPrice)) {
				if (null != newHouseWyMsgAuthPo.getTotalPriceMin() && null != newHouseWyMsgAuthPo.getTotalPriceMax()) {
					priceName = "总价";
					if (!newHouseWyMsgAuthPo.getTotalPriceMin().toString().equals(newHouseWyMsgAuthPo.getTotalPriceMax().toString())) {
						indexPrice = newHouseWyMsgAuthPo.getTotalPriceMin().toString();
						pricedw = "万元/套 起";
					} else {
						indexPrice = newHouseWyMsgAuthPo.getTotalPriceMin().toString();
						pricedw = "万元/套";
					}
				}
			}
		}
		if (StringUtils.isBlank(indexPrice)) {
			if (null != min && null != max) {
				priceName = "周边均价";
				if (!min.toString().equals(max.toString())) {
					indexPrice = min;
					pricedw = "元/平 起";
				} else {
					indexPrice = min;
					pricedw = "元/平";
				}
			}
		}
		HashMap<String, String> hashMap = new HashMap<>();
		hashMap.put("indexPrice", indexPrice);
		hashMap.put("priceName", priceName);
		hashMap.put("pricedw", pricedw);
		return hashMap;
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getByIds(String ids) {
		return dao.getByIds(ids);
	}

	@Override
	public NewHouseFrontVo getNewHouseFrontVo(Long id) {
		// 基本信息
		NewHouseFrontVo vo = dao.getNewHouseFrontVo(id);
		// 楼盘特点
		if (StringUtils.isNotBlank(vo.getDicTraitName())) {
			vo.setDicTraitList(vo.getDicTraitName().split(","));
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", id);
		// 楼盘物业
		List<NewHouseWyMsgAuthPo> wyMsgList = newHouseWyMsgAuthService.listBy(map);
		vo.setWyMsgList(wyMsgList);
		// 楼盘交房时间
		map.put("type", NewHouseOpenHandTimeTypeEnum.HAND.getValue());
		List<NewHouseOpenHandTimeAuthPo> handTimeList = newHouseOpenHandTimeAuthService.listNewBy(map);
		vo.setHandTimeList(handTimeList);
		
		// 楼盘开盘时间
		map.put("type", NewHouseOpenHandTimeTypeEnum.OPEN.getValue());
		List<NewHouseOpenHandTimeAuthPo> openTimeList = newHouseOpenHandTimeAuthService.listNewBy(map);
		vo.setOpenTimeList(openTimeList);
		
		map.remove("type");
		// 户型数量
		List<NewHouseTypeCountVo> houseTypeList = newHouseTypeAuthService.getHouseTypeCount(map);
		vo.setHouseTypeList(houseTypeList);
		String houseTypeStr = "";
		for (NewHouseTypeCountVo newHouseTypeCountVo : houseTypeList) {
			houseTypeStr += "," + newHouseTypeCountVo.getRoomName() + "(" + newHouseTypeCountVo.getCount() + ")";
		}
		// 楼盘户型
		if (StringUtils.isNotBlank(houseTypeStr)) {
			houseTypeStr = houseTypeStr.substring(1);
		}
		vo.setHouseTypeMsg(houseTypeStr);
		return vo;
	}

	@Override
	public Integer listCount(Map<String, Object> paramMap) {
		return dao.listCount(paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> selectByLimit(Map<String, Object> map) {
		return dao.selectByLimit(map);
	}

	public void setRecommendList(NewHouseDirectoryAuthPo authNewHousePo) {

		Map<String, Object> map = new HashMap<>();
		map.put("houseId", authNewHousePo.getId());

		// 推荐规则：
		/*
		 * 1、找同商圈（均价、总价、周边价的前三个价格的楼盘和后三个价格的楼盘；如果找不够6个，将搜索范围扩大到同区域） 2、找同区域
		 * （均价、总价、周边价的前三个价格的楼盘和后三个价格的楼盘；如果找不够6个，在当前均价/总价/
		 * 周边价的基础上找价格比当前价格高的楼盘进行显示）
		 * 如果是暂无售价，找同商圈的楼盘（均价由低到高，找到前六个；如果数量不够需要找总价由低到高的补上）
		 */
		// 商圈
		Long tradingArea = authNewHousePo.getTradingArea();
		// 获取最小单价
		Double minRefrencePrice = newHouseWyMsgAuthService.getMinRefrencePrice(map);
		// 获取最小总价
		Double minTotalPrice = newHouseWyMsgAuthService.getMinTotalPrice(map);

		map.put("tradingArea", tradingArea);// 同商圈
		List<NewHouseRecommendVo> list = new ArrayList<NewHouseRecommendVo>();
		// 最小单价
		if (null != minRefrencePrice) {
			map.put("minRefrencePrice", minRefrencePrice);// 最小单价
			// 均价最近的八个--> 1商圈
			List<NewHouseRecommendVo> wyListBysq = newHouseWyMsgAuthService.listRecommendNewHouseBysq(map);

			// 均价最近的八个 -->2区域
			if (null != wyListBysq && wyListBysq.size() < 8) {
				map.put("areaLevalThree", authNewHousePo.getAreaLevalThree());
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq2(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			// 均价最近的八个 不区分地区
			if (null != wyListBysq && wyListBysq.size() < 8) {
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq3(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			list = wyListBysq;

		} else if (null != minTotalPrice) {
			// 最小总价
			map.put("minTotalPrice", minTotalPrice);
			// 均价最近的八个--> 1商圈
			List<NewHouseRecommendVo> wyListBysq = newHouseWyMsgAuthService.listRecommendNewHouseBysq(map);

			// 均价最近的八个 -->2区域
			if (null != wyListBysq && wyListBysq.size() < 8) {
				map.put("areaLevalThree", authNewHousePo.getAreaLevalThree());
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq2(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}

			// 均价最近的八个 --> 不区分
			if (null != wyListBysq && wyListBysq.size() < 8) {
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq3(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			list = wyListBysq;

		} else if (null != authNewHousePo.getAroundMinPrice()) {
			// 最小周边价
			map.put("aroundMinPrice", authNewHousePo.getAroundMinPrice());
			// 均价最近的八个--> 1商圈
			List<NewHouseRecommendVo> wyListBysq = newHouseWyMsgAuthService.listRecommendNewHouseBysq(map);

			// 均价最近的八个 -->2区域
			if (null != wyListBysq && wyListBysq.size() < 8) {
				map.put("areaLevalThree", authNewHousePo.getAreaLevalThree());
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq2(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			// 均价最近的八个 -->所有
			if (null != wyListBysq && wyListBysq.size() < 8) {
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq3(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			list = wyListBysq;
		} else {
			map.put("minRefrencePrice", 0);// 最小单价
			// 均价最近的八个--> 1商圈
			List<NewHouseRecommendVo> wyListBysq = newHouseWyMsgAuthService.listRecommendNewHouseBysq(map);

			// 均价最近的八个 -->2区域
			if (null != wyListBysq && wyListBysq.size() < 8) {
				map.put("areaLevalThree", authNewHousePo.getAreaLevalThree());
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq2(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			// 均价最近的八个 -->所有
			if (null != wyListBysq && wyListBysq.size() < 8) {
				List<NewHouseRecommendVo> wyListByqy = newHouseWyMsgAuthService.listRecommendNewHouseBysq3(map);
				for (NewHouseRecommendVo vo : wyListByqy) {
					if (wyListBysq.size() < 8) {
						wyListBysq.add(vo);
					} else {
						break;
					}
				}
			}
			list = wyListBysq;
		}
		// 最新上架楼盘
		/*
		 * if(list.size()<8){ List<NewHouseRecommendVo> eightList =
		 * dao.selectEightNewUpNewHouse(); for (NewHouseRecommendVo vo :
		 * eightList) { if(list.size() < 8){ list.add(vo); }else{ break; } } }
		 */

		for (NewHouseRecommendVo newHouseRecommendVo : list) {
			if (null != newHouseRecommendVo) {
				newHouseRecommendVo.setCreateTime(new Date());
				newHouseRecommendVo.setNewHouseId(authNewHousePo.getId());
			}

		}

		dao.deleteNewHouseById(authNewHousePo.getId());
		dao.saveRecommendList(list);
	}

	@Override
	public void saveNewHouseToSolr(HashMap<String, Object> map) {
		List<NewHouseIndexFiled> newHouseList = getToSolrNewHouse(map);
		for (NewHouseIndexFiled filed : newHouseList) {
			String subwayline = subwayObjService.getLineByNewHouse(Long.parseLong(filed.getId()));
			filed.setSubwayline("," + subwayline + ",");
			String subway = subwayObjService.getStationByNewHouse(Long.parseLong(filed.getId()));
			filed.setSubway("," + subway + ",");
			if (filed.getStatus() == NewHouseSaleStatusEnum.SALED.getValue()) {
				filed.setIsSaleOut(1);
			} else {
				filed.setIsSaleOut(2);
			}

		}
		try {
			newHouseIndexService.addItemIndexs(newHouseList);
		} catch (Exception e) {
			LOGGER.error("新房添加solr错误", e);
		}
	}

	public List<NewHouseIndexFiled> getToSolrNewHouse(HashMap<String, Object> map) {
		List<NewHouseIndexFiled> solrList = dao.selectNewHouseIndexFiled(map);

		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		for (NewHouseIndexFiled newHouseIndexFiled : solrList) {
			if (null == newHouseIndexFiled.getOrderNum()) {
				newHouseIndexFiled.setOrderNum(100);
			}
			if (StringUtils.isNotBlank(newHouseIndexFiled.getDicTraitName())) {
				newHouseIndexFiled.setDicTraitName("," + newHouseIndexFiled.getDicTraitName() + ",");
			}
			if (StringUtils.isNotBlank(newHouseIndexFiled.getWyTypeStr())) {
				newHouseIndexFiled.setWyTypeStr("," + newHouseIndexFiled.getWyTypeStr() + ",");
			}
			if(null!=newHouseIndexFiled.getReferencePriceMin()||null!=newHouseIndexFiled.getReferencePriceMax()){
				newHouseIndexFiled.setHasPrice(1);
			}else if(null!=newHouseIndexFiled.getTotalPriceMin()||null!=newHouseIndexFiled.getTotalPriceMax()){
				newHouseIndexFiled.setHasPrice(2);			
			}else if(null!=newHouseIndexFiled.getAroundPriceMin()||null!=newHouseIndexFiled.getAroundPriceMax()){
				newHouseIndexFiled.setHasPrice(3);
			}else{
				newHouseIndexFiled.setHasPrice(4);
			}
			hashMap.put("limitNum", 4);
			hashMap.put("houseId", newHouseIndexFiled.getId());
			List<NewHouseAgentFrontVo> agentList = userService.getNewHouseAgent(hashMap);
			String agents = "";
			for (NewHouseAgentFrontVo newHouseAgentFrontVo : agentList) {
				agents += "," + newHouseAgentFrontVo.getUserId() + "@_s_@" + newHouseAgentFrontVo.getImgUrl() + "@_s_@"
						+ newHouseAgentFrontVo.getUserName() + "@_s_@" + newHouseAgentFrontVo.getRealName() + "@_s_@"
						+ newHouseAgentFrontVo.getMobile() + "@_s_@" + newHouseAgentFrontVo.getShortName() + "@_s_@"
						+ newHouseAgentFrontVo.getWorkyear() + "@_s_@" + newHouseAgentFrontVo.getJgarea() + "@_s_@"
						+ newHouseAgentFrontVo.getDjch() + "@_s_@" + newHouseAgentFrontVo.getDjtb() + "@_s_@"
						+ newHouseAgentFrontVo.getIsDuty();
			}
			if (StringUtils.isNotBlank(agents)) {
				newHouseIndexFiled.setAgent(agents.substring(1));
			}

			hashMap.clear();
			hashMap.put("houseId", newHouseIndexFiled.getId());
			hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			// 户型数量
			Integer fiveAndUp = 0;
			List<NewHouseTypeCountVo> houseTypeList = newHouseTypeAuthService.getHouseTypeCount(hashMap);
			List<NewHouseTypeCountVo> newHouseTypeList = new ArrayList<NewHouseTypeCountVo>();
			for (NewHouseTypeCountVo newHouseTypeCountVo : houseTypeList) {

				if (newHouseTypeCountVo.getRoom() != null) {
					if (newHouseTypeCountVo.getRoom() < 5) {
						newHouseTypeList.add(newHouseTypeCountVo);
					} else {
						fiveAndUp += newHouseTypeCountVo.getCount();
						;
					}
				}
			}
			if (fiveAndUp != 0) {
				NewHouseTypeCountVo fiveNewHouseType = new NewHouseTypeCountVo();
				fiveNewHouseType.setRoom(5);
				fiveNewHouseType.setRoomName("五居室及以上");
				fiveNewHouseType.setCount(fiveAndUp);
				newHouseTypeList.add(fiveNewHouseType);
			}

			// 楼盘户型字符串
			String houseTypeStr = "";
			for (NewHouseTypeCountVo newHouseTypeCountVo : newHouseTypeList) {
				houseTypeStr += "、" + newHouseTypeCountVo.getRoomName() + "(" + newHouseTypeCountVo.getCount() + ")";
			}

			if (StringUtils.isNotBlank(houseTypeStr)) {
				houseTypeStr = houseTypeStr.substring(1);
			}
			newHouseIndexFiled.setRoomNames(houseTypeStr);
			if (StringUtils.isNotBlank(newHouseIndexFiled.getRooms())) {
				newHouseIndexFiled.setRooms("," + newHouseIndexFiled.getRooms() + ",");
			}
		}
		return solrList;
	}

	@Override
	public List<NewHouseDirectoryAuthVo> findAgentNewHouse(Map<String, Object> map) {
		return dao.findAgentNewHouse(map);
	}

	@Override
	public int findAgentNewHouseCount(Map<String, Object> map) {
		return dao.findAgentNewHouseCount(map);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> findFollow(Map<String, Object> map) {
		return dao.findFollow(map);
	}

	@Override
	public int findFollowCount(Map<String, Object> map) {
		return dao.findFollowCount(map);
	}

	@Override
	public List<NewHouseDirectoryAuthVo> lookHistory(Map<String, Object> map) {
		return dao.lookHistory(map);
	}

	@Override
	public int lookHistoryCount(Map<String, Object> map) {
		return dao.lookHistoryCount(map);
	}

	@Override
	public String getUpIds(int start, int count) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("start", start);
		hashMap.put("count", count);
		return dao.getUpIds(hashMap);
	}

	// 获取经纪人负责的新房ids,然后更新solr
	@Override
	public void saveNewHouseToSolrByAgentId(HashMap<String, Object> map) {
		// 获取经纪人负责的新房ids
		String ids = userService.getNewHouseIdsByMap(map);
		if (StringUtils.isNotBlank(ids)) {
			map.clear();
			map.put("newHouseIds", ids);
			List<NewHouseIndexFiled> newHouseList = getToSolrNewHouse(map);
			for (NewHouseIndexFiled filed : newHouseList) {
				String subwayline = subwayObjService.getLineByNewHouse(Long.parseLong(filed.getId()));
				filed.setSubwayline("," + subwayline + ",");
				String subway = subwayObjService.getStationByNewHouse(Long.parseLong(filed.getId()));
				filed.setSubway("," + subway + ",");
				if (filed.getStatus() == NewHouseSaleStatusEnum.SALED.getValue()) {
					filed.setIsSaleOut(1);
				} else {
					filed.setIsSaleOut(2);
				}

			}
			try {
				newHouseIndexService.addItemIndexs(newHouseList);
			} catch (Exception e) {
				LOGGER.error("新房添加solr错误", e);
			}
		}

	}

	@Override
	public NewHouseDirectoryAuthVo getRecommendNewHouse(long id) {
		return dao.getRecommendNewHouse(id);
	}

	@Override
	public void updateEvelopersPerson(Map<String, Object> paramMap) {
		dao.updateEvelopersPerson(paramMap);
	}

	@Override
	public List<NewHouseDirectoryAuthPo> getElev(HashMap<String, Object> map) {
		return dao.getElev(map);
	}

	@Override
	public NewHouseFrontVo getNewHousePrice(long id) {
		// 基本信息
		NewHouseFrontVo vo = dao.getNewHouseFrontVo(id);

		// 楼盘特点
		if (StringUtils.isNotBlank(vo.getDicTraitName())) {
			vo.setDicTraitList(vo.getDicTraitName().split(","));
		}
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("houseId", id);
		List<NewHouseWyMsgAuthPo> wyMsgList = newHouseWyMsgAuthService.listBy(map);
		vo.setWyMsgList(wyMsgList);

		// 首页展示价格 1均价 2总价 3周边单价
		String indexPrice = "";
		String priceName = "";
		String pricedw = "";
		// 多个物业类型
		if (null != wyMsgList && wyMsgList.size() > 1) {
			ArrayList<Double> list = new ArrayList<Double>();
			for (NewHouseWyMsgAuthPo newHouseWyMsgAuthPo : wyMsgList) {
				if (null != newHouseWyMsgAuthPo.getReferencePriceMin()) {
					list.add(newHouseWyMsgAuthPo.getReferencePriceMin());
				}
			}
			if (!list.isEmpty()) {
				indexPrice = Collections.min(list).toString();
				pricedw = "元/平 起";
				priceName = "单价";
				list.clear();
			}
			if (StringUtils.isBlank(indexPrice)) {
				for (NewHouseWyMsgAuthPo newHouseWyMsgAuthPo : wyMsgList) {
					if (null != newHouseWyMsgAuthPo.getTotalPriceMin()) {
						list.add(newHouseWyMsgAuthPo.getTotalPriceMin());
					}

				}
				if (!list.isEmpty()) {
					indexPrice = Collections.min(list).toString();
					pricedw = "万元/套 起";
					priceName = "总价";
				}

			}
			// 单个物业类型
		} else if (null != wyMsgList && wyMsgList.size() == 1) {
			NewHouseWyMsgAuthPo newHouseWyMsgAuthPo = wyMsgList.get(0);
			if (null != newHouseWyMsgAuthPo.getReferencePriceMin()
					&& null != newHouseWyMsgAuthPo.getReferencePriceMax()) {
				priceName = "均价";
				if (newHouseWyMsgAuthPo.getReferencePriceMin() != newHouseWyMsgAuthPo.getReferencePriceMax()) {
					indexPrice = newHouseWyMsgAuthPo.getReferencePriceMin().toString();
					pricedw = "元/平 起";
				} else {
					indexPrice = newHouseWyMsgAuthPo.getReferencePriceMin().toString();
					pricedw = "元/平 ";
				}
			}
			if (StringUtils.isBlank(indexPrice)) {
				if (null != newHouseWyMsgAuthPo.getTotalPriceMin() && null != newHouseWyMsgAuthPo.getTotalPriceMax()) {
					priceName = "总价";
					if (newHouseWyMsgAuthPo.getTotalPriceMin() != newHouseWyMsgAuthPo.getReferencePriceMax()) {
						indexPrice = newHouseWyMsgAuthPo.getTotalPriceMin().toString();
						pricedw = "万元/套 起";
					} else {
						indexPrice = newHouseWyMsgAuthPo.getTotalPriceMin().toString();
						pricedw = "万元/套";
					}
				}
			}
		}
		if (StringUtils.isBlank(indexPrice)) {
			if (null != vo.getAroundPriceMin() && null != vo.getAroundPriceMax()) {
				priceName = "周边均价";
				if (vo.getAroundPriceMin() != vo.getAroundPriceMax()) {
					indexPrice = vo.getAroundPriceMin().toString();
					pricedw = "元/平 起";
				} else {
					indexPrice = vo.getAroundPriceMin().toString();
					pricedw = "元/平";
				}
			}
		}

		if (StringUtils.isNotBlank(indexPrice)) {
			vo.setIndexPrice((int) (Double.parseDouble(indexPrice)));
			vo.setPricedw(pricedw);
			vo.setPriceName(priceName);
		}
		return vo;

	}

	@Override
	public PageBean getListDataType(PageParam pageParam, Map<String, Object> requestMaps) {
		PageBean listPage = dao.listPage(pageParam, requestMaps, "listNewPageDataTypeCount", "listNewDataTypePage");
		return listPage;
	}

	@Override
	public List<NewHouseDirectoryAuthVo> getIndexNewHouse(Map<String, Object> map) {
		return dao.getIndexNewHouse(map);
	}

	@Override
	public int getIndexNewHouseCount(Map<String, Object> map) {
		return dao.getIndexNewHouseCount(map);
	}

}

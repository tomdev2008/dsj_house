package com.dsj.data.web.other.trade;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dsj.common.enums.DeleteStatusEnum;
import com.dsj.common.enums.StatusCode;
import com.dsj.common.utils.StringUtilsOne;
import com.dsj.common.vo.AjaxResultVo;
import com.dsj.data.web.utils.LngLatUtil;
import com.dsj.data.web.utils.PinyinUtil;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthHistoryService;
import com.dsj.modules.newhouse.service.NewHouseDirectoryAuthService;
import com.dsj.modules.oldhouse.service.OldHouseMasterService;
import com.dsj.modules.other.enums.AreaEnum;
import com.dsj.modules.other.po.AreaPo;
import com.dsj.modules.other.po.HouseDirectoryPo;
import com.dsj.modules.other.po.TradeAreaPo;
import com.dsj.modules.other.service.AreaService;
import com.dsj.modules.other.service.HouseDirectoryService;
import com.dsj.modules.other.service.TradeAreaService;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 区域商圈管理
 */
@Controller
@RequestMapping(value = "back/**/tradeArea")
public class TradeAreaController {

	private final Logger logger = LoggerFactory.getLogger(TradeAreaController.class);

	@Autowired
	private AreaService areaService;
	@Autowired
	private TradeAreaService tradeAreaService;
	@Autowired
	private HouseDirectoryService houseDirectoryService;//楼盘字典
	@Autowired
	private NewHouseDirectoryAuthService newHouseDirectoryAuthService;//新房字典
	@Autowired
	private NewHouseDirectoryAuthHistoryService newHouseDirectoryAuthHistoryService;//新房字典历史
	
	@RequestMapping({"to_show_area",""})
	public String toAreaList(Model model) {
		Map<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("parentId", 0);
		hashMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		List<AreaPo> firstAreaList = areaService.listBy(hashMap);
		model.addAttribute("firstAreaList", firstAreaList);
		return "system/area/area_edit";
	}

	/**
	 * 根据父级地区编码查询地区默认加载基础数据
	 * 
	 * @param areaCode 父级地区编码
	 * @return
	 */
	@RequestMapping("getDataByCityId")
	@ResponseBody
	public AjaxResultVo getDataByCityId(Integer areaCode) {
		AjaxResultVo ajaxResult = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		try {
			AreaPo city = areaService.getById(areaCode);
			map.put("parentId", areaCode);
			map.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
			List<AreaPo> areaList = areaService.listBy(map);
			List<TradeAreaPo> tradeList = getTradeList(areaList);
			map.put("areaList", areaList);
			map.put("tradeList", tradeList);
			map.put("city", city);
			ajaxResult.setStatusCode(StatusCode.SUCCESS);
			ajaxResult.setData(map);
		} catch (Exception e) {
			logger.info("地区查询异常", e.getMessage(), e);
			ajaxResult.setStatusCode(StatusCode.SERVER_ERROR);
			ajaxResult.setMessage(e.getMessage());
		}
		return ajaxResult;
	}
	
	@RequestMapping("addArea")
	@ResponseBody
	public AjaxResultVo addArea(Integer lv,AreaPo areaPo,TradeAreaPo tradeAreaPo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			if (AreaEnum.COUNTY_LV.getValue().equals(lv)) {
				areaPo.setIsCustom(AreaEnum.IS_CUSTOM_YES.getValue());
				areaPo.setCreateDate(new Date());
				areaPo.setIsLeaf(1);
				areaPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
				buildAreaPo(areaPo);
				areaService.saveDynamic(areaPo);
			}else if (AreaEnum.TRADE_LV.getValue().equals(lv)) {
				buildTradeAreaPo(tradeAreaPo);
				tradeAreaPo.setDeleteFlag(DeleteStatusEnum.NDEL.getValue());
				tradeAreaPo.setCreateTime(new Date());
				tradeAreaService.saveDynamic(tradeAreaPo);
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("区域新增异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	@RequestMapping("editArea")
	@ResponseBody
	public AjaxResultVo editArea(Integer lv,AreaPo areaPo,TradeAreaPo tradeAreaPo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			ajax.setStatusCode(StatusCode.SUCCESS);
			if (AreaEnum.COUNTY_LV.getValue().equals(lv)) {
				buildAreaPo(areaPo);
				areaService.updateDynamic(areaPo);
			}else if (AreaEnum.TRADE_LV.getValue().equals(lv)) {
				buildTradeAreaPo(tradeAreaPo);
				tradeAreaService.updateDynamic(tradeAreaPo);
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("区域修改异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	private void buildAreaPo(AreaPo areaPo) {
		areaPo.setLikePinyin(PinyinUtil.getFirstWord(areaPo.getName()));
		areaPo.setEnglishHead(PinyinUtil.getFirstWords(areaPo.getName()));
		areaPo.setEnglishName(PinyinUtil.getPinyin(areaPo.getName()));
		if (areaPo.getParentId()!=null) {
			AreaPo fatherPo = areaService.getById(areaPo.getParentId());
			areaPo.setDimension(LngLatUtil.latLng(fatherPo.getName()+areaPo.getName())[0]);
			areaPo.setAccuracy(LngLatUtil.latLng(fatherPo.getName()+areaPo.getName())[1]);
		}
	}
	
	private void buildTradeAreaPo(TradeAreaPo tradeAreaPo) {
		tradeAreaPo.setLikePinyin(PinyinUtil.getFirstWord(tradeAreaPo.getName()));
		tradeAreaPo.setEnglishHead(PinyinUtil.getFirstWords(tradeAreaPo.getName()));
		tradeAreaPo.setEnglishName(PinyinUtil.getPinyin(tradeAreaPo.getName()));
		if (tradeAreaPo.getParentId()!=null) {
			AreaPo fatherPo = areaService.getById(tradeAreaPo.getParentId());
			AreaPo grandfatherPo = areaService.getById(fatherPo.getParentId());
			tradeAreaPo.setDimension(LngLatUtil.latLng(grandfatherPo.getName()+fatherPo.getName()+tradeAreaPo.getName())[0]);
			tradeAreaPo.setAccuracy(LngLatUtil.latLng(grandfatherPo.getFullName()+fatherPo.getName()+tradeAreaPo.getName())[1]);
		}
	}
	
	@RequestMapping("deleteArea")
	@ResponseBody
	public AjaxResultVo deleteArea(Integer lv ,AreaPo areaPo,TradeAreaPo tradeAreaPo) {
		AjaxResultVo ajax = new AjaxResultVo();
		try {
			ajax.setStatusCode(StatusCode.SUCCESS);
			if (AreaEnum.COUNTY_LV.getValue().equals(lv)) {
				areaPo = areaService.getById(areaPo.getId());
				areaPo.setDeleteFlag(DeleteStatusEnum.DEL.getValue());
				if (areaPo.getIsCustom()==AreaEnum.IS_CUSTOM_YES.getValue()) {
					//判断是否有数据绑定该区域
					if (!checkExitCountyData(areaPo)) {
						ajax.setStatusCode(StatusCode.SERVER_ERROR);
						ajax.setData("有数据绑定该区域");
					}else {
						areaService.updateDynamic(areaPo);
					}
				}else{
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
				}
			}else if(AreaEnum.TRADE_LV.getValue().equals(lv)){
				tradeAreaPo.setDeleteFlag(DeleteStatusEnum.DEL.getValue());
				//判断是否有数据绑定该商圈
				if (!checkExitTradeData(tradeAreaPo)) {
					ajax.setStatusCode(StatusCode.SERVER_ERROR);
					ajax.setData("有数据绑定该商圈");
				}else {
					tradeAreaService.updateDynamic(tradeAreaPo);
				}
			}else {
				ajax.setStatusCode(StatusCode.SERVER_ERROR);
			}
		} catch (Exception e) {
			logger.error("区域删除异常", e);
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	private boolean checkExitTradeData(TradeAreaPo tradeAreaPo) {
		Map<String, Object> paramMap = new HashMap<>();
		//houseDirectoryService;//楼盘字典
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("tradingAreaId", tradeAreaPo.getId());
		List<HouseDirectoryPo> list1 = houseDirectoryService.listBy(paramMap);
		if (list1!=null && list1.size()>0) {
			return false;
		}
		paramMap.clear();
		//newHouseDirectoryAuthService;//新房字典
		paramMap.put("tradingArea", tradeAreaPo.getId());
		List<NewHouseDirectoryAuthPo> list2 = newHouseDirectoryAuthService.listBy(paramMap);
		if (list2!=null && list2.size()>0) {
			return false;
		}
		//newHouseDirectoryAuthHistoryService;//新房字典历史
		List<NewHouseDirectoryAuthHistoryPo> list3 = newHouseDirectoryAuthHistoryService.listBy(paramMap);
		if (list3!=null && list3.size()>0) {
			return false;
		}
		return true;
	}

	private boolean checkExitCountyData(AreaPo areaPo) {
		Map<String, Object> paramMap = new HashMap<>();
		//houseDirectoryService;//楼盘字典
		paramMap.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		paramMap.put("areaCode3", areaPo.getId());
		List<HouseDirectoryPo> list1 = houseDirectoryService.listBy(paramMap);
		if (list1!=null && list1.size()>0) {
			return false;
		}
		paramMap.clear();
		//newHouseDirectoryAuthService;//新房字典
		paramMap.put("areaLevalThree", areaPo.getId());
		List<NewHouseDirectoryAuthPo> list2 = newHouseDirectoryAuthService.listBy(paramMap);
		if (list2!=null && list2.size()>0) {
			return false;
		}
		//newHouseDirectoryAuthHistoryService;//新房字典历史
		List<NewHouseDirectoryAuthHistoryPo> list3 = newHouseDirectoryAuthHistoryService.listBy(paramMap);
		if (list3!=null && list3.size()>0) {
			return false;
		}
		return true;
	}

	@RequestMapping("getMaxAreaID")
	@ResponseBody
	public AjaxResultVo getMaxAreaID(Integer lv) {
		AjaxResultVo ajax = new AjaxResultVo();
		HashMap<String, Object> map = new HashMap<String,Object>();
		Long maxAreaId = null ;
		try {
			if (AreaEnum.CITY_LV.getValue() == lv) {
				AreaPo areaPo =  areaService.getMaxIDArea(map);
				if (areaPo==null) {
					maxAreaId = 0L;
				}
				maxAreaId = areaPo.getId();
			}else if(AreaEnum.COUNTY_LV.getValue() == lv){
				TradeAreaPo tradeAreaPo = tradeAreaService.getMaxIDArea();
				if (tradeAreaPo==null) {
					maxAreaId = 0L;
				}
				maxAreaId = tradeAreaPo.getId();
			}else {
				new Throwable("未获取到正确区域等级");
			}
			ajax.setData(maxAreaId+1);
			ajax.setStatusCode(StatusCode.SUCCESS);
		} catch (Exception e) {
			logger.error("区域获取最大ID异常", e);
			ajax.setMessage("未获取到正确区域等级");
			ajax.setStatusCode(StatusCode.SERVER_ERROR);
		}
		return ajax;
	}
	
	public List<TradeAreaPo> getTradeList(List<AreaPo> list){
		List<TradeAreaPo> tradeList = new ArrayList<>(0);
		String res = "";
		if (list!=null && list.size()>0) {
			for (AreaPo vo : list) {
				res += vo.getId().toString()+",";
			}
			res = res.substring(0, res.length()-1);
		}else {
			return tradeList;
		}
		HashMap<String, Object> map2 = new HashMap<String,Object>();
		map2.put("ids", res);
		map2.put("deleteFlag", DeleteStatusEnum.NDEL.getValue());
		tradeList = tradeAreaService.listByIds(map2);
		return tradeList;
	}
	
}



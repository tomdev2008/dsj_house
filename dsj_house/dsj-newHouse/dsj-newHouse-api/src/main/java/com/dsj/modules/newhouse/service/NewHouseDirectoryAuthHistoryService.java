package com.dsj.modules.newhouse.service;

import java.util.List;
import java.util.Map;

import com.dsj.common.page.PageBean;
import com.dsj.common.page.PageParam;
import com.dsj.common.service.BaseService;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthHistoryPo;
import com.dsj.modules.newhouse.po.NewHouseDirectoryAuthPo;
import com.dsj.modules.newhouse.po.NewHouseOpenHandTimeAuthPo;
import com.dsj.modules.newhouse.po.NewHousePictureAuthPo;
import com.dsj.modules.newhouse.po.NewHouseSubwayAuthPo;
import com.dsj.modules.newhouse.po.NewHouseTypeAuthPo;
import com.dsj.modules.newhouse.po.NewHouseWyMsgAuthPo;
import com.dsj.modules.newhouse.vo.NewHouseDirectoryAuthVo;

/**
 *
 * @描述: Service接口.
 * @作者: gaocj.
 * @创建时间: 2017-07-05 16:51:55.
 * @版本: 1.0 .
 */
public interface NewHouseDirectoryAuthHistoryService extends BaseService<NewHouseDirectoryAuthHistoryPo>{

	void saveNewHouseAuthHistory(NewHouseDirectoryAuthPo directoryAuthPo, List<NewHouseWyMsgAuthPo> wyMsgList,
			List<NewHouseOpenHandTimeAuthPo> openHandTimeList, List<NewHouseTypeAuthPo> houseTypeList, List<NewHousePictureAuthPo> pictureList,List<NewHouseSubwayAuthPo> subwayList) throws Exception;

	PageBean listNewPage(PageParam pageParam, Map<String, Object> requestMap);

	NewHouseDirectoryAuthVo getVoById(Long id);

}
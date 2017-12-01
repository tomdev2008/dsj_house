package com.dsj.modules.mobile400.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.utils.DateUtils;
import com.dsj.common.utils.URLUtils;
import com.dsj.common.utils.json.JsonMapper;
import com.dsj.common.utils.redis.constant.JedisConstant;
import com.dsj.common.utils.redis.one.RedisPoolUtil;
import com.dsj.common.utils.spring.ConfigUtils;
import com.dsj.modules.mobile400.dao.MobileDetailDao;
import com.dsj.modules.mobile400.enums.MobileTypeEnum;
import com.dsj.modules.mobile400.po.MobileDetailPo;
import com.dsj.modules.mobile400.service.MobileDetailService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-07-20 13:41:49.
 * @版本: 1.0 .
 */
@Service
public class MobileDetailServiceImpl extends BaseServiceImpl<MobileDetailDao, MobileDetailPo>
		implements MobileDetailService {
	private final Logger LOGGER = LoggerFactory.getLogger(MobileDetailServiceImpl.class);

	
	@Override
	public void insertDatas() {
		GetCallDetailByLastID();
	}
	
	/*
	 * 5.获取电话清单实时 GetCallDetailByLastID
	 */
	public void GetCallDetailByLastID() {
		try {
			Map<String, Object> postData = new HashMap<String, Object>();
			postData.put("date", DateUtils.getRepTime());
			MobileDetailPo lasrDetailPo = dao.getLastMobileDetailPo();
			int lastid;
			if(null!=lasrDetailPo){
				lastid  =  lasrDetailPo.getLastid();
			}else{
				lastid = 0;
			}
			postData.put("lastid", lastid+1);// 第一次请求传 1，后面请求为前一次 lastid+1
			postData.put("column",
					"ani,dni,callertime,callresult,startdate,cityname,recorderwav,calltype,bigcode,extcode,workgroupname");// 新分机号
			LOGGER.info("400 -- 5 TEL resquest Get Cust Big Code Info params:{},{}",
					ConfigUtils.instance.getMobileLoginName(), ConfigUtils.instance.getMobileRedispwd());
			String sendPost = URLUtils.sendPost(JsonMapper.toJsonString(postData), ConfigUtils.instance.getDetailurl(),
					ConfigUtils.instance.getMobileLoginName(), ConfigUtils.instance.getMobileRedispwd());
			LOGGER.info("400 TEL resquest Get Cust Big Code Info result not print：{}", sendPost);
			Map<String, Object> fromJsonString = (Map<String, Object>) JsonMapper.fromJsonString(sendPost, Map.class);
			Integer status = (Integer) fromJsonString.get("status");
			if (status == 1) {
				Integer count = (Integer) fromJsonString.get("count");
				if (count > 0) {
					// 请求到的数据加上redis里的数据lastid，就是下次请求lastid参数
					lastid = count + lastid;
					RedisPoolUtil.set(JedisConstant.LASTID, lastid);
					List<Map<String, Object>> callData = (List<Map<String, Object>>) fromJsonString.get("call");
					LOGGER.info("400 -- 5 TEL resquest Get Cust Big Code Info call Data", callData);
					for (Map<String, Object> map : callData) {
						MobileDetailPo detailPo = new MobileDetailPo();
						detailPo.setAni((String) map.get("ani"));

						// 批量保存话单
						detailPo.setCallertime((String) map.get("callertime"));
						detailPo.setCallresult((String) map.get("callresult"));
						detailPo.setCityname((String) map.get("cityname"));
						detailPo.setDni((String) map.get("dni"));
						detailPo.setRecorderwav((String) map.get("recorderwav"));
						detailPo.setExtcode((String) map.get("extcode"));
						detailPo.setCreateTime(new Date());
						detailPo.setLastid(lastid);
						if(null!=map.get("workgroupname")){
							String name = map.get("workgroupname").toString();
							String[] str = name.split(",");
							try {
								if (Integer.parseInt(str[0]) == MobileTypeEnum.HOUSE.getValue()) {
									detailPo.setHouseName(str[1]);
									detailPo.setHouseCode(str[2]);
									detailPo.setChannel(Integer.parseInt(str[3]));
									detailPo.setType(MobileTypeEnum.HOUSE.getValue());
								}
								if (Integer.parseInt(str[0]) == MobileTypeEnum.AGENT.getValue()) {
									detailPo.setAgentName(str[1]);
									detailPo.setAgentCode(str[2]);
									detailPo.setType(MobileTypeEnum.AGENT.getValue());
								}
								if (Integer.parseInt(str[0]) == MobileTypeEnum.PROPERTY.getValue()) {
									detailPo.setPropertyName(str[1]);
									detailPo.setPropertyId(Integer.parseInt(str[2]));
									detailPo.setType(MobileTypeEnum.PROPERTY.getValue());
								}
							} catch (Exception e) {
								LOGGER.error("错误数据!");
							}
						}
						detailPo.setStartdate(DateUtils.parseDate((String) map.get("startdate"),
								DateUtils.Format.YYYY_MM_DD_HH_MM_SS.value()));
						dao.insertDynamic(detailPo);
					}
				}
			}
		} catch (Exception e) {
			LOGGER.error("400 -- 5 TEL resquest Get Cust Big Code Info error:", e);
		}
	}

	/**
	 * 获取话单(实时) 生成lastid 第一次请求传和每天初始 1，后面请 求为前一次 lastid+1
	 * 
	 * @return
	 *//*
	public static int getLastid() {
		int lastid;
		Object object = RedisPoolUtil.get(JedisConstant.LASTID);
		if (null == object) {
			lastid = 1;
			RedisPoolUtil.set(JedisConstant.LASTID, lastid);
		} else {
			lastid = (int) object+1;
		}
		// 每天凌晨初始化一次
		String beforeDateStr = DateUtils.getDate(DateUtils.Format.YYYY_MM_DD.value());
		Date beforeDate = DateUtils.parseDate(beforeDateStr);
		Date currentDate = new Date();
		Date afterDate = DateUtils.addMinutes(beforeDate, 5);
		if (DateUtils.isTwoDateSection(beforeDate, afterDate, currentDate)) {
			lastid = 1;
			JedisProxy.setByte(SerializingUtil.serialize(JedisConstant.LASTID), lastid);
		}
		return lastid;
	}*/
}

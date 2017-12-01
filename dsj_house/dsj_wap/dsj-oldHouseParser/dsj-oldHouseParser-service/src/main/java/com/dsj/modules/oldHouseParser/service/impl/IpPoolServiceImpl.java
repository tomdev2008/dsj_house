package com.dsj.modules.oldHouseParser.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dsj.common.core.service.BaseServiceImpl;
import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.modules.oldHouseParser.dao.IpPoolDao;
import com.dsj.modules.oldHouseParser.po.IpPoolPo;
import com.dsj.modules.oldHouseParser.service.IpPoolService;

/**
 *
 * @描述: Service接口实现类.
 * @作者: gaocj
 * @创建时间: 2017-10-31 14:32:39.
 * @版本: 1.0 .
 */
@Service
public class IpPoolServiceImpl  extends BaseServiceImpl<IpPoolDao,IpPoolPo> implements IpPoolService {

	@Override
	public IpPoolPo getByLast() {
		return dao.getByLast();
	}
	

}
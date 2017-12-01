package com.dsj.data.lianjia.biz;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.dsj.common.utils.crawler.CrawlerConfig;

public interface LianjiaErshoufangBiz {

	void dealErshoufangByArea(Integer proxyType);


	void dealErshoufangByArea(String name);


	void secondHandHousingDetail(String url, CrawlerConfig config, String areaName, String tradeName,Integer proxyType);


}

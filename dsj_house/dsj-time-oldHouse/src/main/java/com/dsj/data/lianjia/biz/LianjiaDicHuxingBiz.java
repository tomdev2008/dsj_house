package com.dsj.data.lianjia.biz;

import com.dsj.common.utils.crawler.CrawlerConfig;

public interface LianjiaDicHuxingBiz {

	void dealDicHuxingList(String originDicId, CrawlerConfig config);

	void dealHuxingByDic();

}

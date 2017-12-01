package com.dsj.data.lianjia.biz;

import com.dsj.common.utils.crawler.CrawlerConfig;

public interface LianjiaDicWendaBiz {

	void dealDicWendaList(String originDicId, CrawlerConfig config);

	void dealWendaByDic();

}

package com.dsj.data.lianjia.biz;

import java.util.List;

import org.jsoup.nodes.Document;

import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.modules.oldHouseParser.po.DicDealLogsCrawlerPo;

public interface LianjiaDicChengJiaoBiz {
	
	/**
	 * 根据小区id处理成交信息
	 * @param originDicId
	 * @param config
	 */
	void dealDicChengjiaoList(String originDicId, CrawlerConfig config);

	List<DicDealLogsCrawlerPo> getDealPoBy(String originDicId, Document doc);

}

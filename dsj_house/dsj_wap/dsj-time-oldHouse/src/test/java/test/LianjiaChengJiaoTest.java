package test;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.data.lianjia.biz.LianjiaDicChengJiaoBiz;

public class LianjiaChengJiaoTest extends AbstractJUnit {

	@Autowired
	LianjiaDicChengJiaoBiz lianjiaDicChengJiaoBiz;
	 
	 @Test
	 public void secondHandHousingDetail(){
		CrawlerConfig config = new CrawlerConfig();
		Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		configThread.start();
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}
		 
		 lianjiaDicChengJiaoBiz.dealDicChengjiaoList("1111027375945", config);
	 }
	 
	 
	 
	 

}

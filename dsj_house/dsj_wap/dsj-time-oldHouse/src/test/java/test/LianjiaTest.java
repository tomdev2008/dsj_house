package test;
import java.io.IOException;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import com.dsj.common.utils.crawler.CrawlerConfig;
import com.dsj.common.utils.crawler.CrawlerConfig.GetIP;
import com.dsj.data.lianjia.biz.LianjiaErshoufangBiz;
import com.dsj.data.lianjia.service.LianjiaErshoufangService;

public class LianjiaTest extends AbstractJUnit {
	@Autowired
	LianjiaErshoufangService lianjiaErshoufangService;
	
	@Autowired
	LianjiaErshoufangBiz lianjiaErshoufangBiz;
	 @Test
	 public void lianjiaArea() throws NumberFormatException, IOException, SAXException {
		 lianjiaErshoufangService.saveLianjiaArea("https://bj.lianjia.com/ershoufang/dongcheng/");
	}

	 
	 @Test
	 public void lianjiaErshoufang() throws NumberFormatException, IOException, SAXException {
		 lianjiaErshoufangBiz.dealErshoufangByArea(2);
	}
	 
	 @Test
	 public void secondHandHousingDetail(){
		CrawlerConfig config = new CrawlerConfig();
	/*	Thread configThread=new Thread(config.new GetIP(14 * 1000, 5000, "06fa1d2b59c3977ae5ee2c10729b3620"));
		configThread.start();
		 //停顿2秒防止取不到ip
		 try {
			Thread.currentThread().sleep(2000);
		} catch (InterruptedException e) {
			
			e.printStackTrace();
		}*/
		 lianjiaErshoufangBiz.secondHandHousingDetail("https://bj.lianjia.com/ershoufang/101102206406.html",
				 config,"东城","安定门",2);
	 }
	 
	 
	 
	 
	 public static void main(String[] args) {
		
		 String str="https://bj.lianjia.com/ershoufang/101101263203.html";
		 
		 System.out.println(str.split("/")[4]);
	}

}

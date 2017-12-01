package dubbo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dsj.modules.oldHouseParser.biz.HouseMasterCrawlerBiz;

/**
 * 
 * @描述: 启动Dubbo服务用的MainClass.
 * @作者: WuShuicheng .
 * @创建时间: 2013-11-5,下午9:47:55 .
 * @版本: 1.0 .
 */
public class DubboHebingConsumer {
	
	private static final Log log = LogFactory.getLog(DubboHebingConsumer.class);

	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			HouseMasterCrawlerBiz houseMasterCrawlerBiz=context.getBean(HouseMasterCrawlerBiz.class);
			
			houseMasterCrawlerBiz.saveHeBingOldHouse(0, 20);
			//System.out.println(couponCodeService.getConponCode(24l, "ewrwr3234", "615", 148.0, "148.0"));
		} catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}
		
	}
    
}






package dubbo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dsj.modules.oldHouseParser.service.HouseMasterCrawlerService;

/**
 * 
 * @描述: 启动Dubbo服务用的MainClass.
 * @作者: WuShuicheng .
 * @创建时间: 2013-11-5,下午9:47:55 .
 * @版本: 1.0 .
 */
public class DownConsumer {
	
	private static final Log log = LogFactory.getLog(DownConsumer.class);


	public static void main(String[] args) {
		try {
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			HouseMasterCrawlerService houseMasterCrawlerService=context.getBean(HouseMasterCrawlerService.class);
			
			houseMasterCrawlerService.saveDownOldHouse(10,20);
			//System.out.println(couponCodeService.getConponCode(24l, "ewrwr3234", "615", 148.0, "148.0"));
		} catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}
		
	}
    
}






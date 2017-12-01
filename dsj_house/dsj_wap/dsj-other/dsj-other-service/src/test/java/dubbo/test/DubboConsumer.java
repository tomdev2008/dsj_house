package dubbo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dsj.modules.other.service.AreaService;

/**
 * 
 * @描述: 启动Dubbo服务用的MainClass.
 * @作者: WuShuicheng .
 * @创建时间: 2013-11-5,下午9:47:55 .
 * @版本: 1.0 .
 */
public class DubboConsumer {
	
	private static final Log log = LogFactory.getLog(DubboConsumer.class);

	public static void main(String[] args) {
		try {
		/*	@SuppressWarnings("resource")
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			HouseDirectoryService houseDirectoryService=(HouseDirectoryService) context.getBean("houseDirectoryServiceImpl");
			
			houseDirectoryService.saveHouseDicSolr();*/
			
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			AreaService areaService=(AreaService) context.getBean("areaServiceImpl");
			
			areaService.saveCrawlerSubway();
			
		} catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}

	}
    
}
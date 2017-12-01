package dubbo.test;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dsj.common.page.PageBean;
import com.dsj.modules.solr.service.ErshoufangIndexService;
import com.dsj.modules.solr.vo.AreaLatLngGroupVo;
import com.dsj.modules.solr.vo.ErShoufangQueryVo;

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
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			ErshoufangIndexService ershoufangIndexService=context.getBean(ErshoufangIndexService.class);
			ErShoufangQueryVo queryVo=new ErShoufangQueryVo();
			PageBean page=ershoufangIndexService.getErshoufangSolr(10,queryVo);
			System.out.println(page);
		} catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}
		
	}
    
}
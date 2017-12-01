package dubbo.test;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboProvider {
	private static final Log log = LogFactory.getLog(DubboProvider.class);

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring/spring-context.xml");
			context.start();
			System.out.println("================= Start!!! ===============");
		} catch (Exception e) {
			log.error("== DubboProvider context start error:",e);
		}
		synchronized (DubboProvider.class) {
			while (true) {
				try {
					DubboProvider.class.wait();
				} catch (InterruptedException e) {
					log.error("== synchronized error:",e);
				}
			}
		}
	}
}

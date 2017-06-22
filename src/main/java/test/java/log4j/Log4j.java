package test.java.log4j;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * 动态修改log4j的日志级别
 * 
 * @author bin.deng 
 *
 */
public class Log4j {
	static Logger logger;

	public static void main(String[] args) throws InterruptedException {
		String path = Log4j.class.getClass().getResource("/").getPath() + "log4j.properties";
		System.out.println(path);
		logger = Logger.getLogger(Log4j.class);
		PropertyConfigurator.configureAndWatch(path, 1000);
		while (true) {
			System.out.println(logger.isDebugEnabled());
			logger.info("info");
			logger.debug("debug");
			Thread.sleep(3000);
		}
	}
}

package test.java;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4j {
	static Logger logger;

	public static void main(String[] args) throws InterruptedException {
//		String path = Log4j.class.getClass().getResource("/").getPath() + "log4j.properties";
//		System.out.println(path);
		PropertyConfigurator.configureAndWatch("log4j.properties", 1000);
		logger = Logger.getLogger(Log4j.class);
		while (true) {
			logger.isDebugEnabled();
			logger.info("info");
			logger.debug("debug");
			Thread.sleep(3000);
		}
	}
}

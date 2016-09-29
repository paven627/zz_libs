

import org.apache.log4j.Logger;

public class LoggerUtil {
	private static Logger logger = Logger.getLogger(LoggerUtil.class);

	private LoggerUtil() {

	}

	public static Logger getLogger() {
		return logger;
	}

	public void debug(String info) {
		logger.debug(info);
	}

	public void info(String info) {
		logger.info(info);
	}

	public void warn(String info) {
		logger.warn(info);
	}

	public void error(String info) {
		logger.error(info);
	}
}

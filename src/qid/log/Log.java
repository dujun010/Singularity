package qid.log;

import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;

public class Log {
	private static Logger log = Logger.getLogger(Log.class);
	private static org.apache.commons.logging.Log logger1 = LogFactory.getLog("mylogger1");
	private static org.apache.commons.logging.Log logger2 = LogFactory.getLog("mylogger2");
	
	
	public static void main(String[] args) {
		log.info("this is log info");
		log.warn("this is log warn");
		log.error("this is log erro");
		logger1.info("info");
		logger1.warn("warn");
		logger1.error("error");
		logger2.info("info");
		logger2.warn("warn");
		logger2.error("error");
	}
}

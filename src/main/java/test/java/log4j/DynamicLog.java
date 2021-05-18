package test.java.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

/**
 * @author dengbin
 */
public class DynamicLog implements Runnable{
    static Logger logger = LogManager.getLogger("dynamic");
    static Logger debugLog = LogManager.getLogger("debug");
    static Logger infoLog = LogManager.getLogger("infoLog");
    static Logger rootLog = LogManager.getLogger("root");

    private int id ;

    public DynamicLog(int id){
        this.id = id;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 21; i++) {
            DynamicLog t = new DynamicLog(i);
            new Thread(t).start();
        }

    }

    @Override
    public void run() {
//        ThreadContext.put("debugMode", "off");
        debugLog.debug("debug debug");
        debugLog.info("debug info");
        if (this.id % 5 == 0) {
            ThreadContext.put("debugMode", "true");
        }

        infoLog.debug("infoLog debug");
        infoLog.info("infoLog  info");


        logger.debug("dynamic debug");
        logger.info("dynamic info");

        ThreadContext.put("debugMode", "true");
        logger.debug("debugMode dynamic debug");
        logger.info("debugMode dynamic info");
        ThreadContext.put("debugMode", "false");

        logger.debug("dynamic debug");
        logger.info("dynamic info");

        rootLog.debug("rootLog debug");
        rootLog.info("rootLog info");
    }
}

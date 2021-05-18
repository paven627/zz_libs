package test.java.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

public class DynamicFilter implements Runnable {
    static Logger logger = LogManager.getLogger("dynamic");
    static Logger rootLog = LogManager.getLogger("root");

    private int count;

    public DynamicFilter(int count) {
        this.count = count;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 1000; i++) {
            DynamicFilter t = new DynamicFilter(i);
            new Thread(t).start();
        }

    }

    @Override
    public void run() {
        String debugMode = ThreadContext.get("debugMode");

        ThreadContext.put("id", count + "");
        ThreadContext.put("name",Thread.currentThread().getName());
        if (this.count % 1000 == 0) {
            ThreadContext.put("debugMode", "true");
        } else {
            ThreadContext.put("debugMode", "false");
        }

        logger.debug("dynamic debug");
        logger.info("dynamic info");

    }
}

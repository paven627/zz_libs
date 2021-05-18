package test.java.log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.apache.logging.log4j.core.LoggerContext;


/**
 *
 *
 *   <PatternLayout pattern="[%p] %X{id} %x %d{HH:mm:ss:SSS} - %m%n"/>
 */
public class TestMDC implements Runnable {
    static Logger logger = LogManager.getLogger("threadContext");
    final int id;

    public TestMDC(int id) {
        this.id = id;
    }

    public static void main(String[] args) {
//        ThreadContext.push(UUID.randomUUID().toString()); // Add the fishtag;
        for (int i = 1; i < 21; i++) {
            TestMDC t = new TestMDC(i);
            new Thread(t).start();
        }
    }

    @Override
    public void run() {
        final LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
//         Configuration config = ctx.getConfiguration();

        // push 对应 PatternLayout中的 %x
        ThreadContext.push(Thread.currentThread().getName());

        // put 对应PatternLayout中的 %X , 需要key匹配即可直接打印出
        ThreadContext.put("level", "debug");
        ThreadContext.put("id", String.valueOf(id));
        if (id % 10 == 0) {
            logger.debug("Message 1");
            logger.info("Message 2");
        }
        ThreadContext.pop();
    }
}

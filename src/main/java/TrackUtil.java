import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.concurrent.atomic.AtomicInteger;

public final class TrackUtil {
    static Logger log = LoggerFactory.getLogger("system");

    public static final void track(Trackable trackable, String format, Object... arg) {
        if (trackable.isTracking()) {
            log.info(format, arg);
        } else if (log.isDebugEnabled()) {
            log.debug(format, arg);
        }

    }

    public static void main(String[] args) {
        for (int i = 1; i < 21; i++) {
            Session ss = new Session(i + "");
            track(ss, "test" + i);
        }
    }
}

interface Trackable {
    boolean isTracking();
}


class Session implements Trackable {
    private static final int cycle = 1000;
    private static final AtomicInteger counter = new AtomicInteger(0);
    private final boolean tracking;
    private final String id;

    public Session(String id) {
        this.id = id;        // 每 10000 次跟踪一次日志
        this.tracking = (counter.incrementAndGet() % 10 == 0);
        this.mdc();
    }

    @Override
    public boolean isTracking() {
        return tracking;
    }

    public void mdc() {
        MDC.put("sid", id);
    }

    public void clearMdc() {
        MDC.remove("sid");
    }
}
package TestEventBus;

import TestEventBus.event.CustomEvent;
import TestEventBus.eventListeners.EventListener1;
import TestEventBus.eventListeners.EventListener2;
import TestEventBus.util.EventBusUtil;

import java.time.Instant;

/**
 * @author fengjiale
 * @create 2019-09-04 13:38
 * @desc 测试主类
 **/
public class TestMain {
    public static void main(String[] args) {
        EventListener1 listener1 = new EventListener1();
        EventListener2 listener2 = new EventListener2();
        CustomEvent customEvent = new CustomEvent(23);
        EventBusUtil.register(listener1);
        EventBusUtil.register(listener2);
        EventBusUtil.post(customEvent);
        EventBusUtil.asyncPost(customEvent);


//        try {
//            Thread.sleep(10*1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println(Instant.now() + ",主线程执行完毕：" + Thread.currentThread().getName());
    }
}
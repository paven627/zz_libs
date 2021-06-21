package TestEventBus.eventListeners;

import TestEventBus.event.CustomEvent;
import com.google.common.eventbus.Subscribe;

import java.time.Instant;

/**
 * @author fengjiale
 * @create 2019-09-04 13:53
 * @desc 事件监听
 **/
public class EventListener2 {
    @Subscribe
    public void test(CustomEvent event) {
        System.out.println(Instant.now() + ",监听者2,收到事件：" + event.getAge() + "，线程号为：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
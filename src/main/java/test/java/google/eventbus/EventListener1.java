package TestEventBus.eventListeners;

import TestEventBus.event.CustomEvent;
import com.google.common.eventbus.Subscribe;

import java.time.Instant;

/**
 * @author fengjiale
 * @create 2019-09-04 13:40
 * @desc 事件监听
 **/
public class EventListener1 {
    @Subscribe
    public void test1(CustomEvent event) {
        System.out.println(Instant.now() + "监听者1-->订阅者1,收到事件：" + event.getAge() + "，线程号为：" + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void test2(CustomEvent event) {
        System.out.println(Instant.now() + "监听者1-->订阅者2,收到事件：" + event.getAge() + "，线程号为：" + Thread.currentThread().getName());
    }
}
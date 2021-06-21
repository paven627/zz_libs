package TestEventBus.event;
/**
 * @author fengjiale
 * @create 2019-09-04 13:39
 * @desc 自定义事件类
 **/
public class CustomEvent {
    private int age;
    public CustomEvent(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }
}
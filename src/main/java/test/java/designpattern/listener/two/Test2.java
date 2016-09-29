package test.java.designpattern.listener.two;


/**
 * 模拟一个小孩,睡觉醒了之后需要吃东西,大人喂小孩吃东西
 * 
 * 第二种设计 		
 * 针对第一种的问题,使用小孩监控大人,小孩持有大人的对象,
 * 醒来之后自己调用大人的方法
 * 
 * 
 * 设计的问题 : 
 * 小孩醒来之后,通过一个事件类,自己调用父亲的方法,和爷爷的方法
 * 如果需要增加新的通知者,母亲,奶奶灯,每次都需要修改小孩的类,增加
 * 持有各自的引用,并且在醒来的方法中调用每一个人的方法,灵活性依旧不高
 */

//醒来事件
class WakenUpEvent{
	public WakenUpEvent(long time, String location, Child source) {
		super();
		this.time = time;
		this.location = location;
		this.source = source;
	}
	private long time;
	private String location;
	private Child source ; 	//发生事件的源
	
	public WakenUpEvent() {
		super();
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Child getSource() {
		return source;
	}
	public void setSource(Child source) {
		this.source = source;
	}
}

class Child implements Runnable{
	private Dad d ; //持有爹地引用
	private GrandFather gf ;	//持有爷爷引用
	
	public Child(Dad d){
		this.d = d;
	}
	
	
	//调用父亲方法
	void wakenUp(){
//		gf.actionToWakenUp(new WakenUpEvent(
//				System.currentTimeMillis(),"bed",this));
		d.actionToWakenUp(new WakenUpEvent(
				System.currentTimeMillis(),"bed",this));
	}

	@Override
	public void run() {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.wakenUp();
	}
	
}
//爷爷类,醒来之后动作和父亲不同
class GrandFather {
	public void actionToWakenUp(WakenUpEvent event){
		System.out.println("hud child");
	}
}


//父亲类,醒来之后动作是喂
class Dad {
	public void actionToWakenUp(WakenUpEvent wakenUpEvent) {
		System.out.println("喂小孩");
	}
}

public class Test2 {
	public static void main(String[] args) {
		Dad d = new Dad();
		Child c = new Child(d);
		new Thread(c).start();
	}

}

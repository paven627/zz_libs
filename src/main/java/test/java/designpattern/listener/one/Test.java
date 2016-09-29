package test.java.designpattern.listener.one;


/**
 * 模拟一个小孩,睡觉醒了之后需要吃东西,大人喂小孩吃东西
 * 小孩一个线程,5秒之后醒来
 * 大人一个线程,死循环监控小孩状态,发现小孩醒来后调用喂得方法
 * 
 * 第一种设计 	父亲监控小孩状态
 * 设计的问题是: 主动监测小孩,使用一个死循环,系统资源一直占用查看小孩的状态
 */

class Child implements Runnable{
	private boolean wakenUp = false;
	
	void wakenUp(){
		wakenUp = true;
	}

	public boolean isWakenUp() {
		return wakenUp;
	}

	public void setWakenUp(boolean wakenUp) {
		this.wakenUp = wakenUp;
	}

	@Override
	public void run() {
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.wakenUp();
	}
	
}
class Dad implements Runnable{
	Child c ;
	public Dad(Child c){
		this.c= c;
	}
	
	@Override
	public void run() {
		while(!c.isWakenUp()){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		feed(c);
	}
	private void feed(Child c){
		System.out.println("喂小孩吃饭");
	}
}

public class Test {

	public static void main(String[] args) {
		Child c = new Child();
		new Thread(c).start();
		new Thread(new Dad(c)).start();
	}

}

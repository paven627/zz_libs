package test.java.designpattern.proxy.step1;


/**
 * Tank 时间代理
 * 聚合实现测试一个类的一个方法的运行时间
 * Tank3  是 自己聚合的 Tank 的一个代理
 *
 */
public class TankTimeProxy implements Movable{
	public TankTimeProxy() {
	}

	public TankTimeProxy(Movable t) {
		this.t = t;
	}

	Movable t;

	@Override
	public void move(){
		long start = System.currentTimeMillis();
		t.move();
		long end = System.currentTimeMillis();
		System.out.println("time:"+(end - start));
	}
}

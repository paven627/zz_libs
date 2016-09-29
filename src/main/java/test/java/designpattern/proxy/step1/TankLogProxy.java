package test.java.designpattern.proxy.step1;

/**
 * Tank 日志代理
 *
 */
public class TankLogProxy implements Movable{
	public TankLogProxy() {
	}

	public TankLogProxy(Movable t) {
		this.t = t;
	}

	Movable t;

	@Override
	public void move(){
		System.out.println("日志开始");
		t.move();
		System.out.println("日志结束");
	}
}

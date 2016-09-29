package test.java.designpattern.proxy.step2;

/**
 * 只能代理 Tank 的时间,如果新添加的类也需要测试时间,就必须
 * 新添加代理类,代理类也是无限扩充的,没有课扩展性
 * 需要一个通用的代理,代理所有需要的可测试时间的类
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

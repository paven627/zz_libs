package test.java.designpattern.proxy.step1;

/**
 * 测试类,一个类有各种代理,如果想改变代理的顺序或者写法的时候
 * 使用代理从类继承已经不行,必须使用聚合
 * 改变代理的顺序时,只需改变测试类的代理写法
 *
 */
public class Client {
	public static void main(String[] args) {
		Tank t = new Tank();
		TankLogProxy tlp = new TankLogProxy(t);
		TankTimeProxy ttp = new TankTimeProxy(tlp);
//		Movable m = tlp;
//		m.move();
		ttp.move();
	}
}

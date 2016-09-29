package test.java.designpattern.proxy.step1;

/**
 * 为了测试 move() 方法的运行时间,而不是从其他类中调用的时间
 * 可以从原类继承,加上时间的语句调用父类的方法就可以测试方法的时间
 *
 */
public class Tank2 extends Tank{
	@Override
	public void move(){
		long start = System.currentTimeMillis();
		super.move();
		long end = System.currentTimeMillis();
		System.out.println("time"+(end - start));
	}
}

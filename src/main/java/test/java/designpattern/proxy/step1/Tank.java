package test.java.designpattern.proxy.step1;

import java.util.Random;

/**
 * 动态代理, 你不必知道我的存在
 *
 */
public class Tank implements Movable{

	@Override
	public void move() {
		long start = System.currentTimeMillis();
		System.out.println("tank moving...");
		try {
			//10秒以内的随机休眠
			Thread.sleep(new Random().nextInt(3000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		long end = System.currentTimeMillis();
		System.out.println("time: "+(end - start));
		
	}
	
	public static void main(String[] args) {
		new Tank().move();
	}
}

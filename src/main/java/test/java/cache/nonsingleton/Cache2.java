package test.java.cache.nonsingleton;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 控制实例数量为3个
 */

/** 单例模式 */
class A {
	// 1.收回创建权限
	private A() {
	}

	// 5: 用来保存一个实例
	private static A a = null;

	// public static int count = 0;
	// 2: 提供一个外部调用的创建实例方法
	public static A getInstance() {
		// 4 : 内部控制创建实例的个数
		// 6: 只在第一次调用的时候New 一个实例
		if (a == null) {
			return new A();
		} else {
			return a;
		}
	}
}

public class Cache2 {
	// 定义用来缓存的容器
	private static Map map = new HashMap();
	// 用来记录当前正在使用哪一个实例
	private static int num = 1;
	// 用来记录需要控制的实例的总数
	private static int count = 3;

	public static A getInstance2() {
		// 到缓存里面查找需要使用的数据
		A a = (A) map.get(num);
		// 如果找不到
		if (a == null) {
			// 新建一个或者从数据库获取一个数据
			a = A.getInstance();
			// 并且把数据放进缓存中
			map.put(num, a);
		}
		num++;
		// 如果 num 超过需要控制的总数,就设置为1,循环重复使用
		if (num > count) {
			num = 1;
		}
		// 如果找到了就直接使用
		return a;
	}

	public static void main(String[] args) {
		A a1 = Cache2.getInstance2();
		System.out.println("a1 ==" + a1);
		A a2 = Cache2.getInstance2();
		System.out.println("a2 ==" + a2);
		A a3 = Cache2.getInstance2();
		System.out.println("a3 ==" + a3);
		A a4 = Cache2.getInstance2();
		System.out.println("a4 ==" + a4);
		A a5 = Cache2.getInstance2();
		System.out.println("a5 ==" + a5);
		System.out.println(Calendar.getInstance());
		System.out.printf("%tT", Calendar.getInstance());
	}
}

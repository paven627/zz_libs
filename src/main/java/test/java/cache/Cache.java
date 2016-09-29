package test.java.cache;

import java.util.HashMap;
import java.util.Map;

class A {
	//1.收回创建权限
	private A() {}
	
	//5: 用来保存一个实例
	private static A a = null;

	public static int  count = 0;
	// 2: 提供一个外部调用的创建实例方法
	public static A getInstance() {
		//4 : 内部控制创建实例的个数
		//6: 只在第一次调用的时候New 一个实例
		if (a == null) {
			return new A();
		}
		else {
			return a;
		}
	}

////////////////////////////////////////////////////////////	
	/**
	 *  缓存的做法,用缓存实现单例
	 *  
	 *  1. 定义用来缓存数据的容器
	 *  2.到缓存里面找
	 *  3.如果没有,就创建一个,并且放进缓存中
	 */

	//定义用来缓存的容器
	private static Map map = new HashMap();
	
	
	public static A getInstance(String key ) {
		// 到缓存里面查找需要使用的数据
		A a = (A)map.get(key);
		//如果找不到
		if (a==null) {
			//新建一个或者从数据库获取一个数据
			a = new A();  //A.getInstance();
			//并且把数据放进缓存中
			map.put(key, a);
		}
		return a;
	}
	
	
}

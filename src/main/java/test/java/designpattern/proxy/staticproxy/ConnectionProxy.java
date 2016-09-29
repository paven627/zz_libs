package test.java.designpattern.proxy.staticproxy;

import java.sql.Connection;

/**
 * 静态:
 * 静态代理,由程序员手动创建,针对目标是类
 * 在程序运行期间,利用反射动态创建对象
 * 也就是,事先创建好类,动态的去用
 * 
 * 
 * 动态 :
 * 动态代理类的类文件由程序在运行时动态生成,无需手动编写类的源代码
 * 针对一个接口创建一个代理类
 * 事先不创建类文件,也就是说 .class 文件由程序按照程序员指定条件生成
 */
public class ConnectionProxy implements GetConnection{
	RealConnection conn = null;
	
	public ConnectionProxy() {
		super();
		RealConnection real = new RealConnection();
		this.conn = real;
	}

	@Override
	public void close() {
		
		conn.close();
	}

	@Override
	public Connection getConnection() {
		if(check()){
			conn.getConnection();
		} else {
			System.out.println("检查不通过");
		}
		
		return null;
	}
	
	public boolean check(){
		System.out.println("检查状态");
		return true;
	}

}

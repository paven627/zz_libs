package test.java.designpattern.proxy.staticproxy;

import java.sql.Connection;

public class RealConnection implements GetConnection{
	@Override
	public void close() {
		System.out.println("将连接关闭掉");
	}

	@Override
	public Connection getConnection() {
		System.out.println("得到一个连接");
		return null;
	}

}

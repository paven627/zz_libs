package test.java.designpattern.proxy.staticproxy;

import java.sql.Connection;

public interface GetConnection {
	Connection getConnection();
	void close();
}

package test.java.designpattern.proxy.dynamic;

import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;

public class Pool {

	private static final String driverName = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	private static final String UID = "scott";
	private static final String PWD = "tiger";

	// private static final String URL =
	// "jdbc:mysql://localhost:3306/bbs2009?user=root&password=admin";
	//	

	private static LinkedList<Connection> pool = new LinkedList<Connection>();

	static {
		try {
			Class.forName(driverName);
			for (int i = 0; i < 3; i++) {
				pool.addLast(createConnection());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Connection createConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(URL, UID, PWD);
		return (Connection) factory(conn);
	}

	public static Connection getConnection() {
		return pool.removeFirst();
	}

	public static void close(Connection c) {
		pool.addLast(c);
	}

	public static Object factory(Connection conn) {
		Class cls = conn.getClass();
		for (Class s : cls.getInterfaces()) {
			System.out.println("接口: " + s);
		}

		ClassLoader loader = cls.getClassLoader();
		Class[] interfaces = new Class[] { Connection.class };
		return Proxy.newProxyInstance(loader, interfaces, new ConnectionProxy(
				conn));
	}

}

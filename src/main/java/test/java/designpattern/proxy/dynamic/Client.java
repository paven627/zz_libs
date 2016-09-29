package test.java.designpattern.proxy.dynamic;

import java.sql.Connection;
import java.sql.SQLException;

public class Client extends Pool {

	public static void main(String[] args) throws SQLException {
		for (int i = 0; i < 10; i++) {
			Connection conn = getConnection();
			System.out.println(conn);
			conn.close();
		}

	}
}

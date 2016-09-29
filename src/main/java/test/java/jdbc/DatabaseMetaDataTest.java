package test.java.jdbc;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Properties;

/**
 * javaDB , derby 数据库,默认 C盘 program files/sun 目录下
 * 
 * 数据库元数据内部使用查询数据字典实现
 * 
 */
public class DatabaseMetaDataTest {
	public static void main(String[] args) throws Exception {
		Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
		System.out.println("Load the embedded driver");
		Connection conn = null;
		Properties props = new Properties();
		props.put("user", "user1");
		props.put("password", "user1");
		// create and connect the database named helloDB
		conn = DriverManager.getConnection("jdbc:derby:helloDB;create=false",
				props);
		System.out.println("connect to helloDB");

		DatabaseMetaData m = conn.getMetaData();
		/*
		 * ResultSet rs = m.getTables(null, null, null, new String[]{"TABLE"});
		 * while(rs.next()) { System.out.println(rs.getString("TABLE_NAME")); }
		 */

		ResultSet rs = m.getColumns(null, null, "HELLOTABLE", null);
		while (rs.next()) {
			System.out.println(rs.getString("COLUMN_NAME"));
			int dataType = rs.getInt("DATA_TYPE");
			switch (dataType) {
			case Types.VARCHAR:
				System.out.println("varchar");
				break;
			case Types.INTEGER:
				System.out.println("int");
				break;
			}
		}

		rs.close();
		conn.close();

		try { // perform a clean shutdown
			DriverManager.getConnection("jdbc:derby:;shutdown=true");
		} catch (SQLException se) {
			System.out.println("Database shut down normally");
		}
	}
}

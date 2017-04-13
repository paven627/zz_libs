//package test.java.jdbc.procedure;
//import java.io.IOException;
//import java.io.Serializable;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Types;
//
//import oracle.jdbc.OracleCallableStatement;
//import oracle.jdbc.OracleTypes;
//
//public class 调用分页存储过程 implements Serializable {
//	static String driver = "oracle.jdbc.driver.OracleDriver";
//	static String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
//
//	String name = "aaaa";
//	static {
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public static void main(String[] args) throws SQLException, IOException,
//			ClassNotFoundException {
//		testDept();
//		// t1();
//	}
//
//	public static void testDept() throws SQLException {
//		Connection conn = DriverManager.getConnection(
//				"jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
//		CallableStatement cstmt = conn
//				.prepareCall("{call proc_get(?,?,?,?,?,?,?,?,?)}");
//		int count = 1;
//		cstmt.setInt(count++, 3);
//		cstmt.setInt(count++, 2);
//		cstmt.setString(count++, "emp");
//		cstmt.setString(count++, "empno");
//		cstmt.setString(count++, "desc");
//		cstmt.registerOutParameter(count++, Types.INTEGER); // 总页数
//		cstmt.registerOutParameter(count++, Types.INTEGER); // 总记录
//		cstmt.registerOutParameter(count++, oracle.jdbc.OracleTypes.CURSOR);
//		cstmt.registerOutParameter(count++, Types.VARCHAR);
//
//		cstmt.execute();
//		int totalRecords = cstmt.getInt(6);
//		int totalPages = cstmt.getInt(7);
//		System.out.println("总记录: " + totalRecords);
//		System.out.println("总页数" + totalPages);
//
//		ResultSet rs = (ResultSet) cstmt.getObject(8);
//		System.out.println("resultSet: " + rs);
//		System.out.println("sql:  " + cstmt.getString(9));
//		while (rs.next()) {
//			System.out.print(rs.getString(1));
//			System.out.print("  ");
//			System.out.print(rs.getString(2));
//			System.out.print("  ");
//			System.out.println(rs.getString(6));
//		}
//	}
//
//	private static void t1() throws ClassNotFoundException, SQLException {
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection conn = DriverManager.getConnection(
//				"jdbc:oracle:thin:@localhost:1521:orcl", "scott", "tiger");
//
//		// DriverManager.registerDriver (new oracle.jdbc.OracleDriver());
//		CallableStatement cs = conn.prepareCall("{ call retCursor(?) }");
//		cs.registerOutParameter(1, OracleTypes.CURSOR);
//		cs.execute();
//		ResultSet rs = ((OracleCallableStatement) cs).getCursor(1);
//		while (rs.next()) {
//			System.out.println(rs.getString(1) + "  " + rs.getString(2) + "  "
//					+ rs.getString(3));
//		}
//	}
//
//}

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

public class UserClick {
	static Connection connection;

	public static void main(String[] args) throws Exception {
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test1?characterEncoding=utf8&autoReconnect=true",
				"root", "admin");
		write();
		
//		connection = DriverManager.getConnection("jdbc:mysql://192.168.1.12:3306/ad_new?characterEncoding=utf8&autoReconnect=true",
//				"root", "");
//		readData();
	}

	private static void write() throws SQLException, FileNotFoundException, IOException {
		String sql = "insert into click_304 values (?, ?,?,?, ?)";

		File file = new File("C:\\Users\\bin.deng\\Desktop\\test.txt");
		BufferedReader reader = new BufferedReader(new FileReader(file));

		PreparedStatement stmt = connection.prepareStatement(sql);
		int count = 0;
		String readLine = null;
		while ((readLine = reader.readLine()) != null) {
			if (readLine != null && !"".equals(readLine)) {
				String[] split = readLine.split(",");
				stmt.setString(1, split[0]);
				stmt.setString(2, split[1]);
				stmt.setString(3, split[2]);
				stmt.setString(4, split[3]);
				stmt.setString(5, split[4]);

				stmt.addBatch();
				count++;
			}
		}
		stmt.executeBatch();
		System.out.println(count);
		reader.close();
		connection.close();
	}

	private static Result readData() throws Exception {
		PreparedStatement prepareStatement = connection.prepareStatement("select * from click_304");
		ResultSet rs = prepareStatement.executeQuery();
		Result result = ResultSupport.toResult(rs);
		
		return result;
	}
}

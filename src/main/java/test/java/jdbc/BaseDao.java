package test.java.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;



/*
 * 连接类型和字符串可以从配置文件中读取,也可以直接指定 配置文件为项目根目中的 . properties 文件,以键值对方式存储
 * 
 * oracle: url="jdbc:oracle:thin:@127.0.0.1:1521";
 * driver="oracle.jdbc.driver.OracleDriver";
 * 
 * SqlServer2005: url="jdbc:sqlserver://127.0.0.1:1433;databasename=xxxx";
 * driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
 * 
 * 在 JDBC API 4.0 中，DriverManager.getConnection 方法得到了增强，可自动加载 JDBC Driver。
 * 因此，使用 sqljdbc4.jar 类库时，应用程序无需调用 Class.forName 方法来注册或加载驱动程序。
 */

public class BaseDao  {
	
	private static final String driverName="oracle.jdbc.driver.OracleDriver";
//	private static final String url =Bundle.getValue("ourl");
//	private static final String userName=Bundle.getValue("ouid");
//	private static final String pwd= Bundle.getValue("opass");
	
//	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static LinkedList<Connection> pool = new LinkedList<Connection>(); 

	static {
		try {
			Class.forName(driverName);
		} catch (Exception e) {
			System.out.println("数据库驱动没有找到:"+e);
		}
	}
	public BaseDao() {
		super();
		try {
			for(int i = 0; i< 3; i++){
				pool.addLast(getConnection());
			}
		} catch (Exception e) {
			System.out.println("创建连接出错");
		}
	}
	
	/**
	 * 获取格式化好的当前时间
	 * 
	 * @return
	 */
//	protected String getTime() {
//		return sdf.format(new Date());
//	}


	/**
	 * 得到一个connection 连接
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	private Connection getConnection() throws  SQLException {
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl", 
					"scott","tiger");
			return connection;
	}
	
	private void release(Connection conn){
		pool.addLast(conn);
	}
	
	public Connection getConn(){
		return pool.removeFirst();
	}
	
	
	
	/**连接池写法 */
//	public Connection getConnection() {
//		Connection conn = null;
//		DataSource ds = null;
//		try {
//			Context initContext = new InitialContext();
//			Context ctx = (Context)initContext.lookup("java:comp/env");
//			ds = (DataSource)ctx.lookup("jdbc/oracle");
////			Log.log.debug("DataSource.getConnection()");
//			conn = ds.getConnection();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return conn;
//	}
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
//		BaseDao sql = new BaseDao();
//		Connection conn = sql.getConnection();
//		Connection conn = sql.getConnection();
//		System.out.println(conn);
		BaseDao baseDao = new BaseDao();
		for (int i = 0; i < 20 ; i++) {
			Connection conn = baseDao.getConn();
			System.out.println(conn);
			baseDao.release(conn);
		}
	}
	
	/**
	 * 通过SQL语句查询数据库,从一个一列一行的结果集,返回其中的一个int数据
	 * 
	 * @param sql
	 * @return 查询的一列一行的一个int 数据
	 */
	public int executeQuery(String sql) {
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		int r = 0;
		try {
			connection = getConnection();
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				r = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs,stmt,connection);
		}
		return r;
	}

	public  Result executeQuery(String sql,Object...args){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Result result = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			if(args != null){
				for(int i =0 ,length = args.length; i< length; i++){
					pstmt.setObject(i+1, args[i]);
				}
			}
			rs = pstmt.executeQuery();
			result = ResultSupport.toResult(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			close(rs,pstmt,conn);
		}
		return result;
	}
	

	/**
	 * 执行增,删,改 的预编译SQL语句
	 * 
	 * String sql2 ="select @@identity"; 这里不可用此函数, 好像是因为executeSQL 方法关闭连接 造成查询不出
	 * 如果自己insert 不关闭连接可以使用此函数返回正确的id
	 * 
	 * @param preparedSql 预编译SQL语句
	 * @param args 可变参数数组
	 * @return 数据库返回的影响条数
	 */
	public int executeSQL(String preparedSql, Object... args) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		int r = 0;
		try {
			connection = getConnection();
//			connection = this.getTestConnection();
//			Log.log.debug("使用测试连接");
			pstmt = connection.prepareStatement(preparedSql);
			if (args != null) {
				for (int i = 0; i < args.length; i++) {
					pstmt.setObject(i + 1, args[i]);
				}
			}
			r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null,pstmt,connection );
		}
		return r;
	}

	/**
	 * 手动设置是否自动提交
	 * 
	 * @param conn
	 *            连接对象
	 * @param b
	 * @throws SQLException
	 */
	protected void setAutoCommit(Connection conn, boolean b)
			throws SQLException {
		conn.setAutoCommit(b);
	}

	/**
	 * 提交数据库更改
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	protected void commit(Connection conn) throws SQLException {
		conn.commit();
	}

	/**
	 * jdbc 手动回滚
	 * 
	 * @param conn
	 *            连接对象
	 * @throws SQLException
	 */
	protected void rollback(Connection conn) throws SQLException {
		conn.rollback();
	}

	/**
	 * 同时关闭三个需要关闭的对象
	 * 
	 * @param conn
	 *            连接
	 * @param stmt
	 *            statement
	 * @param rs
	 *            结果集
	 * @throws SQLException
	 */
	public void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stmt != null) {
				stmt.close();
				stmt = null;
			}
			this.release(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

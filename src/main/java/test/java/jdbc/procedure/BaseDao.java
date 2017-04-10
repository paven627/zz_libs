package test.java.jdbc.procedure;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;

import oracle.jdbc.OracleTypes;



/*
 * �������ͺ��ַ���Դ������ļ��ж�ȡ,Ҳ����ֱ��ָ�� �����ļ�Ϊ��Ŀ��Ŀ�е� . properties �ļ�,�Լ�ֵ�Է�ʽ�洢
 * 
 * oracle: url="jdbc:oracle:thin:@127.0.0.1:1521";
 * driver="oracle.jdbc.driver.OracleDriver";
 * 
 * SqlServer2005: url="jdbc:sqlserver://127.0.0.1:1433;databasename=xxxx";
 * driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
 * 
 * mysql
 * String url = "jdbc:mysql://localhost/testDB?user=testuser&password=testpassword&useUnicode=true&characterEncoding=UTF-8";
 * 
 * 
 * �� JDBC API 4.0 �У�DriverManager.getConnection �����õ�����ǿ�����Զ����� JDBC Driver��
 * ��ˣ�ʹ�� sqljdbc4.jar ���ʱ��Ӧ�ó���������� Class.forName ������ע�����������
 */



public class BaseDao  {
	
//	private static final String driverName="oracle.jdbc.driver.OracleDriver";
//	private static final String URL="jdbc:oracle:thin:@127.0.0.1:1521:orcl";
	
//	private static final String userName="scott";
//	private static final String pwd= "tiger";
	
	private static LinkedList<Connection> pool = new LinkedList<Connection>();
	private static int size = 3;
	
	private static final String URL = "jdbc:mysql://localhost/erp?user=root&password=admin&useUnicode=true&characterEncoding=UTF-8";
	
	protected SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

//	static {
//		try {
////			Class.forName(driverName);
//			for(int i =0; i< size; i++){
//				pool.addLast(createConnection());
//			}
//		} catch (ClassNotFoundException e) {
//			System.out.println("��ݿ���û���ҵ�:"+e);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * ��ȡ��ʽ���õĵ�ǰʱ��
	 * 
	 * @return
	 */
	protected String getTime() {
		return sdf.format(new Date());
	}

	/**
	 * �õ�һ��connection ����
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public Connection getConnection() throws  SQLException {
		return pool.removeFirst();
	}
	
	public void release(Connection conn){
		pool.addLast(conn);
	}
	
	private static Connection createConnection() throws  SQLException {
		Connection connection = DriverManager.getConnection(URL);
//		Logger log = Logger.getLogger("name");
//		log.info(connection.toString());
		return connection;
	}
	
	/**���ӳ�д�� */
//	public Connection getPoolConnection() {
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
	
	
	
	/**
	 * ͨ��SQL����ѯ��ݿ�,��һ��һ��һ�еĽ��,�������е�һ��int���,���������
	 * 
	 * @param sql
	 * @return ��ѯ��һ��һ�е�һ��int ���
	 */
	public int executeQueryInt(String sql,Object... params) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int r = 0;
		try {
			connection = this.getConnection();
			pstmt = connection.prepareStatement(sql);
			if(params != null){
				for(int i = 0; i< params.length; i++){
					pstmt.setObject( i+1, params[i]);
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				r = rs.getInt(1);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close(rs,pstmt,connection);
		}
		return r;
	}

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		BaseDao sql = new BaseDao();
		System.out.println(createConnection());
//		Connection conn = sql.getConnection();
//		sql.executeQueryProce("proc_del", 6);
//		Result r = sql.executeProce("proc_getAccounts", 0);
//		Map[] m = r.getRows();
//		for(Map mm: m){
//			System.out.println(mm);
//		}
		
		
	}
	
	/**
	 * ִ�в�ѯ�Ĵ洢���,���� ���
	 * @param procName
	 * @param params
	 * @return
	 */
	public Result executeQueryProce(String procName, Object...params){
		Connection conn = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		try {
			conn = this.getConnection();
//			String proc = "{call exec(?,?,?)}";
			//�м���������м��� "?,"  ���Ҫ����һ���α�,������û�в���Ҫ��һ�� "?"
			String proc = "{call "+procName+"(";
			if(params != null){
				for (int i = 0; i < params.length; i++) {
					proc += "?,";
				}
			}
			proc += "?)}";
			cstmt = conn.prepareCall(proc);
			//��Ҫע���������,���Ҫע���������
			if(params != null){
				int i = 0;
				for (; i < params.length; i++) {
					cstmt.setObject(i+1, params[i]);
				}
				cstmt.registerOutParameter(i+1, OracleTypes.CURSOR);
			} else {
				cstmt.registerOutParameter(1, 12);
			}
			cstmt.execute();
			int len = 0;
			if (params != null){
				len = params.length;
			}
			rs = (ResultSet)cstmt.getObject(len + 1);
			return ResultSupport.toResult(rs);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(rs, cstmt, conn);
		}
		return null;
	}
	
	/**
	 * ִ�в�ѯ����,���� Result ���ͽ��
	 * @param sql
	 * @param args
	 * @return
	 */
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
	 * ִ����,ɾ,�� ��Ԥ����SQL���
	 * 
	 * String sql2 ="select @@identity"; ���ﲻ���ô˺���, ��������ΪexecuteSQL �����ر����� ��ɲ�ѯ����
	 * ����Լ�insert ���ر����ӿ���ʹ�ô˺������ȷ��id
	 * 
	 * @param preparedSql Ԥ����SQL���
	 * @param args �ɱ��������
	 * @return ��ݿⷵ�ص�Ӱ������
	 */
	public int executeSQL(String preparedSql, Object... args) {
		PreparedStatement pstmt = null;
		Connection connection = null;
		int r = 0;
		try {
			connection = getConnection();
//			connection = this.getTestConnection();
//			Log.log.debug("ʹ�ò�������");
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
	 * �ֶ������Ƿ��Զ��ύ
	 * 
	 * @param conn
	 *            ���Ӷ���
	 * @param b
	 * @throws SQLException
	 */
	protected void setAutoCommit(Connection conn, boolean b)
			throws SQLException {
		conn.setAutoCommit(b);
	}

	/**
	 * �ύ��ݿ���
	 * 
	 * @param conn
	 * @throws SQLException
	 */
	protected void commit(Connection conn) throws SQLException {
		conn.commit();
	}

	/**
	 * jdbc �ֶ��ع�
	 * 
	 * @param conn
	 *            ���Ӷ���
	 * @throws SQLException
	 */
	protected void rollback(Connection conn) throws SQLException {
		conn.rollback();
	}

	/**
	 * ͬʱ�ر������Ҫ�رյĶ���
	 * 
	 * @param conn
	 *            ����
	 * @param stmt
	 *            statement
	 * @param rs
	 *            ���
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
			if (conn != null && !conn.isClosed()) {
//				conn.close();
				this.release(conn);
//				conn = null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}

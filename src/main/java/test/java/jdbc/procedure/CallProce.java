//package test.java.jdbc.procedure;
//
//import java.io.IOException;
//import java.sql.CallableStatement;
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import oracle.jdbc.OracleTypes;
//
//public class CallProce {
//	/**
//	 * 
//	 * --PL/SQL Code (���д���) ��̴��α��OUT������α�(ref cursor)
//			
//			create or replace package my_pack as
//			 type my_ref_cursor is ref cursor;
//			 procedure getMyCursor(val out my_ref_cursor); 
//			end my_pack;
//			
//			/
//			create or replace package body my_pack as
//			 procedure getMyCursor(val out my_ref_cursor)
//			 is
//			 begin
//			  open val for select * from emp;
//			 end;
//			end my_pack;
//			
//			
//			
//			
//				
//	//sql ����
//			
//			--PL/SQL Code(�洢���) ���α��OUT������α�(ref cursor)
//			 
//			create or replace procedure retCursor(ret_cursor out sys_refcursor)is
//			ret_cursor_value  sys_refcursor;
//			begin
//			open ret_cursor_value for select * from emp;
//			 ret_cursor:=ret_cursor_value;
//			end retCursor;
//			
//			
//	 */
//	
//	
//	
//	
//	// java ����
//	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {
//		 
//		Class.forName("oracle.jdbc.driver.OracleDriver");
//		Connection conn = DriverManager.getConnection                       
//		("jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger"); 
////		DriverManager.registerDriver (new oracle.jdbc.OracleDriver());   
//		
//		CallableStatement cs = conn.prepareCall("{ call my_pack.getMyCursor(?) }");
//		cs.registerOutParameter(1,OracleTypes.CURSOR);
//		cs.execute();
////		ResultSet rs = ((OracleCallableStatement)cs).getCursor(1);
//		ResultSet rs = (ResultSet)cs.getObject(1);
//		while(rs.next())
//		{
//			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
//		}
//	}
//}
//

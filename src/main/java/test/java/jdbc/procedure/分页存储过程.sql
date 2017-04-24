create or replace trigger tr_dept
before insert 
on dept
for each row
begin
	select seq_dept.nextval into :new.deptno from dual; 
end;


create sequence seq_dept;


create or replace procedure proc_get
(
v_pageSize in number ,		--每页大小
v_pageNo in number,		    --需要查询第几页
v_table in varchar2,		--查询的表
v_sorter in varchar2,		--排序字段
v_asc in varchar2:='asc',			--排序顺序 ,为 'asc' 或者 'desc' 
v_totalRows out number,			--总记录数
v_totalPages out number ,		--总页数
v_result out sys_refcursor,	--返回结果集
v_return out varchar2
)
as
v_start number:= v_pageSize*(v_pageNo-1) +1;	--开始行
v_end number:=v_pageNo*v_pageSize ;	--结束行
v_sql varchar2(1000);		--存储语句的临时变量
begin
	if v_sorter is  null then 
		v_sql:='select * from (select t.*,rownum r from (select * from '|| v_table ||')t) '
					||'where r between ' || v_start||' and '||v_end;
	else 
		v_sql:='select * from (select t.*,rownum r from'||
		' (select * from '|| v_table ||' order by '||v_sorter||' '||v_asc ||')t) '
					||'where r between ' || v_start||' and '||v_end;
	end if;
		v_return :=v_sql;			--返回查询语句
		open v_result for v_sql;	
		v_sql:='select count(*) from '||v_table;
		execute immediate v_sql into v_totalRows;
		v_totalPages := (v_totalRows + v_pageSize -1)/ v_pageSize;
		
end;

----         JAVA  
/*
public static void testDept() throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:oracle:thin:@127.0.0.1:1521:orcl", "scott", "tiger");
		CallableStatement cstmt = conn
				.prepareCall("{call proc_get(?,?,?,?,?,?,?,?,?)}");
		int count = 1;
		cstmt.setInt(count++, 3);
		cstmt.setInt(count++, 2);
		cstmt.setString(count++, "emp");
		cstmt.setString(count++, "empno");
		cstmt.setString(count++, "desc");
		cstmt.registerOutParameter(count++, Types.INTEGER); // 总页数
		cstmt.registerOutParameter(count++, Types.INTEGER); // 总记录
		cstmt.registerOutParameter(count++, oracle.jdbc.OracleTypes.CURSOR);
		cstmt.registerOutParameter(count++, Types.VARCHAR);

		cstmt.execute();
		int totalRecords = cstmt.getInt(6);
		int totalPages = cstmt.getInt(7);
		System.out.println("总记录: " + totalRecords);
		System.out.println("总页数" + totalPages);

		ResultSet rs = (ResultSet) cstmt.getObject(8);
		System.out.println("resultSet: " + rs);
		System.out.println("sql:  " + cstmt.getString(9));
		while (rs.next()) {
			System.out.print(rs.getString(1));
			System.out.print("  ");
			System.out.print(rs.getString(2));
			System.out.print("  ");
			System.out.println(rs.getString(6));
		}
	}
*/


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
v_pageSize in number ,		--ÿҳ��С
v_pageNo in number,		    --��Ҫ��ѯ�ڼ�ҳ
v_table in varchar2,		--��ѯ�ı�
v_sorter in varchar2,		--�����ֶ�
v_asc in varchar2:='asc',			--����˳�� ,Ϊ 'asc' ���� 'desc' 
v_totalRows out number,			--�ܼ�¼��
v_totalPages out number ,		--��ҳ��
v_result out sys_refcursor,	--���ؽ����
v_return out varchar2
)
as
v_start number:= v_pageSize*(v_pageNo-1) +1;	--��ʼ��
v_end number:=v_pageNo*v_pageSize ;	--������
v_sql varchar2(1000);		--�洢������ʱ����
begin
	if v_sorter is  null then 
		v_sql:='select * from (select t.*,rownum r from (select * from '|| v_table ||')t) '
					||'where r between ' || v_start||' and '||v_end;
	else 
		v_sql:='select * from (select t.*,rownum r from'||
		' (select * from '|| v_table ||' order by '||v_sorter||' '||v_asc ||')t) '
					||'where r between ' || v_start||' and '||v_end;
	end if;
		v_return :=v_sql;			--���ز�ѯ���
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
		cstmt.registerOutParameter(count++, Types.INTEGER); // ��ҳ��
		cstmt.registerOutParameter(count++, Types.INTEGER); // �ܼ�¼
		cstmt.registerOutParameter(count++, oracle.jdbc.OracleTypes.CURSOR);
		cstmt.registerOutParameter(count++, Types.VARCHAR);

		cstmt.execute();
		int totalRecords = cstmt.getInt(6);
		int totalPages = cstmt.getInt(7);
		System.out.println("�ܼ�¼: " + totalRecords);
		System.out.println("��ҳ��" + totalPages);

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


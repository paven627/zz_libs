---�α� For ѭ��
--˳����ʾemp�����й�Ա
declare
--�����α�
cursor emp_cursor 
is
select ename, sal from emp;
begin
	for emp_row in emp_cursor loop
		dbms_output.put_line('��'||emp_cursor%rowcount||'����Ա'||emp_row.ename);
	end loop;
end;

-----------�α�forѭ����ֱ��ʹ���Ӳ�ѯ
begin
	for emp_row in (select ename,sal from emp)loop
		dbms_output.put_line(emp_row.ename);
	end loop;
end;

-----------ʹ���α����
--��ʾ���Ϊ30�����й�Ա


declare
-- ����ref cursor �α�����
type emp_cursor_type is ref cursor;
	--ͨ�����Ͷ����α����
	emp_cursor emp_cursor_Type;
	emp_row emp%rowtype;  	---���ܻ����α��������¼����
begin
	open emp_cursor for 
	select * from emp where deptno = 30;
	loop
		fetch emp_cursor into emp_row;		--��ȡ�α굱ǰ������
		exit when emp_cursor%notfound;		--û�������˳�ѭ��
		dbms_output.put_line('��'||emp_cursor%rowcount||'����Ա:'||emp_row.ename);
	end loop;		
	close emp_cursor;
end;
	

----------------------------------------------------
----------------------------------------------------

----------ֱ��ʹ���α� ,������ Forѭ��

--------- ������ű��Ϊ30 �������͹���------
-------------------------------------------
declare
--������ʾ�α�
cursor emp_cursor 
is
select ename,sal from emp where deptno =30;
	--�����������ename �� sal
	v_ename emp.ename%type;
	v_sal emp.sal%type;
	begin
		open emp_cursor;	--��
		loop
			fetch
				emp_cursor into v_ename,v_sal;		--���α���ȡ����
				exit when emp_cursor%notfound;
				dbms_output.put_line(v_ename||'   ' || v_sal);
		end loop;
		close emp_cursor;
	end;
	

---------ʹ�� fetch  bulk  collect into ��ȡ��������-----
-------------------------------------------------------
declare
cursor emp_cursor is
select ename,sal from emp where deptno=10;
--����type ����,��װ��Ա���͹���
type emp_record is record
(
ename emp.ename%type,
sal emp.sal%type
);
--����table����,����һ������ȡ������¼
type ename_table_type is  table of emp_record;
--����ename_table_type ����
v_ename_table  ename_table_type;
begin
	--���α�
	open emp_cursor;
	--���α���������һ������ȡ��table���ͱ�����
	fetch emp_cursor bulk collect into v_ename_table;
	--�ر��α�
	close emp_cursor;
	---ѭ��table���Ԫ��ֵ
	for i in v_ename_table.first..v_ename_table.last loop
		dbms_output.put_line(v_ename_table(i).ename||'   '||v_ename_table(i).sal);
	end loop;
end;

----- ʹ���α�����------
-----------------------
declare
cursor emp_cursor is
select ename from emp where deptno=10;
--����table����
type ename_table_type is table of varchar2(10);
--����table���ͱ���
v_ename_table ename_table_type;
begin
	if not emp_cursor%isopen then 	--����α�û�д�,���
		open emp_cursor;
	end if;
	fetch emp_cursor bulk collect into v_ename_table;	--��ȡ��������
	--�����Ϣ
	dbms_output.put_line('��ȡ���ܼ�����:'||emp_cursor%rowcount);
	close emp_cursor;
end;

---------------  ���������α�   ------------
---------------�������ֻ��ָ������,����ָ������ --------------
--- ��ʾ�ض����ŵ����й�Ա��
declare
--�����в������α�
cursor emp_cursor(param_dept number)
is
select ename, sal from emp where deptno=param_dept;
--�����α��¼����
emp_record emp_cursor%rowtype;
begin
	open emp_cursor(10);
	loop
			--��ȡ�α��е�һ�����ݵ�emp_record��¼����
			fetch emp_cursor into emp_record;
			--����ȡ��������ʱ�˳�ѭ��
			exit when emp_cursor%notfound;
			dbms_output.put_line(emp_record.ename||'   '||emp_record.sal);
	end loop;
	close emp_cursor;
end;


--------ʹ���α���»�ɾ������
---------------------------------

declare
----����ɸ����α�
cursor emp_cursor 
is
select ename,sal from emp for update;
--�����α��¼����,�����α�������
v_emp_row emp_cursor%rowtype;
--�������ھ�¹�����¹�Ա�ĸ���
v_update_emp_count number(2):=0;
begin
	--���α�
	open emp_cursor;
	loop
		fetch emp_cursor into v_emp_row;	--��ȡ�α굱ǰ������
		exit when emp_cursor%notfound;	--û�������˳�ѭ��
		if v_emp_row.sal < 2000 then 	--�����ǰ��Ա�Ĺ���С��2000
			--���µ�ǰ�еĹ���
			update emp set sal = sal+100 where current of emp_cursor;
			v_update_emp_count:=v_update_emp_count+1;	--������
		end if;
	end loop;
	dbms_output.put_line('����'||v_update_emp_count||'����Ա��������');
	close emp_cursor;
end;


---------- ʹ����ʽ�α�ɾ������ 
----ɾ�����ű��Ϊ30��

declare
--����ɸ����α�
cursor emp_cursor 
is
select deptno from emp for update;
--�����α��¼����,�����α�������
v_emp_row emp_cursor%rowtype;
--����һ��������
v_update_emp_count number(2):=0;
begin
	open emp_cursor;
	loop
		fetch emp_cursor into v_emp_row;	--��ȡ��ǰ������
		exit when emp_cursor%notfound;	--��ȡ���,�˳�ѭ��
		if v_emp_row.deptno=30 then 
			--ɾ����ǰ��Ա
			delete emp where current of emp_cursor;
			v_update_emp_count := v_update_emp_count+1;
		end if;
	end loop;
	dbms_output.put_line('����'||v_update_emp_count ||'����Ա��ɾ����');
	close emp_cursor;
end;


***********************************************************************************
************************************************************************************
1  . ���� ���� 
---���ؽ�����ĺ���
create or replace function fun_getEmpsByHireDate
(
	param_hireDateYear number		--�����Ӷ���ڵ� ���
) 
return sys_refcursor
as
--����sys_refcursor���ͱ���,���ؽ����
param_resultset sys_refcursor;
begin
	open param_resultset  for  
	select ename,sal  from  emp where  extract(year  from  hiredate) = param_hireDateYear;
	return param_resultset;
end;
/

2  .  ���ú���


declare 
type emp_record is  record
(
	ename varchar2(10),
	sal  number (7,2)
);
--���� sys_refcursor���ͱ���
v_emp_rows  sys_refcursor;
v_year  number(4) :=1981;
v_emp_row  emp_record;
begin
	--���ú���
	v_emp_rows  := fun_getEmpsByHireDate(v_year);
	--ʹ��ѭ�������α�
	loop
		fetch v_emp_rows into v_emp_row;
		exit when v_emp_rows%notfound ;	
		dbms_output.put_line('��'||v_emp_rows%rowcount||'����Ա����'||v_emp_row.ename);
	end loop;
	close v_emp_rows;
end;


*********************************************************************************
**********************************************************************************
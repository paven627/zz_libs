---游标 For 循环
--顺序显示emp表所有雇员
declare
--定义游标
cursor emp_cursor 
is
select ename, sal from emp;
begin
	for emp_row in emp_cursor loop
		dbms_output.put_line('第'||emp_cursor%rowcount||'个雇员'||emp_row.ename);
	end loop;
end;

-----------游标for循环中直接使用子查询
begin
	for emp_row in (select ename,sal from emp)loop
		dbms_output.put_line(emp_row.ename);
	end loop;
end;

-----------使用游标变量
--显示编号为30的所有雇员


declare
-- 定义ref cursor 游标类型
type emp_cursor_type is ref cursor;
	--通过类型定义游标变量
	emp_cursor emp_cursor_Type;
	emp_row emp%rowtype;  	---不能基于游标来定义记录变量
begin
	open emp_cursor for 
	select * from emp where deptno = 30;
	loop
		fetch emp_cursor into emp_row;		--提取游标当前行数据
		exit when emp_cursor%notfound;		--没有数据退出循环
		dbms_output.put_line('第'||emp_cursor%rowcount||'个雇员:'||emp_row.ename);
	end loop;		
	close emp_cursor;
end;
	

----------------------------------------------------
----------------------------------------------------

----------直接使用游标 ,不适用 For循环

--------- 输出部门编号为30 的人名和工资------
-------------------------------------------
declare
--定义显示游标
cursor emp_cursor 
is
select ename,sal from emp where deptno =30;
	--定义变量保存ename 和 sal
	v_ename emp.ename%type;
	v_sal emp.sal%type;
	begin
		open emp_cursor;	--打开
		loop
			fetch
				emp_cursor into v_ename,v_sal;		--从游标提取数据
				exit when emp_cursor%notfound;
				dbms_output.put_line(v_ename||'   ' || v_sal);
		end loop;
		close emp_cursor;
	end;
	

---------使用 fetch  bulk  collect into 提取所有数据-----
-------------------------------------------------------
declare
cursor emp_cursor is
select ename,sal from emp where deptno=10;
--定义type 类型,封装雇员名和工资
type emp_record is record
(
ename emp.ename%type,
sal emp.sal%type
);
--定义table类型,用于一次性提取多条记录
type ename_table_type is  table of emp_record;
--定义ename_table_type 变量
v_ename_table  ename_table_type;
begin
	--打开游标
	open emp_cursor;
	--将游标内所有行一次性提取到table类型变量中
	fetch emp_cursor bulk collect into v_ename_table;
	--关闭游标
	close emp_cursor;
	---循环table输出元素值
	for i in v_ename_table.first..v_ename_table.last loop
		dbms_output.put_line(v_ename_table(i).ename||'   '||v_ename_table(i).sal);
	end loop;
end;

----- 使用游标属性------
-----------------------
declare
cursor emp_cursor is
select ename from emp where deptno=10;
--定义table类型
type ename_table_type is table of varchar2(10);
--定义table类型变量
v_ename_table ename_table_type;
begin
	if not emp_cursor%isopen then 	--如果游标没有打开,则打开
		open emp_cursor;
	end if;
	fetch emp_cursor bulk collect into v_ename_table;	--提取所有数据
	--输出信息
	dbms_output.put_line('提取的总计行数:'||emp_cursor%rowcount);
	close emp_cursor;
end;

---------------  带参数的游标   ------------
---------------定义参数只能指定类型,不能指定长度 --------------
--- 显示特定部门的所有雇员名
declare
--定义有参数的游标
cursor emp_cursor(param_dept number)
is
select ename, sal from emp where deptno=param_dept;
--定义游标记录类型
emp_record emp_cursor%rowtype;
begin
	open emp_cursor(10);
	loop
			--提取游标中的一行数据到emp_record记录类型
			fetch emp_cursor into emp_record;
			--当提取不到数据时退出循环
			exit when emp_cursor%notfound;
			dbms_output.put_line(emp_record.ename||'   '||emp_record.sal);
	end loop;
	close emp_cursor;
end;


--------使用游标更新或删除数据
---------------------------------

declare
----定义可更新游标
cursor emp_cursor 
is
select ename,sal from emp for update;
--定义游标记录变量,保存游标行数据
v_emp_row emp_cursor%rowtype;
--定义用于巨鹿被更新雇员的个数
v_update_emp_count number(2):=0;
begin
	--打开游标
	open emp_cursor;
	loop
		fetch emp_cursor into v_emp_row;	--提取游标当前行数据
		exit when emp_cursor%notfound;	--没有数据退出循环
		if v_emp_row.sal < 2000 then 	--如果当前雇员的工资小于2000
			--更新当前行的工资
			update emp set sal = sal+100 where current of emp_cursor;
			v_update_emp_count:=v_update_emp_count+1;	--计数器
		end if;
	end loop;
	dbms_output.put_line('公有'||v_update_emp_count||'名雇员被更新了');
	close emp_cursor;
end;


---------- 使用显式游标删除数据 
----删除部门编号为30的

declare
--定义可更新游标
cursor emp_cursor 
is
select deptno from emp for update;
--定义游标记录变量,保存游标行数据
v_emp_row emp_cursor%rowtype;
--定义一个计数器
v_update_emp_count number(2):=0;
begin
	open emp_cursor;
	loop
		fetch emp_cursor into v_emp_row;	--提取当前行数据
		exit when emp_cursor%notfound;	--提取完后,退出循环
		if v_emp_row.deptno=30 then 
			--删除当前雇员
			delete emp where current of emp_cursor;
			v_update_emp_count := v_update_emp_count+1;
		end if;
	end loop;
	dbms_output.put_line('共有'||v_update_emp_count ||'名雇员被删除了');
	close emp_cursor;
end;


***********************************************************************************
************************************************************************************
1  . 创建 函数 
---返回结果集的函数
create or replace function fun_getEmpsByHireDate
(
	param_hireDateYear number		--定义雇佣日期的 年份
) 
return sys_refcursor
as
--定义sys_refcursor类型变量,返回结果集
param_resultset sys_refcursor;
begin
	open param_resultset  for  
	select ename,sal  from  emp where  extract(year  from  hiredate) = param_hireDateYear;
	return param_resultset;
end;
/

2  .  调用函数


declare 
type emp_record is  record
(
	ename varchar2(10),
	sal  number (7,2)
);
--定义 sys_refcursor类型变量
v_emp_rows  sys_refcursor;
v_year  number(4) :=1981;
v_emp_row  emp_record;
begin
	--调用函数
	v_emp_rows  := fun_getEmpsByHireDate(v_year);
	--使用循环访问游标
	loop
		fetch v_emp_rows into v_emp_row;
		exit when v_emp_rows%notfound ;	
		dbms_output.put_line('第'||v_emp_rows%rowcount||'个雇员名称'||v_emp_row.ename);
	end loop;
	close v_emp_rows;
end;


*********************************************************************************
**********************************************************************************
select empno,job from emp;
select job,sal,empno from emp;
select * from emp;
select * from EMP order by sal desc;
select * from tab;

-- �α����� ������ ������ ���̺� Ȯ��
select * from tab;

--emp: ������� ������ ����
--dept: �μ�����
--bonus: �󿩱� ���̺�
--salgrade: �޿� ���̺�
select * from bonus;

--desc: ���̺� ���� Ȯ��
desc dept;
desc emp;
desc salgrade;

--not null: insert/update�� null�� ��� ����, PK
--varchar2: ���� ���ڿ�, Oracle, MySQL, MSSQL ǥ�� ��� �ణ�� �ٸ�
--char: ���� ���� ���ڿ�
--date: ��¥ Ÿ����
--number(precision, scale)

select * from emp;
select * from dept;
select distinct sal from emp order by sal desc;
select sal from emp order by sal desc;

--������ �˻� ����
select ename,sal,empno --�÷� �̸�
from emp --���̺� �̸�
--order by empno,sal,ename asc
;


select * from dept;

select deptno
from dept;

--select�� ����� ���ο� ���̺��̴�
--�÷��� ��������� �����ϴ�
-- +,-,/,*,mod�Լ� �̿�

select * from emp;
select ename,sal,sal+comm from emp;
select ename,sal,sal+500 from emp;
select ename,sal,sal-100 from emp;
select ename,sal,sal*12 as ���� from emp;
select ename,sal,sal*0.5 as Ư�����ʽ� from emp;

--null: ��Ȯ���� ��, �� �� ���� ��
--      ����, �Ҵ�, �� �Ұ���
--      �Լ��� null���� Ȯ�� ����
--      null�� ���� ����� null
--nvl(comm,0): comm�� null�� ��� �� 0���� ����

--null���� Ȯ��
select ename, job, sal, comm, SAL*12, SAL*12+COMM
from emp
;

--null�� ġȯ �Լ�: nvl(�÷���, �⺻��)
--�⺻���� �÷��� �������� �ڷ����� ��ġ�ؾ� ��
select ename, job, sal, nvl(comm,100), sal*12 as salary,
sal*12+nvl(comm,100) as total
from emp
;


--db ���ڿ�ǥ��: ��������ǥ'' ���
--���ڿ��� �ٿ��� ����ϴ� ����-> ||(������ ������) ���
select ename||' is a '||job as message from emp;
select ename, job from emp;

--��� �÷��� �ߺ��� �����ϰ� �ϳ����� ���: distinct
select deptno from emp;
select distinct deptno from emp;

select distinct deptno,job from emp;







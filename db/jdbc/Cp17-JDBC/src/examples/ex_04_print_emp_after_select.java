//4. EMP ���̺� �� ��SCOTT�� �̸����� �˻��� ����� ����ϴ� ���α׷��� �ۼ��غ���.
package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ex_04_print_emp_after_select {

	public static void main(String[] args) {
		Connection conn=null;
		ResultSet rs=null;
		Scanner sc=new Scanner(System.in);
		//1. DB����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Oracle ����̹� �ε� ����");
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pw="tiger";
		
		//2. �����ͺ��̽��� ����
		conn=DriverManager.getConnection(url,user,pw);
		System.out.println("�����ͺ��̽��� �����߽��ϴ�");
		
		//3. Statement: Connection
		Statement stmt=conn.createStatement();
		
		String sql="select * from emp where ename='SCOTT'";
		
		//Select�� ����� ResultSet�� ����
		//executeQuery(sql��)->ResultSet ��ȯ
		rs=stmt.executeQuery(sql);
		
		//ResultSet: next()->���� ���� ���� Ȯ��(Ŀ���� ����)
		while(rs.next()) {//rs�� ������ ���� �ݺ�
			System.out.print(rs.getInt(1)+"\t");
			System.out.print(rs.getString(2)+"\t");
			System.out.print(rs.getString(3)+"\n");
//			System.out.print(rs.getInt("deptno")+"\t");
//			System.out.print(rs.getString("dname")+"\t");
//			System.out.print(rs.getString("loc")+"\n");
			
		}
		
		rs.close();
		stmt.close();
		
		//4. close
		conn.close();
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();//���� ���� �߻��� �ѹ�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}
	
	

}

package prac_0601;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ex {

	public static void main(String[] args) {
		Connection conn=null;
		Statement stmt=null;
		Scanner sc=new Scanner(System.in);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String url="jdbc:oracle:thin:@localhost:1521:orcl";
			String user="scott";
			String pwd="tiger";

			conn=DriverManager.getConnection(url,user,pwd);
					
			System.out.println("�����ͺ��̽� ���� ����");
			
			System.out.println("�μ� ��ȣ  �Է�>>");
			int empno=sc.nextInt();
			sc.nextLine();
			System.out.println("��� �̸� �Է�>>");
			String ename=sc.nextLine();
			System.out.println("���� �Է�>>");
			String job=sc.nextLine();
			
			stmt=conn.createStatement();
			String sql="insert into emp(empno,ename,job)"
					+" values('"+empno+"','"+ename+"','"+job+"')";
			
			int resultCnt=stmt.executeUpdate(sql);
			
			System.out.println(resultCnt+"�� ���� �ԷµǾ����ϴ�");
			
			conn.close();
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("�ش� Ŭ������ ã�� �� �����ϴ� "+e.getMessage());
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}

}

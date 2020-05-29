package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ex_01_insert_emp {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		Scanner sc=new Scanner(System.in);
		// 1. DB����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle ����̹� �ε� ����");

			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// 2. �����ͺ��̽��� ����
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("�����ͺ��̽��� �����߽��ϴ�");

			System.out.println("�μ���ȣ  �Է�>>");
			int empno = sc.nextInt();
			sc.nextLine();
			System.out.println("��� �̸� �Է�>>");
			String ename = sc.nextLine();
			System.out.println("���� �Է�>>");
			String job = sc.nextLine();
	
			
			// 3.Statement
			stmt = conn.createStatement();
			String sql = "insert into emp (empno, ename,job) "
					+" values('"+empno+"','"+ename+"','"+job+"')";
		
			int resultCnt=stmt.executeUpdate(sql);
			
			System.out.println(resultCnt+"�� ���� �ԷµǾ����ϴ�.");
			
			// 4. close
			conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

}

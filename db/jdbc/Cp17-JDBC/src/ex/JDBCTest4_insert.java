package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest4_insert {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

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

			// 3.Statement
			stmt = conn.createStatement();
			String sql = "insert into dept (deptno, dname,loc) "
					+" values(60,'design','jeju')";
		
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

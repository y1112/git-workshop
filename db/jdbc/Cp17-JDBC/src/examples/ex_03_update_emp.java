//3. EMP ���̺� �� ��SCOTT�� ����� �޿�(sal) ������ 1000���� �ٲٴ� ���α׷��� �ۼ��غ���.
package examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ex_03_update_emp {

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
			String sql = "update emp set sal=1000 where ename='SCOTT'";
		
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

package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCTest_scanner {

	public static void main(String[] args) {
		Connection conn = null;
		ResultSet rs = null;

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

			// 3. Statement: Connection
			Statement stmt = conn.createStatement();
			
			Scanner sc=new Scanner(System.in);
			
			System.out.println("ȸ���� �̸��� �Է����ּ���.>>");
			String searchName=sc.nextLine();
			
			String sql = "select * from emp order by deptno";// �μ���ȭ,����̸�,����
			String sql1 = "select o.orderid,c.name,b.bookname from customer c, book b, orders o"
					+ " where c.custid=o.custid and b.bookid=o.bookid"
					+" and c.name='"+searchName+"'";
			
			System.out.println("sql: "+sql1);
			System.out.println();
			System.out.println();
			System.out.println();
			// Select�� ����� ResultSet�� ����
			// executeQuery(sql��)->ResultSet ��ȯ
			rs = stmt.executeQuery(sql1);

			// ResultSet: next()->���� ���� ���� Ȯ��(Ŀ���� ����)
			System.out.println("�Ǹ� ����Ʈ");
			System.out.println("---------------------------");
			System.out.println("�Ǹ� ���̵�\tȸ���̸�\tå�̸�");
			System.out.println("---------------------------");
			while (rs.next()) {// rs�� ������ ���� �ݺ�
				System.out.print(rs.getInt(1) + "\t");
				System.out.print(rs.getString(2) + "\t");
				System.out.print(rs.getString(3) + "\n");
//			System.out.println("---------------------------");
//			System.out.print(rs.getInt("deptno")+"\t");
//			System.out.print(rs.getString("ename")+"\t");
//			System.out.print(rs.getString("job")+"\n");
//				System.out.print(rs.getString(1));
//				System.out.print(rs.getString(2));
//				System.out.print(rs.getString(3));

			}

			rs.close();
			stmt.close();

			// 4. close
			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();// ���� ���� �߻��� �ѹ�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}

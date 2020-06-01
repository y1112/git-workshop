package examples.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/*
����������α׷�
���
	1.�Է�
	2.����(�̸�,�μ�,�޿�)
	3.����(�����ȣpk����)
	4.����Ʈ(��ü)
	5.�˻�(�̸�����)
	
�μ��������α׷�
���
	1.�Է�
	2.����(�μ��̸�,��ġ)
	3.����(�μ���ȣpk����)
	4.����Ʈ(��ü)
	5.�˻�(�μ��̸� or ��������)
*/
public class management_of_dept {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		String sql = "";
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

			while (true) {
				System.out.println("----------------------------------");
				System.out.println("1.�Է� 2.���� 3.���� 4.����Ʈ 5.�˻�\n6.����â���� ���ư��� 7. ����");
				System.out.println("----------------------------------");
				int n = sc.nextInt();
				if (n == 6)
					break;
				switch (n) {
				case 1:// �Է�
					System.out.println("�μ���ȣ  �Է�>>");
					int deptno = sc.nextInt();
					sc.nextLine();
					System.out.println("�μ��� �Է�>>");
					String dname = sc.nextLine();
					System.out.println("��ġ �Է�>>");
					String loc = sc.nextLine();
					sql = "insert into dept (deptno,dname,loc)" + " values(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, deptno);
					pstmt.setString(2, dname);
					pstmt.setString(3, loc);

					int resultCnt = pstmt.executeUpdate();
					if (resultCnt > 0) {
						System.out.println("���������� �ԷµǾ����ϴ�");
						System.out.println(resultCnt + "���� �ԷµǾ����ϴ�");
					} else {
						System.out.println("�Է��� ���� �ʾҽ��ϴ�\nȮ�� �� ��õ����ּ���");
					}
					break;
				case 2:// ����(�μ��̸�,��ġ)
					System.out.println("������ �μ��� �μ���ȣ  �Է�>>");
					deptno = sc.nextInt();
					sc.nextLine();

					System.out.println("�μ� �̸� �Է�>>");
					dname = sc.nextLine();
					System.out.println("��ġ �Է�>>");
					loc = sc.nextLine();

					sql = "update dept set dname=?, loc=?" + " where deptno=?";

					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, dname);
					pstmt.setString(2, loc);
					pstmt.setInt(3, deptno);
					// ���� ������ ����
//				pstmt.setInt(1, 10);
					rs = pstmt.executeQuery();

					break;
				case 3:// ����(�����ȣ)
					System.out.println("�μ���ȣ  �Է�>>");
					deptno = sc.nextInt();
					sc.nextLine();

					sql = "delete from dept where deptno='" + deptno + "'";
					pstmt = conn.prepareStatement(sql);

					resultCnt = pstmt.executeUpdate();
					if (resultCnt > 0) {
						System.out.println("���������� �����Ǿ����ϴ�");
					} else {
						System.out.println("������ ���� �ʾҽ��ϴ�\nȮ�� �� ��õ����ּ���");
					}
					break;
				case 4:// ����Ʈ
//				sql = "select * from dept where deptno=?";
					sql = "select * from dept";
					pstmt = conn.prepareStatement(sql);// �޼���� prepare~
					// ���� ������ ����
//				pstmt.setInt(1, 10);
					rs = pstmt.executeQuery();
					System.out.println("�μ� ����Ʈ");
					System.out.println("==================================");
					// ResultSet->��� ����
					while (rs.next()) {
						System.out.print(rs.getInt("deptno") + "\t");
						System.out.print(rs.getString("dname") + "\t");
						System.out.print(rs.getString("loc") + "\n");
					}
					System.out.println("==================================");
					break;
				case 5:// �˻�(�̸�)
					sc.nextLine();
					System.out.println("�μ��� �Է�>>");
					dname = sc.nextLine();
					sql = "select * from dept where dname='" + dname + "'";
					pstmt = conn.prepareStatement(sql);// �޼���� prepare~
					// ���� ������ ����
//				pstmt.setInt(1, 10);
					rs = pstmt.executeQuery();
					System.out.println("�μ� ����Ʈ");
					System.out.println("==================================");
					// ResultSet->��� ����
					while (rs.next()) {
						System.out.print(rs.getInt("deptno") + "\t");
						System.out.print(rs.getString("dname") + "\t");
						System.out.print(rs.getString("loc") + "\n");
					}
					System.out.println("==================================");
					break;
				case 7:
					System.exit(0);
					conn.close();
				}

			}

			// 4. close
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 4.�����ͺ��̽� ���� ����
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}

		}

	}

}

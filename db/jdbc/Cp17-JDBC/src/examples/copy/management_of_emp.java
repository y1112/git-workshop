package examples.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
public class management_of_emp {

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
				if(n==6)break;
				switch (n) {
				case 1:// �Է�
					System.out.println("�����ȣ  �Է�>>");
					int empno = sc.nextInt();
					sc.nextLine();
					System.out.println("��� �̸� �Է�>>");
					String ename = sc.nextLine();
					System.out.println("�޿� �Է�>>");
					String sal = sc.nextLine();
					System.out.println("���� �Է�>>");
					String job = sc.nextLine();
					sql = "insert into emp (empno, ename,sal,job)" + " values(?,?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setInt(1, empno);
					pstmt.setString(2, ename);
					pstmt.setString(3, sal);
					pstmt.setString(4, job);

					int resultCnt = pstmt.executeUpdate();
					if (resultCnt > 0) {
						System.out.println("���������� �ԷµǾ����ϴ�");
						System.out.println(resultCnt + "���� �ԷµǾ����ϴ�");
					} else {
						System.out.println("�Է��� ���� �ʾҽ��ϴ�\nȮ�� �� ��õ����ּ���");
					}
					break;
				case 2:// ����(�̸�,�μ�,�޿�)
					System.out.println("������ ������ �����ȣ  �Է�>>");
					empno = sc.nextInt();
					sc.nextLine();

					System.out.println("��� �̸� �Է�>>");
					ename = sc.nextLine();
					System.out.println("�޿� �Է�>>");
					sal = sc.nextLine();
					System.out.println("���� �Է�>>");
					job = sc.nextLine();
					
//					sql = "update emp set ename='"+ename+"' and job='"+job+"'"
//							+ " where empno="+empno;
					sql = "update emp set ename=?, sal=?, job=?"
							+ " where empno=?";
					
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, ename);
					pstmt.setString(2, sal);
					pstmt.setString(3, job);
					pstmt.setInt(4, empno);
					// ���� ������ ����
//				pstmt.setInt(1, 10);
					rs = pstmt.executeQuery();

					break;
				case 3:// ����(�����ȣ)
					System.out.println("�����ȣ  �Է�>>");
					empno = sc.nextInt();
					sc.nextLine();

					sql = "delete from emp where empno='" + empno + "'";
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
					sql = "select * from emp";
					pstmt = conn.prepareStatement(sql);// �޼���� prepare~
					// ���� ������ ����
//				pstmt.setInt(1, 10);
					rs = pstmt.executeQuery();
					System.out.println("��� ����Ʈ");
					System.out.println("==================================");
					// ResultSet->��� ����
					while (rs.next()) {
						System.out.print(rs.getInt("empno") + "\t");
						System.out.print(rs.getString("ename") + "\t");
						System.out.print(rs.getString("sal") + "\t");
						System.out.print(rs.getString("job") + "\n");
					}
					System.out.println("==================================");
					break;
				case 5:// �˻�(�̸�)
					sc.nextLine();
					System.out.println("�̸� �Է�>>");
					ename = sc.nextLine();
					sql = "select * from emp where ename='" + ename + "'";
					pstmt = conn.prepareStatement(sql);// �޼���� prepare~
					// ���� ������ ����
//				pstmt.setInt(1, 10);
					rs = pstmt.executeQuery();
					System.out.println("��� ����Ʈ");
					System.out.println("==================================");
					// ResultSet->��� ����
					while (rs.next()) {
						System.out.print(rs.getInt("empno") + "\t");
						System.out.print(rs.getString("ename") + "\t");
						System.out.print(rs.getString("sal") + "\t");
						System.out.print(rs.getString("job") + "\n");
					}
					System.out.println("==================================");
					break;
				case 7:
					System.exit(0);
					conn.close();
				}
				
			}

			// 3.Statement
//			String sql = "insert into emp (empno, ename,job) "
//					+" values('"+empno+"','"+ename+"','"+job+"')";

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

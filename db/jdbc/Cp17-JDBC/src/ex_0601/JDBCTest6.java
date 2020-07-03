package ex_0601;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest6 {

	public static void main(String[] args) {
		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;

		try {
			// 0.����̹� LIB�߰�
			// 1.�����ͺ��̽� ����̹� �ε�
			// Class.forname(����̹� Ŭ���� ��ü�̸�)
			// Oracle:oracle.jdbc.driver.OracleDriver
			Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2.�����ͺ��̽� ����
			// String url="jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
			// localhost or 127.0.0.1
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, pw);

			// 3.SQLó��
			// Statement or PreparedStatement
			// pstmt=conn.prepareStatement(SQL����);->sql�� ���� ���
			// ����ó�� �ؾ� �ϴ� �κ� ?�� ó��
			// ����ó�� ���� ��� PreparedStatement�� ����

			String sql = "insert into dept (deptno, dname,loc) " + " values(?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, 90);
			pstmt.setString(2, "������");
			pstmt.setString(3, "����");

			int resultCnt = pstmt.executeUpdate();

			if (resultCnt > 0) {
				System.out.println("���������� �ԷµǾ����ϴ�");
				System.out.println(resultCnt + "���� �ԷµǾ����ϴ�");
			} else {
				System.out.println("�Է��� ���� �ʾҽ��ϴ�\nȮ�� �� ��õ����ּ���");
			}


//			pstmt.close();
//			conn.close();

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

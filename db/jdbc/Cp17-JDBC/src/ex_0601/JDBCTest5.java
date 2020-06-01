package ex_0601;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTest5 {

	public static void main(String[] args) {
		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

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
			String sql = "select * from dept where deptno=?";
			pstmt = conn.prepareStatement(sql);// �޼���� prepare~
			// ���� ������ ����
			pstmt.setInt(1, 10);
			rs = pstmt.executeQuery();
			System.out.println("�μ� ����Ʈ");
			System.out.println("====================================");
			// ResultSet->��� ����
			while (rs.next()) {
				System.out.print(rs.getInt("deptno") + "\t");
				System.out.print(rs.getString("dname") + "\t");
				System.out.print(rs.getString("loc") + "\n");
			}
			System.out.println("====================================");

			// 4.�����ͺ��̽� ���� ����
//			rs.close();
//			pstmt.close();
//			conn.close();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
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

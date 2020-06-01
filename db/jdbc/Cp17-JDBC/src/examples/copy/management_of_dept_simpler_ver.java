package examples.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class management_of_dept_simpler_ver {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		String sql = "";
		// 1. DB����̹� �ε�

		while (true) {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("Oracle ����̹� �ε� ����");

				String url = "jdbc:oracle:thin:@localhost:1521:orcl";
				String user = "scott";
				String pw = "tiger";

				// 2. �����ͺ��̽��� ����
				conn = DriverManager.getConnection(url, user, pw);
				System.out.println("�����ͺ��̽��� �����߽��ϴ�");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			System.out.println("----------------------------------");
			System.out.println("1.�Է� 2.���� 3.���� 4.����Ʈ 5.�˻�\n6.����â���� ���ư��� 7. ����");
			System.out.println("----------------------------------");
			int n = sc.nextInt();
			if (n == 6)
				break;
			switch (n) {
			case 1:// �Է�
				insert(pstmt, conn);
				break;
			case 2:// ����(�μ��̸�,��ġ)
				modify(pstmt, conn);
				break;
			case 3:// ����(�����ȣ)
				delete(pstmt, conn);
				break;
			case 4:// ����Ʈ
				print(pstmt, conn);
				break;
			case 5:// �˻�(�̸�)
				search(pstmt, conn);
				break;
			case 7:
				System.exit(0);
			}
		}
	}

	static void insert(PreparedStatement pstmt, Connection conn) {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		try {
			System.out.println("�μ���ȣ  �Է�>>");
			int deptno = sc.nextInt();
			sc.nextLine();
			System.out.println("�μ��� �Է�>>");
			String dname = sc.nextLine();
			System.out.println("��ġ �Է�>>");
			String loc = sc.nextLine();
			String sql = "insert into dept (deptno,dname,loc)" + " values(?,?,?)";
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

	static void delete(PreparedStatement pstmt, Connection conn) {
		Scanner sc = new Scanner(System.in);
		try {

			System.out.println("�μ���ȣ  �Է�>>");
			int deptno = sc.nextInt();
			sc.nextLine();

			String sql = "delete from dept where deptno='" + deptno + "'";
			pstmt = conn.prepareStatement(sql);

			int resultCnt = pstmt.executeUpdate();
			if (resultCnt > 0) {
				System.out.println("���������� �����Ǿ����ϴ�");
			} else {
				System.out.println("������ ���� �ʾҽ��ϴ�\nȮ�� �� ��õ����ּ���");
			}
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

	static void modify(PreparedStatement pstmt, Connection conn) {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		try {
			System.out.println("������ �μ��� �μ���ȣ  �Է�>>");
			int deptno = sc.nextInt();
			sc.nextLine();

			System.out.println("�μ� �̸� �Է�>>");
			String dname = sc.nextLine();
			System.out.println("��ġ �Է�>>");
			String loc = sc.nextLine();

			String sql = "update dept set dname=?, loc=?" + " where deptno=?";

			System.out.println("sql:" + sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2, loc);
			pstmt.setInt(3, deptno);
			rs = pstmt.executeQuery();
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

	static void print(PreparedStatement pstmt, Connection conn) {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		try {
			String sql = "select * from dept";
			pstmt = conn.prepareStatement(sql);// �޼���� prepare~
			// ���� ������ ����
//		pstmt.setInt(1, 10);
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

	static void search(PreparedStatement pstmt, Connection conn) {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		try {
			System.out.println("�μ��� �Է�>>");
			String dname = sc.nextLine();
			String sql = "select * from dept where dname='" + dname + "'";
			pstmt = conn.prepareStatement(sql);// �޼���� prepare~
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

package examples.copy;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DeptDao {
	// DAO=Data Access Object
	// �����ͺ��̽� ó���ϴ� Ŭ����

	// MVC->Model,View,Controller
	// model->Service(�����͸� ���������� �и��ϴ� �������� ����ϴ� Ŭ����), Dao(�����ͺ��̽� ó�� ����)
	// �����ͺ��̽�
	public void insert(PreparedStatement pstmt, Connection conn) {
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

	public void delete(PreparedStatement pstmt, Connection conn) {
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

	public void modify(PreparedStatement pstmt, Connection conn) {
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

	public void print(PreparedStatement pstmt, Connection conn) {
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

	public void search(PreparedStatement pstmt, Connection conn) {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		List<Dept> deptList = new ArrayList<>();
		try {
			sc.nextLine();
			System.out.println("�μ��� �Է�>>");
			String dname = sc.nextLine();
			String sql = "select * from dept where dname='" + dname + "'";
			pstmt = conn.prepareStatement(sql);// �޼���� prepare~
			rs = pstmt.executeQuery();
			System.out.println("�μ� ����Ʈ");
			System.out.println("====================================");
//			 ResultSet->��� ����
			while (rs.next()) {
				Dept dept = new Dept(rs.getInt("deptno"), rs.getString("dname"), rs.getString("loc"));
				deptList.add(dept);
			}
//				System.out.print(rs.getInt("deptno") + "\t");
//				System.out.print(rs.getString("dname") + "\t");
//				System.out.print(rs.getString("loc") + "\n");
//			}
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

	public static List<Dept> deptList() {
		// VO: Value Object
		// DTO:Data Transfer Object

		// JDBC��� ��ü
		Statement stmt = null;
//		Connection conn;
		ConnectionProvider cp=new ConnectionProvider();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// DaoŬ���� �߰�
		List<Dept> deptList = new ArrayList<>();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �Էµ� ������ ����
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");//driver�� ó�� ������ �� �� ���� ȣ��
			System.out.println("Oracle ����̹� �ε� ����");
			cp.getConnection();
			//Connection pool ���� Connection��ü�� ������ �ִٰ� �ʿ��� �ʿ� ����(ó�� �ð� ����)
			//Transaction ó��(�� Transaction�� ���� ������ ��ٷȴٰ� �ٸ� Transaction ó��)
			//Connection�ϳ� static���� �ΰ� ���� �ȵ�(�������ӽ� ���� �߻�)
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return deptList;
	}
}

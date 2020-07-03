package using_DAO_XXX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ManageMain {

	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ClassNotFoundException {

		// 0. ����̹� LIB �߰�
		// 1. �����ͺ��̽� ����̹� �ε�
		// Class.forName(����̹� Ŭ���� ��ü�̸�)
		// Oracle : oracle.jdbc.driver.OracleDriver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("�����ͺ��̽� ����̹� �ε� �Ϸ�...!!");

		UnivManager univManager = new UnivManager();

		while (true) {
			System.out.println("Select Menu");
			System.out.println("--------------------------------");
			System.out.println("1. University 2. Company 3.Cafe");
			System.out.println("--------------------------------");

			int select = sc.nextInt();

			switch (select) {
			case 1:
				univManager.univManager();
				break;
			case 2:
				break;
			}

		}

	}

	/////////////////////////////////////////////////////////////////
	// EMP MANAGER
	/////////////////////////////////////////////////////////////////

	private static void empManager() {
		System.out.println("EMP Manager Menu");
		System.out.println("=========================================");
		System.out.println("1. List  2. Insert  3. Search  4. Delete  5. Edit  ");
		System.out.println("=========================================");

		int select = sc.nextInt();

		switch (select) {
		case 1:
			System.out.println("��ü ����Ʈ ���");
			empList();
			break;
		case 2:
			System.out.println("��� ������ �Է��մϴ�.");
			empInsert();
			break;
		case 3:
			System.out.println("��� ������ �˻��մϴ�.");
			empSearch();
			break;
		case 4:
			System.out.println("��������� �����մϴ�.");
			empDelete();
			break;
		case 5:
			System.out.println("��������� �����մϴ�.");
			empEdit();
			break;

		}

	}

	private static void empEdit() {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ����� �Է����� ����

		System.out.println("�����ϰ��� �ϴ� ����̸� : ");
		sc.nextLine();
		String searchName = sc.nextLine();

		try {
			// 0. ����̹� LIB �߰�
			// 1. �����ͺ��̽� ����̹� �ε�
			// Class.forName(����̹� Ŭ���� ��ü�̸�)
			// Oracle : oracle.jdbc.driver.OracleDriver
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. �����ͺ��̽� ����

			// String url = "jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
			// �ּ� : localhost or 127.0.0.1
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, pw);

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			// ���� !!!!!
			// �Էµ� �����ϰ��� �ϴ� �̸��� �����Ͱ� �����ؾ� ���� ������ �Է��� ���۽�ŵ�ϴ�.
			// �׸��� �̸��� �����ʹ� ���������� �־�� �մϴ�.
			// ���������� �ƴ϶�� �������� �࿡ ���� ó���� �̷�����ϴ�.
			// ���� ���������� ������ ������ �����ϰ� ó���մϴ�.

			stmt = conn.createStatement();

			String selectSql = "select * from emp where ename='" + searchName + "'";

			rs = stmt.executeQuery(selectSql);

			// ���� ���� ������
			int sEmpno = 0;
			String sEname = "";
			int sDeptno = 0;
			int sSal = 0;

			if (rs.next()) {
				sEmpno = rs.getInt("empno");
				sEname = rs.getString("ename");
				sSal = rs.getInt("sal");
				sDeptno = rs.getInt("deptno");
			} else {
				System.out.println("�˻��Ͻ� �̸��� �����Ͱ� �������� �ʽ��ϴ�.");
				return;
			}

			// ����� �Է����� ����
			System.out.println("��� ������ �Է����ּ���.");

			System.out.println("�����ȣ : " + sEmpno);
			System.out.println("�����ȣ�� �������� �ʽ��ϴ�.");

			System.out.println("����̸� ( " + sEname + "  ) : ");
			String ename = sc.nextLine();

			System.out.println("�޿�( " + sSal + "  ) : ");
			int sal = sc.nextInt();

			System.out.println("�μ���ȣ( " + sDeptno + "  ) : ");
			int deptno = sc.nextInt();

			// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

			String sql = "update emp  set  ename=?, sal=?, deptno=?  where empno=?";

			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ename);
			pstmt.setInt(2, sal);
			pstmt.setInt(3, deptno);
			pstmt.setInt(4, sEmpno);

			int resultCnt = pstmt.executeUpdate();

			if (resultCnt > 0) {
				System.out.println("���������� ���� �Ǿ����ϴ�.");
				System.out.println(resultCnt + "���� �����Ǿ����ϴ�.");
			} else {
				System.out.println("������ �����ʾҽ��ϴ�. Ȯ���� �� �õ����ּ���.");
			}

			// 4. �����ͺ��̽� ���� ����
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. �����ͺ��̽� ���� ����
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private static void empList() {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		try {
			// 0. ����̹� LIB �߰�
			// 1. �����ͺ��̽� ����̹� �ε�
			// Class.forName(����̹� Ŭ���� ��ü�̸�)
			// Oracle : oracle.jdbc.driver.OracleDriver
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. �����ͺ��̽� ����

			// String url = "jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
			// �ּ� : localhost or 127.0.0.1
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, pw);

			String sql = "select * from emp  order by ename";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			int resultCnt = 0;
			System.out.println("�˻� ���");
			System.out.println("=======================================================================");
			while (rs.next()) {
				System.out.print(rs.getInt("empno") + "\t");
				System.out.printf("%6s", rs.getString("ename") + "\t");
				System.out.printf("%9s", rs.getString("job") + "\t");
				System.out.print(rs.getInt("mgr") + "\t");
				System.out.print(rs.getString("hiredate").substring(0, 10) + "\t");
				// System.out.print(rs.getString("hiredate") + "\t");
				System.out.print(rs.getInt("sal") + "\t");
				System.out.print(rs.getInt("comm") + "\t");
				System.out.print(rs.getInt("deptno") + "\n");
				resultCnt++;
			}
			if (resultCnt < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
			}

			System.out.println("=======================================================================");

			// 4. �����ͺ��̽� ���� ����
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. �����ͺ��̽� ���� ����
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private static void empDelete() {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ����� �Է����� ����

		System.out.println("�����ϰ��� �ϴ� ����̸� : ");
		sc.nextLine();
		String searchName = sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		try {
			// 0. ����̹� LIB �߰�
			// 1. �����ͺ��̽� ����̹� �ε�
			// Class.forName(����̹� Ŭ���� ��ü�̸�)
			// Oracle : oracle.jdbc.driver.OracleDriver
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. �����ͺ��̽� ����

			// String url = "jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
			// �ּ� : localhost or 127.0.0.1
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, pw);

			String sql = "delete from emp  where ename=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);

			int resultCnt = pstmt.executeUpdate();

			if (resultCnt < 1) {
				System.out.println("������ ������ �˻� ����� �����ϴ�.");
			} else {
				System.out.println(resultCnt + "���� ���� �Ǿ����ϴ�.");
			}

			System.out.println("=================================");

			// 4. �����ͺ��̽� ���� ����
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. �����ͺ��̽� ���� ����
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private static void empSearch() {
		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ����� �Է����� ����

		System.out.println("�˻��ϰ��� �ϴ� ����̸� : ");
		sc.nextLine();
		String searchName = sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		try {
			// 0. ����̹� LIB �߰�
			// 1. �����ͺ��̽� ����̹� �ε�
			// Class.forName(����̹� Ŭ���� ��ü�̸�)
			// Oracle : oracle.jdbc.driver.OracleDriver
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. �����ͺ��̽� ����

			// String url = "jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
			// �ּ� : localhost or 127.0.0.1
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, pw);

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			// Mysql
			// "SELECT * FROM epm WHERE name LIKE ?"
			// psmt.setString(1, "%"+name+"%");

			// Oracle
			// select * from emp where ename like '%'||?||'%'

			String sql = "select * from emp  where ename like '%'||?||'%'";
			// String sql = "select * from emp where ename=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);
			rs = pstmt.executeQuery();

			int resultCnt = 0;

			System.out.println("�˻� ���");
			System.out.println("=======================================================================");
			while (rs.next()) {
				System.out.print(rs.getInt("empno") + "\t");
				System.out.print(rs.getString("ename") + "\t");
				System.out.print(rs.getString("job") + "\t");
				System.out.print(rs.getInt("mgr") + "\t");
				System.out.print(rs.getString("hiredate").substring(0, 10) + "\t");
				System.out.print(rs.getInt("sal") + "\t");
				System.out.print(rs.getInt("comm") + "\t");
				System.out.print(rs.getInt("deptno") + "\n");
				resultCnt++;
			}
			if (resultCnt < 1) {
				System.out.println("�˻� ����� �����ϴ�.");
			}

			System.out.println("=======================================================================");

			// 4. �����ͺ��̽� ���� ����
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. �����ͺ��̽� ���� ����
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

	private static void empInsert() {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		// ����� �Է����� ����
		System.out.println("��� ������ �Է����ּ���.");

		System.out.println("�����ȣ : ");
		int empno = sc.nextInt();

		System.out.println("����̸� : ");
		sc.nextLine();
		String ename = sc.nextLine();

		System.out.println("����(����) : ");
		String job = sc.nextLine();

		System.out.println("������ : ");
		int mgr = sc.nextInt();

		System.out.println("�Ի��� : ");
		sc.nextLine();
		String hiredate = sc.nextLine();

		System.out.println("�޿� : ");
		int sal = sc.nextInt();

		System.out.println("���� : ");
		int comm = sc.nextInt();

		System.out.println("�μ���ȣ : ");
		int deptno = sc.nextInt();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		try {
			// 0. ����̹� LIB �߰�
			// 1. �����ͺ��̽� ����̹� �ε�
			// Class.forName(����̹� Ŭ���� ��ü�̸�)
			// Oracle : oracle.jdbc.driver.OracleDriver
			// Class.forName("oracle.jdbc.driver.OracleDriver");

			// 2. �����ͺ��̽� ����

			// String url = "jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
			// �ּ� : localhost or 127.0.0.1
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// Connection ��ü ����
			conn = DriverManager.getConnection(url, user, pw);

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			String sql = "insert into emp " + " (empno, ename, job, mgr, hiredate, sal, comm, deptno) "
					+ " values (?, ?, ?, ?, ?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, empno);
			pstmt.setString(2, ename);
			pstmt.setString(3, job);
			pstmt.setInt(4, mgr);
			pstmt.setString(5, hiredate); // 20200601
			pstmt.setInt(6, sal);
			pstmt.setInt(7, comm);
			pstmt.setInt(8, deptno);

			int resultCnt = pstmt.executeUpdate();

			if (resultCnt > 0) {
				System.out.println("���������� �Է� �Ǿ����ϴ�.");
				System.out.println(resultCnt + "���� �ԷµǾ����ϴ�.");
			} else {
				System.out.println("�Է��� �����ʾҽ��ϴ�. Ȯ���� �� �õ����ּ���.");
			}

			// 4. �����ͺ��̽� ���� ����
			// pstmt.close();
			// conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			// 4. �����ͺ��̽� ���� ����
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		}

	}

}
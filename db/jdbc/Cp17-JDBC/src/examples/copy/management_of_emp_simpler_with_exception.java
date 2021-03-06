package examples.copy;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class management_of_emp_simpler_with_exception {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Scanner sc = new Scanner(System.in);
		String sql = "";
		// 1. DB드라이버 로드

		while (true) {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Oracle 드라이버 로드 성공");

			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String pw = "tiger";

			// 2. 데이터베이스에 접속
			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("데이터베이스에 접속했습니다");

			System.out.println("----------------------------------");
			System.out.println("1.입력 2.수정 3.삭제 4.리스트 5.검색\n6.선택창으로 돌아가기 7. 종료");
			System.out.println("----------------------------------");

			try_catch(pstmt, conn);
		}
	}

	static void insert(PreparedStatement pstmt, Connection conn) throws SQLException {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		System.out.println("사원번호  입력>>");
		int empno = sc.nextInt();
		sc.nextLine();
		System.out.println("사원 이름 입력>>");
		String ename = sc.nextLine();
		System.out.println("급여 입력>>");
		String sal = sc.nextLine();
		System.out.println("직급 입력>>");
		String job = sc.nextLine();
		String sql = "insert into emp (empno, ename,sal,job)" + " values(?,?,?,?)";
		pstmt = conn.prepareStatement(sql);
		pstmt.setInt(1, empno);
		pstmt.setString(2, ename);
		pstmt.setString(3, sal);
		pstmt.setString(4, job);

		int resultCnt = pstmt.executeUpdate();
		if (resultCnt > 0) {
			System.out.println("정상적으로 입력되었습니다");
			System.out.println(resultCnt + "행이 입력되었습니다");
		} else {
			System.out.println("입력이 되지 않았습니다\n확인 후 재시도해주세요");
		}
	}

	static void delete(PreparedStatement pstmt, Connection conn) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("사원번호  입력>>");
		int empno = sc.nextInt();
		sc.nextLine();

		String sql = "delete from emp where empno='" + empno + "'";
		pstmt = conn.prepareStatement(sql);

		int resultCnt = pstmt.executeUpdate();
		if (resultCnt > 0) {
			System.out.println("정상적으로 삭제되었습니다");
		} else {
			System.out.println("삭제가 되지 않았습니다\n확인 후 재시도해주세요");
		}
	}

	static void modify(PreparedStatement pstmt, Connection conn) throws SQLException {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		System.out.println("수정할 직원의 사원번호  입력>>");
		int empno = sc.nextInt();
		sc.nextLine();

		System.out.println("사원 이름 입력>>");
		String ename = sc.nextLine();
		System.out.println("급여 입력>>");
		String sal = sc.nextLine();
		System.out.println("직급 입력>>");
		String job = sc.nextLine();

//			sql = "update emp set ename='"+ename+"' and job='"+job+"'"
//					+ " where empno="+empno;
		String sql = "update emp set ename=?, sal=?, job=?" + " where empno=?";

		pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, ename);
		pstmt.setString(2, sal);
		pstmt.setString(3, job);
		pstmt.setInt(4, empno);
		// 변수 데이터 설정
//		pstmt.setInt(1, 10);
		rs = pstmt.executeQuery();

	}

	static void print(PreparedStatement pstmt, Connection conn) throws SQLException {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		String sql = "select * from emp";
		pstmt = conn.prepareStatement(sql);// 메서드는 prepare~
		// 변수 데이터 설정
//		pstmt.setInt(1, 10);
		rs = pstmt.executeQuery();
		System.out.println("사원 리스트");
		System.out.println("==================================");
		// ResultSet->결과 참조
		while (rs.next()) {
			System.out.print(rs.getInt("empno") + "\t");
			System.out.print(rs.getString("ename") + "\t");
			System.out.print(rs.getString("sal") + "\t");
			System.out.print(rs.getString("job") + "\n");
		}
		System.out.println("==================================");

	}

	static void search(PreparedStatement pstmt, Connection conn) throws SQLException {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		System.out.println("이름 입력>>");
		String ename = sc.nextLine();
		String sql = "select * from emp where ename='" + ename + "'";
//		String sql="select * from emp where ename like '%'||?||'";
		//Oracle
		//select * from emp where ename like '%'||?||'%'
		pstmt = conn.prepareStatement(sql);// 메서드는 prepare~
		// 변수 데이터 설정
//		pstmt.setInt(1, 10);
		rs = pstmt.executeQuery();
		System.out.println("사원 리스트");
		System.out.println("==================================");
		// ResultSet->결과 참조
		while (rs.next()) {
			System.out.print(rs.getInt("empno") + "\t");
			System.out.print(rs.getString("ename") + "\t");
			System.out.print(rs.getString("sal") + "\t");
			System.out.print(rs.getString("job") + "\n");
//			System.out.print(rs.getString("hiredate").substring(0,10)+"\t");
		}
		System.out.println("==================================");

	}

	static void try_catch(PreparedStatement pstmt, Connection conn) throws SQLException {
		Scanner sc = new Scanner(System.in);
		ResultSet rs = null;
		try {
			int choice = sc.nextInt();

			switch (choice) {
			case 1:
				insert(pstmt, conn);
				break;
			case 2:
				modify(pstmt, conn);
				break;
			case 3:
				delete(pstmt, conn);
				break;
			case 4:
				print(pstmt, conn);
				break;
			case 5:
				search(pstmt, conn);
				break;
			case 7:
				System.exit(0);
			}

		} finally {
			// 4.데이터베이스 연결 종료
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

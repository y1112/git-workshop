package using_DAO_XXX;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UnivDao {
	
	// DAO = Data Acess Object
	// �����ͺ��̽� ó�� �ϴ� Ŭ����
	// 
	
	// MVC -> Model, View, Controller
	// model -> Service , Dao
	// �����ͺ��̽� 
	
	

	public int deptEdit(Univ newDept, Connection conn) {

		// JDBC ��� ��ü
		//Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;

		
		try {
			// Connection ��ü ����
			//conn = ConnectionProvider.getConnection();

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			// ���� !!!!!
			// �Էµ� �����ϰ��� �ϴ� �̸��� �����Ͱ� �����ؾ� ���� ������ �Է��� ���۽�ŵ�ϴ�.
			// �׸��� �̸��� �����ʹ� ���������� �־�� �մϴ�.
			// ���������� �ƴ϶�� �������� �࿡ ���� ó���� �̷�����ϴ�.
			// ���� ���������� ������ ������ �����ϰ� ó���մϴ�.
	
			String sql = "update dept  set  dname=?, loc=? " + " where deptno=?";

			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, newDept.getDname());
			pstmt.setString(2, newDept.getLoc());
			pstmt.setInt(3, newDept.getDeptno());

			resultCnt = pstmt.executeUpdate();

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

//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}

		}
		
		return resultCnt;

	}

	public int deptDelete(String dname) {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;


		try {

			// Connection ��ü ����
			conn = ConnectionProvider.getConnection();

			String sql = "delete from dept  where dname=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			
			resultCnt = pstmt.executeUpdate();

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
		
		return resultCnt;

	}

	public List<Univ> deptSearch(String dname) {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		
		List<Univ> list = new ArrayList<Univ>();


		try {

			// 2. �����ͺ��̽� ����
			// Connection ��ü ����
			conn = ConnectionProvider.getConnection();

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			// Mysql
			// "SELECT * FROM dept WHERE dname LIKE ?"
			// psmt.setString(1, "%"+name+"%");

			// Oracle
			// select * from dept where dname like '%'||?||'%'

			String sql = "select * from dept  where dname like '%'||?||'%' or  loc like '%'||?||'%'";
			// String sql = "select * from dept where dname=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dname);
			pstmt.setString(2, dname);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				list.add(new Univ(
						rs.getInt("deptno"), 
						rs.getString("dname"), 
						rs.getString("loc")));
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
		
		return list;

	}

	public int deptInsert(Univ dept) {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;


		try {

			// Connection ��ü ����
			conn = ConnectionProvider.getConnection();

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			String sql = "insert into dept  (deptno, dname, loc)  values (?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dept.getDeptno());
			pstmt.setString(2, dept.getDname());
			pstmt.setString(3, dept.getLoc());

			resultCnt = pstmt.executeUpdate();


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
		
		return resultCnt;

	}

	public List<Univ> deptList() {
		
		// VO : Value Object , read only , getter
		// DTO : Data Transfer Object  getter/setter , toString, equals

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		// Dao Ŭ���� �߰�
		List<Univ> deptList= new ArrayList<>();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		try {
			// 2. �����ͺ��̽� ����
			conn = ConnectionProvider.getConnection();

			String sql = "select * from dept  order by dname";

			stmt = conn.createStatement();

			rs = stmt.executeQuery(sql);

			
			while (rs.next()) {
				
				Univ dept = new Univ(
						rs.getInt("deptno"), 
						rs.getString("dname"), 
						rs.getString("loc"));
				
				deptList.add(dept);
				
//				System.out.print(rs.getInt("deptno") + "\t");
//				System.out.printf("%15s", rs.getString("dname") + "\t");
//				System.out.printf("%15s", rs.getString("loc") + "\n");
//				resultCnt++;
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

//			if (conn != null) {
//				try {
//					conn.close();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			}

		}
		return deptList;

	}

	
	
	
	public int deptSearchCount(String searchName, Connection conn) {
		
		//Connection conn = null;
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		int rowCnt = 0;
		
		try {
			//conn = ConnectionProvider.getConnection();
			
			String sql = "select count(*) from dept where dname=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				rowCnt = rs.getInt(1);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		return rowCnt;
	}
	
	
	public Univ deptSearchName(String searchName, Connection conn) {
		
		
		Univ dept = null;
		
		//Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//conn = ConnectionProvider.getConnection();
			
			String sql = "select * from dept where dname=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				dept = new Univ(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return dept;
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
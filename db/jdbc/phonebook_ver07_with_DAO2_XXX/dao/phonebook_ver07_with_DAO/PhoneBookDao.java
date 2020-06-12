package phonebook_ver07_with_DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/*�����ͺ��̽��� �̿��ؼ� ģ���� ������ CRUD �ϴ� ���α׷��� ����� ���ô�.

- JDBC �̿�
- ���̺� ���� DDL ���� ( �����ð��� �ۼ��� ���̺��� �̿��ذ� �˴ϴ�.)
- ������ ó���� ���� ���ǿ� DML �ۼ�
- �ٽɱ�� Ŭ������ DAO Ŭ������ ������ �����ϴ� ������ �ۼ����ּ���.*/

public class PhoneBookDao {
	public void searchInfo(String searchName) {
	};

	public int editInfo(PhoneUnivInfo univ, Connection conn) {
		// JDBC ��� ��ü
		// Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;

		try {
			// Connection ��ü ����
			// conn = ConnectionProvider.getConnection();

			// 3. SQL ó��
			// Statement or PreparedStatement
			// pstmt = conn.prepareStatement(SQL ����)

			// ���� !!!!!
			// �Էµ� �����ϰ��� �ϴ� �̸��� �����Ͱ� �����ؾ� ���� ������ �Է��� ���۽�ŵ�ϴ�.
			// �׸��� �̸��� �����ʹ� ���������� �־�� �մϴ�.
			// ���������� �ƴ϶�� �������� �࿡ ���� ó���� �̷�����ϴ�.
			// ���� ���������� ������ ������ �����ϰ� ó���մϴ�.

			// �ϴ� �̸��� �ּҸ� ����
			String sql = "update phoneInfo_univ  set student_name=?, addr=? " + " where deptno=?";

			pstmt = conn.prepareStatement(sql);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, univ.getName());
//					pstmt.setString(3, univ.getPhoneNumber());
			pstmt.setString(2, univ.getAddr());
			pstmt.setInt(3, univ.getIdx());
//					pstmt.setString(5, univ.getEmail());
//					pstmt.setString(6, univ.getMajor());
//					pstmt.setString(7, univ.getGrade());
//					pstmt.setInt(8, univ.getRef_idx());

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

//					if (conn != null) {
//						try {
//							conn.close();
//						} catch (SQLException e1) {
//							// TODO Auto-generated catch block
//							e1.printStackTrace();
//						}
//					}

		}

		return resultCnt;
	};

	public int deleteInfo(String searchName) {

		// JDBC ��� ��ü
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int resultCnt = 0;

		try {

			// Connection ��ü ����
			conn = ConnectionProvider.getConnection();

			String sql = "delete from phoneInfo_univ  where student_name=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);

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
	};

	public PhoneUnivInfo univSearchName(String searchName, Connection conn) {
		PhoneUnivInfo univ = null;

		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// conn = ConnectionProvider.getConnection();

			String sql = "select * from phoneInfo_univ where student_name=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				univ = new PhoneUnivInfo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return univ;
	}

	public int univSearchCount(String searchName, Connection conn) {
		// Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int rowCnt = 0;

		try {
			// conn = ConnectionProvider.getConnection();

			String sql = "select count(*) from phoneInfo_univ where student_name=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, searchName);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				rowCnt = rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rowCnt;
	}

	public int insertInfo(PhoneUnivInfo univ) {
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

			String sql = "insert into phoneInfo_univ  (idx,student_name,phonenumber,addr,email,major,grade,ref_idx) "
					+ " values (?, ?, ?,?, ?, ?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, univ.getIdx());
			pstmt.setString(2, univ.getName());
			pstmt.setString(3, univ.getPhoneNumber());
			pstmt.setString(4, univ.getAddr());
			pstmt.setString(5, univ.getEmail());
			pstmt.setString(6, univ.getMajor());
			pstmt.setString(7, univ.getGrade());
			pstmt.setInt(8, univ.getRef_idx());
			System.out.println("����");
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
	};

	public List<PhoneUnivInfo> phoneInfoList() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		/*
		 * public PhoneUnivInfo(int idx,String name, String phoneNumber, String addr,
		 * String email, String major, String grade) }
		 */
		List<PhoneUnivInfo> phoneList = new ArrayList<PhoneUnivInfo>();
//'n'
		try {
			conn = ConnectionProvider.getConnection();
			String sql = "select * from phoneInfo_univ";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			// phoneInfo�� id��(pk) �߰�
			// String name, String phoneNumber, String addr, String email
			// PhoneInfo �߻�Ŭ������ ��ü ���� ����->����Ŭ���������� ��ü ����
			// PhoneUnivInfo(int idx,String name, String phoneNumber,
			// String addr, String email, String major, String grade)

//			---------- -------- ------------ 
//			IDX        NOT NULL NUMBER(6)    
//			FR_U_MAJOR          VARCHAR2(20) 
//			FR_U_YEAR           NUMBER(1)    
//			FR_REF              NUMBER(7)    

			while (rs.next()) {
//				PhoneInfo phone=new PhoneInfo(rs.getString(1));
				PhoneUnivInfo phone = new PhoneUnivInfo(rs.getInt("idx"), rs.getString("student_name"),
						rs.getString("phonenumber"), rs.getString("addr"), rs.getString("email"), rs.getString("major"),
						rs.getString("grade"), rs.getInt("ref_idx"));
//				PhoneUnivInfo phone = new PhoneUnivInfo(rs.getInt("idx"), rs.getString("name"),
//						rs.getString("phoneNumber"), rs.getString("addr"), rs.getString("email"), rs.getString("major"),
//						rs.getString("grade"), rs.getInt("ref_idx"));
				phone.showAllInfo();
				phoneList.add(phone);
			}
			System.out.println("=======================================================================");
			
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
			return phoneList;
		}
	}
}
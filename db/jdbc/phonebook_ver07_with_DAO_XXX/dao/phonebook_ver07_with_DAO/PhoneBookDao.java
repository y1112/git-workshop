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
	public void searchInfo() {
	};

	public void editInfo() {
	};

	public void deleteInfo() {
	};

	public void createInfo() {
	};

	public void showAllInfo() {
	};

	public List<PhoneUnivInfo> phoneInfoList() {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PhoneUnivInfo> phoneList = new ArrayList<>();

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
				PhoneUnivInfo phone = new PhoneUnivInfo(rs.getInt("idx"), rs.getString("name"),
						rs.getString("phoneNumber"), rs.getString("addr"), rs.getString("email"), rs.getString("major"),
						rs.getString("grade"));
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
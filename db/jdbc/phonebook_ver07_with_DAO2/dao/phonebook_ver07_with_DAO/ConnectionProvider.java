package phonebook_ver07_with_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

	public static Connection getConnection() throws SQLException {
		// 2. �����ͺ��̽� ����
		Connection conn = null;

		// String url = "jdbc:oracle:thin:@�ּ�:��Ʈ:�����ͺ��̽��̸�";
		// �ּ� : localhost or 127.0.0.1
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String pw = "tiger";

		// Connection ��ü ����

		conn = DriverManager.getConnection(url, user, pw);

		return conn;
	}

}
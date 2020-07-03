package ex;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTest1 {

	public static void main(String[] args) 
			  {
		Connection conn=null;
		
		//1. DB����̹� �ε�
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		System.out.println("Oracle ����̹� �ε� ����");
		
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pw="tiger";
		
		//2. �����ͺ��̽��� ����
		conn=DriverManager.getConnection(url,user,pw);
		System.out.println("�����ͺ��̽��� �����߽��ϴ�");
		
		//Ʈ����� ����
		conn.setAutoCommit(false);
		
		//3. sql�� ������ ó��->������ �����ӿ�ũ������ �̺κи� ó���ϵ��� �ϴ� Ŭ���� ����
		
		//commit: ����ó�� �Ϸ�/ó�� �Ϸ�
		conn.commit();//Ʈ�����
		
		//4. close
		conn.close();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			try {
				conn.rollback();//���� ���� �߻��� �ѹ�
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} 
	}
	
	

}

package Univ_using_DAO;

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
			System.out.println("----------------------------------");
			System.out.println("1. university  2. company 3. club");
			System.out.println("----------------------------------");

			int select = sc.nextInt();

			switch (select) {
			case 1:
				univManager.univManager();
				// empManager();
				break;
			case 2:
				univManager.univManager();
				break;
			case 3:
				break;
			default:
				System.out.println("�ùٸ� �Է��� �ƴմϴ�\n�ٽ� �Է����ּ���");
				break;
			}

		}

	}

}
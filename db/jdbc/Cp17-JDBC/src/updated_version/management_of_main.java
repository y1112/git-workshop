package updated_version;

import java.util.Scanner;


public class management_of_main {

	public static void main(String[] args) {
		management_of_emp_simpler_ver e = new management_of_emp_simpler_ver();
		management_of_dept_simpler_ver d = new management_of_dept_simpler_ver();
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("----------------------------------");
			System.out.println("������ ���� ����");
			System.out.println("1.��� 2.�μ� 3.����");
			System.out.println("----------------------------------");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				e.main(args);
				break;
			case 2:
				d.main(args);
				break;
			case 3:
				System.exit(0);
			}
		}

	}

}

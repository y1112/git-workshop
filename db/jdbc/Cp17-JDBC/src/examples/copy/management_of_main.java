package examples.copy;

import java.util.Scanner;

/*
����������α׷�
���
	1.�Է�
	2.����(�̸�,�μ�,�޿�)
	3.����(�����ȣpk����)
	4.����Ʈ(��ü)
	5.�˻�(�̸�����)
	
�μ��������α׷�
���
	1.�Է�
	2.����(�μ��̸�,��ġ)
	3.����(�μ���ȣpk����)
	4.����Ʈ(��ü)
	5.�˻�(�μ��̸� or ��������)
*/
public class management_of_main {

	public static void main(String[] args) {
		management_of_emp e = new management_of_emp();
		management_of_dept d = new management_of_dept();
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

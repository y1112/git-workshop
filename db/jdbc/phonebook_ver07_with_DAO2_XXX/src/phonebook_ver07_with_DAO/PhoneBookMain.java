package phonebook_ver07_with_DAO;
//�ڵ� ���� ����: 06/02

//�ϴ� ���������θ� ����->����/ȸ��/��ȣȸ ���� ��� �߰�

import java.util.Scanner;

public class PhoneBookMain {

	static Scanner sc = new Scanner(System.in);// �ܺ� Ŭ�������� ������ �� �� �ֵ��� static ����

	public static void main(String[] args) throws ClassNotFoundException {
			Class.forName("oracle.jdbc.driver.OracleDriver");//�����ͺ��̽� ����̹� �ε�
			System.out.println("�����ͺ��̽� ����̹� �ε� �Ϸ�");
		
			PhoneBookManager phoneManager=new PhoneBookManager();
			
			while(true) {
				System.out.println("����");
				System.out.println("1.���б�");//�ϴ� ���б��� �ڵ� �ۼ�
				System.out.println("2.ȸ��");
				System.out.println("3.��ȣȸ");
				
				int select=sc.nextInt();
				
				switch(select) {
				case 1:
					phoneManager.PhoneBookManager();
					break;
				case 2:
					break;
				case 3:
					break;
				default:
					System.out.println("�߸��� �Է°��Դϴ�.\n�ٽ� �Է��� �ּ���.");
					break;
				}
			}
		
		
	}

}

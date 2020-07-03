package phonebook_ver07_with_DAO;


import java.util.InputMismatchException;
//2.interface����� ��� ǥ��->�̸��� �ο��� ��� ������� �޴� ����
import java.util.Scanner;


public class PhoneBookMain {

	public static void main(String[] args) {
//		PhoneBookManager manager = new PhoneBookManager(100);
		// �̱��� ���� ���� ��
		PhoneBookManager manager = PhoneBookManager.getInstance();
		Scanner kb = new Scanner(System.in);
		int type = 0;
		while (true) {
			while (true) {
				Menu.printMenu();
//				System.out.println("1.���� 2.ȸ�� 3.��ȣȸ");
//				System.out.println("[�Է��ϰ��� �ϴ� ��ȣ�� �Է����ּ���]");
//				System.out.println("------------------------------------------------------------------------------------");
				try {
					int select = Integer.parseInt(kb.nextLine());// ���� ���� �ذ�
					if (!(select >= Choice.UNIV && select <= Choice.CAFE)) {
						UserException e = new UserException("�Է� ���� �߻�");
						throw e;
					}
					
					

				} catch (NumberFormatException e) {
					System.out.println("[�ùٸ� ��ȣ�� �Է����ּ���]");
					continue;
				} catch (UserException e) {
					System.out.println("[�ùٸ� ��ȣ�� �Է����ּ���]");
					continue;
				} catch (Exception e) {
					System.out.println("[�ùٸ� ��ȣ�� �Է����ּ���]");
					continue;
				} finally {
//					kb.hasNextLine();
				}
				break;
			}
			Menu.printMainMenu();
//			int select = manager.kb.nextInt();
//			manager.kb.nextLine();
			try {
				type =Integer.parseInt(kb.nextLine());
				if (!(type >= Choice.CREATE && type <= Choice.EXIT)) {
					UserException e = new UserException("�޴� ������ ����� ����Դϴ�\n�ٽ� Ȯ�� �� �Է����ּ���");
					
					//�������� ���� �߻�
					throw e;
				}

			} catch (InputMismatchException e) {
				System.out.println("�߸��� �޴� �Է��Դϴ�.\nȮ���Ͻð� �ٽ� �Է����ּ���");
//				manager.kb.nextLine();
				continue;
			}catch(UserException e) {
				System.out.println("�޴� ������ ��� ���� �Է��Դϴ�\n�ٽ� Ȯ�� �� �Է����ּ���");
				continue;
			}catch (Exception e) {// ����ó���� ������ �����
				System.out.println("�߸��� �޴� �Է��Դϴ�");
				continue;
			} finally {
//				manager.kb.nextLine();
			}

			switch (type) {
			case Choice.CREATE:
				manager.createInfo();
				break;
			case Choice.EDIT:
				manager.editInfo();
				break;
			case Choice.DELETE:
				manager.deleteInfo();
				break;
			case Choice.SEARCH:
				manager.searchInfo();
				break;
			case Choice.SHOW:
//				manager.showAllInfo();
				manager.phonebookList();
				break;
			case Choice.EXIT:
				System.out.println("���α׷��� �����մϴ�");
				return;
			}
		}

	}

}
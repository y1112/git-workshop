package phonebook_ver07_with_DAO;
//���� ��¥: 20/06/02

//�ϴ� ���������θ� ����

import java.util.List;


public class PhoneBookManager {
	PhoneBookDao pdao = new PhoneBookDao();

	// ����/ȸ��/��ȣȸ �� ��� �������� �޾ƿͼ� ���
	public void PhoneBookManager() {
		System.out.println("=======================================================================");
		System.out.println("1.��� 2.�Է� 3. �˻� 4.���� 5.���� 6.����");
		System.out.println("=======================================================================");

		int select = PhoneBookMain.sc.nextInt();

		switch (select) {
		case 1:
			System.out.println("��ü ����Ʈ ���");
			phonebookList();
			break;
		case 2:
			phonebookInsert();
			break;
		case 3:
			phonebookSearch();
			break;
		case 4:
			phonebookDelete();
			break;
		case 5:
			phonebookModify();
			break;
		case 6:
			System.exit(0);
			break;
		default:
			System.out.println("�ùٸ� �Է��� �ƴմϴ�.\n�ٽ� �Է����ּ���");
			break;
		}
	}

	/*
	 * PhoneUnivInfo phone = new PhoneUnivInfo(rs.getInt("idx"),
	 * rs.getString("name"), rs.getString("phoneNumber"), rs.getString("addr"),
	 * rs.getString("email"), rs.getString("major"), rs.getString("grade"));
	 */
	public void phonebookList() {// ����Ʈ 3���� ������ ��(����/ȸ��/��ȣȸ)
		List<PhoneUnivInfo> univList = pdao.phoneInfoList();

		if (univList != null && !univList.isEmpty()) {
			for (int i = 0; i < univList.size(); i++) {
				System.out.println("�������");
				System.out.printf("%5s", univList.get(i).getIdx() + "\t");
				System.out.printf("%12s", univList.get(i).getName() + "\t");
				System.out.printf("%12", univList.get(i).getPhoneNumber() + "\t");
				System.out.printf("%12", univList.get(i).getAddr() + "\t");
				System.out.printf("%12", univList.get(i).getEmail() + "\t");
				System.out.printf("%12", univList.get(i).getMajor() + "\t");
				System.out.printf("%12", univList.get(i).getGrade() + "\t");
				System.out.printf("%5s", univList.get(i).getRef_idx() + "\n");
			}
		} else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�");
		}

	}

	public void phonebookInsert() {

		// ����� �Է����� ����
		System.out.println("���л� ������ �Է����ּ���.");

		System.out.println("�й� : ");
		int univ_index = PhoneBookMain.sc.nextInt();
		System.out.println("�̸� : ");
		PhoneBookMain.sc.nextLine();
		String student_name = PhoneBookMain.sc.nextLine();
		System.out.println("��ȭ��ȣ : ");
		String phonenumber = PhoneBookMain.sc.nextLine();
		System.out.println("�ּ� : ");
		String addr = PhoneBookMain.sc.nextLine();
		System.out.println("�̸��� : ");
		String email = PhoneBookMain.sc.nextLine();
		System.out.println("���� : ");
		String major = PhoneBookMain.sc.nextLine();
		System.out.println("�г�(1����) : ");
		String grade = PhoneBookMain.sc.nextLine();
		System.out.println("����Ű ");// ����Ű ���ߵ�@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		int ref_idx = 1;

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		PhoneUnivInfo univ = new PhoneUnivInfo(univ_index, student_name, phonenumber, addr, email, major, grade,
				ref_idx);
		univ.showAllInfo();
		int resultCnt = pdao.insertInfo(univ);

		if (resultCnt > 0) {
			System.out.println("���������� �Է� �Ǿ����ϴ�.");
			System.out.println(resultCnt + "���� �ԷµǾ����ϴ�.");
		} else {
			System.out.println("�Է��� �����ʾҽ��ϴ�. Ȯ���� ��õ����ּ���.");
		}
	}

	public void phonebookSearch() {
		System.out.println("ã���� �ϴ� �л� �̸�>>");
		PhoneBookMain.sc.nextLine();
		String searchName = PhoneBookMain.sc.nextLine();

//		List<PhoneUnivInfo> univList=pdao.searchInfo(searchName);
		System.out.println("");

	}

	public void phonebookDelete() {

		System.out.println("�����ϰ��� �ϴ� �μ��̸� : ");
		PhoneBookMain.sc.nextLine();
		String searchName = PhoneBookMain.sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		int resultCnt = pdao.deleteInfo(searchName);

		if (resultCnt < 1) {
			System.out.println("������ ������ �˻� ����� �����ϴ�.");
		} else {
			System.out.println(resultCnt + "���� ���� �Ǿ����ϴ�.");
		}

		System.out.println("=================================");
	}

	public void phonebookModify() {
	}
}
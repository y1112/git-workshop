package phonebook_ver07_with_DAO;
//���� ��¥: 20/06/02

//idx �ٲ�ߵ�@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

import java.util.List;
/*
 1.
 PhoneBookManager Ŭ������ �ν��Ͻ� �� �ִ� �ϳ� ���� �ʵ��� �ڵ庯��(�̱��� ����)
 �̱��� ����: ���� ������ ������ ��ü ������ ��, ��ü�� ���� ȣ��, ���� ������ ��
 new�� ���� ���� private �̿�, �� �ϳ��� ������ ��ü �����ϴ� Ŭ���� ����
 
 2.interface����� ��� ǥ��->�̸��� �ο��� ��� ������� �޴� ����
 
 3.PhoneInfor Ŭ������ interface �߰�->�߻� Ŭ����ȭ
 */
import java.util.Scanner;

//PhoneInfoŸ���� �迭�� ģ������ ������ ����, ����, ����, �˻�, ���
public class PhoneBookManager {// �̱��� ����
	private static PhoneBookManager manager = new PhoneBookManager(100);
	PhoneInfo[] books;
	List<PhoneInfo> phoneList;
	Scanner kb;
	int numOfInfo;// int numOfInfo �迭�� ����� ����� ����

	PhoneBookDao dao = new PhoneBookDao();

	private PhoneBookManager(int N) {// �̱���
		books = new PhoneInfo[N];
		kb = new Scanner(System.in);
		numOfInfo = 0;
	}

	public static PhoneBookManager getInstance() {
		return manager;
	}

	public void searchInfo() {
		String name = kb.nextLine();
		for (int i = 0; i < numOfInfo; i++) {
			if (books[i].checkName(name))
				books[i].showBasicInfo();
		}
	}

	public void editInfo() {
		String editName = kb.nextLine();
		int index = searchIndex(editName);

		// idx �����ؾߵ�@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		int idx = 0;

		if (index < 0) {
			System.out.println("[�����ϰ��� �ϴ� �̸��� ������ �����ϴ�]");
			System.out.println("------------------------------------------------------------------------------------");
			return;
		} else {

			while (true) {
				System.out.println("[���� ������ �Է��� �����մϴ�]");
				System.out.println("[�̸��� " + editName + "�Դϴ�]");
//			String name = kb.nextLine();//�̸��� �ٲٸ� �ȵ�
				System.out.println("[��ȭ��ȣ�� �Է����ּ���]");
				String phoneNumber = kb.nextLine();
				System.out.println("[�ּҸ� �Է����ּ���]");
				String addr = kb.nextLine();
				System.out.println("[�̸����� �Է����ּ���]");
				String email = kb.nextLine();

				try {
					if (phoneNumber.trim().isEmpty() || addr.trim().isEmpty() || email.trim().isEmpty()) {
						UserException e = new UserException("���� �߻�");
						throw e;
					}
				} catch (UserException e) {
					System.out.println("������ �Է����� �ʾҽ��ϴ�\n�ٽ� �Է����ּ���");
					continue;
				} catch (Exception e) {
					System.out.println("������ �Է����� �ʾҽ��ϴ�\n�ٽ� �Է����ּ���");
					continue;
				} finally {
					kb.hasNextLine();
				}
				PhoneInfo info = null;

				if (books[index] instanceof PhoneCompanyInfo) {
					System.out.println("[ȸ�縦 �Է����ּ���]");
					String company = kb.nextLine();
					System.out.println("[�μ��� �Է����ּ���]");
					String dept = kb.nextLine();
					System.out.println("[��å�� �Է����ּ���]");
					String job = kb.nextLine();
					try {
						if (company.trim().isEmpty() || dept.trim().isEmpty() || job.trim().isEmpty()) {
							UserException e = new UserException("���� �߻�");
							throw e;
						}
					} catch (UserException e) {
						System.out.println("������ �Է����� �ʾҽ��ϴ�\n�ٽ� �Է����ּ���");
						continue;
					} catch (Exception e) {
						System.out.println("������ �Է����� �ʾҽ��ϴ�\n�ٽ� �Է����ּ���");
						continue;
					} finally {
						kb.hasNextLine();
					}
					info = new PhoneCompanyInfo(idx, editName, phoneNumber, addr, email, company, dept, job);
				} else if (books[index] instanceof PhoneClubInfo) {
					System.out.println("[��ȣȸ �̸��� �Է����ּ���]");
					String cafeName = kb.nextLine();
					System.out.println("[�г����� �Է����ּ���]");
					String nickName = kb.nextLine();
					info = new PhoneClubInfo(idx, editName, phoneNumber, addr, email, cafeName, nickName);
				}
				books[index] = info;
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;
			}
		}

	}

	public void deleteInfo() {
		System.out.println("[�����ϰ��� �ϴ� �̸��� �Է����ּ���]");
		String name = kb.nextLine();
		int index = searchIndex(name);
		if (index < 0) {
			System.out.println("[�����ϰ��� �ϴ� �̸��� ������ �����ϴ�]");
			System.out.println("------------------------------------------------------------------------------------");
		} else {
			for (int i = index; i < numOfInfo - 1; i++) {
				if (i == index) {
					books[i] = books[i + 1];
				}
			}
		}
//			books[numOfInfo - 1] = null;

		numOfInfo--;
	}

	public void addInfo(PhoneInfo p) {
		books[numOfInfo++] = p;
	}

	public void createInfo() {
//	int select=sc.nextInt();
//	sc.nextLine();
		int select = 0;

		// idx �ٲ�ߵ�@@@@@@@@@@@@@@@@@@@@@@@
		int idx = 0;

		while (true) {
			// �⺻ ���� ����:�̸�,��ȭ��ȣ,�ּ�,�̸���
			System.out.println("[�̸��� �Է����ּ���]");
			String name = kb.nextLine();
			System.out.println("[��ȭ��ȣ�� �Է����ּ���]");
			String phoneNumber = kb.nextLine();
			System.out.println("[�ּҸ� �Է����ּ���]");
			String addr = kb.nextLine();
			System.out.println("[�̸����� �Է����ּ���]");
			String email = kb.nextLine();
			// ���� �Է½� ����� ����ó��
//		System.out.println("select:" + select);
			try {
				if (name.trim().isEmpty() || phoneNumber.trim().isEmpty() || addr.trim().isEmpty()
						|| email.trim().isEmpty()) {
					EmptyException e = new EmptyException("���� �߻�");
					throw e;
				}
			} catch (EmptyException e) {
				System.out.println("������ �Է����� �ʾҽ��ϴ�.\n�ٽ� �Է��ϼ���");
				continue;
			} catch (Exception e) {
				System.out.println("������ �Է����� �ʾҽ��ϴ�.\n�ٽ� �Է��ϼ���");
				continue;
			} finally {
//				kb.hasNextLine();
			}
			PhoneInfo info = null;
			switch (select) {
			case Choice.UNIV:
				System.out.println("[������ �Է����ּ���]");
				String major = kb.nextLine();
				System.out.println("[������ �Է����ּ���]");
				String grade = kb.nextLine();
				info = new PhoneUnivInfo(idx, name, phoneNumber, addr, email, major, grade);
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;

			case Choice.COMPANY:
				System.out.println("[ȸ�縦 �Է����ּ���]");
				String company = kb.nextLine();
				System.out.println("[�μ��� �Է����ּ���]");
				String dept = kb.nextLine();
				System.out.println("[��å�� �Է����ּ���]");
				String job = kb.nextLine();
				info = new PhoneCompanyInfo(idx, name, phoneNumber, addr, email, company, dept, job);
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;

			case Choice.CAFE:
				System.out.println("[��ȣȸ �̸��� �Է����ּ���]");
				String cafeName = kb.nextLine();
				System.out.println("[�г����� �Է����ּ���]");
				String nickName = kb.nextLine();
				info = new PhoneClubInfo(idx, name, phoneNumber, addr, email, cafeName, nickName);
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;

			default:
				System.out.println("[�������� ������ �ƴմϴ�]\n[�޴��� �ٽ� �������ּ���]");
				System.out.println(
						"------------------------------------------------------------------------------------");
				return;
			}
			addInfo(info);
			break;
		}

		// �Է¹����� ���� ���� ó��

//		addInfo(new PhoneInfo(name, phoneNumber, addr, email));
	}

	int searchIndex(String name) {
		int searchIndex = -1;// �ش� �ε����� ã�� ������ ��� �⺻��
		for (int i = 0; i < numOfInfo; i++) {
			if (books[i].checkName(name)) {
				searchIndex = i;
				break;
			}
		}
		return searchIndex;
	}

	public void showInfo() {
		System.out.println("[�˻��Ͻ� �̸��� �Է����ּ���]");
		String name = kb.nextLine();
		int index = searchIndex(name);
		if (index < 0) {
			System.out.println("[�˻��Ͻ� �̸��� ������ �����ϴ�]");
			System.out.println("------------------------------------------------------------------------------------");
		} else {
			System.out.println("------------------------------------------------------------------------------------");
			books[index].showBasicInfo();
			System.out.println("------------------------------------------------------------------------------------");
		}
	}

	public void showAllInfo() {

		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("[��ü ������ ����մϴ�]");
		for (int i = 0; i < numOfInfo; i++) {
			books[i].showBasicInfo();

		}
	}

	/*
	 * 
	 * PhoneUnivInfo phone = new PhoneUnivInfo(rs.getInt("idx"),
	 * rs.getString("name"), rs.getString("phoneNumber"), rs.getString("addr"),
	 * rs.getString("email"), rs.getString("major"), rs.getString("grade"));
	 */
	// ----------------------------------------------06/02 phonebookList() �߰�
	public void phonebookList(List<PhoneInfo> phoneList) {
//		List<PhoneUnivInfo> phoneList=dao.phoneInfoList();

		if (phoneList != null && !phoneList.isEmpty()) {

			for (int i = 0; i < phoneList.size(); i++) {
				if (phoneList instanceof PhoneUnivInfo) {
					System.out.printf("%5s", phoneList.get(i).getIdx() + "\t");
					System.out.printf("%12s", phoneList.get(i).getName() + "\t");
					System.out.printf("%12s", phoneList.get(i).getPhoneNumber() + "\t");
					System.out.printf("%12s", phoneList.get(i).getAddr() + "\t");
					System.out.printf("%12s", phoneList.get(i).getEmail() + "\t");
//					System.out.printf("%12s", phoneList.get(i).getMajor() + "\t");
//					System.out.printf("%12s", phoneList.get(i).getGrade() + "\n");
				}
			}
		} else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�");
		}
	}
}
package phonebook_ver07_with_DAO;

//2.interface����� ��� ǥ��->�̸��� �ο��� ��� ������� �޴� ����
//3.PhoneInfor Ŭ������ interface �߰�->�߻� Ŭ����ȭ
//System.out.println("1.���� 2.ȸ�� 3.��ȣȸ");
public interface Choice {
	// �������̽� �� ����� ���� public static final ���� ����
	int CREATE = 1, EDIT = 2, DELETE = 3, SEARCH = 4, SHOW = 5, EXIT = 6;
	int UNIV = 1, COMPANY = 2, CAFE = 3;
}
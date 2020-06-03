package Univ_using_DAO;
/*
 * �Է��� �� ���Ἲ �������Ƕ����� pk�ߺ��Ȱ� �Է��� ��쿡 ���ѷ��� ���� ���� �߻�
 * 
 * */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ClubManager {

	ClubDao dao = new ClubDao();

	/////////////////////////////////////////////////////////////////
	// DEPT MANAGER
	/////////////////////////////////////////////////////////////////

	public void clubManager() {

		System.out.println("Club Manager Menu");
		System.out.println("=========================================");
		System.out.println("1. List  2. Insert  3. Search  4. Delete  5. Edit  ");
		System.out.println("=========================================");

		int select = ManageMain.sc.nextInt();

		switch (select) {
		case 1:
			System.out.println("��ü ����Ʈ ���");
			deptList();
			break;
		case 2:
			System.out.println("��ȣȸ ������ �Է��մϴ�.");
			deptInsert(); // ������� �Էµ����� dept ��ü�� ��Ƽ� dao insert �޼���� ����
			break;
		case 3:
			System.out.println("��ȣȸ ������ �˻��մϴ�.");
			deptSearch(); // ����ڰ� �Է��� �̸��� dao search ����
			break;
		case 4:
			System.out.println("��ȣȸ ������ �����մϴ�.");
			deptDelete(); // �̸� �Ǵ� �μ���ȣ dao delete ����
			break;
		case 5:
			System.out.println("��ȣȸ ������ �����մϴ�.");
			deptEdit(); // 1. ���������� �ϴ� ������ ���� Ȯ�� -> 2.����ڷκ��� ������ �޾Ƽ� ����
			break;

		}

	}

	public void deptEdit() {

		Connection conn = null;
		
		try {
			conn = ConnectionProvider.getConnection();

			conn.setAutoCommit(false); // �⺻���� true : �ڵ� Ŀ��

			// 1. �����ϰ��� �ϴ� ������ ���� Ȯ�� -> 2.����ڷκ��� ������ �޾Ƽ� ����

			// ����� �Է����� ����

			System.out.println("�����ϰ��� �ϴ� ȸ�� �̸� : ");
			ManageMain.sc.nextLine();
			String searchName = ManageMain.sc.nextLine();

			// 1. �����ϰ��� �ϴ� ������ ���� Ȯ��
			int rowCnt = dao.deptSearchCount(searchName, conn);
			// System.out.println(rowCnt);

			if (rowCnt > 0) {
				Club dept2 = dao.deptSearchName(searchName, conn);

				if (dept2 == null) {
					System.out.println("ã���ô� �̸��� ������ ���������ʽ��ϴ�.");
					return;
				}

				// ����� �Է����� ����
				System.out.println("��ȣȸ ������ �Է����ּ���.");

				System.out.println("ȸ�� ��ȣ : " + dept2.getIdx());
				System.out.println("�μ� ��ȣ�� �������� �ʽ��ϴ�.");

				System.out.println("ȸ�� �̸� (" + dept2.getMember_name() + "  ) : ");
				String member_name = ManageMain.sc.nextLine();
				System.out.println("�г��� ( " + dept2.getMember_nickname() + "  ) : ");
				String member_nickname = ManageMain.sc.nextLine();
				System.out.println("��ȣȸ�� ( " + dept2.getClub_name() + "  ) : ");
				String club_name = ManageMain.sc.nextLine();
				System.out.println("��ȭ��ȣ ( " + dept2.getPhonenumber() + "  ) : ");
				String phonenumber = ManageMain.sc.nextLine();
				System.out.println("�ּ� ( " + dept2.getAddr() + "  ) : ");
				String addr = ManageMain.sc.nextLine();
				System.out.println("�̸��� ( " + dept2.getEmail() + "  ) : ");
				String email = ManageMain.sc.nextLine();


				// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

				Club club = new Club(dept2.getIdx(), 
						member_name,member_nickname,club_name,phonenumber,
						addr,email
						);

				int resultCnt = dao.comEdit(club, conn);

				if (resultCnt > 0) {
					System.out.println("���������� ���� �Ǿ����ϴ�.");
					System.out.println(resultCnt + "���� �����Ǿ����ϴ�.");
				} else {
					System.out.println("������ �����ʾҽ��ϴ�. Ȯ���� �� �õ����ּ���.");
				}

			} else {
				System.out.println("ã���ô� �̸��� ������ ���������ʽ��ϴ�.");
			}
			


			conn.commit();
			
			

		} catch (SQLException e) {
			
			if(conn != null) {
				try {
					conn.rollback();
				} catch (SQLException e1) {
					System.out.println("conn.close()");
					e1.printStackTrace();
				}
			}
			
			
			
			e.printStackTrace();
		}

	}

	public void deptInsert() {

		// ����� �Է����� ����
		System.out.println("ȸ�� ������ �Է����ּ���.");

		System.out.println("ȸ����ȣ : ");
		int idx = ManageMain.sc.nextInt();
		System.out.println("ȸ���̸� : ");
		ManageMain.sc.nextLine();
		String member_name = ManageMain.sc.nextLine();
		System.out.println("�г���: ");
		String member_nickname = ManageMain.sc.nextLine();
		System.out.println("��ȣȸ�� : ");
		String club_name = ManageMain.sc.nextLine();
		System.out.println("��ȭ��ȣ : ");
		String phonenumber = ManageMain.sc.nextLine();
		System.out.println("�ּ�: ");
		String addr = ManageMain.sc.nextLine();
		System.out.println("�̸��� : ");
		String email = ManageMain.sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		Club club = new Club(idx,
				member_name,member_nickname,club_name,phonenumber,
				addr,email
				);

		int resultCnt = dao.deptInsert(club);

		if (resultCnt > 0) {
			System.out.println("���������� �Է� �Ǿ����ϴ�.");
			System.out.println(resultCnt + "���� �ԷµǾ����ϴ�.");
		} else {
			System.out.println("�Է��� �����ʾҽ��ϴ�. Ȯ���� �� �õ����ּ���.");
		}

	}

	public void deptDelete() {

		// ����� �Է����� ����

		System.out.println("�����ϰ��� �ϴ� ȸ�� �̸� : ");
		ManageMain.sc.nextLine();
		String searchName = ManageMain.sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		int resultCnt = dao.deptDelete(searchName);

		if (resultCnt < 1) {
			System.out.println("������ ������ �˻� ����� �����ϴ�.");
		} else {
			System.out.println(resultCnt + "���� ���� �Ǿ����ϴ�.");
		}

		System.out.println("=================================");

	}

	public void deptSearch() {

		// ����� �Է����� ����

		System.out.println("�˻��ϰ��� �ϴ� ȸ���̸� : ");
		ManageMain.sc.nextLine();
		String searchName = ManageMain.sc.nextLine();

		List<Club> list = dao.deptSearch(searchName);

		System.out.println("�˻� ���");
		System.out.println("-------------------------------------------------------------------------------------");
		for (Club d : list) {
			System.out.printf("%5s", d.getIdx() + "\t");
			System.out.printf("%10s", d.getMember_name() + "\t");
			System.out.printf("%10s", d.getMember_nickname() + "\t");
			System.out.printf("%10s", d.getClub_name() + "\t");
			System.out.printf("%10s", d.getPhonenumber() + "\t");
			System.out.printf("%10s", d.getAddr() + "\t");
			System.out.printf("%10s", d.getEmail() + "\n");
		}
		System.out.println("-------------------------------------------------------------------------------------");

	}

	public void deptList() {

		List<Club> deptList = dao.deptList();

		if (deptList != null && !deptList.isEmpty()) {

			for (int i = 0; i < deptList.size(); i++) {
				System.out.printf("%5s", deptList.get(i).getIdx() + "\t");
				System.out.printf("%10s", deptList.get(i).getMember_name() + "\t");
				System.out.printf("%10s", deptList.get(i).getMember_nickname() + "\t");
				System.out.printf("%10s", deptList.get(i).getClub_name() + "\t");
				System.out.printf("%10s", deptList.get(i).getPhonenumber() + "\t");
				System.out.printf("%10s", deptList.get(i).getAddr() + "\t");
				System.out.printf("%10s", deptList.get(i).getEmail() + "\n");
			}
		} else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�.");
		}

	}

}
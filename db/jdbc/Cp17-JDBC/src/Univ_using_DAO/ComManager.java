package Univ_using_DAO;
/*
 * �Է��� �� ���Ἲ �������Ƕ����� pk�ߺ��Ȱ� �Է��� ��쿡 ���ѷ��� ���� ���� �߻�
 * 
 * */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class ComManager {

	ComDao dao = new ComDao();

	/////////////////////////////////////////////////////////////////
	// DEPT MANAGER
	/////////////////////////////////////////////////////////////////

	public void comManager() {

		System.out.println("DEPT Manager Menu");
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
			System.out.println("��� ������ �Է��մϴ�.");
			deptInsert(); // ������� �Էµ����� dept ��ü�� ��Ƽ� dao insert �޼���� ����
			break;
		case 3:
			System.out.println("��� ������ �˻��մϴ�.");
			deptSearch(); // ����ڰ� �Է��� �̸��� dao search ����
			break;
		case 4:
			System.out.println("��� ������ �����մϴ�.");
			deptDelete(); // �̸� �Ǵ� �μ���ȣ dao delete ����
			break;
		case 5:
			System.out.println("��� ������ �����մϴ�.");
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

			System.out.println("�����ϰ��� �ϴ� �μ� �̸� : ");
			ManageMain.sc.nextLine();
			String searchName = ManageMain.sc.nextLine();

			// 1. �����ϰ��� �ϴ� ������ ���� Ȯ��
			int rowCnt = dao.deptSearchCount(searchName, conn);
			// System.out.println(rowCnt);

			if (rowCnt > 0) {
				Com dept2 = dao.deptSearchName(searchName, conn);

				if (dept2 == null) {
					System.out.println("ã���ô� �̸��� ������ ���������ʽ��ϴ�.");
					return;
				}

				// ����� �Է����� ����
				System.out.println("��� ������ �Է����ּ���.");

				System.out.println("��� ��ȣ : " + dept2.getIdx());
				System.out.println("�μ� ��ȣ�� �������� �ʽ��ϴ�.");

				System.out.println("��� �̸� (" + dept2.getEmployee_name() + "  ) : ");
				String employee_name = ManageMain.sc.nextLine();

				System.out.println("��ȭ��ȣ ( " + dept2.getPhonenumber() + "  ) : ");
				String phonenumber = ManageMain.sc.nextLine();
				System.out.println("�ּ� ( " + dept2.getAddress() + "  ) : ");
				String address = ManageMain.sc.nextLine();
				System.out.println("�̸��� ( " + dept2.getEmail() + "  ) : ");
				String email = ManageMain.sc.nextLine();
				System.out.println("ȸ��� ( " + dept2.getCom_name() + "  ) : ");
				String com_name = ManageMain.sc.nextLine();
				System.out.println("�μ� ( " + dept2.getDept() + "  ) : ");
				String dept = ManageMain.sc.nextLine();
				System.out.println("���� ( " + dept2.getJob() + "  ) : ");
				String job = ManageMain.sc.nextLine();


				// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

				Com com = new Com(dept2.getIdx(), 
						employee_name,phonenumber,address,email,com_name,dept,job
						);

				int resultCnt = dao.comEdit(com, conn);

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
		System.out.println("��� ������ �Է����ּ���.");

		System.out.println("�����ȣ : ");
		int idx = ManageMain.sc.nextInt();
		System.out.println("����̸� : ");
		ManageMain.sc.nextLine();
		String employee_name = ManageMain.sc.nextLine();
		System.out.println("��ȭ��ȣ: ");
		String phonenumber = ManageMain.sc.nextLine();
		System.out.println("�ּ� : ");
		String address = ManageMain.sc.nextLine();
		System.out.println("�̸��� : ");
		String email = ManageMain.sc.nextLine();
		System.out.println("ȸ���: ");
		String com_name = ManageMain.sc.nextLine();
		System.out.println("�μ� : ");
		String dept = ManageMain.sc.nextLine();
		System.out.println("���� : ");
		String job = ManageMain.sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		Com com = new Com(idx,
				employee_name,phonenumber,address,email,com_name,dept,job
				);


		int resultCnt = dao.deptInsert(com);

		if (resultCnt > 0) {
			System.out.println("���������� �Է� �Ǿ����ϴ�.");
			System.out.println(resultCnt + "���� �ԷµǾ����ϴ�.");
		} else {
			System.out.println("�Է��� �����ʾҽ��ϴ�. Ȯ���� �� �õ����ּ���.");
		}

	}

	public void deptDelete() {

		// ����� �Է����� ����

		System.out.println("�����ϰ��� �ϴ� ȸ���̸� : ");
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

		List<Com> list = dao.deptSearch(searchName);

		System.out.println("�˻� ���");
		System.out.println("-------------------------------------------------------------------------------------");
		for (Com d : list) {
			System.out.printf("%5s", d.getIdx() + "\t");
			System.out.printf("%10s", d.getEmployee_name() + "\t");
			System.out.printf("%10s", d.getPhonenumber() + "\t");
			System.out.printf("%10s", d.getAddress() + "\t");
			System.out.printf("%10s", d.getEmail() + "\t");
			System.out.printf("%10s", d.getCom_name() + "\t");
			System.out.printf("%10s", d.getDept() + "\t");
			System.out.printf("%10s", d.getJob() + "\n");
		}
		System.out.println("-------------------------------------------------------------------------------------");

	}

	public void deptList() {

		List<Com> deptList = dao.deptList();

		if (deptList != null && !deptList.isEmpty()) {

			for (int i = 0; i < deptList.size(); i++) {
				System.out.printf("%5s", deptList.get(i).getIdx() + "\t");
				System.out.printf("%10s", deptList.get(i).getEmployee_name() + "\t");
				System.out.printf("%10s", deptList.get(i).getPhonenumber() + "\t");
				System.out.printf("%10s", deptList.get(i).getAddress() + "\t");
				System.out.printf("%10s", deptList.get(i).getEmail() + "\t");
				System.out.printf("%10s", deptList.get(i).getCom_name() + "\t");
				System.out.printf("%10s", deptList.get(i).getDept() + "\t");
				System.out.printf("%10s", deptList.get(i).getJob() + "\n");
			}
		} else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�.");
		}

	}

}
package Univ_using_DAO;
/*
 * �Է��� �� ���Ἲ �������Ƕ����� pk�ߺ��Ȱ� �Է��� ��쿡 ���ѷ��� ���� ���� �߻�
 * 
 * */
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class UnivManager {

	UnivDao dao = new UnivDao();

	/////////////////////////////////////////////////////////////////
	// DEPT MANAGER
	/////////////////////////////////////////////////////////////////

	public void univManager() {

		System.out.println("Univ Manager Menu");
		System.out.println("=========================================");
		System.out.println("1. List  2. Insert  3. Search  4. Delete  5. Edit  ");
		System.out.println("=========================================");

		int select = ManageMain.sc.nextInt();

		switch (select) {
		case 1:
			System.out.println("��ü �л�  ����Ʈ ���");
			deptList();
			break;
		case 2:
			System.out.println("�л� ������ �Է��մϴ�.");
			deptInsert(); // ������� �Էµ����� dept ��ü�� ��Ƽ� dao insert �޼���� ����
			break;
		case 3:
			System.out.println("�л� ������ �˻��մϴ�.");
			deptSearch(); // ����ڰ� �Է��� �̸��� dao search ����
			break;
		case 4:
			System.out.println("�л� ������ �����մϴ�.");
			deptDelete(); // �̸� �Ǵ� �μ���ȣ dao delete ����
			break;
		case 5:
			System.out.println("�л� ������ �����մϴ�.");
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
				Univ dept = dao.deptSearchName(searchName, conn);

				if (dept == null) {
					System.out.println("ã���ô� �̸��� ������ ���������ʽ��ϴ�.");
					return;
				}

				// ����� �Է����� ����
				System.out.println("�л� ������ �Է����ּ���.");

				System.out.println("�л� ��ȣ : " + dept.getIdx());
				System.out.println("�μ� ��ȣ�� �������� �ʽ��ϴ�.");

				System.out.println("�л� �̸� (" + dept.getStudent_name() + "  ) : ");
				String student_name = ManageMain.sc.nextLine();
				System.out.println("��ȭ��ȣ ( " + dept.getAddr() + "  ) : ");
				String phonenumber = ManageMain.sc.nextLine();
				System.out.println("�ּ� ( " + dept.getAddr() + "  ) : ");
				String addr = ManageMain.sc.nextLine();
				System.out.println("�̸��� ( " + dept.getEmail() + "  ) : ");
				String email = ManageMain.sc.nextLine();
				System.out.println("���� ( " + dept.getMajor() + "  ) : ");
				String major = ManageMain.sc.nextLine();
				System.out.println("�г� ( " + dept.getGrade() + "  ) : ");
				String grade = ManageMain.sc.nextLine();

				// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

				Univ newDept = new Univ(dept.getIdx(), 
						student_name,
						phonenumber,
						addr,email,major,grade
						);

				int resultCnt = dao.univEdit(newDept, conn);

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
		System.out.println("�л� ������ �Է����ּ���.");

		System.out.println("�л���ȣ : ");
		int idx = ManageMain.sc.nextInt();
		System.out.println("�л��̸� : ");
		ManageMain.sc.nextLine();
		String student_name = ManageMain.sc.nextLine();
		System.out.println("���� : ");
		String phonenumber = ManageMain.sc.nextLine();
		System.out.println("�ּ� : ");
		String addr = ManageMain.sc.nextLine();
		System.out.println("�̸��� : ");
		String email = ManageMain.sc.nextLine();
		System.out.println("����: ");
		String major = ManageMain.sc.nextLine();
		System.out.println("�г� : ");
		String grade = ManageMain.sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		Univ dept = new Univ(
				idx,student_name,phonenumber,
				addr,email,major,grade
				);

		int resultCnt = dao.deptInsert(dept);

		if (resultCnt > 0) {
			System.out.println("���������� �Է� �Ǿ����ϴ�.");
			System.out.println(resultCnt + "���� �ԷµǾ����ϴ�.");
		} else {
			System.out.println("�Է��� �����ʾҽ��ϴ�. Ȯ���� �� �õ����ּ���.");
		}

	}

	public void deptDelete() {

		// ����� �Է����� ����

		System.out.println("�����ϰ��� �ϴ� �μ��̸� : ");
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

		System.out.println("�˻��ϰ��� �ϴ� �μ��̸� : ");
		ManageMain.sc.nextLine();
		String searchName = ManageMain.sc.nextLine();

		List<Univ> list = dao.deptSearch(searchName);

		System.out.println("�˻� ���");
		System.out.println("-------------------------------------------------------------------------------------");
		for (Univ d : list) {
			System.out.printf("%5s", d.getIdx() + "\t");
			System.out.printf("%10s", d.getStudent_name() + "\t");
			System.out.printf("%10s", d.getPhonenumber() + "\t");
			System.out.printf("%10s", d.getAddr() + "\t");
			System.out.printf("%10s", d.getEmail() + "\t");
			System.out.printf("%10s", d.getMajor() + "\t");
			System.out.printf("%5s", d.getGrade() + "\n");
		}
		System.out.println("-------------------------------------------------------------------------------------");

	}

	public void deptList() {

		List<Univ> deptList = dao.deptList();

		if (deptList != null && !deptList.isEmpty()) {

			for (int i = 0; i < deptList.size(); i++) {
				System.out.printf("%5s", deptList.get(i).getIdx() + "\t");
				System.out.printf("%10s", deptList.get(i).getStudent_name() + "\t");
				System.out.printf("%10s", deptList.get(i).getPhonenumber() + "\t");
				System.out.printf("%10s", deptList.get(i).getAddr() + "\t");
				System.out.printf("%10s", deptList.get(i).getEmail() + "\t");
				System.out.printf("%10s", deptList.get(i).getMajor() + "\t");
				System.out.printf("%5s", deptList.get(i).getGrade() + "\n");
			}
		} else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�.");
		}

	}

}
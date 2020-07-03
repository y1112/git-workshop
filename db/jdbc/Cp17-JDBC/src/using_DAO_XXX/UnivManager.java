package using_DAO_XXX;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UnivManager {

	UnivDao dao = new UnivDao();

	/////////////////////////////////////////////////////////////////
	// DEPT MANAGER
	/////////////////////////////////////////////////////////////////

	public void univManager() {

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
			System.out.println("�μ� ������ �Է��մϴ�.");
			deptInsert(); // ������� �Էµ����� dept ��ü�� ��Ƽ� dao insert �޼���� ����
			break;
		case 3:
			System.out.println("�μ� ������ �˻��մϴ�.");
			deptSearch(); // ����ڰ� �Է��� �̸��� dao search ����
			break;
		case 4:
			System.out.println("�μ� ������ �����մϴ�.");
			deptDelete(); // �̸� �Ǵ� �μ���ȣ dao delete ����
			break;
		case 5:
			System.out.println("�μ� ������ �����մϴ�.");
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
				System.out.println("�μ� ������ �Է����ּ���.");

				System.out.println("�μ� ��ȣ : " + dept.getDeptno());
				System.out.println("�μ� ��ȣ�� �������� �ʽ��ϴ�.");

				System.out.println("�μ� �̸� ( " + dept.getDname() + "  ) : ");
				String dname = ManageMain.sc.nextLine();

				System.out.println("���� ( " + dept.getLoc() + "  ) : ");
				String loc = ManageMain.sc.nextLine();

				// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

				Univ newDept = new Univ(dept.getDeptno(), dname, loc);

				int resultCnt = dao.deptEdit(newDept, conn);

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
		System.out.println("�μ� ������ �Է����ּ���.");

		System.out.println("�μ���ȣ : ");
		int deptno = ManageMain.sc.nextInt();
		System.out.println("�μ��̸� : ");
		ManageMain.sc.nextLine();
		String dname = ManageMain.sc.nextLine();
		System.out.println("���� : ");
		String loc = ManageMain.sc.nextLine();

		// ���� �Է¿� ���� ����ó���� �־�� �ϳ� �̹� ���������� ��� �� �ԷµȰ����� ó���մϴ�.

		Univ dept = new Univ(deptno, dname, loc);

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
		System.out.println("======================================");
		for (Univ d : list) {
			System.out.printf("%5s", d.getDeptno() + "\t");
			System.out.printf("%12s", d.getDname() + "\t");
			System.out.printf("%12s", d.getLoc() + "\n");
		}
		System.out.println("======================================");

	}

	public void deptList() {

		List<Univ> deptList = dao.deptList();

		if (deptList != null && !deptList.isEmpty()) {

			for (int i = 0; i < deptList.size(); i++) {
				System.out.printf("%5s", deptList.get(i).getDeptno() + "\t");
				System.out.printf("%12s", deptList.get(i).getDname() + "\t");
				System.out.printf("%12s", deptList.get(i).getLoc() + "\n");
			}
		} else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�.");
		}

	}

}
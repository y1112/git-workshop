package updated_version;

import java.util.List;
import java.util.Scanner;

import examples.copy.Dept;
import examples.copy.DeptDao;

public class DEPTManager {
	DeptDao dao=new DeptDao();
	public static void deptManager() {
		Scanner sc=new Scanner(System.in);
			// 2. �����ͺ��̽��� ����
//			conn = DriverManager.getConnection(url, user, pw);
			System.out.println("�����ͺ��̽��� �����߽��ϴ�");

			System.out.println("----------------------------------");
			System.out.println("1.�Է� 2.���� 3.���� 4.����Ʈ 5.�˻�\n6.����â���� ���ư��� 7. ����");
			System.out.println("----------------------------------");
			int n = sc.nextInt();
			switch (n) {
			case 1:// �Է�
//			insert(pstmt, conn);
				break;
			case 2:// ����(�μ��̸�,��ġ)
//			modify(pstmt, conn);
				break;
			case 3:// ����(�����ȣ)
//			delete(pstmt, conn);
				break;
			case 4:// ����Ʈ
//			print(pstmt, conn);
				break;
			case 5:// �˻�(�̸�)
//			search(pstmt, conn);
				break;
			case 7:
				System.exit(0);
			}


	}
	public void deptList() {
		List<Dept> deptList=dao.deptList();
		if(deptList!=null && !deptList.isEmpty()) {
			for(int i=0;i<deptList.size();i++) {
				System.out.printf("%5d",deptList.get(i).getDeptno()+"\t");
				System.out.printf("%12s",deptList.get(i).getDname()+"\t");
				System.out.printf("%12s",deptList.get(i).getLoc()+"\n");
			}
		}else {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�");
		}
	}
}
package ver05;

/*
 1.
 PhoneBookManager 클래스의 인스턴스 수 최대 하나 넘지 않도록 코드변경(싱글톤 패턴)
 싱글톤 패턴: 여러 곳에서 동일한 객체 접근할 때, 객체가 자주 호출, 재사용 가능할 때
 new를 막기 위해 private 이용, 단 하나의 생성된 객체 참조하는 클래스 변수
 
 2.interface기반의 상수 표현->이름에 부여된 상수 기반으로 메뉴 선택
 
 3.PhoneInfor 클래스에 interface 추가->추상 클래스화
 */
import java.util.Scanner;

//PhoneInfo타입의 배열로 친구들의 정보를 저장, 수정, 삭제, 검색, 출력
public class PhoneBookManager {// 싱글톤 패턴
	private static PhoneBookManager manager = new PhoneBookManager(100);
	PhoneInfo[] books;
	Scanner kb;
	int numOfInfo;// int numOfInfo 배열에 저장된 요소의 개수

	private PhoneBookManager(int N) {// 싱글톤
		books = new PhoneInfo[N];
		kb = new Scanner(System.in);
		numOfInfo = 0;
	}

	public static PhoneBookManager getInstance() {
		return manager;
	}

	void searchInfo() {
		String name = kb.nextLine();
		for (int i = 0; i < numOfInfo; i++) {
			if (books[i].checkName(name))
				books[i].showBasicInfo();
		}
	}

	void editInfo() {
		String editName = kb.nextLine();
		int index = searchIndex(editName);

		if (index < 0) {
			System.out.println("[수정하고자 하는 이름의 정보가 없습니다]");
			System.out.println("------------------------------------------------------------------------------------");
			return;
		} else {

			while (true) {
				System.out.println("[수정 데이터 입력을 시작합니다]");
				System.out.println("[이름은 " + editName + "입니다]");
//			String name = kb.nextLine();//이름은 바꾸면 안됨
				System.out.println("[전화번호를 입력해주세요]");
				String phoneNumber = kb.nextLine();
				System.out.println("[주소를 입력해주세요]");
				String addr = kb.nextLine();
				System.out.println("[이메일을 입력해주세요]");
				String email = kb.nextLine();

				try {
					if (phoneNumber.trim().isEmpty() || addr.trim().isEmpty() || email.trim().isEmpty()) {
						UserException e = new UserException("예외 발생");
						throw e;
					}
				} catch (UserException e) {
					System.out.println("정보를 입력하지 않았습니다\n다시 입력해주세요");
					continue;
				} catch (Exception e) {
					System.out.println("정보를 입력하지 않았습니다\n다시 입력해주세요");
					continue;
				} finally {
					kb.hasNextLine();
				}
				PhoneInfo info = null;

				if (books[index] instanceof PhoneCompanyInfo) {
					System.out.println("[회사를 입력해주세요]");
					String company = kb.nextLine();
					System.out.println("[부서를 입력해주세요]");
					String dept = kb.nextLine();
					System.out.println("[직책을 입력해주세요]");
					String job = kb.nextLine();
					info = new PhoneCompanyInfo(editName, phoneNumber, addr, email, company, dept, job);
				} else if (books[index] instanceof PhoneClubInfo) {
					System.out.println("[동호회 이름을 입력해주세요]");
					String cafeName = kb.nextLine();
					System.out.println("[닉네임을 입력해주세요]");
					String nickName = kb.nextLine();
					info = new PhoneClubInfo(editName, phoneNumber, addr, email, cafeName, nickName);
				}
				books[index] = info;
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;
			}
		}

	}

	void deleteInfo() {
		System.out.println("[삭제하고자 하는 이름을 입력해주세요]");
		String name = kb.nextLine();
		int index = searchIndex(name);
		if (index < 0) {
			System.out.println("[삭제하고자 하는 이름의 정보가 없습니다]");
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

	void addInfo(PhoneInfo p) {
		books[numOfInfo++] = p;
	}

	void createInfo() {
//	int select=sc.nextInt();
//	sc.nextLine();
		int select = 0;
		while (true) {
			System.out.println("1.대학 2.회사 3.동호회");
			System.out.println("[입력하고자 하는 번호를 입력해주세요]");
			System.out.println("------------------------------------------------------------------------------------");

			try {
				select = Integer.parseInt(kb.nextLine());// 공백 문제 해결
				if (!(select >= Choice.UNIV && select <= Choice.CAFE)) {
					UserException e = new UserException("입력 문제 발생");
					throw e;
				}
	

			} catch (NumberFormatException e) {
				System.out.println("[올바른 번호를 입력해주세요]");
				continue;
			} catch (UserException e) {
				System.out.println("[올바른 번호를 입력해주세요]");
				continue;
			} catch (Exception e) {
				System.out.println("[올바른 번호를 입력해주세요]");
				continue;
			} finally {
//				kb.hasNextLine();
			}
			break;
		}

		while (true) {
			// 기본 정보 수집:이름,전화번호,주소,이메일
			System.out.println("[이름을 입력해주세요]");
			String name = kb.nextLine();
			System.out.println("[전화번호를 입력해주세요]");
			String phoneNumber = kb.nextLine();
			System.out.println("[주소를 입력해주세요]");
			String addr = kb.nextLine();
			System.out.println("[이메일을 입력해주세요]");
			String email = kb.nextLine();
			// 공백 입력시 사용자 예외처리
//		System.out.println("select:" + select);
			try {
				if (name.trim().isEmpty() || phoneNumber.trim().isEmpty() || addr.trim().isEmpty()
						|| email.trim().isEmpty()) {
					EmptyException e = new EmptyException("오류 발생");
					throw e;
				}
			} catch (EmptyException e) {
				System.out.println("정보를 입력하지 않았습니다.\n다시 입력하세요");
				continue;
			} catch (Exception e) {
				System.out.println("정보를 입력하지 않았습니다.\n다시 입력하세요");
				continue;
			} finally {
//				kb.hasNextLine();
			}
			PhoneInfo info = null;
			switch (select) {
			case Choice.UNIV:
				System.out.println("[전공을 입력해주세요]");
				String major = kb.nextLine();
				System.out.println("[학점을 입력해주세요]");
				String grade = kb.nextLine();
				info = new PhoneUnivInfo(name, phoneNumber, addr, email, major, grade);
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;

			case Choice.COMPANY:
				System.out.println("[회사를 입력해주세요]");
				String company = kb.nextLine();
				System.out.println("[부서를 입력해주세요]");
				String dept = kb.nextLine();
				System.out.println("[직책을 입려해주세요]");
				String job = kb.nextLine();
				info = new PhoneCompanyInfo(name, phoneNumber, addr, email, company, dept, job);
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;

			case Choice.CAFE:
				System.out.println("[동호회 이름을 입력해주세요]");
				String cafeName = kb.nextLine();
				System.out.println("[닉네임을 입력해주세요]");
				String nickName = kb.nextLine();
				info = new PhoneClubInfo(name, phoneNumber, addr, email, cafeName, nickName);
				System.out.println(
						"------------------------------------------------------------------------------------");
				break;

			default:
				System.out.println("[정상적인 선택이 아닙니다]\n[메뉴를 다시 선택해주세요]");
				System.out.println(
						"------------------------------------------------------------------------------------");
				return;
			}
			addInfo(info);
			break;
		}

		// 입력범위에 따른 예외 처리

//		addInfo(new PhoneInfo(name, phoneNumber, addr, email));
	}

	int searchIndex(String name) {
		int searchIndex = -1;// 해당 인덱스를 찾지 못했을 경우 기본값
		for (int i = 0; i < numOfInfo; i++) {
			if (books[i].checkName(name)) {
				searchIndex = i;
				break;
			}
		}
		return searchIndex;
	}

	void showInfo() {
		System.out.println("[검색하실 이름을 입력해주세요]");
		String name = kb.nextLine();
		int index = searchIndex(name);
		if (index < 0) {
			System.out.println("[검색하신 이름의 정보가 없습니다]");
			System.out.println("------------------------------------------------------------------------------------");
		} else {
			System.out.println("------------------------------------------------------------------------------------");
			books[index].showBasicInfo();
			System.out.println("------------------------------------------------------------------------------------");
		}
	}

	void showAllInfo() {
		// for 반복문
		// for each 반복문
//		for(PhoneInfo p: books) {//데이터 삭제 후 인덱스값 그대로 남아 있기 때문에 오류 발생하기 때문에 for-each문 사용 불가
//			p.showAllInfo();
//		}
		System.out.println("------------------------------------------------------------------------------------");
		System.out.println("[전체 정보를 출력합니다]");
		for (int i = 0; i < numOfInfo; i++) {
			books[i].showBasicInfo();

		}
	}
}
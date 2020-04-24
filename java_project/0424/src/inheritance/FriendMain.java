package inheritance;

import java.util.Scanner;

public class FriendMain {

	public static void main(String[] args) {
		FriendInfoHandler handler = new FriendInfoHandler(10);
		int choice;

		while (true) {
			System.out.println("------------------------메뉴룰 선택해주세요");
			System.out.println("1.고교 친구 저장");
			System.out.println("2.대학 친구 저장");
			System.out.println("3.기본 정보 출력");
			System.out.println("4.전체 정보 출력");
			System.out.println("5.종료");
			System.out.println("------------------------------------------------");

			choice = handler.sc.nextInt();
			handler.sc.hasNextLine();// 앞의 버퍼를 클리어 목적
			switch (choice) {
			case 1: case 2:
				handler.addFriend(choice);
				break;
			case 3:
				handler.showAllSimpleData();
				break;
			case 4:
				handler.showAllData();
				break;
			case 5:
				System.out.println("프로그램을 종료합니다");
				return;
			}

		}
	}

}

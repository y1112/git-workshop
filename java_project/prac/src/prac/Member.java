package prac;

public class Member {

	final int num; // 상수 num 선언
	String name;

	public Member(int num, String name) {
		this.num = num;
		this.name = name;
	}

	public int getNum() {
		return num;
	}

	public String toStirng() {
		return String.format("번호:%d 이름:%s", num, name);
	}
}

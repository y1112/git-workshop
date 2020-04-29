package ver05;

public class PhoneCafeInfo extends PhoneInfo {
	String cafeName;
	String nickName;

	PhoneCafeInfo(String name, String phoneNumber, String addr, String email, String cafeName, String nickName) {
		super(name, phoneNumber, addr, email);
		this.cafeName = cafeName;
		this.nickName = nickName;
	}

//public 메서드 상속
	@Override
	public void showAllInfo() {
//		super.showAllInfo();
		showBasicInfo();
		System.out.println("동호회 이름:" + this.cafeName);
		System.out.println("닉네임:" + this.nickName);
	}
}

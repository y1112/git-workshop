package phonebook_ver07_with_DAO;

public class PhoneClubInfo extends PhoneInfo implements PhoneInfoImpl {
	String cafeName;
	String nickName;

	PhoneClubInfo(int idx,String name, String phoneNumber, String addr, String email, String cafeName, String nickName) {
		super(idx, name, phoneNumber, addr, email);
		this.cafeName = cafeName;
		this.nickName = nickName;
	}

	@Override
	public void showBasicInfo() {
		super.showBasicInfo();
		System.out.println("��ȣȸ �̸�:" + this.cafeName);
		System.out.println("�г���:" + this.nickName);
	}

	@Override
	public void showAllInfo() {
		super.showBasicInfo();
	}
}
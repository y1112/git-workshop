package phonebook_ver07_with_DAO;
/* ��������: PhoneInfo Ŭ������ id��(pk) �߰���
 * ��������: 20/06/02
 * 
 * */

public abstract  class PhoneInfo{
	private int idx;
	private String name;
	private String phoneNumber;
	private String addr;
	private String email;

	PhoneInfo(int idx, String name, String phoneNumber, String addr, String email) {
		this.idx=idx;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.addr = addr;
		this.email = email;
	}
	
	public void showBasicInfo() {
		System.out.println("�ε�����ȣ:" + this.idx);
		System.out.println("�̸�:" + this.name);
		System.out.println("��ȭ��ȣ:" + this.phoneNumber);
		System.out.println("�ּ�:" + this.addr);
		System.out.println("����:" + this.email);
	}

	public void showAllInfo() {
		showBasicInfo();
	}
//	public void showAllInfo() {
//		showBasicInfo();
//	}

	public int getIdx() {
		return idx;
	}

	public void setIdx(int idx) {
		this.idx = idx;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	boolean checkName(String name) {
		boolean check = false;
		if (name.equals(this.name))
			check = true;
		return check;
	}
}
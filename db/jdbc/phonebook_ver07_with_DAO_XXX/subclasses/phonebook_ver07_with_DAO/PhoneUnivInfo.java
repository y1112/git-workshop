package phonebook_ver07_with_DAO;


public class PhoneUnivInfo extends PhoneInfo{
	String major;
	String grade;

	PhoneUnivInfo(int idx,String name, String phoneNumber, String addr, String email,
			String major, String grade) {
		super(idx, name, phoneNumber, addr, email);
		this.major = major;
		this.grade = grade;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	@Override
	public void showBasicInfo() {
		super.showBasicInfo();
		System.out.println("����:" + this.major);
		System.out.println("�г�:" + this.grade);
	}

	@Override
	public void showAllInfo() {
		super.showBasicInfo();
	}

}
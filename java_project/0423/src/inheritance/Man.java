package inheritance;

public class Man {
	String name;

//	Man() {
//	}

	Man(String name) {
		this.name = name;
	}
	
	void tellYourName() {
		System.out.println("나의 이름은 "+name+"입니다");
	}
}

package example;

public class assignment7 {

	public static void main(String[] args) {
		String value = "12o34";
		char ch = ' ';
		boolean isNumber = true;
		for (int i = 0; i < value.length(); i++) {
			ch = value.charAt(i);
			if (ch < '0' || ch > '9') {
				System.out.println(value + "는 숫자가 아닙니다.");
				isNumber = false;
				break;
			}

		}

		if (isNumber == true)
			System.out.println(value + "는 숫자입니다.");
	}

}
